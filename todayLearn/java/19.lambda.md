# 람다식
## 1. 람다식이란
- 메서드를 하나의 식으로 표현한 것
- 메서드
  - int min(int x, int y){
  - return x<y?x:y;
  - }
- 람다식
  - (x,y)->x<y?x:y
  - (매개변수 목록) -> {함수몸체}
- 클래스 작성 및 객체생성 없이 메서드 이용 가능
- 람다식은 일종의 익명클래스와 같다고 할 수 있다
- 람다식은 매개변수로 전달될 수 있으며, 결과로 반환될 수도 있다
- 유의사항
  - 매개변수의 타입을 추론할 수 있는 경우 타입 생략 가능
  - 매개변수가 하나인경우 괄호 생략 가능
    - 괄호 생략 시 타입도 생략
  - 함수의 몸체가 하나의 명령문일 경우 중괄호와 세미콜론 생략 가능
  - 함수의 몸체가 하나의 return문으로만 이루어진 경우 중괄호 생략 불가능
  - return문 대신 표현문 사용 가능
    - 반환값은 표현식의 결과값, 세미콜론 생략   

```java
int max(int a, int b) {
  return a > b ? a : b;
}
(a, b) -> a>b?a:b


int printVar(String name, int i) {
  System.out.println(name+"="+i);
}
(name, i) -> System.out.println(name+"="+i)


int square(int x) {
  return x * x;
}
x -> x*x


int roll() {
  return (int)(Math.random()*6);
}
()-> (int)(Math.random()*6)
```

## 2. 함수형 인터페이스
- 람다식을 저장하기 위한 참조변수의 타입을 결정해야 한다
> 참조변수타입 참조변수이름 = 람다식   

- 해당 참조변수의 타입이 함수형 인터페이스
- 단 하나의 추상메서드만 가져야 한다
- 어노테이션을 사용하여 명시 (@FunctionalInterface)
  - 컴파일러가 해당 인터페이스를 함수형 인터페이스로 인식
  - 함수영 인터페이스에 두개이상의 메서드가 선언되면 컴파일러가 오류 발생   

```java
@FunctionalInterface
interface Calc { //함수형 인터페이스의 선언
	public int min(int x, int y);
}

public class test {
	public static void main(String[] args) {
			Calc m = new Calc() {
				public int min(int x, int y) {
					int result = x < y ? x : y;
					return result;
				}
			};
			System.out.println(m.min(5, 9));
			
			Calc minNum=(x, y) -> x < y ? x : y; //추상 메소드의구현
			System.out.println(minNum.min(3, 4)); //함수형 인터페이스의 사용
	}
}
```
- 자바는 java.util.function에 함수형 인터페이스를 미리 정의하여 제공
  - Supplier
    - T get() : 매개변수 없고, 반환값 있음
  - Consumer
    - void accept(T t) : 매개변수 있고, 반환값 없음
  - Function
    - R apply(T t) : 일반적인 함수, 매개변수를 받아 결과 반환
  - Predicate
    - boolean test(T t) : 조건식 표현, 매개변수 하나, 반환값 불린   

```java
Supplier f = ()-> (int)(Math.random()*100+1;
Consumer f = i->System.out.print(i+", ");
Function f = i -> i/10*10;
Predicate f = i -> i%2==0;
```
- 매개변수가 2개인 함수형 인터페이스
  - BiConsumer
    - void accept(T t, U u) : 두개의 매개변수, 반환값 없음
  - BiPredicate
    - boolean test(T t, U u) : 조건식 표현, 매개변수 둘, 반환값 불린
  - BiFunction
    - R apply(T t, U u) : 두개의 매개변수를 받아 하나의 결과 반환
- 매개변수의 타입과 반환타입이 일치하는 함수형 인터페이스
  - UnaryOperator
    - T apply(T t) : Function의 자손
  - BinaryOperator
    - T apply(T t, T t) : BiFunction의 자손   

```java
public class LambdaTest {
	/*
		1.
		(a, b) -> a>b?a:b
		2.
		(name, i) -> System.out.println(name+"="+i)
		3.
		x -> x*x
		4.
		()->(int)(Math.random()*6)
	 */
	/*
	 1. Supplier
	 2. Consumer
	 3. Predicate
	 4. Function
	 */
	public static void main(String[] args) {
		List <Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		list.forEach(i->System.out.print(i+",")); // list의 모든 요소를 출력

		System.out.println();
		list.removeIf(x-> x%2==0 || x%3==0); // 2 또는 3의 배수를 제거
		list.forEach(i->System.out.print(i+",")); // 1,5

		System.out.println();
		list.replaceAll(i->i*10); // 모든 요소에 10을 곱한다.
		list.forEach(i->System.out.print(i+",")); // 10,50
		
		System.out.println();
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "java");
		map.put(2, "Spring");
		map.put(3, "oracle");
		// map의 모든 요소를 {k,v}의 형식으로 출력
		map.forEach((k,v)-> System.out.print("{"+k+","+v+"},")); 

	}
}
```

## 3. 메서드 참조
- 람다식이 단 하나의 메서드만 호출할 경우, 불필요한 매개변수 제거 가능
> 클래스이름::메서드이름   
> 참조변수이름::메서드이름   
> (base, exponent)->Math.pow(base, exponent);   
> Math::pow;   

- 특정 인스턴스의 메서드를 참조할 때도 참조변수의 이름을 통해 메서드 참조 가능
> MyClass obj = new MyClass();   
> Function func = (a)->obj.equals(a);   
> Function func = obj::equals;   

```java
DoubleUnaryOperator oper;
oper = (n) -> Math.abs(n); // 람다 표현식
System.out.println(oper.applyAsDouble(-5));

oper = Math::abs; // 메소드 참조
System.out.println(oper.applyAsDouble(-5));
```
