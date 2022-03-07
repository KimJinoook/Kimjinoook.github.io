package com.day19.sample;

//과일 판매자
public class FruitSeller {
	private int numOfApple;
	private int myMoney;
	private final int APPLE_PRICE;
	
	FruitSeller(int num, int money, int price){
		this.numOfApple = num;
		this.myMoney = money;
		this.APPLE_PRICE = price;
	}
	
	public int[] saleApple(int money) {
		int numchange[] = new int[2];
		numchange[0] = money/APPLE_PRICE;
		numchange[1] = money-money%APPLE_PRICE;
		this.numOfApple -= numchange[0];
		this.myMoney += numchange[1];
		
		return numchange;
		//선언부에서 배열 내 리턴값을 확인할 수 없어 배열을 코드안에서 확인해야 한다
	}
	
	public FruitResult saleAppleClass(int money) {
		int num = money/APPLE_PRICE;
		int change = money%APPLE_PRICE;
		
		numOfApple -= num;
		myMoney+=(money-change);
		
		FruitResult result = new FruitResult(num, change);
		
		return result;
		//선언부에서 배열 내 리턴값을 확인할 수 없어 배열을 코드안에서 확인해야 한다
	}
	
	public void showResult() {
		System.out.println("\n===판매자 정보===");
		System.out.println("남은 사과 : "+this.numOfApple);
		System.out.println("판매수익 : "+this.myMoney);
		System.out.println();
	}

}
