package hust.namtran.FlowControls;

public class PrintPattern {

	public static void main(String[] args) {
		int triSize = 8;
		int size = 7;
		/*
		 * print a square triangle
		 */
		for (int row = 1; row <= triSize; row++) {
			for (int col = 1; col <= row; col++) {
				System.out.print("#");
				if (col != row)
					System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		/*
		 * print a round
		 */
		for (int row = 1; row <= size; row++) {
			if (row == 1 || row == size) {
				for (int col = 1; col <= size; col++) {
					System.out.print("#");
					if (col != size) {
						System.out.print(" ");
					}
				}
			} else
				for (int col = 1; col <= size; col++) {
					if (col == 1 || col == size) {
						System.out.print("#");
						if (col != size) {
							System.out.print(" ");
						}
					} else {
						System.out.print("  ");
					}
				}
			System.out.println();
		}
		System.out.println();
		/*
		 * print a reverse Z
		 */
		for (int row = 1; row <= size; row++) {
			if (row == 1 || row == size) {
				for (int col = 1; col <= size; col++) {
					System.out.print("#");
					if (col != size) {
						System.out.print(" ");
					}
				}
				System.out.println();
			} else {
				for (int col = 1; col <= size; col++) {
					if (col == row) {
						System.out.print("#");
					} else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}
		System.out.println();
		/*
		 * print a 8
		 */
		for (int row = 1; row <= size; row++) {
			if (row == 1 || row == size) {
				for (int col = 1; col <= size; col++) {
					System.out.print("#");
					if (col != size) {
						System.out.print(" ");
					}
				}
				System.out.println();
			} else {
				for (int col = 1; col <= size; col++) {
					if (col == row || col == (size - row + 1)) {
						System.out.print("#");
						if (col != size) {
							System.out.print(" ");
						}
					} else if (col == 1 || col == size) { // delete block to print the number 8
						System.out.print("#");
						if (col != size) {
							System.out.print(" ");
						}
					} else {
						System.out.print("  ");
					}
				}
				System.out.println();
			}
		}
	}

}
