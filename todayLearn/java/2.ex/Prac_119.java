import java.util.*;
class Prac_119 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("�� ���� ������ �Է��ϼ���<������ ���� ������ �Է�>");
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			if(num1==0&&num2==0) break;
			if(num2==0){
				System.out.println("������ 0�̹Ƿ� ������ �����մϴ�.");
				continue;
			}
				System.out.println("�� : "+num1/num2+", ������ : "+(num1%num2));
			
		}
	}
}
