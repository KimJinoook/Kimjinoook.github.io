---
layout: post
title:  "3. session방식 접근제어"
subtitle:   ""
categories: framework
tags: egov
comments: false
header-img: 
---

- 세션 방식으로 접근제어 권한관리를 설정
  - Spring Interceptor 기반으로 인가처리
  - globals.properties 파일로 설정   

## 설정
### pom.xml   
```html
<dependency>
  <groupId>org.egovframe.rte</groupId>
  <artifactId>org.egovframe.rte.fdl.access</artifactId>
  <version>${org.egovframe.rte.version}</version>
</dependency>
```

### egov-com-access.xml
- 버전마다 명칭 다를 수 있다   
- src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-access.xml   


#### xml namespace 및 schema 설정   
```xml
<beans xmlns=“http://www.springframework.org/schema/beans”
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:egov-access="http://maven.egovframe.go.kr/schema/egov-access"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    http://maven.egovframe.go.kr/schema/egov-access
    http://maven.egovframe.go.kr/schema/egov-access/egov-access-4.0.0.xsd">
```   

#### 접근제어 설정   
```xml
<egov-access:config id="egovAccessConfig"
  globalAuthen="session"
  mappingPath="/**/*.do"
  dataSource="dataSource"
  loginUrl="/uat/uia/egovLoginUsr.do"
  accessDeniedUrl="/uat/uia/egovLoginUsr.do?auth_error=1"
  sqlAuthorityUser="select concat(b.user_se, b.user_id) userid, a.author_code authority
    from comtnemplyrscrtyestbs a, comvnusermaster b
    where a.scrty_dtrmn_trget_id = b.esntl_id"
  sqlRoleAndUrl="select a.role_pttrn url, b.author_code authority
    from comtnroleinfo a, comtnauthorrolerelate b
    where a.role_code = b.role_code
    and a.role_ty = 'url'
    order by a.role_sort"
  requestMatcherType="regex"
  excludeList="/uat/uia/**, /uss/umt/**, /sec/rnc/**, /sym/ccm/zip/**, /uss/ion/lsi/**,
    /cmm/fms/getImage.do, /uss/ion/bnr/getBannerImage.do, /index.do, /EgovLeft.do, /EgovContent.do,
    /EgovTop.do, /EgovBottom.do, /EgovModal.do, /cop/cmt/selectArticleCommentList.do,
    /cop/stf/selectSatisfactionList.do, /cop/stf/selectSingleSatisfaction.do
    /cop/cmt/updateArticleCommentView.do”
/>
```

- globalAuthen : 필수 / global.properties 설정과 동일하게 적용
- dataSource : 필수 /  DBMS 설정 dataSource
- loginUrl : 필수/ 로그인 페이지 URL
- accessDeniedUrl : 필수 / 권한이 없는 경우 호출되는 페이지
- sqlAuthorityUser : 필수 / 인증된 사용자의 권한 조회
- sqlRoleAndUrl : 필수/ Role 및 Url 패턴
- requestMatcherType : 선택 / 패턴매칭 방식
  - 디폴트 : regex
- excludeList : 필수 / 접근제한 예외처리 URL
  - 속성에 추가된 url은 접근제어를 하지 않기 때문에, 로그인화면 등의 접근제어가 필요없는 페이지만 추가
  -  회원관리 : /uat/uia/**
  -  약관확인 : /uss/umt/**
  -  처음화면 : /index.do
  -  로그인화면이미지 : /cmm/fms/getImage.do
  -  좌측메뉴 : /EgovLeft.do
  -  초기화면 : /EgovContent.do
  -  상단메뉴 : /EgovTop.do
  -  하단메뉴 : /EgovBottom.do
  -  모달팝업 : /EgovModal.do
  -  만족도조사 : /cop/stf/selectSatisfactionList.do
  -  만족도조사 선택 : /cop/stf/selectSingleSatisfaction.do
  -  댓글 : /cop/cmt/selectArticleCommentList.do
  -  댓글 선택 : /cop/cmt/updateArticleCommentView.do   

***

- https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-handlermapping-interceptor

