---
layout: post
title:  "4. 매퍼"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

- 매퍼의 구성 엘리먼트
  - resultMap
  - sql
  - insert,update,delete
  - selectKey
  - select

## resultMap 엘리먼트
- 속성
  - id
    - 매핑 구문에서 결과 매핑을 사용할 때 구분하기 위한 아이디
  - type
    - 결과 매핑을 적용하는 대상 객체 타입
    - 매핑 구문의 결과 데이터를 갖는 자바 타입을 지정
    - 대개는 Map 이나 자바모델 클래스를 지정   

```html
<resultMap id="baseResultMap" type="Comment">
  <id column="comment_no" jdbcType="BIGINT" property="commentNo" />
  <result column="user_id" jdbcType="VARCHAR" property="userId" />
  <result column="reg_date" jdbcType="TIMESTAMP" property="regDate" />
  <result column="comment_content" jdbcType="VARCHAR" property="commentContent" />
</resultMap>
```

## sql 엘리먼트
- 각각의 매핑 구문에서 공통으로 사용할 수 있는 sql 문자열의 일부를 정의하고 재사용하기 위해 사용
- 별도로 빼둔 sql의 일부는 각각의 매핑 구문에서 include 엘리먼트를 사용해 처리
- sql 엘리먼트에는 정적인 내용뿐 아니라 동적 sql도 삽입 가능   

```html
<sql id="BaseColumns">
  comment_no AS commentNo,
  user_id AS userId,
  comment_content AS commentContent,
  reg_date AS regDate
</sql>

<select id="selectCommentByPrimaryKey" parameterType="long" resultType="ldg.mybatis.model.Comment">
  SELECT
  <include refid="BaseColumns"/>
  FROM comment2
  WHERE comment_no = #{commentNo}
</select>
```

## insert, update, delete 엘리먼트
- sql에서 각각 입력, 수정, 삭제를 위해 사용하는 엘리먼트   

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
  http://mybatis.org/dtd/mybatis-3-mapper.dtd>
  <mapper namespace="ldg.mybatis.repository.mapper.CommentMapper">
    <insert id="insertComment" parameterType="ldg.mybatis.model.Comment">
      INSERT INTO comment2(comment_no, user_id, comment_content, reg_date)
      VALUES (comment2_seq.nextval, #{userId}, #{commentContent}, #{regDate})
    </insert>
    <update id="updateComment" parameterType="ldg.mybatis.model.Comment">
      UPDATE comment2 SET comment_content = #{commentContent}
      WHERE comment_no = #{commentNo};
    </update>
    <delete id="deleteComment" parameterType="long">
      DELETE FROM comment2
      WHERE comment_no = #{commentNo};
    </delete>
</mapper>
```

- 하위 속성
  - id
    - 매핑 구문을 구분하는 아이디
    - SqlSession 객체의 구문아이디 파라미터로 사용
    - 같은 네임스페이스에서는 유일해야 한다
  - parameterType
    - 파라미터 객체의 타입
    - 원시 타입과 자바의 래퍼 객체에 대해 이미 정의된 펼칭이 있다
    - 개발자가 별도로 정의한 객체 사용 가능
    - 패키지 포함 전체 클래스명을 적가니 타입 별칭 사용 가능
  - flushCache
    - 매핑 구문을 실행할 때 캐시를 지울지 여부 설정, boolean
  - timecut
    - sql실행 후 응답을 기다리는 최대 시간
    - 대개 설정하지 않고 jdbc 드라이버 자체의 타임아웃값 사용
  - statementType
    - jdbc의 구문 타입을 지정
    - STATEMENT, PREPARED, CALLABLE중 선택
    - 디폴트 PREPARED
- insert만의 하위 속성
  - useGeneratedKeys
    - 생성 키 값을 만들기 위해 jdbc의 getGeneratedKeys 메소드를 호출할지 여부, 디폴트 false
  - keyProperty
    - jdbc의 getGeneratedKeys 메서드가 반환한 값이나 insert 구문의 하위 엘리머느인 selectKey 엘리먼트에 의해 반횐된 값을 설정할 프로퍼티 지정
  - keyColumn
    - 생성키를 가진 테이블의 컬럼명 설정
    - PostgreSQL처럼 키 컬럼이 페이블의 첫번째 컬럼이 아닌 DB에서만 필요   

## selectKey 엘리먼트
- 자동 생성 키의 값을 가져오기 위해 사용
- 자동 생성키
  - MySql의 auto_increment 속성
  - oracle의 sequence 속성
  - sql서버의 indentity 속성
- 방금 입력한 자동 생성키가 무슨값인지 입력과 동시에 알아내기 위한 기능   

```html
--oracle
<insert id="insertBoard" parameterType="boardVO">
  <selectKey resultType="int" keyProperty="no" order="BEFORE">
    select board_seq.nextval as no from dual
  </selectKey>
  insert into board (no, name, pwd, title, email, content)
  values(#{no},#{name},#{pwd},#{title},#{email}, #{content})
</insert>

--mysql
<insert id="insertBoard" parameterType="boardVO">
  <selectKey resultType="int" keyProperty="no" order="AFTER">
    select last_insert_id() 
    <!-- auto_increment 속성이 지정된 칼럼에 최근에 입력된 값을 반환하는 함수-->
  </selectKey>
  insert into board (name, pwd, title, email, content)
  values(#{name},#{pwd},#{title},#{email}, #{content})
</insert>

```

- keyProperty
  - selectKey 구문의 결과를 설정할 대상 프로퍼티
- resultType
  - 결과 타입 지정
- order
  - 생성 키를 가져오는 시점 결정
  - BEFORE : 키를 먼저 조회후, 값을 프로퍼티에 설정 후, insert 구문 실행
  - AFTER : insert 구문 실행 후 selectKey 구문 실행
- statementType
  - Stqtement, PrepatedStatement, CallableStatement 설정   

## select 엘리먼트   

```html
<select id="selectCommentByPrimaryKey" parameterType="long" resultType="Comment">
  SELECT
    comment_no AS commentNo,
    user_id AS userId,
    comment_content AS commentContent,
    reg_date AS regDate
  FROM comment2
  WHERE comment_no = #{commentNo}
</select>
```


