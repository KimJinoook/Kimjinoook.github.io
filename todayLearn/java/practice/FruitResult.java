package com.day19.sample;

//사과 개수의 거스름돈을 담을 클래스
public class FruitResult {
	private int num; // 사과 개수
	private int change;
	
	public FruitResult(int num, int change) {
		this.num = num;
		this.change = change;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getChange() {
		return change;
	}
	
	public void setChange(int change) {
		this.change =change;
	}
}
