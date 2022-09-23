---
layout: post
title:  "2. 웹프로그래밍 기초"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

- 서블릿(Servlet)
  - 웹 개발을 위해 만든 표준
  - 서블릿 규약에 따라 만든 클래스
  - 서블릿을 만들기 위해서는 자바코드 작성, 코드를 컴파일해 클래스 파일 생성
    - 서블릿은 **실행코드 방식**
    - 화면에 출력되는 데이터를 바꾸고 싶은 경우
      - 코드 수정, 컴파일, 클래스를 알맞은 곳에 복사
      - 개발 효율성 하락
- JSP
  - 서블릿의 단점을 보완하기 제작
  - **스크립트 방식**
  - 서블릿 표준을 기반으로 만들어짐
  - 내부적으로 jsp파일이 번역되면 최종 결과물로 서블릿이 만들어짐   

### 서블릿 코드의 예
```java
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/NowServ") // 서블릿 경로명

public class NowServlet extends HttpServlet {

  @Override //get방식으로 요청한 경우 실행되는 메서드
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    //응답문서에 대한 ContentType 지정
    response.setContentType("text/html; charset=euc-kr");
    
    Date now = new Date();
    
    //브라우저에 출력할 출력 스트림
    PrintWriter writer = response.getWriter();
    
    writer.println("<html>");
    writer.println("<head><title>현재 시간</title></head>");
    writer.println("<body>");
    writer.println("<b>현재 시간:</b>");
    writer.println(now.toLocaleString());
    writer.println("</body>");
    writer.println("</html>");
    
    writer.close();
  }
}
```
- HttpServlet
  - doGet메서드
    - url에서 엔터 클릭 시 메서드 실행

## 1. JSP란
- JSP (Java Server Pages)
  - 스크립트 언어
  - 자바 서블릿 기술을 확장, 웹 환경에서 자바만으로 Server Side 모듈을 개발하기 위한 기술
  - 웹 프로그래밍언어, 자바기반의 동적페이지를 생성하기 위한, **서버에서 실행되는 스크립트 언어**
  - 자바가 제공하는 기능 사용 가능
  - 자바언어의 특징을 그대로 보유
  - HTTP와 같은 프로토콜에 따라 클라이언트의 요청을 처리, 응답
  - 표현언어, 표현식, 스크립트릿 등 다양한 스크립트 요소와 액션 태그 제공   

## 2. JSP 동작 구조
- 웹브라우저에서 jsp페이지를 웹서버로 요청
- 웹서버는 jsp에 대한 요청을 웹컨테이너로 전달
- 웹컨테이너는 해당 jsp페이지를 찾아 parsing 과정
  - 서블릿(java)파일을 컴파일(class)
- 컴파일된 서블릿은 최종적으로 웹브라우저에 응답
- parsing 과정은 최초 요청시에만 실행   

## 3. 웹 컨테이너
- 웹 어플리케이션을 실행할 수 있는 컨테이너
- JSP와 서블릿이 실행 가능한 서버
- 톰캣 : 웹 컨테이너로서 jsp와 서블릿을 지원
- jsp 사용 이유
  - 자바 언어 기반, 플랫폼 상관없이 사용 가능
  - 스크립트언어로 쉽게 배울 수 있다
- jsp는 사실상 자바언어의 일부
  - 서블릿이라 불리는 클래스로 변환되어 실행
  - jsp로 웹 서비스 구축 시
    - 웹 서버가 java언어를 이해해야 한다
    - 클래스의 바이트코드를 이해하는 소프트 웨어는 JVM 뿐이다
    - 대부분의 웹 서버는 자바언어를 해석할 수 없다
    - 서블릿 클래스의 해석 및 관련 객체를 관리해주는 프로그램의 도움이 필요하다
      - => 웹 컨테이너
- 웹 컨테이너의 역할
  - 서블릿을 관리
    - 서블릿에 대한 요청을 받고 응답해주는 중간 역할
    - 클라이언트와 서블릿간의 요청과 답변 전달
    - HttpRequest와 HttpResponse 객체를 만들어 서블릿의 doPost(), doGet()메서드를 호출
  - 생명주기 관리
    - 서블릿 클래스를 로딩하여 인스턴스화
    - 초기화 메서드 호출
    - 요청이 들어오면 적절한 서블릿 메서드 호출   

## 4. 스크립트 방식과 실행코드 방식
![캡처](https://user-images.githubusercontent.com/99188096/166194675-7946bcab-ef4b-470a-830b-98b21536b97a.JPG)   
- 스크립트 방식
  - 번역은 최초 요청시 한번 실행
  - 그 이후 해당 페이지 요청 시, 번역된 코드 실행
  - asp, jsp등의 웹 어플리케이션 서버 방식
  - CGI방식의 실행코드 상긱을 사용하는 것보다 전체적인 성능이 뛰어나다
  - 클라이언트 사이드 스크립트
    - 클라이언트쪽에서 처리
    - javascript, VBScript
  - 서버 사이드 스크립트
    - 서버에서 처리
    - ASP, JSP, PHP
- 실행코드 방식
  - CGI방식, 서블릿   

## 5. WAS란
- 어플리케이션 서버 (Application Server)
  - 클라이언트/서버 시스템 구조에서 서버쪽 어플리캐이션의 생성과 실행, 소멸을 관리하는 프로그램
- 웹 어플리케이션 서버 (Web Application Server)
  - 서블릿과 서블릿 컨테이너와 같이 웹 기술을 기반으로 동작되는 어플리케이션 서버
  - 자바에서 말하는 WAS
    - java EE 기술 사양을 준수하여 만든 서버
    - JEUS, WebLogic, JBoss 등
  - 서블릿 컨테이너(웹 컨테이너)
    - java EE 기술 중 서블릿, JSP등 웹 관련 부분만 구현한 서버
    - 톰캣, resin, jetty 등
