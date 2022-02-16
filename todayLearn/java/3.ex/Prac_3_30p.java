import java.util.*;
public class Prac_3_30p {
	public static float bonus(int a, int b) {
		float rst = (a>=200)?b*0.3f:(a>=100)?b*0.2f:b*0.1f;
		return rst;
	}
	public static boolean numorst(String num){
		boolean rst = true;
		char ch='a';
		for(int i=0; i<num.length();i++) {
			ch=num.charAt(i);
			if (Character.isDigit(ch)==false) {
				rst = false;
				break;
			}else {
				if(i==(num.length()-1)) {
				rst = true;;
				}
			}
		}
		return rst;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		

		//1번
		System.out.println("판매수량, 판매금액을 입력하세요");
		int amount = sc.nextInt();
		int price = sc.nextInt();
		float bonus = bonus(amount, price);
		System.out.println("성과급 : "+bonus);
		
		
		
		//2번
		System.out.println("\n값을 입력하세요");
		String num = sc.nextLine();
		num = sc.nextLine();
		boolean rst = numorst(num);
		if (rst){
			System.out.println(num + "는 숫자입니다.");
		}else{
				System.out.println(num+ "는 숫자가 아닙니다.");
		}
	}
}