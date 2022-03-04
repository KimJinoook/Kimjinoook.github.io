package com.day17;

public class ClassArgsTest {
	//1.매개변수가 클래스인 경우
	public static void func1(Circle c) {
		//참조변수를 사용하는 경우 - 참조변수가 보이면 참조변수.
		c.draw();
		c.delete();
		c.sayCircle();
	}
	//2.매개변수의 다형성
	public static void func2(Shape sh) {
		sh.draw();
		sh.delete();
	}
	
	//3.반환타입이 클래스인 경우
	//=> 반환타입이 Circle(클래스) 이면 Circle 객체가 리턴된다
	public static Circle func3() {
		Circle c= new Circle();
		return c;
	}
	
	//4.반환타입에 다형성 이용
	//반환타입이 Shape (부모클래스)이면 자식객체가 리턴된다
	public static Shape func4(int type) {
		Shape sh= null;
		if(type==1) {
			sh=new Circle();
		}else if(type==2) {
			sh=new Triangle();
		}
		
		return sh;
	}

	public static void main(String[] args) {
		/*
		 메서드의 매개변수가 int면 main에서 int값을 넣는다
		 				String 이면 String을 넣는다
		 				Circle 이면 Circle을 넣는다
		 					(? new로 객체 생성생서 넣는다)
		 				Shape (부모이면) 자식객체를 넣는다
		 */
		
		//1. 매개변수가 클래스인 경우
		func1(new Circle()); 
		
		Circle c= new Circle();
		func1(c);
		
		//2. 매개변수의 다형성
		func2(new Triangle());
		func2(new Circle());
		
		Shape sh= new Triangle();
		func2(sh);
		
		//3. 반환타입이 클래스인 경우
		Circle c2= func3();
		c2.draw();
		
		//4. 반환타입에 다형성 이용
		Shape sh2= func4(1);
		sh2.draw();
	}

}
