# 인터페이스
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


