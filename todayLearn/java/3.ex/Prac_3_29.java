
import java.util.Scanner;

public class Prac_3_29 {
	public static int convToint(String a) {
		int num = Integer.parseInt(a);
		return num;
	}
	public static void ab(int a){
		for (int i=1;i<=a ;i++ ){
			for (int j=1;j<i ;j++ ){
				System.out.print("A");
			}
			System.out.print("B\n");
		}
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//1��
		System.out.print("0~9������ ���ڸ� �Է��ϼ��� : ");
		String num = sc.nextLine();
		int num2 = convToint(num);
		System.out.println(num+"=>"+num2);
		System.out.println(num+"* 100 = " + num2*100);
		
		
		//2��
		System.out.print("\n�ݺ� Ƚ���� �Է��ϼ��� : ");
		ab(sc.nextInt());
		
		

	}

}