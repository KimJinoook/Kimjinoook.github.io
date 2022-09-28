---
layout: post
title:  "스키마/테이블 접근 에러, does not exist"
subtitle:   ""
categories: others
tags: error postgresql springboot
comments: false
header-img: 
---

## 스키마 혹은 테이블 접근에러

- 환경 : springboot / postgresql
- 에러 : relation "테이블명" does not exist   

![error](https://user-images.githubusercontent.com/99188096/192695135-0c28bc21-ab6f-4351-8fd9-ffde41df728a.PNG)   

'테이블명' 이름의 관계가 없다   
보통 이름에 해당하는 스키마나 테이블이 업을 경우 오류 발생   
그런 경우에는 스키마나 테이블을 만들어주면 되지만   
스키마, 테이블 모두 만든 생태에서 이런 오류가 발생하면    
원인이 정말 다양해진다   

주된 원인으로는   
1. 테이블명/스키마명이 대소문자 혼용일 경우   
2. 원하는 테이블이 있는 스키마가 아니라, 다른 스키마에서 해당 테이블을 찾고있을 경우   

### 해결 방법   
#### 테이블명/스키마명이 대소문자 혼용일 경우
- 가장 좋은 방법은 테이블명을 소문자로만 만드는 것
- postgreSQL은 기본적으로 모든 쿼리가 소문자로 변환된다   
  - select & from TAble 쿼리를 실행하면 table 이름의 테이블을 조회한다
  - 테이블의 이름이 대문자 혹은 혼용일 경우 쿼리에서 테이블 이름을 큰따옴표로 감싸주어 대소문자 구분을 지정한다
    > select * from "TAble"   

```sql
-- 테이블명이 table 일 경우
select * from table -- 성공
select * from TABLE -- 성공
select * from TAble -- 성공

-- 테이블명이 TAble 일 경우
select * from table -- 실패
select * from TAble -- 실패
select * from "TAble" -- 성공
```


#### 테이블을 다른 스키마에서 찾을 경우
- 데이터베이스 내의 테이블은 스키마로 그룹지어져있다.
- 오라클 등은 db사용자 생성 시, 사용자에 속하는 스키마가 만들어진다
  - 때문에 데이터베이스에 테이블 생성 시, 자신의 스키마에 생성되고 조회또한 마찬가지
- postgreSQL은 데이터베이스마다 public schema가 존재한다
  - 스프링부트에서 마이바티스 등을 이용해 쿼리문을 실행하면, 기본적으로 public schema에서 조회를 한다
  - 만약 public이 아닌, 따로 생성한 스키마에 테이블을 생성했다면, 해당 스키마를 조회할 수 있도록 해주어야 한다   

##### 쿼리에서 스키마 지정   
```sql
-- 매퍼.xml 에서 쿼리 작성 시
select * from 스키마명.테이블명
```

##### application.properties에서 스키마 지정   
```java
//하이버네이트 일 경우
spring.jpa.properties.hibernate.default_schema=dbo

//jdbc일 경우
jdbc:postgresql://localhost:5432/mydatabase?currentSchema=스키마명
```

##### 어노테이션을 통한 스키마 지정   
```java
//하이버네이트 일 경우
@Entity
@Table(schema="스키마명")
public class Table {

}
```

