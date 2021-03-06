package hust.namtran.Array;

public class ContainandSearch {

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		int key = 1;

		// test contains
		if (contains(array, key)) {
			System.out.println("TRUE");
		} else {
			System.out.println("FALSE");
		}

		// test search
		System.out.println("Position: " + search(array, key));
	}

	public static boolean contains(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (key == array[i]) {
				return true;
			}
		}
		return false;
	}

	public static int search(int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (key == array[i]) {
				return i;
			}
		}
		return -1;
	}

}
