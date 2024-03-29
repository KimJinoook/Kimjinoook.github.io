---
layout: post
title:  "6. 탐색메서드"
subtitle:   ""
categories: library
tags: jquery
comments: false
header-img: 
---

- 개체에 접근할 때, 선택자 혹은 필터로 이루어지지만 **추가적인 필터링이나 추가적인 탐색**이 필요할 경우 사용

## 1. 추가 필터링 관련 메서드   
![캡처](https://user-images.githubusercontent.com/99188096/165654262-d9c8a2d7-6274-46e9-a5f5-66140224ab5f.JPG)   
- 셀렉터를 통해 1차적으로 일치되는 집합을 얻어낸 후, 그 집합에 대해 사용할 수 있는 메서드
- fileter(), is(), not()
  - 2차적으로 추가 필터링을 하거나, 특정 요소가 어떤 조건에 부합하는지 검사할 때 사용
- eq(index)
  - :eq와 동일
  - ("div:eq(1)") = 현재 문서에 있는 div 중 2번째로 등장하는 div 개체를 얻는다
    - 현재 문서 집합에서 탐색
  - ("div").eq(1) = 현재 문서의 div를 셀렉트, 메모리에 올린 후 그 중 2번째로 등장하는 개체를 얻는다
    - div 집합에서 탐색
  - 결과는 동일하지만, eq메서드는 end()메서드를 사용하여, 이전 상태로 되돌아 갈 수 있다
- end()
  - 현재 일치된 개체 집합을 변경하여, 방금 일어난 작업 직전의 상태로 되돌린다
  - 되돌릴 과거가 없다면 빈 값을 반환   

### end()의 사용
```javascript
//홀수번째 인덱스를 구한다음 첫번째 div는 orange, 두번째div는 blue 적용하기

$("div:odd").eq(0).css("background", "orange");
$("div:odd").eq(1).css("background", "blue");
//각각에 대해 동일한 검색을 두번이나 반복한다

$("div:odd")
.eq(0).css("background", "orange")
.eq(1).css("background", "blue");
//제대로 작동하지 않는다. eq(0)으로 인해, 집합이 단일요소로 줄어들었기 때문에 eq(1)을 찾을 수 없다

$("div:odd")
.eq(0).css("background", "orange")
.end()
.eq(1).css("background", "blue");
//end()메서드를 통해 직전의 상태로 되돌린다
//$("div:odd")와 동일
```


- filter(expression)
  - 인자 expression으로 주로 셀렉터를 사용
  - ("div:odd") = ("div").filter(":odd")
  - 인자로 함수 이용 가능
- not(expr)
  - filter메서드와 반대되는 메서드
  - expr 표현식과 매치되지 않는 것들만 가져온다
  - ("div").filter(":odd") = ("div").not(":even")   
- is(expr)
  - 개체를 비교하여 boolean을 알려주는 메서드
  - if문에서 많이 사용
  - 인자를 쉼표로 구분하여 여러 표현식 나열 가능
    - is("인자1, 인자2, 인자3")
    - 인자중 하나라도 만족하면 true   

```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	div{width:60px;height:60px;float:left;
		border:2px solid blue;margin:4px}
		.orange{background:orange
	}
	
	.blue{background:blue
	}
	.pink{background:pink} 
</style>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('div').eq(1).css('color','red');
		
		$('div').filter(':odd')
		.eq(0).addClass('pink')
		.end()
		.eq(1).addClass('orange')
		.end()
		.css('border-width','5px');
		
		$('div').not(':odd').eq(1).css('color','green');
		
		var $myDiv = $('div').eq(5);
		if($myDiv.is('div')){
			$myDiv.css('color','red').css('font-weight','bole');
		}
		if($myDiv.is('blue, .orange, .pink')){
			$myDiv.text('마이칼라');
		}
		$('div').first().css('background','cyan');
		
		
		$('h3').filter(function (index) {
			return index % 3 == 0;
			}).css({
			backgroundColor: 'black',
			color: 'white'
		});

	});
</script>
</head>
<body>
	<div>a</div>
	<div>b</div>
	<div>c</div>
	<div>d</div>
	<div>e</div>
	<div class="orange">f</div>
	<div>g</div>
	
	<br><br><br>
	<h3>Header-0</h3>
	<h3>Header-1</h3>
	<h3>Header-2</h3>
	<h3>Header-3</h3>
	<h3>Header-4</h3>
	<h3>Header-5</h3>
	
</body>
</html>
```

***

## 2. 찾기와 관계된 메서드
![캡처2](https://user-images.githubusercontent.com/99188096/165660711-1a29c690-c831-44ef-aa24-971733291825.JPG)   
- add(expr)
	- 1차적으로 검색된 결과 집합에 추가적인 검색집합을 함치는 역할
	- ("div").add("p") = ("div, p")
- find(expr)
	- 현재 검색된 요소의 자식을 대상으로 추가적인 검색 수행
	- filter() : 검색 결과 집합에 대해서 그 개체들에 대해 다시 필터링
	- fine() : 검색 결과 집합에 대해서 그 개체들의 자식들에게서 찾는 것   

```javascript
$("div").filter("p") //div 태그를 검색 후, div 태그 중에서 p태그를 검색 : 결과 없음
$("div").find("p") // div 태그를 검색 후, 그 자식요소 중 p태그 검색 : div 태그의 자식인 p태그
```

#### next, find, add, siblings
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	div{width:60px;height:60px;float:left;
		margin:7px;border:1px solid blue;
		padding:7px}
</style>
<script src = "../js/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		$('div').eq(1)
			.siblings().css('border-width','3px')
			.end()
			.next().css('background','cyan')
			.end()
			.nextAll().css('color','red')
			.end()
			.end()
			.find('p').css('border','1px solid red')
			.add('span').css('background','yellow');
	});
</script>
</head>
<body>
	<div>A<p>p a</p></div>
	<div>B<p>p b</p></div>
	<div>C<p>p c</p></div>
	<div>D <br /><span>span d</span></div>
	<div>E <br /><span>span e</span></div>
</body>
</html>
```

#### is, selected
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
	$(function(){
		$('.error').hide();
		/*
		$('.submit').click(function(){
			var count = 0;
			$('#signup').find('input[name=lang]').each(function(idx, item){
				if($(this).is(':checked')){
					count++;
				}
			})
			if(count==0){
				$('.error').show();
				event.preventDefault();
			}else{
				$('.error').hide();
				
			}
		});*/
		$('.submit').click(function(){
			if($('#signup').find('input[name=lang]').is(':checked')==false){
				$('.error').show();
				event.preventDefault();
			}
		});
	});
</script>
</head>
<body>
	<form id="signup" method="post" action="a.jsp"> 
		<label><input type="checkbox" name="lang" value="1">java</label>
		<label><input type="checkbox" name="lang" value="2">jsp</label>
		<label><input type="checkbox" name="lang" value="3">oracle</label>
		<span class="error">하나라도 체크하셔야 합니다</span> <br><br>
		<input class="submit" type="submit" value="전송">
	</form>

</body>
</html>
```
