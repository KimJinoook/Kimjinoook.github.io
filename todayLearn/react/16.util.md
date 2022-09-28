---
layout: post
title:  "16. 리액트 라우터의 부가기능"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

# 리액트 라우터 부가기능
## useNavigate
- Link 컴포넌트를 사용하지 않고 다른 페이지로 이동을 해야하는 상황에 사용   

```javascript
//Layout.js
import { Outlet, useNavigate } from 'react-router-dom';
const Layout = () => {
  const navigate = useNavigate();
  const goBack = () => {
    // 이전 페이지로 이동
    navigate(-1);
  };
  const goArticles = () => {
    // articles 경로로 이동
    navigate('/articles');
  };
  const goArticles2 = () => {
    navigate('/articles', { replace: true });
  };
  return (
    <div>
      <header style={{ background: 'lightgray', padding: 16, fontSize: 24 }}>
        <button onClick={goBack}>뒤로가기</button>
        <button onClick={goArticles}>게시글 목록</button>
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};
export default Layout;

```

- 파라미터가 경로일 경우, 해당 경로로 이동
- 파라미터가 숫자일 경우, 숫자만큼 앞 뒤로 이동
- replace 옵션이 true일 경우
  - 페이지를 이동할 때, 현재페이지를 기록에 남기지 않는다.
  - 페이지 이동 후 뒤로가기 클릭 시, 이전 페이지가 기록에 없기 때문에 전전페이지로 이동   

***

## NavLink
- 링크에서 사용하는 경로가 현재 라우트의 경로와 일치하는 경우, 특정 스타일 도는 CSS 클래스를 적용
- 설정할 떄 {isActive:boolean}을 파라미터로 전달받는 함수 타입의 값을 전달   

```javascript
src/pages/Articles.js
import { NavLink, Outlet } from 'react-router-dom';
const Articles = () => {
  
  const activeStyle = {
    color: 'green',
    fontSize: 21,
  };
  
  return (
    <div>
      <Outlet />
      <ul>
        <li>
          <NavLink to="/articles/1"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >게시글 1
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/articles/2"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            게시글 2
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/articles/3"
            style={({ isActive }) => (isActive ? activeStyle : undefined)}
          >
            게시글 3
          </NavLink>
        </li>
      </ul>
    </div>
  );
};
export default Articles;

```   


***

## NotFound
- 사전에 정의되지 않은 경로에 사용자가 진입했을 때 보여주는 페이지   

```javascript
//NotFound.js
const NotFound = () => {
  return (
    <div
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        fontSize: 64,
        position: 'absolute',
        width: '100%',
        height: '100%',
      }}
    >
      404
    </div>
  );
};
export default NotFound;


//App.js
import { Route, Routes } from 'react-router-dom';
import Layout from './Layout';
import About from './pages/About';
import Article from './pages/Article';
import Articles from './pages/Articles';
import Home from './pages/Home';
import NotFound from './pages/NotFound';
import Profile from './pages/Profile';
const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/profiles/:username" element={<Profile />} />
      </Route>
      <Route path="/articles" element={<Articles />}>
        <Route path=":id" element={<Article />} />
      </Route>
      <Route path="*" element={<NotFound />} />
    </Routes>
);
};
export default App;

```
- \*는 wildcard 문자, 아무 문자나 매칭한다
- 위에서 정의되지 않은 모든 경로는 wildcard문자와 매치되어 NotFound 라우트가 화면에 나타난다   

***

## Navigate 컴포넌트
- 컴포넌트를 화면에 보여주는 순간 다른페이지로 이동하고싶을 때 사용
- 페이지 리다이렉트
  - 페이지 이동 시, 로그인이 되어있지 않다면 로그인 페이지로 이동하게 하는 등   

```javascript
//Login.js
const Login = () => {
  return <div>로그인 페이지</div>;
};
export default Login;



//MyPage.js
import { Navigate } from 'react-router-dom';
const MyPage = () => {
  const isLoggedIn = false;
  if (!isLoggedIn) {
    return <Navigate to="/login" replace={true} />;
  }
  return <div>마이 페이지</div>;
};
export default MyPage
```

- 마이페이지 이동 시, 로그인 여부에 따라
  - isLoggedIn 이 false 이면 로그인 페이지로 이동
