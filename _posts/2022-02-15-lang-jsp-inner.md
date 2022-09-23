---
layout: post
title:  "4. 내장객체"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

- jsp 내장 객체
	- 별다른 선언과정과 객체 생성 없이 사용할 수 있는 9개의 객체들을 웹컨테이너가 제공
- 내장 객체의 4가지 범주
	- jsp 페이지 입출력 관련 내장 객체
	- jsp 페이지 외부 환경 정보 제공 내장 객체
	- jsp 페이지 서블릿 관련 내장 객체
	- jsp 페이지 예외 관련 내장 객체   

![캡처](https://user-images.githubusercontent.com/99188096/166202318-e0bf6159-5ce0-4fee-9bac-f4463752cc35.JPG)   

- request, session, application, pageContext 내장 객체의 메서드
	- 속성값을 저장하고 읽을 수 있는 메서드
![캡처](https://user-images.githubusercontent.com/99188096/167977697-d6c0f0b9-66e7-4950-8dd9-26e3014648d0.PNG)   


## 1. request
- 웹 브라우저에서 jsp 페이지로 전달되는 정보의 모임
- HTTP 헤더와 HTTP 바디로 구성
- 웹 컨테이너는 요청된 HTTP 메세지를 통해 HttpServletRequest 객체를 얻어내 사용자의 요구사항 확인
- jsp 페이지에서는 HttpServletRequest 객체를 request 객체명으로 사용   
- request 객체의 요청 파라미터 메서드   
	- ![캡처](https://user-images.githubusercontent.com/99188096/166202554-5294a711-50e6-4268-a088-26b9b86bd12b.JPG)   
- HTML 폼과 요청 파라미터 처리
	- 웹 브라우저 폼에 입력한 값 처리
	- 웹 브라우저는 폼에 입력한 정보를 파라미터로 전송
		- request 기본 객체는 웹 브라우저가 전송한 파라미터를 읽을 수 있는 메서드 제공   

### request 객체 확인
#### test02.jsp
```html
<%@ page contentType = "text/html; charset=euc-kr" %>
<html>
	<head><title>폼 생성</title></head>
	<body>
		<h1>request 객체 연습</h1> 
		
		<!-- post 방식으로 전송-->
		<form action="test02_ok.jsp" method="post">
			이름: <input type="text" name="name" > <br>
			주소: <input type="text" name="address" > <br>
			좋아하는 동물:
			<input type="checkbox" name="pet" value="dog">강아지
			<input type="checkbox" name="pet" value="cat">고양이
			<input type="checkbox" name="pet" value="pig">돼지<br>
			동의합니다<input type="checkbox" name="agree">
			<br><br>
			<input type="submit" value="전송">
		</form> <br>
		
		
		<!--get방식-->
		<a href="test03.jsp?no=7&name=홍길동">test03 페이지로 이동하기</a>
		
	</body>
</html>

```

#### test02_ok.jsp / post 방식
```html
<%@ page contentType = "text/html; charset=euc-kr" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE> New Document </TITLE>
 </HEAD>

 <BODY>
	<%
		//post 방식

		//요청 파라미터에 대한 한글 인코딩 처리
		request.setCharacterEncoding("euc-kr");

		//1.요청 파라미터 읽어오기
	   	//넘어오는 파라미터명과 변수명 일치
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String agree=request.getParameter("agree");
		String[] petArr=request.getParameterValues("pet");

		if(petArr != null){
			out.print("<h2>좋아하는 동물</h2>");

			for(int i=0; i<petArr.length;i++){
				out.print("<p>"+petArr[i]+"</p>");
			}
		}

		//2. db작업
		//3. 결과처리
	%>
	<h1> 요청 파라미터들 </h1>
	<p>이름 - <%=name%></p>
	<p>주소 - <%=address%></p>
	<p>동의여부 - <%=agree%></p>

 </BODY>
</HTML>

```

#### test03.jsp / get 방식
```html
<%@ page contentType = "text/html; charset=euc-kr" %>
<!DOCTYPE HTML>
<HTML>
 <HEAD>
  <TITLE> test03 </TITLE>
 </HEAD>
 <BODY>
	<%
		//get 방식으로 이동
		//http://localhost:9090/testsite/day1/test03.jsp?no=7&name=hong

		//1. 요청 파라미터 읽어오기
		String no = request.getParameter("no");
		String name = request.getParameter("name");

		//2. db작업

		//3. 결과처리
	%>
	<h1>get - 요청 파라미터</h1>
	<p>번호 : <%=no%></p>
	<p>이름 : <%=name%></p>
  
 </BODY>
</HTML>

```

- 한글 깨질 시 tomcat9.0/conf/server.xml 내 Connector에 URIEncoding="euc-kr" 추가   

```xml
<Connector port="9090" protocol="HTTP/1.1"
                connectionTimeout="20000"
                redirectPort="8443" 
	   	URIEncoding="euc-kr" />
```


- 클라이언트 정보 및 서버 정보를 구할 수 있는 메서드   
![캡처](https://user-images.githubusercontent.com/99188096/167978044-d3a20314-b65c-4204-94b9-21c0827c826e.PNG)   

```java
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
<head><title>클라이언트 및 서버 정보</title></head>
<body>
클라이언트IP = <%= request.getRemoteAddr() %> <br>
요청정보길이 = <%= request.getContentLength() %> <br>
요청정보 인코딩 = <%= request.getCharacterEncoding() %> <br>
요청정보 컨텐트타입 = <%= request.getContentType() %> <br>
요청정보 프로토콜 = <%= request.getProtocol() %> <br>
요청정보 전송방식 = <%= request.getMethod() %> <br>
요청 URI = <%= request.getRequestURI() %> <br>
컨텍스트 경로 = <%= request.getContextPath() %> <br>
서버이름 = <%= request.getServerName() %> <br>
서버포트 = <%= request.getServerPort() %> <br>
</body>
</html>
```


***

## 2. response
- 웹 브라우저로 응답할 응답 정보를 가지고 있다
- 서블릿
	- HttpServletResponse 객체로
- jsp
	- response 객체로
- 주로 헤더정보 입력, 페이지 리다이렉트 등의 기능 제공   

![캡처](https://user-images.githubusercontent.com/99188096/166391434-d74538c8-e121-4bb1-98a9-b76c21e0a139.JPG)   

- sendRedirect
	- html : a 태그
	- javascript : location.href=""
	- **get 이동 방식**   


```html
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>현재 페이지 - responseTest.jsp</h1>
	
	<%
		response.sendRedirect("../day1/test02.jsp");
	%>
</body>
</html>
```

***

## 3. out
- jsp 페이지가 생성한 결과를 웹 브라우저에 전송해주는 출력 스트림
- 서블릿
	- javax.servlet.jsp.jspWriter
- jsp
	- out
- println() 메서드
	- 웹 브라우저에 출력을 하기 위한 메서드
	- 표현식 <%=코드%\> 와 <% out.println(코드)%\> 는 동일   

- out 객체의 메서드   
![캡처](https://user-images.githubusercontent.com/99188096/166394647-77332f98-ed92-466c-a66a-cb2afdce7795.JPG)   


***

## 4. pageContext

- 현재 jsp 페이지의 컨텍스트를 나타낸다
- 주로 다른 내장 객체를 얻어내거나, 페이지의 흐름제어, 에러데이터를 얻을 때 사용
	- jspWriter outObj = pageContext.getOut();

![캡처](https://user-images.githubusercontent.com/99188096/167979154-43550168-105e-4efd-bcae-b97935523b87.PNG)   



***

## 5. session
- 웹 브라우저의 요청 시 요청한 웹 브라우저(세션)에 관한 정보를 저장하고 관리하는 내장 객체
- javax.servlet.http.Httpsession
- 클라이언트당 1개 할당
	- 로그인 처리 등에 사용
- request가 하나의 요청을 처리하는데 사용되는 jsp페이지 사이에서 공유
- session은 클라이언트의 여러 요청을 처리하는 jsp페이지 사이에서 공유   

![캡처](https://user-images.githubusercontent.com/99188096/167980703-049a7e65-f218-40e0-9c14-b2aa2fec9647.PNG)   

***

## 6. application
- 웹 어플리케이션의 설정 정보를 갖는 context와 관련이 있는 객체
- 웹 어플리케이션당 1개의 객체 생성
- 하나의 웹 어플리케이션에서 공유하는 변수로 사용
- 웹 사이트의 방문자 기록 등   

![캡처](https://user-images.githubusercontent.com/99188096/167981515-783988cb-a9d2-4d63-a447-989aef1f73e3.PNG)   

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
String info = application.getServerInfo();
int major = application.getMajorVersion();
int minor = application.getMinorVersion();
String path = application.getRealPath("/");
%>
<h2>application내장객체 예제</h2>
웹 컨테이너의 이름과 버전 : <%=info%><p>
서블릿의 버전 : <%=major%>.<%=minor%><p>
웹 어플리케이션 폴더의 로컬 시스템 경로 : <%=path%>

```

***

## 7. config
- 서블릿이 초기화될 때 참조해야 하는 정보를 가지고 있다가 전달
- 컨테이너당 1개의 객체 생성
- 같은 컨테이너에서 서비스되는 모든 페이지는 같은 객체 공유   

![캡처](https://user-images.githubusercontent.com/99188096/167982674-c84ef8d2-3cd5-46b2-a32b-0121e2a15344.PNG)   

***

## 8. page
- jsp 페이지 그 자체를 나타내는 객체
- page 객체는 this로 자기 자신을 참조할 수 있다   

```java
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page info = "Page 내장 객체 예제"%>
<% 
String info = this.getServletInfo();
%>
<h2>Page내장객체 예제</h2>
page디렉티브의 info속성값 " <%=info%> " 을 얻어낸다.

```


***

## 8. exception
- 예외가 발생할 경우, 예외를 처리할 페이지에 전달되는 객체
- page 디렉티브의 isErrorPage="true"로 지정한 페이지에서만 사용 가능   

![캡처](https://user-images.githubusercontent.com/99188096/167982955-f577875f-704d-4525-b66f-1ba8a5eaa8e2.PNG)   


***

## 9. 내장 객체의 영역
- 객체의 유효기간, 객체를 누구와 공유할 것인가를 나타낸다
- page, request, session, application 4개의 영역을 가지고 있다
- page 영역
	- 한번의 클라이언트 요청에 대해 하나의 jsp 페이지가 호출
	- 웹브라우저 요청 시 한개의 페이지만 대응
	- page 영역은 객체를 하나의 페이지 내에서만 공유
	- page 영역은 pageContext 내부 객체 사용
- request 영역
	- 한번의 웹 브라우저의 요청에 대해 같은 요청을 공유하는 페이지 대응
	- 웹 브라우저의 한번의 요청에 한개의 페이지만 요청될 수 있다
		- 같은 request 영역이면 두개의 페이지가 같은 요청을 공유 가능
	- request 영역은 객체를 하나 또는 두 개의 페이지 내에서 공유할 수 있다
	- include 액션태그, forward 액션태그를 사용하면 request 내장객체를 공유, 같은 영역이 된다
	- request 영역은 request 내장객체 사용
- session 영역
	- 하나의 웹 브라우저당 1개의 session 객체 생성
	- 같은 웹 브라우저 내에서 요청되는 페이지들은 같은 객체 공유
	- 회원인증 등에 사용
	- session 영역은 session 내장객체 사용
- application 영역
	- 하나의 웹 어플리케이션당 1개의 application 객체 생성
	- 같은 웹 어플리케이션에 요청되는 페이지들은 같은 객체 공유
	- 방문자수 등
	- application 영역은 application 내장객체 사용
