package svmc1609;

import java.util.Scanner;

public class PRIME1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int min = sc.nextInt();
			int max = sc.nextInt();
			generatePrime(min, max);
		}
		sc.close();
	}

	static void generatePrime(int min, int max) {
		for (int i = min; i <= max; i++) {
			if (isPrime(i)) {
				System.out.println(i);
			}
		}
		System.out.println();
	}

	static boolean isPrime(int n) {
		if (n == 2)
			return true;
		if (n == 1 || (n & 1) == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
