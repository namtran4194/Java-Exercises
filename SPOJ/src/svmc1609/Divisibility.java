package svmc1609;

import java.util.Scanner;

public class Divisibility {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();

			for (int i = 2; i < n; i++) {
				if (i % x == 0 && i % y != 0) {
					System.out.print(i + " ");
				}
			}
			System.out.println();
		}
		sc.close();
	}

}
