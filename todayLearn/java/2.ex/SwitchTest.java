import java.io.*;
import java.util.*;
class SwitchTest  
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("������ �Է��ϼ��� : A. ȸ��� B. �л� C. �ֺ� D. ��Ÿ");
		char pick = (char)System.in.read();

		System.out.println(pick);
		switch(Character.toUpperCase(pick)){
			case 'A': 
				System.out.println("ȸ����̽ñ���");
				break;
			case 'B':
				System.out.println("�л��̽ñ���");
				break;
			case 'C':
				System.out.println("�ֺ��̽ñ���");
				break;
			case 'D':
				System.out.println("��Ÿ�� �����߾ƿ�");
				break;
			default:
				System.out.println("�߸��Է�");
				break;
		}

	}
}
