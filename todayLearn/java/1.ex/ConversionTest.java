public class ConversionTest{
    public static void main(String[] args){
        //float형 변수 pi의 값을 소수점 셋째 자리까지만 빼내서 출력하기
        float pi = 3.141592f;
        int pi2 = (int)(1000*pi);
        float pi3 = pi2/1000f;
        System.out.println(pi3);

        //소문자를 대문자로 변환하기
        char a = 'a';
        char b = (char)(a-32);
        System.out.println(b);

    }
}