# 기타 메서드 및 참고사항
## 1. Escape Sequence
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

## 2. Scanner클래스
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
