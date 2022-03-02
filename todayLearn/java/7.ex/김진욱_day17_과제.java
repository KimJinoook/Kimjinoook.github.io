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
		System.out.println("원의 면적 : "+PI*r*r);
	}
}
class Rect extends Shape{
	private int w,h;
	Rect(int w, int h){
		this.w = w;
		this.h = h;
	}
	public void findArea() {
		System.out.println("사각형의 면적 : "+w*h);
	}
}
class Manager{
	public Scanner sc = new Scanner(System.in);
	private final int MAX_COUNT = 100;
	private Shape[] sh = new Shape[MAX_COUNT];
	private int idx;
	public void showMenu() {
		System.out.print("1.원 2.사각형 3.보기 4.종료 ==> ");
	}
	public void inputCircle() {
		if(idx==100) {
			System.out.println("저장공간 없음");
			return;
		}
		System.out.print("r = ");
		sh[idx++] = new Circle(sc.nextInt());
	}
	public void inputRect() {
		if(idx==100) {
			System.out.println("저장공간 없음");
			return;
		}
		System.out.print("w = ");
		int w = sc.nextInt();
		System.out.print("h = ");
		int h = sc.nextInt();
		sh[idx++] = new Rect(w,h);
	}
	public void show() {
		System.out.println("-----보기-----");
		if (idx==0) System.out.println("자료가 없습니다.");
		else for(int i = 0; i<idx; i++) sh[i].findArea();
		System.out.println("-------------");
	}
}
public class 김진욱_day17_과제 {
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
			case 4 : System.out.println("종료함!");
				return;
			default : System.out.println("잘못입력\n");
				continue;
			}
		}
	}
}