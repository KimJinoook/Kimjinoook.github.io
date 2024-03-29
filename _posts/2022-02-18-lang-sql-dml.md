---
layout: post
title:  "7. 데이터조작어 / DML"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

## 1. Insert
- 테이블에 새로운 데이터를 입력할 때 사용하는 명령어
- 데이터를 입력할 때 숫자 값 이외에는 데이터를 따옴표로 감싸야한다

### a. 단일 행 입력하기
> insert into table [(column1, 2,..)] values (value1, 2 ..)   

```sql
-- 원하는 컬럼에 데이터 넣기
insert into dept2(dcode, pdept, dname)
values('9000','1006','특반1팀'); --지정한 컬럼 순으로 입력

DCODE  DNAME                PDEPT  AREA                          
------ -------------------- ------ --------               
9000   특반1팀              1006   (null)


--모든 컬럼에 데이터 넣기
insert into dept2
values (9001,'특판2팀','1006','임시지역');

```

- 데이터 설정 nullable이 허용되지 않은 컬럼은 null 입력 불가   

```sql
insert into dept2(dcode,pdept)
values(9004,1006);

오류 보고 -
ORA-01400: NULL을 ("HR"."DEPT2"."DNAME") 안에 삽입할 수 없습니다

```

- 날짜 형식
  - 리눅스용 오라클 : dd-mon-yy
    - 날짜 부분에 to_date함수를 입력하여 yyyy-mm-dd로 변경
  - 윈도우용 오라클 : yyyy-mm-dd   
  - 현재 날짜 입력 시 sysdate 사용   

```sql
insert into professor(profno, name, id, position,pay,hiredate)
values(5001,'김설희','kimsh','정교수',510,'2013-02-19');
```

- null값 입력
  - 컬럼에 값을 넣지 않거나 null 입력   

### b. 다중 행 입력하기
> insert into 테이블명 select 문   

- select문의 컬럼의 개수와 데이터타입이 일치해야 입력이 가능하다   

```sql
insert into pd(no,pdname, price, regdate)
select p_code,p_name,p_price,sysdate
from product
where p_code in(102,103,104);

104	맛짱구	800	22/03/24 10:13:00
103	에이서	900	22/03/24 10:13:00
102	맛큰산	1000	22/03/24 10:13:00
5	외장하드	270000	22/02/24 10:54:15
4	모니터	340000	22/03/15 10:54:15
3	키보드	47000	22/02/03 10:54:15
2	컴퓨터	2300000	22/03/13 10:54:15
1	마우스	15000	22/03/16 10:54:15

-- 기존 pd테이블 1~5
-- product 테이블의 102~104번 데이터를 pd테이블에 입력
```

### c. 테이블 생성하며 데이터 입력
> create table 신규테이블명 as select 문   

- 신규테이블을 만들고 동시에 다른 테이블에서 select 된 컬럼과 결과데이터를 insert한다
- 테이블과 컬럼의 제약조건과 기본키는 복제되지 않는다
  - 신규테이블에 대해 제약조건을 새로 정의해야 한다
- 함수를 이용해 컬럼을 만들 시 컬럼명을 지정해주어야 한다   

```sql
--컬럼명 미리 지정
create table imsi_tbl2(emp_id, name, pay, deptno, dname)
as
select e.employee_id, e.first_name || ' ' || e.last_name, 
nvl2(e.commission_pct, e.salary+e.salary*e.commission_pct, e.salary), 
d.department_id, d.department_name
from employees e left join departments d
on e.department_id=d.department_id;


--컬럼명 별칭으로 지정
create table imsi_tbl
as
select e.employee_id, e.first_name || ' ' || e.last_name as "name", 
nvl2(e.commission_pct, e.salary+e.salary*e.commission_pct, e.salary) as "pay", 
d.department_id, d.department_name
from employees e left join departments d
on e.department_id=d.department_id;

```

### d. insert all
- 하나의 insert문으로 두개 이상의 테이블에 값을 넣는다
- 뒤에 select문이 와줘야 한다
  - 다른 테이블에서 가져오지 않는 경우에도 dual 테이블을 넣어줘야 한다   

```sql
insert all into p_01 values(1,'AA')
            into p_02 values(2,'BB')
select * from dual;
```

- 조건에 따라 다른 테이블에 값이 들어가도록 할 수 있다   

```sql
insert all 
    when profno between 1000 and 1999 then into p_01 values(profno, name)
    when profno between 2000 and 2999 then into p_02 values(profno, name)
select profno, name from professor;
```

- 다른 테이블에 동시에 같은 데이터 넣기   

```sql
insert all into p_01 values(profno, name)
            into p_02 values(profno, name)
select profno, name from professor
where profno between 3000 and 3999;
```

***

## 2. Update
- 기존 데이터를 다른 데이터로 변경할 때 사용   
> update 테이블 set 컬럼명1=값1, 컬럼명2=값2.. where 조건   

```sql
select * from professor where position = '조교수';

update professor
set bonus = 100
where position = '조교수';

    PROFNO NAME            BONUS
---------- ---------- ----------
      1002 박승곤            100
      2002 김영조            100
      3002 나한열            100
```

### 다중건의 update
- 서브쿼리를 이용, 여러 컬럼을 서브쿼리의 결과로 update   

```sql
select job, deptno from emp
where empno = 7782; --매니저, 10

select * from emp
where empno = 7844; --세일즈맨, 30

update emp
set (job,deptno) = (select job,deptno from emp where empno = 7782)
where empno = 7844; --매니저,10

```

### exists 이용
- 서브쿼리의 컬럼값이 존재하는지 체크
- 존재하면 true, 없으면 false
- true 리턴 시 update 진행
- false 리턴 시 update 불가   

```sql
update panmae a
set p_code = (select p_code_new from product b where b.p_code = a.p_code and del_yn='Y')
where exists (select 1 from product b where b.p_code = a.p_code and del_yn='Y');
```

***

## 3. Delete
> delete from 테이블 where 조건;   

- 조건이 없으면 테이블 전체 삭제
- 해당 데이터가 사용하고 있던 저장공간은 반납하지 않는다   

```sql
--단일행
delete from new_employees
where employee_id=(select manager_id from departments where department_id=10);


--다중행
delete from employees
where department_id in (select department_id from departments where location_id = 1700);


--다중컬럼
delete from employees
where (job_id, salary)in (select job_id, max(salary) from employees group by job_id);

--상관관계 서브쿼리
delete from employees a
where a.salary > (select avg(nvl(salary,0)) from employees b where a.job_id = b.job_id);

```
