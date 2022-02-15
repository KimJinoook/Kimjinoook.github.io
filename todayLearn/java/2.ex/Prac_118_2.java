import java.util.Scanner;

public class Prac_118_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("입력할 정수의 개수를 입력하세요");
		int amount = sc.nextInt();
		int sum = 0;
		for (int i=0;i<amount;i++) {
			System.out.println("정수를 입력하세요");
			sum += sc.nextInt();
		}
		System.out.println("입력된 정수의 전체 평균 : "+Math.round((double)sum*100/amount)/100f);
		
	}

}
