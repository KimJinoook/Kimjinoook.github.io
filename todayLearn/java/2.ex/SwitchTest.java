import java.io.*;
import java.util.*;
class SwitchTest  
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("직업을 입력하세요 : A. 회사원 B. 학생 C. 주부 D. 기타");
		char pick = (char)System.in.read();

		System.out.println(pick);
		switch(Character.toUpperCase(pick)){
			case 'A': 
				System.out.println("회사원이시군요");
				break;
			case 'B':
				System.out.println("학생이시군요");
				break;
			case 'C':
				System.out.println("주부이시군요");
				break;
			case 'D':
				System.out.println("기타를 선택했아요");
				break;
			default:
				System.out.println("잘못입력");
				break;
		}

	}
}
