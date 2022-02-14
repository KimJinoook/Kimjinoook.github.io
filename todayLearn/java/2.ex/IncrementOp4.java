class  IncrementOp4
{
	public static void main(String[] args) 
	{
		/*
		
		0
		0
		2

		7.0
		7.0
		5.0

		A
		B

		a
		b

		*/
		int a= 0;
		System.out.println("a:"+a);
		System.out.println("a++ : " + a++);
		System.out.println("++a : " + ++a);

		
		double b = 7;
		System.out.println("b : " +b);
		System.out.println("b-- : " + b--);
		System.out.println("--b : " + --b);

		
		char c='A';
		System.out.println("c : " + c);
		System.out.println("++c : " + ++c);
		c='a';
		System.out.println("c : " +c);
		c++;
		System.out.println("c++ : " + c);
	}
}
