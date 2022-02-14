class LogicalOp 
{
	public static void main(String[] args) 
	{
		/*
		논리연산자
		&& : and / 피연산자 두개 동시에 만족할때 true
		|| : or / 피연산자 둘중 하나라도 만족하면 true
		^  : 피연산자 둘이 같으면 false, 다르면 true (비트논리연산자)

		비교연산자, 논리연산자의 결과는 true, fales / 조건식에 사용
		*/
		int x = 23, y = -8;
		boolean bool = x>0;//t
		boolean bool2 = y>0;//f
		boolean bool3 = (x>0 && y>0);//f


		System.out.println("x=" + x+", y="+y);
		System.out.println("x>0" +bool+", y>0"+bool2);
		System.out.println("(x>0 && y>0) : " +bool3);
		System.out.println("(x>0 || y>0) : " +(x>0 || y>0));
		System.out.println("!(x>0) : " + !(x>0));
		System.out.println("!(x>0 && y>0) : " + !(x>0 && y>0));

	}
}
