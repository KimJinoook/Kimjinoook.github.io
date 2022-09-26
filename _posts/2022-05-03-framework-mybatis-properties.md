---
layout: post
title:  "3. 설정파일"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

### 예시파일   

```html
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
  
  <!-- 외부 프로퍼티 파일 로드및 공통 프로퍼티 정의 -->
  <properties resource="mybatis.properties">
  <!--properties url="file:d:\mybatis.properties"-->
    
    <property name="jdbc.driver" value="oracle.jdbc.driver.OracleDriver" />
    <!-- <property name="jdbc.url" value="jdbc:oracle:thin:@303-0:1521:xe" /> -->
    <property name="jdbc.username" value="herb" />
    <property name="jdbc.password" value="herb" />
  </properties>

  <!-- 마이바티스의 작동 규칙정의 -->
  <settings>
    <setting name="cacheEnabled" value="false" />
    <setting name="useGeneratedKeys" value="true" />
    <setting name="mapUnderscoreToCamelCase" value="true" />
    <setting name="autoMappingBehavior" value="PARTIAL" />
  </settings>

  <!-- 타입별칭 -->
  <typeAliases>
    <typeAlias type="ldg.mybatis.model.Comment" alias="Comment" />
    <typeAlias type="ldg.mybatis.model.User" alias="User" />
    <typeAlias type="ldg.mybatis.model.Reply" alias="Reply" />
    <typeAlias type="ldg.mybatis.model.CommentUser" alias="CommentUser" />
    <typeAlias type="ldg.mybatis.model.CommentReplies" alias="CommentReplies" />
  </typeAliases>

  <!-- 매퍼정의 -->
  <mappers>
    <mapper resource="ldg/mybatis/repository/mapper/CommentMapper.xml" />
    <mapper resource="ldg/mybatis/repository/mapper/CommentMapperResultMap.xml" />
    <mapper resource="ldg/mybatis/repository/mapper/CommentMapperDynamicSql.xml" />
  </mappers>

  <!-- 데이터베이스 및 트랜잭션 관리자 -->
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
    <environment id="release">
      <transactionManager type="JDBC" />
      <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driver}" />
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />
      </dataSource>
    </environment>
  </environments>
</configuration>
```

## Properties 엘리먼트
- 공통적인 속성을 정의하거나 외부 파일에서 값을 가져와 사용해야 하는 경우 사용
- 프로젝트는 개발장비와 운영장비로 구분해 사용하는 경우가 많다
  - 서버 구분 없이 동일한 값은 그대로 설정파일에 설정
  - 서버별로 다른 값은 외부 프로퍼티 파일로 분리 후 서버별로 프로퍼티 파일을 선택해 배포
- 외부 프로퍼티 파일을 읽기 위해서 resource 속성에 위치 지정
  - 클래스패스 기준으로 프로퍼티 파일을 찾는다
- 공통 속성은 하위 엘리먼트인 property 엘리먼트를 이용해 선언할 수 있다   

```html
<properties resource="mybatis.properties">
  <property name="jdbc.driver" value="oracle.jdbc.driver.OracleDriver" />
  <property name="jdbc.username" value="herb" />
  <property name="jdbc.password" value="herb123" />
</properties>
```

## settings 엘리먼트
- 생성되는 SqlSession 객체의 특성을 결정한다
- cacheEnabled : 캐시를 기본으로 사용할지 결정, 디폴트 true
- useGeneratedKeys : 생성 키 사용여부 결정, 디폴트 false
  - MySql : auto_increment
  - oracle : sequence
  - sql서버 : identity
- mapUnderscoreToCamelCase : 언더바 형태를 카멜케이스로 자동 매핑할지 결정, 디폴트 false   

```html
<settings>
  <setting name="cacheEnabled" value="false"/>
  <setting name="useGeneratedKeys" value="true"/>
  <setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```

## typeAliases 엘리먼트
- 매핑 구문의 파라미터나 결과 타입을 지정할 때 타입별 별칭을 설정할 수 있다   

```html
<typeAliases>
  <typeAlias type="ldg.mybatis.model.Comment" alias="Comment" />
  <typeAlias type="ldg.mybatis.model.User" alias="User" />
  <typeAlias type="ldg.mybatis.model.Reply" alias="Reply" />
  <typeAlias type="ldg.mybatis.model.CommentUser" alias="CommentUser" />
  <typeAlias type="ldg.mybatis.model.CommentReplies" alias="CommentReplies" />
</typeAliases>

```

- 마이바티스는 어노테이션으로 설정하는 방법을 추가로 제공   

```java
package ldg.mybatis.model;
@Alias("Comment")
public class Comment {
}
```

## environments 엘리먼트
- 마이바팃의 트랜잭션 관리자와 데이터 소스 2가지를 설정할 수 있다
- 스프링 연동 모듈을 사용할 경우에는 필요없다
- transactionManager
  - 트랜잭션 관리자 클래스를 설정
  - type 속성
    - jdbc : 마이바티스 api에서 제공하는 커밋, 롤백 메서드 등을 사용해 트랜잭션을 관리
    - MANAGED : 컨테이너가 직접 트랜잭션을 관리하는 방식
- dataSource
  - 데이터 소스를 설정
  - 데이터 소스는 데이터베이스의 정보를 갖는 객체
  - type 속성
    - UNPOOLED, POOLED, JNDI
    - POOLED : 일정수의 데이터베이스 연결을 풀이라는 메모리 영역에 넣어두고, 필요할 때마다 가져다가 사용한 후, 다시 풀에 넣는다
    - UNPOOLED : 데이터베이스에 요청할 때마다 데이터베이스 연결을 새롭게 생성하고 처리 후 완전히 해제한다
    - JNDI : 컨테이너의 JNDI 컨텍스트를 참조한다
      - Java Naming and Directory Interface
      - 디렉토리 서비스를 위해 자바가 제공하는 인터페이스
      - 산재된 정보를 쉽게 찾을 수 있게 해준다
      - 톰캣과 같은 대부분의 웹 서버가 제공   

```html
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
```

## mappers 엘리먼트
- 매퍼를 지정하는 엘리먼트
- 지정 방법
  - 클래스패스에 위치한 XML 매퍼파일 지정 (resource 속성)
  - URL을 사용한 XML파일 지정 (url 속성)
  - 매퍼 이너페이스를 사용하는 인터페이스 위치 지정 (class 속성)
  - 패키지 지정으로 패키지 내 자동으로 매퍼 검색 (name 속성)   

```html
<mappers>
  <mapper resource="ldg/mybatis/repository/mapper/CommentMapper.xml" />
  <mapper url="file:///ldg/mybatis/repository/mapper/CommentMapperResultMap.xml" />
  <mapper class="ldg.mybatis.repository.mapper.CommentMapper" />
  <packgae name="ldg.mybatis" />
</mappers>
```

- 스프링 연동 모듈 사용 시 mapperLocations 프로퍼티를 사용해 매퍼 위치 지정 가능   

```html
<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
  <property name="typeAliasesPackage" value="ldg.mybatis.model" />
  <property name="dataSource" ref="dataSource" />
  <property name="configLocation" value="classpath:/mybatis-config.xml" />
  <property name="mapperLocations">
    <array>
      <value>classpath:/ldg/mybatis/repository/mapper/CommentMapper.xml</value>
      <!-- <value>classpath*:/ldg/mybatis/repository/mapper/**/*.xml</value> -->
    </array>
  </property>
</bean>
```
