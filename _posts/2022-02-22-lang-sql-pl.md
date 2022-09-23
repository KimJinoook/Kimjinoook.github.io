---
layout: post
title:  "11. PL/SQL"
subtitle:   ""
categories: lang
tags: sql
comments: false
header-img: 
---

## 1. PL/SQL이란
- Procedual Language extension to Structured Query Language
- SQL과 일반 프로그래밍 언어의 특성을 결합
- 변수 상수 선언 가능
- 조건문 반복문 사용 가능
- DBMS의 역할이 커지면서 SQL을 넘어서는 일반 프로그래밍 언어의 기능이 필요하게 되었다
  - 1989년 오라클 6버전부터 등장

### a. 기본구조
- 선언부 (Declarative part)
  - 실행부에서 사용할 변수나 상수 선언
  - 변수와 상수 선언은 반드시 선언부에서만 선언
  - Declare 사용
- 실행부 (Executable part)
  - 셀지 처리할 로직 부분 담당
  - 변수에 값 할당, if, while, sql 등
  - 실행부에 여러개의 sql문장들을 순차적으로 사용
  - 블록 단위로 한번에 처리
  - Begin으로 시작, End로 끝
- 예외처리부 (Exception-building part)
  - 로직 처리의 오류처리부분
  - Exception 사용   


- 기본구조   

```sql
declare
  counter INTEGER; --선언부
begin
  counter := 10;
  counter := counter /0;
  dbms_output.put_line(counter); --실행부
exception when others then
  dbms_output.put_line('ERRORS'); --예외처리부
end;
```

- if문 예   
```sql
declare --선언부 - 변수 선언
    counter number;
    
begin --실행부 - 로직처리
    counter :=1; --변수에 값 할당
    
    --로직처리
    counter := counter/0;
    
    if counter is not null then
        dbms_output.put_line('counter => ' || counter);
    end if;
    
    exception when others then --예외처리부
        dbms_output.put_line('0으로 나누면 안됩니다');
end;

--dbms 출력 : 0으로 나누면 안됩니다
```

- for문 예   
```sql
declare
    i number;
    rst number;
begin
    for i in 1..10 loop
        rst := i*2;
        dbms_output.put_line(rst);
    end loop;
    
    exception when others then
        dbms_output.put_line('error');
end;

2
4
6
8
10
12
14
16
18
20
```

- while문 예   

```sql
declare
    i number;
    result number;
begin
    i := 1;
    while i<=10 loop
        result := i*3;
        dbms_output.put_line(i||'*3 = ' ||result);
        i:=i+1;
    end loop;

    exception when others then
        dbms_output.put_line('error');
        
end;

1*3 = 3
2*3 = 6
3*3 = 9
4*3 = 12
5*3 = 15
6*3 = 18
7*3 = 21
8*3 = 24
9*3 = 27
10*3 = 30
```


### b. 변수와 상수
> 변수명 데이터타입;   
> 상수명 constant 데이터타입;   
> 변수명 테이블명.컬럼명%type;   


### c. 조건문
- if문   

```sql
if 조건 then
  처리문1;
elsif 조건2 then
  처리문2;
else
  처리문3;
end if;

--java와 다르게, else if 가 아니라 elsif, e가 없다


declare
    grade char;
    result clob;
begin
    grade := 'B';
    
    if grade = 'A' then
        result := 'Excellent';
    elsif grade = 'B' then
        result := 'Good';
    elsif grade = 'C' then
        result := 'Fair';
    elsif grade = 'D' then
        result :='Poor';
    else
        result := 'Not found!';
    end if;
        dbms_output.put_line(grade || '=>' || result);
    
    exception when others then
        dbms_output.put_line('error');
end;
```

- case문   

```sql
case 변수
  when 조건 then 명령문
  when 조건2 then 명령문
  else 명령문
end case;


declare
    grade char;
    result varchar2(50);
begin
    grade := 'C';
    
    case grade
        when 'A' then
            result := 'Excellent';
        when 'B' then
            result := 'Good';
        when 'C' then
            result := 'Fair';
        when 'D' then
            result := 'Poor';
        else
            result := 'Not found';
        end case;
    dbms_output.put_line(grade || '=>' || result);
    
    exception when others then
        dbms_output.put_line('error');
end;
```

### d. 반복문
- loop 문   

```sql
loop
  처리문장
end loop
 
declare
    i number;
    result number;
    
begin
    i := 1;
    
    loop
        result := i*2;
        exit when result > 20;
        dbms_output.put_line(result);
        i := i+1;
    end loop;
    exception when others then
        dbms_output.put_line('errer');
end;
```

- while문   

```sql
while 조건 loop
  처리문장
end loop;

declare
    i number;
    result number;
begin
    i := 1;
    result :=0;
    
    while result < 20 loop
        result := i*2;
        dbms_output.put_line(result);
        
        i := i+1;
    end loop;
    
    exception when others then
        dbms_output.put_line('error');
end;
```

- for문   

```sql
for 카운터 in [reverse] 최소값..최대값 loop
  처리문장
end loop;


declare
    i number;
    result number;
begin
    for i in 1..10 loop
        result := i*5;
        dbms_output.put_line('i,result ='||i||','||result);
    end loop;
    
    dbms_output.put_line('---for문 reverse---');
    result := 0;
    
    for i in reverse 1..10 loop
        result := i*5;
        dbms_output.put_line('i,result ='||i||','||result);
    end loop;
    
    exception when others then
        dbms_output.put_line('error');
end;


i,result =1,5
i,result =2,10
i,result =3,15
i,result =4,20
i,result =5,25
i,result =6,30
i,result =7,35
i,result =8,40
i,result =9,45
i,result =10,50
---for문 reverse---
i,result =10,50
i,result =9,45
i,result =8,40
i,result =7,35
i,result =6,30
i,result =5,25
i,result =4,20
i,result =3,15
i,result =2,10
i,result =1,5
```

### e. goto, null 문
- goto 문
  - 특정 위치로 분기하는 문장
- null 문
  - 아무것도 처리하지 않는 문장
- goto문   

```sql
declare
    i number;
    result number;
begin
    goto second;
    for i in 1..10 loop
        result := i*5;
        dbms_output.put_line('i,result ='||i||','||result);
    end loop;
    
    dbms_output.put_line('---for문 reverse---');
    <<second>>
    result := 0;
    
    for i in reverse 1..10 loop
        result := i*5;
        dbms_output.put_line('i,result ='||i||','||result);
    end loop;
    
    exception when others then
        dbms_output.put_line('error');
end;

-- 첫번째 for 문을 생략하고 <<second>>아래의 구문으로 이동하여 두번째 for 문만 실행
```
***

## 2. PL/SQL 서브프로그램
- pl/sql 블록을 데이터 베이스 객체로 저장
  - 필요할 때마다 호출해서 사용
  - 내장프로시저, 함수


### a. 함수
- 특정 기능을 수행한 뒤, **결과값을 반환하는** 서브프로그램   
- 호출 : 함수명(파라미터)   

```sql
create or replace function 함수명
( 파라미터1 데이터타입,
  파라미터2 데이터타입,...
)
return 데이터타입
is [as]
  begin
    처리내용
    return 반환값;
  end;
```

- 함수 사용 예   

```sql
-- 함수 생성
create or replace function get_gender(
    p_ssn varchar2
)
return varchar2
is
    v_gender varchar2(10);
begin
    select case when substr(p_ssn,7,7) in ('1','3') then '남자'
                else '여자' end
        into v_gender
    from dual;
    
    return v_gender;
    
    exception when others then
        dbms_output.put_line('error');
end;

--함수 실행
select get_gender('9412151111111') from dual;


select gno, gname, jumin, get_gender(jumin) 성별, length(gname) 이름길이
from gogak;
```

### b. 프로시저
- 특정 기능을 수행하지만 **값을 반환하지 않는** 서브프로그램   
- 호출 : execute 프로시저명(파라미터);   

```sql
create or replace procedure 프로시저명
( 파라미터1 데이터타입,
  파라미터2 데이터타입,...)
is [as]
  변수선언부
begin
  처리내용
end;

```
- 프로시저 사용 예   

```sql
--프로시저 생성
--pd2테이블에 입력하는 프로시저
create or replace procedure pd2_insert
(
    --파라미터
    --pd2 테이블에 insert 할 때 필요한 파라미터들
    p_pdcode char,
    p_pdname varchar2,
    p_price number,
    p_company varchar2
)
is
    --변수선언
begin
    insert into pd2
    values(pd2_seq.nextval,p_pdcode, p_pdname,p_price,p_company,sysdate);
    commit;
    exception when others then
        dbms_output.put_line('error');
        rollback;
end;

--저장 프로시저 실행
execute pd2_insert('D01','마이크',30000,'보스');

select * from pd2;

--pd2 테이블 컬럼을 수정하는 프로시저 만들기
create or replace procedure pd2_update
(
    --파라미터
    p_no pd2.no%type,
    p_pdcode pd2.pdcode%type,
    p_pdname pd2.pdname%type,
    p_price pd2.price%type,
    p_company pd2.company%type
)
is
    --변수 선언부
    v_cnt number(3);
begin
    select count(*) into v_cnt
    from pd2
    where no=p_no;
    
    --해당 데이터가 존재하면 update
    if v_cnt>0 then
        update pd2
        set pdcode = p_pdcode, pdname= p_pdname, price=p_price,
            company = p_company
        where no = p_no;
    end if;
    commit;
    exception when others then
        dbms_output.put_line('error');
end;
--실행
exec pd2_update(3,'D02','마이크2',4000,'보스2');

select * from pd2;
```




***



## 3. 예외
- exception 이하 dbms_output.put_line('error') 메세지
  - dbms 내 임시 확인을 위한 출력 메세지
  - 자바 연동 시 제대로 된 에러 작동이 일어나지 않음
- raise_application_error(-999,'메세지');
  - 에러번호 999와 함께 에러 메세지 출력
  - 자바에서도 같은 에러 발생시 확인 가능

- 사용자 정의 예외   

```sql
create or replace procedure member_insert
(
    p_name member.name%type,
    p_jumin member.jumin%type,
    p_passwd member.passwd%type,
    p_id member.id%type
)
is
    system_check_insert_fail exception; --사용자 정의 예외
    
begin
    --일요일 23:00:00 ~ 23:59:59 사이 시스템 점검으로 입력 불가
    if to_char(sysdate,'d-hh24') = '1-23' then
        raise system_check_insert_fail; --사용자 정의 예외 발생시키기
    else
        insert into member(no,name,jumin,passwd,id)
        values(member_seq.nextval, p_name, p_jumin, p_passwd, p_id);
        commit;
    end if;
    exception when others then
        raise_application_error(-20998,'시스템점검중');
        rollback;
end;

exec member_insert('홍길동','9907081113333','123','hong');

오류 보고 -
ORA-20998: 시스템점검중
```

***

## 4. 커서
### a. 개요
- 먼저 프로시저를 통해 한번에 여러개의 레코드를 출력할 수 있는지 확인   

```sql
select * from pd2;
create or replace procedure pd2_select
is
begin
    select * from pd2;
    --error
    exception when others then
        dbms_output.put_line('error');
end;
```
- error, select 절에 into절이 들어가야 한다.
  - into를 통해 하나의 값을 받아온다
  - 레코드 하나 출력

- 이를 보완하기 위해 커서 이용

### b. 커서 사용
- 커서를 통해 메모리상에 위치한 쿼리의 결과집합에 접근 가능
- 명시적 커서
  - 커서 선언 : 쿼리 정의
    - cursor 커서명 is select 문장;
  - 커서 열기 : 쿼리 실행
    - open 커서명;
  - 패치 : 쿼리결과 접근, 루프를 돌며 개별 값에 접근
    - fetch 커서명 is 변수 ..;
  - 커서 닫기 : 메모리상 존재하는 쿼리결과 소멸
    - close 커서명;   

```sql
create or replace procedure pd2_select
is
    --1.커서선언
    cursor pd2_csr is
        select no,pdcode,pdname,price
        from pd2;
    
    --변수선언
    pd2_rcd pd2%rowtype;

begin
    --2.커서 열기
    open pd2_csr;
    
    --3.패치
    loop
        fetch pd2_csr into pd2_rcd.no, pd2_rcd.pdcode, pd2_rcd.pdname,
                        pd2_rcd.price;
        exit when pd2_csr %notfound;
        
        dbms_output.put_line(pd2_rcd.no|| pd2_rcd.pdcode|| pd2_rcd.pdname||
                        pd2_rcd.price);
    end loop;
    
    --4.커서닫기
    close pd2_csr;
    
    exception when others then
        raise_application_error(-20006,'pd2 select error');
end;


exec pd2_select();

2B01LG노트북25000
3B04키보드19000
1A01삼성노트북25000
```

- %notfound : 커서에서만 사용가능한 속성
  - 더이상 할당 할 데이터가 없다는 뜻

### c. for loop 커서
```sql
create or replace procedure pd2_select2
is
    cursor pd2_csr is
    select no, pdcode, pdname, price from pd2;
begin
    for pd2_row in pd2_csr loop
        dbms_output.put_line(pd2_row.no || ' ' || pd2_row.pdcode ||
        ' ' || pd2_row.pdname || ' ' || pd2_row.price);
    end loop;
    
    exception when others then 
        raise_application_error(-20007, 'pd2 select error!');
end;

exec pd2_select2;
```

### d. SYS_REFCURSOR
```sql
create or replace procedure pd2_select3
(
    pd2_cursor out SYS_REFCURSOR
)
is
begin
    open pd2_cursor for
        select no, pdcode, pdname, price, company, regdate
        from pd2;
    
    exception when others then
        raise_application_error(-20009,'에러');
end;
```
***

## 5. 참고사항
### a. rowtype
- table명.coulmn명%type
  - 해당 테이블의 컬럼의 데이터타입과 동일하다는 뜻
- %rowtype
  - %type과 유사하나, 한 개 이상의 값에 대해 적용
  - 로우타입 변수를 선언해 테이블에 있는 row를 대입 가능   

```sql
create or replace procedure prof_info
(
    p_profno professor.profno%type
)
is
     v_prof_row professor%rowtype; --professor 테이블의 한 개의 row(레코드) 정보를 담을 수 있는 타입
     v_result varchar2(2000);
begin
    select * into v_prof_row
    from professor
    where profno = p_profno;
    
    v_result := v_prof_row.profno || ' ' || v_prof_row.name ||
        ' ' || v_prof_row.position;
    
    dbms_output.put_line(v_result);
    
    exception when others then
        raise_application_error(-20002,'professor 조회 에러');
end;

exec prof_info(1003);

1003 송도권 전임강사
```

### b. in,out
- in 매개변수
  - 일반적인 매개변수, 입력 용 매개변수
  - in 매개변수에는 값을 넣는다
- out 매개변수
  - 결과를 출력하는 용도의 매개변수   
  - out 매개변수에는 변수를 넣는다   

```sql
create or replace procedure prof_info2
(
    p_profno in professor.profno%type, --in매개변수
    o_name out professor.name%type, --out매개변수
    o_pay out professor.pay%type --out매개변수
)
is
begin
    select name, pay into o_name, o_pay
    from professor
    where profno= p_profno;
    
    exception when others then
        raise_application_error(-20003,'professor 조회 에러');
end;

--out 매개변수가 있는 프로시저 실행하기
declare
    v_name professor.name%type,
    v_pay professor.pay%type;
begin
    prof_info2(1002,v_name,v_pay);
    dbms_output.put_line('이름:' || v_name || ',급여:'||v_pay);
    
    exception when others then
        dbms_output.put_line('error');
end;

--이름:박승곤,급여:380
```
