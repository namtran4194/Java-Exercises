package others;

import java.io.FileInputStream;
import java.util.Scanner;

/** How many trailing zeros are in the factorial number */
public class Factorial {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int number = sc.nextInt();
			int numOfTrailingZeros = 0;

			int n = 1;
			while (power(n) <= number) {
				numOfTrailingZeros += number / power(n);
				n++;
			}
			System.out.println(numOfTrailingZeros);
		}
		sc.close();
	}

	static int power(int n) {
		int s = 1;
		for (int i = 0; i < n; i++)
			s *= 5;
		return s;
	}

}
