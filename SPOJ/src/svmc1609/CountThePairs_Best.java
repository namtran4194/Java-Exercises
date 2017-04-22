package svmc1609;

import java.util.Scanner;

/** O(nlogn) */
public class CountThePairs_Best {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		/* Best if reading the input with the others\SuperFastIO */
		int n = sc.nextInt();
		int k = sc.nextInt();
		int nums[] = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		combSort(nums, n);

		int count = 0;
		for (int i = 0; i < n; i++) {
			int value = nums[i] + k;
			if (binarySearch(nums, i, n - 1, value) != -1) {
				count++;
			}
		}

		System.out.println(count);
		sc.close();
	}

	static void combSort(int a[], int n) {
		int gap = n;
		float shrink = 1.3f;
		boolean swapped = true;

		while (gap != 1 || swapped) {
			gap = (int) (gap / shrink);
			if (gap < 1)
				gap = 1;
			swapped = false;

			int i = 0;
			while (i + gap < n) {
				if (a[i] > a[i + gap]) {
					int temp = a[i];
					a[i] = a[i + gap];
					a[i + gap] = temp;

					swapped = true;
				}
				i++;
			}
		}
	}

	static int binarySearch(int[] array, int low, int high, int value) {
		if (high >= low) {
			int mid = (low + high) / 2;
			if (value == array[mid]) {
				return mid;
			}
			if (value > array[mid]) {
				return binarySearch(array, mid + 1, high, value);
			} else {
				return binarySearch(array, low, mid - 1, value);
			}
		}
		return -1;
	}

}
