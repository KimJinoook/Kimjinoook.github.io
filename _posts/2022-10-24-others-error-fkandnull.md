---
layout: post
title:  "fk 참조시 null값과 공백값의 차이"
subtitle:   ""
categories: others
tags: error postgresql springboot
comments: false
header-img: 
---
### 환경 : postgresql   


### 에러   

테이블의 fk가 null값을 허용하는 컬럼이고 분명히 아무 값도 넣지않았는데, fk가 잠조하는 부모테이블에 해당하는 값이 없다고 뜬다.   

```sql
SQL Error [23503]: ERROR: insert or update on table "varchar_test" violates foreign key constraint "varchar_test_fk"
  Detail: Key (varchar)=() is not present in table "varchar_father".
```

![캡처](https://user-images.githubusercontent.com/99188096/197690480-9c9d3890-655f-4d68-b7ad-8a91a7746c88.PNG)   

테스트용 테이블이며, varchar_father 테이블의 pk를 varchar_test에서 fk로 참조하고있다.   
varchar_father테이블은 아무런 데이터도 들어있지 않다.   

```sql
--정상
insert into scdsp.varchar_test
values(null)

--오류발생
insert into scdsp.varchar_test
values('')
```

null값을 넣으면 정상적으로 값이 들어가지만, ''로 값을 넣으면 부모테이블에 해당 데이터가 없다고 오류가 발생   

### 원인   
이는 null과 '', 공백없는 따옴표의 차이에 있다. 오라클의 경우 null과 ''를 동일하게 취급하지만, postgresql이나 mysql은   
null은 어떠한 값도 존재하지 않는 것이고   
''은 길이가 0인 값이 존재하는 것으로 인식.   
![캡처](https://user-images.githubusercontent.com/99188096/197713302-76cccb3d-c6b5-4575-b680-2226fcd6d701.PNG)   


### postgresql의 테이블 구성   
postgresql의 테이블이나 인덱스는 일반적으로 8kb 고정크기의 페이지 배열로 저장된다   
- 페이지의 레이아웃   
![캡처](https://user-images.githubusercontent.com/99188096/197709948-8764b920-1efb-409c-bb05-475849781ae5.PNG)   
   
항목 자체가 저장되는 구조는 아래 표를 따른다   
![캡처](https://user-images.githubusercontent.com/99188096/197711653-bef11b74-63fd-451c-b2d3-a93ef38b26ed.PNG)   
각 테이블 행은 고정 크기 헤더를 가지고 있다   
대부분 23바이트를 차지하며, 고정 헤더 다음에 각 열당 하나의 비트로 해당 열이 null인지 아닌지 식별한다   
1비트는 null이며, 0비트는 null이 아님을 나타낸다.   
postgresql은 이로 인해 null과 공백없는 따옴표를 구분하여 서로 다른 것으로 인식한다.



