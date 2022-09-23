---
layout: post
title:  "5. 어노테이션"
subtitle:   ""
categories: framework
tags: spring
comments: false
header-img: 
---

- 과도한 xml 설정으로 인한 불편함 해결 필요
  - Annotation 기반의 설정방법 적용   

### @Controller
- Controller클래스를 Controller Bean으로 인식시키기 위해 사용
- 어떤 인터페이스 클래스도 상속할 필요가 없다
  - POJO로 구현될 수 있다
- @Controller 사용 시   


```java
import org.springframework.stereotype.Controller;
@Controller
public class LoginController {
  public String loginUser(UserVO vo){
    return null;
  }
}
```

- 어노테이션 미사용 시   


```java
public class LoginController implements Controller {
  public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
    …
  }

```

### @RequestMapping
- 컨트롤러클래스의 메서드에 설정할 때, 요청url과 적절하게 매핑   


```java
package com.testmall.view.user.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoginController {
  @RequestMapping("/login.do")
  public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
  }
}

```

```html
[WEB-INF/spring-mvc.xml]
<!-- Controller -->
<bean id="loginController" class="com.testmall.view.user.LoginController"/>
```

- 어노테이션 미사용 시   


```html
[WEB-INF/spring-mvc.xml]

<!-- HandlerMapping -->
<bean id="handlerMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
  <property name="mappings">
    <props>
      <prop key="/login.do">loginController</prop>
    </props>
  </property>
</bean>

<!-- Controller -->
<bean id="loginController" class="com.testmall.view.user.LoginController"/>
```

- 사용자의 요청에 따라 같은 url도 get 방식과 post방식으로 구분하여 사용 가능   

```java
@Controller
public class LoginController {
  @RequestMapping(value="/login.do", method=RequestMethod.GET)
  public String loginView(HttpServletRequest request,HttpServletResponse response) {
  }
  
  @RequestMapping(value="/login.do", method=RequestMethod.POST)
  public String login(HttpServletRequest request,HttpServletResponse response) {
  }
}
```

- 혹은 중복부분을 클래스에 바로 어노테이션을 달 수 있다   


```java
@Controller
@RequestMapping("/login.do")
public class LoginController {
  @RequestMapping(method=RequestMethod.GET)
  public String loginView(HttpServletRequest request,HttpServletResponse response) {
  }
  
  @RequestMapping(method=RequestMethod.POST)
  public String login(HttpServletRequest request,HttpServletResponse response) {
  }
}
```

### @ModelAttribute
- 클라이언트의 요청 처리 시, 매개변수로 사용자가 입력한 값을 매핑할 수 있는 자바빈을 등록하면 spring 컨테이너가 자바빈을 생성하여 넘겨준다
- 사용자가 입력한 값을 자바빈의 property에 자동으로 채워준다
- 사용자가 입력한 값 추출과 자바빈 생성 및 값 설정 과정을 컨테이너에 의해 자동으로 처리
  - 이런 자바빈을 Command객체 라고 한다   

```java
@Controller
public class LoginController {
  @RequestMapping("/login.do")
  public String login(@ModelAttribute UserVO vo) throws Exception {
    int n = userService.insertUser(vo);
  }
}
```

- vo의 멤버변수 명과 사용자가 입력한 파라미터의 name이 같아야한다   


```java
[UserVO.java]
public class UserVO {
  private String id;
  private String password;

}
```

```html
[login.jsp]

<tr><td>아이디</td><td><input name="id" type="text" /></td></tr>
<tr><td>비밀번호</td><td><input name="password" type="password" /></td></tr>
```

- 어노테이션 미사용 시   


```java
@Controller
public class LoginController {
  @RequestMapping("/login.do")
  public String login(HttpServletRequest request) {
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    UserVO vo = new UserVO();
    vo.setId(id);
    vo.setPassword(password);
    int n = userService.insertUser(vo);
  }
}
```

### @RequestParam
- Http 요청 파라미터를 메서드의 파라미터로 전달받을 때 사용   

```java
@Controller
public class getUserListController {
  @RequestMapping("/getUserList.do")
  public String getUserList(@RequestParam("searchCondition") String searchCondition,@RequestParam("searchKeyword") Strin searchKeyword){
    System.out.println("검색 조건 : " + searchCondition);
    System.out.println("검색 단어 : " + searchKeyword);
    return null;
  }
}

```

- RequestParam이 적용된 파라미터는 기본적으로 필수 파라미터
  - 요청 파라미터가 존재하지 않을 경우 400 응답 코드
  - 필수가 아닌 파라미터의 경우 required 속성 값을 false로 지정   

```java
@Controller
public class SearchController {
  @RequestMapping("/search/external.do")
  public ModelAndView searchExternal(@RequestParam(value="query", required=false) String query, @RequestParam(value = "p", required=false) int pageNumber) {
    System.out.println("query=" + query + ",pageNumber=" + pageNumber);
    return new ModelAndView("search/external");
  }
}
```

- 파라미터에 null을 할당할 수 없을 경우 기본값 지정   

```java
@RequestMapping("/search/external.do")
public ModelAndView searchExternal(@RequestParam(value="query", required=false) String query, @RequestParam(value = "p", defaultValue = "1") int pageNumber) {
  System.out.println("query=" + query + ",pageNumber=" + pageNumber);
  return new ModelAndView("search/external");
}
```   

### @CookieValue
- 쿠키 값을 파라미터로 전달받을 수 있다   

```java
import org.springframework.web.bind.annotation.CookieValue;
@Controller
public class CookieController {
  @RequestMapping("/cookie/view.do")
  public String view(@CookieValue(value = "auth") String authValue) {
    System.out.println("auth 쿠키: " + authValue);
    return "cookie/view";
  }
}
```

### @RequestHeader
- 어노테이션을 이용하면 Http요청 헤더의 값을 파라미터로 받을 수 있다   


```java
@Controller
public class HeaderController {
  @RequestMapping("/header/check.do")
  public String check(@RequestHeader("Accept-Language") String languageHeader) {
    System.out.println(languageHeader);
    return "header/pass";
  }
}

```
***
## 컨트롤러 클래스 자동 스캔
- @Controller 어노테이션은 @Component 어노ㅔ이션과 마찬가지로 컴포넌트 스캔 대상이다
- @Controller가 적용된 컨트롤러를 스캔하여 자동으로 빈으로 등록한다   

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:context="http://www.springframework.org/schema/context" 
  xmlns:p="http://www.springframework.org/schema/p"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
  http://www.springframework.org/schema/context
  http://www.springframework.org/schema/context/spring-context-3.0.xsd">

  <context:component-scan base-package="mysite.spring.chap06.controller" />

```
