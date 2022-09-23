---
layout: post
title:  "13. 패키지"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 패키지란
- 클래스의 묶음
- **서로 관련된 클래스들끼리 묶어 효울적으로 관리**
- 물리적으로 하나의 폴더
  - 패키지에 속한 클래스는, 해당 파일에 존재하는 class파일이어야함
- 클래스의 실제이름
  - 패키지명을 포함한 이름(java.lang.String)
- jar
  - 클래스와 관련된 파일들이 압축되어 있는 것
  - java_jdk_jre_lib_rt.jar 
    - jdk의 기본 클래스들이 기능할 수 있는 이유

***

## 2. 패키지의 선언   
> package 패키지명;   

- 선언문은 주석 공백 제외 첫 번째 문장이어야 한다.
- 하나의 소스파일에 **한번만 선언**가능
- **모든 클래스는 반드시 하나의 패키지에 포함되어야함**
  - 패키지를 선언하지 않으면, 이럼읎는 패키지 기본 제공
- 패키지는 점(.)을 구분자로 하여 계층구조로 구성 가능
- cmd 내 컴파일 시
  - javac -d . TestPackage.java
  - -d : 패키지 생성 옵션
  - . : 패키지 생성 디렉토리(.은 현재 폴더)
  - javac -d subdir TestPackage.java
  - 현재폴더의 subdir 폴더에 패키지가 생성됨

## 3. static import
- import 문 사용
  - 클래스의 패키지명 생략
- static import 문 사용
  - static멤버를 호출할 때 클래스명 생략 가능   

```java

//import java.util.*;

import static java.lang.System.out;
import static java.lang.Math.random;
import static java.lang.Math.PI;
//import static java.lang.Math.*;


public class StaticImportTest {

	public static void main(String[] args) {
		//System.out.println(Math.random()); // 기존
		out.println(random()); // static import로 클래스명 생략

		//System.out.println(Math.PI);
		out.println(PI);
	}
}
```
