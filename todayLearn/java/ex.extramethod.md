# 기타 메서드 및 참고사항
## Escape Sequence
    - **문자열 안에서** 특별한 의미로 해석되는 문자
    - 표현하기 어려운 문자상수를 표현한다.
        > \n 개행   
        > \t 탭   
        > \" 큰 따옴표   
        > \\ 역슬래쉬

```java
public class EscSequence{
    public static void main(String[] a){
        System.out.println("a+b"); // 출력 a+b
        System.out.println("a"+"b"); // 출력 ab
        System.out.println("a\"+\"b"); // 출력 a"+"b
    }
}
```

## 사용자로부터 입력
### a. Scanner클래스
    - 사용자로부터 정보를 입력받을 때 사용한다.
    - java.util 패키지 내에 있어, import 예약어를 이용한다.
    > public boolean nextBoolean()   
    > public byte nextByte()   
    > public short nestShort()   
    > public int nextInt()   
    > public long nextLong()   
    > public float nextFloat()   
    > public double nextDouble()   
    > public String nextLine()

```java
import java.util.*; //스캐너가 들어있는 package
class ScannerTest{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in); //입력받은 데이터를 저장할 참조변수

        String name = sc.nextLine(); //데이터 입력

    }
}
```

### b. System.in.read
- 스캐너 이용시 nextChar 이란 메서드는 없었다.
- System.in.read() 메서드를 이용한다.
- 사용자가 입력한 데이터의 앞의 1바이트만 읽는다.
- 아스키코드 값을 반환해준다.
- ABC 입력 시 A에 해당하는 코드 65를 반환
- java.io 패키지에 존재한다.

```java
import java.io.*;
class If{
  public static void main(String[] args) throws IOException{
    System.out.println("약관에 동의합니까(Y/N)");
    char agree = (char)System.in.read();
    
    switch(agree){
      case 'Y':
        System.out.println("동의");
        break;
      case 'N':
        System.out.prinln("반대");
        break;
      default:
        System.out.println("잘못입력");
        break;
      }
  }
}
```


## String 클래스 
### a. equals()
- 기본자료형에서 등가연산자(==)는 값을 비교한다. (a==b)
- 참조형에서 등가연산자는  주소값을 비교한다.
- 문자열 내용 비교는 String 클래스의 equals()메서드 이용

```java
import java.util.*;
class If{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    System.out.println("약관에 동의합니까(Y/N)");
    String agree = sc.nextLine();
    if (agree.equals("Y"){
      System.out.println("동의하셨습니다");
    }
  }
}
```

## Character클래스
### a. 대문자 변환 메서드
- Character.toUpperCase()

```java
import java.io.*;
class a62 {
	public static void main(String[] args) throws IOException{
		System.out.println("a를 입력하세요");
		char pick = (char)System.in.read();
    
    System.out.println(pick); // a
		char pick = (char)System.in.read();
		pick = Character.toUpperCase(pick);
		System.out.println(pick); // A

	}
}
```

### b. 숫자, 알파벳 여부 확인 메서드
- Character.isAlphabetic();
  - 알파벳인 경우 true를 반환
- Character.isDigit();
  - 숫자인 경우 true를 반환

```java
import java.util.*;
import java.io.*;
class  a66{
	public static void main(String[] args) throws IOException {
		String rst3 ="";
		System.out.println("0~9나 알파벳, 그외 문자를 입력하세요");
		char pick = (char)System.in.read();
		if(Character.isAlphabetic(pick)){
			rst3 ="알파벳 문자";
		}else if(Character.isDigit(pick)){
			rst3 ="숫자";
		}else{
			rst3="기타 문자";
		}
		System.out.println("입력한 값: "+pick+"\n"+rst3+"입니다");
	}
}
```

## 문자열 길이 확인
- 변수,length()

> String a = "가나다라마바";
> int b = a.length(); // b=6

## 문자열 뽑기
- 변수.charAt(i)
- String의 문자열중 한글자만 선택해 char 타입으로 변환
- i값은 String 문자열 내 index번호
- index는 0부터 문자길이-1 까지

```java
class cA{
	public static void main(String[] args){
		String str = "가나다라마"
		// index 0번 : 가
		// index 4번 : 마
		char c = str.charAt(0); // c에 '가'가 저장된다.
		char d = str.charAt(3); // d에 '라'가 저장된다.
		System.out.println(c); //가
	}
}
```

-nextLine 뒤에 붙여 바로 뽑아내는 것도 가능하다.
> String a= "가나다라마";
> char b = sc.nextLine().charAt(1);

## hasNext()
- 다음 데이터가 있다면 true를 반환한다.

```java
		Scanner sc = new Scanner("\n\n가나다 하나 둘\n\n라\n\n");
		String str = "";
		while(sc.hasNext()) {
			str=sc.nextLine();
			System.out.println("출력 : " + str);
		}//while
		
		str = sc.nextLine();
		System.out.println("while문 탈출 후 1:" + str);
```

> 출력 : //\\n   
> 출력 : //\\n  
> 출력 : 가나다 하나 둘 //가나다 하나 둘\\n   
> 출력 : //\\n  
> 출력 : 라 //라\\n   
> while문 탈출 후 1:   

- nextLine은 엔터값 기준으로 데이터를 읽는다.
- 다음 데이터가 엔터값 하나라면 데이터가 없다 판단해 false를 반환한다.

```java
		Scanner sc = new Scanner("\n\n가나다 하나 둘\n\n라\n\n");
		String str = "";
		while(sc.hasNext()) {
			str=sc.next();
			System.out.println("출력 : " + str);
		}//while
		
		str = sc.nextLine();
		System.out.println("while문 탈출 후 1:" + str);
		str = sc.nextLine();
		System.out.println("while문 탈출 후 2:" + str);
		str = sc.nextLine();
		System.out.println("while문 탈출 후 3:" + str);
```

> 출력 : 가나다   
> 출력 : 하나  
> 출력 : 둘   
> 출력 : 라   
> while문 탈출 후 1:   
> while문 탈출 후 2:   
> while문 탈출 후 3: //데이터가 없기 때문에 error

- nextLine은 공백 기준으로 데이터를 읽는다.

## Math클래스
### a. Math.round
- 반올림 함수
- 실수를 반올림하여 정수로 반환한다.
- float => int
- double => long

> float a = 3.1415f;   
> int b = Math.round(a); // b=3

- 소숫점 자리를 원할경우

> float a= 3.1415f;   
> int b = Math.round(a*100); // b=314;   
> float c = b/100f; // c = 3.14;   

### b. Math.random()
- 0.0 <= x < 1.0 의 실수를 랜덤으로 추출한다.
- 원하는 범위의 정수 추출하기 ex(1~100)
	- 범위 곱하기
		- 범위 100
		- Math.random()*100
		- 0.0 <= x < 100.0
	- 시작값 더하기
		- 시작값 1
		- Math.random()*100 +1
		- 1.0 <= x < 101.0
	- 정수로 변환
		- (int)(Math.random()*100+1)
		- 1 <= x < 101

- 원하는 범위의 숫자 추출하기 ex(a (97) ~ z (122))
	- 범위 곱하기
		- 범위 26
		- Math.random()*26
		- 0.0 <= x < 26.0
	- 시작값 더하기
		- 시작값 97
		- Math.random()*100 +1
		- 97.0 <= x < 123.0
	- 정수로 변환
		- (int)(Math.random()*100+1)
		- 97 <= x < 123

