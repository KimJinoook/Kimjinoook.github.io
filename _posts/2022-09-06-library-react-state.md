---
layout: post
title:  "6. state"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- props
  - 컴포넌트가 사용되는 과정에서 부모 컴포넌트가 설정하는 값
  - 컴포넌트 자신은 해당 props를 읽기전용으로만 사용 가능
  - props의 값을 바꾸려면 부모컴포넌트에서 바꾸어야 한다
- state
  - 컴포넌트 내부에서 바뀔 수 있는 값
  - 동적 데이터를 다룰 때 사용
  - 종류
    - 클래스형 컴포넌트의 state
    - 함수형 컴포넌트에서 useState 함수를 통해 사용하는 state   

***

## 클래스형 컴포넌트의 state   
```javascript
import React, { Component } from 'react';

class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = { number: 0 };
  }

  render() {
    const { number } = this.state;
    return (
      <div>
        <h1>{number}</h1>
        <button
        onClick={() => {
          this.setState({ number: number + 1 });
        }}
        >+1</button>
      </div>
    );
  }
}
export default Counter;

```

- 컴포넌트에 state를 설정할때, constructor 메서드를 이용
- 컴포넌트의 state는 객체형식이어야 한다
- render함수에서 현재 state를 조회할 때는 this.state을 조회   

#### setState에 함수인자 전달   

```javascript
<button
  onClick={() => {
    this.setState({ number: number + 1 });
    this.setState({ number: this.state.number + 1 });
  }}
>
```
- this.setState는 값을 업데이트할 때 상태가 비동기적으로 업데이트
  - setState를 두번 호출했지만 숫자는 1만 늘어난다.
  - 값이 비동기적으로 변하기때문에, 바로 바뀌지 않기 때문   

```javascript
<button
  onClick={() => {
    this.setState((prevState) => {
      return {
        number: prevState.number + 1,
      };
    });
    this.setState((prevState) => ({
      number: prevState.number + 1 //함수에서 바로 객체를 반환한다는 의미
    }));
  }}
>
```
- setState에 객체 대신 함수인자를 전달
- 함수에서 객체를 바로 반환하기 때문에, 숫자 2씩 증가   

#### setState 후 특정 작업 수행   

```javascript
<button
  onClick={() => {
    this.setState(
      {
        number: number + 1,
      },
      () => {
        console.log('setState 호출됨');
        console.log(this.state);
      }
    );
  }}
>
```
- setState의 두번째 파라미터로 콜백함수 등록
  - setState의 작업이 끝난 후, 해당 콜백함수 수행   

***

## 함수형 컴포넌트의 state
- 리액트 16.8 이전까지는 함수형 컴포넌트에서 state 사용 불가
- 이 후 부터는 useState 함수를 이용해 state 사용
  - 이 과정에서 Hooks 사용

```javascript
import React, { useState } from 'react';
const Say = () => {
  const [message, setMessage] = useState('');
  const onClickEnter = () => setMessage('안녕하세요');
  const onClickLeave = () => setMessage('안녕히 가세요');
  return (
    <div>
      <button onClick={onClickEnter}>입장</button>
      <button onClick={onClickLeave}>퇴장</button>
      <h1>{message}</h1>
    </div>
  );
};
export default Say;

```
- useState 함수의 인자에는 상태의 초기값을 넣어준다
  - 클래스형 컴포넌트에서의 state 초기값 : 객체
  - 함수형 컴포넌트에서는 객체가 아니어도 상관없다
- 함수를 호출하면 배열 반환
  - 첫번째 원소 : 현재 상태
  - 두번째 원소 : 상태를 바꾸어주는 함수 (setter 함수)
  - 배열 비구조화 할당을 통해 이름을 자유롭게 정할 수 있다
    - const\[message,setMessage] = useState('')
- state 사용 이유
  - 변수가 변경될 때 자동으로 관련 HTML을 재렌더링하려면, 일반 변수 대신 state에 저장해 데이터바인딩
    - 리액트는 state가 변경되면 state가 포함된 HTML을 자동으로 재렌더링
    - 일반 변수는 병경이 발생해도 자동으로 재렌더링 해주지 않는다   

#### 주의사항
- state의 값을 바꿀 때는 setState나 useState를 통해 전달받은 세터함수 사용   

```javascript
//잘못된예 - 클래스형 컴포넌트
this.state.number = this.state.number+1;
this.state.array = this.array.push(2);

//잘못된예 - 함수형 컴포넌트
const[object, setObject] = useState({a:1, b:1});
object.b=2;
```

#### 배열이나 객체의 업데이트
- 배열이나 객체의 사본을 만든다
  - spread(...)연산자를 이용
- 사본의 값을 업데이트한다
- 사본의 상태를 세터함수를 통해 업데이트 한다   

```javascript
//객체 다루기
const object = { a:1, b:2, c:3 };
const nextObject = {...object, b:3}; //사본 만든 후 b값만 덮어씀

//배열 다루기
const array = [
  {id: 1, value:true},
  {id: 2, value:true},
  {id: 3, value:false}
];
let nextArray = array.concat({id:4}); // 새 항목 추가
nextArray.filter(item => item.id != = 2); // id가 2인 항목 제거
nextArray.map(item => (item.id === 1 ? {...item,value:false) : item)); // id가 1인 항목의 value를 false로 설정
```

***

## 자식 컴포넌트에서 부모 state 이용
- 자신의 스테이트처럼 접근할 수 없음
- props를 이용해 부모 컴포넌트에서 state를 전달
- 자식컴포넌트에서 {props.state 이름} 으로 접근   

#### 잘못된 예   
```javascript
function App (){
  let [title, setTitle] = useState(['react 책 추천', '점심메뉴추천', 'node.js스터디']);
  return (
    <div>
      <Detail/>
    </div>
  )
}


function Detail(){
  return (
    <div>
      <h2>제목 { title[0] }</h2>
      <p>작성자</p>
      <p>내용</p>
    </div>
  )
}
//title 변수가 정의되지 않았다고 에러
```

#### props 이용
```javascript
import logo from "./logo.svg";
import "./App.css";
import React, { useState } from 'react';
import Detail from './components/Detail';
const App = () => {
  let [title, setTitle] = useState(['react 책 추천', '점심메뉴추천', 'node.js스터디']);
  return (
    <div> 
      <Detail title={title} />
    </div>
  )
};
export default App;


function Detail(props){
  return (
    <div>
      <h2>제목 { props.title[0] }</h2>
      <p>작성자</p>
      <p>내용</p>
    </div>
  )
}

```
