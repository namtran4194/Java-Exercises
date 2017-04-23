package svmc1609;

import java.util.Scanner;

/**
 * QKP - spoj
 */
public class QueensKnightPawns {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int b = 0;
		while (sc.hasNext()) {
			int n = sc.nextInt(); // rows
			int m = sc.nextInt(); // columns
			if (n == 0 && m == 0) {
				break;
			}
			b++;
			boolean board[][] = new boolean[n][m];
			boolean pawns[][] = new boolean[n][m];

			int numberOfQueens = sc.nextInt();
			for (int i = 0; i < numberOfQueens; i++) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				board[row][col] = true;
			}

			int numberOfKnights = sc.nextInt();
			for (int i = 0; i < numberOfKnights; i++) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				board[row][col] = true;
			}

			int numberOfPawns = sc.nextInt();
			for (int i = 0; i < numberOfPawns; i++) {
				int row = sc.nextInt();
				int col = sc.nextInt();
				pawns[row][col] = true;
			}

			int numberOfSafeSquares = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
				}
			}
			System.out.println("Board " + b + " has " + numberOfSafeSquares + " safe squares.");
		}

		sc.close();
	}

}
