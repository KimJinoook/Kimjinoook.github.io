package com.day6;
import java.util.*;
public class Gugudan {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("몇단까지 출력할지 입력");
		int dan = sc.nextInt();
		
		for (int i=1; i<=dan; i++) {
			for (int j=1; j<=9; j++) {
				System.out.println(i+"*"+j+"= "+i*j);
			}
			System.out.println();
		}

	}

}
