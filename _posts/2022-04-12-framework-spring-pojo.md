---
layout: post
title:  "2. POJO"
subtitle:   ""
categories: framework
tags: spring
comments: false
header-img: 
---

- Plain Old Java Object
- 오래된 방식의 간단한 자바 오브젝트
- Java EE 등의 중량 프레임워크를 사용하면서, 해당 프레임워크에 종속된 무거운 객체를 만들게 된 것에 반발해서 사용하게 된 용어
- 특정 자바 모델이나 기능, 프레임워크 등을 따르지 않는 자바 오브젝트를 지칭하는 말
- 언어 사양 외에 어떠한 제한에도 묶이지 않은 자바 오브젝트
  - 다음과 같은 행동을 해서는 안된다
    - 미리 정의된 클래스의 확장
      > public class Exam extends java.servlet.http.HttpServlet{}   
    - 미리 정의된 인터페이스의 구현
      > public class Exam implements javax.ejb.EntityBean{}   
    - 미리 정의된 어노테이션 포함
      > @javax.persistence.Entity public class Exam{}   


## EJB와 스프링 비교   
- EJB   

```java
package com.ejb.session;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloWorldBean implements SessionBean{
  public void ejbActivate(){}
  public void ejbPassivate(){}
  public void ejbRemove(){}
  public void setSessionContext(SessionContext ctx){}
  public void ejbCreate(){}
  
  //EJB의 핵심 비즈니스 로직
  public String sayHello() {
    return "Hello World!";
  }
}
//일반적으로 필요하지도 않은 메서드를 강제로 구현하게 한다
```   

- 스프링

```java
package com.spring;

public class HelloWorldBean{
  public String sayHello() {
    return "Hello World!";
  }
}
//구현하거나 확장하지도 않고, 어떤것도 import하지 않았다
//모든 구문이 POJO
//DI를 활용해 조립하며, 애플리케이션 객체간 상호간의 결합도를 낮춘다
```
