import java.util.Scanner;

class PhoneInfo{
	private String name, phone, birth;
	PhoneInfo(String name, String phone, String birth){
		this.name=name;
		this.phone=phone;
		this.birth=birth;
	}
	PhoneInfo(String name, String phone){
		this.name=name;
		this.phone=phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void showInfo(){
		System.out.println("\nname: "+this.name);
		System.out.println("phone: "+this.phone);
		if(this.birth!=null&&(this.birth.isEmpty()==false)) System.out.println("birth: "+this.birth);
	}
}


public class PhoneBook {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = 0; //index
		PhoneInfo[] s = new PhoneInfo[100];
		while(true) {
			System.out.println("\n선택하세요...");
			System.out.println("1. 데이터 입력");
			System.out.println("2. 전체 데이터 조회");
			System.out.println("3. 데이터 검색");
			System.out.println("4. 데이터 삭제");
			System.out.println("5. 프로그램 종료");
			System.out.print("선택: ");
			String con = sc.nextLine();
			if(con.equals("1")) {
				System.out.println("데이터 입력을 시작합니다...");
				System.out.print("이름: ");
				String n = sc.nextLine();
				System.out.print("전화번호: ");
				String p = sc.nextLine();
				System.out.print("생년월일: ");
				String b = sc.nextLine();
				s[i++] = new PhoneInfo(n,p,b);
				System.out.println("데이터 입력이 완료되었습니다.\n");
			}else if(con.equals("2")) {
				System.out.println("-------------전체 데이터 조회------------");
				for(int j = 0; j<i;j++) s[j].showInfo();
			}else if(con.equals("5")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else if(con.equals("3")) {
				System.out.println("데이터 검색을 시작합니다..");
				System.out.print("이름: ");
				String search = sc.nextLine();
				for(int j = 0; j<i;j++) {
					if(search.equals(s[j].getName())){
						s[j].showInfo();
					}
				}
				System.out.println("데이터 검색이 완료되었습니다.");
			}else if(con.equals("4")){
				System.out.println("데이터 삭제를 시작합니다..");
				System.out.print("이름: ");
				String search = sc.nextLine();
				for(int j = 0; j<i;j++) {
					if(search.equals(s[j].getName())){
						for (int k=j+1; k<i;k++){
							s[k-1] = s[k];
						}
						--i;
					}
				}
				System.out.println("데이터 삭제가 완료되었습니다.");
			}else {
				System.out.println("잘못입력");
				continue;
			}
		}
	}
}