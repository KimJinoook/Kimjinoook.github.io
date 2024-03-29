---
layout: post
title:  "1. 데이터베이스란"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

- 여러 응용시스템들이 공유할 수 있도록 통합 저장된 데이터의 집합
- 중복을 최소화, 다수의 사용자가 공유할 수 있도록 데이터를 모아둔 시스템
- 상호 공유가 가능해야한다
- 필요에 따라 데이터를 신속하게 검색할 수 있어야 한다
- 데이터의 독립성
  - 프로그램과 데이터 간의 독립성이 제공
- 데이터의 무결성
  - 데이터베이스 안의 데이터는 오류가 있어서는 안된다
- 한 조직체의 운영데이터 뿐만 아니라 데이터에 관한 설명까지 포함
  - 데이터베이스 스키마, 데이터베이스 구조
- 효율적으로 접근이 가능하고 질의가 가능
- 데이터베이스의 정의
  - integrated
    - 중복이 배제된 통합된 데이터의 집합
  - stored
    - 컴퓨터화되어 저장된 데이터
  - operational
    - 조직의 기능을 수행하는데 유지해야 할 운영데이터
  - shared
    - 한 조직에 있는 여러 응용시스템들이 공동으로 소유하고 유지하며 이용하는 공용데이터
- 장점
  - 데이터 중복 최소화
  - 데이터 공유
  - 무결성 유지
  - 일관성 유지
  - 데이터의 보안 보장
- 단점
  - 운영비 증대 : 만ㅇㅎ은 시스템 자원 요구
  - 자료 처리의 복잡 : 고급 프로그래밍 요구
  - 복잡한 예비와 회복 : 장애 발생 대비를 위한 작업 필요
  - 시스템의 취약성 : 시스템 성능에 따라 DBMS 성능이 좌우

## 1. 데이터베이스 관리시스템
### DBMS
- DBMS (date base management system)
  - 응용프로그램들이 데이터베이스를 공유할 수 있도록 관리해주고 유지하기 위한 소프트웨어 시스템
  - 데이터를 적절하고 효율적으로 관리하기 위한 체계적인 시스템
  - oracle, ms sql server, mysql 등
- 기능
  - 정의기능
    - 논리적 구조정의
      - 테이블 등의 논리적 구조를 정의
    - 물리적 구조정의
      - 실제 파일
    - 구조 매핑
      - 물리적 파일과 우리가 볼수 있는것을 연결
  - 조작기능
    - 데이터를 검색 삽입 수정 삭제할 수 있는 기능
  - 제어기능
    - 데이터의 정확성, 안정성을 유지하는 기능
    - 무결성, 보안

### RDBMS
- 관계형 데이터베이스
- 실체, 속성, 관계로 구성된 ER diagram 으로 표현
- 2차원 수평적 구조
  - 데이터들을 2차원 구조를 가진 테이블 형태로 저장
- 데이터의 무결성, 트랜잭션 처리등 기본적 기능에서 뛰어난 성능
- 질의어를 사용한 데이터 접근
- 구조
  - 필드-레코드-테이블
  - 필드
    - 테이블의 열에 해당 (컬럼)
  - 레코드
    - 필드들의 집합체, 테이블의 각 행
  - 인덱스
    - 데이터를 신속하게 찾을 수 있도록 정렬된 목록, 키
  - 테이블
    - 여러개의 레코드를 담고있는 논리적인 구조
    - 특정 주제와 목적으로 만들어지는 집합
    - 데이터베이스의 기본단위
    - 모든 자료는 테이블에 등록
- 관계내용의 정의
  - 관계란 업무적인 연관성
  - 두 테이블 사이에 존재, 부모 자식테이블로 연결
  - 부모테이블의 기본키는 자식테이블에 외래키로 

 
 ## 2. 스키마
 - user : 사용자, 오라클 서버에 접속하기 위해 사용하는 것이 user
 - schema : 특정 사용자(user)가 만들어 놓은 모든 object 집합
    - hr schema : 오라클 서버안에 hr 계정으로 로그인해서 만들어놓은 모든 것을 모아둔 것
    - 스키마이름.테이블이름
    - hr.emp : hr 스키마에 있는 emp 테이블, hr 사용자로 로그인해 있기 때문에 hr 생략

## 3. dba 계정 생성
```cmd
- cmd
- sqlplus / as sysdba
  - 관리자 권한 접속
  
- ALTER SESSION SET "_ORACLE_SCRIPT"=true;
- @?/demo/schema/human_resources/hr_main.sql

specify password for HR as parameter 1:
Enter value for 1: hr

specify default tablespace for HR as parameter 2:
Enter value for 2: users

specify temporary tablespace for HR as parameter 3:
Enter value for 3: temp

specify log path as parameter 4:
Enter value for 4: $ORACLE_HOME/demo/schema/log/

ALTER USER HR ACCOUNT UNLOCK IDENTIFIED BY hr123;
-- hr 계정 언락 및 비밀번호 hr123 설정

conn hr/hr123;
--계정연결 확인

```

## 4.오라클 내 권환 확인
```sql
-- sys계정에서 scott 사용자 계정 만들기
alter session set "_ORACLE_SCRIPT"=true;

create user scott
identified by scott123
default tablespace users;
    
-- 권한 부여하기
grant resource, connect to scott;


select * from dba_users
where username='HR'; --HR유저 확인

select * from dba_users
where username='SCOTT'; --SCOTT 유저 확인

select * from dba_sys_privs where grantee='HR'; --권한 조회
select * from dba_role_privs where grantee='HR'; --롤 조회


--scott 계정에서
select * from hr.v_emp; -- hr의 v_emp 권한이 없어 볼 수 없다

-- hr 계정에서
--hr계정의 뷰를 볼수있는 권한 scott에게 부여
grant select on v_emp to scott; --hr계정의 뷰 이므로 select 권한 부여 가능

--scott 계정에서
select * from hr.v_emp; -- 조회가능

--권한 제거시
--
revoke select on v_emp from scott;
```

## 5. SQL문
- structured query language
- 데이터 질의 및 처리언어
- 구조화된 언어
- 집합적, 선언적 언어
- ANSI, ISO 표준


### 종류
- DML : 데이터 조작어
  - date manipulation lang
  - 데이터를 조회, 저장, 삭제할때 이용
  - SELECT, INSERT, UPDATE, DELETE
- DDL : 데이터 정의어, 객체 조작어
  - data definition lang
  - 데이터베이스 객체를 생성하는데 이용
  - CREATE, DROP, ALTER, TRUNCATE
- DCL : 데이터 제어어
  - data control lang
  - 데이터 접근을 제어하는데 이용
  - GRANT, REVOKE
- TCL : 트랜잭션 제어어   



### 구성요소
- 예약어
- 연산자
  - 단항, 이항연산자
  - 수식,문자,논리, 계층형 쿼리, set연산자
- 의사컬럼
  - 모조,유령컬럼
  - rownum
  - rowid : 각 row가 저장된 주소 값
- 함수
  - 내장함수
  - 사용자 정의 함수
- 표현식
  - select 리스트
  - where, having의 조건절
  - order by, connect by, start with
  - insert문의 values 절
  - update문의 set절
- 조건
  - 하나이상의 표현식과 논리연산자가 결합
  - 결과가 true, false, unknown값을 반환하는 것
 
