import java.util.*;
class ArraytestCharconfirm {
	public static void main(String[] args) {
		System.out.println("������ ���ڿ��� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		for(int i=0;i<str.length();i++){
			System.out.println("str.charAt<"+i+">:"+str.charAt(i)+"=>"+(int)str.charAt(i));
		}
	}
}
