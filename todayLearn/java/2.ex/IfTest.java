import java.util.*;
import java.io.*;
class  IfTestp
{
	public static void main(String[] args) throws IOException 
	{
		//1
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ �Է��ϼ���");
		int num = sc.nextInt();
		String rst = "";
		if(num%2==0){
			rst ="¦��";
		}else{
			rst ="Ȧ��";
		}
		System.out.println("�Է��� ���� : "+num+", "+rst);

		String rst2 =(num%2==0)?"¦��":"Ȧ��";
		System.out.println("���׿����� �̿� ���=>"+rst2);

		System.out.println("\n");
		
		
		//2
		String rst3 ="";
		System.out.println("0~9�� ���ĺ�, �׿� ���ڸ� �Է��ϼ���");
		char pick = (char)System.in.read();
		if(Character.isAlphabetic(pick)){
			rst3 ="���ĺ� ����";
		}else if(Character.isDigit(pick)){
			rst3 ="����";
		}else{
			rst3="��Ÿ ����";
		}
		System.out.println("�Է��� ��: "+pick+"\n"+rst3+"�Դϴ�");

	}
}
