---
layout: post
title:  "1. 제이쿼리란"
subtitle:   ""
categories: library
tags: jquery
comments: false
header-img: 
---

- 가장 많이 사용되며 잘 만들어진 **오픈 소스 자바스크립트 라이브러리**
- Rich웹 UI를 개발하는데 도움이 되는 다양한 기능들 지원
- CSS에서 사용되는 선택자 개념으로 DOM 멤버에 접근 가능
- 플러그인 개념 도입, 기능 확장이 쉬움
- 특징
  - 편리한 CSS 선택자
  - 크로스 브라우저 지원
    - 코드가 사용될 브라우저를 신경 쓸 필요가 없다
  - 메서드 체이닝
  	- 메서드를 연속으로 사용
  	- jquery가 제공하는 모든 메서드는 반환값이 효과가 반영된 jquery개체
  	- 때문에 **메서드를 이어서 사용 가능**
  	- $(선택자).메서드().메서드().메서드();
  	- $('tr:odd').css("font-weight","bold).css("color","blue");
  - Ajax 지원
  - 풍부한 플러그인 지원
  - [jQuery 사이트](https://jquery.com)

## 1. jquery 사용
- CDN호스트 사용
  - 다운로드 페이지에서 jquery의 cdn 호스트 주소 이용   
  > script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"   
- 직접 다운로드 받아사용   
  > script src="jquery/ jquery-3.6.0.js " type="text/javascript"   

### 비교
- javascript   
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
	window.onload=function(){
		var divEls = document.getElementsByTagName("div");
		for(var i=0; i<divEls.length; i++){
			divEls[i].onclick=function(){
				this.style.display="none";
			}
		}
	}
</script>
</head>
<body>
	<div>1. 클릭하면 사라짐</div>
	<div>2. 클릭하면 사라짐</div>
	<div>3. 클릭하면 사라짐</div>
</body>
</html>
```

- jquery   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$("div").click(function(){
				$(this).hide();
			})
		});
	</script>

</head>
<body>
	<div>1. 클릭하면 사라짐</div>
	<div>2. 클릭하면 사라짐</div>
	<div>3. 클릭하면 사라짐</div>
</body>
</html>
```

## 2. $()   
> $(선택자).메서드();   

- $()로 CSS의 선택자와 모든 DOM 요소에 접근이 가능하다   

- jQuery의 진입점(엔트리 포인트)   
	> $(document).ready()   
	> $().ready()   
	> $()   

		- jquery가 제공하는 페이지 로드 이벤트 함수
		- 문서의 DOM 요소들을 조작가능한 시점이 되면 자동으로 호출
		- window.onload 이벤트와 유사   
