package sorts;

/**
 * Độ phức tạp thuật toán O(n2)
 * Duyệt từ cuối lên đầu mảng, 2 phần tử cạnh nhau không đúng vị trí thì đổi
 * chỗ.
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] a = { 1, 5, 3, 13, 9, 31, 4 };
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	// Sắp xếp tăng dần
	public static void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			boolean swapped = false;
			for (int j = a.length - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					int temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
					swapped = true;
				}
			}
			if (!swapped) {
				break;
			}
		}
	}
}
