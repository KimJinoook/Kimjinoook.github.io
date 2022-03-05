# 추상
## 1. 추상메서드 (abstract method)
- 메서드의 구현부가 없는 것
- 오버라이딩 관계를 형성하기 위해 정의된 메서드
  > public abstract int func(int a);   
  - 메서드 블락{}을 포함하지 않는다
- 상속 계층의 부모클래스에서 자식 클래스를 위해 메서드 시그니처만 정의
- 자식클래스에서 오버라이딩 해야만 호출가능
- 부모클래스에서는 선언부만 작성
- 자식클레스에서 기능 구현

## 2. 추상클래스 (abstract class)
- 미완성 클래스
- 객체 생성 불가
- 상속을 통해 자식클래스로만 완성 가능
- 추상메서드 하나라도 있다면 추상클래스
- 추상메서드가 없더라도 abstract 선언으로 추상클래스 사용 가능
- 인스턴스화 목적이 아닌, 단지 상속의 관계를 형성하기 위해 사용
  > abstract class 클래스명{}   

## 3. 특징
- 자식클래스를 통해 **모든 추상메서드를 구현**해야만 객체 생성 가능
  -  모두 구현하지 않으면 자식클래스도 추상메서드를 포함하게 되어 객체 생성 불가
- 자식클래스에서 추상메서드를 반드시 구현하도록 강요

```java
abstract class Animal{
	public abstract void sound();
}
class Dog extends Animal{
	public void sound() {
		System.out.println("멍멍");
	}
}
abstract class Mammal extends Animal{
	public void breed(int n) {
		System.out.println(n+"마리 새끼를 낳는다");
	}	
}
class Cow extends Mammal{
	public void sound() {
		System.out.println("음메");
	}
}

public class AbstractClass {

	public static void main(String[] args) {
		// Animal a = new Animal(); //객체생성불가
		Dog d = new Dog();
		d.sound();
		
		Animal an = new Dog(); // 다형성 가능
		an.sound();
		
		//Mammal m = new Mammal(); // 객체생성불가
		Mammal m = new Cow();
		m.sound();
		m.breed(2);
	}
}
```
