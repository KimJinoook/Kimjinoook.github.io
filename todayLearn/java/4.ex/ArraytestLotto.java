import java.util.*;
class ArraytestLotto{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[8];
		int num3 = 0;
		
		while(true){
		System.out.println("복권판매점 번호<1~8>와 복권 판매량을 입력하세요.<끝낼때는 q>");
		String num = sc.nextLine();
		if(num.equals("q"))break;
		String cnt = sc.nextLine();
		if(cnt.equals("q"))break;
		int num2 = Integer.parseInt(num);
		int cnt2 = Integer.parseInt(cnt);
		arr[num2-1] +=cnt2;		
		System.out.println("그룹번호 : "+num2 + ", 판매량 : "+arr[num2-1]);
		}
		System.out.println("종료");
	}
}
