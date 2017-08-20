package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class AddingReversedNumbers {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			int addition = reverse(a) + reverse(b);
			System.out.println(reverse(addition));
		}
		sc.close();
	}

	static int reverse(int n) {
		return Integer.parseInt(new StringBuffer(n + "").reverse().toString());
	}

}
