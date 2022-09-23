---
layout: post
title:  "7. 이벤트 관련 메서드"
subtitle:   ""
categories: library
tags: jquery
comments: false
header-img: 
---

- 이벤트 처리기 매핑 (event handler mapping)
  - 이벤트가 발생하는 경우 특정 로직을 수행하기 위해 이벤트와 메서드를 연결
  - window.onload = pageLoad;
  - 이벤트 :load
  - 이벤트 처리기 : pageLoad
  - 이 둘을 연결하기 위한 코드 : 매핑코드
- 이벤트 객체
	- 자바스크립트
		- 브라우저마다 이벤트 객체 사용방법이 다르다
		- 브라우저마다 이벤트 객체 속성이 다르다
	- JQuery
		- 모든 브라우저가 같은 방법으로 사용, 같은 속성을 가진다
		- 속성
			- event.pageX : 브라우저 화면 기준 마우스 x좌표
			- event.pageY : 브라우저 화면 기준 마우스 y좌표
			- event.preventDefalut() : 기본이벤트 제거
			- event.stopPropagation() : 이벤트 전달 제거   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('img').on('mousedown', function (event) {
			var x = event.pageX;
			var y = event.pageY;
			$("#div1").html("x="+x+", y="+y); 
		});
	})
</script>
</head>
<body>
	<img src="../images/dog.jpg" border="1"><br><br>
	<div id="div1"></div>

</body>
</html>
```

## 1. jQuery 제공 이벤트 메서드
![캡처](https://user-images.githubusercontent.com/99188096/165672223-bbe048ec-1ded-407d-8f98-bd0c21ca20e3.JPG)
- on()
  - 이벤트를 연결
  - $(selector).on(이벤트이름, 이벤트리스너);
  - $(selector).on(이벤트이름, function(event){});
  - $(selector).on(object);
  - 이벤트 리스너 안에서 this는 이벤트가 발생한 객체를 의미   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('button').on('click', function(event){
			$('#div1').html($(this).text()+" 클릭됨!!");
		});
		$('button').on({
			mouseover: function () { $(this).css('background', 'pink'); },
			mouseout: function () { $(this).css('background', ''); }
		});
	})
</script>
</head>
<body>
	<button>버튼1</button><br>
	<button>버튼2</button><br>
	<button>버튼3</button><br>
	<div id="div1"></div>
</body>
</html>
```

***

## 2. 간단한 이벤트 연결
![캡처4](https://user-images.githubusercontent.com/99188096/165672918-c0f8b526-b50a-4441-b0de-873b4f418c35.jpg)   
- 많이 사용하는 이벤트들을 간단 방식으로 연결할 수 있다
	- $(selector).method(function(event){});
- hover()
	- 두개의 함수를 매개변수로 받는다
	- 첫번째 매개변수는 mouseenter 이벤트리스너
	- 두번째 매개변수는 mouseleave 이벤트리스너
	- $(selector).hover(function(event){}, function(event){});   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('button').hover(function () { 
			$(this).css('background', 'skyblue'); 
		},
		function () { 
			$(this).css('background', ''); 
		});

		$('button').mouseover(function () { 
			$(this).css('background', 'cyan'); 
		}).mouseout(function () { 
			$(this).css('background', ''); 
		}); //메서드 체이닝 이용
	})
</script>
</head>
<body>
	<button>버튼1</button><br>
	<button>버튼2</button><br>
	<button>버튼3</button><br>
	<div id="div1"></div>
</body>
</html>
```

## 3. 이벤트 제거
### 이벤트 연결 제거
- $(selector).off();
	- 해당 문서객체와 관련된 모든 이벤트 제거
- $(selector).off(eventName);
	- 해당 문서 객체의 특정 이벤트와 관련된 모든 이벤트 제거
- $(selector).off(eventName,function);
	- 특정 이벤트리스너 제거   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('button').click(function(){
			$(this).html('클릭됨');
			alert('이벤트발생');
			$(this).off();
		});
	})
</script>
</head>
<body>
	<button>버튼1</button><br>
	<button>버튼2</button><br>
	<button>버튼3</button><br>

</body>
</html>
```

### 기본 이벤트 제거 / 이벤트 전달 제거
- preventDefault() : 기본 이벤트 제거
- stopPropagation() : 이벤트 전달 제거
- 이 둘을 함께 사용하는 경우가 많다
	- return false 이용
		- jquery : 위 두 메서드를 함께 적용한 것으로 인식
		- js : 기본이벤트만 제거   

- 이벤트 버블링
	- 겹쳐진 개체에서 가장 위쪽에 있는 개체를 선택 시, 아래 개체 모두 이벤트가 발생
	- 자식 요소 클릭 시 부모로 이벤트 전파
	- 이벤트를 발생시킨 컴포넌트에서 이벤트를 부모 컴포넌트로 전파
	- 버블링 : 자식노드에서 부모 노드순으로 이벤트 전달 실행
	- 캡쳐링 : 부모노드에서 자식 노드순으로 이벤트 전달 실행

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	div, a {
		padding: 10px;
		border: 1px solid gray;
	}
</style>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		//a태그 기본이벤트 : naver.com으로 링크 이동
		$('a').click(function(event){
			$(this).css('background','pink');
			
			//기본 이벤트 제거, 클릭시 네이버로 넘어가지 않게 한다
			event.preventDefault();
			
			//a태그 클릭시 아래 div태그 클릭 이벤트까지 발생 : 이벤트버블링
			//이벤트의 전달을 제거, a태그 클릭 시 div이벤트 발생 x
			event.stopPropagation();
		});
		$('div').click(function(){
			$(this).css('background','skyblue');
		})
	})
</script>
</head>
<body>
	<div>
		<a href="http://www.naver.com">naver</a>
	</div>

</body>
</html>
```

***

## 4. 마우스 이벤트
![mouse](https://user-images.githubusercontent.com/99188096/165681892-e9c12106-b276-43d6-84cc-ce02cefed6f2.JPG)   

***

## 5. 키보드 이벤트
- keydown
	- 키보드를 누를 때 발생
- keypress
	- 글자가 입력될 때 발생
- keyup
	- 키보드를 뗄 때   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
	div, a {
		padding: 10px;
		border: 1px solid gray;
	}
</style>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
	//[1] lblError 레이어 클리어
		$('#txtPwd').keyup(function() {
			$('#lblError').text(''); // 클리어
		});
		//[2] 암호 확인 기능 구현
		$('#txtPwdConfirm').keyup(function() { 
			if ($('#txtPwd').val() != $('#txtPwdConfirm').val()) {
				$('#lblError').html("<b>비밀번호가 틀립니다.</b>"); // 레이어에 HTML 출력
			}else {
				$('#lblError').html("<b>비밀번호가 맞습니다.</b>");
			}
		});
	});
</script>

</head>
<body>
	비밀번호:<input type="password" id="txtPwd" size="20"><br />
	비밀번호 확인:<input type="password" id="txtPwdConfirm" size="20"><br />
	<input type="button" id="btnOK" value="확인" />
	<div id="lblError" style="color:red">암호를 입력하시오.</div>

</body>
</html>
```

***

## 6. 윈도우 이벤트
- ready
	- 문서 객체가 준비 완료되었을 때
- road
	- 윈도우(문서객체)를 불러들일 때
- unload
	- 윈도우(문서객체)를 닫을 때
- resize
	- 윈도우의 크기를 변화시킬 때
- scroll
	- 윈도우를 스크롤할 때
- error
	- 에러가 있을 때   


***

## 7. 입력 양식 이벤트
- change
	- 입력 양식의 내용을 변경할 때
- focus
	- 입력 양식에 초점을 맞추면
- focusin
	- 입력 양식에 초점이 맞추어지기 바로 전
- focusout
	- 입력 양식에 초점이 사라지기 바로 전
- blur
	- 입력 양식에 초점이 사라지면
- select
	- 입력 양식을 선택할 때 발생 (text, textarea 제외)
- submit
	- submit 버튼 클릭 시
- reset
	- reset 버튼 클릭 시   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('#chkAll').change(function() {
			if (this.checked) {
				$('#chkItem').children().prop('checked', true);
			} else {
				$('#chkItem input[type=checkbox]').prop('checked', false);
			}
		});
	});
</script>

</head>
<body>
	<input type="checkbox" id="chkAll" /><label for="chkAll">All</label>
	<div id="chkItem">
		<input type="checkbox" id="chk1"/><label for="chk1">참외</label>
		<input type="checkbox" id="chk2"/><label for="chk2">자두</label>
		<input type="checkbox" id="chk3"/><label for="chk3">수박</label>
	</div>
</body>
</html>
```


***

## 8. .prop()
- 지정한 선택자를 가진 첫 번째 요소의 속성값을 가져오거나 추가
- html의 속성 (attribute)이 아닌 js입장에서의 속성(property)
- 속성값을 가져올 때 .prop(propertyName)
- 속성값을 추가할 때 .prop(propertyName, value)   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('#chkAll').change(function() {
			$('#chkItem').children().prop('checked',this.checked);
		});
	});
</script>

</head>
<body>
	<input type="checkbox" id="chkAll" /><label for="chkAll">All</label>
	<div id="chkItem">
		<input type="checkbox" id="chk1"/><label for="chk1">참외</label>
		<input type="checkbox" id="chk2"/><label for="chk2">자두</label>
		<input type="checkbox" id="chk3"/><label for="chk3">수박</label>
	</div>
</body>
</html>
```

***

## 9. 이벤트 관련 메서드
- one()
	- 일회성 이벤트 처리를 지원하기 위한 메서드
	- $("#btn").one('click', function(){});
- trigher()
	- 코드를 사용해 특정 이벤트를 일으키기 위한 메서드
	- 사용자가 클릭하지 않아도, 클릭한 것처럼 이벤트를 일으키는 코드
	- $('#btn').trigger('click');
	- 코드를 사용해 강제로 이벤트 발생   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('#btn').on("click", function() {
			alert('버튼 클릭됨');
		});
			// 페이지 로드시 사용자의 반응이 아닌 코드에 의해서 click 이벤트를 실행
		$('#btn').trigger("click");
		$('#img1').one('click', function() {
			alert('이미지는 한번만 클릭하세요');
		});
	});
</script>

</head>
<body>
	<div id="my">
		<input type="button" id="btn" value="버튼" >
		<img id="img1" src='../images/dog.jpg'>
	</div>
</body>
</html>
```
