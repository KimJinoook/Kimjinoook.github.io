import java.util.*;
class ArraytestLotto{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[8];
		int num3 = 0;
		
		while(true){
		System.out.println("�����Ǹ��� ��ȣ<1~8>�� ���� �Ǹŷ��� �Է��ϼ���.<�������� q>");
		String num = sc.nextLine();
		if(num.equals("q"))break;
		String cnt = sc.nextLine();
		if(cnt.equals("q"))break;
		int num2 = Integer.parseInt(num);
		int cnt2 = Integer.parseInt(cnt);
		arr[num2-1] +=cnt2;		
		System.out.println("�׷��ȣ : "+num2 + ", �Ǹŷ� : "+arr[num2-1]);
		}
		System.out.println("����");
	}
}
