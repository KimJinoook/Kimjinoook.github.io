---
layout: post
title:  "5. http, get과 post"
subtitle:   ""
categories: lang
tags: jsp
comments: false
header-img: 
---

## 1. HTTP 프로토콜
- 웹 브라우저와 웹 서버 사이의 데이터 통신 규칙
- 웹 페이지의 링크 클릭
  - 웹 브라우저는 http 요청 형식에 따라 웹 서버에 데이터 전송
  - 웹 서버는 받은 데이터를 분석, 처리
  - 응답하여 보내는 데이터도 http 응답 형식에 맞추어 전송
- HTTP
  - tcp/ip 위에서 돌아간다
  - 웹에서만 사용하는 프로토콜
  - stateless, connectless의 특징
    - 연결 유지x, 상태 정보 유지 x
    - 요청시 연결, 응답 후 연결 종료를 반복
    - 구글 크롬-도구-개발자도구(f12)-network에서 확인 가능
  - 요청/응답을 끊임없이 주고 받는 구조
  - http 응답 안에 html 컨텐츠가 데이터로 포함   

## 2. GET, POST
- get
  - 단순한 요청
  - url에 파라미터를 포함하여 전송
    - http header에 정보를 실어 보낸다
  - 요청파일, 요청헤더로 구성
  - 적은 양의 데이터 전송에 유리
    - 전달 속도가 빠르다
    - 256byte가 한계
  - 방식
    - 웹 브라우저의 주소창에 URL을 입력
    - 웹 페이지에서 링크를 클릭
      - html의 a태그
      - javascript의 location.href
    - 입력 폼의 method 속성값이 get인 경우
- post
  - 요청파일, 요청헤더, 메세지 몸체로 구성
  - 파라미터는 메세지 몸체에서 기술
    - http body에 정보를 실어 보낸다
  - 데이터 사이즈 제한 x
  - 보안에 유리
  - 방식
    - 폼에 정보를 입력하고 submit 버튼 클릭하는 경우
    - 사용자가 입력한 정보를 함께 보내려면 post
