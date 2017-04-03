package sorts;

/** Độ phức tạp thuật toán O(n2) */
public class InsertionSort {
	public static void main(String[] args) {
		int[] a = { 1, 5, 3, 13, 9, 31, 4 };
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	// Sắp xếp giảm dần
	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int index = i;
			while (index > 0 && a[index - 1] < a[index]) {
				int temp = a[index];
				a[index] = a[index - 1];
				a[index - 1] = temp;
				index--;
			}
		}
	}
}
