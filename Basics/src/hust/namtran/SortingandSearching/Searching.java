package hust.namtran.SortingandSearching;

import java.util.Arrays;

public class Searching {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int key = 4;
		System.out.println(linearSearch(array, key));
		System.out.println(binarySearch(array, key, 0, array.length));
	}

	/*
	 * Linear Search is applied on the unsorted or unordered list when there are
	 * fewer elements in a list
	 */
	private static int linearSearch(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (key == array[i]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * Binary Search is applied on the sorted array or list, useful when there
	 * are large numbers of elements in an array
	 */
	private static boolean binarySearch(int[] array, int key, int fromId, int toId) {
		Arrays.sort(array); // sort array
		if (fromId > toId) // does not exist
			return false;
		if (fromId == toId - 1) { // one-element list
			if (key == array[fromId])
				return true;
			else
				return false;
		} else {
			int middleId = (fromId + toId) / 2;
			if (key == array[middleId])
				return true;
			else if (key < array[middleId])
				toId = middleId;
			else
				fromId = middleId + 1;
			return binarySearch(array, key, fromId, toId);
		}
	}

}
