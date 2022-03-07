package com.day19.sample;

public class FruitMain {

	public static void main(String[] args) {
		FruitBuyer buyer = new FruitBuyer(10000);
		FruitSeller s1 = new FruitSeller(30,0,1500);
		FruitSeller s2 = new FruitSeller(20,0,1000);
		
		
		buyer.showResult();
	
		buyer.buyAppleClass(s1, 2000); //1500��¥��, 2000�� ����

		buyer.showResult(); // �Ž����� 500�� �޾�, 8500��
		s1.showResult(); // ����ϳ��Ȱ� 1500���� ����
		
		buyer.buyApple(s1, 100000000); //���ʰ�
		buyer.showResult(); // ����� �״��
		s1.showResult(); // ���Ȱ� �״��
		
		
		
		
	}
}
