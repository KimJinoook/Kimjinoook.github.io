---
layout: post
title:  "12. Hooks"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

- 함수형 컴포넌트에서도 상태관리를 할 수 있게 해준다   

***

## useState
- 가장 기본적인 Hook
- 함수형 컴포넌트에서 상태를 가변적으로 지닐 수 있게 해준다   

```javascript
mport React, { useState } from "react";

const Counter = () => {
  const [value, setValue] = useState(0);
  return (
    <div>
      <p>
        현재 카운터 값은 <b>{value}</b> 입니다.
      </p>
      <button onClick={() => setValue(value + 1)}>+1</button>
      <button onClick={() => setValue(value - 1)}>-1</button>
    </div>
  );
};
export default Counter;
```

- const\[valye,serValue] = useState(0);
  - useState함수의 파라미터에는 상태의 기본값
  - 함수 호출 시 배열 반환
    - 첫번째 원소는 상태 값, 두번째 원소는 상태를 설정하는 함수
- 하나의 useState는 하나의 상태만 관리   

***

## useEffect
- 컴포넌트가 렌더링될 때마다 특정 작업을 수행
- 클래스형 컴포넌트의 componentDidMount, componentDidUpdate를 합친 형태   

```javascript
import React, { useState, useEffect } from "react";

const Info = () => {
  const [name, setName] = useState("");
  const [nickname, setNickname] = useState("");
  useEffect(() => {
    console.log("렌더링이 완료됨");
    console.log({
      name,
      nickname,
    });
  });
```


#### 마운트 될 때에만 실행하고 싶들 때
- 처음 화면에 렌더링될 때만 실행하고, 업데이될 때는 실행하지 않으려면   

```javascript
useEffect(() => {
    console.log("마운트될 때만 실행됨");
  }, []);
```

- 함수의 두 번째 파라미터로 비어있는 배열을 넣어준다   

#### 업데이트될 때만 실행   
```javascript
useEffect(() => {
  console.log(name);
}, [name]);
```
- 배열안에는 useState를 통해 관리하고있는 상태 혹은 props로 전달받은 값   

#### 뒷정리
- useEffect는 기본적으로 렌더링 직후 실행
- 컴포넌트가 언마운트되기 전이나 업데이트 되기 직전에 작업을 수행하고싶을 경우
  - useEffect에서 뒷정리함수(cleanip)을 반환   

```javascript
useEffect(() => {
  console.log("effect");
  console.log(name);
  return () => {
    console.log("cleanup");
    console.log(name);
  };
}, [name]);
```
```javascript
import Info from "./Info";
import { useState } from "react";
function App() {
  const [visible, setVisible] = useState(false);
  return (
    <div>
      <button
        onClick={() => {
          setVisible(!visible);
        }}
      >
        {visible ? "숨기기" : "보이기"}
      </button>
      <hr />
      {visible && <Info />}
    </div>
  );
}
export default App;
```

- 컴포넌트가 나타날 때 콘솔에 effect
- 컴포넌트가 사라질 때 콘솔에 cleanup
- 언마운트될 때만 뒷정리 함수를 호출하고 싶다면, 수번째 파라미터에 비어있는 배열   

***

## useMemo
- 함수형 컴포넌트 내부에서 발생하는 연산을 최적화   

```javascript
import React, { useState } from "react";
const getAverage = (numbers) => {
  console.log("평균값 계산중..");
  if (numbers.length === 0) return 0;
  const sum = numbers.reduce((a, b) => a + b);
  return sum / numbers.length;
};
const Average = () => {
  const [list, setList] = useState([]);
  const [number, setNumber] = useState("");
  const onChange = (e) => {
    setNumber(e.target.value);
  };
  const onInsert = (e) => {
    const nextList = list.concat(parseInt(number));
    setList(nextList);
    setNumber("");
  };
  return (
    <div>
      <input value={number} onChange={onChange} />
      <button onClick={onInsert}>등록</button>
      <ul>
        {list.map((value, index) => (
          <li key={index}>{value}</li>
        ))}
      </ul>
      <div>
        <b>평균값:</b> {getAverage(list)}
      </div>
    </div>
  );
};
export default Average;
```
- 인풋내용이 수정될 때 getAverage() 함수 호출
- 렌더링할 때마다 함수를 호출해 계산하는 것은 낭비
- useMemo를 이용해 작업 최적화
  - 렌더링하는 과정에서 특정 값이 바뀌었을 때만 연산을 실행
  - 원하는 값이 바뀌지 않았다면 이전 결과를 재사용   

```javascript
import React, { useState, useMemo } from "react";

  ...
  const avg = useMemo(() => getAverage(list), [list]);
  return (
    <div>
      <input value={number} onChange={onChange} />
      <button onClick={onInsert}>등록</button>
      <ul>
        {list.map((value, index) => (
          <li key={index}>{value}</li>
        ))}
      </ul>
      <div>
        <b>평균값:</b> {avg}
      </div>
    </div>
  );
```

***

## useCallback   
- useMemo와 비슷
- 렌더링 성능을 최적화해야할 때 사용
- 만들어놓은 함수 재사용 가능
- 위 예시의 onChange, onInsert함수는
  - 컴포넌트가 리렌더링될 때마다 새로 만들어진다
  - useCallback를 사용하면 해당 함수가 의존하는 값(deps)가 바뀌지 않는 한 기존 함수 재사용
  - const onChange = useCallback(function, deps);   
  - 첫번째 인자의 함수를 두번째 인자의 값이 변경될 때까지 저장해놓고 재사용   

```javascript
import React, { useState, useMemo, useCallback } from "react";
.........
const onChange = useCallback((e) => {
  setNumber(e.target.value);
}, []); // 컴포넌트가 처음 렌더링 될 때만 함수 생성
const onInsert = useCallback(() => {
  const nextList = list.concat(parseInt(number));
  setList(nextList);
  setNumber("");
}, [number, list]); // number 혹은 list 가 바뀌었을 때만 함수 생성
```

- 비어있는 배열을 넣으면 렌더링 시 만든 함수를 계속 재사용   

***

## useRef   

```javascript
import React, { useState, useMemo, useRef, useCallback } from "react";
const Average = () => {
  const [list, setList] = useState([]);
  const [number, setNumber] = useState("");
  const inputEl = useRef(null);
  const onChange = useCallback((e) => {
    setNumber(e.target.value);
  }, []); // 컴포넌트가 처음 렌더링 될 때만 함수 생성
  const onInsert = useCallback(() => {
    const nextList = list.concat(parseInt(number));
    setList(nextList);
    setNumber("");
    inputEl.current.focus();
  }, [number, list]); // number 혹은 list 가 바뀌었을 때만 함수 생성
  const avg = useMemo(() => getAverage(list), [list]);
  return (
    <div>
      <input value={number} onChange={onChange} ref={inputEl} />
```
