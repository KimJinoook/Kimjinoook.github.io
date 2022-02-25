class Human{
	public void work() {
		System.out.println("하는 일을 기술한다");
	}
}
class Teacher extends Human {
	public void work() {
		System.out.println("가르치는 일을 합니다");
	}
}
class Programmer extends Human {
	public void work() {
		System.out.println("프로그래밍을 합니다");
	}
}
public class extendsTest {
	public static void main(String[] args) {
		Teacher t = new Teacher();
		t.work();
		Programmer p = new Programmer();
		p.work();
	}
}
