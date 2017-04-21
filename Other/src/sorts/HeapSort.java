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
		// Vị trí con trái, phải của i
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int positionOfMax;
		// Tìm vị trí của max(a[i], a[left])
		if (left < n && a[left] > a[i]) {
			positionOfMax = left;
		} else {
			positionOfMax = i;
		}
		// Tìm vị trí của max(a[positionOfMax], a[right])
		if (right < n && a[right] > a[positionOfMax]) {
			positionOfMax = right;
		}
		// Nếu max ko phải ở vị trí i thì swap
		if (i != positionOfMax) {
			swap(a, i, positionOfMax);
			heapify(a, n, positionOfMax);
		}
	}

	static void swap(int a[], int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
}
