package others;

import java.io.FileInputStream;
import java.util.Scanner;

/** n=(a^2-1)*(b^2-1) and 1<a<=b, find all pairs of a and b */
public class AlmostSquareFactorisation {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			long n = sc.nextLong();
			System.out.println("Case #" + t + ":");
			if (n < 9)
				System.out.println("For n=" + n + " there is no almost square factorisation.");
			else {
				int cnt = 0;
				long a = 2;
				while ((a * a - 1) * (a * a - 1) <= n) {
					if (n % (a * a - 1) == 0) {
						long b = (long) Math.sqrt(n / (a * a - 1) + 1);
						if (b * b == (n / (a * a - 1) + 1)) {
							cnt++;
							if (cnt == 1)
								System.out.print(n);
							System.out.format("=(%d^2-1)*(%d^2-1)", a, b);
						}
					}
					a++;
				}

				if (cnt == 0)
					System.out.println("For n=" + n + " there is no almost square factorisation.");
				else
					System.out.println();
			}
		}
		sc.close();
	}

}
