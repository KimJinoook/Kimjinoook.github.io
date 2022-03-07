package com.day19.sample;

//���� ������
public class FruitBuyer {
	private int myMoney; // ���� ��
	private int numOfApple; // ������ ��� ����
	
	public FruitBuyer(int money) {
		this.myMoney = money;
	}
	
	//����� �����ϴ� �޼���
	//����� �����ϴµ� �־ �ʿ��� �� - ���Ŵ��, ���űݾ�
	public void buyApple(FruitSeller seller, int money) {
		if(money > myMoney) {
			System.out.println("�� ����, ���� �Ұ�");
			return;
		}
		int[] arr = seller.saleApple(money);
		this.numOfApple += arr[0];
		this.myMoney -= arr[1];
		//buyer�� �����ϴ� ��ü�� ���� �����ϰ� ����� ��Եȴ�
		//seller�� �����ϴ� ��ü�� ����� �Ǹ��ϰ� ������ �����
	}
	
	public void buyAppleClass(FruitSeller seller, int money) {
		if(money > myMoney) {
			System.out.println("�� ����, ���� �Ұ�");
			return;
		}
		FruitResult result = seller.saleAppleClass(money);
		this.numOfApple += result.getNum();
		myMoney -= (money - result.getChange());
	}
	public void showResult() {
		System.out.println("\n===������ ����===");
		System.out.println("���� �ܾ� : "+this.myMoney);
		System.out.println("��� ���� : "+this.numOfApple);
		System.out.println();
	}

}

/*
 
 
 */
