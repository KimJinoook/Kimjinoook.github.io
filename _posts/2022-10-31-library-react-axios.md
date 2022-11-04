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
- **url** : 요청에 사용될 서버 URL
  - url 값이 상대url이라면, 앞에 자동적으로 baseURL이 붙는다
  - '/user' 입력 시, 'http://localhost:8080/user'
- **method** : 요청할 때 사용될 메서드
  - 기본값 get
- **headers** : 서버에 전송 될 사용자 정의 헤더
  - { 'X-Requested-With': 'XMLHttpRequest' }
- **params** : 요청과 함께 전송될 URL 파라미터
  - 일반 객체이거나 URLSearchParams 객체여야 한다.
  - URL뒤에 붙는 ?key=value를 객체로 표현한 것   
  > params:{id:'userid'}   


- **data** : 요청 본문 (request body)로 전송할 데이터
  - put, post, patch 요청 메서드에만 적용 가능
  - transformRequest가 설정되지 않은 경우에는 string, plain object, ArrayBuffer, ArrayBufferView, URLSearchParams 즁 하나여야 한다
  - 브라우저 전용 : FormData, File, Blob
  - Node.js 전용 : Stream, Buffer   
  > data : {id:'userId'}   


- **responseType** : 서버에서 응답할 데이터 타입 설정
  - 기본값 json
  - arraybuffer, blob, document, json, text, stream
- responseEncoding : 응답 디코딩에 사용할 인코딩
  - 기본값 utf8
  - 클라이언트 사이드 요청 또는 responseType이 stream일 경우에는 무시한다
- transformRequest : 서버에 보내기 전 요청 데이터를 변경
  - put, post, patch에만 적용 가능
  - 배열의 마지막 함수는 버퍼(buffer)의 문자열이나 인스턴스를 반환해야 한다
  - ArrayBuffer, FormData, Stream 헤더 객체를 수정 가능   
  > transformRequest: \[function(data,headers) {.. return data}]   
- transformResponse : 응답 데이터에 대한 변경을 전달해 then/catch에 전달하도록 허용한다   
  > transformResponse: \[functoin(data){..return data;}]   
- paramsSerializer : params를 직렬화하는 옵션 함수
- timeout : 요청이 타임아웃되는 밀리초를 설정
  - 기본값 0
  - timeout 설정시간보다 길어지는 경우 요청이 중단된다
- **withCredentials** : 자격증명을 사용, cross-site-access-control 요청 허용여부를 설정한다
  - 기본값 false
- adapter : 테스트를 보다 쉽게 해주는 커스텀 핸들링 요청을 허용
  - 유효한 응답(promise)를 반환해야 한다
- auth : HTTP 기본인증이 사용되며, 자격증명을 제공함을 나타낸다
  - 기존의 Authorization 커스텀 헤더를 덮어쓰는 헤더를 설정한다   
  > auth:{username:'asd', password:'123'}   
- xsrfCookieName : xsrf 토큰에 대한 값으로 사용할 쿠키 이름
- xsrfHeaderName : xsrf 토큰 값을 운반하는 HTTP 헤더 이름
- onUploadProgress : 업로드 프로그레스 이벤트 처리
- onDownloadProgress : 다운르도 프로그레스 이벤트 처리


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
