class  NotOp
{
	public static void main(String[] args) 
	{
		/*
		���׿�����-������ ������
		! : boolean������ ���
			true�� false, false�� true
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
