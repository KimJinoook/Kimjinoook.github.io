class  ThreeOp
{
	public static void main(String[] args) 
	{
		/*
		���׿�����(���ǿ�����)
		���� ���� = (���ǽ�) ? ��1 : ��2;
		true�� ��1, false�� ��2�� ������ ����
		������ ��1, ��2�� ������Ÿ���� ���ƾ���.
		���ǽ��� true,false => �񱳿����ڳ� �������� �̿�
		*/

		int a=15, b=34;
		int c=(a>b)?a:b;

		System.out.println("a="+a+", b="+b+", �� ū���� " +c);

		int d=77;
		System.out.println(d==77?"����":"�ٸ���");

		int g=1;
		String gender = (g!=1)?"����":"����";
		System.out.println("1990������ ���� : " + gender);
	}
}
