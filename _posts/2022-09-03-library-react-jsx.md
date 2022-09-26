---
layout: post
title:  "3. JSX"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 자바스크립트의 확장 문법
- 번들링되는 과정에서 자바스크립트 형태의 코드로 변환된다   
- jsx   

```javascript
function App(){
  return (
    <div>
      Hello <b>react</b>
    </div>
  );
}
```   
- 변환 후   

```javascript
function App(){
  return React.createElement("div",null,"Hello", React.createElement("b",null,"react"));
}
```


## 특징
#### 감싸인 요소
- 컴포넌트에 여러 요소가 있다면, 반드시 부모요소 하나로 감싼 후 리턴해야 한다
  - 컴포넌트 내부는 하나의 DOM 트리 구조로 이루어져야 한다는 규칙
- v16 이후부터는 div태그 대신 Fragment 기능 사용 가능   

```javascript 
function App() {
  return (
    <h1>에러</h1>
    <h2>에러</h2>
  );
}
export default App;

function App() {
  return (
    <div>
      <h1>정상</h1>
      <h2>정상</h2>
    </div>
  );
}
export default App;


import { Fragment } from "react";
function App() {
  return (
    <Fragment>
      <h1>정상</h1>
      <h2>정상</h2>
    </Fragment>
  );
}
export default App;

function App() {
  return (
    <>
      <h1>정상</h1>
      <h2>정상</h2>
    <>
  );
}
export default App;

```

#### 자바스크립트 표현
- js 안에서 자바스크립트 표현식 사용 가능
- jsx 내부에서 코드를 {}로 감싼다   

```javascript
function App() {
  const name='React';
  return (
    <>
      <h1>{name} 이름</h1>
    </>
  );
}
```
- es6 이전에는 변수 var 사용, scope 함수 단위
- es6 이후 scope 블록 단위 변수
  - const : 한번 지정하면 변경할 수 없는 상수 선언 시
  - let : 동적인 값을 담을 수 있는 변수 선언 시   
  
#### 조건부 연산자
- jsx 내부의 자바스크립트 표현식에는 if문 사용 불가
  - jsx 외부에서 미리 if문을 사용하여 값 할당
  - jsx 내부 {}안에서 삼항연산자 사용   
  
```javascript
function App() {
  const name='React';
  return (
    <div>
      {name === 'React' ? (<h1>참.</h1>) : (<h2>거짓.</h2>)}
    </div>
  );
}
```   

#### AND 연산자 조건부 렌더링   
- 조건이 참일 경우 렌더링, 거짓일 경우 렌더링하지 않는다   

```javascript
//조건부 연산자일 경우
function App() {
  const name='Reacto';
  return <div>{name === 'React' ? <h1>참.</h1> : null}</div>;
}

//&&연산자일 경우
function App() {
  const name='Reacto';
  return <div>{name === 'React' && <h1>참.</h1>}</div>;
}

```

#### OR 연산자 조건부 렌더링
- 값이 undefined일 경우 렌더링한다   

```javascript
function App() {
  const name=undefined;
  return name || '값이 undefined 입니다.';
}
```   

#### 인라인 스타일링
- jsx 내에서 DOM에 css를 적용할 때, 객체형태로 적용해야 한다   

```javascript
function App() {
  const name='리액트';
  const style={
    backgroundColor: 'black',
    color:'aqua',
    fontSize: '48px',
    fontWeight:'bold',
    padding:16
  };
  return <div style={style}>{name}</div>;
}


function App() {
const name='리액트';
  return <div style={
    {
      backgroundColor: 'black',
      color:'aqua',
      fontSize: '48px',
      fontWeight:'bold',
      padding:16
    }
  }>{name}</div>;
}
```   

#### class 대신 className   
```css
.react {
  background: aqua;
  color: black;
  font
  -size: 48px;
  font
  -weight: bold;
  padding: 16px;
}
```   
```javascript
import './App.css';
function App(){
  const name='리액트';
  return <div className="react">{name}</div>;
}
```   

#### 주석
- {/* 주석내용 */}
