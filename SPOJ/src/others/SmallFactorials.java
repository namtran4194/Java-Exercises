package others;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class SmallFactorials {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int number = sc.nextInt();
			System.out.println(factorial(number));
		}
		sc.close();
	}

	static BigInteger factorial(int n) {
		BigInteger sum = BigInteger.valueOf(1);
		for (int i = 1; i <= n; i++)
			sum = sum.multiply(BigInteger.valueOf(i));
		return sum;
	}

}
