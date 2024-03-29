---
layout: post
title:  "20. 스트림"
subtitle:   ""
categories: lang
tags: java1
comments: false
header-img: 
---

## 1. 스트림API
- 배열이나 컬렉션에 접근하기 위해서는
  - 매번 새로운 코드 작성, 반복문 사용
  - 코드가 길어지고 가독성 하락
- 위 문제점 해결을 위해 스트림 API 도입

### 특징
- 내부반복을 통해 작업
  - 컬렉션은 외부반복
- 단 한번만 사용 가능
  - 컬렉션은 재사용 가능
- 원본데이터를 변경하지 않는다
- 필터-맵 기반의 지연연산
- parrallelStream() 메서드를 통한 병렬처리

### 동작흐름
- 스트림 생성
- 스트림의 변환
  - 중개연산(필터)
  - 중개연산(맵)
- 스트림의 사용
  - 최종연산   

```java
//문자열의 길이가 5 이상인 요소 출력
sList.straem().filter(s->s.length()>=5).forEach(s->System.out.println(s));
//스트림생성   /         중간연산        /       최종연산
```

## 2. 스트림의 생성
### 컬렉션
- 컬렉션의 최고 상위인 Collection인터페이스에는 stream()메서드가 정의되어있다
- Stream stream = list.stream();   

```java
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamTest1 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		// 컬렉션에서 스트림 생성
		Stream<Integer> stream = list.stream();
		// forEach() 메소드를 이용한 스트림 요소의 순차 접근
		stream.forEach(System.out::println);

	}
}
```
- stream클래스의 forEach()메서드
  - 해당 스트림의 요소를 하나씩 소모하며 순차적으로 접근
  - 같은 스트림으로는 forEach()메서드를 한번밖에 호출할 수 없다.

### 배열
- Arrays클래스에는 다양한 형태의 stream()메서드 정의
- insStream, longStream 등   

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamTest1 {

	public static void main(String[] args) {

		String[] arr = new String[]{"자바", "오라클", "스프링", "파이썬"};
		// 배열에서 스트림 생성
		//void forEach(Consumer<? super T> action)
		Stream<String> stream1 = Arrays.stream(arr);
		stream1.forEach(e -> System.out.print(e + " "));
		System.out.println();
		// 배열의 특정 부분만을 이용한 스트림 생성
		Stream<String> stream2 = Arrays.stream(arr, 1, 3);
		stream2.forEach(e -> System.out.print(e + " "));
    // 오라클,스프링 출력 (1<=
   
	}

}
```

### 가변매개변수
- Stream클래스의 of()메서드를 사용
  - 가변매개변수를 전달받아 스트림 생성   

```java
//static <T> Stream<T> of(T... values)
Stream<Double> stream = Stream.of(4.2, 2.5, 3.1, 1.9);
stream.forEach(System.out::println);

```
- 가변인수
  - 인수의 개수가 정해져있지 않을 때   

```java
public class test
  public void calc(int... nums){
  }
  public static void main(String[] args){
    calc(1,2,3,4);
    calc(1,2);
    calc(1,2,3);
  }
}
```
### 지정된 범위의 연속된 정수
- IntStream이나 LongStream 인터페이스
	- range()와 rangeClosed()메서드 정의되어있다
	- range(a,b) : a<=x<b;
	- rangeClosed(a,b) : a<=x<=b;   

```java
// 지정된 범위의 연속된 정수에서 스트림 생성
IntStream stream = IntStream.range(1, 4);
stream.forEach(e -> System.out.print(e + " ")); //123
System.out.println();
IntStream stream2 = IntStream.rangeClosed(1, 4);
stream2.forEach(e -> System.out.print(e + " ")); //1234

```

### 빈 스트림
- 아무 요소도 가지지 않는 빈 스트림은 empty()메서드를 이용해 생성   

```java
Stream<Object> stream = Stream.empty();
System.out.println(stream.count()); // 스트림의 요소의 총 개수를 출력함.
//0
```

## 3. 스트림의 중개연산
- 초기스트림은 중개연산을 통해 또 다른 스트림으로 변환
- 연속 사용 가능
- 스트림 필터링 : filter(), distinct()
- 스트림 변환 : map(), flatMap()
- 스트림 제한 : limit(), skip()
- 스트림 정렬 : sorted()
- 스트림 연산 결과 확인 : peek() 

### 스트림 필터링
- filter
	- 주어진 조건(predicate)에 맞는 요소만으로 구성된 새로운 스트림 반환
- distinct
	- 해당 스트림에서 중복된 요소가 제거된 새로운 스트림 반환
	- 내부적으로 Object클래스의 equals메소드 사용하여 중복 비교   

```java
IntStream stream1 = IntStream.of(7,5,5,2,1,2,3,5,4,6);
IntStream stream2 = IntStream.of(7,5,5,2,1,2,3,5,4,6);
// 스트림에서 중복된 요소를 제거함.
stream1.distinct().forEach(e->System.out.print(e+" "));
System.out.println();
// 스트림에서 홀수만을 골라냄.
stream2.filter(n->n%2!=0).forEach(e->System.out.print(e+" "));

/*
실행 결과
7 5 2 1 3 4 6
7 5 5 1 3 5
*/
```

### 스트림 변환
- map
	- 스트림의 요소들을 함수에 인수로 전달, 반환값으로 이루어진 새로운 스트림 반환
	- 요소가 배열이라면, flatMap() 메서드 사용
		- 각 요소의 반환값을 하나로 합친 새로운 스트림 반환   

```java
Stream<String> stream = Stream.of("HTML","CSS","JAVA","JAVASCRIPT");
stream1.map(s -> s.length()).forEach(System.out::println);
/* 결과
4 3 4 10
*/

String[] arr = {"I study hard", "You study JAVA", "I am hungry"};
Stream<String> stream = Arrays.stream(arr);
stream.flatMap(s -> Stream.of(s.split(" "))).forEach(System.out::println);
/*결과
I
study
hard
You
study
JAVA
I
am
hungry
*/

```

## 4. 스트림의 최종 연산
- 최종연산을 통해 각 요소를 소모하여 결과 표시
- 요소를 소모한 스트림은 더이상 아용 불가
- 요소의 출력 : forEach()
- 요소의 소모 : reduce()
- 요소의 검색 : findFirst(), findAny()
- 요소의 검사 : anyMatch(), allMatch(), noneMatch()
- 요소의 통계 : count(), min(), max()
- 요소의 연산 : sum(), average()
- 요소의 수집 : collect()

### 요소의 출력
- forEach메서드
	- 각 요소를 소모하여 명시된 동작 수행   

```java
Stream<String> stream = Stream.of("넷","둘","셋","하나");
stream.forEach(System.out::println);
/* 결과
넷
둘
셋
하나
*/
```

### 요소의 소모
- reduce()메서드
	- 첫번째와 두번째 요소를 가지고 연산 수행
	- 결과와 세번째요소를 가지고 다시 연산수행
	- 순차적으로 모든 요소를 소모, 결과 반환
	- 인수로 초기값 전달 시, 첫번째 요소와 인수로 연산시작   

```java
Stream<String> stream1 = Stream.of("넷","둘","셋","하나");
Stream<String> stream2 = Stream.of("넷","둘","셋","하나");

Optional<String> result1 = stream1.reduce((s1,s2) -> s1 + "++" + s2);
result1.ifPresent(System.out::println);

String result2 = stream2.reduce("시작",(s1,s2) -> s1 + "++" + s2);
System.out.println(result2);

/*실행 결과
넷++둘++셋++하나
시작++넷++둘++셋++하나
*/
```

### 요소의 검색
- findFirst(), findAny()
	- 해당 스트림에서 첫번째 요소를 참조하는 Optional 객체 반환
	- 병렬스트림인 경우 findAny를 이용해야 정확한 결과 출력   

```java
IntStream stream1 = IntStream.of(4,2,7,3,5,1,6);
IntStream stream2 = IntStream.of(4,2,7,3,5,1,6);

OptionalInt result1 = stream1.sorted().findFirst();
System.out.println(result1.getAsInt());

OptionalInt result2 = stream2.sorted().findAny();
System.out.println(result2.getAsInt());
/*실행 결과
1
1*/
```

