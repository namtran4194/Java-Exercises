package svmc1609;

import java.util.Scanner;

public class NumberSteps {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int num = -1;
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (x == y) {
				if (x % 2 == 0)
					num = x * 2;
				else
					num = x * 2 - 1;
			} else if (x > y && (x - y) == 2) {
				if (x % 2 == 0)
					num = x * 2 - 2;
				else
					num = x * 2 - 3;
			}
			System.out.println(num < 0 ? "No Number" : num);
		}
		sc.close();
	}

}
