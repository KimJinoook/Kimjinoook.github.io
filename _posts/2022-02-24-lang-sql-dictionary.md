---
layout: post
title:  "13. 데이터 딕셔너리"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

## 1. data dictionary
- **데이터베이스** 내에 저장된 모든 **객체의 정보를 제공**해주는 테이블
- 내용
  - 모든 스키마 오브젝트
  - 스키마 오브젝트에 의해 현재 사용된 공간
  - 컬럼들의 기본값
  - 제약조건 정보
  - 오라클 사용자 이름
  - 각 사용자에게 부여된 권한과 롤
  - auditing 정보
- 종류
  - DBA_xx : 데이터베이스 관리를 위한 정보 제공
  - ALL_xx : 사용자가 접근가능한 모든 스키마의 정보 제공
  - USER_xx : 자신이 생성한 오브젝트 정보 제공
  - V$_xx : DB의 성능 분석/통계정보 제공하며 X$ 테이블에 대한 view
  - X$_xx : DB의 성능 분석/통계정보를 제공하는 테이블   

```sql
select * from dictionary; --모든 정보 출력
select * from dict_columns; -- 위 테이블의 각 컬럼에 대한 설명 출력

--user_xxx
select * from user_objects; --사용자가 생성한 모든 오브젝트 출력
select * from user_tables; --사용자가 생성한 모든 테이블 출력
select * from user_constraints; --제약조건
select * from user_indexes; --인덱스
select * from user_sequences; --시퀀스
select * from user_views; --뷰
select * from user_source; --함수나 프로시저

--all_xxx
select *
from all_tables
where table_name='EMPLOYEES';

--DBA_XXX
--dba권한을 가진 사용자만 접근 가능
select * from dba_users; --모든 계정목록
select * from dba_data_files; 
select * from dba_tablespaces;
select * from dba_roles;
select * from dba_sys_privs;
select * from dba_role_privs;

```
