class LogicalOp 
{
	public static void main(String[] args) 
	{
		/*
		��������
		&& : and / �ǿ����� �ΰ� ���ÿ� �����Ҷ� true
		|| : or / �ǿ����� ���� �ϳ��� �����ϸ� true
		^  : �ǿ����� ���� ������ false, �ٸ��� true (��Ʈ��������)

		�񱳿�����, ���������� ����� true, fales / ���ǽĿ� ���
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
