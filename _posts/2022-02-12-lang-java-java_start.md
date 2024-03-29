---
layout: post
title:  "1. 기본 골격과 구성"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 구조 
### a.실행구조
* 일반 프로그램 : 하드웨어 - 운영체제 - 프로그램
* 자바 프로그램 : 하드웨어 - 운영체제 - 자바가상머신(JVM) - 자바프로그램
* 운영체제의 차이를 jvm이 해결해주어 자바프로그램 하나로 서로다른 운영체제에서 실행이 가능하다.

### b. 실행과정
>1. 소스코드 (파일명.java)   
>1. 컴파일 (javac.exe)   
>3. 결과물 (파일명.class)
>4. 실행 (java.exe)

* 사용자가 작성한 소스코드를 기계가 이해할 수 있는 바이트 코드로 변환하는 것을 컴파일이라 한다.

### c. JVM의 메모리구조
- 자바 가상머신에 필요한 메모리는 운영체제가 할당
- 운영체제가 할당한 메모리 기반
  - jvm 실행
  - 자바응용프로그램 실행
 
### d. jvm의 메모리 모델
- 운영체제로부터 메모리 할당
- 데이터의 특성에 따라 메모리 공간 나눈 후 분류

### e. 메모리 영역
- 메서드영역
  - 클래스 사용 시, 클래스에 대한 정보 저장
  - 클래스변수 (static변수) 생성 후 저장
- 힙(heap)
  - 인스턴스가 생성되는 공간
  - 인스턴스 변수 생성 후 저장
- 호출스택 (call stack / execution stack)
  - 메서드 작업에 필요한 메모리 공간 제공
  - 지역변수들과 연산의 중간결과 저장
  - 메서드 작업 종료 후 메모리 공간 반환

### f. 클래스패스
- 클래스의 경로(클래스가 존재하는 경로)
  - 환경변수 path : 확장자가 exe인 파일을 찾는 경로
  - 환경변수 classpath : 확장자가 class인 파일을 찾는 경로

- cmd 내 환경변수 확인
  - echo %classpath%

- cmd 내 환경변수 임시 추가
  - set classpath=.;
    - 현재 디텍토리 추가
  - set classpath=.;.\sub
    - 현재디텍토리와, 현재 파일의 sub파일 추가


***

## 2. 골격
* 최소한 **하나 이상의 클래스**가 있어야 한다.
* 클래스의 이름은 파일명과 **동일**해야 한다.
* 파일명과 동일한 클래스에 **main 메서드**가 있어야 한다.

```java
public class Hello{
    public static void main(String[] args){
        System.out.println("Hello");
    }
}
```
#### - 파일명과 동일한 클래스 내에 main() 이라는 main메서드가 존재한다.
#### - 문장의 끝은 세미콜론 ';'을 붙여준다.   

***

## 3.  주석
* 컴퍼일의 대상에서 제외되는 문장
* 특정 코드에 간단한 설명을 달 때 사용된다.
> /* ~ */  : 블록 단위 주석(여러 줄 주석)      
// : 행 단위 주석   
/** ~ */ : 자바 고유 Document 주석, 사용자 정의 Documentation Api를 만들 때 사용된다.
```java
public class Hello{
    public static void main(String[] args){
        System.out.println("Hello");
    }//주석은 컴파일에 영향을 미치지 않는다.
}
```

***

## 4. package
* 비슷한 유형의 클래스를 묶어놓은 것
* 해당 package 내에 있는 클래스를 사용하려면 import 라는 예약어를 이용한다.
> import java.lnag.*;   
> - java의 lang 패키지 안의 모든 클래스를 현재 파일에서 사용할 수 있도록 하겠다는 뜻

> import 패키지명.클래스명;   
import java.lang.String;
import java.lang.System;   
import java.io.File;   
import java.util.Date; 등등

#### - java.lang 패키지는 사용빈도가 높아 자동으로 적용되어있다.

```java
import java.lang.*; // import 예약어를 이용하여 java.lang의 모든 클래스를 적용
public class Hello{
    public static void main(String[] args){
        System.out.println("Hello");
    }
}
```
***
## 5. class
* 여러 개의 클래스를 하나의 파일에서 사용할 수 있다.
* **public**이라는 예약어를 사용할 수 있는 클래스는 **하나*뿐이다.
* main()메서드는 파일명과 동일한 클래스 내에 있어야 한다.

```java
import java.lang.*;
public class Hello2{
    public static void main(String[] args){
        System.out.println("Hello Java!!");
        System.out.println("Hello Jsp!!");
    }
}

class Test{
    public void write(){
        System.out.println("Test Class!!");
    }
}
```

***

## 6. 명명 규칙
* 클래스, 메서드, 필드   
    + 첫 글자는 $, _, 영문자
    + 특수, 공백문자는 포함될 수 없다.
    + 숫자는 첫 글자가 아닐 경우 사용 가능하다.
    + 예약어는 사용할 수 없다.


* 권장사항   
    + 클래스 : 첫글자는 $, _, 대문자
    + 메서드 : 첫글자는 $, _, 소문자
    + 변수 : 소문자
    + 합성어의 첫글자는 대문자
        > 낙타표기법 - HelloJava
    + 상수 : 모든 문자를 대문자로 구성, 둘 이상의 단어는 _로 연결
        > final int COLOR = 7;   
        > final int COLOR_RAINBOW = 5;
