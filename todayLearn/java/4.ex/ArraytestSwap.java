import java.util.*;
class ArraytestSwap {
	public static void swap(int[] a){
		for(int i = 1 ; i<a.length;i++){
			for(int j = 0 ; j<i ; j++){
				if (a[i]>a[j]) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("5���� ���ڸ� �Է��ϼ���");
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[5];
		for(int i=0;i<arr.length;i++){
			arr[i] = sc.nextInt();
		}
		System.out.println("===������������ ����===");
		swap(arr);
		for(int i : arr) System.out.print(i+"\t");		
	}
}
