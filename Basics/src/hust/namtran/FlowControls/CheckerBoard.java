package hust.namtran.FlowControls;

public class CheckerBoard {
	/*
	 * Print checkerboard pattern using nested-loop
	 */
	public static void main(String[] args) {
		int size = 7;
		for (int row = 1; row <= size; row++) {
			if (row % 2 == 0) { // row 2, 4, 6, 8
				for (int col = 1; col <= size; ++col) {
					System.out.print(" #");
				}
				System.out.println();
			} else {
				for (int col = 1; col <= size; ++col) {
					System.out.print("#");
					if (col != size) { // print a space
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		}

	}
}
