---
layout: post
title:  "15. DBCP"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

## 1. DBCP를 이용한 커넥션 풀 
- 자카르타 프로젝트의 DBCP api를 사용
  - 관련 jar 파일 설치
    - 톰캣설치폴더/lib 에 포함되어 있는 jar
  - server.xml 수정   


```html
<!-- Tomcat DBCP -->
<Context >
  <Resource name="jdbc/oracledb"
    auth="Container" driverClassName="oracle.jdbc.driver.OracleDriver"
    type="javax.sql.DataSource"
    url="jdbc:oracle:thin:@192.168.1.101:1521:orcl"
    username="herb" password="herb123" 
    maxActive="20" maxWait="-1" />
</Context>
```
- jsp페이지에서 커넥션 풀 사용   

```java
Connection con=null;

/*
JNDI(java Naming Directory Interface)
- XML과 같은 외부 자원을 통해 객체의 레퍼런스를 얻어오는 기법
이미 올라온 객체를 이름을 통해 검색해서 찾아내는 것
lookup()이라는 메소드를 이용
*/

try {
  Context ctx = new InitialContext();
  Context envCtx = (Context)ctx.lookup("java:/comp/env");
  DataSource ds = (DataSource)envCtx.lookup("jdbc/oracledb");
  //DataSource ds = (DataSource)ctx.lookup("java:/comp/env/jdbc/oracledb");
  //톰캣이 구현한 커넥션풀 객체
  
  /*was에 따라 DataSource 등록하는 방법이 다르며, 가져오는 방법도 다르다
  java:comp/env는 톰캣에서 사용하는 프로토콜 */
  
  con = ds.getConnection();
  System.out.println("DataSource LookUp 성공!");
} catch (NamingException e) {
  e.printStackTrace();
} catch (SQLException e) {
  e.printStackTrace();
}
```

- initialContext 객체 생성
  - lookup()메서드를 이용해 찾는다
- 찾아낸 context객체로 datasource 객체 리턴
- 어떤 객체든지 찾을 수 있도록 해야하기 때문에 할당된 객체들은 object 타입으로 감싸져있다
  - 사용시 원하는 타입으로 형변환 해야 한다
- DataSource 객체의 getConnection() 메서드 사용   

```java
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border = "1">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>이름</th>
	</tr>

<%
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	try{
		Context ctx = new InitialContext();
		Context envCtx = (Context)ctx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracledb");
		
		con=ds.getConnection();
		
		String sql = "select * from reboard";
		ps = con.prepareStatement(sql);
		
		rs=ps.executeQuery();
		while(rs.next()){
			int no = rs.getInt("no");
			String title=rs.getString("title");
			String name = rs.getString("name");%>
			<tr>
				<td><%=no %></td>
				<td><%=title %></td>
				<td><%=name %></td>
			</tr>
		<%}
	}catch(NamingException e){
		e.printStackTrace();
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}

%>
</table>

</body>
</html>
```
