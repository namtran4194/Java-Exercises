package svmc1609;

import java.util.Scanner;

public class AddingReversedNumbers {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println(generateReverse(a, b));
		}
		sc.close();
	}

	private static int generateReverse(int a, int b) {
		return reversing(reversing(a) + reversing(b));
	}

	static int reversing(int n) {
		int x = 0;
		while (n > 0) {
			x = x * 10 + n % 10;
			n = n / 10;
		}
		return x;
	}
}
