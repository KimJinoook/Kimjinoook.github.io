---
layout: post
title:  "12. SYS계정 사용자 관리"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

## 1. 사용자 계정
- sys,system : 오라클 데이터베이스 관리자
  - DBA권한 자동 할당
  - sys
    - 데이터 딕셔너리 소유자
  - system
    - 모든 권한이 sys와 같으나 데이터베이스 생성 권한 없음
- user
  - 사용자, 오라클 서버에 접속하기 위해 사용하는 것이 user
- schema
  - 특정 사용자가 만들어놓은 모든 오브젝트 집합

## 2. 오라클의 논리적 저장구조
- 오라클
  - 데이터관리
  - 데이터를 저장, 추출, 삭제 변경하는 작업
  - 데이터는 파일에 저장
- 오라클 데이터베이스
  - 데이터파일들을 가지고 있다
  - 오라클 내부에서는 논리적인 개념으로 데이터 관리
    - 데이터블록 : 오라클에서 데이터를 저장하는 가장 최소의 논리적 단위
      - 사이즈는 2k,4k,8k,16k,32k,64k, 기본 8k
    - 익스텐트 : 8개의 데이터 블록이 모여 익스텐트가 된다
    - 세그먼트 : 익스텐트가 모여 세그먼트가 된다
    - 테이블스페이스 : 세그먼트가 모여서 테이블스페이스가 된다
      - 테이블, 인덱스, 프로시저, 뷰등 여러 객체 저장
    - 데이터베이스 : 테이블스페이스들이 모인 것
  - 물리적인 데이터파일(.dbf, .ora)은 테이블 스페이스와 대응

## 3. 테이블 스페이스 생성
#### 정보 조회   

```sql
--사용자 계정 정보 조회
select * from dba_users;

--데이터 파일에 대한 정보 조회
select * from dab_date_files;

--테이블스페이스에 대한 정보 조회
select * from dba_tablespaces;
```

#### 테이블스페이스 생성   
> create tablespace 테이블스페이스명   
> datafile 경로 size 크기   
> autoextend on next 크기 : 자동증가옵션   

```sql
create tablespace tb_test1
datafile 'C:\mydata\tb_test1.dbf' size 48m
autoextend on next 10m;


--한번에 여러개 생성
create tablespace tb_test2
datafile 
'C:\mydata\tb_test2_01.dbf' size 48m
autoextend on maxsize 1000m,
'C:\mydata\tb_test2_02.dbf' size 48m
autoextend on maxsize 1000m,
'C:\mydata\tb_test2_03.dbf' size 48m
autoextend on maxsize 1000m;
```

#### 테이블스페이스 제거   
> drop tablespace 테이블스페이스명;   
> drop tablespace 테이블스페이스명   
> including contents and datafiles; : 물리적인 데이터파일까지 삭제   

```sql
drop tablespace tb_test2
    including contents and datafiles;
```


***

## 4. 사용자 계정 관리
#### 사용자 계정 만들기
> alter session set "_ORACLE_SCRIPT"=true;   
> create user 사용자이름   
> identified by 비밀번호   
> default tablespace 테이블스페이스명;

```sql
alter session set "_ORACLE_SCRIPT"=true;

create user testuser1
identified by testuser123
default tablespace tb_test1;

```

#### 사용자 삭제
> drop user 사용자이름   

```sql
drop user testuser;

--해당 사용자가 이미 오브젝트를 만들었을 경우 삭제 불가
drop user testuser cascade;
--오브젝트까지 삭제
```

#### 권한관리
```sql
-- 권한 조회
select * from dba_sys_privs
where grantee='TESTUSER2';

-- 롤 조회
select * from dba_role_privs
where grantee='TESTUSER2';
```

- 권한 추가   
> grant 권한 to 사용자명;   

```sql
--접속권한
grant create session to testuser2;

--테이블생성 권한
grant create table to testuser2;
```

- 권한 박탈   
> revoke 권한 from 사용자명;   

```sql
revoke create table from testuser2;
```

#### 롤 관리
- 롤 부여   
```sql
grant resource, connect to testuser;
```

#### 계정 락
```sql
--잠긴 계정 열기
--alter user 사용자ID account unlock;


--계정 잠그기
--alter user 사용자ID account lock;
```

#### 계정 암호 변경
```sql
--기존 계정의 암호 변경하기
--alter user 사용자ID identified by 새로운암호;
```

## 5. 롤
- 권한에 대한 부여와 관리를 보다 편리하게 하기 위해 사용
- 권한의 그룹   

```sql
--롤 생성하기
create role testrole;


--롤에 권한 부여
grant create session to testrole;
grant create table to testrole;

--사용자에게 롤 부여
grant testrole to testuser;
```

## 6. 오브젝트에 관한 권한
- 해당 오브젝트를 소유한 계정에서 권한을 부여하거나 박탈할 수 있다   

```sql
--hr계정에서
select * from employees; --조회 가능

--scott계정에서
select * from hr.employees; --권한이 없어 조회 불가

--hr계정에서
grant select on hr.employees to scott; --scott에게 조회 권한 부여
revoke select on hr.employees from scott; -- 조회권한 박탈
grant update on hr.employees to scott; -- 업데이트 권한 부여
revoke update on hr.employees from scott; --업테이트 권한 박탈

```

## 7. 위임
- with admin option
- with grant option
  - 해당 오브젝트의 권한을 위임
  - 또 다른 사용자에게 권한을 할당 가능   

```sql
--hr 계정에서
grant select on hr.employees to testuser; --조회권한만 부여

--testuesr 계정에서
select * from hr.employees; -- 조회 가능
grant select on hr.employees to scott; -- 스콧에게 권한부여 불가


--hr계정에서
grant select on hr.employees to testuser with grant option; --권한위임

--testuesr 계정에서
select * from hr.employees; -- 조회 가능
grant select on hr.employees to scott; -- 스콧에게 권한부여 가능

--scott 계정에서
select * from hr.employees; --조회가능

```
