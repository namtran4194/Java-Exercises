package sorts;

/**
 * Độ phức tạp thuật toán: xấu nhất: O(n2) - trung bình: O(nlog(n)) - tốt nhất: O(n)
 */
public class CombSort {
	public static void main(String[] args) {
		int[] a = { 1, 3, 9, 6, 2 };
		sort(a, a.length);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public static void sort(int[] a, int n) {
		int gap = n;
		float shrink = 1.3f;
		boolean swapped = true;

		while (gap > 1 || swapped) {
			// Update the gap value for a next comb
			gap = (int) (gap / shrink);
			if (gap < 1)
				gap = 1;
			
			// Initialize swapped as false so that we can
			// check if swap happened or not
			swapped = false;

			// A single " comb " over the input list
			int i = 0;
			while (i + gap < n) {
				if (a[i] > a[i + gap]) {
					// swap
					int temp = a[i];
					a[i] = a[i + gap];
					a[i + gap] = temp;

					swapped = true;
				}
				i++;
			}
		}
	}
}
