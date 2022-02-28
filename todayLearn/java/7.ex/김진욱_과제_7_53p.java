import java.util.*; 
class FoodSale{
	protected String menu;
	protected int amount;
	protected int cost;
	protected int price;
	public static int totalPrice;
	FoodSale(String menu, int amount, int cost){
		this.menu = menu;
		this.amount = amount;
		this.cost = cost;
	}
	public int getPrice() {
		return price;
	}

	public void findPrice() {
		this.price = cost*amount;
	}
	public void findTotalPrice() {
		FoodSale.totalPrice +=price;
	}
}

class StudentFoodSale extends FoodSale{
	private int salePrice;
	public static int totalSalePrice;
	private final double SALE_RATE = 0.1;
	StudentFoodSale(String menu, int amount, int cost){
		super(menu, amount, cost);
	}
	public int getPrice() {
		return price;
	}
	public void findSalePrice() {
		this.salePrice = (int)(amount*cost*SALE_RATE);
	}
	public void findPrice() {
		this.price = amount*cost - (int)(amount*cost*SALE_RATE);
	}
	public void findTotalPrice() {
		FoodSale.totalPrice +=price;
		StudentFoodSale.totalSalePrice += salePrice;
	}
}

public class 김진욱_과제_7_53p {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("메뉴,수량,단가,학생여부<y/n>를 입력하세요!");
			String menu = sc.nextLine();
			int amount = sc.nextInt();
			int cost = sc.nextInt();
			sc.nextLine();
			String con = sc.nextLine();
			
			if(con.equalsIgnoreCase("Y")) {
				StudentFoodSale s = new StudentFoodSale(menu, amount, cost);
				s.findSalePrice();
				s.findPrice();
				s.findTotalPrice();
				System.out.println("판매금액="+s.getPrice()+", 누적판매금액="+FoodSale.totalPrice+", 누적할인금액="+StudentFoodSale.totalSalePrice);
			}else if(con.equalsIgnoreCase("N")) {
				FoodSale s = new FoodSale(menu,amount,cost);
				s.findPrice();
				s.findTotalPrice();
				System.out.println("판매금액="+s.getPrice()+", 누적판매금액="+FoodSale.totalPrice);
				
			}else {
				System.out.println("잘못입력");
			}
			System.out.println("그만하시겠습니까?<Q>uit");
			String q = sc.nextLine();
			if (q.equalsIgnoreCase("Q")) break;
		}
	}

}