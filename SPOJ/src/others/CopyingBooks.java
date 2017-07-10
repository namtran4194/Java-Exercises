package others;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class CopyingBooks {
	static int[] numbers;
	static int numberOfBlocks;

	public static void main(String[] args) throws Exception {
		BufferedInputStream in = new BufferedInputStream(System.in);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = readInt(in);
		while (T-- > 0) {
			int n = readInt(in);
			numberOfBlocks = readInt(in);

			int sum = 0;
			numbers = new int[n];
			for (int i = 0; i < n; i++) {
				numbers[i] = readInt(in);
				sum += numbers[i];
			}

			int result = Try(sum / numberOfBlocks, sum);

			int temp = 0;
			for (int i = 0; i < n; i++) {
				temp += numbers[i];
				if (temp > result) {
					writer.write("/ ");
					writer.flush();
					i--;
					temp = 0;
				} else {
					writer.write(String.valueOf(numbers[i] + " "));
					writer.flush();
				}
			}
			writer.write(System.lineSeparator());
			writer.flush();
		}
	}

	public static int readInt(InputStream in) throws Exception {
		int result = 0;
		boolean dig = false;

		for (int c = 0; (c = in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				result = result * 10 + c - '0';
			} else if (dig)
				break;
		}

		return result;
	}

	static int Try(int left, int right) {
		int mid = (left + right) / 2;
		if (mid == left) {
			return right;
		}
		if (isDivided(mid))
			return Try(left, mid);
		else
			return Try(mid, right);
	}

	static boolean isDivided(int value) {
		int sum = 0;
		int blocks = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > value)
				return false;
			sum += numbers[i];
			if (sum > value) {
				blocks++;
				i--;
				sum = 0;
			} else if (i == numbers.length - 1 && sum != 0)
				blocks++;
		}
		return blocks <= numberOfBlocks;
	}

}
