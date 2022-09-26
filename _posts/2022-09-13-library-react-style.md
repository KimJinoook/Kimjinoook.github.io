---
layout: post
title:  "13. 리액트의 스타일링"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 일반 CSS
- Sass
  - CSS 전처리기
  - 확장된 CSS문법
- CSS 모듈
  - CSS클래스가 다른 CSS클래스의 이름과 충돌하지 않도록 파일마다 고유 이름을 자동으로 생성
- styled-components
  - 스타일을 자바스크립트 파일에 내장시키는 방식
  - 스타일을 작성함과 동시에 해당 스타일이 적용된 컴포넌트를 만듦   

***

## 일반 CSS
- CSS클래스를 중복되지 않게 만들어야 한다
  - App.js / App.css
    - 컴포넌트이름으로 css를 만든다
  - BEM네이밍
    - 클래스가 어디에서 어떤 용도로 사용되는지 명확하게 명명하는 방식
    - card_title-primary 등   

- CSS Selector
  - CSS클래스가 특정 클래스 내부에 있는 경우에만 스타일 적용 가능
  - .App .logo{}   

```css
.App {
  text-align: center;
}
.App .logo {
  animation: App-logo-spin infinite 20s linear;
  height: 40vmin;
}
.App header {
  background-color: #282c34;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: calc(10px + 2vmin);
  color: white;
}
.App a {
  color: #61dafb;
}
@keyframes App-logo-spin {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(360deg);
  }
}
```

```javascript
import logo from "./logo.svg";
import "./App.css";
function App() {
  return (
    <div className="App">
      <header>
        <img src={logo} className="logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a href="https://reactjs.org" target="_blank" rel="noopener noreferrer">
          Learn React
        </a>
      </header>
    </div>
  );
}
export default App;
```

- 컴포넌트의 최상위 html요소에는 컴포넌트의 이름으로 클래스 이름을 짓는다
- 그 내부에서 소문자를 입력하거나 header같은 태그를 사용해 클래스 이름이 불필요한 경우 생략 가능   

***

## Sass
- Syntactically Awesome Style Sheets
- CSS 전처리기로 복잡한 작업을 편리하게해준다
- 코드의 재사용성 향상
- 코드의 가독성 향상
- .scss 와 .sass 확장자 지원   

#### 비교   
```css
//sass
$font-stack : Helvetica, sans-serif
$primary-color : #333

body
  font : 100% $font-stack
  color: $primary-color


//scss
$font-stack : Helvetica, sans-serif;
$primary-color : #333;

body {
  font : 100% $font-stack;
  color: $primary-color;
}
```

- node-sass 라이브러리 설치 필요
  - sass를 css로 변환해준다
  - npm add node-sass@4.14.1
  - npm install -g node-sass


### SassComponent.scss   
```css
// 변수 사용하기
$red: #fa5252;
$orange: #fd7e14;
$yellow: #fcc419;
$green: #40c057;
$blue: #339af0;
$indigo: #5c7cfa;
$violet: #7950f2;

// 믹스인 만들기 (재사용되는 스타일 블록을 함수처럼 사용 할 수 있음)
@mixin square($size) {
  $calculated: 32px * $size;
  width: $calculated;
  height: $calculated;
}
.SassComponent {
  display: flex;
  .box {
    background: red; // 일반 CSS 에선 .SassComponent .box 와 마찬가지
    cursor: pointer;
    transition: all 0.3s ease-in;
    &.red { // .red 클래스가 .box 와 함께 사용 됐을 때
      background: $red;
      @include square(1);
    }
    &.orange {
      background: $orange;
      @include square(2);
    }
    &.yellow {
      background: $yellow;
      @include square(3);
    }
    &.green {
      background: $green;
      @include square(4);
    }
    &.blue {
      background: $blue;
      @include square(5);
    }
    &.indigo {
      background: $indigo;
      @include square(6);
    }
    &.violet {
      background: $violet;
      @include square(7);
    }
    &:hover {
      // .box 에 마우스 올렸을때
      background: black;
    }
  }
}

```
```javascript
import React from 'react';
import './SassComponent.scss';
const SassComponent = () => {
  return (
    <div className="SassComponent">
      <div className="box red" />
      <div className="box orange" />
      <div className="box yellow" />
      <div className="box green" />
      <div className="box blue" />
      <div className="box indigo" />
      <div className="box violet" />
    </div>
  );
};
export default SassComponent;
```

### 유틸함수 분리
- 여러 파일에서 사용될 수 있는 Sass변수 및 믹스인은 다른파일로 따로 분리하여 작성   

```css
// 변수 사용하기
$red: #fa5252;
$orange: #fd7e14;
$yellow: #fcc419;
$green: #40c057;
$blue: #339af0;
$indigo: #5c7cfa;
$violet: #7950f2;

// 믹스인 만들기 (재사용되는 스타일 블록을 함수처럼 사용 할 수 있음)
@mixin square($size) {
  $calculated: 32px * $size;
  width: $calculated;
  height: $calculated;
}
```

```css
@import "./styles/utils";

.SassComponent {
  display: flex;
  .box {
    background: red; // 일반 CSS 에선 .SassComponent .box 와 마찬가지
    cursor: pointer;
    transition: all 0.3s ease-in;
```

### node_modules에서 라이브러리 불러오기

- @import '~libarary/styles';

```css
@import "~include-media/dist/include-media";
@import "~open-color/open-color";

// 변수 사용하기
$red: #fa5252;
$orange: #fd7e14;
```
```javascript
.SassComponent {
  display: flex;
  background: $oc-gray-2;
  @include media("<768px") {
    background: $oc-gray-9;
  }

```

- 물결표시는 node_modules에서 디렉토리를 탐지하게 해준다   

***

## styled-components
- CSS-in-JS
- 자바스크립트 파일안에 스타일을 선언하는 방식
- https://github.com/MicheleBertoli/css-in-js
- styled-components는 CSS-in-JS 라이브러리 중 하나
  - yarn add styled-components
  - npm install styled-components
- 자바스크립트 파일 하나에 스타일까지 작성할 수 있기 때문에 따로 css파일을 만들지 않아도 된다   

```javascript
import React from "react";
import styled, { css } from "styled-components";
const Box = styled.div`
/* props로 넣어준 값을 직접 전달해줄수 있다. */
  background: ${(props) => props.color || "blue"};
  padding: 1rem;
  display: flex;
`;

const Button = styled.button`
  background: #fff;
  color: #333;
  border-radius: 0.5rem;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  font-size: 1rem;
  font-weight: 600;
/* & 문자를 사용하여 Sass처럼 자기 자신 선택 가능*/
  &:hover {
    background: rgba(255, 255, 255, 0.9);
  }
/* 다음 코드는 inverted 값이 true일 때 특정 스타일을 부여해 준다. */
  ${(props) =>
    props.inverted &&
    css`
      background: none;
      border: 2px solid white;
      color: white;
      &:hover {
        background: white;
        color: black;
      }
    `};
  & + button {
    margin-left: 1rem;
  }
`;

const StyledComponent = () => (
  <Box color="black">
    <Button>안녕하세요.</Button>
    <Button inverted={true}>테두리만</Button>
  </Box>
);
export default StyledComponent
```

- props값으로 전달해주는 값을 쉽게 스타일에 적용할 수 있다
- Tagged 템플릿 리터럴
  - `사이에 css정보 삽입
  - 템플릿 사이에 들어가는 자바스크립트 객체나 함수의 원본값을 그대로 추출 가능
- 스타일링된 엘리먼트
  - 상단에서 styled를 불러오고, styled.태그명으로 구현
  - 해당 스타일이 적용된 태그로 이루어진 리액트 컴포넌트 생성
- 스타일에서 props 조회
  - background: ${(props) => props.color || "blue"};
  - props.color를 통해 props로 넘오언 color 값 적용
  - 없을 경우 기본값 blue
- props에 따른 조건부 스타일링
