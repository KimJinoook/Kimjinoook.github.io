import java.util.Scanner;

public class Prac_118_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("�Է��� ������ ������ �Է��ϼ���");
		int amount = sc.nextInt();
		int sum = 0;
		for (int i=0;i<amount;i++) {
			System.out.println("������ �Է��ϼ���");
			sum += sc.nextInt();
		}
		System.out.println("�Էµ� ������ ��ü ��� : "+Math.round((double)sum*100/amount)/100f);
		
	}

}
