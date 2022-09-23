---
layout: post
title:  "16. 문자열 String"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. String
- 변경이 불가능한 문자열  
- 상수 개념    

> String str = "java";   
> String str = new String("java");   

- 문자열을 더하거나 변경하면 새로운 String객체 생성
- 기존 객체는 버려지며 쓰레기가 된다.   
- 같은 문자열은 하나의 String 인스턴스 공유   

> String str1 = "hi";   
> String str2 = "hi";   
> String str3 = "hello";   
> String str4 = new String("hi");

- str1과 str2는 같은 인스턴스 공유
- str4는 새로 객체를 생성했기 때문에, 인스턴스를 공유하지 않는다.   


```java
//equals가 아닌 ==를 통해 주소값 
public class StringTest {

	public static void main(String[] args) {
		
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = "Java";
		String str4 = new String("Hello");
		String str5 = new String("Hello");
		
		if(str1==str2) {//참조형에서는 ==이 주소 비교
			System.out.println("str1, str2는 같은 주소=>동일인스턴스 참조");
		}else {
			System.out.println("다른 인스턴스 참조");
		}
		if(str2==str3) {
			System.out.println("str2, str3는 같은 주소=>동일인스턴스 참조");
		}else {
			System.out.println("다른 인스턴스 참조");
		}
		
		if(str4==str5) {
			System.out.println("str4, str5는 같은 주소=>동일인스턴스 참조");
			
		}else {
			System.out.println("다른 인스턴스 참조");
			
		}
		if(str1==str5) {
			System.out.println("str1, str5는 같은 주소=>동일인스턴스 참조");
			
		}else {
			System.out.println("다른 인스턴스 참조");
			
		}
	}
}
```
- concat()메서드
	- 두 문자열을 결합
	- 서로 다른 두개의 문자열을 이어서 새로운 String 인스턴스 생성   

```java
public class ConcatTest {

	public static void main(String[] args) {
		// concat() - 두 문자열 결합
		String str1 = "Happy";
		String str2 = "and";
		String str3 = "Smile";
		String str4=str1.concat(str2).concat(str3);
		
		System.out.println(str4);
		}
	}
}
```

## 2. StringBuilder, StringBuffer
- 변경가능한 문자열
- 내부적으로 문자열 편집을 위한 버퍼를 가지고 있다
- 인스턴스 생성 시 크기 지정 가능   

### a. StringBuilder
- Tgread safe하지 않으며 속도는 더빠름
- +기호 대신 append()메서드 이용  
- 문자열 저장을 위한 char형 배열의 참조변수를 인스턴스 변수로 선언
- 생성자
  - StringBuilder()
    - 16개의 문자 저장 버퍼 생성
    - 문자 저장에 따라 자동으로 증가
  - StringBuilder(int a)
    - a개의 문자저장버퍼 생성
  - StringBuilder(String str)
    - str.length()+16개의 문자저장 버퍼 생성
- 멀티쓰레드 프로그래밍에서는 사용하지 않는다   

```java
public class StringBuilderTest1 {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder("AB");
		sb.append(25);
		sb.append('y').append(true);
		System.out.println(sb); //AB25ytrue
		
		sb.insert(2, false);
		sb.insert(sb.length(), 'Z');
		System.out.println(sb); //ABfalse25ytrueZ

    StringBuilder sb = new StringBuilder("ABCDEFG");
		System.out.println(sb.reverse()); //GFEDCBA
		
		StringBuilder sb2 = new StringBuilder("990101-1112222");
		sb2.deleteCharAt(sb2.lastIndexOf("-"));
		System.out.println(sb2); //9901011112222
	}
}
```

## 3. StringTokenizer
- 문자열을 지정된 구분자를 기준으로 토큰으로 잘라낸다.
- 구분자로 단 하나의 문자밖에 사용하지 못함
- split()과 유사   

```java
import java.util.StringTokenizer;

public class TokenizerTest1 {

	public static void main(String[] args) {
		
		String str = "100,200,300,400";
		StringTokenizer st = new StringTokenizer(str,",");
		
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
		}
		
		System.out.println();
		String[] arr = str.split(",");
		for(String s : arr) {
			System.out.println(s);
		}
		
		str = "x=100*(200+300)/2";
		StringTokenizer st2 = new StringTokenizer(str,"+-*/=()",true);
		// 단 한문자의 구분자만 사용할 수 있기 때문에, 전체가 하나의 구분자가 아니라
		// 각각의 문자가 모두 구분자
		// split은 +-*/=() 전체를 하나의 구분자로 
		// true : 구분자도 토큰으로 간주
		System.out.println();
		while(st2.hasMoreTokens()) {
			String token = st2.nextToken();
			System.out.println(token);
		}// true 없을 시, {x,100,200,300,2}만 출력
	
	}
}
```
- split은 빈 문자열도 토큰으로 인식
- StringTokenizer는 빈 문자열을 토큰으로 인식하지 않는다.   

```java
import java.util.StringTokenizer;

public class TokenizerTest3 {

	public static void main(String[] args) {
		String str = "100,,,200,300";
		
		String[] arr= str.split(",");
		int n=0;
		for(String s: arr) {
			System.out.println(s+"|");
			n++;
		}
		System.out.println("개수 : " +n);
		
		System.out.println();
		StringTokenizer st = new StringTokenizer(str,",");
		n=0;
		while(st.hasMoreTokens()) {
			String s = st.nextToken();
			System.out.println(s+"|");
			n++;
		}
		System.out.println("개수 : " +n);
	}
}
```

## String클래스의 메서드들 
### a. equals()
- 기본자료형에서 등가연산자(==)는 값을 비교한다. (a==b)
- 참조형에서 등가연산자는  주소값을 비교한다.
- 문자열 내용 비교는 String 클래스의 equals()메서드 이용

```java
import java.util.*;
class If{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("약관에 동의합니까(Y/N)");
    String agree = sc.nextLine();
    if (agree.equals("Y"){
      System.out.println("동의하셨습니다");
    }
  }
}
```

### b. toString()
- Object클래스의 toString()메서드
- 해당 객체가 어떤 객체인지 문자열로 표현하는 값 리턴
	- 클래스명@16진수 해시코드
- p.toString()과 p를 출력한 결과값 동일
	- 클래스의 멤버가 할당된 주소를 숨기려는 특성=>
	- 직접 주소 칠력시, 자동으로 출력형식의 메서드 (toString)로 연결
- 자동으로 호출되는 경우
	- System.out.pritnln() 메서드에 매개변수로 들어가는 경우
	- 객체에 대하여 더하기 연산을 하는경우
- 클래스에서 오버라이딩 후 사용 가능
- toString 결과값 확인   



```java
class Person{
	public void showInfo() {
		System.out.println(this);
	}
}

public class ToStringTest {

	public static void main(String[] args) {
		Person p = new Person();
		p.showInfo();
		
		System.out.println("p객체 문자열"+p.toString());
		System.out.println("p"+p);
		System.out.println(p);

	}
}
```
- toString 오버라이딩   

```java
class Person2{
	private String name;
	private int age;
	
	//object의 toString()메서드를 오버라이딩
	public String toString() {
		return "Person2[name="+name+", age="+age+"]";
	}
}

public class ToStringTest2 {

	public static void main(String[] args) {
		Person2 p =new Person2();
		System.out.println(p);
		System.out.println(p.toString());
		

	}

}
```

### c. length
- 변수,length()
- 문자열의 길이 확인

> String a = "가나다라마바";
> int b = a.length(); // b=6

### d. charAt
- 변수.charAt(i)
- String의 문자열중 한글자만 선택해 char 타입으로 변환
- i값은 String 문자열 내 index번호
- index는 0부터 문자길이-1 까지

```java
class cA{
	public static void main(String[] args){
		String str = "가나다라마"
		// index 0번 : 가
		// index 4번 : 마
		char c = str.charAt(0); // c에 '가'가 저장된다.
		char d = str.charAt(3); // d에 '라'가 저장된다.
		System.out.println(c); //가
	}
}
```

-nextLine 뒤에 붙여 바로 뽑아내는 것도 가능하다.
> String a= "가나다라마";
> char b = sc.nextLine().charAt(1);   

### e. indexOf, substring
- indexOf
	- 문자열을 첫부분부터 검색, 입력한 문자열을 만나는 위치를 int로 반환
- lastIndexOf
	- 문자열을 마지막 부분부터 검색, 입력한 문자열을 만나는 위치를 int로 반환
- substring
	- 일부 문자열 추출   

```java
public class StringTest {

	public static void main(String[] args) {
		            //012345678901234567890123456789
		String str = "Hello Java, Hi Java!";
		char ch = str.charAt(4); //o
		System.out.println("ch="+ch);
		
		int idx = str.indexOf("Java");
		System.out.println("앞 Java의 위치 : "+idx); //6
		System.out.println("뒤 Java의 위치 : "+str.lastIndexOf("Java")); //15
		
		String sub = str.substring(6,10); //6<= x < 10, Java
		System.out.println("sub="+sub);
		System.out.println(str.substring(15)); //15부터 끝까지, Java!
		
		String s = "www.nate.com";
		if(s.startsWith("www")) {
			System.out.println("www로 시작함");
			
		}
		if (s.endsWith(".com")) {
			System.out.println(".com으로 끝남");
		}
		
		            //01234567890123
		String email="hong@gmail.com"; 
					//
		if(email.indexOf("@") != -1&& email.indexOf(".")!=-1
				&& email.indexOf("@")<email.indexOf(".")) {
			System.out.println("이메일 규칙이 올바르다");
		}else {
			System.out.println("이메일 규칙이 올바르지 않다");
		}
		
		int gol = email.indexOf("@");
		int dot = email.lastIndexOf(".");
		//hong만 추출
		System.out.println(email.substring(0,gol));
		//com
		System.out.println(email.substring(dot+1));
		//gmail
		System.out.println(email.substring(gol+1,dot));
	}
}
```

### . String[] split
- 특정 문자들을 경계로 하여 문자열을 여러개의 토막으로 분리
- 인수로 배열을 전달하면, 토막 문자열을 배열형태로 리턴   

```java
package com.day23.extra;

public class SplitTest {

	public static void main(String[] args) {
		//String[] split(String regex)
		str = "java,oracle,jsp,spring";
		String[] arr = str.split(",");
		for(String s3 : arr) System.out.println(s3);
		
		System.out.println("\n\n");
		str="html.css.js.jquery";
		String[] arr2 = str.split("\\.",3); //특정 기호로 잘리지않으면 역슬래시 두개
						//두번째 매개변수는 limit, 자를 개수
		for(String s3 : arr2) System.out.println(s3);

	}
}
```
### . replace
- 문자열을 다른 문자열로 교체   

```java
public class SplitTest {

	public static void main(String[] args) {
		String str = "123456789";
		String s = str.replace('7', '칠');
		String s2 = str.replace("89", "여덟아홉");
		System.out.println(s);
		System.out.println(s2);
	}
}
```
### . trim
- 앞위 공백 제거   

```java
s="             java spring!!          ";
String s2= s.trim();
System.out.println("s2=["+s2+"]"); // [java spring!!]
```
