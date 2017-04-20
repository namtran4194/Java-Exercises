package svmc1609;

import java.util.Scanner;

public class MirrorStrings_GoodCase {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			char[] c = sc.next().toCharArray();
			int len = c.length;
			int maxLen = 0, count = 0;
			for (int i = 0; i < len; i++) {
				int r = 1;
				while (i - r + 1 >= 0 && i + r < len) {
					if (c[i - r + 1] == c[i + r])
						r++;
					else
						break;
				}
				if (2 * (r - 1) > maxLen) {
					maxLen = 2 * (r - 1);
					count = 1;
				} else if (2 * (r - 1) == maxLen) {
					count++;
				}

				r = 1;
				while (i - r >= 0 && i + r < len) {
					if (c[i - r] == c[i + r])
						r++;
					else
						break;
				}
				if (2 * (r - 1) + 1 > maxLen) {
					maxLen = 2 * (r - 1) + 1;
					count = 1;
				} else if (2 * (r - 1) + 1 == maxLen) {
					count++;
				}
			}
			System.out.println(maxLen + " " + count);
		}
		sc.close();
	}

}
