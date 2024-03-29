---
layout: post
title:  "5. 동적SQL"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

- 동적 SQL을 만들기 위해 마이바티스가 제공하는 XML 엘리먼트
  - if
  - choose(when, otherwise)
  - trim(where, set)
  - foreach
- OGNL 표현식 사용
  - jsp의 JSTL표현식이 OGNL
  - Object Graph Navigation Language
  - 프로퍼티의 값을 가져오거나 설정하기 위한 언어
    > <c:if test="${obj.val!=null}"\>   
  - 기본 문법
  - coment 객체의 userid 필드 : comment.userid
  - 현재 객체의 hashcode()메서드 호출 : hashCode()
  - 댓글 배열(comments)의 첫번째 인덱스 값 : comments[0]   

## if 엘리먼트   

```html
<select id="selectCommentByCondition" parameterType="hashmap" resultType="Comment">
  SELECT
    comment_no,
    user_id,
    comment_content,
    reg_date
  FROM comment2
  <if test="commentNo != null">
    WHERE comment_no = #{commentNo}
  </if>
</select>
```
- commentNo가 null이 아닐 경우에먼 where 조건이 추가된다   

## choose(when, otherwise) 엘리먼트   

```html
<select id="selectCommentByConditionChoose" parameterType="hashmap" resultType="Comment">
  SELECT comment_no, user_id, comment_content, reg_date
  FROM comment2
  <choose>
    <when test="commentNo != null">
      WHERE comment_no = #{commentNo}
    </when>
    <when test="user != null and user.userId != null">
      WHERE user_id = #{user.userId}
    </when>
    <otherwise>
      WHERE comment_no = 1
      AND user_id = 'fromm0'
    </otherwise>
  </choose>
</select>
```

- 댓글번호가 있으면 해당 댓글 조회
- 작성자 정보가 있고 사용자 아이디가 있으면 해당 댓글 조회
- 둘다 아니라면 댓글번호는 1, 작성자 아이디는 fromm0인 댓글만 조회   

## trim(where) 엘리먼트
- if 엘리먼트의 단점을 보완할 수 있는 기능 제공
#### if 조건이 두개일 경우   

```html
<select id="selectCommentByConditionIf" parameterType="hashmap" resultType="Comment">
  SELECT comment_no, user_id, comment_content, reg_date
  FROM comment2
  <if test="commentNo != null">
    where comment_no = #{commentNo}
  </if>
  <if test="user != null and user.userId != null">
    where user_id = #{user.userId}
  </if>
</select>
```
- 댓글번호가 있고, 작성자와 아이디가 모두 있을때 결과는
  > select comment_no, user_id, comment_content, reg_date from comment2 where comment_no=#{commentNo} where user_id=#{user.userId}   
- where 구문이 두번 나오며 에러가 난다   


#### trim을 사용할 경우   

```html
<select id="selectCommentByConditionIf" parameterType="hashmap" resultType="Comment">
  SELECT comment_no, user_id, comment_content, reg_date
  FROM comment2
  <where>
    <if test="commentNo != null">
      comment_no = #{commentNo}
    </if>
    <if test="user != null and user.userId != null">
      AND user_id = #{user.userId}
    </if>
  </where>
</select>

```

- where 엘리먼트에서 생성된 내용이 있을 경우 자동으로 where을 붙여주며, 없을 경우 무시
- 하위 내용이 AND나 OR로 시작할 경우 자동으로 단어를 지워준다
- 조회 조건중 AND나 OR이 아닌 다른 구문으로 시작한다면 prefixOverrides를 통해 규칙을 추가 가능   

```html
<select id="selectCommentByConditionTrim" parameterType="hashmap" resultType="Comment">
  SELECT comment_no, user_id, comment_content, reg_date
  FROM comment2
  <trim prefix="WHERE" prefixOverrides="AND |OR ">
    <if test="commentNo != null">
      AND comment_no = #{commentNo}
    </if>
    <if test="user != null and user.userId != null">
      AND user_id = #{user.userId}
    </if>
  </trim>
</select>
```


  
- 하위 속성
  - prefix
    - 처리 후 엘리먼트의 내용이 있으면 가장 앞에 붙여준다
  - prefixOverrides
    - 처리 후 엘리먼트 내용 중 가장 앞에 해당 문자들이 있다면 자동으로 지워준다
  - suffix
    - 처리 후 엘리먼트 내에 내용이 있으면 가장 뒤에 붙여준다
  - suffixOverrides
    - 처리 후 엘리먼트 내용 중 가장 뒤에 해당 문자들이 있다면 자동으로 지워준다   
  
## foreach엘리먼트   
- sql문의 in 절   

```html
<select id="selectCommentByConditionForeach" parameterType="hashmap" resultType="Comment">
  SELECT comment_no, user_id, comment_content, reg_date
  FROM comment2
  <trim prefix="WHERE" prefixOverrides="AND |OR ">
    <if test="commentNos != null">
      comment_no IN 
      <foreach collection="commentNos" item="commentNo" 
        index="index" open="(" close=")" separator=",">
        #{commentNo}
      </foreach>
    </if>
  </trim>
</select>
```

```sql
select commnet_no, user_id, comment_content, reg_date
from comment2
where comment_no in(?)
```

- 속성
  - collection : 값 목록을 가진 객체를 설정, 배열이나 List
  - item : 목록에서 각각의 값을 사용하고자 할 때 사용
  - index : 몇번째 값인지 나타내는 인덱스 값
  - open : 목록에서 값을 가져와서 설정할 때 가장 앞에 붙여주는 문자, in절의 (
  - close : in절의 )
  - separator : 값들 사이에 붙여주는 문자, in절의 쉼표   

## set 엘리먼트
- update에서 마지막으로 명시된 컬럼의 표기에서 쉼표를 제거해준다   

```html
<update id="updateCommentIf" parameterType="Comment"> 
  UPDATE comment2 
  <set> 
    <if test="commentContent != null">comment_content = #{commentContent},</if>
    <if test="regDate != null">reg_date = #{regDate},</if>
  </set> 
  WHERE comment_no = #{commentNo}; 
</update>

```

