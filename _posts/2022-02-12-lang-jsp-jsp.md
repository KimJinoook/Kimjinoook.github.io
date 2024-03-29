---
layout: post
title:  "1. 개요, 환경변수 설정 및 웹 어플리케이션 생성"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

# 개요, 환경변수 설정 및 웹 어플리케이션 생성
- html
  - **클라이언트에서 실행**
  - 사용자 컴퓨터의 브라우저 : html문서 요청
  - 웹서버 : 해당 html문서 탐색, 순수 html문서로 결과 응답
- jsp
  - **웹서버에서 실행**
  - 사용자 컴퓨터의 브라우저 : jsp문서 요청
  - 웹서버
    - 해당 jsp문서 탐색
    - 서블릿(java파일 생성)으로 변환 (parsing과정)
    - 컴파일 (class파일 생성) (jsp문서 해석, 가공)
    - 컴파일된 서블릿(class) 최종적으로 웹브라우저에 응답   

### 웹페이지 요청
- 요청(request) : 사용자pc -> 웹서버
- 응답(response) : 웹서버 -> 사용자pc
- http://www.naver.com
- http://202.131.30.11/
- http://202.131.30.11:80/app/index.jsp
- http://202.131.30.11:80/app/list.jsp?currentpage=1
- ip 접속, 포트를 찾은 후 연결, jsp파일 요청
- 포트
  - 80 : http(웹서버)
  - 21 : ftp
  - 25 : smtp
  - 1521 : dbms


### 인터넷 주소
- 세가지 구조
  - 도메인(ip), 어플리케이션(서버에서 자원의 위치, 폴더), 소스페이지(웹페이지, html jsp, asp등)
  - http://202.131.30.11:80/app/index.jsp
  - http://도메인/어플리케이션/소스페이지
- ip
  - 서버(컴퓨터)를 찾는 주소
- 포트 (port)
  - 호스트가 외부와 통신을 하기 위한 통로
  - 컴퓨터 내 프로그램을 찾는 주소
  - 프로그램들마다 모두 포트를 가지고 있고, 포트를 통해 해당 프로그램에 통신으로 접근
- 어플리케이션
  - 웹 프로젝트의 이름 (서버에서 자원의 위치, 폴더)
- 소스페이지
  - 웹 페이지
- 파라미터
  - 소스페이지를 호출할 때 넘겨주는 인자값
  - 같은 소스페이지여도, 파라미터값에 따라 다른 화면을 보여주기 위함

### URL
- Uniform Resource Locator
- 웹 상에서 서비스를 제공하는 각 서버들에 있는 **파일들의 위치**를 표시하기 위한 것
- 프로토콜://호스트:포트/경로/파일명/확장자/쿼리문자열
- http://java.sun.com:80/javase/6/docs/api/index.html
- 프로토콜
  - 서버와 클라이언트가 통신할 때 사용할 프로토콜 입력
  - http, ftp, https 등
- 호스트(도메인
  - 클라이언트가 접속할 서버 주소
- 포트
  - 서버와 클라이언트가 통신할 때 사용할 포트
- uri (Uniform Resource Identifier)
  - url에서 프로토콜, 호스트명 포트번호를 제외한 것
  - 존재하는 자원을 식별하기 위한 일반적인 식별자를 규정
  - /javase/6/docs/api/index.html
  - 포트번호 다음 슬래쉬부터
- 쿼리문자열
  - 주소 뒤에 추가로 붙는 정보
- 인자
  - 각각 &을 이용하여 구분
  - 이름과 값은 =을 이용하여 구분
  - ?이름1=값1&이름2=값2..   


***

## 2. 개발환경 구축
- 웹 프로그래밍 절차
  - 개발환경 구축
  - 웹 어플리케이션 코드 개발 및 테스트
  - 완성된 웹 어플리케이션을 서비스 환경에 배포
    - 배포(deploy)
    - jsp/서블릿의 경우 war파일로 묶어 배포하거나, 개별 파일을 배포하기도 한다
    - 개발된 결과물을 실제 서비스로 사용되는 서버 장비에 복사하는 과정이 배포
- 프로그램
  - jdk
    - 자바 개발 도구
    - jsp 2.2 표준 : 자바 6 이상의 버전 필요
  - 웹 컨테이너
    - jsp와 서블릿을 실행시켜주는 컨테이너
    - 톰캣, 제티, GlassFish
  - 또는 WAS(Web Application Server)
    - WebLogic, JEUS, JBoss
  - 코드 편집기

### 웹컨테이너 설치
- [톰캣](https://tomcat.apache.org/download-90.cgi)
  - 설치시 포트번호 9090
    - 8080은 오라클에서 사용중
  - 톰캣 설치 및 실행 확인
    - http://localhost:9090

### 디렉토리 확인   
![캡처](https://user-images.githubusercontent.com/99188096/166173583-3ebb3f08-ee92-4bf8-ba82-120901caaf65.JPG)   
- bin : 톰캣을 실행하고 종료시키는 스크립트 파일 위치
- conf : server.xml파일을 포함한 톰캣 설정 파일 위치
- lib : 톰캣을 실행하는데 필요한 라이브러리(jar)파일 위치
- logs : 톰캣 로그파일 위치
- temp : 톰캣이 실행되는 동안 임시 파일이 위치
- webapps : 웹 어플리케이션이 위치
- work : 톰캣이 실행되는 동안 사용되는 작업 파일이 위치
- 웹 어플리케이션은 기본적으로 webapps 디렉토리에 배포
  - server.xml파일을 사용해 경로 변경 가능

### 환경변수 및 설정
- 시스템 변수
  - CATALINA_HOME 생성
    - 값 : 톰캣 설치경로
    - c:\Tomcat9.0
  - PATH 편집
    - %CATALINA_HOME%bin 추가
  - classpath 편집
    - 기존
      - %classpath%;.;C:\Java\jdk1.8.0_321\jre\lib\ext\ojdbc8.jar
    - 추가
      - c:\Tomcat 9.0\lib\jsp-api.jar
      - c:\Tomcat 9.0\lib\servlet-api.jar
    - 확인
      - %classpath%;.;C:\Java\jdk1.8.0_321\jre\lib\ext\ojdbc8.jar;c:\Tomcat 9.0\lib\jsp-api.jar;c:\Tomcat 9.0\lib\servlet-api.jar
- 톰캣 실행
  - 시작, 모든프로그램, apache tomcat9.0, Monitor Tomcat 실행
- 웹브라우저 테스트
  - http://localhost:9090
- servlet-api.jar 복사
  - C:\Java\jdk1.8.0_321\jre\lib\ext 붙여넣기
- ojdbc8.jar 복사
  - C:\Java\jdk1.8.0_321\jre\lib\ext 붙여넣기
    - 기존 jdbc 사용 간 해당 작업 실행 완료
  - C:\Tomcat 9.0\lib 붙여넣기
- c:\Tomcat 9.0\conf\context.xml 수정
  - <Context\>를 <Context reloadable="true" privileged="true"\>로 변경
  - 컨텍스트 변경시 자동으로 재로딩 되도록 설정
- c:\Tomcat 9.0\conf\context.xml
  - 포트번호 9090 확인
- 톰캣 재실행 후 웹브라우저 테스트 확인

***
  
## 3. 웹 어플리케이션 생성
 웹 어플리케이션 생성
- 웹 컨테이너에 직접 개발 디렉토리 생성
  - 웹 컨테이너 디렉토리 / webapps / 아래 개발 디렉토리 생성
  - 개발디렉토리 (testsite) 폴더의 하위 볼더로 WEB-INF 폴더 생성
  - WEB-INF 폴더의 하위폴더로 classes 폴더 생성

![캡처](https://user-images.githubusercontent.com/99188096/166176235-e58fbcd9-8931-4b12-a2e8-36b63e08be47.JPG)   
  
### 생성 및 확인
- jsp 파일 생성 후 testsite 폴더 저장
- http://localhost:9090/testsite/폴더명.확장자명 확인   
  
```jsp
<%@ page contentType="text/html;charset=euc-kr"%>
<%@ page import="java.util.*" %>
<!--page 지시자(디렉티브) -page에 대한 정보 설정-->
<%
	//스크립트릿 = jsp코드를 넣는 곳
	Date d = new Date();
	String str = d.toLocaleString();
	out.print(str); //out=>jsp의 내장 객체, out.print() => 웹에 출력하라는 것
	/*자바의 여러 줄 주석*/
	//자바의 한줄 주석
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE> jsp연습1 </TITLE>

 </HEAD>

 <BODY>
	<h1>jsp페이지</h1>
	<p>현재 날짜 : <%=d %></p>
		<!-- 표현식 => out.print(d)와 동일-->

		<!--html 주석-->
		<%-- jsp 주석 --%>
  
 </BODY>
</HTML>
<!-- testsite\now1.jsp-->
```

- <%@ : 지시자, page에 대한 정보 설정
- jps의 영역   
	- jsp영역   
		- <% ~ %\> 사이 작성   
	- html 영역   
	- javascript 영역   

***
	
## 4. Server.xml 수정
### a. 기본 경로인 webapps 이외의 경로 추가   
#### 기존 호스트 문구 하단
```html
<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />
		
</Host>
```
#### 경로 추가
```html
<Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
	prefix="localhost_access_log" suffix=".txt"
	pattern="%h %l %u %t &quot;%r&quot; %s %b" />
		
	<Context path="/samplesite" docBase="C:\lecture\jsp\samplesite"></Context>
</Host>
```
- context path를 추가해, url에 해당 경로 입력시 탐색할 폴더 경로 지정    
	
### b. get 방식의 한글처리 추가
- get 방식으로 보낸 데이터가 한글인 경우를 처리하기 위한 수정   
#### 기존   
```html
	<Connector port="9090" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```
#### 인코딩 추가   

```html
	<Connector port="9090" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" 
		URIEncoding="euc-kr" /> 
```
