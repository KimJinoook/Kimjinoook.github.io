---
layout: post
title:  "10. jquery ui 플러그인"
subtitle:   ""
categories: lang
tags: jquery
comments: false
header-img: 
---

- 폼 유효성 검사, 슬라이더 등의 추가 기능을 별도의 js파일로 제공하는 jquery 라이브러리
- 코어 플러그인
  - 코어 프로젝트의 일부로 간주되는 jquery의 확장 부분
  - ex) jquery UI
- 커뮤니티 플러그인
  - 개발자들이 직접 개발한 것을 공개한 플러그인
  - [plugins.jquery.com](http://plugins.jquery.com/)   
- jquery ui플러그인
  - jquery에서 공식적으로 지정한 플러그인
  - 많이 사용된 유명한 jquery플러그인을 모아 정리
- jquery UI 플러그인의 메서드 형태
  - $(selector).플러그인이름("메서드이름")
  - $(selector).darepicker("getDate")
- 플러그인 소스는 항상 jquery 라이브러리 소드보다 뒤에서 삽입해야 한다   

### datePicker   
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="../css/jquery-ui.min.css" type="text/css" rel="stylesheet">
<script src = "../js/jquery-3.6.0.min.js"></script>
<script src = "../js/jquery-ui.min.js"></script>
<script>
	$(function(){
		$('#startDay').datepicker();
		$('#endDay').datepicker({
			dateFormat : 'yy-mm-dd',
			changeYear : true,
			dayNamesMin:['일','월','화','수','목','금','토'],
			monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
		});
		$('button').click(function(){
			var curDate=$('#startDay').datepicker("getDate");
			alert(curDate);
		});
	});
</script>
</head>
<body>
	<p>Date: <input type="text" id="startDay"></p>
	<p>Date: <input type="text" id="endDay"></p>
	<button>버튼</button>

</body>
</html>
```

### Accordion   
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="../css/jquery-ui.min.css" type="text/css" rel="stylesheet">
<script src = "../js/jquery-3.6.0.min.js"></script>
<script src = "../js/jquery-ui.min.js"></script>
<script>
	$(function(){
		//$('#accordion').accordion();
		$('#accordion').accordion({event:"mouseenter"});
	});
</script>
</head>
<body>
	<div id="accordion">
	  <h3>Section 1</h3>
	  <div>
	    <p>
	    Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer
	    ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit
	    amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut
	    odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.
	    </p>
	  </div>
	  <h3>Section 2</h3>
	  <div>
	    <p>
	    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet
	    purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor
	    velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In
	    suscipit faucibus urna.
	    </p>
	  </div>
	  <h3>Section 3</h3>
	  <div>
	    <p>
	    Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
	    Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero
	    ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis
	    lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.
	    </p>
	    <ul>
	      <li>List item one</li>
	      <li>List item two</li>
	      <li>List item three</li>
	    </ul>
	  </div>
	  <h3>Section 4</h3>
	  <div>
	    <p>
	    Cras dictum. Pellentesque habitant morbi tristique senectus et netus
	    et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in
	    faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia
	    mauris vel est.
	    </p>
	    <p>
	    Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
	    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
	    inceptos himenaeos.
	    </p>
	  </div>
	</div>

</body>
</html>
```

### tabs   
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="../css/jquery-ui.min.css" type="text/css" rel="stylesheet">
<script src = "../js/jquery-3.6.0.min.js"></script>
<script src = "../js/jquery-ui.min.js"></script>
<script>
	$(function(){
		$('#tabs').tabs();
	});
</script>
</head>
<body>
	<div id="tabs">
	  <ul>
	    <li><a href="#tabs-1">Nunc tincidunt</a></li>
	    <li><a href="#tabs-2">Proin dolor</a></li>
	    <li><a href="#tabs-3">Aenean lacinia</a></li>
	  </ul>
	  <div id="tabs-1">
	    <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
	  </div>
	  <div id="tabs-2">
	    <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
	  </div>
	  <div id="tabs-3">
	    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
	    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
	  </div>
	</div>

</body>
</html>
```

### autocomplete   
```javascript
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="../css/jquery-ui.min.css" type="text/css" rel="stylesheet">
<script src = "../js/jquery-3.6.0.min.js"></script>
<script src = "../js/jquery-ui.min.js"></script>
<script>
	$(function(){
		var availableTags = [
		      "ActionScript",
		      "AppleScript",
		      "Asp",
		      "BASIC",
		      "C",
		      "C++",
		      "Clojure",
		      "COBOL",
		      "ColdFusion",
		      "Erlang",
		      "Fortran",
		      "Groovy",
		      "Haskell",
		      "Java",
		      "JavaScript",
		      "Lisp",
		      "Perl",
		      "PHP",
		      "Python",
		      "Ruby",
		      "Scala",
		      "Scheme"
		    ];
		    $( "#tags" ).autocomplete({
		      source: availableTags
		    });
	});
</script>
</head>
<body>
	<div class="ui-widget">
	  <label for="tags">Tags: </label>
	  <input id="tags">
	</div>

</body>
</html>
```
