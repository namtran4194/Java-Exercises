package others;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class FastMultiplication {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			BigInteger n1 = sc.nextBigInteger();
			BigInteger n2 = sc.nextBigInteger();
			System.out.println(n1.multiply(n2));
		}
		sc.close();
	}

}
