---
layout: post
title:  "12. 인터페이스"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 인터페이스 란
- 일종의 추상 클래스
- **추상메서드와 상수**만을 멤버로 가질 수 있다
  - 일반메서드나 멤버변수를 가질 수 없다
  - 메서드 목록만을 가지고 있다
  - 뼈대로만 구성
- 상속받을 클래스가 구현해야 할 기능을 나열
- 일종의 약속, 최소한의 계약

  > interface 인터페이스명{}   
- 모든 멤버변수는 public static final, 생략가능
- 모든 메서드는 public abstract, 생략가능

## 2. 인터페이스 구현
- 상속받을 클래스에서
  > class 클래스명 implements 인터페이스명{}   
  - extends를 쓰지않고 implements를 쓴다.

```java
interface IAnimal{
	public abstract void sound();
	void display(); //접근제한자 생략 가능
}

//IAnimal 인터페이스를 구현하는 자식 클래스
class Cat implements IAnimal{
	public void sound() {
		System.out.println("야옹");
	}
	public void display() {
		System.out.println("cat메서드");
	}
}

//IAnimal의 display를 구현하지 않아 추상클래스
abstract class Dog implements IAnimal{
	public void sound() {
		System.out.println("멍멍");
	}
}

class Cow implements IAnimal{
	public void sound() {
		System.out.println("음메");
	}
	/*
	void display() { //error : 부모메서드를 오버라이딩 할 때 접근제한자는 부모보다 같거나 넓어야 한다
		System.out.println("Cow 메서드");
	}*/
	public void display() {
		System.out.println("Cow메서드");
	}
	
}

public class InterfaceTest {

	public static void main(String[] args) {
		// IAnimal an = new IAnimal(); // error 인터페이스는 객체 생성 불가
		Cat c = new Cat();
		c.sound();
		c.display();
		
		// Dog d = new Dog(); // error 추상클래스 객체 생성 불가
		
		IAnimal an = new Cow();
		an.sound();
		an.display();

	}
}
```   

- 인터페이스의 구현과 부모클래스의 상속 동시 가능
	> class child extneds fater implements interface{}   

```java
class Tv{
	public void onTv() {
		System.out.println("전원 on");
	}
}
interface Computer{
	public void dataReceive();
}

//상속과 구현을 동시에
class IPTv extends Tv implements Computer{
	public void dataReceive() {
		System.out.println("영상 데이터 수신 중");
	}
	public void powerOn() {
		dataReceive();
		onTv();
	}
}

public class IpTvTest {

	public static void main(String[] args) {
		IPTv t = new IPTv();
		t.powerOn();
	}
}
```   

## 3. 인터페이스 상속
- 인터페이스는 인터페이스만 상속받을 수 있다
- **다중 상속 가능**

```java
interface Movable{}
interface Attackable{}
interface Fightable extends Movable, Attackable{}
```   

- 하나의 클래스가 여러개의 인터페이스를 구현할 수 있다

```java
interface TV{}
interface Computer{}
class IPTV implements TV, Computer{}
```   
```java
interface Tv2{
	public void onTv();
}
interface Computer2{
	public void dataReceive();
}

//인터페이스 다중 상속 가능
class IPTv2 implements Tv2, Computer2{
	public void dataReceive() {
		System.out.println("영상 데이터 수신 중");
	}
	public void onTv() {
		System.out.println("tv영상 출력");
	}
	public void powerOn() {
		dataReceive();
		onTv();
	}
}

public class IpTvTest2 {

	public static void main(String[] args) {
		IPTv2 t = new IPTv2();
		t.powerOn();
	}
}
```   

- 종합   

```java
interface Attackable{
	/**
	 * 지정된 대상을 공격하는 기능
	 * @param u
	 */
	public abstract void attack(Unit u);
}

interface Movable{
	/**
	 * 지정된 위치로 이동하는 기능
	 * @param x
	 * @param y
	 */
	void move(int x, int y);
}

//인터페이스끼리의 상속 - extends 사용, 다중상속 가능
interface Fightable extends Attackable, Movable{
	
}

abstract class Unit{
	protected int x, y; //위치
	protected int currentHP; //체력
	
	Unit(int x, int y, int currentHP){
		this.x = x;
		this.y = y;
		this.currentHP = currentHP;
	}
}

class Fighter extends Unit implements Fightable{
	public Fighter(int x, int y, int currentHP) {
		super(x,y,currentHP);
	}
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println(x+","+y+"위치로 이동");
	}
	public void attack(Unit u) {
		System.out.println(u.x+","+u.y+"위치의"+u.currentHP+"체력 유닛 공격");
	}
}

public class UnitTest {

	public static void main(String[] args) {
		Fighter f = new Fighter(10,25,350);
		f.move(30,40);
		
		f.attack(new Fighter(30,40,240));
		f.move(70, 80);
		f.attack(new Fighter(70,80,100));
	}
}
```   

## 4. 인터페이스의 장점
- 표준화가 가능하다
	- 인터페이스를 기본 틀로, 일관되고 정형화된 프로그램의 개발 가능
- 관계없는 클래스들에게 관계를 맺어줄 수 있다
	- 상속관계도 아닌, 같은 조상을 가지고 있지 않은 클래스도 인터페이스를 통해 공통적으로 구현, 관계 형성 가능
- 독립적인 프로그래밍 가능
	- 클래스 간 직접적인 관계를 인터페이스를 통해 간접적인 관계로 변경
	- 클래스의 변경이 다른 클래스에게 영향을 미치지 않도록 독립적인 프로그래밍 가능

- ex) 직접적인 관계   

```java
/*
 * 직접적인 관계의 두 클래스 A,B
 * 단점 : 한쪽이 provider 되면 다른쪽도 변경
 */
class A{
	/*
	public void methodA(B b) {
		b.methodB();
	}*/
	public void methodA(C c) {
		c.methodC();
	}
}
class B{
	public void methodB() {
		System.out.println("B클래스 메서드");
	}
}
class C{
	public void methodC() {
		System.out.println("C 클래스 메서드");
	}
}

public class InterfaceTest2 {

	public static void main(String[] args) {
		A a = new A();
		//메서드A 호출
		//a.methodA(new B());
		
		//c클래스를 추가해 c를 호출하고 싶은 경우
		//a클래스의 메서드 구현부를 변경해주어야 한다
		a.methodA(new C());
	}
}
```   

- ex) 간접적인 관계   

```java
/*
 * 간접적인 관계의 두 클래스 (A-I-B)
 * 클래스 A가 클래스 B를 직접 호출하지 않고 인터페이스를 매개체로 하는 경우
 * 클래스A는 인터페이스 I하고만 직접적인 관계에 있기 때문에 클래스B의 변경에
 * 영향을 받지 않는다
 */

interface I{
	void method();
}
class AA{
	public void methodA(I i) {
		i.method();
	}
}
class BB implements I{
	public void method() {
		System.out.println("B클래스 메서드");
	}
}
class CC implements I{
	public void method() {
		System.out.println("C클래스 메서드");
	}
}
public class InterfaceTest3 {

	public static void main(String[] args) {
		AA a = new AA();
		//methodA()호출
		a.methodA(new BB());

		//c클래스를 추가해 c를 호출하고 싶은 경우
		//a클래스의 메서드 구현부를 변경할 필요 없다
		a.methodA(new CC());
	}
}
```   

## 5. 인터페이스 기반의 상수 표현
- 인터페이스 내 변수는 무조건 public static final로 선언되는 특성을 활용

```java
interface Week{
	int MON=1, THE=2, WED=3, THU=4, FRI=5, SAT=6, SUN=7;
} // 인터페이스 활용

class Week2 {
	public static final int MON=1;
	public static final int TUE=2;
	public static final int WED=3;
	public static final int THU=4;
	public static final int FRI=5;
	public static final int SAT=6;
	public static final int SUN=7;
} // 클래스로 선언할 경우

public class InterfaceConst {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("요일 선택:1.월 ~ 7.일");
		int type = sc.nextInt();
		
		switch(type) {
		case Week.MON :
			System.out.println("주간회의");
			break;
		case Week.THE :
			System.out.println("프로젝트 기획 회의");
			break;
		case Week.WED :
			System.out.println("진행사항 보고");
			break;
		case Week.THU :
			System.out.println("사내 축구");
			break;
		case Week.FRI :
			System.out.println("프로젝트 마감");
			break;
		case Week.SAT :
			System.out.println("가족");
			break;
		case Week.SUN :
			System.out.println("휴일");
			break;		
		}
	}
}

```   


