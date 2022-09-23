---
layout: post
title:  "3. 단일행 함수"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

- 데이터가 여러 건 존재하지만 함수에 들어가는 데이터는 한번에 한개
- 한번에 하나씩 처리하는 함수
  - select ename, initcap(ename), job, length(job), sal from emp;
- 복수행 함수 : 여러건의 데이터를 동시에 입력받아 1건의 결과
  - select sum(sal) from emp;


## 1. 문자함수
- initcap() : 영문의 첫글자만 대문자로 나머지는 소문자로
  - select initcap('preTTy girl') from dual;
  - Pretty Girl
- upper() : 전부 대문자로
- lower() : 전부 소문자로
- length(),lengthb() : 문자열의 길이,바이트수를 리턴
- concat('','') : 두 문자열을 연결
- substr('문자열',시작위치, 글자수) : 특정 길이의 문자열 추출
  - substr('abcdefghi',2,3) : bcd
  - substr('abcdefghi',6) : fghi
  - substr('abcdefghi`,-5,2) : ef
  - substrb : 바이트수의 문자열 추출
- instr('문자열','찾는글자',시작위치,몇번째인지)
  - instr('a*b*c*','*') : 2
  - instr('a*b*c*','*',3) : 4
  - instr('a*b*c*','*',3,2) : 6
- lpad('문자열', 자리수,'채울문자') : 왼쪽 빈자리 채우기
- rpad : 오른쪽 빈자리 채우기
- ltrim('문자열','제거할 문자') : 왼쪽에서 해당 문자 제거
  - 문자 미지정시 공백 제거
- rtrim : 오른쪽에서 해당 문자 제거
- reverse() : 문자열을 거꾸로
- replace('문자열, '문자1', '문자2')
  - 문자열에서 문자1이 있으면 문자2로 교체

## 2. 숫자함수
- round(숫자, 자리수) : 반올림
  - round(12345.126) : 12345
  - round(12345.126, 1) : 12345.1
  - round(12345.126, 2) : 12345,13
  - round(12345.126, -1) : 12350
  - round(12345.126, -2) : 12300
- trunc(숫자, 자리수) : 버림
- mod(숫자, 나누는 수) : 나머지
- ceil() : 정수로 올림
- floor() : 정수로 내림
- power(숫자1, 숫자2) : 숫자1의 숫자2승

## 3. 날짜함수
- alter session set NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS';
  - 포맷형식으로 시스템 날짜 표기방법 변경
- sysdate : 현재 날짜 시간 리턴
- sysdate + 숫자
  - 숫자(일) 증가
  - 시간, 분 초 증가 시 일로 변환 후 증가
  - 1/24 + 1/(24*60) + 1/(24*60*60)
- add_months(날짜, 숫자)
  - 숫자(월) 만큼 증가
- to_yminterval()
  - 문자형태를 연,월의 기간 차이로 리턴
  - sysdate + to_tminterval('02-04')
  - 현재 날짜에 2년 4개월 증가
- to_dsinterval()
  - 문자형태를 일, 시분초의 기간 차이로 리턴
  - sysdate + to_dsinterval('1 03:10:20')
  - 1일 3시간 10분 20초 증가
- months_between : 날짜 사이의 개월 수
- next_day(날짜, 숫자) : 돌아오는 숫자(1:일~7:토)의 날짜 리턴
  - select next_day(sysdate, 1) from dual;
  - select next_day(sysdate, '월') from dual;
  - select next_day(sysdate, '화요일') from dual;
- last_day : 해당 달의 마지막 날짜 리턴
- round : 정오를 기준으로 반올림
- trunc : 시간을 버리고 해당 날짜 리턴

## 4. 형변환함수
- to_number
  - 문자를 숫자로 형변환
- to_date
  - 문자를 날짜로 형변환
- to_char(날짜, 패턴)
  - 숫자와 날짜를 문자로 형변환, 패턴 형식으로 표현


```sql
select sysdate, to_char(sysdate, 'yyyy'), --2022
to_char(sysdate, 'mm'), --03
to_char(sysdate, 'dd'), --17
to_char(sysdate, 'd') 요일, --(1:일, 2:월~) 5
to_char(sysdate, 'year'), --twenty twenty-two
to_char(sysdate, 'mon'), --3월 
to_char(sysdate, 'month'), --3월 
to_char(sysdate, 'ddd'), --1년중 며칠인지 076
to_char(sysdate, 'day'), --요일을 한글로 목요일
to_char(sysdate, 'dy'), --요일 목
to_char(sysdate, 'q')--분기 1
from dual;


select sysdate, to_char(sysdate,'yyyy-mm-dd'), --2022-03-17
to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), --2022-03-17 17:28:56
to_char(sysdate,'yyyy-mm-dd hh:mi:ss am d'), --2022-03-17 05:28:56 오후 5
to_char(sysdate,'yyyy-mm-dd hh:mi:ss pm day') --2022-03-17 05:28:56 오후 목요일
from dual;


```
  - 9 : 남은 자리를 공백으로
  - 0 : 남은 자리를 0으로   

```sql
select 1234, to_char(1234,'999999'), --  1234
to_char(1234,'099999'), -- 001234
to_char(1234, '$99999'), --  $1234
to_char(1234,'L99999'), --         ￦1234
to_char(1234.56, '9999.9'), -- 1234.6
to_char(1234, '99,999'), --  1,234
to_char(123456789,'999,999,999'), -- 123,456,789
to_char(1234.56,'9999') -- 1235
from dual;
```

- extract() - 날짜에서 년,월,일 추출   

```sql
select extract(year from sysdate) 년도, --2022
extract(month from sysdate) 월, --3
extract(day from sysdate) 일 --17
from dual;

select extract(year from to_date('2023-05-19')) 년도, --2023
extract(month from to_date('2023-05-19')) 월, --5
extract(day from to_date('2023-05-19')) 일 --19
from dual;
```

## 5. 일반함수
- 함수의 입력값으로 숫자, 문자, 날짜 구분없이 모두 사용가능한 함수

### NVL
- NVL(컬럼, 치환할 값)
  - null값을 만나면 다른 값으로 치환   

```sql
select name, bonus, nvl(bonus,0) from professor;

NAME            BONUS NVL(BONUS,0)
---------- ---------- ------------
조인형            100          100
박승곤             60           60
송도권                           0
```
- NVL2(col1, col2, col3)
  - col1의 값이 null이 아니면 col2로 출력
  - col1의 값이 null이면 col3으로 출력   

```sql
select name, pay, bonus, nvl2(bonus,pay*12+bonus,pay*12) from professor;

NAME              PAY      BONUS NVL2(BONUS,PAY*12+BONUS,PAY*12)
---------- ---------- ---------- -------------------------------
조인형            550        100                            6700
박승곤            380         60                            4620
송도권            270                                       3240
```

### DECODE
- if문을 표현하는 함수   

- A=B일때 1 출력   


```sql
if a = b then
  return '1';
end if;


decode(a,b,'1',null) -- null은 생략 가능
```
- A=B일때 1 출력, 아니면 2출력   

```sql
if a=b then
  return '1';
else
  return '2';
end if;


decode(a,b,'1','2')
```

- a=b일때 1, a=c=일때 2, 아니면 3   

```sql
if a=b then
  return '1';
elsif a=c then
  return '2';
else
  return '3';
end if;


decode(a, b,'1',
          c,'2',
            '3')
```

- a=b이면서 a=c이면 1, 아니면 2, 둘다아니면 3   

```sql
if a=b then
  if a=c then
    return '1';
  else
    return '2';
  end if
else
  return '3';
end if


decode(a,b, decode(a,c, '1',
                        '2'),
            '3')
```

### CASE
- if문을 대신하는 함수, 조건이 범위값일 때도 사용 가능   


```sql
--동일 값 비교시
case when 조건1 then 출력1
    when 조건2 then 출력2
     else 출력3
     end "별칭"
     
--범위 값 비교시
case when 조건1 then 출력1
    when 조건2 then 출력2
    else 출력3
    end "별칭"
```

- 동일 값 비교 예   

```sql
select name, grade,
case grade when 1 then '1학년'
           when 2 then '2학년'
           when 3 then '3학년'
           else '4학년'
           end 학년
from student;

NAME            GRADE 학년 
---------- ---------- ---
서진수              2 2학년
서재수              3 3학년
이미경              4 4학년
```

- 범위 값 비교 예   
```sql
select name, pay,
case when pay>400 then '400초과'
     when pay between 300 and 400 then '사이'
     when pay<300 then '300미만'
     end 범위
from professor;

NAME              PAY 범위   
---------- ---------- -----
조인형            550 400초과
박승곤            380 사이 
송도권            270 300미만
```
