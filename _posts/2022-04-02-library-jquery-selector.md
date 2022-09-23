---
layout: post
title:  "2. 선택자"
subtitle:   ""
categories: lang
tags: jquery
comments: false
header-img: 
---

> $(selector) 또는 jQuery(selector)   

  - 각각의 표현식은 각 DOM요소의 확장 개체인 jQuery 개체 집합을 반환
  - 일반 DOM개체를 jQuery 개체집합으로 반환하여, jQuery개체가 지원하는 메서드 적용 가능   


- body태그 내의 모든 DOM 요소에 접근 가능
- $("selector")
  - selector로 읽어오는 개체의 반환값은 jquery에서 사용할 수 있는 개체
- CSS선택자를 그대로 사용

## 1. 기본 선택자
- $('*') : 전체 선택자
- $('#id') : id 선택자
- $('.className') : 클래스 선택자
- $('tagName') : 태그 선택자   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('*').css("font=size","20px"); //전체 선택자
		$('h2').css("font-style","italic"); //태그 선택자
		$('#header2').css('color','blue'); // 아이디 선택자
		$('.ph').css('color','red');
		$('h1, div, span').css('border','1px solid green');	
		$('span.ph').css('background','yellow');
		$('.ph.red').css('background','cyan');
	})

</script>
</head>
<body>
	<h1>h1태그</h1>
	<h2>h2 태그1</h2>
	<h2 id="header2">h2태그 2</h2>
	<div>div 태그</div>
	<p class='ph'>문단1</p>
	<p class='ph red'>문단2</p>
	<span class='ph'>안녕</span>
</body>
</html>
```

***

## 2. 자식선택자, 후손선택자
- $('parent > child') : 자식선택자 / p 요소 바로 아래 자식인 c요소 선택
- $('parent child) : 후손선택자/ p요소 하위에 존재하는 모둔 a요소 선택   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('#div1 div > *').css('border','1px solid red');
		$('#div2 div *').css('border','1px solid blue');
		
		$('#div1 div > li').css('background','yellow');
		$('#div2 div li').css('background','cyan');
		
	});
</script>
</head>
<body>
	<div id = "div1">
		<div>
			<div>
				<span>프로그래밍 언어</span>
			</div>
			<ul>
				<li>spring</li>
				<li>jsp</li>
				<li>jquery</li>
			</ul>
		</div>
	</div>
	<div id = "div2">
		<div>
			<div>
				<span>프로그래밍 언어</span>
			</div>
			<ul>
				<li>spring</li>
				<li>jsp</li>
				<li>jquery</li>
			</ul>
		</div>
	</div>
</body>
</html>
```

***

## 3. 인접선택자, 이웃 선택자
- $('prev + next') : 인접 선택자, p요소 바로 다음에 나오는 형제 n요소 선택
- $('prev ~ siblings') : 이웃 선택자, p요소 다음에 나오는 모든 형제 s요소 선택   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('label+input').css('border','1px solid blue'); //다로 다음에 나오는 형제
		$('div.start ~ div').css('background','cyan'); //다음에 나오는 모든 형제
	});
</script>
</head>
<body>
	<div>
		<label>이름 : </label><input type="text">
	</div>
	<div class="start">
		<label>아이디 : </label><input type="text">
	</div>
	<div>
		<span>비밀번호 : </span><input type="text">
	</div>
	<p>
		<label>주소 : </label><input type="text">
	</p>
	<div>
		<label>전화번호 : </label><input type="text">
	</div>
</body>
</html>
```

***

## 4. 속성 선택자   
![캡처](https://user-images.githubusercontent.com/99188096/165209663-556696fe-4544-474b-9ff2-fefb663034f6.JPG)   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('input[type=text]').val('java');
		$('a[title]').css('color','orange');
	});
</script>
</head>
<body>
	<input type="text">
	<input type="password">
	<input type="radio">
	<input type="checkbox">
	<hr>
	<a href="#">a태그1</a><br>
	<a href="#" title="a태그입니다">a태그2</a><br>
</body>
</html>
```

***

## 5. 필터선택자
- 필터선택자   

![캡처1](https://user-images.githubusercontent.com/99188096/165211503-752151ea-c88c-486e-9d92-5a656b036d61.JPG)   
- 입력양식 필터선택자   

![캡처2](https://user-images.githubusercontent.com/99188096/165211540-2944818e-9d4c-458d-b54e-6423ae07deea.JPG)   
![3](https://user-images.githubusercontent.com/99188096/165211557-9c2fc469-6e0e-4bcd-9528-55e93142a869.JPG)   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$('tr:first').css('font-style','italic');
		$('tr:even').css('font-size','25px');
		$('tr:eq(1)').css('color','orange');
		$('tr:gt(4)').css('color','blue').css('font-weight','bold');
		$('th:contains("제목2")').css('color','red');
		
		
	});
</script>
</head>
<body>
	<h1>filter 선택자</h1>
	<table border="1" style="width:400px">
		<tr>
			<th>제목1</th>
			<th>제목2</th>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
		<tr>
			<td>내용1</td>
			<td>내용2</td>
		</tr>
	</table>
</body>
</html>
```

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn1").click(function() {
			var str="체크된 값 - " + $("input[type=radio]:checked").val() + "<br>";
			str += "selected된 값 - " + $("select > option:selected").val() + "<br>";
			$("#div1").html(str);
			//$("#div1").text(str);
			});
	});
</script>
</head>
<body>
	<input id="rd1" type="radio" name="num" value="one" >하나
	<input id="rd2" type="radio" name="num" value="two" checked>둘<br><br>
	<select id="sel1">
		<option value="1">사과</option>
		<option value="2">귤</option>
		<option value="3" selected>배</option> 
	</select>
	<input id="btn1" type="button" value="버튼" />
	<h2>결과</h2>
	<div id="div1"></div> 
</body>
</html>
```
