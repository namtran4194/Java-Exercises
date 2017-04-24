package svmc1609;

public class Temp {

	public static void main(String[] args) {
		int a[] = { 8, 4, 1, 56, 3, -44, 23, -6, 28, 0 };
		//		sort(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	static void sort(int[] a, int n) {
		int gap = n;
		float shrink = 1.3f;
		boolean swapped = true;

		while (gap > 1 || swapped) {
			gap = (int) (gap / shrink);
			if (gap < 1)
				gap = 1;

			swapped = false;
			int i = 0;
			while (i + gap < n) {
				if (a[i + gap] < a[i]) {
					int temp = a[i + gap];
					a[i] = a[i + gap];
					a[i + gap] = temp;
					swapped = true;
				}
				i++;
			}
		}
	}
}
