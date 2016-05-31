package hust.namtran.FlowControls;

public class SquareBoard {
	/*
	 * Print square pattern using nested-loop
	 */
	public static void main(String[] args) {
		int size = 5; // size of the board
		for (int row = 1; row <= size; ++row) {
			for (int col = 1; col <= size; ++col) {
				System.out.print("#");
				if (col != size) { // print a space
					System.out.print(" ");
				}
			}
			// Print a newline after all the columns
			System.out.println();
		}
	}

}
