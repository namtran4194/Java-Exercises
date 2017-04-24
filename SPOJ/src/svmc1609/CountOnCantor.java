package svmc1609;

import java.util.Scanner;

public class CountOnCantor {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int term = sc.nextInt();
			int i = 1, j = 1;
			int count = 1;
			while (count < term) {
				j++;
				count++;
				if (count == term)
					break;
				while (j > 1 && count < term) {
					i++;
					j--;
					count++;
					if (count == term)
						break;
				}
				if (count == term)
					break;

				i++;
				count++;
				if (count == term)
					break;
				while (i > 1 && count < term) {
					i--;
					j++;
					count++;
					if (count == term)
						break;
				}
			}

			System.out.println("TERM " + term + " IS " + i + "/" + j);
		}
		sc.close();
	}

}
