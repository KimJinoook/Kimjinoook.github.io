---
layout: post
title:  "4. crypto 간소화"
subtitle:   ""
categories: framework
tags: egov
comments: false
header-img: 
---

- ARIA 블록암호 알고리즘 기반 암복호화 설정 간소화
- 내부적으로 필요한 설정 보유, XML Schema를 통해 필요 설정 추가 가능
- globals.properties 설정파일의 중요정보를 암복호화 처리 가능
  - url, username등
- properties 파일에 암호화 데이터 설정 후 #(…} 표현식으로 복호화 가능
  - #{egovEnvCryptoService.decrypt('…')}   

***

## 설정
### pom.xml   
```xml
<dependency>
  <groupId>org.egovframe.rte</groupId>
  <artifactId>org.egovframe.rte.fdl.crypto</artifactId>
  <version>${org.egovframe.rte.version}</version>
</dependency>
```

### context-crypto.xml
- src/main/resources/egovframework/spring/com/context-crypto.xml   

#### xml namespacee 및 schema 설정   
```xml
<beans xmlns=“http://www.springframework.org/schema/beans”
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns:egov-crypto="http://maven.egovframe.go.kr/schema/egov-crypto"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
    http://maven.egovframe.go.kr/schema/egov-crypto
    http://maven.egovframe.go.kr/schema/egov-crypto/egov-crypto-4.0.0.xsd"
>
```

#### config 설정
```xml
<egov-crypto:config id="egovCryptoConfig"
  initial="true"
  crypto="true"
  algorithm="SHA-256"
  algorithmKey="(사용자정의 값)"
  algorithmKeyHash="(생성값)"
  cryptoBlockSize="1024"
  cryptoPropertyLocation="classpath:/egovframework/egovProps/globals.properties"
/>
```

- initial : 필수 / globals.properties 연계 url, username, password값 로드 여부
- ctypto : 필수 / 암호하 여부
- algorithm : 필수 / 암호화 알고리즘
- algorithmKey : 필수 / 암호화 키
- algorithmKeyHash : 필수 / 암호화 키 해쉬값
- cryptoBlockSize : 필수 / 암호화 키 블록사이즈
- cryptoPropertyLocation : 선택 / 암호화 설정값이 포함된 설정파일 경로
  - 디폴트 : classpath:/egovframework/egovProps/globals.properties
- 암호화 키와 해쉬값은 아래 테스트를 통해 생성된 값 입력   

#### 키, 해쉬값 생성   
```java
public class EgovEnvCryptoAlgorithmCreateTest {
  // 암호화 알고리즘
  public String algorithm = "SHA-256";
  
  // 암호화 키
  public String algorithmKey = "(사용자정의 값)";
  
  // 암호화 키 블럭사이즈
  public int algorithmBlockSize = 1024;
  
  public static void main(String[] args) {
    EgovEnvCryptoAlgorithmCreateTest cryptoTest = new EgovEnvCryptoAlgorithmCreateTest();
    EgovPasswordEncoder egovPasswordEncoder = new EgovPasswordEncoder();
    egovPasswordEncoder.setAlgorithm(cryptoTest.algorithm);
    
    // 암호화 키 해쉬값
    String algorithmKeyHash = egovPasswordEncoder.encryptPassword(cryptoTest.algorithmKey);
    System.out.println("algorithmKeyHash >>> " + algorithmKeyHash);
  }
}
```


### 데이터베이스 연결항목 암호화
- globals.properties 데이터베이스 연결항목 (url, username, password)등을 암호화하여 관리   

```xml
<egov-crypto:config id="egovCryptoConfig"
  initial="true"
  crypto="true"
  algorithm="SHA-256"
  algorithmKey="(사용자정의 값)"
  algorithmKeyHash="(생성값)"
  cryptoBlockSize="1024"
/>

<bean name="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
  <property name="useCodeAsDefaultMessage">
    <value>true</value>
  </property>
  <property name="basenames">
    <list>
      <value>classpath:/org/egovframe/rte/fdl/property/messages/properties</value>
    </list>
  </property>
</bean>
```

##### 데이터베이스 연결항목 인코딩값 생성   
- 환경설정 파일에 설정한 Crypto 간소화서비스 정보에 EgovEnvCryptoServiceImpl 클래스를 이용하여 암호화   

```java
public class EgovEnvCryptoUserTest {
  public static void main(String[] args) {
    String driverClassName = "driverClassName";
    String url = "url";
    String userName = "username";
    String password = "password";
    
    // 환경설정 파일 로드
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/context-crypto.xml");
    EgovEnvCryptoService cryptoService = context.getBean(EgovEnvCryptoServiceImpl.class);
    
    // 데이터베이스 드라이버 암호화
    System.out.println("driverClassName >>> " + cryptoService.encrypt (driverClassName));
    
    // 데이터베이스 접속 URL 암호화
    System.out.println("url >>> " + cryptoService.encrypt(url));
    
    // 데이터베이스 접속 아이디 암호호
    System.out.println("userName >>> " + cryptoService.encrypt(userName));
    
    // 데이터베이스 접속 비밀번호 암호화
    System.out.println("password >>> " + cryptoService.encrypt(password));
  }
}
```

#### 생성된 데이터베이스 연결항목을 생성값에 입력   

```html
globals.properties

#mysql
Globals.mysql.DriverClassName = (생성값)
Globals.mysql.Url = (생성값)
Globals.mysql.UserName = (생성값)
Globals.mysql.Password = (생성값)
```

#### context-datasource.xml 파일의 데이터베이스 연결항목 복화 연결 설정   
- 데이터베이스 설정파일에서 데이터베이스 연결항목을 디코딩하는 설정 방법 제공   

```xml
<beans profile="mysql">
  <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="#{egovEnvCryptoService.decrypt('${Globals.mysql.DriverClassName}')}"/>
    <property name="url" value="#{egovEnvCryptoService.getUrl()}" />
    <property name="username" value="#{egovEnvCryptoService.getUsername()}" />
    <property name="password" value="#{egovEnvCryptoService.getPassword()}" />
  </bean>
</beans>
```

***

## WAS VM arguments 환경변수 등록
- 선택사항
- 간소화설정파일에서 algorithmKey, algorithmKeyHash 키의 노출을 피하고 싶은 경우
- 간소화설정파일에서 해당 키를 삭제하고 WAS VM arguments 환경변수를 등록
- globals.properties 설정파일의 중요 정보 Url, UserName, Password항목에 대해 암/복호화 처리   
  - Degov.crypto.algorithmKey="(사용자정의 값)" -Degov.crypto.algorithmKeyHash="(생성값)"   

***

## ARIA
- https://seed.kisa.or.kr/kisa/algorithm/EgovAriaInfo.do


