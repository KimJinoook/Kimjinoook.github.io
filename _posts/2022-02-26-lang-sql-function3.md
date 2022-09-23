---
layout: post
title:  "15. 분석함수"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

- 데이터를 분석하는 함수   
> 분석함수(파라미터1, 파라미터2~)   
> over(<partition절\> <orderby절\>)   

## 1. 순위함수
- rank, dense_rank, row_number
- partition 절에 있는 각 행의 순위를 리턴
- rank
  - 동일한 결과값일 때는 순위가 같다
  - 다음의 결과값은 동일한 결과값의 개수를 더하여 리턴
  - 1등, 공동2등, 4등
- dense_rank
  - 동일한 결과값의 건수가 여러개여도 다음값은 1만 더한다
  - 1등, 공동2등, 3등
- row_number
  - partition 내 분할되어 정렬된 row별로 순위를 1부터 순차 적용
  - 데이터별 유일 순위를 뽑을때 사용
  - 1등, 2등=3등,4등   

```sql
select department_id, employee_id, first_name, hire_date, salary,
    rank() over(order by salary desc) "전체순위(rank)",
    rank() over(partition by department_id order by salary desc) "부서 내 순위(rank)",
    dense_rank() over(order by salary desc) "전체순위(dense_rank)",
    dense_rank() over(partition by department_id order by salary desc) "부서 내 순위(dense_rank)",
    row_number() over(order by salary desc) "전체순위(row_number)",
    row_number() over(partition by department_id order by salary desc) "부서 내 순위(row_number)"
from employees;
```

- 분석함수는 where 절에 올 수 없다   

```sql
--1~5만 조회
select employee_id, first_name, salary, department_id,
    rank() over(order by salary desc) 순위
from employees
where rank() over(order by salary desc) <= 5; --error 분석함수는 where 절에 올 수 없다 


select *
from(
select employee_id, first_name, salary, department_id,
    rank() over(order by salary desc) rank
from employees
)
where rank <= 5; -- rownum 처럼 inline view 이용
```

- rownum과 row_num 비교   


```sql
--기존 rownum 이용시
--먼저 정렬 후 inline view 이용 rownum 적용
select rownum, A.*
from
(
    select empno, ename, hiredate
    from emp
    order by hiredate desc
)A;


--row_number 이용시 바로 가능
select rownum, row_number() over(order by hiredate desc) no,
    empno, ename, hiredate
from emp;
```




## 2. top-n 분석
- top-n 쿼리
  - rownum을 이용해 출력 건수 제한   

```sql
--상위 7명 조회

--rownum 이용
select * from(
    select rownum no, A.*
    from(
        select empno, ename, hiredate
        from emp
        order by hiredate desc
    )A
)
where no <= 7;


--row_number 이용
select *
from(
    select row_number() over(order by hiredate desc) no,
        empno, ename, hiredate
    from emp
)
where no <= 7;
```


## 3. 누적합계
- sum() over   

```sql
select p_code, p_date, p_qty,
        sum(p_qty) over(partition by p_code order by p_date) 누적
from panmae

    P_CODE P_DATE        P_QTY         누적
---------- -------- ---------- ----------
       100 20110101          3          3
       100 20110103          3         18
       100 20110103          2         18
       100 20110103         10         18
       100 20110104          5         29
       100 20110104          4         29
       100 20110104          2         29
       101 20110101          5          5
       101 20110103          4          9
       
       
--group by 미리 실행
select p_code, p_date, 판매량, sum(판매량) over(partition by p_code order by p_date) 누적
from(
    select p_code, p_date, sum(p_qty) 판매량 from panmae
    group by p_code, p_date
);

    P_CODE P_DATE          판매량         누적
---------- -------- ---------- ----------
       100 20110101          3          3
       100 20110103         15         18
       100 20110104         11         29
       101 20110101          5          5
       101 20110103          4          9
       101 20110104         10         19
```
