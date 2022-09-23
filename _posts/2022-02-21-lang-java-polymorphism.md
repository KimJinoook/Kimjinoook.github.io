---
layout: post
title:  "10. 다형성"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 다형성
### a. 다형성이란
- **부모 클래스 타입의 참조변수로 자식클래스의 인스턴스를 참조**
  > Parent p = new Child();   
  - 부모의 참조변수로는 부모의 멤버들만 사용
  - 오버라이딩 메서드의 경우 자식 메서드 호출
  - 반대의 경우는 불가
- 한 타입의 참조변수로 여러 타입의 객체를 참조
	- 부모 타입이 파생된 모든 자식타입을 가리킬 수 있다.
- 여러 개의 개별 클래스를 하나의 부모클래스로 통합관리
	- 부모 타입의 변수로 자식 타입을 일관되게 관리할 수 있다.

### b. 다형성 이용
- ex1
```java
class Parent{
	public void parentFunc() {
		System.out.println("부모메서드");
	}
	public void showInfo() {
		System.out.println("parent-showinfo");
	}
}

class Child extends Parent{
	public void showInfo() {
		System.out.println("child-showinfo-오버라이딩");
	}
	public void childFunc() {
		System.out.println("자식만의 메서드");
	}
}

public class PolymoTest {

	public static void main(String[] args) {
		Child ch = new Child();
		ch.showInfo();
		ch.parentFunc();
		ch.childFunc();
		
		//다형성
		Parent p = new Child(); // 부모의 참조변수로 자식의 인스턴스 참조
		p.showInfo(); // 자식의 오버라이딩 메서드 호출
		p.parentFunc(); // 부모의 메서드 호출
		//p.childFunc(); // error 자식만의 메서드는 호출 불가
	}
}
```
- ex2
```java
import java.util.Scanner;

class Shape{
	public void draw() {
		System.out.println("도형 메서드");
	}
	public void delete() {
		System.out.println("도형 삭제 메서드");
	}
	public void display() {
		System.out.println("부모 메서드");
	}
}
class Circle extends Shape{
	public void draw() {
		System.out.println("원 메서드");
	}
	public void delete() {
		System.out.println("원 삭제 메서드");
	}
	public void display() {
		System.out.println("원 자식 메서드");
	}
	
}
class Triangle extends Shape{
	public void draw() {
		System.out.println("삼각형 메서드");
	}
	public void delete() {
		System.out.println("삼각형 삭제 메서드");
	}
	public void display() {
		System.out.println("삼각형 자식 메서드");
	}
	
}

public class PolymoTest2 {

	public static void main(String[] args) {
		//기본 사용법
		Circle c = new Circle();
		c.draw();
		c.delete();
		
		//다형성 이용
		Shape sh = new Circle();
		sh.draw();
		sh.delete();
		sh.display();
		
		sh = new Triangle();
		sh.draw();
		sh.delete();
		
		//사용자로부터 입력
		Scanner sc = new Scanner(System.in);
		System.out.println("도형 선택 1 원 2 삼각형");
		int type = sc.nextInt();
		
		if(type==1) {
			Circle c2 = new Circle();
			c2.draw();
			c2.delete();
		}else if(type==2) {
			Triangle tr = new Triangle();
			tr.draw();
			tr.delete();
		}else {
			System.out.println("잘못선택");
		}

		// 다형성 이용
		Shape sh2= null;
		if(type==1) {
			sh2=new Circle();
		}else if(type==2) {
			sh2=new Triangle();
		}else {
			System.out.println("잘못입력");
		}
		sh2.draw();
		sh2.delete();
	}

}
```
- ex3 모듈화
```java
import java.util.Scanner;

public class ClassArgsTest {

	// 1. 매개변수가 클래스인 경우
	public static void func1(Circle c) {
		// 참조변수가 보이면 참조변수.메서드
		c.draw();
		c.delete();
		c.sayCircle();
	}
	// 2. 매개변수 다형성
	public static void func2(Shape sh) {
		sh.draw();
		sh.delete();
	}
	
	// 3. 반환타입이 클래스인 경우
	public static Circle func3() {
		Circle c = new Circle();
		return c; // Circle 객체 리턴
	}
	
	//반환타입에 다형성 이용
	public static Shape createShape(int type) {
		Shape sh2= null;
		if(type==1) {
			sh2=new Circle();
		}else if(type==2) {
			sh2=new Triangle();
		}
		return sh2;
	}// 반환타입이 부모클래스면, 자식객체 리턴
	
	public static void main(String[] args) {
		// 1. 매개변수 클래스인 경우 호출
		func1(new Circle());

		Circle c = new Circle();
		func1(c);

		// 2. 매개변수 다형성
		func2(new Circle());
		
		Shape sh = new Circle();
		func2(sh);
		
		// 3. 반환타입이 클래스인 경우
		func1(func3());
		
		Circle c2 = func3();
		func1(c2);
		
		// 4. 반환타입 다형성
		Scanner sc = new Scanner(System.in);
		int type = sc.nextInt();
		Shape sh2 = createShape(type);
		func2(sh2);
	}
}
```

### c. 배열 이용
```java
public class PolymoTest33 {

	public static void main(String[] args) {
		Shape[] arr = new Shape[3];
		arr[0] = new Circle() ;
		arr[1] = new Triangle();
		arr[2] = new Circle();
		
		for(int i = 0; i<arr.length;i++) {
			arr[i].draw();
			arr[i].delete();
		}
		for(Shape i : arr) {
			i.draw();
			i.delete();
		}
	}
}

```

***
## 2. 참조변수의 형변환
### a. 기본
- 참조형 변수도 형변환 가능
	- **상속관계에 있는 클래스 사이**에서만 가능
	- 다형성 또한 자동 형변환
	> 부모<-자식 (Up-casting) : 자동형변환   
	> 자식<-부모 (down-casting) : 명시적 형변환   
	> Parent p = (Parent)new Child(); // 생략가능, 자동형변환   
	> Child c = (Child)p; //생략불가   
	> Child c = (Child)new Parent(); //runtime 에러 (컴파일은 됨)   

```java
class Car{
	protected String color;
	protected int door;
	
	public void drive() {
		System.out.println("drive");
	}
	public void stop() {
		System.out.println("stop");
	}
}

class FireEngine extends Car{
	public void water() {
		System.out.println("water");
	}
	public void drive() {
		System.out.println("fire engine drive");
	}
}

class Ambulance extends Car{
	public void siren() {
		System.out.println("siren");
	}
	public void drive() {
		System.out.println("Ambulance drive");
	}
}

public class DownCasting {

	public static void main(String[] args) {
		Car c = new FireEngine(); // 업캐스팅, 다형성, 자동형변환
		c.drive();
		//c.water; //error 자식만의 메서드 호출 불가
		
		FireEngine f = (FireEngine)c; // 다운캐스팅, 명시적 형변환
		f.water(); //자식메서드 호출 가능해짐
		
		//FireEngine fe = (FireEngine)new Car(); //컴파일은 되지만 실행에러
		
		//Ambulance a = (Ambulance)c; //불가, 현재 c에 fireengine이 들어있기 때문

	}

}
```
### b. instance of
- 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알아보기 위한 연산자
	> 참조변수 instanceof 타입(클래스명)   
- 다운캐스팅 전, 가능한지 확인
- 주로 조건문에 사용
- 결과로 boolean값 반환

```java
class Car{
	protected String color;
	protected int door;
	
	public void drive() {
		System.out.println("drive");
	}
	public void stop() {
		System.out.println("stop");
	}
}

class FireEngine extends Car{
	public void water() {
		System.out.println("water");
	}
	public void drive() {
		System.out.println("fire engine drive");
	}
}

class Ambulance extends Car{
	public void siren() {
		System.out.println("siren");
	}
	public void drive() {
		System.out.println("Ambulance drive");
	}
}

public class DownCasting {

	public static void main(String[] args) {
		Car c = new FireEngine(); // 업캐스팅, 다형성, 자동형변환
		c.drive();
		//c.water; //error 자식만의 메서드 호출 불가
		
		FireEngine f = (FireEngine)c; // 다운캐스팅, 명시적 형변환
		f.water(); //자식메서드 호출 가능해짐
		
		//FireEngine fe = (FireEngine)new Car(); //컴파일은 되지만 실행에러
		
		c = new Ambulance();
		Ambulance am = (Ambulance)c;
		am.siren();
		
		if(c instanceof FireEngine) {
			FireEngine f2 = (FireEngine)c;
			f2.water();
		}else if(c instanceof Ambulance) {
			Ambulance am2 = (Ambulance)c;
			am2.siren();
		}else System.out.println("형변환 불가");
	}
}
```
- 자식 instanceof 부모 : true
	- 자식은 부모의 인스턴스를 모두 갖고있다
	- fireengine instanceof car : true
	- car instanceof fireengine : false

***

## 3. 매개변수의 다형성
```java
class test{
	
	public static void action(Robot a) { // 매개변수로 부모클래스를 넣어 다형성 이용
		if (a instanceof DanceRobot) {
			DanceRobot dr = (DanceRobot)a;
			dr.dance();
		}else if (a instanceof SingRobot) {
			SingRobot sr = (SingRobot)a;
			sr.sing();
		}else if (a instanceof DrawRobot) {
			DrawRobot drr = (DrawRobot)a;
			drr.draw();
		}
	}
	
	public static void main(String[] args) {
		Robot[] arr = { new DanceRobot(), new SingRobot(), new DrawRobot()};
		for(int i=0; i< arr.length;i++) {
			action(arr[i]);
		}
		
		for(Robot i : arr) action(i);
	} // main
}
class Robot {}
class DanceRobot extends Robot {
	void dance() {
	System.out.println("춤을 춥니다.");
	}
}
class SingRobot extends Robot {
	void sing() {
	System.out.println("노래를 합니다.");
	}
}
class DrawRobot extends Robot {
	void draw() {
	System.out.println("그림을 그립니다.");
	}
} 
```
