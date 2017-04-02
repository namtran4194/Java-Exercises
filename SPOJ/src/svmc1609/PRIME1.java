package svmc1609;

import java.util.Scanner;

public class PRIME1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int num = sc.nextInt();
			if (isPrime(num))
				System.out.println("YES");
			else {
				System.out.println("NO");
			}
		}
		sc.close();
	}

	static boolean isPrime(int i) {
		if (i == 2)
			return true;
		else if (i == 1 || (i & 1) == 0)
			return false;
		for (int k = 3; k * k <= i; k += 2)
			if (i % k == 0)
				return false;
		return true;
	}

}
