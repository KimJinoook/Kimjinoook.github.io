package com.day19.sample;

//���� �Ǹ���
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
		//����ο��� �迭 �� ���ϰ��� Ȯ���� �� ���� �迭�� �ڵ�ȿ��� Ȯ���ؾ� �Ѵ�
	}
	
	public FruitResult saleAppleClass(int money) {
		int num = money/APPLE_PRICE;
		int change = money%APPLE_PRICE;
		
		numOfApple -= num;
		myMoney+=(money-change);
		
		FruitResult result = new FruitResult(num, change);
		
		return result;
		//����ο��� �迭 �� ���ϰ��� Ȯ���� �� ���� �迭�� �ڵ�ȿ��� Ȯ���ؾ� �Ѵ�
	}
	
	public void showResult() {
		System.out.println("\n===�Ǹ��� ����===");
		System.out.println("���� ��� : "+this.numOfApple);
		System.out.println("�Ǹż��� : "+this.myMoney);
		System.out.println();
	}

}
