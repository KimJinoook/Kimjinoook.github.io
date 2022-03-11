package com.test;

import java.util.*;

class Score{
	private String name, classNum, rank;
	private int java, oracle, jsp, sum;
	private double avg;
	
	public Score(String name, String classNum, int java, int oracle, int jsp) {
		this.name = name;
		this.classNum = classNum;
		this.java=java;
		this.oracle = oracle;
		this.jsp = jsp;
	}
	
	public String getName() {
		return name;
	}
	public String getClassNum() {
		return classNum;
	}
	public int getJava() {
		return java;
	}
	public int getOracle() {
		return oracle;
	}
	public int getJsp() {
		return jsp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setClassNum(String num) {
		this.classNum = num;
	}
	public void setJava(int num) {
		this.java = num;
	}
	public void setOracle(int num) {
		this.oracle = num;
	}
	public void setJsp(int num) {
		this.jsp = num;
	}
	
	
	public int findSum() {
		this.sum = java+oracle+jsp;
		return sum;
	}
	public double findAvg() {
		this.avg = (java+oracle+jsp)/3;
		return avg;
	}
	public String findRank() {
		if(this.avg>=90) this.rank = "A";
		else if(this.avg>=80) this.rank = "B";
		else if(this.avg>=70) this.rank = "C";
		else if(this.avg>=50) this.rank = "D";
		else this.rank = "F";
		return rank;
	}
	public void showData() {
		System.out.print("이름 : \t");
		System.out.print("반 : \t");
		System.out.print("java : \t");
		System.out.print("oracle : \t");
		System.out.print("jsp : \t");
		System.out.print("총점 : \t");
		System.out.print("평균 : \t");
		System.out.print("학점 : \n");
		System.out.print(this.getName()+"\t");
		System.out.print(this.getClassNum()+"\t");
		System.out.print(this.getJava()+"\t");
		System.out.print(this.getOracle()+"\t\t");
		System.out.print(this.getJsp()+"\t");
		System.out.print(this.findSum()+"\t");
		System.out.print(this.findAvg()+"\t");
		System.out.print(this.findRank()+"\n");
		System.out.println();
	}
}

class ScoreManager{
	Scanner sc = new Scanner(System.in);
	public void showMenu() {
		System.out.println("1. 성적입력");
		System.out.println("2. 전체학생의 성적 조회");
		System.out.println("3. 학생별 성적 조회");
		System.out.println("4. 클래스 별 성적 조회");
		System.out.println("5. 학생 성적 수정");
		System.out.println("6. 종료");
		System.out.print("선택하세요 : ");
	}
	public Score inPut() {
		System.out.println("\n학생이름, 반, java,oracle, jsp 점수를 입력하세요");
		String name = sc.nextLine();
		String classNum = sc.nextLine();
		int java = sc.nextInt();
		int oracle = sc.nextInt();
		int jsp = sc.nextInt();
		sc.nextLine();
		return new Score(name, classNum, java, oracle, jsp);
		
	}
	public void showAll(ArrayList<Score> list) {
		for(int i=0;i<list.size();i++) {
			Score s = list.get(i);
			s.showData();
		}
	}
	public void showStu(ArrayList<Score> list) {
		System.out.println("학생 이름 입력");
		String namesearch = sc.nextLine();
		for(int i=0;i<list.size();i++) {
			Score s = list.get(i);
			if(s.getName().equals(namesearch)) {
				s.showData();
			}
		}
	}
	public void showClass(ArrayList<Score> list) {
		System.out.println("반 번호 입력");
		String classsearch = sc.nextLine();
		for(int i=0;i<list.size();i++) {
			Score s = list.get(i);
			if(s.getClassNum().equals(classsearch)) {
				s.showData();
			}
		}
	}
	public void change(ArrayList<Score> list) {
		System.out.println("클래스<반>와 학생이름을 입력하세요");
		String classnum = sc.nextLine();
		String name = sc.nextLine();
		
		System.out.println("수정하려는 점수를 java, oracle, jsp과목 순으로 입력하세요");
		int java = sc.nextInt();
		int oracle = sc.nextInt();
		int jsp = sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<list.size();i++) {
			String cn = list.get(i).getClassNum();
			String s = list.get(i).getName();
			if(s.equals(name)&&cn.equals(classnum)) {
				list.get(i).setJava(java);
				list.get(i).setOracle(oracle);
				list.get(i).setJsp(jsp);
				list.get(i).showData();
			}
		}
	}
}

public class 김진욱_ScoreTest {

	public static void main(String[] args) {
		ArrayList<Score> list = new ArrayList<Score>();
		ScoreManager m = new ScoreManager();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			m.showMenu();
			
			switch(sc.nextLine()) {
				case "1":
					list.add(m.inPut());
					break;
				case "2":
					m.showAll(list);
					break;
				case "3":
					m.showStu(list);
					break;
				case "4":
					m.showClass(list);
					break;
				case "5":
					m.change(list);
					break;
				case "6":
					System.out.println("종료합니다");
					return;
				default :
					System.out.println("잘못입력했습니다");
					break;
			}
		}
	}
}
