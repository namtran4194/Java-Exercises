package sorts;

/**
 * Độ phức tạp thuật toán O(n2)
 * Tìm vị trí phần tử nhỏ nhất trong subarray rồi cho lên đầu mảng.
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] a = { 1, 5, 3, 13, 9, 31, 4 };
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	// Sắp xếp giảm dần
	public static void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int minIdx = i; // lưu vị trí chứa phần tử lớn nhất
			// Tìm phần tử lớn nhất
			for (int j = i + 1; j < a.length; j++) {
				if (a[minIdx] > a[j]) {
					minIdx = j;
				}
			}
			// Nếu chỉ số đã thay đổi, ta sẽ hoán vị
			if (minIdx != i) {
				int temp = a[i];
				a[i] = a[minIdx];
				a[minIdx] = temp;
			}
		}
	}
}
