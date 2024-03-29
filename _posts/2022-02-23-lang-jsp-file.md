---
layout: post
title:  "12. 파일업로드"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

## 1. 폼 형태
- 웹 브라우저를 통해 파일을 전송하기 위한 폼 구성
  - input type = file
  - method = post
  - enctype = multipart/form-data
    - 파일 이름과 함께 데이터 전송
    - 기본값 application/x-www-form-urlencoded
      - 파일 이름만 전송
  - 업로드 컴포넌트 : cos.jar
- post로 전송된 파라미터는 request 객체를 통해 이름에 해당하는 값을 얻어낼 수 있다
  - multipart/form-data로 지정한 폼은 request 객체로 얻어낸 파라미터의 이름으로 값을 얻어낼 수 없다
  - 이름과 값을 가져오고, input type = file로 지정된 파일을 업로드 하기 위해서는 특별 컴포넌트가 필요
    - www.servlets.com에서 제공하는 cos.jar 파일에서 필요 컴포넌트를 선택하여 업로드 수행   

***

## 2. MultipartRequest 클래스
- 생성자   

```java
 MultipartRequest(javax.servlet.http.HttpServletRequest request, 
    java.lang.String saveDirectory, 
    int maxPostSize, 
    java.lang.String encoding, 
    FileRenamePolicy policy)
    
MultipartRequest mr = new MultipartRequest(request, //request 객체
    fileDirectory, // 파일이 저장될 경로
    1024*5, // 파일 최대 크기 (1024*5 = 5kb)
    “utf-8”, // 인코딩 타입
    new DefaultFileRenamePolicy()); //한글 인코딩이나 업로드 파일이 중복 시, 파일명 변경, 덮어쓰기 방지

```   

#### 메서드
![캡처](https://user-images.githubusercontent.com/99188096/167757940-bd704e4f-63f9-4a82-875d-629f3b067008.PNG)   
![캡처2](https://user-images.githubusercontent.com/99188096/167757945-4463ad1f-9f61-4c82-b021-a96693422e52.PNG)   


***

## 3. 예제
#### uploadTest1.jsp
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="frm1" method="post" action="upload_ok.jsp" enctype="multipart/form-data" >
		아이디 : <input type="text" name="id"><br>
		파일 업로드 : <input type="file" name="fileName" size="30"><br>
		<input type = "submit" value="등록"><br>
	</form>
</body>
</html>
```

#### upload_ok.jsp
```
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
		//MultipartRequest 객체 생성 성공 -> 업로드 완료
		//실패 -> IOException 발생(용량 초과 등)
		
		String upDir="pos_upload";//업로드할 폴더
		
		String saveDir=application.getRealPath(upDir); //업로드할 폴더 절대경로 구하기
		System.out.println("saveDir = "+saveDir);
		
		saveDir = config.getServletContext().getRealPath(upDir);
		System.out.println("saveDir = "+saveDir);
		
		saveDir = "C:\\lecture\\workspace.list\\jsp_ws\\herbmall\\src\\main\\webapp\\pds_upload";
		
		int maxSize = 2*1024*1024; // 업로드 최대 용량, 2M
		
		String encoding="utf-8"; // 인코딩 형식
		
		//업로드 시 동일한 파일명이 있을 경우 나중에 업로드한 파일에 번호를 붙여 이름 변경
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding, policy);
		
		out.print("업로드 완료");
		
		//업로드된 파일 정보 얻어오기
		String fileName = mr.getFilesystemName("fileName"); //변경된 파일명
		String originFileName = mr.getOriginalFileName("fileName"); //원본 파일명
		
		File file = mr.getFile("fileName");
		long fileSize = file.length();
		String id = request.getParameter("id");
		String id2 = mr.getParameter("id");
		
	%>
	<h1> 업로드된 파일 정보</h1>
	<p> 변경 후 파일 명 : <%=fileName %></p>
	<p> 변경 전 파일 명 : <%=originFileName %></p>
	<p> 파일 크기 : <%=fileSize %></p>
	
	<h1> 파라미터</h1>
	<p>id (request) : <%=id %></p>
	<p>id2(mr) : <%=id2 %></p>
</body>
</html>
```

***

## 4. 예제2 다중 업로드
#### uploadTest2.java   
```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>파일 업로드 테스트2</h1>
	<form name="frm1" method="post" action="uploadTest2_ok.jsp" 
		encType="multipart/form-data">
		
		아이디 <input type="text" name="id"/><br>
		주소 <input type="text" name="address"/><br>
		파일첨부<br> 
		<input type="file" name="upfile1" /><br>
		<input type="file" name="upfile2" /><br>
		<input type="file" name="upfile3" /><br><br>
		<input type="submit" value="전송" />
	</form>
</body>
</html>
```

#### uploadTest2_ok.jsp
```java
<%@page import="java.io.IOException"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
		//MultipartRequest 객체 생성 성공 -> 업로드 완료
		//실패 -> IOException 발생(용량 초과 등)
		
		String upDir="pos_upload";//업로드할 폴더
		
		String saveDir=application.getRealPath(upDir); //업로드할 폴더 절대경로 구하기
		System.out.println("saveDir = "+saveDir);
		
		saveDir = config.getServletContext().getRealPath(upDir);
		System.out.println("saveDir = "+saveDir);
		
		saveDir = "C:\\lecture\\workspace.list\\jsp_ws\\herbmall\\src\\main\\webapp\\pds_upload";
		
		int maxSize = 2*1024*1024; // 업로드 최대 용량, 2M
		
		String encoding="utf-8"; // 인코딩 형식
		
		//업로드 시 동일한 파일명이 있을 경우 나중에 업로드한 파일에 번호를 붙여 이름 변경
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		try{
			
			MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding, policy);
			
			out.print("업로드 완료");
			
			Enumeration enFileNames = mr.getFileNames(); // 여러 파일의 이름 목록 리턴
			
			while(enFileNames.hasMoreElements()){
				String fName=(String)enFileNames.nextElement();
				
				String fileName = mr.getFilesystemName(fName); //변경된 파일 이름
				
				String originFileName=mr.getOriginalFileName(fName); //변경전 파일 이름
				
				File f = mr.getFile(fName);
				long fileSize = 0;
				if(f != null){
					fileSize = f.length();
				}
				
				out.print("업로드된 파일명 : "+fileName+"<br>");
				out.print("원래 파일명 : "+originFileName+"<br>");
				out.print("파일크기 : "+fileSize+"<br>");
				
				
			}
			
			String id = mr.getParameter("id");
			String addr=mr.getParameter("address");
			out.print("<h1> 파라미터 <h1>");
			out.print("id : "+id+"<br>");
			out.print("address : "+addr+"<br>");
			
		}catch(IOException e){
			out.print("2M 이상의 파일은 업로드할 수 없습니다.");
			e.printStackTrace();
		}
	%>
</body>
</html>
```

***

## 5. 예제 3, 게시판 응용   
#### 테이블 sql   
```sql
--자료실 테이블
drop table reboard  cascade constraint;
create table reboard
(
    no                number        primary key,    --번호
    name         varchar2(20)    not null,            --이름    
    pwd              varchar2(20)    not null,            --비밀번호
    title             varchar2(100)   not  null,            --제목
    email          varchar2(80)    null,            --이메일
    regdate     date        default  sysdate,    --작성일    
    readcount    number        default 0     null,        --조회수
    content         clob         null,            --내용
    groupNo            number          default 0,           --그룹번호
    step           number           default 0,          --글의 단계 
    sortNo         number           default 0,       --글의 정렬순서
    delFlag         char     default 'N',             --삭제 Flag
    fileName    varchar2(50)     null,                      --업로드파일명
    fileSize    number            default 0,                      --파일사이즈 
    downCount    number     default 0,            --다운수
    originalFileName    varchar2(150)     null               --변경전 파일명	
);

drop sequence reboard_seq;
create sequence reboard_seq
increment by 1
start with 1
nocache;

select * from reboard order by no desc;

```

#### write.jsp
```java
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.reboard.model.ReBoardDAO"%>
<%@page import="com.herbmall.reboard.model.ReBoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	//[1] 글쓰기 :  list.jsp에서 [글쓰기]클릭하면 get방식으로 이동 
	//[2] 답변화면 : detail.jsp에서 [답변]클릭하면 get방식으로 이동
	//1
	String no=request.getParameter("no");
	
	ReBoardVO vo=null;	
	boolean isReply=false;
	String url="", btLabel="";
	if(no!=null && !no.isEmpty()){
		isReply=true; //답변화면
		url="reply_ok.jsp";
		btLabel="답변";
		
		//2
		ReBoardDAO dao=new ReBoardDAO();
		try{
			vo=dao.selectByNo(Integer.parseInt(no));	
		}catch(SQLException e){
			e.printStackTrace();
		}
	}else{
		url="write_ok.jsp";
		btLabel="등록";		
	}
	
	//3
%>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />

<title>답변형게시판 
<%if(isReply){ %>
	답변하기
<%}else{ %>
	글쓰기
<%} %>
 - 허브몰</title>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btList').click(function(){
			location.href = 'list.jsp';
		});
		
		$('form[name=frmWrite]').submit(function(){
			$('.infobox').each(function(idx, item){
				if($.trim($(this).val()).length<1){
					alert($(this).prev().text() + '을(를) 입력하세요');
					$(item).focus();
					event.preventDefault();
					return false;  //each 탈출
				}
			});			
		});
		
	});
</script>

</head>
<body>
<div class="divForm">
<form name="frmWrite" method="post" action="write_ok.jsp" 
		encType="multipart/form-data" >
	<%if(isReply){ %>
		<input type="text" name="groupNo" value="<%=vo.getGroupNo()%>">
		<input type="text" name="step" value="<%=vo.getStep()%>">
		<input type="text" name="sortNo" value="<%=vo.getSortNo()%>">
	<%} %>
 <fieldset>
	<legend><%if(isReply){ %>
				답변하기
			<%}else{ %>
				글쓰기
			<%} %>
	</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" class="infobox" 
            	<%if(isReply){ %>
					value="Re : <%=vo.getTitle() %>"
				<%} %>
            />
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" class="infobox"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" class="infobox"/>
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" />
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div>
        <div>
            <label for="upfile">첨부파일</label>
            <input type="file" id="upfile" name="upfile" /><span>(최대 2M)</span>
        </div>
        <div class="center">
            <input type = "submit" value="<%=btLabel%>"/>
            <input type = "Button" value="글목록" id="btList"/>         
        </div>
    </fieldset>
</form>
</div>   
<form name="frm1" method="post" action="upload_ok.jsp" enctype="multipart/form-data" >
	아이디 : <input type="text" name="id"><br>
	파일 업로드 : <input type="file" name="fileName" size="30"><br>
	<input type = "submit" value="등록"><br>
</form>

              
</body>
</html>
```

#### write_ok.jsp   


```java
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.herbmall.common.Utility"%>
<%@page import="java.io.IOException"%>
<%@page import="com.herbmall.reboard.model.ReBoardVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.reboard.model.ReBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write_ok.jsp</title>
</head>
<body>
<%
	try{
		String upDir = Utility.UPLOAD_PATH;
		
		String saveDir = application.getRealPath(upDir);
		System.out.println("saveDir = "+saveDir);
		
		saveDir = config.getServletContext().getRealPath(upDir);
		System.out.println("saveDir = "+saveDir);
		
		saveDir = Utility.TEST_PATH;
		System.out.println("testDir = "+saveDir);
		
		int maxSize = 2*1024*1024;
		String encoding = "utf-8";
		
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding, policy);
		
		String fileName = mr.getFilesystemName("upfile");
		long fileSize=0;
		String originFileName = "";
		if(fileName!=null && !fileName.isEmpty()){
			File f = mr.getFile("upfile");
			fileSize=f.length();
			
			originFileName = (String)mr.getOriginalFileName("upfile");
		}
		//write.jsp에서 post방식으로 서브밋됨	
		//1
		
		String title=mr.getParameter("title");
		String name=mr.getParameter("name");
		String pwd=mr.getParameter("pwd");
		String email=mr.getParameter("email");
		String content=mr.getParameter("content");
		
		//ip 읽어오기
		String ip=request.getRemoteAddr();
		System.out.println("ip="+ip);
		
		ip=request.getRemoteHost();
		System.out.println("ip="+ip);
		
		//2
		ReBoardDAO dao = new ReBoardDAO();
		ReBoardVO vo = new ReBoardVO();
		vo.setContent(content);
		vo.setEmail(email);
		vo.setName(name);
		vo.setPwd(pwd);
		vo.setTitle(title);
		vo.setFileName(fileName);
		vo.setOriginalFileName(originFileName);
		vo.setFileSize(fileSize);
	
	
		int cnt=dao.insertReBoard(vo);
		
		//3
		if(cnt>0){ %>
			<script type="text/javascript">
				alert("글쓰기 성공");
				location.href="list.jsp";
			</script>
		<% }else{ %>
			<script type="text/javascript">
				alert("글쓰기 실패");
				history.back();
			</script>			
		<%}
	}catch(IOException e){
		e.printStackTrace();%>
		<script type="text/javascript">
			alert("2M 이상의 파일은 업로드 불가");
			history.back();
		</script>
	<%
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	
%>
</body>
</html>




```

## 6. 파일 다운로드

#### detail.jsp

```java
<%@page import="com.herbmall.common.Utility"%>
<%@page import="com.herbmall.reboard.model.ReBoardVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.reboard.model.ReBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	//list.jsp에서 get방식으로 이동
	//=> http://192.168.0.100:9090/herbmall/reBoard/detail.jsp?no=2
	//1
	String no=request.getParameter("no");
	if(no==null || no.isEmpty()){ %>
		<script type="text/javascript">
			alert('잘못된 url입니다.');
			location.href="list.jsp";
		</script>
		
	<%	return;
	}
	
	//2
	ReBoardDAO dao=new ReBoardDAO();
	ReBoardVO vo=null;
	try{
		vo=dao.selectByNo(Integer.parseInt(no));	
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	//3
	String content=vo.getContent();
	if(content!=null && !content.isEmpty()){
		content=content.replace("\r\n", "<br>");
	}else{
		content="";
	}
	String fileInfo = "";
	String downInfo="";
	if(vo.getFileName()!=null && !vo.getFileName().isEmpty()){
		
		fileInfo=Utility.getFileInfo(vo);
		downInfo=" 다운 : "+vo.getDownCount();
	}
%>    
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>답변형게시판 상세보기 - 허브몰</title>
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm {
		width: 500px;
		}
</style>  
</head>
<body>
	<h2>글 상세보기</h2>
	<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">제목</span> <span><%=vo.getTitle() %></span>
		</div>
		<div>
			<span class="sp1">작성자</span> <span><%=vo.getName() %></span>
		</div>
		<div>
			<span class="sp1">등록일</span> <span><%=vo.getRegdate() %></span>
		</div>
		<div>
			<span class="sp1">조회수</span> <span><%=vo.getReadcount() %></span>
		</div>
		<div>
			<span class="sp1">첨부파일</span> 
				
			<span><a href="downCount.jsp?no=<%=no%>&fileName=<%=vo.getFileName() %>"><%=fileInfo%> </a></span>
			<span><%=downInfo%></span>
		</div>
		<div class="lastDiv">			
			<p class="content"><%=content %></p>
		</div>
		<div class="center">
			<a href='edit.jsp?no=<%=no%>'>수정</a> |
        	<a href='delete.jsp?no=<%=no%>&groupNo=<%=vo.getGroupNo()%>&step=<%=vo.getStep()%>'>
        	삭제</a> | 
        	<a href='write.jsp?no=<%=no%>'>답변</a> |
        	<a href='list.jsp'>목록</a>			
		</div>
	</div>

	
</body>
</html>
```

#### downcount.jsp
```java
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.herbmall.common.Utility"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.reboard.model.ReBoardDAO"%>
<%@page import="com.herbmall.reboard.model.ReBoardVO"%>
<%@page import="com.herbmall.board.model.BoardVO"%>
<%@page import="com.herbmall.board.model.BoardDAO"%>
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
	//다운로드 수 증가
	String no = request.getParameter("no");
	String fileName = request.getParameter("fileName");
	
	ReBoardDAO dao = new ReBoardDAO();
	ReBoardVO vo = dao.selectByNo(Integer.parseInt(no));
	
	BufferedInputStream bis = null;
	
	BufferedOutputStream bos = null;
	
	
	try{		
		int cnt = dao.updateDownCount(Integer.parseInt(no));
		
		//파일 다운로드 처리
		//page의 설정을 바꾸기 위해 response를 날린다
		response.reset();
		
		//setContentType은 MIME 타입을 지정-octet-stream으로 지정시,
		//형식을 지정하지 않겠다는 것
		response.setContentType("application/octet-stream");
		
		//브라우저 파일 확장자를 포함하여 모든 확장자의 파일들에 대해 다룬로드 시 무조건
		//파일 다운로드 대화상자가 뜨도록 하는 헤더 속성
		response.setHeader("Content-Disposition", "attachment;filename="
         + new String(fileName.getBytes("euc-kr"), "ISO-8859-1"));

		//url 전송 시 ISO-8859-1로 인코딩되므로 한글 처리를 위해 인코딩
		
		out.clear();
    	out=pageContext.pushBody();

		//생략 시 프로그램 상엔 이상이 없으나 이미 존재하고 있는 out객체로
		//바이트 기반의 스트림 작업을 명시하면서 오류 발생
		
		
		String path = application.getRealPath(Utility.UPLOAD_PATH); //UPLOAD_PATH = "pds_upload"; 
		path=Utility.TEST_PATH; //TEST_PATH = "C:\\lecture\\workspace.list\\jsp_ws\\herbmall\\src\\main\\webapp\\pds_upload";
		
		File file = new File(path,fileName);
		
		byte[] data = new byte[1024*1024];
		bis = new BufferedInputStream(new FileInputStream(file));
		
		bos = new BufferedOutputStream(response.getOutputStream());
		
		int count = 0;
		while((count=bis.read(data))!=-1){
			bos.write(data);
		}
		
		
	}catch(SQLException e){
		e.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(bis!=null) bis.close();
		if(bos!=null) bos.close();
	}

%>
</body>
</html>
```

## 7. 삭제
#### delete.jsp
```java
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>답변형게시판 글 삭제 - 허브몰</title>
<link rel="stylesheet" type="text/css" href="../css/mainstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/clear.css" />
<link rel="stylesheet" type="text/css" href="../css/formLayout.css" />
<link rel="stylesheet" type="text/css" href="../css/mystyle.css" />
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm{
		width:650px;
		border:1px solid #ddd;		
	}
	/* .divForm form{
		width:650px;
	} */
	.divForm div	{
		/* clear: both; */
		border:none;
		padding: 7px 0;
		margin: 0;
		overflow: auto;
	}	
	.sp{
		font-size:0.9em;
		color:#0056AC;			
	}
	.divForm fieldset	{
		border:0;
	}
</style>
<%
	//1
	String no=request.getParameter("no");
	if(no==null || no.isEmpty()){ %>
		<script type="text/javascript">
			alert('잘못된 url입니다.');
			location.href="list.jsp";
		</script>
		
	<%	return;
	}
	
	String groupNo=request.getParameter("groupNo");
	String step=request.getParameter("step");
	String fileName = request.getParameter("fileName");
	   
	//2
	//3
%>
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('form[name=frmDelete]').submit(function(){
			if(confirm('삭제하시겠습니까?')){
				if($.trim($('#pwd').val()).length<1){
					alert('비번 입력!!');
					$('#pwd').focus();
					event.preventDefault();
				}
			}else{
				event.preventDefault();
			}
		});
	});
	
</script>
</head>
<body>
<div class="divForm">
<form name="frmDelete" method="post"	action="delete_ok.jsp" >
	<input type="text" name="no" value="<%=no %>" />
	<input type="text" name="groupNo" value="<%=groupNo %>" />
   <input type="text" name="step" value="<%=step %>" />
	<!-- 삭제 처리시 필요한 no를 hidden 필드에 넣어준다 --> 
   <input type="text" name="fileName" value="<%=fileName %>" />
   
		<fieldset>
		<legend>글 삭제</legend>
	        <div>           
	        	<span class="sp"><%=no %> 번 글을 삭제하시겠습니까?</span>                        
	        </div>
	        <div>           
	            <label for="pwd">비밀번호</label>
	            <input type="password" id="pwd" name="pwd" />   
	        </div>
	        <div class="center">
	            <input type ="submit"  value="삭제" />
	            <input type = "Button" value="글목록" 
                	OnClick="location.href='list.jsp'" />
	        </div>
	    </fieldset>
    </form>
</div>

</body>
</html>
```

#### delete_ok.jsp   

```java
<%@page import="java.io.File"%>
<%@page import="com.herbmall.common.Utility"%>
<%@page import="javax.swing.JTree.DynamicUtilTreeNode"%>
<%@page import="com.herbmall.reboard.model.ReBoardVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.reboard.model.ReBoardDAO"%>
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
	//delete.jsp에서 post방식으로 이동
	//1
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
	String pwd = request.getParameter("pwd");
	String groupNo = request.getParameter("groupNo");
	String step = request.getParameter("step");
	String fileName = request.getParameter("fileName");
	String saveDir = Utility.TEST_PATH;

	//2
	ReBoardDAO dao = new ReBoardDAO();
	ReBoardVO vo = new ReBoardVO();
	vo.setNo(Integer.parseInt(no));
	vo.setGroupNo(Integer.parseInt(groupNo));
	vo.setStep(Integer.parseInt(step));

	try {
		//[1]비밀번호 체크
		if(dao.checkPwd(Integer.parseInt(no), pwd)){
			//[2]삭제
			dao.deleteReBoard(vo);
			if(fileName!=null && !fileName.isEmpty()){
	            File file=new File(saveDir, fileName);
	            if(file.exists()){
	               boolean bool=file.delete();
	               System.out.println("업로드된 파일 삭제:"+bool);
	            }
	         }%>
		%>
			<script type="text/javascript">
				alert("삭제 성공");
				location.href = "list.jsp";
			</script>
	<%  }else{ %>
			<script type="text/javascript">
				alert("비번 불일치!");
				history.back();
			</script>			
	<%	} //if
	} catch (SQLException e) {
		e.printStackTrace();
	}
	%>
</body>
</html>
```
