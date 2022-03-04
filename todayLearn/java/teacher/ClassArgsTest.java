package com.day17;

public class ClassArgsTest {
	//1.�Ű������� Ŭ������ ���
	public static void func1(Circle c) {
		//���������� ����ϴ� ��� - ���������� ���̸� ��������.
		c.draw();
		c.delete();
		c.sayCircle();
	}
	//2.�Ű������� ������
	public static void func2(Shape sh) {
		sh.draw();
		sh.delete();
	}
	
	//3.��ȯŸ���� Ŭ������ ���
	//=> ��ȯŸ���� Circle(Ŭ����) �̸� Circle ��ü�� ���ϵȴ�
	public static Circle func3() {
		Circle c= new Circle();
		return c;
	}
	
	//4.��ȯŸ�Կ� ������ �̿�
	//��ȯŸ���� Shape (�θ�Ŭ����)�̸� �ڽİ�ü�� ���ϵȴ�
	public static Shape func4(int type) {
		Shape sh= null;
		if(type==1) {
			sh=new Circle();
		}else if(type==2) {
			sh=new Triangle();
		}
		
		return sh;
	}

	public static void main(String[] args) {
		/*
		 �޼����� �Ű������� int�� main���� int���� �ִ´�
		 				String �̸� String�� �ִ´�
		 				Circle �̸� Circle�� �ִ´�
		 					(? new�� ��ü �������� �ִ´�)
		 				Shape (�θ��̸�) �ڽİ�ü�� �ִ´�
		 */
		
		//1. �Ű������� Ŭ������ ���
		func1(new Circle()); 
		
		Circle c= new Circle();
		func1(c);
		
		//2. �Ű������� ������
		func2(new Triangle());
		func2(new Circle());
		
		Shape sh= new Triangle();
		func2(sh);
		
		//3. ��ȯŸ���� Ŭ������ ���
		Circle c2= func3();
		c2.draw();
		
		//4. ��ȯŸ�Կ� ������ �̿�
		Shape sh2= func4(1);
		sh2.draw();
	}

}
