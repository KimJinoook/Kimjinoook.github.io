package com.day19.sample;

//과일 구매자
public class FruitBuyer {
	private int myMoney; // 남은 돈
	private int numOfApple; // 구매한 사과 개수
	
	public FruitBuyer(int money) {
		this.myMoney = money;
	}
	
	//사과를 구매하는 메서드
	//사과를 구매하는데 있어서 필요한 것 - 구매대상, 구매금액
	public void buyApple(FruitSeller seller, int money) {
		if(money > myMoney) {
			System.out.println("돈 부족, 구매 불가");
			return;
		}
		int[] arr = seller.saleApple(money);
		this.numOfApple += arr[0];
		this.myMoney -= arr[1];
		//buyer가 참조하는 객체는 돈을 지불하고 사과를 얻게된다
		//seller가 참조하는 객체는 사과를 판매하고 수익이 생긴다
	}
	
	public void buyAppleClass(FruitSeller seller, int money) {
		if(money > myMoney) {
			System.out.println("돈 부족, 구매 불가");
			return;
		}
		FruitResult result = seller.saleAppleClass(money);
		this.numOfApple += result.getNum();
		myMoney -= (money - result.getChange());
	}
	public void showResult() {
		System.out.println("\n===구매자 정보===");
		System.out.println("현재 잔액 : "+this.myMoney);
		System.out.println("사과 개수 : "+this.numOfApple);
		System.out.println();
	}

}

/*
 
 
 */
