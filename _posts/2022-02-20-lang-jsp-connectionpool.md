---
layout: post
title:  "9. 커넥션풀, 싱글톤패턴"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

## 1.  커넥션 풀 개요
- 데이터베이스에 연결하기 위한 커넥션 객체는 새로 만들어질 때 많은 시스템 자원이 요구
  - 메모리에 객체를 할당할 자리를 만드는 작업
  - 객체가 사용할 자원들을 초기화하는 작업
  - 객체가 필요없어지면 거두어들이는 작업
  - 매번 새로운 데이터베이스 연결 요청이 들어올때마다 해당 작업을 수행해야된다면 많은 부담
- 커넥션 객체 생성 관리 방법
  - service method (doGet, doPost)에서 커넥션 객체 생성
    - 데이터베이스와 연동하기 위해 사용한 방법
    - 커넥션 객체의 레퍼런스 변수가 지역변수에 할당
      - 요청당 한개씩 커넥션 객체 생성, 시스템 부하
      - 메모리 낭비
      - 커넥션 시간이 요청시간에 포함
  - init method에서 커넥션 객체 생성
    - 커넥션 객체의 레퍼런스 변수는 전역변수에 할당
    - 커넥션 시간이 걸리지 않는다
    - 하나의 커넥션을 쓰기때문에 쿼리가 쌓이게 되어 응답시간 증가
  - **커넥션 풀에서 객체 생성**
    - 자원을 빌려쓰고 회수하는 방법 사용
    - 미리 여분의 커넥션을 만들어놓는다
    - 사용자의 요청이 있을 때 미리 만들어져있는 커넥션 부여
    - 사용된 커넥션 객체는 다시 풀로 회수
    - 커넥션 생성 개수 결정 가능


### 커넥션 풀
- 끊임없이 생성되는 커넥션의 문제를 해결하기 위한 목적
- 반드시 컨테이너에 1개만 만들어지도록 패턴을 만들어야 한다
- service()메서드당 1개씩은 가지고 쓰게 한다
- 커넥션 개수를 제한한다
- 커넥션 객체 관리자가 다쓰면 자원을 회수한다   

```java
package com.herbmall.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class ConnectionPoolMgr1 {
	private String url,user,pwd;
	private HashMap<Connection, Boolean> hmap;
	private int increment; //증가치
	
	//생성자
	public ConnectionPoolMgr1(){
		increment=5;//5만큼씩 증가
		hmap=new HashMap<Connection,Boolean>(10);	
		Connection con=null;	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
			url="jdbc:oracle:thin:@aa:1521:xe";	
			user="herb"; 	
			pwd="herb123";
			
			//커넥션 객체를 미리 생성해 놓기 - 10개
			for(int i=0;i<10;i++){	
				con=DriverManager.getConnection(url,user,pwd);		
				//해시맵의 key에 커넥션 저장
				
				hmap.put(con, Boolean.FALSE);		
				//해시맵의 value에 true, false 저장, false - 쉬는 커넥션이라는 표시	
			}//for
			
			System.out.println("ConnectionPool 생성!");
		}catch (ClassNotFoundException e) {			
			e.printStackTrace();
			System.out.println("Class Not Found!"); 
		}catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("sql 예외발생!"); 
		}
	}//생성자
		
	public synchronized Connection getConnection() //jsp - 요청시 Thread로 처리
			throws SQLException{
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){ //hmap에 key가 있는 동안 반복	
			con=iterKeys.next();//key값	
			Boolean b=hmap.get(con);//value값	
			//만약 쉬고있는 컨넥션이라면 일하는 컨넥션으로 표시해주고 반환한다.	
			if(b==Boolean.FALSE){	
				hmap.put(con, Boolean.TRUE);//일한다고 표시		
				return con; //일하러 나감	
			}//if	
		}//while
		
		//쉬고 있는 컨넥션이 없으면 일할 Connection을 5개 증가시킨다	
		for(int i=0;i<increment;i++){	
			Connection con2=DriverManager.getConnection(url,user,pwd);	
			hmap.put(con2, Boolean.FALSE);	
		}//for
		
		return getConnection();//재귀호출
	}
	
	//커넥션을 사용하고 난 후 다시 되돌려주는 메소드	
	public void returnConnection(Connection returnCon){
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){	
			con=iterKeys.next();		
			if(con==returnCon){	//con의 주소값이 일치하면
				hmap.put(con, Boolean.FALSE);  //쉬는 커넥션으로 표시	
				break;
			}//if
		}//while
		
		try{	
			removeConnection(); //쉬고있는 커넥션 10개를 유지해주는 메소드	
		}catch(SQLException e){	
			e.printStackTrace();	
			System.out.println("sqlerror:" + e.getMessage());
		}
	}
	
	//Connection 10개만 유지해주는 메서드
	public void removeConnection() throws SQLException{
		Connection con=null;
		Iterator<Connection> iterKeys=hmap.keySet().iterator();
		int count=0;//false인 커넥션 개수
		while(iterKeys.hasNext() ){ 	
			con=iterKeys.next();	
			Boolean b=hmap.get(con);
			boolean b_pre=b.booleanValue();
			if(!b_pre){//쉬고있는 커넥션 개수 세기 - false인 경우
				count++;
				if(count>10){ //쉬고 있는 커넥션이 10개가 넘어가면
					//해시맵에서 삭제
					hmap.remove(con);
					con.close();
				}
			}//if
		}//while
	}
	
	//모든 커넥션 close하는 메서드
	public void closeAll() throws SQLException{
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){ 	
			con=iterKeys.next();	
			con.close();
		}//while
	}
	
	
	//자원해제하는 메서드
	public void dbClose(PreparedStatement ps,  Connection con) throws SQLException{
		if(ps!=null) ps.close();
		if(con!=null)returnConnection(con);
	}
	
	public void dbClose(ResultSet rs,  PreparedStatement ps,  
			Connection con) throws SQLException{
		if(rs!=null)rs.close();
		if(ps!=null) ps.close();
		if(con!=null)returnConnection(con);				
	}
	
	public void dbClose(CallableStatement cs,  Connection con) throws SQLException{
		if(cs!=null) cs.close();
		if(con!=null)returnConnection(con);			
	}
}//class

```

***

## 2. 싱글톤 패턴
- 객체 지향 개념에 따른 설계 중 재사용할 경우 유용한 설계들을 디자인 패턴으로 정립
- 디자인 패턴
  - 여러 문제에 대한 설계 사례를 분석
  - 서로 비슷한 문제를 해결하기 위한 설계들을 분류
  - 각 문제 유형별로 가장 적합한 설계를 일반화
  - 패턴으로 정립
- 싱글톤 패턴
  - 인스턴스를 하나만 생성해서 사용하는 패턴
  - 인스턴스의 개수를 하나로 제한하는 패턴   

```java
public class Singleton{
  
  private static Singleton instance;
  
  private Singleton(){} //private 생성자
  
  public static Singleton getInstance(){
    if(instance==null){
    instance = new Singleton();
    }
    return instance;
  }
}
```

- 객체의 무분별한 생성을 막기위해 생성자를 private
- 메서드를 이용해 인스턴스 생성
  - 인스턴스가 없을 경우에만 새로운 인스턴스 생성
    - 인스턴스 변수에 저장
  - 인스턴스가 있을 경우 이미 만들어진 인스턴스 리턴   

### 싱글톤 패턴 예
#### person.java
```java
package com.herbmall.test;

/* 싱글톤 패턴(songleton)
 * 인스턴스를 하나만 생성해서 사용하는 패턴*/
public class Person {
	
	//static 변수-클래스 차원에서 하나만 생성되어 모든 객체가 공유한다
	private static Person instance;
	
	private Person() { //private 생성자
		
	}
	
	public static Person getInstance() {
		if(instance==null) {
			instance = new Person();
		}
		return instance;
	}
	
	public void showInfo() {
		System.out.println("person 메서드");
	}

}

```

#### singleTonTest.jsp
```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.herbmall.test.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	Person p = Person.getInstance();
	out.print(p);
	
	p.showInfo();
%>

</body>
</html>
```

***

## 3. 커넥션풀의 싱글톤패턴 적용
#### 커넥션풀
```java
package com.herbmall.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class ConnectionPoolMgr1 {
	private String url,user,pwd;
	private HashMap<Connection, Boolean> hmap;
	private int increment; //증가치
	
	private static ConnectionPoolMgr1 instance;
	
	//생성자
	public ConnectionPoolMgr1(){
		increment=5;//5만큼씩 증가
		hmap=new HashMap<Connection,Boolean>(10);	
		Connection con=null;	
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공!");
			url="jdbc:oracle:thin:@localhost:1521:xe";	
			user="herb"; 	
			pwd="herb123";
			
			//커넥션 객체를 미리 생성해 놓기 - 10개
			for(int i=0;i<10;i++){	
				con=DriverManager.getConnection(url,user,pwd);		
				//해시맵의 key에 커넥션 저장
				
				hmap.put(con, Boolean.FALSE);		
				//해시맵의 value에 true, false 저장, false - 쉬는 커넥션이라는 표시	
			}//for
			
			System.out.println("ConnectionPool 생성!");
		}catch (ClassNotFoundException e) {			
			e.printStackTrace();
			System.out.println("Class Not Found!"); 
		}catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("sql 예외발생!"); 
		}
	}//생성자
	
	//static 메거스
	public static ConnectionPoolMgr1 getInstance() {
		if(instance==null) {
			instance=new ConnectionPoolMgr1();
		}
		return instance;
	}
		
	public synchronized Connection getConnection() //jsp - 요청시 Thread로 처리
			throws SQLException{
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){ //hmap에 key가 있는 동안 반복	
			con=iterKeys.next();//key값	
			Boolean b=hmap.get(con);//value값	
			//만약 쉬고있는 컨넥션이라면 일하는 컨넥션으로 표시해주고 반환한다.	
			if(b==Boolean.FALSE){	
				hmap.put(con, Boolean.TRUE);//일한다고 표시		
				return con; //일하러 나감	
			}//if	
		}//while
		
		//쉬고 있는 컨넥션이 없으면 일할 Connection을 5개 증가시킨다	
		for(int i=0;i<increment;i++){	
			Connection con2=DriverManager.getConnection(url,user,pwd);	
			hmap.put(con2, Boolean.FALSE);	
		}//for
		
		return getConnection();//재귀호출
	}
	
	//커넥션을 사용하고 난 후 다시 되돌려주는 메소드	
	public void returnConnection(Connection returnCon){
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){	
			con=iterKeys.next();		
			if(con==returnCon){	//con의 주소값이 일치하면
				hmap.put(con, Boolean.FALSE);  //쉬는 커넥션으로 표시	
				break;
			}//if
		}//while
		
		try{	
			removeConnection(); //쉬고있는 커넥션 10개를 유지해주는 메소드	
		}catch(SQLException e){	
			e.printStackTrace();	
			System.out.println("sqlerror:" + e.getMessage());
		}
	}
	
	//Connection 10개만 유지해주는 메서드
	public void removeConnection() throws SQLException{
		Connection con=null;
		Iterator<Connection> iterKeys=hmap.keySet().iterator();
		int count=0;//false인 커넥션 개수
		while(iterKeys.hasNext() ){ 	
			con=iterKeys.next();	
			Boolean b=hmap.get(con);
			boolean b_pre=b.booleanValue();
			if(!b_pre){//쉬고있는 커넥션 개수 세기 - false인 경우
				count++;
				if(count>10){ //쉬고 있는 커넥션이 10개가 넘어가면
					//해시맵에서 삭제
					hmap.remove(con);
					con.close();
				}
			}//if
		}//while
	}
	
	//모든 커넥션 close하는 메서드
	public void closeAll() throws SQLException{
		Iterator<Connection> iterKeys=hmap.keySet().iterator();	
		Connection con=null;	
		while(iterKeys.hasNext() ){ 	
			con=iterKeys.next();	
			con.close();
		}//while
	}
	
	
	//자원해제하는 메서드
	public void dbClose(PreparedStatement ps,  Connection con) throws SQLException{
		if(ps!=null) ps.close();
		if(con!=null)returnConnection(con);
	}
	
	public void dbClose(ResultSet rs,  PreparedStatement ps,  
			Connection con) throws SQLException{
		if(rs!=null)rs.close();
		if(ps!=null) ps.close();
		if(con!=null)returnConnection(con);				
	}
	
	public void dbClose(CallableStatement cs,  Connection con) throws SQLException{
		if(cs!=null) cs.close();
		if(con!=null)returnConnection(con);			
	}
}//class

```

#### DAO
```java
package com.herbmall.reboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.herbmall.db.ConnectionPoolMgr;

public class ReBoardDAO {
	private ConnectionPoolMgr pool;

	public ReBoardDAO() {
		pool=new ConnectionPoolMgr();
	}

	public int insertReBoard(ReBoardVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			//1,2
			con=pool.getConnection();

			//3
			String sql="insert into reBoard(no, name, pwd, title, email, content)"
					+ "values(reBoard_seq.nextval, ?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getTitle());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getContent());

			//4
			int cnt=ps.executeUpdate();
			System.out.println("글등록 결과 cnt="+cnt+", 매개변수 vo="+vo);

			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}

	public List<ReBoardVO> selectAll(String condition, String keyword) 
				throws SQLException {
		/*
		select * from reBoard
		where title like '%안%';

		select * from reBoard
		where name like '%길%';

		select * from reBoard
		where content like '%k%';
		*/
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		List<ReBoardVO> list=new ArrayList<ReBoardVO>();
		try {
			//1,2
			con=pool.getConnection();

			//3
			String sql="select * from reBoard";
			if(keyword!=null && !keyword.isEmpty()) {
				sql+=" where "+condition+" like '%' || ? || '%'";
			}
			sql+=" order by no desc";
			ps=con.prepareStatement(sql);
				
			if(keyword!=null && !keyword.isEmpty()) {
				ps.setString(1, keyword);
			}
			
			//4
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				int readcount=rs.getInt("readcount");
				String name=rs.getString("name");
				String pwd=rs.getString("pwd");
				String title=rs.getString("title");
				String email=rs.getString("email");
				String content=rs.getString("content");
				Timestamp regdate=rs.getTimestamp("regdate");

				ReBoardVO vo = new ReBoardVO(no, name, pwd, title, 
						email, regdate, readcount, content);
				list.add(vo);
			}

			System.out.println("전체 조회 결과 list.size="+list.size()
				+", 매개변수 condition="+condition+", keyword="
				+ keyword);
			
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}

	public ReBoardVO selectByNo(int no) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		ReBoardVO vo = new ReBoardVO();
		try {
			//1,2
			con=pool.getConnection();

			//3
			String sql="select * from reBoard where no=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);

			//4
			rs=ps.executeQuery();
			if(rs.next()) {
				vo.setNo(no);
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPwd(rs.getString("pwd"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setReadcount(rs.getInt("readcount"));				
			}

			System.out.println("글 상세보기 결과 vo="+vo+", 매개변수 no="+no);

			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}

	public int updateReBoard(ReBoardVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			//1,2
			con=pool.getConnection();

			//3
			String sql="update reBoard"
					+ " set name=?, title=?, email=?, content=?"
					+ " where no=? and pwd=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getEmail());
			ps.setString(4, vo.getContent());
			ps.setInt(5, vo.getNo());
			ps.setString(6, vo.getPwd());

			//4
			int cnt=ps.executeUpdate();
			System.out.println("글수정 결과 cnt="+cnt+", 매개변수 vo="+vo);

			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}

	public int deleteReBoard(int no, String pwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			con=pool.getConnection();

			String sql="delete reBoard where no=? and pwd=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, pwd);

			int cnt=ps.executeUpdate();
			System.out.println("글 삭제 결과 cnt="+cnt+", 매개변수 no="+
					no+", pwd="+pwd);

			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	public int updateCount(int no) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			con=pool.getConnection();

			String sql="update reBoard set readcount=readcount+1"
		               + " where no=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);

			int cnt=ps.executeUpdate();
			System.out.println("조회수 증가 결과 cnt="+cnt+", 매개변수 no="+no);

			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
}

```

- 커넥션 객체 대여
	- ConnectionPoolMgr pool = ConnectionPoolMgr.getInstance();
	- Connection con = pool.getConnection();
- 커넥션 객체 반납
	- 
