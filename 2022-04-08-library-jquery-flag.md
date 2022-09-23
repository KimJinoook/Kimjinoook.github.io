---
layout: post
title:  "8. 정규표현식"
subtitle:   ""
categories: library
tags: jquery
comments: false
header-img: 
---

- 객체초기화 방법
- var regExp = /정규표현식/[Flag];
- flag의 종류
  - 자주 사용하는 flag
  - g-Global-문자열 내의 모든 패턴을 찾는다
  - i-ignore case - 문자열의 대소문자를 구별하지 않는다
  - m-multi line - 문자열이 행이 바뀌어도 찾는다   

- /^[0-9-+]+$/g
	- ^는 시작, $는 끝을 의미
	- 대괄호 안
		- 0-9 : 0과 9사이의 숫자
		- -+ : -기호, +기호
	- 대괄호 밖
		- + : 패턴이 한번 또는 그 이상 반복된다는 의미
	- 결과
		- 0~9사이 숫자나 -기호, +기호만 사용할 수 있으며, 여러번 입력할 수 있다
- /^[a-z0-9_]+$/g
	- a-z사이 문자나 0-9사이 숫자나 언더바기호만 입력 가능

- 대괄호 밖 + : 배턴이 한번 또는 그이상 반복
- 대괄호 밖 * : 0번이상 반복, 입력 안해도 된다는 

### 숫자 체크
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('.error').hide();
		$('.submit').click(function(event){
			var data=$('.infobox').val(); //12a3
			var len=data.length;
			var c;
			for(var i=0;i<len;i++) {
				//c=data.charAt(i).charCodeAt(0); //1,2,a
				c=data.charCodeAt(i); 
				if(c <48 || c >57) {
					$('.error').show();
					event.preventDefault();
					break;
				} else {
					$('.error').hide();
				}
			}
		});
	});
</script>

</head>
<body>
	<form id="signup" method="post" action="">
	<div>
		<label class="label" for="age">나이 </label> 
		<input type="text" class="infobox" name="age" id="age"/> 
		<span class="error"> 숫자만 입력하셔야 합니다!</span>
	</div>
	<input class="submit" type="submit" value="Submit">
	</form>

</body>
</html>
```

### 정규식 이용 체크
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<style type="text/css">
	.error{
		color:red;
		font-size:0.8em;
	}
</style>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() { 
		$('.error').hide();
		$('.submit').click(function(event){
			var data=$('.infobox').val();
			if(validate_phoneno(data)){
				$('.error').hide();
			}else{
				$('.error').show();
				event.preventDefault();
			}
		});
	});
	
	function validate_phoneno(ph){
		var pattern=new RegExp(/^[0-9-+]+$/g);
		return pattern.test(ph); //정규식과 일치하면 true
	}
</script>

</head>
<body>

	<form id="signup" method="post" action="a.jsp"> 
	<div>
		<label class="label" for="phone">전화번호</label>
		<input type="text" class="infobox" name="phone" id="phone"/>
		<span class="error">전화번호는 숫자,+,-만을 포함할 수 있습니다.</span>
	</div> 
	<input class="submit" type="submit" value="전송 ">

	</form>
	
</body>
</html>
```

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<style type="text/css">
	.error{ color:red;font-size:0.8em;}
</style>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() { 
		$('.error').hide();
		$('.submit').click(function(event){
			var data=$('.infobox').val();
			if(validate_userid(data)){
				$('.error').hide();
			}else{
				$('.error').show();
				event.preventDefault();
			}
		});
	});
	
	function validate_userid(uid){
		var pattern= new RegExp(/^[a-z0-9_]+$/g);
		return pattern.test(uid);
	}
</script>

</head>
<body>
	<form id="signup" method="post" action="a.jsp"> 
		<div>
			<label class="label" for="userid">아이디</label>
			<input type="text" class="infobox" name="userid" id="userid"/>
			<span class="error">아이디는 문자,숫자,_(밑줄문자)만을 포함할 수 있습니다.</span>
		</div> 
		<input class="submit" type="submit" value="전송 ">
	</form>
	
</body>
</html>
```
