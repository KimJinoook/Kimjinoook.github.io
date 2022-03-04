package com.day19;

class Child{
	private int numOfBead;
	Child(int n){
		this.numOfBead = n;
	}
	public int loseBead(int loseCount) {
		if (loseCount>this.numOfBead) {
			loseCount = this.numOfBead;
		}
		return loseCount;
	}
	public void obtainBead(Child c, int obtainCount) {
		c.numOfBead -= c.loseBead(obtainCount);
		this.numOfBead += obtainCount;
	}
	public void showProperty() {
		System.out.println("보유 구슬의 개수 : "+this.numOfBead);
	}
}

public class Prac_11_12 {
	public static void showInfo(Child a, Child b) {
		System.out.println("===어린이1===");
		a.showProperty();
		System.out.println("===어린이2===");
		b.showProperty();
	}
	public static void main(String[] args) {
		Child c1 = new Child(15);
		Child c2 = new Child(9);
		System.out.println("게임 전 구슬의 보유 개수");
		showInfo(c1,c2);
		c1.obtainBead(c2, 2);
		c2.obtainBead(c1, 7);
		System.out.println("\n게임 후 구슬의 보유 개수");
		showInfo(c1,c2);
	}
}
