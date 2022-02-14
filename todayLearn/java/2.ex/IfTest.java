import java.util.*;
import java.io.*;
class  IfTestp
{
	public static void main(String[] args) throws IOException 
	{
		//1
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요");
		int num = sc.nextInt();
		String rst = "";
		if(num%2==0){
			rst ="짝수";
		}else{
			rst ="홀수";
		}
		System.out.println("입력한 정수 : "+num+", "+rst);

		String rst2 =(num%2==0)?"짝수":"홀수";
		System.out.println("삼항연산자 이용 결과=>"+rst2);

		System.out.println("\n");
		
		
		//2
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
