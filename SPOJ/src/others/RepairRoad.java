package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class RepairRoad {

	static int[] roads;
	static int max;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();

			roads = new int[n];
			for (int i = 0; i < roads.length; i++)
				roads[i] = sc.nextInt();

			max = 0;
			for (int i = 0; i < roads.length; i++)
				if (roads[i] == 0)
					repair(i, k);

			System.out.println(max);
		}
		sc.close();
	}

	static void repair(int pos, int k) {
		for (int i = pos; i < roads.length && k > 0; i++) {
			if (roads[i] == 0) {
				roads[i] = -1;
				k--;
			}
		}

		int count = 0;
		for (int i = 0; i < roads.length; i++) {
			if (roads[i] != 0)
				count++;
			else {
				max = Math.max(max, count);
				count = 0;
			}
		}
		max = Math.max(max, count);

		for (int i = 0; i < roads.length; i++) {
			if (roads[i] == -1)
				roads[i] = 0;
		}
	}

}
