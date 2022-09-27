---
layout: post
title:  "14. 라우팅"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

# 라우팅
## 라우팅이란
- 사용자가 요청한 URL에 따라 알맞는 페이지를 보여주는 것
- 리액트의 라우트 시스템
  - 리액트 라우터 (React Router)
    - 가장 오래된 리액트의 라우팅 관련 라이브러리
    - 컴포넌트 기반으로 라우팅 시스템 설정
  - Next.js
    - 리액트 프로젝트의 프레임워크
    - 서버 사이드 렌더링 등 다양한 기능 제공
    - 리액트 라우터 대안으로 많이 사용하는 프레임워크   

## 싱글페이지 어플리케이션
- 한 개의 페이지로 이루어진 어플리케이션
- 차이점
  - 멀티 페이지
    - 다른페이지로 이동할 때마다 새로운 HTML을 받아온다
    - 페이지를 로딩할 때마다 서버에서 CSS, JS 등의 리소스를 전달받는다
    - 각 페이지마다 다른 html을 만들어 제공하거나, 템플릿 엔진 사용
    - **새로운 페이지를 보여줄 때마다 서버에서 준비, 서버측 자원 사용**
      - 트래픽 증가
  - 싱글 페이지
    - 리액트같은 라이브러리를 사용
    - 사용자와 인터랙션 발생 시 필요한 부분만 자바스크립트를 이용해 업데이트
    - html은 한번만 받아오고, 이후 **필요한 데이터만 받아와 화면에 업데이트**   

***

## 리액트 라우터 적용
- 프로젝트 생성   
  > $ yarn create react-app 프로젝트명   

- 해당 디렉토리로 이동 후 리액트 라우터 라이브러리 설치   
  > $ cd 프로젝트명   
  > $ yarn add react-router-dom   

- 프로젝트에 라우터 적용
  - src/index.js 파일에서 react-router-dom에 내장되어있는 BrowserRouter 컴포넌트를 이용해 감싼다
  - HTML5의 HistoryAPI를 사용해 주소 변경 및 관련 정보를 사용할 수 있게 해준다   

```jacascript
src/index.js
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { BrowserRouter } from 'react-router-dom';

ReactDOM.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>,
  document.getElementById('root')
);

```

### 라우터 기본 사용   
```javascript
src/App.js
import { Route, Routes } from 'react-router-dom';
import About from './pages/About';
import Home from './pages/Home';

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
    </Routes>
  );
};
export default App;

```

- path가 /about 일때 About 컴포넌트를 보여준다   

### Link 컴포넌트
- 라우터의 링크는 a태그를 바로 사용하면 안된다
  - a태그로 페이지를 이동하면, 브라우저에서 페이지를 새로 불러오기 때문
- Link 컴포넌트를 이용해 페이지 이동
  - 페이지를 새로 불러오는 것을 막고, HistoryAPI를 통해 브라우저 주소의 경로만 변경해준다   

```javascript
src/pages/Home.js
import { Link } from 'react-router-dom';
const Home = () => {
  return (
    <div>
      <h1>홈</h1>
      <p>가장 먼저 보여지는 페이지입니다.</p>
      <Link to="/about">소개</Link>
    </div>
  );
};
export default Home;
```

***

## URL 파라미터와 쿼리스트링
- 페이지 주소를 정의할 때 유동적인 값이 필요할 때가 있다
  - URL파라미터
    - /profile/**hong**
  - 쿼리스트링
    - /profile**?name=hong&age=12**   

### URL 파라미터를 사용한 라우터   
```javascript
//Profile.js
import { useParams } from 'react-router-dom';
const data = {
  kim: {
    name: '김길동',
    description: '개발자',
  },
  gildong: {
    name: '홍길동',
    description: '홍길동전의 주인공',
  },
};
const Profile = () => {
  const params = useParams();
  const profile = data[params.username];
  return (
    <div>
      <h1>사용자 프로필</h1> {profile ? (
        <div>
          <h2>{profile.name}</h2> 
          <p>{profile.description}</p>
        </div>
      ) : ( <p>존재하지 않는 프로필입니다.</p>
      )}
    </div>
  );
};
export default Profile;


//App.js
import { Route, Routes } from 'react-router-dom';
import About from './pages/About';
import Home from './pages/Home';
import Profile from './pages/Profile';
const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/about" element={<About />} />
      <Route path="/profiles/:username" element={<Profile />} />
    </Routes>
  );
};
export default App;


//Home.js
import { Link } from 'react-router-dom';
const Home = () => {
  return (
    <div>
      <h1>홈</h1>
      <p>가장 먼저 보여지는 페이지입니다.</p>
      <ul>
        <li>
          <Link to="/about">소개</Link>
        </li>
        <li>
          <Link to="/profiles/kim">kim의 프로필</Link>
        </li>
        <li>
          <Link to="/profiles/gildong">gildong의 프로필</Link>
        </li>
        <li>
          <Link to="/profiles/void">존재하지 않는 프로필</Link>
        </li>
      </ul>
    </div>
  );
};
export default Home;
```

- Profile.js
  - useParams를 이용해 URL 파라미터를 객체형태로 조회
  - URL 파라미터의 이름은 라우트 설정 시 컴포넌트의 path props를 통해 설정
- App.js
  - 라우트 설정에서 /profiles/:username과 같이 경로에 :를 사용해 설정
  - 파라미터가 여러개인 경우 /profiles/:username/:age   

### 쿼리스트링   
```javascript
//About.js
import { useLocation } from 'react-router-dom';
const About = () => {
  const location = useLocation();
  return (
    <div>
      <h1>소개</h1>
      <p>리액트 라우터를 사용해 보는 프로젝트입니다.</p>
      <p>쿼리스트링: {location.search}</p>
    </div>
  );
};
export default About;

```
- useLocation
  - location 객체를 반환
  - 현재 사용자가 보고있는 페이지의 정보를 지닌다
  - 값
    - pathname : 현재 주소의 경로 (쿼리스트링 제외)
    - search : 맨 앞의 ? 문자를 포함한 쿼리스트링 값
    - hash : 주소의 #문자열 뒤의 값
    - state : 페이지로 이동할 때 임의로 넣을 수 있는 상태 값
    - key : location 객체의 고유 값
- 파싱작업
  - 쿼리스트링은 라우트에서 별도로 파라미터이름을 정할 필요가 없다
  - url에  http://localhost:3000/about?detail=true&mode=1 을 칠 경우
    - location.search에 쿼리스트링이 그대로 출력된다 ?detail=true&mode=1
    - ? 문자열을 지우고, &문자열로 분리한 후 key와 value를 파싱하는 작업 필요   

```javascript
src/pages/About.js
import { useSearchParams } from 'react-router-dom';
const About = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const detail = searchParams.get('detail');
  const mode = searchParams.get('mode');
  const onToggleDetail = () => {
    setSearchParams({ mode, detail: detail === 'true' ? false : true });
  };
  const onIncreaseMode = () => {
    const nextMode = mode === null ? 1 : parseInt(mode) + 1;
    setSearchParams({ mode: nextMode, detail });
  };
  return (
    <div>
      <h1>소개</h1>
      <p>리액트 라우터를 사용해 보는 프로젝트입니다.</p>
      <p>detail: {detail}</p>
      <p>mode: {mode}</p>
      <button onClick={onToggleDetail}>Toggle detail</button>
      <button onClick={onIncreaseMode}>mode + 1</button>
    </div>
  );
};
export default About;
```

- useSearchParams
  - 배열 타입의 값 반환
  - get 메서드를 통해 특정 파라미터 조회가능
  - set 메서드를 통해 특정 파라미터 업데이트 가능
  - 파라미터가 존재하지 않으면 null
- 주의할점
  - 파라미터의 값은 모두 문자열로 조회된다
  - true/false값을 비교할땐 'true'와 같이 문자열로 비교
  - 혹은 parseInt 등을 이용해 변환
