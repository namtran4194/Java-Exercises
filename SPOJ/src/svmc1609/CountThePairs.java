package svmc1609;

import java.util.Scanner;

/** O(nlogn) */
public class CountThePairs {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int nums[] = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}

		heapSort(nums, n);

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

	static void heapSort(int a[], int n) {
		buildHeap(a, n);
		for (int i = n - 1; i > 0; i--) {
			swap(a, 0, i);
			heapify(a, i, 0);
		}
	}

	// Xây dựng Heap sao cho mọi nút cha luôn lớn hơn nút con trên cây
	static void buildHeap(int a[], int n) {
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(a, n, i);
		}
	}

	// Hoán vị nút cha thứ i phải lớn hơn nút con
	static void heapify(int a[], int n, int i) {
		int left = 2 * (i + 1) - 1;
		int right = 2 * (i + 1);
		int max;
		if (left < n && a[left] > a[i]) {
			max = left;
		} else {
			max = i;
		}
		if (right < n && a[right] > a[max]) {
			max = right;
		}
		if (i != max) {
			swap(a, i, max);
			heapify(a, n, max);
		}
	}

	static void swap(int a[], int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	static int binarySearch(int[] array, int low, int high, int value) {
		if (high >= low) {
			int mid = low + (high - low) / 2;
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
