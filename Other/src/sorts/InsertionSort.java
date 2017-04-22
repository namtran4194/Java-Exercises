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

	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int key = a[i]; // Phần tử đầu tiên chưa sắp xếp
			int j = i - 1;
			// Dịch tất cả phần tử sang phải để tạo vị trí cho "key"
			while (j >= 0 && a[j] > key) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key; // Thêm phần tử chưa đc sắp xếp vào vị trí đúng
		}
	}
}
