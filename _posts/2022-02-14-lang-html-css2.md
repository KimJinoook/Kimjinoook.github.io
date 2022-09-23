---
layout: post
title:  "3. CSS 단위"
subtitle:   ""
categories: lang
tags: html
comments: false
header-img: 
---

## 1. 키워드
- 각각의 스타일 속성에 따라 별도의 키워드 존재
- display : block,inline, compact, grid 등

## 2. 크기단위
- %, em, cm, mm, inch, px 등
- %단위
  - 기본 설정된 크기에서 상대적으로 크기를 지정
  - 100%가 초기 설정 크기
  - 부모요소의 크기 설정이 있을 경우 부모 요소의 상대 크기
- em단위
  - 배수를 나타내는 단위
  - 1배 = 1em = 100%
  - 1.5배 = 1.5em = 150%
  - 부모요소의 크기 설정이 있을 경우 부모 요소의 상대 크기
- px 단위
  - 절대적인 크기를 지정할 때 사용
  - 기본 속성 16픽셀   

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

	<style>
	p:nth-child(1) { }
	p:nth-child(2) { font-size: 100%; }
	p:nth-child(3) { font-size: 150%; }
	p:nth-child(4) { font-size: 200%; }
	
	p:nth-child(5) { }
	p:nth-child(6) { font-size: 1.0em; }
	p:nth-child(7) { font-size: 1.5em; }
	p:nth-child(8) { font-size: 2.0em; }
	
	p:nth-child(9) { }
	p:nth-child(10) { font-size: 16px; }
	p:nth-child(11) { font-size: 24px; }
	p:nth-child(12) { font-size: 32px; }
	</style>
</head>
<body>
	<p>기본%</p>
	<p>100%</p>
	<p>150%</p>
	<p>200%</p>
	<p>기본em</p>
	<p>1em</p>
	<p>1.5em</p>
	<p>2em</p>
	<p>기본px</p>
	<p>16px</p>
	<p>24px</p>
	<p>32px</p>
</body>
```

![unit](https://user-images.githubusercontent.com/99188096/163941981-e9f3d697-83e6-49e4-8ef9-b9153fa0bced.JPG)   

## 3. 색상 단위
- 키워드값
  - color : red; 등의 방법으로 키워드 색상을 입력
- hex 코드 단위
  - #000000
- RGB 색상 단위
  - rgb(red, green,blue)
- RGBA 색상 단위
  - rgba(red,green,blue,alpha)
- HSL 색상 단위
  - hsl(hue, saturation, lightness)
- HSLA 색상 단위
  - hsla(hua, saturation, lightness, alpha)   

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

	<style>
	p:nth-child(1) {background-color: red;}
	p:nth-child(2) { background-color: #0094FF; }
	p:nth-child(3) { background-color: rgb(255,100,255);}
	p:nth-child(4) { background-color: hsl(126,50%,37%);}
	</style>
</head>
<body>
	<p>키워드</p>
	<p>hex</p>
	<p>rgb</p>
	<p>hsl</p>
</body>
```
![캡처](https://user-images.githubusercontent.com/99188096/163943414-939c8f0f-dbfa-4e84-b6e1-995cf8a0e3de.JPG)   


## 4. URL단위
- 이미지파일이나 폰트파일을 불러올 때 URL단위 사용   
> background-image : url('파일경로');   

