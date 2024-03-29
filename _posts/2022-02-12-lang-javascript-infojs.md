---
layout: post
title:  "1. 자바스크립트"
subtitle:   ""
categories: lang
tags: javascript
comments: false
header-img: 
---

## 1. 자바스크립트란

- 오라클과 넷스케이프에서 공동 개발한 **클라이언트 쪽 스크립트 언어**
- 웹브라우저 내에서만 돌아가는 스크립트 언어
- 동적인 효과, 데이터의 유효성 검사 목적
- 웹 스크립트 언어
    - 컴파일되어 실행파일 형태로 작동하는 것이 아님
    - 사용자의 요청이 있을 때마다 각 언어 해석기에 의해 프로그램 실행
- server side script
    - 사용자의 요청이 있을 때마다 웹서버 상에서 DB등을 활용한 작업 처리
    - html형대로 가공하여 사용자의 브라우저에 전달
    - jsp, asp, php 등
- client side script
    - 실행 위치가 클라이언트의 웹 브라우저인 스크립트 언어
    - 브라우저에 의해 해석되어 실행
    - javaScript, Vbscript 등

## 2. 기본

- 자바스크립트는 html문서에 script 태그를 이용하여 프로그램을 직접 삽입한다
- script 태그는 hrml 문서 어디에든 사용할 수 있다
- script 태그는 한 문서 안에서 여러개를 사용할 수 있다
- script 태그가 삽입된 위치에소 소스가 실행된다
- html 내 script 태그를 이용하여 소스전체를 작성할 수 있다
- 외부에 js파일로 저장하여 모듈화, html 내 script 태그를 이용해 js파일을 연결할 수 있다



```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>age</title>
    <link rel="stylesheet" href="css/age.css"
</head>
<body>
    <button class="btn" onclick="calc()">나이 계산</button>
    <div id="result" class="show">(결과값 표시)</div>
    <script src="js/age.js"></script>
</body>

</html>
```

- 코드의 동작 원리
    - 브라우저가 html문서를 처음부터 한 줄씩 태그들을 해석해 나가면서 결과를 화면상에 출력
    - html 문서 엔진이 script 태그를 만나면 자바스크립트 엔진 호출, script 태그내 코드 실행
    - 브라우저는 문서 끝가지 태그를 정상적으로 해석하며, 이벤트가 발생하면 그에 상응하는 결과 실행

## 3. 코드의 동작 방법
1. script 태그 이용
2. 외부 js파일 이용
3. html 태그의 이벤트 핸들러를 자바스크립트 코드로 표현
4. 자바스크립트 코드를 URL로 사용   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 자바스크립트 동작 방법 -->
	<!--1. <script> ~ </script> 태그 이용 -->
	
	<script type = "text/javascript">
		document.write("<h1>script 태그 내의 코드</h1>");
	</script>
	
	<!-- 2. 외부 자바스크립트 불러오기
			- js 확장자를 갖는 별도의 독립된 자바스크립트 파일을 외부에 만들어 두고,
			이를 html문서에서 불러와 실행 -->
	
	<script type = "text/javascript" src = "../js/test1.js"></script>
		
	
</head>
<body>
	<!--  3. html 태그의 이벤트 핸들러를 자바스크립트 코드로 표현하여 동작-->
	
	<input type = "button" value = "배경색 변경"
		onClick="document.bgColor = 'skyblue'"><br>
		<a href="http://www.naver.com" onMouseOver = "document.bgColor='green'"
		onMouseOut="document.bgColor=''">naver</a>
		<a href="#" onclick="alert('html태그의 이벤트 핸들러를 자바스크립트로 표현')">메세지 띄우기</a>

</body>
</html>
```

### 자바스크립트 입력과 출력 
- 사용자로부터 입력받기
    - prompt()
- 알림창 출력
    - alert()
- 웹브라우저 화면에 출력
    - document.write()   


```javascript
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <style>
        body {
            font-size: 1.3em;
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>안녕하세요</h1>


    <script>
        // 주석 실험
        var name = prompt("이름을 입력하세요");
        // document 는 현재문서, write 는 출력함수
        document.write("<b>" + name + "</b>님, 환영합니다.")
    </script>

</body>
</html>
```

![캡처](https://user-images.githubusercontent.com/99188096/162602371-3bd55820-9a06-4d18-a740-f6353ce8103f.PNG)   
![캡처2](https://user-images.githubusercontent.com/99188096/162602488-e11ecb5b-3418-4694-b515-0f113c8db373.PNG)   
![캡처3](https://user-images.githubusercontent.com/99188096/162602493-cf4666bb-cf1a-4a72-b4af-9e03b88a644a.PNG)





