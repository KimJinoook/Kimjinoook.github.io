---
layout: post
title:  "10. map함수"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 자바스크립트 배열 객체의 내장함수인 map 함수
- 반복되는 컴포넌트를 렌더링할 수 있다
- 파라미터로 전달된 함수를 사용해, 배열 내 각 요소를 규칙에 따라 변환
  - 결과로 새로운 배열 생성
- 문법
  - arr.map(callback,\[thisArg])
  - 파라미터 callback
    - 새로운 배열의 요소를 생성하는 함수, 파라미터는 세가지
    - currentValue : 현재 처리하고 있는 요소
    - index : 현재 처리하고 있는 요소의 index 값
    - array : 현재 처리하고 있는 원본 배열
  - thisArg(선택항목) : callback 함수 내부에서 사용할 this 레퍼런스   

```javascript
var numbers=[1,2,3,4,5];
var processed = numbers.map(function(num){
  return num*num;
});
console.log(processed);

//결과 : [1,4,9,16,25]


//es6 문법
const nums = [1,2,3,4,5,6];
const result = nums.map(num => num*num);
console.log(result);
```

### 데이터 배열을 컴포넌트 배열로 변환   
#### 컴포넌트의 반복   
```javascript
mport React from "react";
const IterationSample = () => {
  return (
    <ul>
      <li>눈사람</li>
      <li>얼음</li>
      <li>눈</li>
      <li>바람</li>
    </ul>
  );
};
export default IterationSample;
```
- 코드의 리스트 태그가 반복된다
- map 함수를 이용해 관리 가능   



```javascript
import React from 'react';
const TestCom = () => {
  const names = ['눈사람', '얼음', '눈', '바람'];
  const nameList = names.map(name => <li>{name}</li>);
  return <ul>{nameList}</ul>;
};
export default TestCom;
```
- 문자열로 구성된 배열을 선언
- 그 배열값을 사용하여 <li>...</li> jsx코드로 된 배열을 새로 생성
- 그 후 nameList에 담는다
- map 함수에서 jsx를 작성할 때는 DOM 요소 가능, 컴포넌트 사용 가능   

***

## key
![1](https://user-images.githubusercontent.com/99188096/190935823-2e1b78d8-84b2-4d50-a653-ef1b22bc7408.PNG)   

- 위 예제를 실행하면 key prop가 없다는 경고 표시
- 리액트는 컴포넌트 배열 렌더링 시, 어떤 원소에 변동이 있었는지 알아내려한다
  - key 사용
- 유동적인 데이터를 다룰 때는 원소를 새로 생성, 제거, 수정 가능
- key가 없으면 VirtualDOM을 비교하는 과정에서 리스트를 순차적으로 비교하며 변화 감지
  - key가 있다면 어떤 변화가 있는지 더 빠르게 알아낼 수 있다

### 설정
- map 함수의 인자로 전달되는 함수 내부에서 컴포넌트 props를 설정하듯 설정
- key 값은 유일해야 한다
  - 데이터가 가진 고유값을 key값으로 설정 (no, id 등)   

```javascript
const articleList = articles.map((article) => 
  <Article
    title={article.title}
    writer={article.writer}
  key={article.id} />
);
```

- 고유번호가 없는 경우 map 함수에 전달되는 콜백함수의 인수인 index값을 사용   

```javascript
import React from 'react';
const IterationSample = () => {
  const names = ['눈사람', '얼음', '눈', '바람'];
  const nameList = names.map((name, index) => <li key={index}>{name}</li>);
  return <ul>{nameList}</ul>;
};
export default IterationSample;
```

***

## 유동적인 데이터 렌더링
- 고정된 배열이 아닌 동적인 배열 렌더링 시
  - index값을 key로 사용하면 리렌더링이 비효율적
- 이러한 상황에서는
  - 초기 상태 설정
  - 데이터 추가 기능 구현
  - 데이터 제거 기능 구현   

### 초기상태 설정
- useState를 사용하여 상태 설정
  - 데이터배열
  - 텍스트를 입력할 수 있는 input 상태
  - 데이터 배열에서 새로운 항목을 추가할 때 사용할 고유 id를 위한 상태   

```javascript
import React, {useState} from 'react';

const IterationSample = () => {
  const [names, setNames] = useState([
    {id:1, text:'눈사람'}, 
    {id:2, text:'얼음'}, 
    {id:3, text:'눈'}, 
    {id:4, text:'바람'}
  ]);

  const [inputText, setInputText] = useState('');
  const [nextId, setNextId] = useState(5); //새로운 항목을 추가할 때 사용할 id

  const nameList = names.map(name => <li key={name.id}>{name.text}</li>);
    return <ul>{nameList}</ul>;
};
export default IterationSample;
```

### 데이터 추가기능 구현   
```javascript
import React, { useState } from "react";
const IterationSample = () => {
  const [names, setNames] = useState([
    { id: 1, text: "눈사람" },
    { id: 2, text: "얼음" },
    { id: 3, text: "눈" },
    { id: 4, text: "바람" },
  ]);
  const [inputText, setInputText] = useState("");
  const [nextId, setNextId] = useState(5); //새로운 항목을 추가할 때 사용할 id
  const onChange = (e) => setInputText(e.target.value);
  const onClick = () => {
    const nextNames = names.concat({
      id: nextId, //nextId 값을 id로 설정
      text: inputText,
    });
    setNextId(nextId + 1); //nextId값에 1을 더해 준다
    setNames(nextNames); //names 값을 업데이트한다
    setInputText(""); //inputText를 비운다
  };
  const nameList = names.map((name) => <li key={name.id}>{name.text}</li>);
  return (
    <>
      <input value={inputText} onChange={onChange} />
      <button onClick={onClick}>추가</button>
      <ul>{nameList}</ul>
    </>
  );
};
export default IterationSample;
```

- 배열에 새 항목을 추가할 때, 배열의 push가 아닌 concat 사용
  - push : 기존 배열 자체를 변경
  - concat : 새로운 배열을 생성
  - 불변성 유지를 위해 concat 사용
  - 새로운 배열 생성 후 이를 새로운 상태로 설정
    - 리액트 컴포넌트의 성능 최적화   

### 데이터 제거 기능 구현   

- 불변성을 유지하면서 특정 항목을 지울 때는 filter 사용   

```javascript
const numbers = [1,2,3,4,5,6];
const result = numbers.filter(num => num>3);
//결과 : [4,5,6]

const numbers = [1,2,3,4,5,6];
const result = numbers.filter(num => num!==3); //특정 배열에서 특정 원소만 제외시킬 수도 있다
//결과 : [1,2,4,5,6]
```

```javascript
import React, { useState } from "react";
const IterationSample = () => {
  const [names, setNames] = useState([
    { id: 1, text: "눈사람" },
    { id: 2, text: "얼음" },
    { id: 3, text: "눈" },
    { id: 4, text: "바람" },
  ]);
  const [inputText, setInputText] = useState("");
  const [nextId, setNextId] = useState(5); //새로운 항목을 추가할 때 사용할 id
  const onChange = (e) => setInputText(e.target.value);
  const onClick = () => {
    const nextNames = names.concat({
      id: nextId, //nextId 값을 id로 설정
      text: inputText,
    });
    setNextId(nextId + 1); //nextId값에 1을 더해 준다
    setNames(nextNames); //names 값을 업데이트한다
    setInputText(""); //inputText를 비운다
  };
  const onRemove = (id) => {
    const nextNames = names.filter((name) => name.id !== id);
    setNames(nextNames);
  };
  const nameList = names.map((name) => (
    <li key={name.id} onDoubleClick={() => onRemove(name.id)}>
      {name.text}
    </li>
  ));
  return (
    <>
      <input value={inputText} onChange={onChange} />
      <button onClick={onClick}>추가</button>
      <ul>{nameList}</ul>
    </>
  );
};
export default IterationSample;

```
