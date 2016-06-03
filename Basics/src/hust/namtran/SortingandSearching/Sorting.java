package hust.namtran.SortingandSearching;

import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		int[] array = { 9, 6, 4, 1, 5 };
		bubbleSort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void bubbleSort(int[] array) {
		int size = array.length;
		int temp;
		boolean swapped;
		do {
			swapped = false;
			for (int i = 0; i < size - 1; i++) {
				if (array[i] > array[i + 1]) {
					temp = array[i];
					array[i] = array[i + 1];
					array[i + 1] = temp;
					swapped = true;
				}
			}
			--size;
		} while (swapped);
	}

}
