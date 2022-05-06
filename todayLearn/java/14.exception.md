# 예외 (Exception)
## 1. 예외 기본
### a. 예외 란
- 프로그램의 **실행 도중에 발생하는** 예상치 못한 **오류**
- 컴파일 시 발생하는 문법적인 에러는 예외 x
  - 나눗셈 시 나누는 수로 0이 입력
  - 나이 입력 시 0보다 작은 값 입력 등

- 처리되지 않은 예외는 프로그램의 실행 중단 원인
- 에러
  - 컴파일 에러(compile error)
  - 실행 에러(Runtime error) : 프로그램 실행 도중 발생되는 에러
    - 에러(error) : 메모리부족, 스택오버플로우 등 발생하면 복수할 수 없는 심각한 에러
    - 예외(exception) : 발생하더라도 수습될 수 있는 비교적 덜 심각한 오류

### b. 예외클래스
- Exception 클래스
	- getMessager()메서드
		- String형 반환
		- 예외가 발생한 원인에 대한 설명
		- 발생한 예외 클래스의 인스턴스에 저장된 메세지 획득
	- printStackTrace() 메서드
		- 예외발생 당시의 호출스택에 있었던 메서드의 정보와 예외메세지 출력
		- 예외가 발생한 위치에 대한 정보
		- 예외가 발생해서 전달되는 과정 출력 

- 예외클래스의 계층구조
	- **Runtime Exception**클래스 및 자식클래스
		- 개발자의 실수에 의해 발생
			- 배열의 범위를 벗어나는 경우(IndexOutOfBoundsException)
			- 값이 null 인 참조변수의 멤버를 호출하려 하는 경우(NullPointerException)
			- 클래스간의 형변환을 잘못한 경우(ClassCastException)
			- 정수를 0으로 나누려 하는 경우에 발생하는 예외들(ArithmeticException)
		- 예외처리 안해도 됨

	- **Exception**클래스 및 자식클래스
		- 사용자의 실수와 같은 외적 요인에 의해 발생
			- 존재하지 않는 파일의 이름을 입력하는 경우(FileNotFoundException)
			- 실수로 클래스의 이름을 잘못 적은 경우(ClassNotFoundException)
			- 입력한 데이터 형식이 잘못된 경우(DataFormatException)
		- **반드시 예외처리**
		- IOException
			- input/output 작업 처리시 발생하는 예외상황
			- RuntimeException은 프로그램 내부의 원인
			- IOException은 주로 JVM 외부의 원인   

## 2. 예외처리
- 예외의 발생에 대비한 코드를 작성하는 것
- 프로그램 실행 도중 발생한 에러를 처리, 에러에 의해서 비정상적으로 프로그램이 종료되는 것을 막아줌
- 비정상 종료를 막고, 정상적인 실행상태를 유지할 수 있도록 하는 것   

## 3. try~catch

```java
try{
  예외가 발생할 만한 코드들 배치
}catch(Exception e){
  예외를 잡아 실패에 대한 처리를 하는 코드
}
```

- 예외처리 없을 시   


```java
import java.util.Scanner;

public class Test1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두개의 정수 입력");
		int n1 = sc.nextInt();
		int n2 = sc.nextInt(); 
    //몫에 0 또는 문자를 넣으면 다음 구문까지 가지 못하고 실행에러 발생, 프로그램 종료됨
		
		System.out.println("목:"+n1/n2);
		System.out.println("나머지:"+n1%n2);
		
		System.out.println("\n----next----");
	}
}
```
- 예외처리 사용   


```java
import java.util.Scanner;

public class TryTest1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("두개의 정수 입력");
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			int result = n1/n2;
			
			System.out.println("목:"+result);
			System.out.println("나머지:"+n1%n2);
		}catch (Exception e) {
			System.out.println("예외 발생"+e.getMessage()); //예외 발생/ 발생이유 출력
		}//몫에 0 또는 문자를 넣어도 프로그램이 종료되지 않는다
		
		System.out.println("\n----next----");
	}
}
```
- 자바 가상머신이 예외상황 인식
- ArithmeticException 클래스의 인스턴스 생성
- 가상머신에 의해 생성된 인스턴스의 챔조값을 catch영역 매개변수에 전달 (Exception e)
- e.getMessage()
  - 매개변수의 참조변수를 이용, 예외상황 발생이유를 담은 문자열을 반환   

- for문에서 예외처리 이용   

```java
import java.util.*;
public class TryTest2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i<2; i++) {
			try {
				System.out.println("두 정수 입력");
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				
				System.out.println("나눗셈:"+n1/n2);
			}catch(ArithmeticException e) {
				System.out.println("예외 : "+e.getMessage());
			}
		}
	}
} //예외가 발생해도 for문은 계속 돈다.
```

## 3. 예외타입
- ArrayIndexOutOfBoundsException
	- 배열의 접근에 잘못된 인덱스값을 사용했을 때 던져짐
- NumberFormatException
	- int형 숫자로 변경될 수 없을때 예외발생
- ArithmeticException
	- 0으로 나눗셈을 하는 등의 수학적 연산이 불가능한 상황
- ClassCastException
	- 허용할 수 없는 형변환 연산을 진행하는 예외상황
- NullPointerException
	- 참조변수가 null 로 초기화된 상황에서 메소드를 호출하는 예외상황
- NegativeArraySizeException
	- 배열선언 과정에서 배열의 크기를 음수로 지정하는 예외상황   

```java
public class ExceptionTest {

	public static void main(String[] args) {

		try {
			int[] arr = new int[3];
			arr[3] = 30;
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("예외 : "+e.getMessage());
		}
		
		
		try {
			String s = "a";
			int a = Integer.parseInt(s);
		}catch(NumberFormatException e) {
			System.out.println("예외 : "+e.getMessage());
		}
		
		
		try {
			int n1 = 5, n2=0;
			int res = n1/n2;
		}catch(ArithmeticException e) {
			System.out.println("예외 : "+e.getMessage());
		}
		
		
		try {
			Object obj = new int[4];
			String srt = (String)obj;
			//Circle = (Circle)new Shape();
		}catch(ClassCastException e) {
			System.out.println("예외 : "+e.getMessage());
		}
		
		
		try {
			String str = null;
			int len = str.length();
		}catch(NullPointerException e) {
			System.out.println("예외 : "+e.getMessage());
		}
		
		
		try {
			int[] arr = new int[-4];
		}catch(NegativeArraySizeException e) {
			System.out.println("예외 : "+e.getMessage());
			e.printStackTrace();
		}
	}
}
```


## 4. 다중 예외처리
- try문 안에서 발생가능한 모든 예외에 대해 여러개의 catch문을 나열, 각각 다르게 처리
- 어떤 에러가 발생했는지 구분 가능
- 발생한 예외의 종류와 일치하는 단 한개의 catch블럭 수행
- 하위에서 상위 순으로 와야 한다
- 특수하고 상세한 예외가 앞쪽, 일반적인 예외게 뒤쪽   

```java
public class MultiCatch {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("숫자입력");
			int num = sc.nextInt();
			int res = 100/num;
			System.out.println(res);
		}catch(ArithmeticException e) {
			System.out.println("0으로 나누지 마세요"+e.getMessage());
		}catch(InputMismatchException e) {
			System.out.println("정수만 입력"+e);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
```
## 5. throw
- 프로그램의 실행에서 비정상적 상황을 알리기위해 강제로 예외 발생
- 개발자가 고의로 예외를 직접 발생시킬 수 있다.
- 자바 가상머신에 의해 인식되는 예외상황은 아니지만 프로그램의 성격에 따라 개발자가 정의한 예외상황인 경우 throw문 사용  

```java
public class ThrowTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("나이 입력");
			int age=sc.nextInt();
			if(age<0||age>150) {
				throw new Exception("나이는 양수여야하고, 150보다 작아야함");
			}
			
			System.out.println("나이 : "+age);
		}catch(Exception e) {
			System.out.println("예외 : "+ e.getMessage());
		}
		System.out.println("다음문장");
	}
}
```

## 6. finally
- 예외 발생여부와 상관없이 반드시 실행되어야 하는 구문을 입력하는 곳
- 예외가 발생해도 호출되며, 그렇지 않아도 호출됨
- try 영역으로 일단 들어가면 무조건 실행되는 영역
- 사용했던 자원 해제 : 파일닫기 등   

```java
try {
	예외가 발생할 가능성이 있는 문장
}
catch(){
	예외 처리
}
finally {
	예외의 발생여부에 관계없이 항상 수행되어야 하는 문장
}
```
```java
import java.util.Scanner;

public class FinallyTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("숫자 입력");
			int n = sc.nextInt();
			int res = 100/n;
			System.out.println(res);
		}catch(Exception e) {
			System.out.println("예외 : "+e.getMessage());
		}finally {
			System.out.println("반드시 실행");
		}
		System.out.println("\n======");
	}
}
```


- 메서드 내 return문과 사용 시   

```java
public class FinallyTest2 {
	public static boolean divider(int n1, int n2) {
		try {
			int result = n1/n2;
			System.out.println("나눗셈:"+result);
			return true; // 중간에 return을 하더라도 finally영역 실행 후 메서드 탈출
		}catch(ArithmeticException e) {
			System.out.println(e.getMessage());
			return false;
		}finally {
			System.out.println("finally영역 실행");
		}
	}
	public static void main(String[] args) {
		boolean divOk = divider(7,3);
		System.out.println(divOk);
		
		divOk = divider(7,0);
		System.out.println(divOk);
		
	}

}
```

## 7. throws
- 예외처리 방법
	- try~catch문 사용
	- 예외를 메서드에 선언하는 방법(예외전달, 떠넘기기)
		- 메서드의 선언부에 throws 이용
		- void method() throws Exception1, Exception2, ..ExceptionN{}
		- 메서드를 사용하는 쪽에서는 이에 대한 처리 강요
		- 일반적으로 RuntimeException 클래스들은 적지 않는다.   

```java
public class ThorwsTest1 {

	public static void method1() throws Exception {
		method2();
	}
	public static void method2() throws Exception {
		throw new Exception("메서드2 예외문구");
	}
	
	public static void main(String[] args) {

		try {			
			method1();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
```

## 8. 사용자 정의 예외 클래스
- 필요에 따라 개발자가 새로운 예외 클래스를 정의하여 사용할 수 있음   

```java
class MyException extends Exception{
	MyException(String msg){
		super(msg); //부모인 Exception 클래스의 생성자를 호출함
	}
}
```
```java
import java.util.Scanner;

/*
 	사용자 정의 예외 클래스 만들기
 	Exception을 상속받고, super(message) 이용 Exception 생성자에 메세지를 넘겨준다
 	public Exception(String message)
 */
class AgeInputException extends Exception{
	public AgeInputException() {
		super("유효하지 않은 나이");
	}
}

public class UserExTest {

	public static int readAge() throws AgeInputException {
		Scanner sc = new Scanner(System.in);
		System.out.println("나이 입력");
		int age = sc.nextInt();
		if(age<0||age>150) {
			throw new AgeInputException();
		}
		return age;
	}
	
	public static void main(String[] args) {
		try {
			int age = readAge();
			System.out.println("나이는 "+age + "세");
		}catch(AgeInputException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
```
