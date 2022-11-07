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
  > <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"\></script\>   
  > <script src="https://unpkg.com/axios/dist/axios.min.js"\></script\>   


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
- maxContentLength : HTTP 응답 콘텐츠의 최대 크기를 바이트 단위로 설정
- validateStatus : 주어진 HTTP응답 상태코드에 대한 약속을 해결할지 거절할지 정의
- maxRedirects : Node.js에서 리다이렉트 가능한 최대 개수를 정의
  - 기본값 5
- socketPath : Node.js에서 사용될 UNIX 소켓 정의
  - 기본값 null
- httpAgent, httpsAgent : Node.js에서 http,https 요청을 수행할 때 사용할 커스텀에이전트 정의
- proxy : 프록시 서버의 호스트 이름과 포트를 정의
- cancelToken : 요청을 취소하는데 사용할 수 있는 취소토큰 지정   

## 구성을 설정한 인스턴스 생성   
``` javascript
const instance = axios.create({
  baseURL : 'http://localhost:9999',
  headers: { 'X-Custom-Header': 'foobar' },
  timeout: 1000,
})

const onClick =() => {
  instance.get('url')
  .then((resp)=>{
    setData(resp.data);
  });
}
```
- 기본 구성을 설정한 인스턴스를 생성해 axios 기본세팅을 할 수 있다.   

***

## 단축메서드
- get : 주로 리소스 조회에 사용
  - axios.get(url)
  - axios.get(url,config)
- post : 요청 데이터 처리, 주로 등록세 아용
  - axios.post(url,data)
  - axios.post(url,data,config)
- put : 리소스를 대체할 때 주로 사용
- delete : 리소스를 삭제할 때 주로 시용

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

*** 
## axios.get
#### 1
```javascript
axios.get('/getOne',{
  params:searchVO
})

axios.get('/getOne',{
  params:{
    searchKeyword:'a',
    searchCondition:'b'
  }
})
```
```java
@GetMapping("/getOne")
public ResultVO getOne(SearchVO searchVO) throws Exception{
  ...
  return ResultVO
}
```

#### 2
```javascript
axios.get('/getTwo?key=value')
```
```java
@GetMapping("/getTwo")
public ResultVO getTwo(@RequestParam int key)
  ...
  return ResultVO
}
```

#### 3
```javascript
//es6의 템플릿리터럴 이용
axios.get('getThree/${key})
```
```java
@GetMapping("/getThree/{key}
public ResultVO getThree(@PathVariable int key)
  ...
  return ResultVO
}
```

## axios.post
#### 1
```javascript
axios.post('/postOne',searchVO)

axios.post('/postOne',{
  searchKeyword:'a',
  searchCondition:'b'
})
```
```java
@PostMapping('/postOne')
public ResultVO postOne(@RequestBody SearchVO searchVO) {
  ...
  return ResultVO
}
```

- 파일 업로드 등 formData를 내보낼 시   

```javascript
const form = new FormData();
form.append("file",file);
form.append("name",name);

axios.post('/url',form,{
  headers:{
     "content-type": "multipart/form-data",
  },
})
```
```java
public ResultVO insertForm(final MultipartHttpServletRequest multiRequest,DataVO dataVO) throws Exception {
}
```

## axios.put
- 서버 내부적으로 get-post 과정을 거치기 때문에 post 메서드와 비슷한 형태   

#### 1
```javascript
axios.put('/putOne',boardVO)

axios.put('/putOne',{
  boardNo:1,
  boardTitle:'title',
  boardContent:'content'
})
```
```java
@PutMapping("/putOne")
public ResultVO putOne(@RequestBody BoardVO boardVO){
  ...
  return ResultVO
}
```
#### 2
```javascript
axios.put('/putTwo/${no}',boardVO)
```
```java
@PutMapping("/putTwo/{no}")
public ResultVO putTwo(@PathVariable int no, @RequestBody BoardVO boardVO){
  ...
  return ResultVO
}
```

## axios.delete
- 일반적으로 body가 비어있으며, 리소스 삭제를 목적으로 사용된다   

```javascript
axios.delete('/deleteOne/${id}/${pw})
```
```java
@DeleteMapping("/deleteOne/{id}/{pw}")
public ResultVO deleteOne(@PathVariable String id, @PathVariable String pw){
  ...
  return ResultVO
}
```
