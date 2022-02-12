import java.util.*;
public class ScannerTest{
    //칼로리 계산
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("지방의 그램을 입력하세요:");
        int fat = sc.nextInt();
        System.out.print("탄수화물의 그램을 입력하세요:");
        int car = sc.nextInt();
        System.out.print("단백질의 그램을 입력하세요:");
        int pro = sc.nextInt();
        System.out.println();

        int cal = fat*9 + car*4 + pro*4;
        System.out.println("총 칼로리 : "+cal);

        float kcal = cal/1000f;
        System.out.println("총 키로칼로리 : "+kcal);

        
        //사각형 구하기
        Scanner sc2 = new Scanner(System.in);
        System.out.print("사각형의 너비를 입력하세요:");
        String w = sc2.nextLine();
        System.out.print("사각형의 높이를 입력하세요:");
        String h = sc2.nextLine();
        System.out.println("사각형의 가로 : "+w);
        System.out.println("사각형의 높이 : "+h);
        int wi = Integer.parseInt(w);
        int hi = Integer.parseInt(h);
        System.out.println("사각형의 면적 : "+(wi*hi));

    }
}