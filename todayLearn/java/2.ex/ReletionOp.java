class ReletionOp 
{
	public static void main(String[] args) 
	{
		/*
		비교연산자
		>크다
		<작다
		>=크거나같다
		<=작거나같다
		==같다
		!=같지않다

		비교연산자의 결과는 true, fales 조건식에 사용함
		*/

		int a=10, b=4;
		boolean bool = a>b;
		
		System.out.println("a="+a+", b="+b);
		System.out.println("a>b : " +bool);
		System.out.println("a<b : "+(a<b));
		System.out.println("a>=b : "+(a>=b));
		System.out.println("a<=b : "+(a<=b));
		System.out.println("a==b : "+(a==b));
		System.out.println("a!=b : "+(a!=b));
	}
}
