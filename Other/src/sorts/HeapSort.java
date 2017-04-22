package sorts;

/** Độ phức tạp thuật toán O(nlogn) */
public class HeapSort {

	public static void main(String[] args) {
		int[] a = { 1, 5, 3, 13, 9, 31, 4 };
		heapSort(a, a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	static void heapSort(int a[], int n) {
		// Build heap (Xây dựng Heap sao cho mọi nút cha luôn lớn hơn nút con trên cây)
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(a, n, i);
		}

		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			swap(a, 0, i);
			// Call max heapify on the reduced heap
			heapify(a, i, 0);
		}
	}

	// Hoán vị nút cha thứ i phải lớn hơn nút con
	static void heapify(int a[], int n, int i) {
		// Vị trí con trái, phải của i
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int largest = i; // Initialize largest as root
		// If left child is larger than root
		if (left < n && a[left] > a[i]) {
			largest = left;
		}
		// If right child is larger than largest so far
		if (right < n && a[right] > a[largest]) {
			largest = right;
		}
		// If largest is not root
		if (i != largest) {
			swap(a, i, largest);
			// Recursively heapify the affected sub-tree
			heapify(a, n, largest);
		}
	}

	static void swap(int a[], int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}
