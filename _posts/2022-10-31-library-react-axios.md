---
layout: post
title:  "axios 엑시오스란"
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
- 라이브러리로, npm이나 기타 방법을 통한 별도 설치 필요   
  > npm install axios   
  > bower install axios   
  > yarn add axios   
  > <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>   
  > <script src="https://unpkg.com/axios/dist/axios.min.js"></script>   


***

## 구성   
```javascript
axios({
    method: "", // 통신 방식
    url: "", // 서버
    headers: {} // 요청 헤더 설정
    params: {}, // ?파라미터를 전달
    responseType: '', 
})
.then(function (response) {
    setData(response.data);
});
```
- 구성옵션은 url이 필수값
- *url* : 요청에 사용될 서버 URL
  - url 값이 상대url이라면, 앞에 자동적으로 baseURL이 붙는다
  - '/user' 입력 시, 'http://localhost:8080/user'
- method : 요청할 때 사용될 메서드, 기본값 get
- headers : 서버에 전송 될 사용자 정의 헤더
  - { 'X-Requested-With': 'XMLHttpRequest' }
- params : 요청과 함께 전송될 URL 파라미터
  - 일반 객체이거나 URLSearchParams 객체여야 한다.
  - URL뒤에 붙는 ?key=value를 객체로 표현한 것
  - params:{id:'userid'}


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
