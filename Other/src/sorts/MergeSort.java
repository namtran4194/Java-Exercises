package sorts;

/** Độ phức tạp thuật toán: trung bình O(nlogn) */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = { 4, 2, 5, 10, 7, 1, 15, 74, 13, 21 };
		sort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public static void sort(int array[], int left, int right) {
		if (left < right) {
			int middle = (left + right) / 2;
			// Sắp xếp nửa thứ nhất và thứ hai
			sort(array, left, middle);
			sort(array, middle + 1, right);
			// Ghép hai nửa đã đc sắp xếp
			merge(array, left, right);
		}
	}

	static void merge(int array[], int left, int right) {
		int mid = (left + right) / 2;
		// Find sizes of two subarrays to be merged
		int n1 = mid - left + 1;
		int n2 = right - mid;
		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];
		/*Copy data to temp arrays*/
		for (int i = 0; i < n1; i++)
			L[i] = array[left + i];
		for (int i = 0; i < n2; i++)
			R[i] = array[mid + 1 + i];

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;
		// Initial index of merged subarry array
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				i++;
			} else {
				array[k] = R[j];
				j++;
			}
			k++;
		}
		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			array[k] = L[i];
			i++;
			k++;
		}
		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			array[k] = R[j];
			j++;
			k++;
		}
	}

}
