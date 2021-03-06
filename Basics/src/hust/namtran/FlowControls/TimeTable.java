package hust.namtran.FlowControls;

public class TimeTable {

	public static void main(String[] args) {
		int size = 11;
		for (int row = 1; row <= size; row++) {
			if (row == 1) {
				System.out.print("* |");
				for (int col = 1; col <= 9; col++) {
					System.out.print("  " + col);
				}
				System.out.println();
			} else if (row == 2) {
				System.out.println("------------------------------");
			} else {
				int result;
				System.out.print(row - 2 + " |");
				for (int col = 1; col <= 9; col++) {
					result = (row - 2) * col;
					if (result <= 9) {
						System.out.print("  " + result);
					}else {
						System.out.print(" " + result);
					}
				}
				System.out.println();
			}
		}
	}

}
