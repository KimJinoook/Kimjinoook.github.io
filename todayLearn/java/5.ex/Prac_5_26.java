import java.util.*;
class Calculator{
	float a = 10f;
	float b = 20f;
	public float add(){
		float c = a+b;
		return c;
	}
	public float sub(){
		float c = a-b;
		return c;
	}
	public float div(){
		float c = a/b;
		return c;
	}
	public float mul(){
		float c = a*b;
		return c;
	}
}
class rectangle{
	float w = 0;
	float h = 0;
	public float rng(){
		float rng = w+w+h+h;
		return rng;
	}
	public float area(){
		float area = w*h;
		return area;
	}
}
class Prac_5_26 {
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		System.out.println("a="+cal.a+",b="+cal.b);
		System.out.println("a+b = "+cal.add());
		System.out.println("a-b = "+cal.sub());
		System.out.println("a*b = "+cal.mul());
		System.out.println("a/b = "+cal.div());

		rectangle rec = new rectangle();
		Scanner sc = new Scanner(System.in);
		System.out.println("\n���簢�� ���� �Է�");
		rec.w = sc.nextFloat();
		System.out.println("���簢�� ���� �Է�");
		rec.h = sc.nextFloat();
		System.out.println("���簢�� �ѷ� : "+rec.rng());
		System.out.println("���簢�� ���� : "+rec.area());
	}
}