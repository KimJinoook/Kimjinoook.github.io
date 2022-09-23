---
layout: post
title:  "9. "
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---


## 1. 상속 (extends)
- 기존의 클래스를 재사용하여 새로운 클래스를 작성하는 것
- 이미 만들어진 클래스의 멤버들을 물려받아 새로운 클래스를 정의한다
- 공통되는 부분을 base클래스로 추상화, 이를 상속하면서 Derived 클래스를 정의한다.
- 하위클래스가 상위클래스를 상속받으면 하위는 상위의 모든 것을 이용할 수 있다.

> 부모클래스 - 상위(super)클래스, 기본(base)클래스, 조상클래스   
> 자식클래스 - 하위(sub)클래스, 파생(derived)클래스, 자손클래스

> class 클래스명 extends 부모클래스   

```java
class Parent{
}
class Child extends Parent{
}
```

```java
import java.util.Scanner;

class Person{
	private int age;
	private String name;
	public void setAge(int age) {
		this.age = age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return this.age;
	}
	public String getName() {
		return this.name;
	}
}

class Student151 extends Person{
	private String stNo;
	public String getStNo() {
		return stNo;
	}
	public void setStNo(String stNo) {
		this.stNo = stNo;
	}
} // 자식클래스인 student는 부모클래스인 Person의 멤버변수 name과 age를 모두 이용할 수 있다.


public class sp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("이름, 나이, 학번입력!");
		String name = sc.nextLine();
		int age = sc.nextInt();
		sc.nextLine();
		String stNo=sc.nextLine();
		
		Student151 st = new Student151();
		st.setAge(age);
		st.setName(name);
		st.setStNo(stNo);
		
		System.out.println(st.getStNo());
		System.out.println(st.getName());
		System.out.println(st.getAge());
	}
}
```

#### 상속과 protected
```java
package day15;
import day15sub.MyParent;

//부모클래스가 다른 패키지에 있을 경우

class MyChild extends MyParent{
	public void putData() {
		//varprivate  //부모클래스의 private 변수 접근 불가
		varprotected = 10; // 부모클래스의 protected 변수 접근 가능
		//vardefault  // 부모클래스의 default 클래스 접근 불가
		varpublic = 40; // 부모클래스의 public 변수 접근 가능
	}
}

public class ProtectedTest {

	public static void main(String[] args) {
		MyChild ch = new MyChild();
		//ch.varprivate
		//ch.varprotected //자식클래스는 받아오지만, 멤버변수는 다른패키지에 있기에 메인클래스에서는 접근 불가
		//ch.vardefault
		ch.varpublic = 10;

	}

}
```

#### 상속의 생성 순서
```java
class GrandFather{
	GrandFather(){
		System.out.println("그랜파 생성자");
	}
}

class Father extends GrandFather{
	Father(){
		System.out.println("파더 생성자");
	}
}

class Child extends Father{
	Child(){
		System.out.println("차일드 생성자");
	}
}



public class Inheritance3 {

	public static void main(String[] args) {
		Child ch = new Child();
	}

} // 자식클래스 생성시, 부모클래스부터 차례로 생성 후 자식클래스를 생성하는 것을 알 수 있다.

```

#### object 클래스
- object클래스 : 모든 클래스의 최상위 클래스
- 생성하는 클래스는 자동적으로 object를 상속받고 있다
> class exam{   
> }   
> class exam (extends object){   
> }   

- toString, equals 등의 메서드들을 사용할 수 있는 이유

***

## 2. 오버라이딩
- 부모클래스로부터 **상속받은 메서드의 내용을 변경**하는 것
- 상속받은 메서드를 **자식클래스에 맞게 변경해야 하는 경우**

```java
class Point{
	protected int x;
	protected int y;
	
	public String findLocationd() {
		String result = "x="+x+", y="+y;
		return result;
	}
}

class Point3D extends Point{
	private int z;
	
	public String findLocationd() {
		String result = "x="+x+", y="+y+", z="+z;
		return result;
	}
}

public class PointTest {

	public static void main(String[] args) {
		Point p = new Point();
		String res = p.findLocationd();
		System.out.println("2 : "+res);
		
		Point3D p3 = new Point3D();
		System.out.println("3 : "+p3.findLocationd());
	}
}

```

### a. 오버라이딩 조건
- 메서드의 선언부는 부모클래스와 완전히 일치해야 한다
  - 내용만을 재정의 하는 것
  - 이름이 같아야 한다.
  - 매개변수가 같아야 한다.
  - 리턴타입이 같아야 한다.

***

## 3. this / super
### a. this
- this. : 자기자신을 가리키는 참조변수
- this() : 한 생성자에서 다른 생성자를 호출
	- 생성자의 이름으로 클래스 이름 대신 this 사용
	- 첫 줄에서만 호출 가능
- 클래스 내 유일하게 호출할 수 없는 메서드 : 생성자
	- 생성자를 호출하는 유일한 방법 : this()

```java
class Car{
	private String color;
	private String gearType;
	private int door;
	
	Car(String color, String gearType, int door){
		this.color = color;
		this.gearType = gearType;
		this.door = door;
	}
	/*
	Car(){
		this.color = "white";
		this.gearType = "auto";
		this.door = 4;
	} 중복되는 줄이 많다
	*/
	Car(){
		this("white","auto",4);
	} // this를 이용, 오버로딩 생성자의 중복부분을 삭제
	
	Car(String color){
		this(color, "auto",4);
	}
	
	public void showInfo() {
		System.out.println(color);
		System.out.println(gearType);
		System.out.println(door);
	}
}

public class ThisTest {

	public static void main(String[] args) {
		Car c1 = new Car();
		c1.showInfo();
		
		Car c2 = new Car("Black");
		c2.showInfo();
		
		Car c3 = new Car("Red","manual",2);
		c3.showInfo();
	}

}
```

### b. super
- 자식클래스에서 부모클래스로부터 상속받은 멤버를 참조하는데 사용
	- 부모의 멤버 호출 : super.멤버
	- 부모의 생성자 호출 : super()
- 상속받은 멤버와 자신의 멤버이름이 같을 때 구별하기 위해 사용
- 그 외에는 근본적으로 this와 같다.

- ex) super.
```java
class Parent {
	int x=10;
}
class Child extends Parent {
	void method() {
		System.out.println("x=" + x);  // 10
		System.out.println("this.x=" + this.x); // 10
		System.out.println("super.x="+ super.x); // 10
	}
}
```
```java
class Parent {
	int x=10;
}
class Child extends Parent {
	int x = 20;
	void method() {
		System.out.println("x=" + x); // 20
		System.out.println("this.x=" + this.x); // 20
		System.out.println("super.x="+ super.x); // 10
	}
}
```

- ex) super()
```java
class Point{
	protected int x;
	protected int y;	
	
	Point(int x, int y){
		//super() 가 생략되어있으며, 최상위 클래스 object를 호출하고있다.
		this.x = x;
		this.y = y;
	}
	public String findLocation() {
		return "x="+x+", y="+y;
	}
}
class Point3D extends Point{
	private int z;
	Point3D(int x, int y, int z){
		super(x,y);
		// 부모생성자가 매개변수를 가지고 있다면, 자식은 부모의 생성자에
		// 매개변수를 넣어줘야 한다.
		// 모든 클래스의 생성자 첫줄에는 생성자 호출을 해야하며
		// super()가 보이지는 않지만 들어가있다.
		this.z=z;
	}
	public String findLocation() {
		return super.findLocation()+", z="+z;
	}	
}
public class SuperTest {

	public static void main(String[] args) {
		Point3D a = new Point3D(10,20,30);
		System.out.println(a.findLocation());
	}
}
```

***

## 4. final
- 변수 : 값 변경할 수 없는 상수가 된다.(멤버, 지역 둘다 사용 가능)
- 메서드 : 오버라이딩 불가
- 클래스 : 자식클래스를 정의할 수 없다.
	- ex) String, Math 클래스   


```java
class Parent2{
	public void func1() {
		System.out.println("func1()");
	}
	public final void func2() {
		System.out.println("func2()");
	}
}
class Child2 extends Parent2{
	public void func1() {
		System.out.println("오버라이딩 func1");
	}
	/*
	public void func2() {
		System.out.println("오버라이딩 func2");
	} error 
	*/
}

class MyFinal{
	public final static double PI=3.141592;
	public final int DELIVERY=3000;
	int age = 20;
}

public class FinalClassTest {

	public static void main(String[] args) {
		MyFinal obj = new MyFinal();
		System.out.println(obj.age);
		System.out.println(MyFinal.PI);
		System.out.println(obj.DELIVERY);
		
		obj.age = 23;
		// MyFinal.PI=3.14;
		// obj.DELIVERY=2500;
		
		//final 지역변수
		final double INTEREST_RATE=0.02;
		System.out.println(INTEREST_RATE);
		
		// INTEREST_RATE = 0.15;
	}
}
```

- 지역변수에서 final은 static와 함께 사용 불가
	- final필드가 이미 static필드의 특성을 띄고있다.
	- 클래스명으로 접근
- 생성자를 이용해 final 멤버변수 초기화
	- final 변수
		- 상수지만 선언과 함께 초기화하지 않고 생성자를 통해 초기화 가능
		- 각 인스턴스별 다른 값을 갖도록 할 수 있다.
	- static final 변수
		- 선언과 함께 초기화
		- 클래스 차원에서 하나만 생성, 모든 인스턴스가 같은 값을 갖는다   


```java
class Card{
	final String KIND;
	final int NUMBER;
	static final int WIDTH=100;
	static final int HEIGHT=250;
	
	Card(String k, int num){
		KIND = k;
		NUMBER=num;
	}
	public void showInfo() {
		System.out.println("카드 종류"+KIND);
		System.out.println("카드 숫자"+NUMBER);
		
		// KIND="spade"; error
	}
}

public class CardFinal {

	public static void main(String[] args) {
		Card c1 = new Card("dia",5);
		c1.showInfo();
		
		Card c2 = new Card("heart",9);
		c2.showInfo();
		
		System.out.println("너비:"+Card.WIDTH);
		System.out.println("높이:"+Card.HEIGHT);
	}
}
```

***

## 5. 클래스 포함관계 (Has a)
- 클래스 자체를 다른 클래스의 멤버변수로 포함
- 상속 이외에 클래스를 재사용하는 방법
	- 상속 이용시
		- is a 관계가 성립
		- ~은 일종의 ~이다
		- sportsCar is a Car
	- 포함 이용시
		- has a 관계가 성립
		- ~은 ~을 가지고 있다
		- Circle has a Point
- 상속 이용 ex)   


```java
class Points{
	protected int x;
	protected int y;
	
	Points(int x, int y){
		this.x=x;
		this.y=y;
	}
}
class Circle extends Points{
	private int r;
	Circle(int x, int y, int r){
		super(x,y);
		this.r=r;
	}
	public void pritnInfo() {
		System.out.println("x="+x+", y="+y);
		System.out.println("r="+r+"\n");
	}
}

public class IsATest {

	public static void main(String[] args) {
		Circle c = new Circle(10, 20, 30);
		c.pritnInfo();
	}
}
```

- 포함 이용 ex)   


```java
class Points2{
	int x;
	int y;
	
	Points2(int x, int y){
		this.x=x;
		this.y=y;
	}
}

class Circle2{
	private Points2 p;
	private int r;
	
	Circle2(Points2 p, int r){
		this.p = p;
		this.r = r;
	}
	public void pritnInfo() {
		System.out.println("x="+p.x+", y="+p.y);
		System.out.println("r="+r+"\n");
	}
}

public class HasATest {

	public static void main(String[] args) {
		Circle2 c = new Circle2(new Points2(40,50),60);
		c.pritnInfo();
	}
}
```
