import java.util.Scanner;
class Shape{
	public void findArea() {}
}
class Circle extends Shape{
	private int r;
	final double PI = 3.1415;
	Circle(int r){
		this.r=r;
	}
	public void findArea() {
		System.out.println("���� ���� : "+PI*r*r);
	}
}
class Rect extends Shape{
	private int w,h;
	Rect(int w, int h){
		this.w = w;
		this.h = h;
	}
	public void findArea() {
		System.out.println("�簢���� ���� : "+w*h);
	}
}
class Manager{
	public Scanner sc = new Scanner(System.in);
	private final int MAX_COUNT = 100;
	private Shape[] sh = new Shape[MAX_COUNT];
	private int idx;
	public void showMenu() {
		System.out.print("1.�� 2.�簢�� 3.���� 4.���� ==> ");
	}
	public void inputCircle() {
		if(idx==100) {
			System.out.println("������� ����");
			return;
		}
		System.out.print("r = ");
		sh[idx++] = new Circle(sc.nextInt());
	}
	public void inputRect() {
		if(idx==100) {
			System.out.println("������� ����");
			return;
		}
		System.out.print("w = ");
		int w = sc.nextInt();
		System.out.print("h = ");
		int h = sc.nextInt();
		sh[idx++] = new Rect(w,h);
	}
	public void show() {
		System.out.println("-----����-----");
		if (idx==0) System.out.println("�ڷᰡ �����ϴ�.");
		else for(int i = 0; i<idx; i++) sh[i].findArea();
		System.out.println("-------------");
	}
}
public class ������_day17_���� {
	public static void main(String[] args) {
		Manager m = new Manager();
		while(true) {
			m.showMenu();
			switch(m.sc.nextInt()) {
			case 1 : m.inputCircle();
				break;
			case 2 : m.inputRect();
				break;
			case 3 : m.show();
				break;
			case 4 : System.out.println("������!");
				return;
			default : System.out.println("�߸��Է�\n");
				continue;
			}
		}
	}
}