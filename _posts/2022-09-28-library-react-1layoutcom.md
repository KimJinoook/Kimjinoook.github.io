---
layout: post
title:  "15. 공통 레이아웃 컴포넌트"
subtitle:   ""
categories: library
tags: react
comments: false
header-img: 
---

# 공통 레이아웃 컴포넌트
- 헤더, 푸터등의 공통적으로 보여주는 레이아웃의 경우
  - 헤더 컴포넌트, 푸터 컴포넌트를 따로 만든 후 각 페이지에서 컴포넌트 재사용
  - 혹은 중첩라우트를 이용해 구현   

```javascript
//Layout.js
import { Outlet } from 'react-router-dom';
const Layout = () => {
  return (
    <div>
      <header style={{ background: 'lightgray', padding: 16, fontSize: 24 }}>
        Header
      </header>
      <main>
        <Outlet />
      </main>
    </div>
  );
};
export default Layout;


//App.js
mport { Route, Routes } from 'react-router-dom';
import Layout from './Layout';
import About from './pages/About';
import Article from './pages/Article';
import Articles from './pages/Articles';
import Home from './pages/Home';
import Profile from './pages/Profile';
const App = () => {
  return (
    <Routes>
      <Route element={<Layout />}>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/profiles/:username" element={<Profile />} />
      </Route>
      <Route path="/articles" element={<Articles />}>
        <Route path=":id" element={<Article />} />
      </Route>
    </Routes>
  );
};
export default App;
```

- 각 페이지 컴포넌트가 보여져야하는 부분에 Outlet 컴포넌트를 사용   

***

## index props
- route 컴포넌트의 index props
  - path="/"와 동일한 의미   

```javascript
//App.js
import { Route, Routes } from 'react-router-dom';
import Layout from './Layout';
import About from './pages/About';
import Article from './pages/Article';
import Articles from './pages/Articles';
import Home from './pages/Home';
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
    </Routes>
  );
};
export default App;
```

- index prop은 상위 라우트의 경로와 일치하지만, 그 이후에 경로가 주어지지 않았을 때 보여지는 라우트를 설정할 때 사용
