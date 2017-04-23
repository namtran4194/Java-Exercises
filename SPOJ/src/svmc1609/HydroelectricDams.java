package svmc1609;

import java.util.Scanner;

/**
 * Tìm max bên trái và bên phải. Sau đó tìm giá trị nhỏ hơn trong 2 giá trị đó.
 * Rồi tính thể tích nước tại block đó.
 * */
public class HydroelectricDams {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int blocks[] = new int[n];
			int currentRightMaxIdx = 0;
			for (int i = 0; i < n; i++) {
				blocks[i] = sc.nextInt();
				currentRightMaxIdx = getMaxIdx(blocks, currentRightMaxIdx, i);
			}

			int currentLeftMax = -1;
			int volumn = 0;
			for (int i = 1; i < n - 1; i++) {
				currentLeftMax = getMax(currentLeftMax, blocks[i - 1]);
				if (i > currentRightMaxIdx) {
					currentRightMaxIdx = getMaxRightIdx(blocks, i + 1);
				}
				int minHigh = getMinHigh(currentLeftMax, blocks[currentRightMaxIdx]);
				volumn += minHigh - blocks[i] > 0 ? minHigh - blocks[i] : 0;
			}

			System.out.println(volumn);
		}

		sc.close();
	}

	static int getMinHigh(int a, int b) {
		return a < b ? a : b;
	}

	static int getMaxRightIdx(int[] a, int i) {
		int maxIdx = i;
		for (int j = i + 1; j < a.length; j++) {
			maxIdx = getMaxIdx(a, j, maxIdx);
		}
		return maxIdx;
	}

	static int getMax(int a, int b) {
		return a > b ? a : b;
	}

	static int getMaxIdx(int[] a, int i, int j) {
		return a[i] > a[j] ? i : j;
	}

}
