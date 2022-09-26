---
layout: post
title:  "5. props"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 컴포넌트에서 컴포넌트로 전달하는 데이터
- 리액트 컴포넌트로 넘어가는 매개변수
- 컴포넌트를 효율적으로 재사용할 수 있다
- 데이터는 문자열을 제외하고 {}로 감싼다   

```javascript
function App() {
  return (
    <div>
      <h1>Hello</h1>
      <Food fav="kimchi" />
      <Test something={true} papapapa={['hello',1,2,3,4, true]}/>
    </div>
  );
}
```

- 데이터를 받는 컴포넌트   

```javascript
function Food(args){
  console.log(args);
  return <h1>I like potato</h1>;
}
```

- 받은 데이터를 사용할 때   

```javascript
function Food(args){
  console.log(args);
  return <h1>I like {args.fav}</h1>;
}
```   

#### 컴포넌트의 재사용   
```javascript
function Food({fav}){
  return <h1>I like {fav}</h1>;
}
function App() {
  return (
    <div>
      <h1>Hello</h1>
      <Food fav="kimchi" />
      <Food fav="ramen" />
      <Food fav="samgiopsal" />
      <Food fav="chukumi" />
    </div>
  );
}
```
- Food 컴포넌트에 서로다른 props를 넘겨주며 불러온다
  - Food 컴포넌트의 재사용   

#### props 기본값 설정
- props값을 따로 넘겨주지 않았을 때 기본으로 보여줄 값을 설정   

```javascript
const App = () => {
  return <TestC/>;
);

const Test = (props)=>{
  return {props.name}
}
Test.defaultProps = {
  name:'기본';
}
```

#### 태그 사이를 보여주는 children   
```javascript
const App = () => {
  return <MyComponent>리액트</MyComponent>;
};
```
```javascript
const MyComponent = (props) => {
  return (
    <div>
      안녕하세요 제 이름은 {props.name}입니다. <br />
      children 값은 {props.children} 입니다.
    </div>
  );
};
```
- 컴포넌트 태그 사이의 값 리액트를 출력하기 위해서 props.children 값 사용   

***

#### 구조분해 할당
```javascript
function Food({fav}){ 
  return <h1>I like {fav}</h1>; 
}

function Food(props){
  { fav }=props;
  return <h1>I like {fav}</h1>; 
}

const MyComponent = (props) => {
  const { name, children } = props;
  return (
    <div>
      안녕하세요 제 이름은 {name}입니다.
      <br />
      children 값은 {children} 입니다.
    </div>
  );
};

```
- 매번 props 내의 값을 조회할 때마다 props.name과 같이 쓰기 번거롭다
- 구조분해(비구조화)문법을 통해 내부 값을 바로 추출
- 함수의 파라미터가 객체라면, 그 값을 비구조화해 사용   

#### propTypes   
- 컴포넌트의 필수 props를 지정하고나 type를 지정   

```javascript
const MyComponent = ({ name, favoriteNumber, children }) => {
  return (
    <div>
      안녕하세요 제 이름은 {name}입니다. <br />
      children 값은 {children} 입니다. <br />
      제가 좋아하는 숫자는 {favoriteNumber} 입니다.
    </div>
  );
};
MyComponent.propTypes = {
  name: PropTypes.string,
  favoriteNumber: PropTypes.number.isRequired,
};
```
- propType 임포트
- PropTypes.타입 : 넘어온 데이터가 해당 타입이 아닐경우 경고 발생
- isRequired : 데이터가 넘어오지 않으면 경고발생
- propType의 종류
  - array : 배열
  - arrayOf(PropTypes.타입) : 특정 PropType으로 이루어진 배열
    - arrayOf(PropTypes.number) : 숫자로 이루어진 배열
  - bool
  - func : 함수
  - number
  - object
  - string
  - symbol : es6의 symbol
  - node : 렌더링할 수 있는 모든 것
  - instanceOf(클래스) : 특정 클래스의 인스턴스
  - oneOf(\['a','1']) : 주어진 배열 요소 중 하나
  - oneOfType(\[PropTypes.string, PropTypes.number]) : 주어진 배열안의 타입중 하나
  - objectOf(\[React.PropTypes.number]) : 객체의 모든 키 값이 인자로 주어진 PropType인 객체
  - shape({name: PropTypes.string, num: PropTypes.number}) : 주어진 스키마를 가진 객체
  - any : 아무 종류   

#### 클래스형 컴포넌트에서의 props   
```javascript
import React, { Component } from 'react';
import PropTypes from 'prop-types';

class MyComponent extends Component {
  render() {
    const { name, favoriteNumber, children } = this.props; // 비구조화 할당
    return (
      <div>
        안녕하세요, 제 이름은 {name} 입니다. <br />
        children 값은 {children} 입니다. <br />
        제가 좋아하는 숫자는 {favoriteNumber}입니다.
      </div>
    );
  }
}

MyComponent.defaultProps = {
  name: '기본 이름',
};

MyComponent.propTypes = {
  name: PropTypes.string,
  favoriteNumber: PropTypes.number.isRequired,
};




class MyComponent extends Component {
  static defaultProps = {
  name: '기본 이름',
};

static propTypes = {
  name: PropTypes.string,
  favoriteNumber: PropTypes.number.isRequired,
};

render() { ... }
```
