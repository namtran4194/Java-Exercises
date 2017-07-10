package other;

import java.io.InputStream;

public class Solution {

	static int[] numbers;
	static int numberOfBlocks;
	static int result;

	public static void main(String[] args) throws Exception {
		int T = readInt(System.in);
		for (int t = 1; t <= T; t++) {
			int n = readInt(System.in);
			numberOfBlocks = readInt(System.in);

			int sum = 0;
			numbers = new int[n];
			for (int i = 0; i < n; i++) {
				numbers[i] = readInt(System.in);
				sum += numbers[i];
			}

			Try(sum / numberOfBlocks, sum);

			int temp = 0;
			for (int i = 0; i < n; i++) {
				temp += numbers[i];
				if (temp > result) {
					System.out.print("/ ");
					i--;
					temp = 0;
				} else
					System.out.print(numbers[i] + " ");
			}
			System.out.println();
		}
	}

	public static int readInt(InputStream in) throws Exception {
		int result = 0;
		boolean dig = false;

		// read a byte (8bit) and transform it to number

		for (int c = 0; (c = in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				result = result * 10 + c - '0';
			} else if (dig)
				break;
		}

		return result;
	}

	static void Try(int left, int right) {
		int mid = (left + right) / 2;
		if (mid == left || mid == right)
			return;
		result = mid;
		if (isDivided(mid))
			Try(left, mid);
		else
			Try(mid, right);
	}

	static boolean isDivided(int value) {
		int sum = 0;
		int blocks = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > value)
				return false;
			sum += numbers[i];
			if (sum >= value) {
				blocks++;
				if (sum > value)
					i--;
				sum = 0;
			} else if (i == numbers.length - 1 && sum != 0)
				blocks++;
		}
		return blocks <= numberOfBlocks;
	}

}
