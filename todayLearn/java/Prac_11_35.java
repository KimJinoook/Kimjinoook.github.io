package com.day19;
import java.util.*;

class Pitcher{
	int[] answer = new int[3];
	public void makeAnswer() {
		for(int i = 0; i<answer.length;i++) {
			answer[i] = (int)(Math.random()*9);
			for(int j = 0; j<i;j++) {
				if(answer[i]==answer[j]) --i;
			}
		}
	}
}

class Hitter{
	int[] user;
	Hitter(int[] a) {
		user = a;
	}
}

class Umpire{
	int strike, ball;

	public void search(Pitcher p, Hitter h) {
		int s = 0;
		int b = 0;
		for(int i = 0; i<3; i++) {
			if(h.user[i] == p.answer[i]) {
				s+=1;
			}
			for(int j = 0;j<3;j++) {
				if(i!=j) {
					if(p.answer[i]==h.user[j]) {
						b+=1;
					}
				}
			}
		}
		this.strike = s;
		this.ball = b;
	}
}

public class Prac_11_35 {
	
	public static int[] input() {
		Scanner sc = new Scanner(System.in);
		int[] a = {sc.nextInt(),sc.nextInt(),sc.nextInt()};
		for(int i = 0; i<3;i++) {
			for(int j = 0; j<i;j++) {
				if(a[i]==a[j]) {
					System.out.println("중복있음, 다시 입력");
					input();
				}
			}
		}
		return a;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Umpire u = new Umpire();
		Hitter h = null;
		Pitcher p = new Pitcher();
		
		
		while(true) {
			p.makeAnswer();
			for(int i : p.answer) System.out.println(i);
			for(int i =0; i<10; i++) {
				System.out.println("다른 세 수를 입력하세요");
				h = new Hitter(input());
				u.search(p, h);
				if(u.strike == 3) {
					System.out.println("You Win in "+(i+1));
					break;
				}
				System.out.println("반복회수 : "+(i+1)+", "+ u.strike+" Strike!! "+u.ball+" Ball!!");
				if(i==9) {
					System.out.println("\n\nYou Lose, Pitcher is");
					System.out.println(p.answer[0]+"\t"+p.answer[1]+"\t"+p.answer[2]+"\t");
				}
			}
			System.out.println("\n계속하시겠습니까?<Y/N>");
			
			String con = sc.nextLine();
			if(con.equalsIgnoreCase("N")) break;
		}
	}
}
