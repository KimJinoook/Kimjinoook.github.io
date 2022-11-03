---
layout: post
title:  "axios"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

## Axios란
- Promise 기반 HTTP 비동기 통신 라이브러리
- 운영환경에 따라 브라우저 간 XMLHttpRequest 객체 또는 Node.js의 HTTP API를 사용
- 요청과 응답을 JSON형태로 자동 변경   
- 라이브러리로, npm을 통한 별도 설치 필요   
  > npm install axios   



***

## 기본 사용법   
```javascript
const onClick = () => {
  axios.get('url')
  .then((resp)=>{
    setData(resp.data);
  });
}
```
- 비동기 방식으로 url에 get방식으로 접근하여 응답을 받아온다.
- then 을 통해, 받아온 응답을 이용해 처리할 수 있다.


```javascript
const onClick = async() => {
  try{
    const resp = await axios.get('url');
    setData(resp.data);
  }catch(error){
    console.error(error)
  }
}
```

- 비동기 방식이지만, async와 await을 사용하면 promise가 끝날 때까지 기다렸다가 결과값을 특정 변수에 담을 수 있다.
