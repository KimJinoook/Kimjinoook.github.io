---
layout: post
title:  "7. 클래스"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 객체 지향 프로그래밍
- 객체 (object)
  - 물건, 대상 등을 의미
  - 상태정보(속성, 데이터) / 변수
  - 행동(기능) / 메서드
- 객체 지향 프로그래밍
  - 현실에 존재하는 사물, 대상, 그에 따른 행동 등을 실체화 시키는 형태의 프로그래밍
    - 나는 상점에서 물건을 두개 구매했다
    - 나, 상점, 물건 / 객체
    - 사과 두개 / 상태
    - 구매 / 행동   


```java
class apple{
  public static void main(String[] args){
    int apple = 20; // 사과의 수, 상태정보, 변수
    int money = 0; // 판매수익, 상태정보, 변수
    int sale = sc.nextInt(); // 판매량, 상태정보, 변수
    
    //판매행위를 메서드로 표현
    int num = sale/1000; // 개당 1000원
    apple -=num; // 사과 보유량
    money +=sale; // 판매수익
  }
}
```

- 객체를 생성하기전, 생성을 위한 틀을 만들어야 한다.
  - 객체 붕어빵 / 틀 붕어빵틀
  - 객체 변수, 메서드 등 / 틀 클래스

 
***

## 2. 클래스
- 코드화시킨 클래스
  - 필드 : 클래스의 멤버 변수
  - 메서드 : 클래스의 멤버 함수
```java
class 클래스명 {
    멤버변수;
    메서드();
}
```

```java
class Account{
	String accID; // 계좌번호
	String name; // 이름
	int balance; // 잔액
	
	//2. 메서드
	public void deposit(int money) { // 입금
		balance+=money;
	}
	public void withdraw(int money) { // 출금
		balance-=money;
	}
	public void showInfo() { // 추가 메서드
		System.out.println("계좌번호 : " + accID);
		System.out.println("이름 : "+name);
		System.out.println("잔액 : " + balance);
	}
} //class Account

//클래스 작성 후, 클래스로부터 객체 생성후 사용
//객체를 사용한다는 것 : 객체가 가지고 있는 속성과 기능을 사용

public class AccountTest {

	public static void main(String[] args) {
		//1. 객체 생성 - 해당 클래스의 멤버변수와 메서드를 메모리에 할당
		Account acc;
		acc = new Account();
		
		//2. 메서드 사용
		acc.showInfo(); // 멤버변수는 자동으로 디폴트값으로 초기화
		
		//3. 두번째 객체 생성
		Account acc2 = new Account();
		
		//4. 멤버변수 사용
		acc2.accID = "100-000-2000";
		acc.name="홍길동";
		acc2.balance=1000000;
		
		//5. 메서드 사용
		acc2.withdraw(30000); // 30000원 출금
		acc2.showInfo();
		
		acc2.deposit(50000); // 50000원 입금
		System.out.println("현재 잔액 : "+acc2.balance);
	}
}
```
- new
	- 객체 생성을 명령
	- 메모리 공간에 객체 생성
	- 클래스의 인스턴스화 (instantiation)
	- 클래스로부터 만들어진 객체 : 인스턴스
- 객체와 인스턴스
	- 객체 : 모든 인스턴스를 포괄하는 의미
	- 인스턴스 - 어떤 클래스로부터 만들어진 것인지를 강조하는 구체적인 의미
		- 계좌번호는 객체
		- 계좌번호는 Account 클래스의 인스턴스
- 클래스
	- 클래스에 존재하는 객체는 메모리 공간에 할당되어있지 않음
	- 접근 호출 불가능한, 틀로서만 역할
- 객체
	- 메모리 공간에 할당됨
- 클래스의 정의
	- 사용자 정의 자료형을 정의하는 것
	- 서로 관련된 변수들을 정의
	- 이들에 대한 작업을 수행하는 메서드들을 함께 정의

### a. 클래스 기반 객체 생성
- 클래스는 객체가 아니다.
	- 클래스 내 변수에 접근하고 메서드를 호출하기 위해서는 클래스를 객체화시켜야한다.
		> 클래스이름 변수명 = new 클래스이름();   
		> Account acc = new Account();   

			- Account 객체를 생성하고 이를 acc라는 이름의 변수로 참조
			- acc를 통해 각각의 객체에 접근할 수 있게 된다.
			- acc : 참조변수
### b. 생성된 객체의 접근 방법
- 객체의 변수에 값 저장
	- Account acc = new Account();
	- acc.balance = 1000000;
- 객체의 메서드 호출
	- acc.withdraw(30000);
- .연산자를 이용, 객체에 접근

***

## 3. 생성자
### a. 생성자란
- 객체가 생성될 때 자동호출되어 가장 먼저 실행되는 메서드
- 멤버변수의 초기화 목적
- new연산자가 힙 영역에 메모리를 생성한 직후 호출
- 조건
	- **클래스의 이름과 동일**한 이름의 메서드
	- 매개변수는 가질 수 있으나 **반환값은 가질 수 없다**
> 클래스이름 변수이름 = new 클래스이름([매개변수]);   
- new연산자 우측 클래스이름 = 생성자

### b. 기본생성자(default 생성자)
- 따로 정해주지 않았을 때 기본으로 제공되는 생성자
	- 컴파일러에 의해 자동으로 만들어진다.
- 매개변수를 가지지 않는다.
- 다른 생성자가 있으면 컴파일러에 의해 default 생성자가 만들어지지 않는다.
- 반환값도 없으며 void도 아니다.
- 모든 필드를 0, false, null로 초기화

```java
class Account{
	Account(){
		...
	}
}
```


### c. 재정의 생성자 (매개변수가 있는 생성자)
- 사용자가 임의로 다시 만들어 놓은 생성자   


```java
class Account {
	//1. 멤버변수
	String accID; //계좌번호
	String name; //이름
	int balance; // 잔액
	
	//2.1 기본생성자
	Account(){
		
	}
	
	//2.2 매개변수가 있는 생성자
	Account(String p_accId, String p_name, int p_balance){
		accID = p_accId;
		name = p_name;
		balance = p_balance;
	}
	
	//3. 메서드
	public void deposit(int money) {
		balance += money;
	}
	public void showInfo() {
		System.out.println("계좌번호 : "+accID);
		System.out.println("이름 : "+name);
		System.out.println("잔액 : "+balance);
	}
	public void withdraw(int money) {
		balance-=money;
	}

}
public class AccountTest{
	public static void main(String[] args) {
		Account acc2 = new Account(); // 기본생성자 호출
		
		//멤버변수 사용
		acc2.accID = "111-111-1111";
		acc2.name="홍길동";
		acc2.balance=100000;
		
		//메서드 사용
		acc2.withdraw(30000);
		acc2.showInfo();
		
		
		//매개변수 있는 생성자 이용
		Account acc = new Account("1000-1010-10", "김길동",20000);
		acc.showInfo();
	}
}
```

***

## 4. 접근 제한자
### a. 접근제한자란
- 멤버나 클래스에 사용되어, 해당하는 멤버나 클래스를 외부에서 접근하지 못하도록 제한
- 은닉성 : 객체는 필요한 것만 외부에 노출, 그외의 것은 은닉
- 클래스 내부의 멤버를 노출하거나 숨길 때 사용
- 클래스, 멤버변수, 메서드, 생성자에 사용
	> private : 같은 클래스 내에서만 접근 가능   
	> default(생략형) : 같은 패키지 안에 있는 클래스에서만 접근 가능   
	> protected : 다른 패키지여도 상속관계, 자식클래스는 접근 가능   
	> public : 어디서나 접근 가능

### b. 요소별 사용가능 접근제한자
- 클래스 : public, default
- 메서드 : public, protected, default, private
- 멤버변수 : public, protected, default, private
- 지역변수 : 없음

### c. 변수, 메서드 접근제한자
- 클래스 : 일반적으로 멤버변수는 숨기고(private), 메서드는 노출(public)
	- 멤버변수 private : 클래스 외부 접근 불가, 내부에서만 접근 가능
	- 메서드 public : 외부에서도 접근 가능

- 클래스의 멤버변수에 값을 입력하고 싶을 때 메서드에 매개변수를 넘기는 방법 이용
	- 멤버변수는 private으로 지정, 메서드를 public으로

```java
package thisPackage;

class privateVar{
	private int x = 10;
	int y=20;
	protected int z = 30;
	public int n = 40;
	public void showInfo() {
		System.out.println("x="+x);
		System.out.println("y="+y);
		System.out.println("z="+z);
		System.out.println("n="+n);
	}
}

public class PrivateTest {

	public static void main(String[] args) {
		privateVar a = new privateVar();
		a.showInfo();
		// a.x = 20; => the field privateVar.x is not visible
		// 멤버변수가 private이라 변경 불가능
		
		a.y=1; // 접근가능 default
		a.z=1; // 접근가능 protected
		a.n=1; // 접근가능 public
		
		otherPackage.PrivateVar2 b = new otherPackage.PrivateVar2(); // 다른패키지 클래스
		//b.x = 20; //접근불가 private
		//b.y=1; // 접근불가 default
		//b.z=1; // 접근불가 protected
		b.n=1; // 접근가능 public
	}

}
```

### d. 클래스 접근제한자
- public 클래스 / 어디서나 접근 가능
- default 클래스 / 같은 패키지 내에서만 접근 가능

```java
package thisPackage;

public class classTest {

	public static void main(String[] args) {

		//otherPackage.defaultClass dc = new otherPackage.defaultClass();
		//other패키지의 default클래스는 접근이 불가능해 error
	
		otherPackage.publicClass pc = new otherPackage.publicClass();
		//other패키지의 public클래스는 접근 가능
	}

}
```

### e. getter/setter
- private으로 보호되는 멤버변수의 값을 가져오거나 변경하기 위해 사용
- ger~(), set~() 메서드
- 멤버변수 하나당 한쌍의 getter, setter 메서드 필요
	> pirvate int a;   
	> public void seta(int p_a){   
	> a = p_a;      
	> }   
	> public int geta(){   
	> return a;   
	> }

```java
class Man2{
	private int age;
	int height;
	
	public int getAge() { // 값을 읽어오기 위해 사용
		return age;
	}
	public void setAge(int p_age) { // 값을 저장하기 위해 사용
		if(p_age<1) {
			p_age = 1;
			
		}else if(p_age>100) {
			p_age = 100;
		}
		age = p_age;
	}
}

public class ManTest2 {

	public static void main(String[] args) {
		Man2 m = new Man2();
		m.height = 1800;
		//m.age=20; error
		m.setAge(1800); // setter 이용 값 저장
		
		System.out.println("height = "+m.height);
		//System.out.println("age = " + m.age); error 접근불가
		System.out.println("age = "+m.getAge()); // getter 이용 값 읽기

	}

}
```
- private으로 보호받지 못하는 height는 1800이라는 값이 그대로 들어가버린다.
- private으로 보호받는 age는 범위 지정을 통해 잘못된 값을 걸러낼 수 있다.

***

## 5. this
- 자기참조 변수
- 인스턴스의 주소가 저장되어있다.
- 객체를 메모리에 로드했을 때, 로드된 자기 자신을 나타낼 때 사용
- 클래스 내부에서 **자기 자신의 클래스**를 지칭
- **클래스를 디자인 할 때 사용**, 객체 생성후 사용하지 않는다
	- 클래스 안에서만 사용가능, 밖에서는 쓸 수 없다.
	- static 메서드에서는 사용할 수 없다.

```java
class thistest{
	private int num = 10;
	public void set(int num){
		this.num = num; //this를 통해 멤버변수를 구분하기에 같은 변수이름을 사용할 수 있다.
	}
}
```

***

## 6. 클래스와 배열
- int 배열에는 int를, String배열에는 String을 넣는다.
- 객체 또한 배열에 넣을 수 있다.   


```java
//변수에 값 넣기
int a = 10; // int값을 갖는 a 변수에 10을 넣는다
String s = "java"; // String 값을 갖는 s 변수에 java를 넣는다
Account acc = new Account(); // 객체생성 또한 Account클래스를 갖는 acc 참조변수에 객체를 넣는 것

//배열에 값 넣기
int[] a = new int[2] // int배열 a 생성
a[0] = 0; // a배열 0번인덱스 값 0을 넣는다
a[1] = 1; // a배열 1번인덱스 값 0을 넣는다

Account[] accArr = new Account[2]; // account 클래스 배열 accArr 생성
accArr[0] = new Account("111-111", 10000); // accArr클래스 배열 0번 인덱스 객체를 넣는다
accArr[1] = new Account("222-222", 2000); // accArr클래스 배열 1번 인덱스 객체를 넣는다
```
- 객체생성 후 참조변수를 이용하는 것과 마찬가지로 배열을 이용할 수 있다.
```java
Account[] accArr = new Account[2];
accArr[0] = new Account("111-111", 10000);
accArr[1] = new Account("222-222", 2000);

accArr[0].display(); //0번인덱스에 저장된 객체의 메서드 이용

for(int i=0; i<accArr.length;i++){
	accArr[i].display();
} // for문 이용

for(Account acc : accArr) {
	acc.display();
} // 확장for
```

***

## 7. 기본 매개변수와 참조형 매개변수
- 기본형 매개변수 (call by value)
	- 변수의 값을 읽기만 할 수 있다
	- 매개변수가 기본자료형, 값이 전달
	- 메서드에서 매개변수의 값을 변경해도 호출한 곳에서는 변경의 영향을 받지 않는다.
- 참조형 매개변수 (call by reference)
	- 변수의 값을 읽고 변경할 수 있다
	- 매개변수가 참조형, 주소가 전달됨
	- 메서드에서 매개변수의 값을 변경하면 호출한 곳에서도 변경의 영향을 받는다.

