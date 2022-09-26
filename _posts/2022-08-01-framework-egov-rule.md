---
layout: post
title:  "1. 전자정부 표준 프레임워크"
subtitle:   ""
categories: framework
tags: egov
comments: false
header-img: 
---

## 적용기준
### 아키텍쳐 규칙
- Annotation 기반 Spring MVC 준수
  - @Controller 및 @RequestMappin을 통한 URL mapping 활용
  - view와 model 부분을 controller를 통해 분리
- Annotation 기반 layered architecture 준수
  - 화면처리, 업무처리, 데이터처리 부분에 대하여 각각 @Controller, @Service, @Repository 활용
- 업무처리를 담당하는 서비스클래스는 EgovAbstractServiceImpl을 확장하고 업무에 대한 특정 인터페이스 구현
- 데이터 처리를 담당하는 DAO 클래스(@Repository)는 EgovAbstractDAO 또는 EgovAbstractMapper를 상속
  - EgovAbstractDAO : iBatis
  - EgovAbstractMapper : MyBatis
  - Hibermate/JPA 적용시에는 예외, 데이터처리 규칙 참조
