---
layout: post
title:  "6. jsp와 jdbc"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

### pdWrite.jsp
```html
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>pdWrite.jsp</title>
</head>
<body>
	<h1>상품 등록</h1>
	<form method="post" action="pdWrite_ok.jsp">
		상품명 : <input type = "text" name ="pdName"><br>
		가격 : <input type = "text" name ="price"><br><br>
		<input type = "submit" value = "등록">
		<input type = "reset" value = "취소">
	</form>
	<br>
	<a href="pdList.jsp">상품 목록</a>
</body>
</html>
```

### pdWrite_ok.jsp
```html
<%@page import="java.sql.*"%>
<%@page import="com.mystudy.pd.model.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>pdWrite_ok.jsp</title>
</head>
<body>
	<%
		//pdWrite.jsp에서 post 방식으로 submit
		
		request.setCharacterEncoding("euc-kr");
		//1.요청 파라미터 읽어오기
		String pdName = request.getParameter("pdName");
		String price = request.getParameter("price");
		
		//2.DB작업
		PdDAO dao = new PdDAO();
		PdDTO dto = new PdDTO();
		dto.setPdName(pdName);
		dto.setPrice(Integer.parseInt(price));
		
		try{
			int cnt = dao.insertPd(dto);
			
			if(cnt>0){
				response.sendRedirect("pdList.jsp");
			}else{
				System.out.println("상품등록 실패");
				response.sendRedirect("pdWrite.jsp");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		//3.결과처리
		
	%>

</body>
</html>
```

### pdList
```html
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.mystudy.pd.model.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%
	//1 pdWrite.jsp에서 상품몰곡 클릭 시 get 방식으로 이동
	//2 pdWrㅑte_ok.jsp에서 등록성공 시 get방식으로 이용
	
	//1
	//2
	PdDAO dao = new PdDAO();
	List<PdDTO> list = null;
	try{
		list = dao.selectAll();
	}catch(SQLException e){
		e.printStackTrace();
	}
	//3
	DecimalFormat df = new DecimalFormat("#,###");
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>pdList.jsp</title>
</head>
<body>
	<h1>글 목록</h1>
	<table border="1" style="width:500px">
		<tr>
			<th>번호</th>
			<th>상품명</th>
			<th>가격</th>
			<th>등록일</th>
		</tr>
		<!-- 반복 시작 -->
		<%
			for(int i=0;i<list.size();i++) {
				PdDTO dto =list.get(i);%>
				<tr>
					<td><%=dto.getNo() %></td>
					<td><a href="pdDetail.jsp?no=<%=dto.getNo() %>">
					<%=dto.getPdName() %>
					</a></td>
					<td style="text-align: right"><%=df.format(dto.getPrice()) %></td>
					<td><%=sdf.format(dto.getRegdate()) %></td>
				</tr>
		<%}%>
		<!-- 반복 끝 -->
		
	
	</table>

	<br>
	<a href="pdWrite.jsp">상품 등록</a>
</body>
</html>
```

### pdDetail.jsp
```html
<%@page import="java.sql.SQLException"%>
<%@page import="com.mystudy.pd.model.PdDTO"%>
<%@page import="com.mystudy.pd.model.PdDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	//pdList.jsp에서 상품명 클릭시 get 방식으로 이동
	//http://localhost:9090/mystudy/pd/pdDetail.jsp?no=a
	
	//1
	String no = request.getParameter("no");

	//2
	PdDAO dao = new PdDAO();
	PdDTO dto = null;
	
	try{
		dto=dao.selectByNo(Integer.parseInt(no));
	}catch(SQLException e){
		e.printStackTrace();
	}
	//3
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('#del').click(function(){
			if(confirm('삭제하시겠습니까?')){
				location.href="pdDelete.jsp?no=<%=no%>";
			}
		});
	});
</script>
</head>
<body>

	<h1>상품 상세보기</h1>
	<p>번호 : <%=dto.getNo() %></p>
	<p>상품명 : <%=dto.getPdName() %></p>
	<p>가격 : <%=dto.getPrice() %></p>
	<p>등록일 : <%=dto.getRegdate() %></p>
	<br>
	<a href="pdList.jsp">목록</a>
	<a href="pdEdit.jsp?no=<%=no%>" id="edit">수정</a>
	<a href="#" id="del">삭제</a>
</body>
</html>
```

### pdDelete.jsp
```html
<%@page import="java.sql.SQLException"%>
<%@page import="com.mystudy.pd.model.PdDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

	//pdDetail.jsp에서 삭제 클릭 get방식 이동
	
	String no = request.getParameter("no");

	PdDAO dao = new PdDAO();
	
	try{
		int cnt = dao.deletePd(Integer.parseInt(no));
		
		if(cnt>0){
			response.sendRedirect("pdList.jsp");
		}else{
			System.out.println("삭제 실패");
			response.sendRedirect("pdDetail.jsp?no="+no);
		}
		
	}catch(SQLException e){
		e.printStackTrace();
	}
%>

</body>
</html>
```

### pdEdit.jsp
```html
<%@page import="java.sql.SQLException"%>
<%@page import="com.mystudy.pd.model.PdDTO"%>
<%@page import="com.mystudy.pd.model.PdDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>pdWrite.jsp</title>
</head>
<body>
<%
	//pdDetail.jsp 수정 클릭시 get 방식으로 이동
	//1 요청파라미터 읽어오기
	String no = request.getParameter("no");
	System.out.println(no);
	//2 db작업
	PdDAO dao = new PdDAO();
	PdDTO dto = null;
	try{
		dto=dao.selectByNo(Integer.parseInt(no));
		
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	//3 결과처리

%>


	<h1>상품 수정</h1>
	<form method="post" action="pdEdit_ok.jsp">
		<!--  수정 처리시 필요한 no를 hidden field 필드에 넣어준다 -->
		<input type="hidden" name="no" value="<%=no %>">
		상품명 : <input type = "text" name ="pdName" value="<%=dto.getPdName()%>"><br>
		가격 : <input type = "text" name ="price" value="<%=dto.getPrice()%>"><br><br>
		<input type = "submit" value = "수정">
		<input type = "reset" value = "취소">
	</form>
	<br>
	<a href="pdList.jsp">상품 목록</a>
</body>
</html>
```

### pdEdit_ok.jsp
```html
<%@page import="java.sql.SQLException"%>
<%@page import="com.mystudy.pd.model.PdDAO"%>
<%@page import="com.mystudy.pd.model.PdDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	//pdEdit.jsp에서 post 방식으로 서브밋
	request.setCharacterEncoding("euc-kr");

	//1. 파라미터
	String no = request.getParameter("no");
	String pdName = request.getParameter("pdName");
	String price = request.getParameter("price");
	//2. db
	PdDTO dto = new PdDTO();
	dto.setNo(Integer.parseInt(no));
	dto.setPdName(pdName);
	dto.setPrice(Integer.parseInt(price));
	
	PdDAO dao = new PdDAO();
	
	try{
		int cnt = dao.updatePd(dto);
		
		if(cnt>0){
			response.sendRedirect("pdDetail.jsp?no="+no);
		}else{
			System.out.println("실패");
			response.sendRedirect("pdEdit.jsp?no="+no);
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	//3. 

%>

</body>
</html>
```


### ConnectionPoolMgr.java
```java
package com.mystudy.pd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPoolMgr {
	public ConnectionPoolMgr() {
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
		
	}
	public Connection getConnection(String url, String uid, String upwd) throws SQLException {
		Connection con = DriverManager.getConnection(url, uid, upwd);
		System.out.println("db연결 : con = "+con);
		
		return con;
	}
	public Connection getConnection(String uid, String upwd) throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = DriverManager.getConnection(url, uid, upwd);
		System.out.println("db연결 : con = "+con);
		
		return con;
	}
	public Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uid = "javauser";
		String upwd = "javauser123";
		Connection con = DriverManager.getConnection(url, uid, upwd);
		System.out.println("db연결 : con = "+con);
		
		return con;
	}
	public void dbClose(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}
	public void dbClose(PreparedStatement ps, Connection con) throws SQLException {
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}
}

```

### PdDTO
```java
package com.mystudy.pd.model;

import java.sql.Timestamp;

public class PdDTO {
   //1. private 멤버변수
   private int no;
   private String pdName; //마우스
   private int price; //19000
   private Timestamp regdate;

   //2. 생성자
   public PdDTO() {
      super();
   }

   public PdDTO(int no, String pdName, int price, Timestamp regdate) {
      super();
      this.no = no;
      this.pdName = pdName;
      this.price = price;
      this.regdate = regdate;
   }

   //3. getter/setter
   public int getNo() {
      return no;
   }

   public void setNo(int no) {
      this.no = no;
   }

   public String getPdName() {
      return pdName;
   }

   public void setPdName(String pdName) {
      this.pdName = pdName;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public Timestamp getRegdate() {
      return regdate;
   }

   public void setRegdate(Timestamp regdate) {
      this.regdate = regdate;
   }

   //4. toString()
   @Override
   public String toString() {
      return "PdDTO [no=" + no + ", pdName=" + pdName + ", price=" + price + ", regdate=" + regdate + "]";
   }

}

```

### PdDAO
```html
package com.mystudy.pd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PdDAO {
	private ConnectionPoolMgr pool;
	
	public PdDAO() {
		pool = new ConnectionPoolMgr();
	}
	public int insertPd(PdDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int cnt = 0;
		try {
			con = pool.getConnection();
			
			String sql = "insert into pd values(pd_seq.nextval, ?, ?, sysdate,null)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPdName());
			ps.setInt(2, dto.getPrice());
			cnt = ps.executeUpdate();
			System.out.println("dto = "+dto+", cnt = "+cnt);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
		
	}
	public List<PdDTO> selectAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PdDTO> list = new ArrayList<PdDTO>();
		try{
			con = pool.getConnection();
			
			String sql = "select * from pd order by no desc";
			ps= con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int no = rs.getInt("no");
				int price = rs.getInt("price");
				String pdName = rs.getString("pdName");
				Timestamp regdate=rs.getTimestamp("regdate");
				PdDTO dto = new PdDTO(no,pdName,price,regdate);
				list.add(dto);
			}
			return list;
			
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	public PdDTO selectByNo(int no) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PdDTO dto = new PdDTO();;
		
		try {
			con=pool.getConnection();
			
			String sql = "select * from pd where no = ?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int price = rs.getInt("price");
				String pdName = rs.getString("pdName");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				dto.setNo(no);
				dto.setPdName(pdName);
				dto.setPrice(price);
				dto.setRegdate(regdate);
			}
			System.out.println("dto = "+dto+", 매개변수 no = "+no);
			return dto;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}
	public int deletePd(int no) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "delete from pd where no=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, no);
			
			int cnt = ps.executeUpdate();
			System.out.println("cnt = "+cnt);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	public int updatePd(PdDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = pool.getConnection();
			
			String sql = "update pd set pdname=? , price=? where no = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPdName());
			ps.setInt(2, dto.getPrice());
			ps.setInt(3, dto.getNo());
			
			int cnt = ps.executeUpdate();
			System.out.println("cnt = "+cnt);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
}


```
