---
layout: post
title:  "1. 마이바티스란"
subtitle:   ""
categories: framework
tags: mybatis
comments: false
header-img: 
---

- JDBC를 대체하는 퍼시스턴스 프레임워크
- 내부적으로 JDBC API를 사용
  - 개발자가 직접 JDBC를 사용할 때의 중복작업을 대부분 자동화
- SQL을 별도의 XML이나 어노테이션으로 정의
  - SQL의 관리가 편리
- 특징
  - JDBC를 작성할 때의 try/catch 구문 사용 불필요
  - 객체 프로퍼티를 Prepared 구문의 파라미터로 자동 매핑
  - 조회결과를 객체로 자동 매핑
  - 트랜잭션 관리
  - 데이터 매퍼
    - 객체와 DB, 매퍼 자체를 독립적으로 유지하면서, 객체와 DB간 데이터 이동
    - VO에 해당하는 부분이 SQL 구문에 매핑
    - SQL 실행결과도 VO로 매핑
      - 일일이 ResultSet으로 꺼낼 필요가 없다
    - 예   
 

```sql

--member 테이블

create table member(
  id number primary key,
  pwd varchar2(10),
  name varchar2(20)
);
```
```java
//MemberVO 클래스
public class MemberVO{
  private int id;
  private String pwd;
  private String name;
}
```
```html
<!--매핑파일 member-mapping.xml-->
<select id="getMember" resultType="MemberVO" parameterType="java.lang.String">
    select id, pwd, name, tel from member where id=#{id}
</select>
```
```java
MemberVO vo = (MemberVO)sqlSession.selectOne("getMember",pwd);   
```   


***

# mybatis 구성 요소
- 설정파일(mybatis-config.xml)
  - 데이터베이스 설정과 트랜잭션 등 마이바티스가 동작하는 규칙을 정의
- 매퍼
  - sql을 xml에 정의한 매퍼XML파일과 SQL을 인터페이스마다 어노ㅔ이션으로 정의한 매퍼 인터페이스
- 결과매핑과 매핑구문
  - 조회 결과를 자바 객체에 설정하는 규칙을 나타내는 결과매핑
  - SQL을 XML에 정의한 매핑 구문
- 지원하는 파라미터 타입, 결과타입
  - Map 객체, 자바모델클래스, 원시타입(int, String 등)
