class ShiftOp {
	public static void main(String args[]) {
		 int temp; // 계산 결과를 담기 위한 변수

		System.out.println(-8);
        // -8을 2진수 문자열로 변경한다.
		System.out.println(Integer.toBinaryString(-8));	
		System.out.println();			
		
		temp = -8 << 1;
		System.out.println( "-8 << 1  = " + temp);	
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
		
		temp = -8 << 2;
		System.out.println( "-8 << 2  = " + temp);
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
				
		System.out.println();
		System.out.println(-8);
		System.out.println(Integer.toBinaryString(-8));
		System.out.println();
		
		temp = -8 >> 1;
		System.out.println( "-8 >> 1  = " + temp);
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
		
		temp = -8 >> 2;
		System.out.println( "-8 >> 2  = " + temp);
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
				
		System.out.println();
		System.out.println(-8);
		System.out.println(Integer.toBinaryString(-8));
		System.out.println();
		
		temp = -8 >>> 1;
		System.out.println( "-8 >>> 1  = " + temp);
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
		
		temp = -8 >>> 2;
		System.out.println( "-8 >>> 2  = " + temp);
		System.out.println(Integer.toBinaryString(temp));
		System.out.println();
	}
}