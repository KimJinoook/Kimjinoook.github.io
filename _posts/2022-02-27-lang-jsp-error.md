---
layout: post
title:  "16. 에러처리"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

### jsp에서의 에러 메시지
- 에러가 발생하면 어떤 경로로 발생했는지 스택을 뒤집어 추척, 에러메세지 표시
- 에러 발생시 에러메세지가 아닌, 다른 페이지를 보여주기 위해 에러처리   

### HTTP 에러코드표
- 상황에 따라 다른 코드 표시
- 404 : 사용자가 잘못된 페이지 요청시 발생
  - not found, 문서를 찾을 수 없음
- 500 : 프로그램 코딩 오류 시
  - internal server error, 서버 내주 오류   

### 404, 500 오류 처리
- 프로젝트 폴더 내 webapp/WEB-INF 폴더에 web.xml 추가
- web.xml에 기술
  - <error-page\></error-page\> 태그 안에 처리할 에러코드 페이지 기술   

```html

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns="http://xmlns.jcp.org/xml/ns/javaee" 
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
  <display-name>semiProject2</display-name> <!-- 컨텍스트패스 -->
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  
  <error-page>
  	<error-code>404</error-code>
  	<location>/404.html</location> 
      <!-- 코드 발생시 보낼 페이지 (컨텍스트페스 자동 추가, http://localhost:9090/semiProject2/404.html)-->
  </error-page>
  <error-page>
  	<error-code>500</error-code>
  	<location>/404.html</location>
  </error-page>

 
</web-app>
```   
