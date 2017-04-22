package svmc1609;

import java.util.Scanner;

public class HydroelectricDams {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int blocks[] = new int[n];
			for (int i = 0; i < n; i++) {
				blocks[i] = sc.nextInt();
			}

			int min = blocks[0] < blocks[n - 1] ? blocks[0] : blocks[n - 1];
			int volumn = 0;
			for (int i = 1; i < n - 1; i++) {
				volumn += min - blocks[i];
			}
			
			System.out.println(volumn);
		}

		sc.close();
	}
	
	static int minHigh(int a[], int i){
		return 0;
	}
	
	static int maxHigh(int[] a, int i){
		return 0;
	}

}
