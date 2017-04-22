package sorts;

import java.util.Scanner;

public class RepairRoad {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int status[] = new int[n];
			for (int i = 0; i < n; i++) {
				status[i] = sc.nextInt();
			}

			int count, max = 0;
			for (int i = 0; i < n; i++) {
				count = 0;
				int temp = k;
				for (int j = i; j < n; j++) {
					if (status[j] == 1) {
						count++;
					} else {
						if (temp > 0) {
							count++;
							temp--;
						} else {
							break;
						}
					}

				}
				max = count > max ? count : max;
			}

			System.out.println(max);
		}
		sc.close();
	}

}
