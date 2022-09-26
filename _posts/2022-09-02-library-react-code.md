---
layout: post
title:  "2. 리액트의 코드"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

## 프로젝트 생성 및 실행
- node.js, npm, yarn 설치
  - node.js는 당장은 필요없지만, 설치시 npm이 함께 설치된다
  - npm은 패키지매니저 도구
  - node.js 설치 후, node.js의 cmd창 실행
  - npm 설치 확인
    - 명령어 : npm -v
  - yarn 설치 (패키지 관리자 도구)
    - 설치 명령어 : npm install --global yarn
    - 설치 확인 : yarn --version
- 코드에디터 설치 (Visual Studio Code)
  - 설치 후 좌측 확장팩 설치 메뉴 이용
  - ESLint 설치
  - Reactjs code Snippets 설치
  - Prettier-code formatter 설치
  - Koran Language Pack for Visual Studio Code 설치
    - f1 누른 후 Configure Display Language 입력
    - ko로 설정 후 재시작
- 프로젝트 생성 및 실행
  - node.js cmd 혹은 vscode의 터미널창
  - 프로젝트를 만들 디렉토리로 이동
  - 프로젝트 생성 명령어 입력
    - yarn create react-app 프로젝트명
  - 프로젝트 생성 후 해당 디렉토리에서 실행명령어 입력
    - npm start   

***

## 기본 코드
#### App.js   

```javascript
import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";

function App() {
    const [message, setMessage] = useState([]);

    useEffect(() => {
        fetch("/test")
            .then((response) => {
                return response.json();
            })
            .then(function (data) {
                setMessage(data);
            });
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    Edit <code>src/App.js</code> and save to reload.
                </p>
                <a
                    className="App-link"
                    href="https://reactjs.org"
                    target="_blank"
                    rel="noopener noreferrer"
                >
                    Learn React
                </a>
                <ul>
                    {message.map((text, index) => <li key={`${index}-${text}`}>{text}</li>)}
                </ul>
            </header>
        </div>
    );
}

export default App;
```
- 프로젝트 생성시 node_modules 디렉토리 자동 생성
- 해당 폴더 내 react 모듈이 설치되어있다
- import 구문을 통해 리액트를 불러와 사용
  - 번들링을 통해 불러온 모듈들을 하나로 합쳐 파일을 생성
  - index.js를 시작으로 필요한 파일을 불러와 번들링
- 동작원리
  - 프로젝트 내 코드들을 자바스크립트를 이용해 해석
  - 해석한 결과물을, 함수형 컴포넌트가 반환하는 내용을 index.html에 끼워넣는다   

### index.js   
```javascript
ReactDOM.render(
  <App />
  document.getElementById('root')
);

```
- document ~ id('root') : index.html에서 id가 root인 엘리먼트에
- App : App 컴포넌트를 (App.js에서 작성한 코드를)
- ReactDOM.render : 그린다
- 리액트는 처음부터 모든 html을 그리지 않는다
- 일부만 그린 후, 엘리먼트를 추가하거나 제거하는 방식으로 화면을 그린다 

