---
layout: post
title:  "7. 매퍼 어노테이션"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

- 기존 스프링에서는 DAO 클래스에 @Repository 선언
  - 해당 클래스가 DB와 통신하는 클래스임을 명시
- 마이바티스의 매퍼 어노테이션 지정 시, XML mapper에서 메서드의 이름과 일치하는 SQL문을 찾아 실행
  - mapper 영역은 DB와 통신, SQL쿼리를 호출하는 것이 전부이며 로직은 필요하지 않다   

### DAO

```java
@Mapper
public interface BoardDAO {
  public int insertBoard(BoardVO vo);
  public List<BoardVO> selectAll(SearchVO searchVo);
  public int selectTotalRecord(SearchVO searchVo);
  public int updateReadCount(int no);
  public BoardVO selectByNo(int no);
  public int updateBoard(BoardVO vo);
  public int deleteBoard(BoardVO vo);
  public List<BoardVO> selectMainNotice();
}

```

### board.xml

```java
<mapper namespace="com.it.herb.board.model.BoardDAO">
  <insert id="insertBoard" parameterType="boardVO">
    <selectKey keyProperty="no" resultType="int" order="BEFORE">
      select board_seq.nextval from dual 
    </selectKey>
    insert into board(no,name, pwd, title, email, content)
    values(#{no} ,#{name}, #{pwd}, #{title}, #{email}, #{content})
  </insert>

```

### application.properties
```html
# DataBase
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@host:1521:xe
spring.datasource.username=name
spring.datasource.password=pw
spring.datasource.hikari.connection-test-query=SELECT sysdate FROM dual


#mapper location settings
mybatis.config-location=classpath:/config/mybatis/oracle/mybatis-config.xml
mybatis.mapper-locations=classpath:/config/mybatis/mapper/oracle/*.xml
mybatis.type-aliases-package=패키지명

#MyBatis
mybatis.configuration.map-underscore-to-camel-case=true

```
