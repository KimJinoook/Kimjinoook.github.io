---
layout: post
title:  "1. 리액트란"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

## 개요
- 자바스크립트의 발전
  - 자바스크립트 만으로 대규모 어플리케이션을 만들 수 있게 됨
- 구조관리의 발전 필요
  - 이를 해결하기 위해 수많은 프레임워크 출시
    - 앵귤러,뷰js 등
  - 해당 프레임워크는 주로 MVC, MVVM 아키텍쳐 사용
    - 모델과 뷰가 있다는 공통점
  - 보통 사용자가 어떤 작업을 하면, 변경사항을 뷰에 반영
    - 반영하는 과정에서 뷰를 변형(mutate)
    - 페이스북 개발팀은 뷰를 변형하는 것이 아닌, 새로 렌더링하는 방식 고안
      - 리액트   

### 리액트의 특징
- 리액트는 라이브러리이다
  - 구조가 MVC등인 프레임워크와는 달리 오직 view만 신경쓰는 라이브러리
  - 기타 기능은 직접 구현해 사용해야한다
- 컴포넌트
  - 특정 부분이 어떻게 생길지 정하는 선언체
  - 템플릿과는 다른 개념
  - 재사용이 가능한 API로 수많은 기능을 내장
  - 컴포넌트 하나에서 해당 컴포넌트의 생김새와 작동방식을 정의
- 렌더링
  - 사용자 화면에 뷰를 보여주는 것
  - 리액트는 초기렌더링 후 데이터가 변할때마다 새롭게 리렌더링
  - 초기 렌더링
    - 맨 처음 어떻게 보일지 정함
    - render() 함수
      - 컴포넌트가 어떻게 생겼는지 정의
      - html 문자열을 반환하지 않는다
      - 뷰의 모양과 작동에 대한 정보를 지닌 객체를 반환
      - 컴포넌트 내부에는 또 다른 컴포넌트가 들어갈 수 있다
      - render 함수 실행 시, 내부에있는 컴포넌트들도 재귀적으로 렌더링
      - 최상위 컴포넌트의 렌더링 작업이 끝나면, 지니고있는 정보를 사용해 html 마크업 제작
      - 이를 실제 페이지의 DOM 요소에 주입
- 조화과정
  - 뷰를 업데이트하는 것
  - 데이터에 변화가 있을 때마다 뷰가 변형되는 것이 아닌, 새로운 요소로 갈아끼운다
    - 데이터 업데이트시, 값을 수정하는 것이 아닌, 새로운 데이터로 render함수를 또 다시 호출
    - 해당 데이터를 지닌 새로운 뷰를 생성
    - 이전 컴포넌트정보와 현재 컴포넌트 정보를 비교
    - 두 뷰의 차이를 알아내 최소한의 연산으로 DOM 트리 업데이트
- Virtual DOM
  - DOM
    - Document Object Model
    - 객체로 문서구조를 표현, HTML이나 XML 등
    - 웹 브라우저는 DOM을 활용해 객체에 js와 css 적용
    - Dom은 트리형태, 특정 노드를 찾거나 제거하거나 삽입가능
    - DOM의 변화가 일어날 시, 웹브라우저는 CSS를 다시연산하고, 레이아웃을 구성하고, 페이지를 리페인트
      - 성능 저하
    - 최소한의 DOM 조작 필요
  - Virtual DOM
    - 실제 DOM을 조작하는 대신, 이를 추상화한 자바스크립트 객체를 구성해 사용
    - 실제 DOM의 사본과 비슷
    - 데이터 업데이트 시 전체 UI를 Virtual DOM에 리렌더링
    - DOM과 내용을 비교
    - 바뀐부분만 실제 DOM에 적용
