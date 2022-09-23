# Optional 클래스
- Integer나 Double 클래스처럼 객체를 포장해주는 Wrapper 클래스
- 모든 타입의 참조변수를 저장할 수 있다
- Optional 객체 사용시 NullPointerException 메서드로 간단한 회피 가능
  - 복잡한 조건문 없이 null값으로 발생하는 예외 처리 가능

## 1. optional 객체의 생성
- of()메서드나 ofNullable()메서드를 이용해 객체 생성 가능
- of()메서드
  - null이 아닌, 명시된 값을 가지는 Optional 객체 반환
  - 해당 객체에 null이 저장되면 NullPointerException 예외 발생
  - null 가능성 있을 시 ofNullable()메서드 사용
- ofNullable() 메서드
  - null일 시 비어있는 객체 반환   

```java
Optional<String> opt = Optional.ofNullable("자바 Optional 객체");
System.out.println(opt.get());

/*실행 결과
자바 Optional 객체*/
```

## 2. optional 객체의 접근
- get()메서드를 통해 객체에 저장된 값에 접근
- 저장된 값이 null일 시 NoSuchElementException 예외 발생
- get메서드 호출 전 isPresent()메서드를 사용해 null인지 판별   

```java
Optional<String> opt = Optional.ofNullable("자바 Optional 객체");
  if(opt.isPresent()) {
  System.out.println(opt.get());
}
/*실행 결과
자바 Optional 객체*/

```
- orElse()
  - 저장된 값이 존재혀만 그 값을 반환
  - 값이 존재하지 않으면 인수로 전달된 값을 반환
- orElseGet()
  - 저장된 값이 존재혀만 그 값을 반환
  - 값이 존재하지 않으면 인수로 전달된 람다식의 결과값을 반환
- orElseThrow()
  - 저장된 값이 존재혀만 그 값을 반환
  - 값이 존재하지 않으면 인수로 전달된 예외 발생   

```java
Optional<String> opt = Optional.empty(); //Optional를 null로 초기화함.
System.out.println(opt.orElse("빈 Optional 객체"));
System.out.println(opt.orElseGet(()->new String("java")));

/*
실행 결과
빈 Optional 객체
java
*/
```

## 3. 기본 타입의 Optional 클래스
- 기본타입 스트림을 위한 별도의 Optional 클래스 제공
- OptionalInt 클래스
  - 접근 시 getAsInt()
- OptionalLong 클래스
  - 접근 시 getAsLond()
- OptionalDouble 클래스
  - 접근 시 getAsDouble()
- 반환타입은 Optional이 아닌 해당 기본타입   

```java
IntStream stream = IntStream.of(4,2,1,3);
OptionalInt result = stream.findFirst();
System.out.println(result.getAsInt());

/* 결과
4*/
```

