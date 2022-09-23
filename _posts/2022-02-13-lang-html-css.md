---
layout: post
title:  "2. CSS 선택자"
subtitle:   ""
categories: lang
tags: html
comments: false
header-img: 
---

## 1. 기본
- 특정 html태그를 선택할 때 사용하는 기능
- 선택자를 사용해 특정 태그를 선택하면 원하는 스타일 적용 가능   
> 선택자{스타일속성:스타일값;}   

- 인라인 방식   
> 태그 style = "스타일속성:값;스타일속성:값;"   

- style 태그 안에 스타일 넣기   
> style type = "text/css"   
> div{~}   

- css 경로 불러오기   
> link rel = "stylesheet" type="text/css" href="파일경로"   

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 3. css외부파일 경로 지정 -->
<link rel = "stylesheet" type="text/css" href="../css/mystyle.css">

<style type = "text/css">
	/* 2 style 태그 안에 스타일 넣기
		선택자{스타일속성:값} 
	*/
	div{
		color:red;
		border:1px solid green;
	}
	p{
		color:orange;
	}
</style>
</head>
<body>
	<h1>스타일시트 사용방법</h1>
	<h2>1 inline방식</h2>
	<!-- 
	<태그 style = "스타일속성:값;스타일속성:값;">
	 -->
	 <div style="color:blue;background-color:yellow;">
	 	div태그
	 </div>
	 <p>p태그1</p>
	 <p>p태그2</p>
	 <h3>h3태그</h3>
</body>
</html>
```
![s](https://user-images.githubusercontent.com/99188096/163904979-19aa1676-03b4-4f44-9c40-64b57c53e4e0.JPG)   

- import를 이용한 css 경로 추가
	- 파일을 분리할 수록 html 내부에 link 태그가 많아져 코드가 지저분해진다
	- import를 이용하여 정리
	- @import 뒤에 url 함수를 이용해 파일경로 입력   

```html
<!-- 기존 link 태그를 이용한 경로 추가 -->
<head>
	<title></title>
	<link rel="stylesheet" href="../css/StyleSheetA.css"/>
</head>


<!-- @import를 이용한 경로 추가 -->
<style>
	@import url(../css/StyleSheetA.css);
<style>

```

## 2. 스타일의 우선순위
- 스타일의 상속
  - 부모요소에 있는 속성들이 자식 요소로 전달
  - body태그의 스타일이, body태그 내부의 h2,p 태그등에도 적용
  - 배경색이나 배경이미지는 상속되지 않는다   

- 스타일의 단계
  - 스타일은 단계적으로 적용
  - 하나의 요소에 여러 스타일 규칙이 정의되어있다면, 가장 나중에 정의된 스타일 적용   

## 3. 선택자의 종류
![k1](https://user-images.githubusercontent.com/99188096/163907042-3de9b4d1-2c0e-4c27-a1f9-334df18e87ac.JPG)   
![k2](https://user-images.githubusercontent.com/99188096/163907064-b8346710-ee75-43c6-befd-51d5afeacfae.JPG)   
![k3](https://user-images.githubusercontent.com/99188096/163907079-f246fde3-ceb4-4348-bb0c-b70a9ebd4113.JPG)   

- 전체선택자
	- * : html 페이지 내부의 모든 태그를 선택
- 태그선택자
	- 태그 : 특정한 태그를 선택
	- 여러개의 선택자를 한번에 적용할 때는 쉼표 사용
- 아이디 선택자
	- #아이디 : 아이디 속성을 가지고 있는 태그 선택
	- 아이디는 중복불가이므로, 특정한 하나의 태그를 선택할 때 사용
- 클래스 선택자
	- .클래스 : 특정한 클래스를 가지고 있는 태그 선택
	- 클래스 속성은 공백으로 구분해 여러 클래스 사용 가능   

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type = "text/css">
	/* 전체선택자 : * : html페이지 내부의 모든 태그 선택 */
	
	*{
		color : red;
	}
	
	/* 태그 선택자 : 태그 : 특정한 태그를 선택 */
	h2{
		background-color : skyblue;
	}
	
	p{
		color : blue;
		border : 1px solid gray;
	}
	
	/* 여러개 선택 */
	h2, p{
		font-style: italic;
	}
	
	/* 아이디 선택자 : #아이디 : 아이디속성을 가지고 있는 태그 선택 */
	#header{
		width:300px;
		background : pink;
	}
	
	/* 클래스 선택자 : .클래스 : 특정 클래스를 가지고있는 태그 선택*/
	.item {
		color : red;
	}
	.header{
		background-color:blue;
	}
	/*특정 태그의 특정클래스 */
	li.header{
		background-color:pink;
	}
	
	

</style>
</head>
<body>
	<h1>전체선택자</h1>
	<h2>h2선택자, h2,p동시선택자</h2>
	<p>p선택자,동시선택자</p>
	<p id = "header">아이디 선택자</p>
	<p class="item">클래스 item선택자</p>
	<p class="header">클래스 header선택자</p>
	<p class="item header">클래스 item, header 선택자</p>
	<ul>
		<li class ="header"> li이면서 header클래스</li>
	</ul>

</body>
</html>
```

![selector](https://user-images.githubusercontent.com/99188096/163911224-642e02b8-d7fb-45db-bd2c-9b43a59c0460.JPG)   

- 속성 선택자
	- 특정 속성을 가진 html 태그를 선택할 수 있다
	- 기본속성 선택자
		- 선택자뒤에 대괄호를 사용해 속성과 같은 값을 입력
		- 선택자[속성]
		- 선택자[속성=값]
		- input[type=text] 등
	- 문자열 속성 선택자
		- 태그가 가지고있는 속성의 특정 문자열을 확인
		- 선택자[속성~=값] : 속성 안의 값이 특정 값을 단어로 포함하는 태그 선택
		- 선택자[속성|=값] : 속성 안의 값이 특정 값을 단어로 포함하는 태그 선택
		- 선택자[속성^=값] : 값이 특정 값으로 시작하는 태그
		- 선택자[속성$=값] : 값이 특정 값으로 끝나는 태그
		- 선택자[속성*=값] : 값이 특정 값을 포함하는 태그 선택
- 후손선택자
	- 특정 태그 아래에 있는 후손을 선택할 때
	- 선택자A 선택자B : 선택자 A의 후손에 위치하는 선택자B
	- 선택자A 선택자B, 선택자C
		- 선택자A의 후손 선택자B와 그냥 선택자C
	- 선택자A 선택자B, 선택자A 선택자C
		- 선택자 A의 후손 선택자B,C
- 자식선택자
	- 선택자A \> 선택자B
	- 선택자 A의 자식 요소인 선택자 B 선택
	- table의 자식을 선택할 땐 tbody가 자동으로 생성되기 때문에
	- table \> tbody \> th
- 동위선택자
	- 동위관계에 위치한 선택자를 선택할 때 사용
	- 선택자A + 선택자B
		- 선택자A 바로 뒤에 위치하는 선택자 B 선택
	- 선택자A ~ 선택자B
		- 선택자 A 뒤에 위치하는 선택자 B들을 선택
- 반응선택자
	- 사용자의 반응으로 생성되는 특정한 상태를 선택
	- :active : 사용자가 마우스를 클릭한 태그 선택
	- :hover : 사용자가 마우스를 올린 태그 선택   
	> h1:active   
		- h1태그를 클릭했을 때 적용

- 상태선택자
	- :checked : 체크상태의 input 태그 선택
	- :focus : 초점이 맞추어진 input 태그 선택
	- :enabled : 사용가능한 input 태그 선택
	- :disabled : 사용 불가능한 input 태그 선택

- 구조선택자
	- 일반구조선택자
		- :first-child : 첫번째에 위치하는 자식 선택
		- :last-chile : 마지막에 위치하는 자식 선택
		- :nth-child(수열) : 수열 n번째에 있는 자식 선택
		- :nth-last-child(수열) : 뒤에서 수열n번째 있는 자식 선택
	- 형태구조선택자
		- :first-of-type : 형제관계중 첫번째로 등장하는 특정 태그 선택
		- :last-of-type : 형제관계중 마지막으로 등장하는 특정 태그 선택
		- :nth-of-type(수열) : 형제관계중 앞에서 수열n번째 등장하는 특정 태그 선택
		- :nth-last-of-type(수열) : 형제관계중 뒤에서 수열n번째 등장하는 특정 태그 선택
- 문자선택자
	- 시작문자 선택자
		- ::first-letter : 첫번째 글자 선택
		- ::first-line : 첫번째 줄 선택
	- 전후문자 선택자
		- ::after : 태그 뒤에 위치하는 공간 선택
		- ::before : 태그 앞에 위치하는 공간 선택
	- 반응문자 선택자
		- ::selection : 사용자가 드래그한 글자 선택
- 링크선택자
	- :link : href속성을 가지고있는 a태그 선택
	- :visited : 방문했던 링크를 가지고있는 a태그 선택
- 부정선택자
	- :not : 선택자를 반대로 적용




