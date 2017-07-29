package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class HydroelectricDams {

	static int[] walls;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			walls = new int[n];
			for (int i = 0; i < walls.length; i++)
				walls[i] = sc.nextInt();

			int volumn = 0;
			int pos = 1;
			while (pos < n - 1) {
				int left = findMaxLeft(pos);
				int right = findMaxRight(pos);
				volumn += Math.min(walls[left], walls[right]) - walls[pos];
				pos++;
			}

			System.out.println(volumn);
		}
		sc.close();
	}

	static int findMaxLeft(int pos) {
		int maxId = 0;
		for (int i = 0; i <= pos; i++)
			if (walls[maxId] <= walls[i])
				maxId = i;
		return maxId;
	}

	static int findMaxRight(int pos) {
		int maxId = pos;
		for (int i = pos + 1; i < walls.length; i++)
			if (walls[maxId] <= walls[i])
				maxId = i;
		return maxId;
	}

}
