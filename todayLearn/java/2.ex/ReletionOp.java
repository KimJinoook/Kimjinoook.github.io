class ReletionOp 
{
	public static void main(String[] args) 
	{
		/*
		�񱳿�����
		>ũ��
		<�۴�
		>=ũ�ų�����
		<=�۰ų�����
		==����
		!=�����ʴ�

		�񱳿������� ����� true, fales ���ǽĿ� �����
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
