---
layout: post
title:  "3. main메서드"
subtitle:   ""
categories: framework
tags: springboot
comments: false
header-img: 
---

```java
package com.it.study;

@SpringBootApplication
public class TestApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestApplication.class, args);
  }
}

```

- main 메서드의 역할
  - SpringApplication.run 메서드를 호출해 어플리케이션을 실행
- @SpringBootApplication
  - 아래 세가지 어노테이션으로 구성되어있다
  - @EnableAutoConfiguration
    - 스프링부트는 개발에 필수적인 설정들의 처리가 미리 되어있다
    - 해당 어노테이션을 통해 다양한 설정들의 일부가 자동으로 완료된다
  - @ComponentScan
    - 기존 방식은 xml설정에 빈 등록 및 수동으로 ComponentScan을 여러 개 선언
    - 스프링부트는 해당 어노테이션에 의해 자동으로 컴포넌트 클래스 검색
      - @Controller 등의 클래스
    - 스프링 어플리케이션 콘텍스트 (IoC 컨테이너)에 빈으로 자동 등록
  - @Configuration
    - 해당 어노테이션이 선언된 클래스는 자바 기반의 설정파일로 인식된다
    - xml설정에 많은 시간을 소모하지 않아도 된다
