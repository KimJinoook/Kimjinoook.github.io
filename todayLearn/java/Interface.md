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
