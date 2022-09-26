---
layout: post
title:  "9. ref"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 일반 HTML 태그에서의 id와 비슷
- id
  - 특정 DOM 요소에 id를 달면, css나 자바스크립트에서 해당 id를 찾아 작업 가능
- ref
  - 리액트 프로젝트 내부에서 DOM에 이름을 다는 방법
- 사용 이유
  - JSX 안에서 DOM에 id를 달면 렌더링 시 그대로 전달된다
    - 같은 컴포넌트를 여러번 사용 시 중복id를 가진 DOM이 여러개 생긴다
  - ref는 전역적으로 작동하지 않고, 컴포넌트 내부에서만 작동하기 때문에 사용
  - 클래스형 컴포넌트 : createRef : 리액트 내장함수
  - 함수형 컴포넌트 : useRef() : 리액트 훅   

***

## 클래스형 컴포넌트
### 콜백 함수를 통한 ref 설정
- ref를 달 요소에 ref라는 콜백함수를 props로 전달
  - 이 콜백함수는 ref값을 파라미터로 전달받는다
  - 함수내부에서 파라미터로 받은 ref를 컴포넌트의 멤버변수로 설정
  > <input ref={(ref)=>(this.input=ref}}/\>   
  - 앞으로 this.input은 input요소의 DOM을 가리킨다
  - ref의 이름은 원하는 것으로 자유롭게 지정 가능
  - this.superman = ref 등   

### createRef를 통한 ref 설정
- 16.3v 부터 도입된 내장함수   

```javascript
import React, { Component } from 'react';
class RefSample extends Component {
  input = React.createRef();
  handleFocus = () => {
    this.input.current.focus();
  };
  render() {
    return (
      <div>
        <input ref={this.input} />
      </div>
    );
  }
}
export default RefSample;
```


- 컴포넌트 멤버변수로 React.createRef()를 담는다
- 해당 멤버변수를 ref를 달 요소에 ref props로 넣어준다   

```javascript
handleButtonClick = () => {
  this.setState({
    clicked: true,
    validated: this.state.password === "0000",
  });
  this.input.focus();
};
```

- this.input이 현재 가리키고 있는 input으로 포커스 이동   

***

## 함수형 컴포넌트
- useRef() hook 이용
- useRef를 통해 만든 객체 안의 current 값이 실제 엘리먼트를 가리킨다   

```javascript
//RefSample
import React, { useRef } from 'react';
const RefSample =()=> {
  const inputRef = useRef();
  const handleFocus = () => {
    inputRef.current.focus();
  };
  return (
    <div>
      <input ref={inputRef} />
      <button onClick={handleFocus}>포커스 주기</button>
    </div>
  );
}


export default RefSample;


//ValidationSample
import React, { useState, useRef } from 'react';
import '../ValidationSample.css';
const ValidationSample = () => {
  const [password, setPassword] = useState('');
  const [clicked, setClicked] = useState(false);
  const [validated, setValidated] = useState(false);
  let inputRef = useRef();
  const handleChange = e => { 
    setPassword(e.target.value); 
  };
  const handleButtonClick = () => {
    setClicked(true);
    setValidated(password === '0000');
    inputRef.current.focus();
  };
  return (
    <div>
      <input ref={inputRef}
        type="password"
        value={password}
        onChange={handleChange}
      className={ clicked? (validated? 'success' : 'failure' ): '' } /> 
      <button onClick={handleButtonClick}>검증하기</button>
    </div>
  ); 
} 
export default ValidationSample;
```
