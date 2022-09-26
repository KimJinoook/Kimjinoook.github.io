---
layout: post
title:  "2. 마이바티스 사용 예"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

## 마이바티스 설정
- mybatis-config.xml
- 설정파일은 getConnection 메서드의 데이터베이스 연결정보를 대체한다
  - 트랜잭션 관리자
    - JDBC 코드를 대체하기 때문에 type은 JDBC로 지정
  - 데이터베이스 설정
    - 데이터베이스 연결 정보를 설정
  - 매퍼 정보 설정
    - sql을 선언해둔 xml이나 인터페이스 형태의 매퍼 위치를 지정
    - xml의 위치는 클래스패스를 기준으로 지정   

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
  <typeAliases>
    <typeAlias type="ldg.mybatis.model.Comment" alias="Comment" />
  </typeAliases>
  
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC" />
      <dataSource type="POOLED">
        <property name="driver" value="oracle.jdbc.driver.OracleDriver" />
        <property name="url" value="jdbc:oracle:thin:@user-00:1521:orcl" />
        <property name="username" value="herb" />
        <property name="password" value="herb123" />
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="ldg/mybatis/repository/mapper/CommentMapper.xml" />
  </mappers>
</configuration>

```

***

## 마이바티스 객체 생성
- 설정파일을 로드해 마이바티스 객체 생성
- 마이바티스 객체는 SQL선언을 제외하고 JDBC 코드가 처리하던 대부분을 내부적으로 처리해준다   

```java
public class CommentSessionRepository {
  private SqlSessionFactory getSqlSessionFactory() {
    String resource = "mybatis-config.xml";
    InputStream inputStream;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
    return new SqlSessionFactoryBuilder().build(inputStream);
  }
}
```
- 마이바티스 사용 시 getSqlSessionFactory() 메서드를 사용해 객체를 생성한 후 api 호출 가능
- inputStream = Resources.getResourceAsStream(resource);
  - 설정 파일 위치를 지정해 마이바티스 설정 정보에 대한 객체를 생성한다
  - 설정파일은 클래스패스를 기준으로 파일명을 적어주면 된다
- new SqlSessionFactoryBuilder().build(inputStream);
  - SqlSessionFactory 객체 생성
  - 전반적인 정보를 갖는 특성으로 인해 애플리케이션 내에서 한개만 생성되어야 한다   

***

## 파라미터 표기법
- #{파라미터명}
- 값을 설정할 때 사용
- 자바빈의 경우 프로퍼티명
- Map객체의 경우 key값
- 파라미터의 값이 1개인 원시타입인 경우 #{}안에 아무 이름이나 적어도 가능
- 파라미터 타입과 컬럼의 타입을 자동으로 처리하지는 않는다
  - Date와 가은 일부 타입은 명시적으로 지정해줘야 한다
  - #{regdate,javaType=Date,jdbcType=TIMESTAMP}   

***

## 구성요소
- 매핑구문
  - 별도 xml이나 어노테이션에서 선언한 SQL
- 매퍼xml
  - 매핑구문을 xml에 선언한 경우
  - XML과 DOCTYPE 선언
  - 매퍼 네임스페이스
    - 매핑 구문들의 그룹
    - 반드시 선언
    - 매핑구문 아이디가 겹치더라도 네임스페이스가 다르면 사용 가능
  - 매핑구문
    - jdbc 코드에서 가져온 sql과 xml 속성으로 구성
    - xml 속성
      - id : 마이바티스에서 이 SQL을 사용하기 위한 id
      - parameterType : 파라미터 타입, 주로 자바빈이나 MAP 사용
      - resultType : 결과 데이터 타입
      - SQL 종류에 따라 select 등의 엘리먼트 사용   

***

## 트랜잭션 관리
- 마이바티스의 중요 객체는 SqlSessionFactory 객체
- 각각의 세부작업은 SqlSessionFactory에서 만들어지는 SqlSession 객체가 담당
- SqlSession 객체를 생성하는 시점에 트랜잭션 관련 속성을 설정
- openSession() 메서드
  - SqlSessionFactory 클래스가 제공하는 메서드
  - 마이바티스 설정파일을 사용하는 SqlSession 객체 생성
  - 객체를 생성할 때마다 트랜잭션 시작
  - 트랜잭션에 관련한 격리 레벨이나 전파설정은 설정한 값을 사용해 설정
  - PreparedStatement는 재사용되지 않고, 배치 형태로 처리하지 않는다
- commit(), rollback()
  - SqlSession 클래스가 제공하는 메서드
  - 메서드를 호출함과 동시에 트랜잭션 종료
  - 스프링 연동 모듈 사용시, 트랜잭션에 대한 제어는 마이바티스가 담당하지 않고, 스프링에 위임   

***

## 예시
### mybatis-config.xml   

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
  <!-- 외부 프로퍼티 파일 로드 및 공통 프로퍼티 정의 -->
  <properties resource="mybatis.properties">
    <property name="jdbc.driver" value="oracle.jdbc.driver.OracleDriver" />
    <property name="jdbc.username" value="scott" />
    <property name="jdbc.password" value="tiger" />
  </properties>

  <!-- 타입별칭 -->
  <typeAliases>
    <typeAlias type="com.pd.model.PdVO" alias="PdVO" />
  </typeAliases>
  
  <!-- 데이터베이스및 트랜잭션 관리자 -->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC" />
      <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
      </dataSource>
    </environment>
  </environments>

  <!-- 매퍼정의 -->
  <mappers>
    <mapper resource="com/mybatis/mapper/pd.xml" />
  </mappers>
</configuration>
```

### AbstractRepository   

```java
package com.mybatis.config;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class AbstractRepository {
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
      setSqlSessionFactory();
    }
    
    private static void setSqlSessionFactory() {
      String resource = "mybatis-config.xml";
      InputStream inputStream;
      try {
        inputStream = Resources.getResourceAsStream(resource);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    protected SqlSessionFactory getSqlSessionFactory() {
      return sqlSessionFactory;
    }
}

```

### pd.xml   

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mybatis.mapper.pd">
  <insert id="pdInsert" parameterType="pdVo">
    <selectKey keyProperty="no" resultType="int" order="BEFORE">
      select pd_seq.nextval as no from dual
    </selectKey>
    insert into pd(no, pdname, price)
    values(#{no}, #{pdName}, #{price})
  </insert>
  
  <select id="pdList" resultType="pdVo">
    select * from pd order by no desc
  </select>

  <select id="pdDetail" parameterType="int" resultType="pdVo">
    select * from pd where no=#{no}
  </select>

  <update id="pdUpdate" parameterType="pdVo">
    update pd set pdname=#{pdName}, price=#{price}
    where no=#{no}
  </update>

  <delete id="pdDelete" parameterType="int">
    delete from pd where no=#{no}
  </delete>
</mapper>
```

### PdVO

```java
package com.pd.model;
import java.io.Serializable;
import java.sql.Timestamp;

public class PdVO{
  //멤버변수
  private int no;
  private String pdName; 
  private int price; 
  private Timestamp regdate;
  ... 
}
```

### PdDAO   

```java
package com.pd.model;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.mybatis.config.AbstractRepository;

public class PdDAO extends AbstractRepository{
  
  private final String namespace = "com.mybatis.mapper.pd";
  private SqlSession sqlSession;

  public int insertPd(PdVO pdVo) {
    //pd 테이블에 insert하는 메서드
    sqlSession = getSqlSessionFactory().openSession();
    try{
      int n = (int) sqlSession.insert(namespace + ".pdInsert", pdVo);
      if (n > 0) {
        sqlSession.commit();
      }
      return n;
    } finally {
      sqlSession.close();
    }
  }

  public List<PdVO> selectAll() {
    //전체 글을 조회하는 메서드
    sqlSession = getSqlSessionFactory().openSession();
    try{
      List<PdVO> alist= sqlSession.selectList(namespace + ".pdList");
      return alist;
    } finally {
      sqlSession.close();
    }
  }

  public PdVO selectByNo(int no) {
    //no에 해당하는 글 조회
    sqlSession = getSqlSessionFactory().openSession();
    try{
      PdVO bean = (PdVO) sqlSession.selectOne(namespace + ".pdDetail", no);
      return bean;
    } finally {
      sqlSession.close();
    }
  }

  public int updatePd(PdVO bean) {
    sqlSession = getSqlSessionFactory().openSession();
    try{
      int n= sqlSession.update(namespace + ".pdUpdate", bean);
      if (n > 0) {
        sqlSession.commit();
      }
      return n;
    } finally {
      sqlSession.close();
    }
  }

  public int deletePd(int no) {
    //no에 해당하는 상품 삭제
    sqlSession = getSqlSessionFactory().openSession();
    try{
      int n=sqlSession.delete(namespace + ".pdDelete", no);
      if (n > 0) {
        sqlSession.commit();
      }
      return n;
    } finally {
      sqlSession.close();
    }
  }
}//class

```
