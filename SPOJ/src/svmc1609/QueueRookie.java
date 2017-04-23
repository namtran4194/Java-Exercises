package svmc1609;

import java.io.IOException;
import java.io.InputStream;

public class QueueRookie {

	public static void main(String[] args) throws Exception {
		int t = readInt(System.in);
		while (t-- > 0) {
			int n = readInt(System.in);
			int[] heights = new int[n];
			int[] output = new int[n];

			int max = 0;
			for (int i = 0; i < n; i++) {
				heights[i] = readInt(System.in);
				max = max > heights[i] ? max : heights[i];
			}

			int[] positions = new int[max + 1];
			for (int i = 0; i < n; i++) {
				positions[heights[i]] = readInt(System.in);
			}

			combSort(heights, n);

			for (int i = 0; i < n; i++) {
				int pos = positions[heights[i]];

				for (int j = 0; j < n; j++) {
					if (pos == 0 && output[j] == 0) {
						output[j] = heights[i];
						break;
					}
					if (output[j] == 0)
						pos--;
				}
			}

			for (int i = 0; i < n; i++) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
		}
	}

	public static int readInt(InputStream in) throws IOException {
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

	static void combSort(int[] heights, int n) {
		int gap = n;
		float shrink = 1.3f;
		boolean swapped = true;

		while (gap > 1 || swapped) {
			gap = (int) (gap / shrink);
			if (gap < 1)
				gap = 1;

			swapped = false;
			int i = 0;
			while (i + gap < n) {
				if (heights[i] > heights[i + gap]) {
					// swap
					swap(heights, i, i + gap);

					swapped = true;
				}
				i++;
			}
		}
	}

	static void swap(int[] a, int i, int j) {
		a[i] ^= a[j];
		a[j] ^= a[i];
		a[i] ^= a[j];
	}
}
