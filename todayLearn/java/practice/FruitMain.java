package com.day19.sample;

public class FruitMain {

	public static void main(String[] args) {
		FruitBuyer buyer = new FruitBuyer(10000);
		FruitSeller s1 = new FruitSeller(30,0,1500);
		FruitSeller s2 = new FruitSeller(20,0,1000);
		
		
		buyer.showResult();
	
		buyer.buyAppleClass(s1, 2000); //1500원짜리, 2000원 구매

		buyer.showResult(); // 거스름돈 500원 받아, 8500원
		s1.showResult(); // 사과하나팔고 1500원만 받음
		
		buyer.buyApple(s1, 100000000); //돈초과
		buyer.showResult(); // 못사고 그대로
		s1.showResult(); // 안팔고 그대로
		
		
		
		
	}
}
