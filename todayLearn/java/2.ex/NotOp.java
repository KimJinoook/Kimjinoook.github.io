class  NotOp
{
	public static void main(String[] args) 
	{
		/*
		단항연산자-논리부정 연산자
		! : boolean형에만 사용
			true는 false, false는 true
		*/
		
		boolean power = false;
		System.out.println(power);

		power = !power;
		System.out.println(power);

		int a=10, b=5;
		boolean bool = a<b;
		System.out.println("a<b = "+bool);
		bool=!(a<b);
		System.out.println("!(a<b)=" +bool);


	}
}
