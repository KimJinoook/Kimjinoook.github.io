# 오브젝트 클래스
- 모든 클래스의 최고 상위 클래스
- object클래스의 멤버들은 모든 클래스에서 바로 사용 가능
- 8개의 메서드 보유
  - 객체를 처리하기 위한 메서드
    - clone() : 객체의 복사본을 만들어 리턴
    - equals() : 객체의 주소값 비요
    - finalize() : 객체가 쓸모없어졌을 때 가비지컬렉터에 의해 호출
    - getClass() : 현재 객체의 클래스의 객체를 리턴
    - hashCode() : 객체에 대한 해시코드 메모리주소 리턴
    - toString() : 객체를 문자열로 표현하는 값 리턴
  - 쓰레드를 위한 메서드   
    - notify() : 객체의 모니터에 대기하고있는 단일 쓰레드를 깨운다
    - notifyAll() : 객체의 모니터에 대기하고있는 모든 클래스를 깨운다
    - wait() : 다른 쓰레드가 현재 객테에 대한 메서드를 호출할 때까지 현재 쓰레드가 대기하고있도록 한다


```java
class Person{
	public void showInfo() {
		System.out.println("this ="+this);
	}
}

public class ObjectTest1 {

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		Person p = new Person();
		Person p2 = new Person();
		
		System.out.println(p.equals(p2)); //false
		System.out.println(p.getClass()); // class com.day24.Person
		System.out.println(p.hashCode()); // 1586600255
		System.out.println(p.toString()); // com.day24.Person@5e91993f
		System.out.println(p);			  // com.day24.Person@5e91993f
		System.out.println(Integer.toHexString(p.hashCode())); //5e91993f
		
		p.showInfo(); // this =com.day24.Person@5e91993f
		
		p2=p;
		if(p.equals(p2)) {
			System.out.println("p와 p2주소 같다");
		}else {
			System.out.println("p와 p2주소 다르다");
		} //같다 출력
		/*
		 	toString()메서드 결과
		 	클래스명@16진수 해시코드
		 	getClass().getName()+"@"+Integer.toHexString(hashCode)
		 */
	}
}

```
## 1. toString()메서드
- 해당 클래스가 어떤 객체인지 문자열로 표현 

```java
class Person2{
	private String name;
	private int age;
	
	public String toString() { //오버라이딩
		return "Person2[name = "+name+", age = "+age+"]";
	}
}

public class ObjectTest2 {

	public static void main(String[] args) {
		Person2 p = new Person2();
		System.out.println(p.toString());
		System.out.println(p);
	}
}
```
## 2.equals()메서드
- 연산자==
  - 기본자료형에서는 값이 같은지 비교
  - 참조자료형에서는 주소값을 비교
- String클래스의 equals()메서드
  - 값이 같은지 비교
  - Object클래스의 equals()메서드를 오버라이딩 한 것
- Object클래스의 equals()메서드
  - 매개변수로 객체의 참조변수를 받아서 비교하여 그 결과를 불린으로 알려준다.
  - 주소값을 비교   

- 기본 equals   

```java
class Test{
	private int value;
	Test(int value){
		this.value=value;
	}
}

public class ObjectTest3 {

	public static void main(String[] args) {
		
		Test t1 = new Test(10);
		Test t2 = new Test(10);
		
		if(t1==t2) {
			System.out.println("t1,t2의 주소가 같다");
		}else {
			System.out.println("t1,t2의 주소가 다르다"); //다르다 출력
			
		}
		if(t1.equals(t2)) {
			System.out.println("t1,t2의 주소가 같다");
			
		}else {
			System.out.println("t1,t2의 주소가 다르다"); // 다르다 출력
			
		}
	}
}
```
- equals 오버라이딩   

```java
class Person3{
	private long id;
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof Person3) {
			Person3 p = (Person3)obj;
			return id==p.id;
		}else {
			return false;
		}
	}
	public Person3(long a) {
		this.id = a;
	}
}

public class EqualsTest {

	public static void main(String[] args) {
		Person3 p1 = new Person3(9901071112222L);
		Person3 p2 = new Person3(9901071112222L);

		
		if(p1==p2) {
			System.out.println("p1,p2는 같은주소");
		}else {
			System.out.println("p1,p2는 다른주소"); //다르다 출력
			
		}
		if(p1.equals(p2)) {
			System.out.println("p1,p2는 같은값"); //같다 출력 
		}else {
			System.out.println("p1,p2는 다른값");
			
		}
	}

}
```
## 3.hashCode()메서드
- 해싱기법에 사용되는 해시함수 구현
	- 해싱 : 데이터 관리기법중 하나
- Object클래스의 hashCode()메서드
	- 객체의 주소값을 해시코드로 만들어 반환
- 몇몇 클래스는 hashCode()메서드가 이미 오버라이딩 되어있다   

```java
class Man{
	
}

public class HashCodeTest {

	public static void main(String[] args) {
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		String str3 = "abc";
		String str4 = "abc";
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str3.hashCode());
		System.out.println(str4.hashCode());
		//str1, str2는 문자열의 내용이 같아 같은 해시코드값

		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		System.out.println(System.identityHashCode(str3));
		System.out.println(System.identityHashCode(str4));
		//객체의 주소값 리턴 => str1, str2,는 다른 주소 해시코드값을 갖는다
		
		/*
		 * String클래스는 문자열의 내용이 같으면 동일한 해시코드를 반환하도록
		 * 오버라이딩 되어있다.
		 * 
		 * System.identityHashCode(Object x)
		 * 객체의 주소값으로 해시코드 생성
		 * 모든 객체에 대해 항상 다른 해시코드값 반환
		 */
		
		Integer n1=10, n2=30;
		System.out.println("===Integer===");
		System.out.println(n1.hashCode()); // 10출력
		System.out.println(n2.hashCode()); // 30출력
		System.out.println(System.identityHashCode(n1));
		System.out.println(System.identityHashCode(n2));
		
		Man m1 = new Man();
		Man m2 = new Man();
		System.out.println("===man===");
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		System.out.println(System.identityHashCode(m1));
		System.out.println(System.identityHashCode(m2));
	}
}
```

## 4.clone()메서드
- 자신을 복제하여 새로운 인스턴스 생성
- Object클래스의 clone()메서드
	- 멤버변수의 값만 복사한다
	- 배열이나 인스턴스가 멤버로 정의되어있는 클래스는 완전한 복제가 이루어지지 않는다
- Cloneable 인터페이스를 구현한 클래스의 인스턴스만 복제가 가능하다   

```java
class Point implements Cloneable{
	int x, y;
	
	Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public String toString() {
		return "Point[x="+x+", y="+y+"]";
	}
	
	public Point copy() {
		Object obj = null;
		try {
			//Object clone() throws CloneNotSupportedException
			obj = clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return (Point) obj;
	}
}

public class CloneTest {

	public static void main(String[] args) {
		Point p = new Point(10,35);
		Point p2 = p.copy();
		System.out.println("p="+p); //10,35
		System.out.println("p2="+p2); //10,35
		
		System.out.println("p 주소 : "+System.identityHashCode(p));
		System.out.println("p2 주소 : "+System.identityHashCode(p2));

		p.x++;
		p.y++;
		
		System.out.println("p의 멤버변수 변경후 p="+p); //11, 36
		System.out.println("p의 멤버변수 변경후 p2="+p2); //10, 35
	}
}
```

## 5.finalize()메서드
- 인스턴스가 소멸되기 직전 자바가상머신에 의해 자동으로 호출되는 메서드
- 인스턴스 소멸 시 반드시 실행되어야하는 코드가 존재한다면, finalize메서드 활용   
> protected void finalize() throws Throwable   

```java
class MyName{
	private String objName;
	
	MyName(String name){
		this.objName = name;
	}
	
	//오버라이딩
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println(objName+"이 소멸되었습니다.");
	}
}

public class FinalizeTest {

	public static void main(String[] args) {
		MyName obj1 = new MyName("인스턴스1");
		MyName obj2 = new MyName("인스턴스2");
		
		obj1=null;
		obj2=null;
		System.out.println("종료");

	}
}
```
- 위 예제는 가비지컬렉션이 실행되지 않아 finalize메서드를 호출하지 않는다
- 빈번한 가비지컬렉션은 성능에 문제 야기
- 특정 알고리즘을 통해 계산된 시간에 가비지 컬렉션 수행
- System.gc()
	- 명시적으로 가비지컬렉션을 수행시키는 메서드
	- 참조되지 않는 인스턴스들을 소멸시킨다.
	- finalize메서드의 호출을 보장하지는 못한다
		- 가비지컬렉션이 수행되어도 상황에 따라 인스턴스의 소멸이 유보될 수 있다
- System.runFinalization()
	- 소멸이 유보된 인스턴스들의 finalize메서드 호출을 위해 사용   

```java
class MyName{
	private String objName;
	
	MyName(String name){
		this.objName = name;
	}
	
	//오버라이딩
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println(objName+"이 소멸되었습니다.");
	}
}

public class FinalizeTest {

	public static void main(String[] args) {
		MyName obj1 = new MyName("인스턴스1");
		MyName obj2 = new MyName("인스턴스2");
		
		obj1=null;
		obj2=null;
		System.gc();
		System.runFinalization();
		System.out.println("종료");

	}
}
```
- 종료 출력
- 인스턴스2 소멸
- 인스턴스1 소멸
- 순서는 매 번 달라지며 알 수 없다.
