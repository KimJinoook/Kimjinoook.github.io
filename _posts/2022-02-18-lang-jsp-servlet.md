---
layout: post
title:  "7. 서블릿"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

## 1. 서블릿의 동작 원리
- 서블릿
  - 자바의 클래스 중 오직 서버에서만 해석되어 실행되는 클래스
  - 서블릿 규약에 따라 만든 클래스
  - **최초 브라우저 접속에 의해 인스턴스가 메모리에 올라간다**
- 서블릿 생성
  - 서블릿 API 이용
  - 패키지 이용
    - javax.servlet
      - 프로토콜에 독립적인 서블릿을 만들기 위한 클래스
    - javax.servlet.http
      - http 프로토콜의 고유 기능 (get, post 등) 을 제공하는 서블릿을 만들기 위한 클래스
- 서블릿 구현
  - GenericSevlet 이나 HTTPServlet 중 하나를 상속받아 구현
  - main()메서드가 없다
  - 서블릿의 특정 메서드는 웹 컨테이너(서버)가 호출한다
  - 서버가 서블릿에 요청을 전달할 때마다 서블릿의 **service()** 메서드 호출
  - GenericServlet
    - 요청을 처리하기 위해 자신의 service() 메서드를 오버라이드해야한다
    - service()메서드
      - 두 개의 매개변수(request, response)
  - HttpServlet
    - doGet(), doPost() 메서드를 오버라이드한다   

## 2. 서블릿 작성
- 서블릿 이용 개발 과정
  - 서블릿 소스코드를 저장할 디렉토리 생성
  - 클래스 파일을 저장할 WEB-INF\\classes 디렉토리 생성
  - CLASSPATH 환경변수 설정
    - 톸캣설치지렉토리\\lib\\servlet-api.jar
  - 서블릿 소스코드 작성
  - 소스코드 컴파일 후 생성된 클래스 파일을 classes 디렉토리에 복사
  - web.xml 파일에 서블릿 정보 설정
  - 웹컨테이너 시작
  - 웹브라우저 테스트   

```java
package com.mystudy.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;


@WebServlet("/NowServ")
public class NowServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		Date now = new Date();
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>현재 시간</title></head>");
		writer.println("<body>");
		writer.println("<b>현재 날짜는:</b>");
		writer.println(now.toString());
		writer.println("<hr></body>");
		writer.println("</html>");
		writer.close();
	}
}
```

### get방식으로 데이터 전송
#### html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<BODY>
	<!--GET방식으로 데이터 전송
	사용자가 입력한 데이터는 action에 지정된 GetServlet이라는
	서블릿 페이지로 전달됨-->

	<form action="/mystudy/GetServ" method="GET" >
		아이디: <INPUT TYPE="text" NAME="id"><br>
		비밀번호:<INPUT TYPE="password" NAME="pwd"><br>
		<INPUT TYPE="submit" value="로그인"><INPUT TYPE="reset" value="취소">
	</form>
</BODY>
</html>
```
#### 서블릿
```java
package com.mystudy.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GetServ")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//응답문서에 대한 ContentType 지정
		response.setContentType("text/html;charset=euc-kr");
		
		//출력스트림
		PrintWriter out = response.getWriter();
		
		//1.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//2
		
		//3
		out.print("<html><head><title>get~servlet연습</title></head>");
		
		out.print("<body>");
		out.print("<h1>요청 파라미터 값</h1>");
		out.print("<p>id : "+id+"</p");
		out.print("<p>pwd : "+pwd+"</p");
		out.print("</body>");
		
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}

```

### post 방식으로 데이터 전송
#### html
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<BODY>
	<!--GET방식으로 데이터 전송
	사용자가 입력한 데이터는 action에 지정된 GetServlet이라는
	서블릿 페이지로 전달됨-->

	<form action="/mystudy/PostServ" method="POST" >
		아이디: <INPUT TYPE="text" NAME="id"><br>
		비밀번호:<INPUT TYPE="password" NAME="pwd"><br>
		<INPUT TYPE="submit" value="로그인"><INPUT TYPE="reset" value="취소">
	</form>
</BODY>
</html>
```

#### 서블릿
```java
package com.mystudy.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PostServ")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//응답문서에 대한 ContentType 지정
		response.setContentType("text/html;charset=euc-kr");
		request.setCharacterEncoding("euc-kr");
		
		//출력스트림
		PrintWriter out = response.getWriter();
		
		//1.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//2
		
		//3
		out.print("<html><head><title>get~servlet연습</title></head>");
		
		out.print("<body>");
		out.print("<h1>요청 파라미터 값</h1>");
		out.print("<p>id : "+id+"</p");
		out.print("<p>pwd : "+pwd+"</p");
		out.print("</body>");
		
		out.close();
	}

}

```


***

## 3. 웹 컨테이너
- 사용자가 서블릿에 대한 링크를 클릭
- 웹 컨테이너는 들어온 요청이 서블릿이라는 것을 파악
	- HttpServletResponse 와 HttpServletRequest 객체를 생성
- 사용자가 보낸 URL을 분석, 어떤 서블릿에 대한 요청인지 파악
	- DD : web.xml 참조
	- 해당 서블릿 스레드를 생성, Request / Response 객체를 인자로 넘긴다
- 웹 컨테이너는 서블릿 service() 메서드 호출
	- 요청에 지정한 방식에 따라 doGet() / doPost() 호출 결정
- doGet() / doPost() 메서드는 동적인 페이지 생성
	- 이를 Response 객체에 실어 전송
	- 보낸 후에도 웹컨테이너는 Response 객체에 대한 참조값 보유
- 스레드 작업 종료 후 웹 컨테이너는 Response 객체를 HTTP Response로 전환하여 클라이언트로 전송
	- 마지막으로 Request와 Response 객체를 소멸   

***

## 4. 서블릿 라이프 사이클   
![캡처](https://user-images.githubusercontent.com/99188096/166618858-757fc58b-5438-4bb5-b973-231a2f1b8bda.JPG)   
- 서블릿이 처음 로드 되면 init()메서드 호출
	- 서블릿이 서비스하기 위해 필요한 초기화작업 수행
	- service()메서드 호출
- 초기화된 서블릿은 클라이언트의 요청이 있을 때마다 쓰레드 생성
	- 병행적으로 service()메서드 실행
- 서블릿은 더이상 서비스하지 않을 경우 서블릿 엔진에 의해 메모리에서 unload
	- unload 되기 전 destroy()메서드 실행   

#### 라이프 사이클 확인   
```java
package com.mystudy.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LifeCycle")
public class LifeCycle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	public LifeCycle() {
		super();
		System.out.println("생성자 호출");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() 호출");
	}
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿은 클라이언트의 요청이 있을 때마다 쓰레드가 생성되어 병행적으로 service()메서드 실행
		
		System.out.println("doGet()호출");
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		
		out.print("<html><head><title>서블릿 생명주기</title></head>");
		out.print("<body>");
		out.print("<h1>현재 쓰레드이름</h1>");
		out.print("<p>"+Thread.currentThread().getName()+"<p>");
		out.print("</body></html>");
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

```

***

## 5. 서블릿의 호출
- 어노테이션
	- @webServlet("/경로명")   
	- 경로명 요청 시 해당 어노테이션 아래 서블릿 클래스로 처리   

```java
@WebServlet("/InsertServ")
public class InsertServlet extends HttpServlet {}

//http://localhost:9090/mystudy/InsertServ 요청 시
//InsertServlet 클래스로 처리
```

- web.xml 수정
	- web-inf 폴더 내 web.xml로 매핑   


```java
  	<servlet>
		<servlet-name>listServ</servlet-name>
		<servlet-class>com.mystudy.pd.serv.ListServlet</servlet-class>
	</servlet>
	
	<servlet-mapping>
		<servlet-name>listServ</servlet-name>
		<url-pattern>/ListServ</url-pattern>
	</servlet-mapping>
```

***

## 6. 서블릿 예제
### registerPd.html

```html
<HTML>
<HEAD>
<TITLE> registerPd.html </TITLE> 
</HEAD>
<BODY>
	<form name="frm" method="POST" action="/mystudy/InsertServ">
		상품명: <INPUT TYPE="text" NAME="pdName"><br>
		가격:<INPUT TYPE="text" NAME="price"><br>
		<INPUT TYPE="submit" value="등록">
		<INPUT TYPE="reset" value="취소">
	</form>
</BODY>
</HTML>
```

### insertServ.java 서블릿
```java
package com.mystudy.pd.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.pd.model.PdDAO;
import com.mystudy.pd.model.PdDTO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/InsertServ")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답 문서에 대한 타입 지정
		response.setContentType("text/html;charset=euc-kr");
		
		//출력스트림 업어도기
		PrintWriter out = response.getWriter();
		
		//1 요청파라미터
		request.setCharacterEncoding("euc-kr");
		
		String pdName = request.getParameter("pdName");
		String price = request.getParameter("price");
		
		//2 db작업
		PdDAO dao = new PdDAO();
		PdDTO dto = new PdDTO();
		
		dto.setPrice(Integer.parseInt(price));
		dto.setPdName(pdName);
		
		try {
			int cnt = dao.insertPd(dto);
			
			//3 결과처리
			if(cnt>0) {
				response.sendRedirect("/mystudy/ListServ");
			}else {
				System.out.println("등록 실패");
				out.println("등록 실패");
				response.sendRedirect("/mystudy/servTest/registerPd.html");
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		out.close();
	}
}

```

### ListServ.java
```java
package com.mystudy.pd.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mystudy.pd.model.PdDAO;
import com.mystudy.pd.model.PdDTO;

//@WebServlet("/ListServ")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 응답문서에 대한 콘텐츠 타입 지정
		response.setContentType("text/html;charset=euc-kr");

		// 출력스트림 얻어오기
		PrintWriter out = response.getWriter();
		
		//1
		
		//2
		PdDAO dao = new PdDAO();
		PdDTO dto = new PdDTO();
		List<PdDTO> list = null;
		DecimalFormat df = new DecimalFormat("#,###");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			list = dao.selectAll();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		//3
		
		out.print("<html><head><title>상품 목록</title></head>");
		out.print("<body>");
		out.print("<table border='1'>");
		out.print("<tr><th>번호</th><th>상품명</th><th>가격</th><th>등록일</th></tr>");
		for(int i=0; i<list.size(); i++) {
			dto = list.get(i);
			
			out.print("<tr>");
			out.print("<td>"+dto.getNo()+"</td>");
			out.print("<td><a href='/mystudy/DetailServ?no="+dto.getNo()+"'>"+dto.getPdName()+"</td>");
			out.print("<td>"+df.format(dto.getPrice())+"</td>");
			out.print("<td>"+sdf.format(dto.getRegdate())+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<a href='/mystudy/servTest/registerPd.html'>상품 등록</a>");
		out.print("</body></html>");
		out.close();
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

```

