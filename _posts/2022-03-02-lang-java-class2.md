---
layout: post
title:  "18. 내부클래스"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 내부클래스란
- 클래스 내에 선언된 클래스
- 주로 AWT나 Swing과 같은 GUI어플리케이션 이벤트처리에 사용
- 클래스 내에 내부클래스를 선언하면 두 클래스의 멤버 간 접근이 용이
- 외부에는 불필요한 클래스를 감춘다
- 내부클래스 선언 위치에 따른 종류
  - 인스턴스 클래스
    - 외부클래스의 멤버변수 선언위치에 선언
    - 외부클래스의 인스턴스멤버처럼 다루어진다
  - 스태틱 클래스
    - 외부클래스의 멤버변수 선언위치에 선언
    - 외부캘르새의 static 멤버처럼 다루어진다
  - 지역 클래스
    - 외부클래스의 메서드나 초기화블럭 안에 선언
    - 선언된 영역 내부에서만 사용가능
  - 익명 클래스
    - 클래스의 선언과 객체의 생성을 동시에하는 일회용 클래스
- 내부클래스도 클래스이기에 abstract나 final 등의 제어자 사용 가능
- 추가적으로 private, protected 제어자 사용 가능   

```java
public class InnerTest1 {
	class InstanceInner{
		int iv=100;
		// static int cv = 100; // error : 내부클래스 중 static클래스는 static 멤버를 가질 수 있다
		final static int CONST = 100; //static final은 상수이므로 사용 가능
	}
	static class StaticInner{
		int iv = 200;
		static int cv = 200; // static 클래스만 static 멤버 정의 가능
	}
	void method() {
		class LocalInner{
			int iv = 300;
			// static int cv = 300; // error
			final static int CONST = 300;
		}
	}
	public static void main(String[] args) {
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);

	}
}
```
- static 내부 클래스는 외부클래스의 static 멤버만 접근 가능
- 내부클래스는 외부클래스의 지역변수중 final이 붙은 상수만 접근 가능
  - jdk8.0부터는 지역변수도 접근 가능   

```java
public class InnerTest2 {
	private int outerIv = 0;
	static int outerCv = 0;
	
	class InstanceInner{
		int iiv = outerIv;
		int icv = outerCv;
	}
	
	static class StaticInner{
		// int siv = outerIv; // error : 외부클래스의 static 멤버만 접근 가능
		static int scv = outerCv;
	}
	
	public void method() {
		int lv = 0; //지역변수
		final int LV = 0;
		
		class LocalInner{
			int liv = outerIv;
			int lcv = outerCv;
			int llv = lv; // jdk8.0부터는 지역변수도 접근가능
			int lLv = LV;
		}
	}
	
	public static void main(String[] args) {

	}
}
```
- 인스턴스 내부 클래스의 인스턴스를 생성하려면 외부클래스의 인스턴스 먼저 생성해야한다.
- 스태틱 내부 클래스의 인스턴스는 외부클래스 먼저 생성하지 않아도 된다.   

```java
class Outer{
	class InstanceInner{
		int iv = 100;
	}
	static class StaticInner{
		int iv = 200;
		static int cv = 300;
	}
	void method() {
		class LocalInner{
			int iv = 400; // 외부에서 접근 불가
		}
	}
}


public class InnerTest3 {
	
	
	public static void main(String[] args) {
		//1 static 내부클래스의 인스턴스 생성
		Outer.StaticInner si = new Outer.StaticInner();
		System.out.println(si.iv);

		//2 인스턴스 내부 클래스의 인스턴스 생성
		Outer oc = new Outer();
		Outer.InstanceInner ii = oc.new InstanceInner();
		System.out.println(ii.iv);
		
	}
}
```

- 내부 클래스의 this 확인   

```java
class Outer2{
	int value = 10;
	class Inner{
		int value = 20;
		void method() {
			int value = 30;
			System.out.println("value="+value); //30
			System.out.println("this.value="+this.value); // 20
			System.out.println("Outer2.this.value="+Outer2.this.value); //10
		}
	}
}

public class InnerTest4 {
	
	
	public static void main(String[] args) {
		Outer2 oc = new Outer2();
		Outer2.Inner inner = oc.new Inner();
		inner.method();
	}
}
```
- 내부클래스 내 객체생성 확인   

```java

public class InnerTest5 {
	class instanceInner{
	}
	static class StaticInner{
	}
	
	static void staticMethod() {
		//static멤버는 static 만 접근 가능
		//instanceInnsr obj1 = new InstanceInner(); //error
		
		StaticInner obj2 = new StaticInner();
	}
	
	void instanceMethod() {
		instanceInner obj1 = new instanceInner(); //error
		StaticInner obj2 = new StaticInner();
		
	}
	
	public static void main(String[] args) {

	}
}
```

### 익명클래스
- 클래스의 선언과 객체의 생성을 동시에 한다
	- 단 한번만 사용될 수 있으며, 하나의 객체만 생성하는 일회용 클래스
- 이름이 없어 생성자를 가질 수 없다
- 부모의 클래스이름이나 인터페이스의 이름을 사용해서 정의
- 오직 하나의 클래스를 상속 혹은 하나의 인터페이스만을 구현 가능   
> new 부모클래스명(){멤버선언};   

```java
public class Anonymous1 {
	Object iv = new Object() {
		void method() {}
	};
	static Object cv = new Object() {
		void method() {}
	};
	void myMethod() {
		Object lv = new Object() {
			void method() {}
		};
	}
	public static void main(String[] args) {
	}
}
```

## 2. 내부클래스 활용 이벤트처리
- 내부클래스 이용   

```java
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventTest1 extends Frame {
	private Button bt;
	public EventTest1() {
		bt=new Button("닫기");
		this.add(bt,"South");
		bt.addActionListener(new EventHandler());
	}
	
	class EventHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		EventTest1 f= new EventTest1();
		f.setSize(300,200);
		f.setVisible(true);

	}

}
```
- 익명클래스 이용   

```java
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventTest2 extends Frame {
	private Button bt;
	public EventTest2() {
		bt=new Button("닫기");
		this.add(bt,"South");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		EventTest2 f= new EventTest2();
		f.setSize(300,200);
		f.setVisible(true);

	}
}
```
