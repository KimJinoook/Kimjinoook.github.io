---
layout: post
title:  "6. 서브쿼리"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

## 1. Sub query란
- 쿼리 안에 또 다른 쿼리가 담겨있는 것
- sql문을 작성할 때 질문이 여러가지가 한번에 나오는 경우
  - a보다 급여를 많이 받는 사람 조회
    - a의 급여 조회
    - 더 많이 받는 사람 한번 더 조회
    - 2번의 sql 작성
    - 이런 문제점 보안 : sub query
- 문법
  - where 조건연산자 (select ~ from where)
  - 괄호 안에 서브쿼리 작성   
- 주의사항
  - where절의 연산자 오른쪽에 위치
  - 괄호로 묶어야 함
  - 특별한 경우를 제외하고는 서브쿼리 절에 order by절이 올 수 없다

- 위치별 이름
  - select(sub query)
    - 1행만 반환할 경우 Scalar sub query
    - select 절에 오는 서브쿼리로 한번에 결과를 1행씩 반환
  - from(sub query)
    - inline view
  - where(sub query)
    - sub query
- 종류
  - 단일 행 서브쿼리 : 서브쿼리의 결과가 1개의 행
  - 다중 행 서브쿼리 : 서브쿼리의 결과가 2건 이상
  - 다중 컬럼 서브쿼리 : 서브쿼리의 결과가 여러 컬럼인 경우
  - 상호연관 서브쿼리 : 
    - 메인 쿼리의 값을 서브쿼리에 주고 서브쿼리 수행
    - 서브쿼리 결과를 다시 메인쿼리로 반환해서 수행

```sql
--기존
select sal from emp
where ename = 'SCOTT'; --급여 3000

select * from emp
where sal > 3000; -- 3000보다 높은 급여 조회

--서브쿼리 이용
select * from emp
where sal > (select sal from emp where ename = 'SCOTT');

     EMPNO ENAME      JOB              MGR HIREDATE                 SAL       COMM     DEPTNO
---------- ---------- --------- ---------- ----------------- ---------- ---------- ----------
      7839 KING       PRESIDENT            81/11/17 00:00:00       5000                    10
```

***

## 2. 단일 행 서브쿼리
- 서브쿼리의 결과가 1개의 행만 나오는 것
- 연산자
  - =,!=,>,>=,<,<=   

```sql
--기존
--이윤나의 학과번호
select deptno1 from student where name = '이윤나'; --101

--학과번호 같은 학생 조회
select * from student where deptno = 101;


--서브쿼리 이용
select * from student s join department d
on s.deptno1 = d.deptno
where deptno1 = (select deptno1 from student where name = '이윤나');

    STUDNO NAME       DEPTNO DNAME     PART BUILD                                   
---------- ---------- ----------------------------
      9411 서진수     101 컴퓨터공학과  100 정보관                                  
      9511 김신영     101 컴퓨터공학과  100 정보관                                  
      9611 일지매     101 컴퓨터공학과  100 정보관                                  
      9711 이윤나     101 컴퓨터공학과  100 정보관  
```

***

## 3. 다중 행 서브쿼리
- 서브쿼리의 결과가 2건 이상 출력되는 것
- 결과가 여러건이기에 단일 행 연산자 사용 불가
- 연산자
  - in
  - \>any : 최소값 반환
    - sal > any (100,200,300)
    - sal > 100
  - <any : 최대값 반환
    - sal < any (100,200,300)
    - sal < 300
  - <all : 최소값 반환
    - sal < all (100,200,300)
    - sal < 100
  - \>all : 최대값 반환
    - sal > all (100,200,300)
    - sal > 300
  - exist, : 서브쿼리의 값이 있을 경우 반환   

```sql
--기존
--서울지사인 부서 조회
select dcode from dept2 where area = '서울지사'; --1000,1001,1002,1010

--서울근무 사원 조회
select * from emp2 where in (1000,1001,1002,1010);

--서브쿼리 이용
select empno, name, deptno from emp2
where deptno in (select dcode from dept2 where area = '서울지사');

     EMPNO NAME       DEPTNO
---------- ---------- ------
  19960101 전부장     1000   
  19930331 백원만     1001  
  19950303 천만득     1002  
  20000203 최오대     1010  
```

***

## 4. 다중 컬럼 서브쿼리
- 서브쿼리의 결과가 여러 컬럼인 경우
- 주로 기본키(primary key)를 여러 컬럼을 합쳐서 만들었을 경우 한꺼번에 비교하기 위해 사용   

```sql
--학년별로 최대키를 가진 학생을 구하고자 할 때

--학년별 최대키
select grade, max(height) from student group by grade; -- 1:179,  2:184,  3:177,  4:182
--조회
select * from student where grade = 1 and height = 179; --1학년 최대키인 학생
-- 이를 각 학년마다 따로 조회, 4번의 조회가 필요하다


--다중컬럼 서브쿼리 이용
select * from student
where (grade, height) in (select grade, max(height) from student group by grade);

NAME            GRADE     HEIGHT
---------- ---------- ----------
박동호              4        182
오나라              3        177
노정호              2        184
김주현              1        179

```


***

## 5. 상호연관 서브쿼리
- 서브쿼리가 메인쿼리에 독립적이지 않다
  - 연관관계, 조인을 통해 연결되어 있다
  - 서브쿼리와 메인쿼리 사이에 조인이 사용
- 서브쿼리에서 테이블 별칭이 사용
- 메인쿼리의 컬럼이 서브쿼리의 where 절에 사용된다   

```sql
--자신의 직급 평균 연봉보다 많이 받는 사원
select * from emp2 a
where pay >= (select avg(nvl(pay,0)) from emp2 b where a.position = b.position)
order by position;
```

- exists 연산자
  - 특정 컬럼값이 존재하는지 여부 체크
  - 성능면에서 in보다 우수
  - 서브쿼리가 반환하는 결과에 메인쿼리에서 추출될 데이터들이 존재하기만 하면 조건 만족   
  - in
    - 어떤 값에 포함되는지 여부 체크
    - 괄호 안에 값 또는 서브쿼리가 올 수 있다
  - exists
    - 특정 컬럼값이 존재하는지 여부 체크
    - 괄호 안에 오직 서브쿼리만 올 수 있다   



```sql
-- in을 사용할 경우
select * from emp2 where deptno in (select decode from dept2 where pdept is not null);

-- exists를 사용할 경우
select * from emp2 e where exists (select 1 from dept2 d where d.pdept is not null and e.deptno = d.dcode);


--in
select * from emp2
where deptno in (select dcode from dept2 where area = '경기지사');

--exists

select * from emp2 e
where exists (select 1 from dept2 d where e.deptno = d.dcode and d.area = '경기지사');

```

***

## 6. Scalar sub query
- select 절에 오는 서브쿼리, 한번에 결과를 1행씩 반환한다
- 서브쿼리의 결과값을 컬럼이나 함수처럼 사용
- 출력된 값은 select list의 한 항목 값으로 대체
- 공집합이더라도 select list 상의 값은 null로 표시
- 대량의 데이터 처리시 조인으로 대체   
- select 문에서 사용하려면 단일 서브쿼리 중 단일행이면서 단일컬럼인 경우만 사용 가능   

```sql
--join 이용
select e.name, d.dname
from emp2 e left join dept2 d
on e.deptno = d.dcode;

--scalar subquery이용
select e.name, (select dname from dept2 d where e.deptno = d.dcode)
from emp2 e;

NAME       (SELECTDNAMEFROMDEPT
---------- --------------------
김설악     영업2팀             
최오대     영업3팀                                 
장금강     H/W지원             
나한라     S/W지원
```
- 스칼라서브쿼리는 결과가 없을 경우 null을 반환
  - outer join과 동일하다
- 출력하고자 하는 데이터의 양이 적은 경우, join보다 빠른 속도
- 동작 순서
  - 메인쿼리 수행 후 스칼라서브쿼리에 필요한 값 제공
  - 서브쿼리를 수행하기 위해 필요한 데이터가 들어있는 블록을 메모리로 로딩
  - 메인쿼리에서 주어진 조건을 가지고 필요한 값 찾음
  - 결과를 메모리에 입력값과 출력값으로 저장 (메모리 내 캐시)
  - 다음 조건이 주어지면 메모리 내 값이 존재하는지 찾고, 출력
  - 없으면 다음 블록을 엑세스 후 해당 값 찾음
- 빠른 이유
  - 메모리에 만들어져있는 값을 찾아오기 때문
  - 데이터양이 많은 경우 캐시에서 해당 데이터를 찾는 시간이 많아진다
    - 이럴 경우 조인이 유리
- 데이터 종류가 적고, 개수도 비교적 적은 코드성 테이블에서 데이터를 가져올 경우   

***

## 7. Inline view (Top-n 분석)
- from 절에 오는 서브쿼리
- 의사컬럼 (pseudocolumn)
  - 모조, 유형 컬럼
  - 테이블에 있는 일반적인 컬럼처럼 행동
    - 실제로 테이블에 저장되어있지는 않은 컬럼
  - ROWNUM
    - 쿼리의 결과로 나오는 각각의 row들에 대한 **순서 값**을 가리킨다
    - 상위 5건의 정보만 조회하기 등
  - ROWID
    - 테이블에 저장된 각각의 row들이 저장된 **주소값**을 가진다
    - 모든 테이블의 모든 row는 각각의 유일한 rowid 값을 가진다   

```sql
select rownum, empno, ename, sal, rowid as "ROW_ID"
from emp;

    ROWNUM      EMPNO ENAME             SAL ROW_ID            
---------- ---------- ---------- ---------- ------------------
         1       7369 SMITH             800 AAAR+EAAHAAAAFsAAA
         2       7499 ALLEN            1600 AAAR+EAAHAAAAFsAAB
         3       7521 WARD             1250 AAAR+EAAHAAAAFsAAC
         4       7566 JONES            2975 AAAR+EAAHAAAAFsAAD

```

- 상위 n건의 데이터 조회시   

```sql
-- 이름순으로 앞순부터 5명 조회

-- 잘못된방식
select rownum, empno, ename, sal
from emp
where rownum <=5
order by ename;

    ROWNUM      EMPNO ENAME             SAL
---------- ---------- ---------- ----------
         2       7499 ALLEN            1600
         4       7566 JONES            2975
         5       7654 MARTIN           1250
         1       7369 SMITH             800
         3       7521 WARD             1250

-- 정렬의 실행순서가 제일 마지막이기 때문에
-- 테이블에 저장된 순서대로 5개의 데이터를 가져온 후
-- 그 안에서 정렬
-- 이름 또한 이름순 5명이 아닌, 저장순 5명


-- inline view 이용
select rownum, empno, ename, sal
from (select empno, ename, sal from emp order by ename)
where rownum <= 5;

    ROWNUM      EMPNO ENAME             SAL
---------- ---------- ---------- ----------
         1       7876 ADAMS            1100
         2       7499 ALLEN            1600
         3       7698 BLAKE            2850
         4       7782 CLARK            2450
         5       7902 FORD             3000
-- 이름 또한 이름순으로 가져온 것을 볼 수 있다
```

- n1과 n2 사이의 데이터 조회시   

```sql
select rownum, a.*
from (select * from employees order by salary desc) a
where rownum between 2 and 4;
-- 데이터가 나오지 않는다

select rownum, a.*
from (select * from employees order by salary desc) a
where rownum between 1 and 4;
-- 가능, rownum은 1이 포함되지 않으면 데이터를 읽어오지 못한다


--2와 4 데이터를 읽기 위해 inline view 한번 더 이용
--rownum에 별칭을 주어 사용
select b.*
from (select rownum as 별칭, a.*
      from (select * from employees order by salary desc) a) b
where 별칭 between 2 and 4;

        별칭 EMPLOYEE_ID FIRST_NAME           LAST_NAME                 EMAIL   
---------- ----------- -------------------- ------------------------- ---------
         2         101 Neena                Kochhar                   NKOCHHAR  
         3         102 Lex                  De Haan                   LDEHAAN 
         4         145 John                 Russell                   JRUSSEL 
```
