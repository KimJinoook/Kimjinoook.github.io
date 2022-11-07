---
layout: post
title:  "fetch 페치란"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---
## fetch란
- promise 기반 ES6+에 추가된 javascript 내장 함수
- 별도의 라이브러리를 설치할 필요는 없다
- 모든 브라우저에서 호환되지는 않으며, 브라우저 버전이 낮을 경우 polyfill을 적용하여 사용해야 한다   

***

## 구성   

```javascript
fetch(url, {
  method: 'GET' //통신방식, GET, POST, PUT, DELETE 등
  mode: 'cors', // no-cors, cors, *same-origin
  cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
  credentials: 'same-origin', // include, *same-origin, omit
  headers: {
    'Content-Type': 'application/json',
    // 'Content-Type': 'application/x-www-form-urlencoded',
  },
  redirect: 'follow', // manual, *follow, error
  referrer: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
  
  body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
})
.then(response => response.json())
.catch(error => console.log(error));


```   

- 혹은 async와 await을 이용해, 요청 및 응답을 기다렸다 변수에 응답을 담을 수 있다   

```javascript
async function postData(url = '', data = {}) {

  const response = await fetch(url, {
    method: 'POST', // *GET, POST, PUT, DELETE 등
    mode: 'cors', // no-cors, *cors, same-origin
    cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
    credentials: 'same-origin', // include, *same-origin, omit
    headers: {
      'Content-Type': 'application/json',
      // 'Content-Type': 'application/x-www-form-urlencoded',
    },
    redirect: 'follow', // manual, *follow, error
    referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    
    body: JSON.stringify(data), // body의 데이터 유형은 반드시 "Content-Type" 헤더와 일치해야 함
  });
  return response.json(); // JSON 응답을 네이티브 JavaScript 객체로 파싱
}

postData('url', { 
  data: data 
}).then((data) => {
  console.log(data); 
});
```

- promise 기반으로, then()함수를 이용해 이전 then에서 반환한 값을 가져와 추가적인 처리가 가능하다   

```javascript
export function requestFetch(url, requestOptions, handler, errorHandler) {
    console.groupCollapsed("requestFetch");
    console.log("requestFetch [URL] : ", SERVER_URL + url);
    console.log("requestFetch [requestOption] : ", requestOptions);

    //CORS ISSUE 로 인한 조치 - origin 및 credentials 추가 
    // origin 추가
    if (!requestOptions['origin']) {
        requestOptions = { ...requestOptions, origin: SERVER_URL };
    }
    // credentials 추가 
    if (!requestOptions['credentials']) {
        requestOptions = { ...requestOptions, credentials: 'include' };
    }

    fetch(SERVER_URL + url, requestOptions)
        .then((response) => {// response Stream. Not completion object
            //console.log("requestFetch [Response Stream] ", response); 
            return response.json();
        })
        .then((resp) => {
            if (Number(resp.resultCode) === Number(CODE.RCV_ERROR_AUTH)) {
                alert("Login Alert");
                window.location.href = URL.LOGIN;
                return false;
            } else {
                return resp;
            }
        })
        .then((resp) => {
            console.groupCollapsed("requestFetch.then()");
            console.log("requestFetch [response] ", resp);
            if (typeof handler === 'function') {
                handler(resp);
            } else {
                console.log('egov fetch handler not assigned!');
            }
            console.groupEnd("requestFetch.then()");
        })
 
        .finally(() => {
            console.log("requestFetch finally end");
            console.groupEnd("requestFetch");
        });
}
```
