class ForTest
{
	public static void main(String[] args) 
	{
		//1
		for(int i=0;i<5;i++){
			for(int j=0;j<=i;j++){
				System.out.print("*");
			}
			System.out.println("\n");
		}
		
		System.out.println("\n");
		
		//2
		for(int i=0;i<5;i++){
			for(int j=5;j>i;j--){
				System.out.print("*");
			}
			System.out.println("\n");
		}

		System.out.println("\n");

		//3
		System.out.println("1부터 10까지의 합");
		for(int i=1;i<11;i++){
			System.out.print("1");
			for(int j=2;j<=i;j++){
				System.out.print("+"+j);
			}
			System.out.println("\n");
		}

	}
}
