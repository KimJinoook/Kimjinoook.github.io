import java.util.*;
class Employee{
	protected String name;
	Employee(String name){
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public int findPay() {
		return 0;
	}
}

class Permanent extends Employee{
	private int salary;
	private int bonus;
	Permanent(String name, int salary, int bonus){
		super (name);
		this.salary = salary;
		this.bonus = bonus;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getSalary() {
		return this.salary;
	}
	public int getBonus() {
		return this.bonus;
	}
	public int findPay() {
		return salary+bonus;
	}
}

class Temporary extends Employee{
	private int time;
	private int pay;
	Temporary(String name, int time, int pay){
		super(name);
		this.time = time;
		this.pay = pay;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getTime() {
		return this.time;
	}
	public int getPay() {
		return this.pay;
	}
	public int findPay() {
		return time*pay;
	}
}

public class ������_����_7_50p  {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�������-�����<P>,�ӽ���<T>�� �Է��ϼ���");
		String c = sc.nextLine();
		if(c.equalsIgnoreCase("P")) {
			System.out.println("�̸�, �⺻�޿�, ���ʽ��� �Է��ϼ���");
			Permanent p = new Permanent(sc.nextLine(),sc.nextInt(),sc.nextInt());
			sc.nextLine();
			System.out.println("----------------");
			System.out.println("������� : �����");
			System.out.println("�̸�:"+p.getName());
			System.out.println("�޿�:"+p.findPay());
		}else if(c.equalsIgnoreCase("T")) {
			System.out.println("�̸�, ���ѽð�, �ð��� �޿��� �Է��ϼ���");
			Temporary t = new Temporary(sc.nextLine(),sc.nextInt(),sc.nextInt());
			sc.nextLine();
			System.out.println("----------------");
			System.out.println("������� : �ӽ���");
			System.out.println("�̸�:"+t.getName());
			System.out.println("�޿�:"+t.findPay());
		}else {
			System.out.println("�߸��Է�");
		}

	}

}
