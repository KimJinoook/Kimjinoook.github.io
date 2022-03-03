package com.day18;
import java.util.Scanner;
abstract class Employee{
	protected String name;
	Employee(String name){
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public abstract int findPay();
	public abstract void kind();
}
class Permanent extends Employee{
	protected int salary;
	Permanent(String name, int salary){
		super(name);
		this.salary = salary;
	}
	public int getSalary() {
		return this.salary;
	}
	public int findPay() {
		return this.salary;
	}
	public void kind() {
		System.out.println("�������:�����");
	}
}
class Temporary extends Employee{
	private int time, pay;
	Temporary(String name, int time, int pay){
		super(name);
		this.time = time;
		this.pay = pay;
	}
	public int findPay() {
		return time*pay;
	}
	public void kind() {
		System.out.println("�������:�ӽ���");
	}
}
class SalesPerson extends Permanent{
	private int earnings;
	private final double RATE = 0.15;
	SalesPerson(String name, int salary, int earnings){
		super(name, salary);
		this.earnings = earnings;
	}
	public int findPay() {
		return salary+(int)(earnings*RATE);
	}
	public void kind() {
		System.out.println("�������:�Ǹ���");
	}
}
class manager{
	private Scanner sc = new Scanner(System.in);
	Employee e = null;
	public String showMenu() {
		System.out.println("������� - �����<p>, �ӽ���<T>, �Ǹ���<s>�� �Է��ϼ���");
		return sc.nextLine();
	}
	public void p(){
		System.out.println("�̸�, �⺻�޿��� �Է��ϼ���");
		String name = sc.nextLine();
		int salary = sc.nextInt();
		sc.nextLine();
		e = new Permanent(name, salary);
	}
	public void t() {
		System.out.println("�̸�, ���ѽð�, �ð��� �޿��� �Է��ϼ���");
		String name = sc.nextLine();
		int time = sc.nextInt();
		int pay = sc.nextInt();
		sc.nextLine();
		e = new Temporary(name, time, pay);
	}
	public void s() {
		System.out.println("�̸�, �⺻�޿�, �Ǹż����� �Է��ϼ���");
		String name = sc.nextLine();
		int salary = sc.nextInt();
		int earnings = sc.nextInt();
		sc.nextLine();
		e = new SalesPerson(name, salary, earnings);
	}
	public void disPlay() {
		System.out.println("=============================");
		e.kind();
		System.out.println("�̸�:"+e.name);
		System.out.println("�޿�:"+e.findPay());
	}
}
public class Prac_10_48 {
	public static void main(String[] args) {
		manager m = new manager();
		if(m.showMenu().equalsIgnoreCase("P")) m.p();
		else if(m.showMenu().equalsIgnoreCase("T")) m.t();
		else if(m.showMenu().equalsIgnoreCase("S")) m.s();
		m.disPlay();
	}
}
