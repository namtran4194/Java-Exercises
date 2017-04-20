package svmc1609;

import java.util.Scanner;

public class MirrorStrings_BadCase {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		for (int tc = 0; tc < t; tc++) {
			char[] c = sc.next().toCharArray();
			int len = c.length;
			int subLen = len;

			int count = 0;
			while (true) {
				boolean isFinish = false;

				for (int i = 0; i < len; i++) {
					if (i + subLen > len)
						break;
					if (subLen == 1 || subLen == 2 && c[i] == c[i + 1]) {
						count++;
						isFinish = true;
					} else {
						boolean check = true;
						for (int j = 0; j < subLen / 2; j++) {
							if (c[j + i] != c[(subLen - 1 - j) + i]) {
								check = false;
								break;
							}
						}
						if (check) {
							count++;
							isFinish = true;
						}
					}
				}
				if (isFinish)
					break;
				if (subLen == 1)
					break;
				subLen--;
			}

			System.out.println(subLen + " " + count);
		}
		sc.close();
	}
}
