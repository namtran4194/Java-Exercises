package svmc1609;

import java.util.Scanner;

public class ToAndFro {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cols;
		while ((cols = sc.nextInt()) != 0) {
			char[] c = sc.next().toCharArray();
			int rows = c.length / cols;
			String[][] mesEncrypted = new String[rows][cols];

			int k = 0;
			for (int i = 0; i < rows; i++) {
				if (i % 2 == 0) {
					for (int j = 0; j < cols; j++) {
						mesEncrypted[i][j] = String.valueOf(c[k++]);
					}
				} else {
					for (int j = cols - 1; j >= 0; j--) {
						mesEncrypted[i][j] = String.valueOf(c[k++]);
					}
				}
			}

			String mess = "";
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					mess += mesEncrypted[j][i];
				}
			}

			System.out.println(mess);
		}
		sc.close();
	}
}
