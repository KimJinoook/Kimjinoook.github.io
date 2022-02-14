class  ThreeOp
{
	public static void main(String[] args) 
	{
		/*
		삼항연산자(조건연산자)
		변수 선언 = (조건식) ? 값1 : 값2;
		true면 값1, false면 값2를 변수에 대입
		변수와 값1, 값2의 데이터타입이 같아야함.
		조건식은 true,false => 비교연산자나 논리연산자 이용
		*/

		int a=15, b=34;
		int c=(a>b)?a:b;

		System.out.println("a="+a+", b="+b+", 더 큰수는 " +c);

		int d=77;
		System.out.println(d==77?"같다":"다르다");

		int g=1;
		String gender = (g!=1)?"여자":"남자";
		System.out.println("1990년대생의 성별 : " + gender);
	}
}
