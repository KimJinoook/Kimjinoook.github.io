---
layout: post
title:  "13. 액션태그"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

- jsp 페이지에서 페이지의 모듈화와 흐름을 제어
  - include, forward 액션태그 제공
- 자바빈 사용을 위해
  - useBean, setProperty, getProgerty 액션테그 제공
- 사용법 
  - <jsp:include page="포함될 페이지" flush="false"\>   
  - 포함될 페이지 : 상대 경로나 절대경로 사용
  - page 속성 값은 표현식 사용 가능
  - flush
    - 포함될 페이지가 제어로 이동될 때 현재 포함하는 페이지가 지금까지 출력버퍼에 저장한 결과를 처리하는 방법
    - true
      - 지금까지 저장한 내용을 웹브라우저에 출력하고 버퍼를 비운다   

## 1. include 액션태그
- 처리과정
  - 웹브라우저가 a.jsp를 요청
  - 서버는 페이지 처리 중, a.jsp페이지 출력내용을 출력 버퍼에 저장
  - include 액션태그를 만나면 프로그램 제어를 b.jsp 페이지로 이동
  - b.jsp페이지는 페이지 내 출력 내용을 출력 버퍼에 저장하는 등의 작업 처리
  - b 처리가 끝나면 다시 a.jsp로 제어 이동
- 같은 request 내부 객체를 공유한다   

#### includeTest   

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String url = "date.jsp";
%>
	<h1>main 페이지</h1>
	<!-- include 액션태그를 이용 date.jsp 포함시키기 -->
	<jsp:include page = "date.jsp"></jsp:include>
	
	<h2>include 액션태그 - 표현식 이용</h2>
	<jsp:include page="<%=url %>"></jsp:include>

	<h2>include 액션태그 - param 이용</h2>
	<jsp:include page = "date.jsp">
		<jsp:param value ="5" name="no"/>
		<jsp:param value ="홍길동" name="name"/>	
	</jsp:include>
	
</body>
</html>
```

#### date.jsp   

```html
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
	String name = request.getParameter("name");

	Date d = new Date();
	String str = d.toLocaleString();

%>
	<hr>
	<p> no : <%=no %></p>
	<p> name : <%=name %></p>
	<p> 현재 일자 : <%=str %></p>
</body>
</html>
```

***

## 2. forward 액션 태그
- 페이지의 제어흐름을 현재 페이지에서 다른 페이지로 이동시킬 때 사용
- 페이지 내 액션태그를 만나면 그 전까지 출력 버퍼에 저장되어 있던 내용을 제거
  - 액션태그가 지정하는 페이지로 이동
- <jsp:forward page="이동할 페이지명"/\>   

#### forwardTest.jsp    

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="frm1" method="post" action="forwardTest_ok.jsp">
		이름 : <input type="text" name="name"><br>
		주소 : <input type="text" name="address"><br><br>
		<input type="submit" value="전송">
	</form>

</body>
</html>
```

#### foreardTest_ok.jsp   

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	request.setAttribute("fruit","사과");

%>
	<h1>forwardTest_ok.jsp</h1>
	<jsp:forward page="b.jsp">
		<jsp:param value="축구" name="hobby"/>
	</jsp:forward>

</body>
</html>
```

#### b.jsp   

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String name= request.getParameter("name");
	String addr= request.getParameter("address");
	String hobby= request.getParameter("hobby");
	
	String fruit = (String)request.getAttribute("fruit");
	
%>

	<h1>b.jsp 페이지</h1>
	<h2>요청 파라미터</h2>
	<p>name : <%=name %></p>
	<p>address : <%=addr %></p>
	<p>hobby : <%=hobby %></p>
	<p>fruit : <%=fruit %></p>

</body>
</html>
```
