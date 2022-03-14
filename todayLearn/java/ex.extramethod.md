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
### c. 명령줄 인수(Command-Line Arguments)
- 자바프로그램의 main 메서드의 형식을 보면
	> public static void main(String[] args)
- main메서드 또한 문자열 배열을 매개변수로 받는 메서드임을 알 수 있다.
- cmd창에서 자바프로그램 실행 시, 배개변수 입력을 통해 main메서드에 매개변수를 전달할 수 있다.
	> 기존 자바프로그램 (Test.class) 실행 시
	> 파일 경로> java Test   
	> 매개변수 전달 시   
	> 파일 경로> java Test 매개변수1 매개변수2 매개변수3 ...
- main 메서드 내에서 args[] 배열을 통해 문자열에 접근할 수 있다.

```java
class CmdTest{
	public static void main(String[] args){
		for (String s : args) System.out.println(s) ;
		// cmd창에서 프로그램 실행과 동시에 매개변수를 입력한 만큼
		// 해당 매개변수가 출력되는 것을 알 수 있다.
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

### b. toString()
- Object클래스의 toString()메서드
- 해당 객체가 어떤 객체인지 문자열로 표현하는 값 리턴
	- 클래스명@16진수 해시코드
- p.toString()과 p를 출력한 결과값 동일
	- 클래스의 멤버가 할당된 주소를 숨기려는 특성=>
	- 직접 주소 칠력시, 자동으로 출력형식의 메서드 (toString)로 연결
- 자동으로 호출되는 경우
	- System.out.pritnln() 메서드에 매개변수로 들어가는 경우
	- 객체에 대하여 더하기 연산을 하는경우
- 클래스에서 오버라이딩 후 사용 가능
- toString 결과값 확인   



```java
class Person{
	public void showInfo() {
		System.out.println(this);
	}
}

public class ToStringTest {

	public static void main(String[] args) {
		Person p = new Person();
		p.showInfo();
		
		System.out.println("p객체 문자열"+p.toString());
		System.out.println("p"+p);
		System.out.println(p);

	}
}
```
- toString 오버라이딩   

```java
class Person2{
	private String name;
	private int age;
	
	//object의 toString()메서드를 오버라이딩
	public String toString() {
		return "Person2[name="+name+", age="+age+"]";
	}
}

public class ToStringTest2 {

	public static void main(String[] args) {
		Person2 p =new Person2();
		System.out.println(p);
		System.out.println(p.toString());
		

	}

}
```

### c. length
- 변수,length()
- 문자열의 길이 확인

> String a = "가나다라마바";
> int b = a.length(); // b=6

### d. charAt
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

### e. indexOf, substring
- indexOf
	- 문자열을 첫부분부터 검색, 입력한 문자열을 만나는 위치를 int로 반환
- lastIndexOf
	- 문자열을 마지막 부분부터 검색, 입력한 문자열을 만나는 위치를 int로 반환
- substring
	- 일부 문자열 추출   

```java
public class StringTest {

	public static void main(String[] args) {
		            //012345678901234567890123456789
		String str = "Hello Java, Hi Java!";
		char ch = str.charAt(4); //o
		System.out.println("ch="+ch);
		
		int idx = str.indexOf("Java");
		System.out.println("앞 Java의 위치 : "+idx); //6
		System.out.println("뒤 Java의 위치 : "+str.lastIndexOf("Java")); //15
		
		String sub = str.substring(6,10); //6<= x < 10, Java
		System.out.println("sub="+sub);
		System.out.println(str.substring(15)); //15부터 끝까지, Java!
		
		String s = "www.nate.com";
		if(s.startsWith("www")) {
			System.out.println("www로 시작함");
			
		}
		if (s.endsWith(".com")) {
			System.out.println(".com으로 끝남");
		}
		
		            //01234567890123
		String email="hong@gmail.com"; 
					//
		if(email.indexOf("@") != -1&& email.indexOf(".")!=-1
				&& email.indexOf("@")<email.indexOf(".")) {
			System.out.println("이메일 규칙이 올바르다");
		}else {
			System.out.println("이메일 규칙이 올바르지 않다");
		}
		
		int gol = email.indexOf("@");
		int dot = email.lastIndexOf(".");
		//hong만 추출
		System.out.println(email.substring(0,gol));
		//com
		System.out.println(email.substring(dot+1));
		//gmail
		System.out.println(email.substring(gol+1,dot));
	}
}
```

### . String[] split
- 특정 문자들을 경계로 하여 문자열을 여러개의 토막으로 분리
- 인수로 배열을 전달하면, 토막 문자열을 배열형태로 리턴   

```java
package com.day23.extra;

public class SplitTest {

	public static void main(String[] args) {
		//String[] split(String regex)
		str = "java,oracle,jsp,spring";
		String[] arr = str.split(",");
		for(String s3 : arr) System.out.println(s3);
		
		System.out.println("\n\n");
		str="html.css.js.jquery";
		String[] arr2 = str.split("\\.",3); //특정 기호로 잘리지않으면 역슬래시 두개
						//두번째 매개변수는 limit, 자를 개수
		for(String s3 : arr2) System.out.println(s3);

	}
}
```
### . replace
- 문자열을 다른 문자열로 교체   

```java
public class SplitTest {

	public static void main(String[] args) {
		String str = "123456789";
		String s = str.replace('7', '칠');
		String s2 = str.replace("89", "여덟아홉");
		System.out.println(s);
		System.out.println(s2);
	}
}
```
### . trim
- 앞위 공백 제거   

```java
s="             java spring!!          ";
String s2= s.trim();
System.out.println("s2=["+s2+"]"); // [java spring!!]
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
- 수학 함수들을 제공하는 클래스
- 모든 계산 메서드는 static
- 두 개의 상수
	- Math.E
		- 자연로그의 밑, 2.7182
	- Math.PI
		- 원주율, 3.1415
- abs() : 절대값
- ceil() : 올림
- floor() : 내림


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
> static int round (float a)   
> static long round (double a)   


```java
class mr{
	public static voud main(String[] a){
		int num1 = Math.round(3.14f); // 3
		long num2 = Math.round(-3.8); // -4
	}
}
```
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

## array클래스
### a. arrays.sort()
- 배열 요소를 오름차순 정렬해주는 메서드
- public static void sort(int[] a)

```java
import java.util.*;
public class SortTest {
	public static void main(String[] args) {
		int[] arr = {10,5,44,55,1000,140,3,55};
		
		System.out.println("sort 정렬전");
		for(int i: arr)System.out.print(i+"\t");
		System.out.println("\n");
		
		System.out.println("sort 정렬후");
		Arrays.sort(arr);
		for(int i:arr) System.out.print(i+"\t");
		System.out.println("\n");
	}
}
```

## Calendar & Date 클래스
- 날짜와 시간에 관련된 데이를 처리하기 위한 클래스
- java.util에 존재
- Calendar
	- 추상클래스로, 메서들 통해 구현된 클래스의 인스턴스를 얻어야 한다.
	- Calendar cal = Calendar.getInstance();
		- getInstance는 캘린더 클래스를 구현한 클래스의 인스턴스 반환
	- Calendar cal = new GregorianCalendar();
	- GregorianCalendar cal = new GregorianCalendar()
		- 양력을 나타내는 자식클래스   

- Calendar를 Date로
	- Date date = cal.getTime();
- Date를 Calendar로
	- cal.setTime(date);

```java
import java.util.*;


public class DateTest1 {

	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d);
		System.out.println(d.toLocaleString());
		
		int year=d.getYear();
		System.out.println((year+1900)+"년");
		System.out.println((d.getMonth()+1)+"월 "+d.getDate()+"일");
		//=> 월은 0~11 리턴
		
		System.out.println(d.getHours()+":"+d.getMinutes()+":"
				+d.getSeconds());
		
		System.out.println("요일:"+d.getDay()); //0 : 일요일
		
		//1970-01-01 0:0:0 기준 경과된 시간을 밀리초로 리턴
		long gap = d.getTime()/1000;  //초 (초는 1000밀리초)
		System.out.println("1970~ 경과시간(초)"+gap);
		
		gap=gap/(24*60*60); //일
		System.out.println("경과일수:"+gap+"일");

	}
}
``` 
```java
public class CalanderTest {

	public static void main(String[] args) {
		// Calendar cal = new Calendar(); error
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = new GregorianCalendar();
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int h = cal.get(Calendar.HOUR); //12시간
		int h2 = cal.get(Calendar.HOUR_OF_DAY); //24시간
		int m = cal.get(Calendar.MINUTE);
		int s = cal.get(Calendar.SECOND);
		int week = cal.get(Calendar.DAY_OF_WEEK);
		
		System.out.println(year+"-"+(month+1)+"-"+date);
		System.out.println(h+":"+m+":"+s);
		System.out.println(h2+"시, "+cal.get(Calendar.AM));
		System.out.println("요일:"+week); // 1: 일요일
		
		long gap = cal.getTimeInMillis()/1000;
		gap=gap/(24*60*60);
		System.out.println("경과일"+gap);
		
					//  0   1    2    3   4    5   6   7
		String[] arr = {"","일","월","화","수","목","금","토"};
		System.out.println("요일 : " + arr[week]);
		

	}

}

```
```java
public class CalendarTest2 {

	public static String showDate(Calendar cal) {
		return cal.get(Calendar.YEAR)+"년"
		+(cal.get(Calendar.MONTH)+1)+"월"
		+cal.get(Calendar.DATE);
	}
	
	public static void main(String[] args) {
		// 100일 후, 3개월 전, 7년 후
		Calendar today = Calendar.getInstance();
		
		//2022-01-01
		Calendar cal = new GregorianCalendar(2022,0,1);
		
		System.out.println("현재일자 : "+showDate(today));
		
		System.out.println("\n===100일 후===");
		today.add(Calendar.DATE, 100);
		System.out.println(showDate(today));
		
		today.add(Calendar.MONTH, -3);
		System.out.println("\n3개월전 일자 : "+showDate(today));

		today.add(Calendar.YEAR, 7);
		System.out.println("\n7년후 일자 : "+showDate(today));
		
		//2 두 날짜 사이의 간격
		// 크리스마스까지 며칠 남았는지
		Date d = new Date();
		Date d2 = new Date(2022-1900,11,25);
		
		long gap = (d2.getTime()-d.getTime())/1000;
		gap=gap/(24*60*60);
		
		System.out.println("\n"+d.toLocaleString());
		System.out.println(d2.toLocaleString());
		System.out.println("\n 두 날짜 사이의 간격 : " + gap);
	}

}
```
```java
public class Calendat {

	public static void main(String[] args) {
		// 1. Calendar를 Date로 변환
		Calendar cal = Calendar.getInstance();
		cal.set(2022,5,10);
		
		Date d = cal.getTime();
		System.out.println(d.toLocaleString());
		
		//2. Date를 Calendar로 변환
		Date date = new Date(2022,7,23);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		
		System.out.println(CalendarTest2.showDate(cal2));

	}
}
```

## 형식화 클래스
### DecimalFormat
- 형식화 클래스 중 **숫자를 형식화**하는데 사용된다
- 숫자 데이터를 정수, 부동소수점, 금액 등의 다양한 형식으로 표현
- 숫자를 형식화된 문자열로 변환
- 반대로 일정한 형식의 텍스트 데이터를 숫자로 변환도 가능   

```java
import java.text.*;
class PrintTest{
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#,###.##");
		String s2 = df.format(1234567.89512);
		System.out.println("\n" +s2); // 결과 1,234,567.9

		DecimalFormat df2 = new DecimalFormat("#,###.00");
		s2 = df2.format(1234567.89512);
		System.out.println("\n" +s2); // 결과 1,234,567.90

		DecimalFormat df3 = new DecimalFormat("#,##0");
		s2 = df3.format(123456.723456);
		System.out.println("\n" +s2); // 결과 123,457
	}
}
```

### SimpleDateFormat
- 날짜 데이터를 원하는 형태로 출력
- Date 인스턴스만 format()메서드 사용 가능
- parser()메서드는 문자열을 날짜로 변환   


```java
import java.util.*;
import java.text.*;
class DateFormatEx2{
	public static void main(String[] args) {
		// Calendar와 Date간의 변환
		Calendar cal = Calendar.getInstance();
		cal.set(2012, 4, 30); // 2012년 5월 30일 - Month는 0~11의 범위
		Date date = cal.getTime();
		SimpleDateFormat sdf1, sdf2, sdf3, sdf4;
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf2 = new SimpleDateFormat("yy-MM-dd E요일");
		sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		sdf4 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		String str = sdf1.format(date) // format(Date d)
		System.out.println(str); 
		System.out.println(sdf2.format(date));
		System.out.println(sdf3.format(date));
		System.out.println(sdf4.format(date));
	}
}
```
```java
import java.util.*;
import java.text.*;
class DateFormatTest5{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("오늘 이전 날짜를 입력하세요(2013-09-30)");
		String str = sc.nextLine();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date date = df.parse(str);
			Date today=new Date();
			long diff = (today.getTime()-date.getTime())/1000;
			diff = diff/(24*60*60);
			System.out.println("두 날짜 사이의 간격 : "+diff);
		} catch(ParseException e) {
			e.printStackTrace();
		}
	}
}
```
