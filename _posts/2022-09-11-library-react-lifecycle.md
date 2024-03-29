---
layout: post
title:  "11. 라이프사이클"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---


- 모든 컴포넌트에는 라이프사이클이 존재
  - 시작 : 페이지에 렌더링 되기전 준비과정
  - 끝 : 페이지에서 사라질 때
- 컴포넌트를 처음 렌더링하거나 업데이트 할때, 특정 작업을 처리해야할 경우
  - 라이프사이클 메서드 사용
- 라이프사이클 메서드
  - 클래스형 컴포넌트에서만 사용 가능
  - 함수형 컴포넌트에서는 Hooks 기능을 사용하여 비슷한 작업 처리
- 종류
  - 3가지 카테고리 (마운트, 업데이트, 언마운트)   
![1](https://user-images.githubusercontent.com/99188096/190939464-7b3d73c0-1adc-4f23-8dcd-a7ecc2d42563.PNG)   


- 마운트
  - DOM이 생성되고 웹 브라우저상에 나타나는 것
  - 호출되는 메서드
    - constructor
      - 컴포넌트를 새로 만들 때마다 호출되는 클래스 생성자 메서드
    - getDerivedStateFromProps
      - props에 있는 값을 state에 넣을 때 사용하는 메서드
    - render
      - 준비한 UI를 렌더링
    - **componentDidMount**
      - 컴포넌트가 웹브라우저상에 나타난 후 호출하는 메서드
- 업데이트
  - 4가지 경우에 컴포넌트 업데이트
    - props가 바뀔 때
    - state가 바뀔 때 (setState 등)
    - 부모 컴포넌트가 리렌더링될 때
    - this.forceUpdate로 강제로 렌더링을 트리거할 때
  - 메서드
    - getDerivedStateFromProps
      - 마운트 과정에서도 호출된 메서드
      - 업데이트 시작하기전에도 호출
      - props의 변화에 따라 state 값에도 변화를 주고 싶을 때 사용
    - shouldComponentUpdate
      - 컴포넌트의 리린더링 여부 결정
      - boolean값 반환
      - true : 다음 라이프사이클 메서드 계속 실행
      - false : 작업중지, 컴포넌트 리렌더링하지 않음
      - forceUpdate 함수가 호출되면 이 과정을 생략하고 render 함수 호출
    - render
      - 컴포넌트를 리렌더링
    - getSnapshotBeforeUpdate
      - 컴포넌트 변화를 DOM에 반영하기 직전에 호출되는 메서드
    - **componentDidUpdate**
      - 컴포넌트의 업데이트 작업이 끝난 후 호출하는 메서드
- 언마운트
  - 컴포넌트를 DOM에서 제거하는 것
  - 메서드
    - **componentWillUnmount**
      - 컴포넌트가 웹브라우저상에서 사라지기 전에 호출하는 메서드

***

## 메서드
- render() 함수
  - 컴포넌트의 모양을 정의
  - 라이프사이클 메서드 중 유일한 필수 메서드
  - this.props와 this.state 접근 가능
  - 이 메서드 안에서는 이벤트 설정이 아닌곳에서는 setState 사용 X, DOM 접근 X
    - componentDidMount에서 처리해야 한다   

- constructor(props){}
  - 컴포넌트의 생성자 메서드
  - 컴포넌트를 만들 때 처음으로 실행
  - 초기 state 정의 가능   

- getDerivedStateFromProps
  - props로 받아온 값을 state에 동기화시키는 용도
  - 컴포넌트가 마운트될 때와 업데이트될 때 호출

- componentDidMount
  - 컴포넌트를 만들고, 첫 렌더링을 다 마친 후 실행
  - 다른 자바스크립트 라이브러리나 프레임워크 함수 호출 가능
  - 이벤트 등록, setTimeout, 네트워크 요청 등의 비동기 작업처리 가능   

- shouldComponentUpdate(nextProps, nextState){}
  - props나 state를 변경했을 때, 리렌더링을 시작할지 여부를 지정하는 메서드
  - 메서드를 따로 생성하지 않으면 기본적으로 true 반환
  - 현재 props, state는 this.props, this,state로 접근
  - 새로 설정된 props, state는 nextProps, nextState로 접근

- getSnapshotBeforeUpdate
  - render에서 만들어진 결과물이 브라우저에 실제로 반영되기 직전 호출
  - 반환값은 componentDidUpdate에서 세번째 매개변수인 snopshot 값으로 전달받을 수 있다
  - 업데이트 하기 직전의 값을 참고할 일이 있을 때 활용   

- componentDidUpdate
  - componentdidUpdate(prevProps, prevState, snapshot){}
  - 리렌더링 완료 후 실행
  - 업데이트가 끝난 직후로, DOM 관련 처리 가능
  - prevProps나 prevState를 사용하여 컴포넌트의 이전 데이터에 접근 가능
  - getSnapshotBeforeUpdate에서 반환한 값이 있으면 snapshot값을 전달받을 수 있다

- componentWillUnmout
  - 컴포넌트를 DOM에서 제거할 때 실행
  - componentDidMount에서 등록한 이벤트, 타이머, 직접 생성한 DOM이 있다면 여기서 제거작업 실행

- componentDidCatch
  - 컴포넌트 렌더링 도중 에러발생 시 오류UI를 보여줄 수 있게 해준다   

***

## ex
### LifeCycleSample.js   

```javascript
import React, { Component } from 'react';

class LifeCycleSample extends Component {
  state = {
    number: 0,
    color: null
  };
  myRef = null; // ref를 설정할 부분
  constructor(props) {
    super(props);
    console.log('constructor');
  }
  static getDerivedStateFromProps(nextProps, prevState) {
    console.log('getDerivedStateFromProps');
    if (nextProps.color !== prevState.color) {
      return { color: nextProps.color };
    }
    return null;
  }
  componentDidMount() {
    console.log('componentDidMount');
  }
  shouldComponentUpdate(nextProps, nextState) {
    console.log('shouldComponentUpdate', nextProps, nextState); // 숫자의 마지막 자리가 4면 리렌더링하지 않음
    return nextState.number % 10 !== 4;
  }
  componentWillUnmount() {
    console.log('componentWillUnmount');
  }
  handleClick = () => {
    this.setState({
      number: this.state.number + 1
    });
  };

  getSnapshotBeforeUpdate(prevProps, prevState) {
    console.log('getSnapshotBeforeUpdate');
    if (prevProps.color !== this.props.color) {
      return this.myRef.style.color;
    }
    return null;
  }
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log('componentDidUpdate', prevProps, prevState);
    if (snapshot) {
      console.log('업데이트되기 직전 색상: ', snapshot);
    }
  }
  render() {
    console.log('render');
    const style = {
      color: this.props.color
    };
    return (
      <div>
        {/* {this.props.missing.value} */}
        <h1 style={style} ref={ref => (this.myRef = ref)}>
          {this.state.number}
        </h1>
        <p>color: {this.state.color}</p>
        <button onClick={this.handleClick}>더하기</button>
      </div>
    );
  }
}
export default LifeCycleSample;
```

- 각 라이프사이클 메서드 실행마다 콘솔 디버거 기록
- 부모 컴포넌트에서 props로 색상을 받아 버튼을 누르면 state.number 값을 1씩 더한다
- getDerivedStateFromProps
  - 부모에게서 받은 color 값을 state에 동기화
- getSnapshotBeforeUpdate
  - DOM에 변화가 일어나기 직전의 색상 속성을 snapshot 값으로 반환
  - componentDidUpdate에서 조회 가능
- shouldComponentUpdate
  - state.number 1의자리수가 4이면 리렌더링 취소   

### App.js   
```javascript
import React, { Component } from "react";
import LifeCycleSample from "./LifeCycleSample";
//import ErrorBoundary from './ErrorBoundary';
// 랜덤 색상을 생성합니다.

function getRandomColor() {
  return "#" + Math.floor(Math.random() * 16777215).toString(16);
}
class App extends Component {
  state = {
    color: "#000000",
  };
  handleClick = () => {
    this.setState({
      color: getRandomColor(),
    });
  };
  render() {
    return (
      <div>
        <button onClick={this.handleClick}>랜덤 색상</button>
        <LifeCycleSample color={this.state.color} />
      </div>
    );
  }
}
export default App;
```

- getRandomColor 함수는 state의 color값을 랜덤 색상으로 설정
- 버튼을 렌더링하고, 누를때마다 handleClick 메서드 호출
  - 불러온 LifeCycleSample 컴포넌트에 color 값을 props로 설정
- 1의자리수가 4일때는 업데이트를 취소하는지 확인
