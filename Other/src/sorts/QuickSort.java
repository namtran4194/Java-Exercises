package sorts;

/**
 * Độ phức tạp thuật toán: trung bình O(nlogn) - xấu nhất: O(n^2)
 * Là thuật toán chia để trị. Chọn một phần tử là "pivot" và chia mảng quanh
 * phần tử pivot đó. Cho tất cả phần tử nhỏ hơn(nhỏ hơn pivot) về trước pivot,
 * các phần tử lớn hơn (lớn hơn pivot) về sau pivot.
 */
public class QuickSort {

	public static void main(String[] args) {
		int[] a = { 1, 5, 3, 13, 9, 31, 4 };
		sort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public static void sort(int a[], int left, int right) {
		int i = left, j = right;
		int pivot = (left + right) / 2;
		do {
			// tìm phần tử lớn hơn pivot nhưng lại nằm bên trái pivot
			while (a[i] < a[pivot])
				i++;
			// tìm phần tử nhỏ hơn pivot nhưng lại nằm bên phải pivot
			while (a[j] > a[pivot])
				j--;
			// Hoán đổi i và j
			if (i <= j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				i++;
				j--;
			}
		} while (i <= j);

		if (left < j) {
			sort(a, left, j);
		}
		if (right > i) {
			sort(a, i, right);
		}
	}
}
