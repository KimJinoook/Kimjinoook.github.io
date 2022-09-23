---
layout: post
title:  "6. MVC"
subtitle:   ""
categories: lang
tags: spring
comments: false
header-img: 
---

## 스프링의 주요 구성요소
- DispatcherServlet
  - 클라이언트의 요청을 전달
  - 컨트롤러에게 클라이언트의 요청을 전달
  - 컨트롤러가 리턴한 결과값을 view에게 전달
- HandlerMapping
  - 클라이언트의 요청 URL을 어떤 컨트롤러가 처리할지 결정
- Controller
  - 클라이언트의 요청을 처리
  - 결과를 DispatcherServlet에 전달
- ModelAndView
  - 컨트롤러가 처리한 결과 정보 및 뷰 선택에 필요한 정보를 담는다
- ViewResolver
  - 컨트롤러의 처리 결과를 생성할 뷰를 결정
- View
  - 컨트롤러의 처리결과 화면을 생성. JSP 등   

***
## 처리 흐름
- 클라이언트의 요청이 DispatcherServlet에 전달
- DispatcherServlet은 HandlerMapping을 사용하여 요청을 처리할 컨트롤러 객체를 구함
- DispatcherServlet은 컨트롤러 객체를 이용해 클라이언트 요청 처리
- 컨트롤러는 클라이언트의 요청 처리결과 정보를 담은 ModelAndView 객체 리턴
- DispatcherServlet은 ViewResolver로부터 응답결과를 생성할 뷰 객체를 구함
- 뷰는 클라이언트에 전송할 응답을 생성   
> 개발자는 컨트롤러와 뷰를 개발   
> 나머지는 스프링이 기본적으로 제공하는 구현클래스 사용   

***
## SpringMVC 적용 절차
- web.xml 파일에 DispatcherServlet 등록
- 클라이언트의 요청에 대한 Controller 작성
- Spring 설정파일에 HandlerMapping, Controller, ViewResolver 등록   

### Web.xml
- web.xml 파일에 DispatcherServlet 등록   


```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app ...>
  <display-name>SpringMVC</display-name>
  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>
      org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
</web-app>

```

- web.xml파일에 SpringMVC 설정파일 등록   


```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app ...>
  
  <display-name>SpringMVC</display-name>

  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>
      org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/config/presentation/spring-mvc.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  
</web-app>
```
- web.xml 파일에 인코딩 설정   


```html
<?xml version="1.0" encoding="UTF-8"?>
<web-app ...>
  <display-name>SpringMVC</display-name>
  
  <filter>
    <filter-name>Encoding Filter</filter-name>
    <filter-class>
      org.springframework.web.filter.CharacterEncodingFilter
    </filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>Encoding Filter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping> 
</web-app>
```

### 컨트롤러
- Spring MVC에서 제공하는 Controller 인터페이스를 implements하여 로직 구현   

```java
public class LoginController implements Controller {
  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    //1. 사용자 입력정보(id, password)추출
    String id = request.getParameter("id");
    String pwd = request.getParameter("password");
    
    //2. DB 연동
    UserDAO dao = new UserDAO();
    UserVO user = dao.getUser(id, pwd);

    //3. 적절한 화면으로 이동
    ModelAndView mav = new ModelAndView();
    if(user != null){
      mav.addObject("message", "로그인 성공");
      mav.addObject("userVo", user);
      mav.setViewName("loginResult");
    }else{
      mav.addObject("message", "로그인 실패");
      mav.setViewName("login");
    }
    return mav;
  }
}
```

- 로직 수행 결과를 ModelAndView 객체에 담아 리턴
- 직접 특정 JSP를 식별하지않고, 뷰 이름을 DispatcherServler으로 돌려보낸다
  - 컨트롤러가 특정 뷰에 종속되지 않는다
- 뷰 이름은 결과를 만들어낼 실제 뷰를 찾는데 사용되는 논리적 이름
- DispatcherServler은 뷰 리졸버에게 논리적 뷰이름을 실제 뷰 구현체에 매핑하도록 요청   

### 스프링 MVC 설정파일   
- Spring MVC 설정파일인 spring-mvc.xml파일에 HandlerMapping, Controller, ViewResolver를 등록   

```html
<?xml version="1.0" encoding="UTF-8"?>
<beans ...>
  
  <!-- HandlerMapping -->
  <bean id="handlerMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
    <property name="mappings">
      <props>
        <prop key="/hello.do">helloController</prop>
      </props>
    </property>
  </bean>
  
  <!-- Controller -->
  <bean id="helloController" class="com.mymall.test.controller.HelloController"></bean>
  
  <!-- ViewResolver -->
  <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix">
      <value>/WEB-INF/view/</value>
    </property>
    <property name="suffix">
      <value>.jsp</value>
    </property>
  </bean>
  
</beans>

```

- HandlerMapping
  - SimpleUrlHandlerMapping 클래스는 클라이언트의 요청 URL과 매핑되는 Controller를 찾는다
  - hello.do라는 요청에 대해 helloController라는 이름의 Controller가 요청을 처리한다
- Controller
  - 앞서 작성한 HelloController를 Bean으로 등록한다
  - HandlerMapping에 설정한 Controller이름과 동일한 id 속성값으로 등록한다
- ViewResolver
  - jsp를 view로 사용할 경우, InternalResourceViewResolver 사용
  - 접두사(prefix)와 접미사(suffix)를 지정하여 view 정보를 완성
  - ModelAndView에서 view 이름이 login 이라면, 완성된 정보는 /WEB-INF/view/login.jsp   

### jsp 작성
- 일반적인 jsp와 동일하게 구현
