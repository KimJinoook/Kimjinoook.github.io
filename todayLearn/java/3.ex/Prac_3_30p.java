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
		

		//1��
		System.out.println("�Ǹż���, �Ǹűݾ��� �Է��ϼ���");
		int amount = sc.nextInt();
		int price = sc.nextInt();
		float bonus = bonus(amount, price);
		System.out.println("������ : "+bonus);
		
		
		
		//2��
		System.out.println("\n���� �Է��ϼ���");
		String num = sc.nextLine();
		num = sc.nextLine();
		boolean rst = numorst(num);
		if (rst){
			System.out.println(num + "�� �����Դϴ�.");
		}else{
				System.out.println(num+ "�� ���ڰ� �ƴմϴ�.");
		}
	}
}