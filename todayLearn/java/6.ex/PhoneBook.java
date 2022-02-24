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
			System.out.println("\n�����ϼ���...");
			System.out.println("1. ������ �Է�");
			System.out.println("2. ��ü ������ ��ȸ");
			System.out.println("3. ������ �˻�");
			System.out.println("4. ������ ����");
			System.out.println("5. ���α׷� ����");
			System.out.print("����: ");
			String con = sc.nextLine();
			if(con.equals("1")) {
				System.out.println("������ �Է��� �����մϴ�...");
				System.out.print("�̸�: ");
				String n = sc.nextLine();
				System.out.print("��ȭ��ȣ: ");
				String p = sc.nextLine();
				System.out.print("�������: ");
				String b = sc.nextLine();
				s[i++] = new PhoneInfo(n,p,b);
				System.out.println("������ �Է��� �Ϸ�Ǿ����ϴ�.\n");
			}else if(con.equals("2")) {
				System.out.println("-------------��ü ������ ��ȸ------------");
				for(int j = 0; j<i;j++) s[j].showInfo();
			}else if(con.equals("5")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			}else if(con.equals("3")) {
				System.out.println("������ �˻��� �����մϴ�..");
				System.out.print("�̸�: ");
				String search = sc.nextLine();
				for(int j = 0; j<i;j++) {
					if(search.equals(s[j].getName())){
						s[j].showInfo();
					}
				}
				System.out.println("������ �˻��� �Ϸ�Ǿ����ϴ�.");
			}else if(con.equals("4")){
				System.out.println("������ ������ �����մϴ�..");
				System.out.print("�̸�: ");
				String search = sc.nextLine();
				for(int j = 0; j<i;j++) {
					if(search.equals(s[j].getName())){
						for (int k=j+1; k<i;k++){
							s[k-1] = s[k];
						}
						--i;
					}
				}
				System.out.println("������ ������ �Ϸ�Ǿ����ϴ�.");
			}else {
				System.out.println("�߸��Է�");
				continue;
			}
		}
	}
}