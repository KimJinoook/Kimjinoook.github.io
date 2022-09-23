---
layout: post
title:  "8. 게시판만들기"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---


## 1. db
### 테이블스페이스, 사용자   
```sql
--테이블 스페이스 생성
create tablespace herbmall
datafile 'c:\oracle\data\herbmall.dbf' size 48m
extent management local
uniform size 64k
segment space management auto;

--사용자 계정 생성
alter session set "_ORACLE_SCRIPT"=true;

create user herb
identified by herb123
default tablespace herbmall;

--사용자 권한부여
grant connect,resource to herb;
--뷰 생성 권한 부여하기
grant create view to herb;
```

### 테이블
```sql
create table board
(
no number primary key, --번호
name varchar2(20) not null, --이름
pwd varchar2(20) not null, --비밀번호
title varchar2(100) not null, --제목
email varchar2(80) null, --이메일
regdate date default sysdate, --작성일
--regip varchar2(15) null, --작성IP
readcount number default 0 null, --조회수
content clob null --내용
);

create sequence board_seq
increment by 1
start with 1
nocache;


--drop table book  cascade constraint;
create table book(
    no number primary key,
    title varchar2(100),
    publisher	varchar2(50),
    price number,
    joindate date default  sysdate
);

--no는 자동증가..
--drop sequence book_seq;
create sequence book_seq
increment by 1
start with 1
nocache;

select * from book;



CREATE TABLE guestbook ( 
no		    number		primary key,   --글 번호	
name		varchar2(20)    not null,    		--작성자 이름
pwd			varchar2(20)    not null, 		--비밀번호
content		varchar2(4000)	null, 		--글의 내용
regdate		date		default  sysdate 	--작성일자
);

create sequence guestbook_seq
increment by 1
start with 1
nocache;

select * from guestbook;


```



## 2. DAO / DTO

### BoardDTO   
```java
package com.mystudy.board.model;

import java.sql.Timestamp;

public class BoardVO {
	private int no;
	private String name;
	private String pwd;
	private String title;
	private String email;
	private Timestamp regdate;
	private int readcount;
	private String content;
	
	
	
	public BoardVO() {
		super();
	}



	public BoardVO(int no, String name, String pwd, String title, String email, Timestamp regdate, int readcount,
			String content) {
		super();
		this.no = no;
		this.name = name;
		this.pwd = pwd;
		this.title = title;
		this.email = email;
		this.regdate = regdate;
		this.readcount = readcount;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

```
