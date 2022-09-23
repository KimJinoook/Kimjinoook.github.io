---
layout: post
title:  "15. 컬렉션"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 컬렉션 프레임 워크
- 프레임워크(Framework)
  - 잘 정의된, 약속된 구조나 골격
  - 잘 정의된 클래스들의 모임
  - **모든 컬렉션 클래스를 표준화된 방식으로 다룰 수 있도록** 체계화됨
- 컬렉션(Collection)
  - 데이터의 저장, 관련있는 알고리즘을 구조화 해놓은 프레임워크
  - 데이터의 저장을 위해 정의된 클래스
  - 변수들의 조직적인 집합
- 컬렉션 프레임워크
  - **데이터 그룹을 저장하는 클래스들을 표준화한 설계**   

### 컬렉션
- 컬렉션 클래스
  - 데이터를 보관, 삭제, 검색, 삽입등의 기능
  - 메모리의 사이즈를 **동적으로 확장**
  - 데이터 삽입 시 메모리 확장
 
- 배열과의 차이
  - 배열
     - 같은 타입의 변수를 여러개 저장
     - 사용이 간단, 첨자연산이 빨라 효율이 좋다
     - 최초 생성시 지정한 크기 변경 불가
   - 컬렉션
    - 크기가 가변적, 미리 크기를 결정하지 않아도 된다
- 컬렉션 프레임워크의 핵심 인터페이스
  - List : 순서가 있고, 데이터의 중복 허용
    - ArrayList, Vector 등
  - Set : 순서가 없고, 데이터 중복 불가
    - HashSet 등
  - Map : 키와 값의 쌍으로 이루어짐, 순서가 없고, 키는 중복 불가, 값은 중복 허용
    - HashMap, Hashtable, Properties 등

## 2. List
### a. ArrayList
- Object 배열을 이용 데이터를 순차적으로 저장
- 장점 : 데이터의 참조가 용이, 빠른 참조 가능
- 단점 : 
  - 저장소의 용량을 늘리는 시간이 소요
  - 데이터의 삭제에 필요한 연산과정이 길다
- 데이터의 수가 예측가능하며, 참조가 빈번할 때 이용   

```java
import java.util.ArrayList;

public class ArrayListTest0 {

	public static void main(String[] args) {

		ArrayList list = new ArrayList(3); // 크기 3으로 생성
		System.out.println(list.size()); // 최초 크기 0
		//데이터 저장
		list.add(new Integer(10)); // Jdk 5.0 dlwjs
									// 컬렉션에 값을 저장할 때 객체로 저장해야 하므로
									// Wrapper 클래스 사용
		list.add(new Integer(20));
		list.add(40); // jdk 5.0 - autoboxing : 기본 자료형이 컴파일러에 의해 자동으로
						// Wrapper 클래스로 변환되어 저장
		System.out.println("ArrayList 크기 : " + list.size()+"\n"); //크기 3
		
		//데이터 참조
		for(int i=0; i<list.size();i++) {
			Object obj = list.get(i);
			Integer n = (Integer)list.get(i);
			int num = n.intValue();
			System.out.println(n.intValue());
			System.out.println(num);
		}
		
		list.add(new Double(3.14));
		list.add("java");
		
		Double d = (Double)list.get(3);
		System.out.println("\n" + d);
		
		String str = (String)list.get(4);
		System.out.println(str);
		
		System.out.println(list.size());
		
		for(int i = 0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
		int num2 = (int)list.get(1);
		System.out.println(num2);
		
		if(list.get(4)=="java") System.out.println("a");	
	}
}
```

### b. 제네릭스(Generics)
- jdk 1.5에 추가된 기능
- 컬렉션에 저장하는 객체의 타입을 컴파일 시 체크
- 안정성 증가
- 꺼낼 때는 자동으로 형변환
- 컬렉션에 저장할 객체의 타입을 지정
- 지정한 타입의 객체만 해당 컬렉션에 저장할 수 있다
> 컬렉션 클래스<저장할 객체의 타입> 변수명 = new 컬렉션 클래스<저장할 객체의 타입>();   

- 일반 컬렉션
  - 요소타입 Object, 임의의 요소 저장
  - 어떤 객체든지 컬렉션에 넣을 수 있다.
  - 컬렉션에 저장된 정보를 읽을 때, Object 타입으로 리턴, 원하는 타입으로 형변환해야한다.
  - 저장할때는 Object 타입으로 박싱, 꺼낼떄는 언박싱
    - boxing : 스택에 저장된 기본자료형 데이터를 힙영역의 참조형으로 변환 (Wrapper 클래스)
    - unboxing : 참조형의 데이터를 기본형으로 변환
- 제네릭스
  - 형매개변수(타입인수)로 처리대상 지정, 위의 문제 해결   

```java
import java.util.ArrayList;

public class ArrayListTest1 {

	public static void main(String[] args) {
		
		//Generics
		ArrayList<Integer> list = new ArrayList<Integer>(3);
		list.add(10);
		list.add(30);
		list.add(77);
		
		System.out.println("size:"+list.size()); //3
		
		//데이터 읽어오기
		for(int i=0; i<list.size();i++) {
			int n = list.get(i); //unboxing : Integer=>int
			System.out.println(n);
		}
		
		//데이터 삭제
		list.remove(0);
		System.out.println("\n삭제 후");
		for(int n : list) {
			System.out.println(n);
		}
		
		//for문 안에서 초기화
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		for(int i=0;i<4;i++) { //현재 size 0이라 list.size 이용 불가 
			list2.add(i*2);
		}
		for(int i=0 ; i<list2.size();i++) {
			System.out.println(list2.get(i));
		}
	}
}
```

### c. 오토박싱(autoboxing)
- jdk 5.0 이전
	- 컬렉션에 값을 저장할 때 객체로 저장해햐 한다
	- 기본형을 Integer, Long 과 같은 Wrapper클래스로 변환해준 후 저장
- jdk 5.0 이후
	- 오토박싱 : 기본 자료형 값이 컴파일러에 의해 자동으로 Wrapper 클래스로 변환
	- 언박싱 : 꺼낼 때에도 변환과정 없이 바로 기본형 값 획득   



```java
import java.util.ArrayList;

public class Prac_13_18_1 {

	public static void main(String[] args) {
		// 1번
		ArrayList<Double> list = new ArrayList<Double>(3);
		list.add(new Double(3.14)); // 박싱
		list.add(5.87); // 오토박싱
		list.add(2.476);

		for(int i = 0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
		
		for(double d : list) System.out.println(d);
	}
}
```

## 3. Set
### a. HashSet

- Set 인터페이스를 구현하는 컬렉션 클래스
- 데이터의 저장순서 유지 x
- 데이터 중복 불가   

```java
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
		/*
		 	HashSet
		 	- 순서 유지x
		 	- 중복 불가
		 */
		HashSet<String> hset = new HashSet<String>();
		hset.add("first");
		hset.add("second");
		hset.add("third");
		hset.add("first");
		
		System.out.println("데이터 수 : "+hset.size());
		
		Iterator<String> iter = hset.iterator();
		while(iter.hasNext()) {
			String s = iter.next();
			System.out.println(s);
		}
	}
}
```

### b. Enumeration, Iterator
- Enumeration, Iterator
	- 컬렉션에 저장된 요소를 접근하는데 사용되는 인터페이스
	- 저장된 데이터를 전부 참조할 때 유용
- Iterator
	- 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator 인터페이스를 정의
	- 컬렉션 인터페이스에는 iterator를 반환하는 메서드 정의
	- 컬렉션 클래스에 대해 iteraotr()를 호출하여 iterator를 얻은 후 반복문 사용, 요소 획득

### c. TreeSet
- Set인터페이스를 구현하는 TreeSet클래스
	- Tree라는 자료구조 기반 구현
	- 데이터를 정렬된 상태로 저장
	- 저장순서와는 관계 없음   

```java
import java.util.*;

public class TreeTest {

	public static void main(String[] args) {
		TreeSet<Integer> tset = new TreeSet<Integer>();
		tset.add(10);
		tset.add(4);
		tset.add(2);
		tset.add(7);
		tset.add(1); //저장될 때마다 데이터가 정렬됨
		
		System.out.println("저장된 데이터 수 : "+tset.size());
		
		Iterator<Integer> iter = tset.iterator();
		while(iter.hasNext()) {
			int n=iter.next();
			System.out.println(n);
		}
		
		Set<Integer> set = new TreeSet<Integer>();
		while(set.size()<6) {
			int n = (int)(Math.random()*45+1);
			set.add(n);
		}
		
		System.out.println("\n로또");
		System.out.println(set);
		
		TreeSet<String> tset2 = new TreeSet<String>();
		tset2.add("홍길동");
		tset2.add("박길동");
		tset2.add("김길동");
		tset2.add("홍길동");
		tset2.add("oracle");
		tset2.add("mz");
		tset2.add("abcd");
		tset2.add("Java");
		tset2.add("ABC");
		tset2.add("XYZ");
		tset2.add("987");
		tset2.add("123");
		
		Iterator<String> iter2 = tset2.iterator();
		while(iter2.hasNext()) {
			String s = iter2.next();
			System.out.println(s); //숫자, 대문자, 소문자, 가나다 순으로 
		}
	}
}
```

- TreeSet에 사용자 정의 클래스 넣을시 에러 확인   

```java
import java.util.Iterator;
import java.util.TreeSet;

class Person{
	private String name;
	private int age;
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "Person[name="+name+", age="+age+"]";
	}
}

public class ComparableTest {

	public static void main(String[] args) {
		TreeSet<Person> tset = new TreeSet<Person>();
		tset.add(new Person("홍길동",20)); //실행에러
//ClassCastException: class com.day23.Person cannot be cast to class java.lang.Comparable
		tset.add(new Person("박길동",17));
		tset.add(new Person("김길동",31));
		
		Iterator<Person> iter = tset.iterator();
		while(iter.hasNext()) {
			Person p =iter.next();
			System.out.println(p);
		}
	}
}
```
- TreeSet의 정렬 원리
	- Comparable 인터페이스를 구현
	- CompareTo 메서드를 오버라이딩

### d. Comparable
- Treeset은 인스턴스가 저장될 때마다 기존 저장된 인스턴스와 비교
- 이를 위해 compareTo메서드를 호출
- 리턴되는 값을 기반으로 정렬
	- 현재 객체와 매개변수 객체를 비교하여 0,1,-1 리턴
	- 이를 기준으로 정렬
	- Integer나 String은 이미 오버라이딩 되어있다.
	- 사용자정의 클래스는 개발자가 직접 정의해야 한다.   

```java
import java.util.Iterator;
import java.util.TreeSet;

class Person implements Comparable<Person>{
	private String name;
	private int age;
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		return "Person[name="+name+", age="+age+"]";
	}
	@Override
	public int compareTo(Person p) {
		//정렬기준 나이
		if(this.age>p.age) {
			return 1;
		}else if(this.age<p.age) {
			return -1;
		}else {
			return 0;
		}
	}	
}

public class ComparableTest {

	public static void main(String[] args) {
		TreeSet<Person> tset = new TreeSet<Person>();
		tset.add(new Person("홍길동",20)); //실행에러
		tset.add(new Person("박길동",17));
		tset.add(new Person("김길동",31));

		
		Iterator<Person> iter = tset.iterator();
		while(iter.hasNext()) {
			Person p =iter.next();
			System.out.println(p);
		}
	}
}
```


## 4. Map
### a. HashMap
- 키와 값을 한 쌍으로 데이터 저장
- 키 중복불가
- 값 중복 허용   

```java
import java.util.HashMap;
import java.util.Iterator;

public class MapTest {

	public static void main(String[] args) {

		HashMap<Integer, String> map = new HashMap<Integer,String>();
		map.put(7, "홍길동");
		map.put(9, "김길동");
		map.put(15, "이길동");
		
		String s = map.get(7);
		System.out.println("7번 키 : "+s);
		
		map.remove(9); // 9번 키의 데이터 삭제
		
		
		//Set<K> keySet()
		//Set<Integer> set = map.keySet();
		//Iterator<Integer> iter = set.iterator();
		Iterator<Integer> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			int key = iter.next();
			String val = map.get(key);
			System.out.println("key = "+key+", value="+val);
		}
		
		System.out.println("데이터 개수 : "+map.size());
		
		for(int i = 0; i<map.size();i++) {
			System.out.println(map.get(map.keySet().iterator().next()));
		}
	}
}
```

### b. Properties
- Hashtable
	- 키와 값을 (object,object)의 형태로 저장
- Properties
	- HashMap의 구버전인 Hashtable을 상속받아 구현한 것
	- (String, String)의 형태로 저장하는 보다 단순화된 컬렉션 클래스
	- 주로 어플리케이션의 환경설정과 관련된 속성(property)를 저장
	- 데이터를 파일로부터 읽고 쓰는 편리한 기능
	- 간단한 입출력 시 사용   

```java
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		/*
		 	Properties
		 		- Hashtable을 상속받아 구현한 것
		 		- (String, String)의 형태로 저장
		 		- 주로 어플리케이션의 환경설정과 관련된 속성을 저장하는 데 사용
		 		- 데이터를 파일로부터 읽고 쓰는 기능 제공
		 */
		
		Properties prop = new Properties();
		prop.setProperty("timeout", "30");
		prop.setProperty("Language", "kr");
		prop.setProperty("size", "10");
		prop.setProperty("capacity", "20");
		
		String size = prop.getProperty("size");
		System.out.println("size = " + size); //10
		System.out.println("capacity = " + prop.getProperty("capacity","40")); //20, 값이 없을 경우 default값(40)을 찍어라
		System.out.println("loadfactor = " + prop.getProperty("loadfactor","0.75")); //0.75, 값이 없을 경우 default값(0.75)을 찍어라
		System.out.println("capacity = " + prop.getProperty("capacity")); //20
		
		prop.setProperty("capacity", "40");
		System.out.println("capacity = " + prop.getProperty("capacity")); //40

	}
}
```

- 파일 이용, properties 일괄 등록   

```java
public class PropertiesTest2 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		
		InputStream is;
		try {
			is = new FileInputStream("text/input.txt");
			prop.load(is);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		String name = prop.getProperty("name");
		String data = prop.getProperty("data");
		System.out.println("name = "+name);
		System.out.println("data = "+data);
	}
}
```

