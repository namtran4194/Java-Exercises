package svmc1609;

import java.util.Scanner;

/**
 * QKP - spoj
 */
public class QueensKnightPawns {
	private static final boolean UNSAFE = true;
	private static final boolean SAFE = false;

	private static final int[] KNIGHT_MOVE_ROWS = { -1, -2, 1, 2, -2, -1, 2, 1 };
	private static final int[] KNIGHT_MOVE_COLS = { 2, 1, 2, 1, -1, -2, -1, -2 };

	private static final int[] QUEEN_MOVE_ROWS = { 0, 0, 1, 1, 1, -1, -1, -1 };
	private static final int[] QUEEN_MOVE_COLS = { 1, -1, -1, 0, 1, -1, 0, 1 };
	private static int n, m;

	private static boolean[][] queens, knights, pawns;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int b = 0;
		while (sc.hasNext()) {
			n = sc.nextInt(); // rows
			m = sc.nextInt(); // columns
			if (n == 0 && m == 0) {
				break;
			}
			b++;

			boolean board[][] = new boolean[n][m];
			queens = new boolean[n][m];
			knights = new boolean[n][m];
			pawns = new boolean[n][m];

			int numberOfQueens = sc.nextInt();
			for (int i = 0; i < numberOfQueens; i++) {
				int row = sc.nextInt() - 1;
				int col = sc.nextInt() - 1;
				board[row][col] = UNSAFE;
				queens[row][col] = UNSAFE;
			}

			int numberOfKnights = sc.nextInt();
			for (int i = 0; i < numberOfKnights; i++) {
				int row = sc.nextInt() - 1;
				int col = sc.nextInt() - 1;
				board[row][col] = UNSAFE;
				knights[row][col] = UNSAFE;
			}

			int numberOfPawns = sc.nextInt();
			for (int i = 0; i < numberOfPawns; i++) {
				int row = sc.nextInt() - 1;
				int col = sc.nextInt() - 1;
				board[row][col] = UNSAFE;
				pawns[row][col] = UNSAFE;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (knights[i][j] == UNSAFE)
						movingKnight(board, i, j);

					else if (queens[i][j] == UNSAFE)
						for (int k = 0; k < 8; k++)
							movingQueen(board, i, j, k);
				}
			}

			int numberOfSafeSquares = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (board[i][j] == SAFE)
						numberOfSafeSquares++;
				}
			}
			System.out.println("Board " + b + " has " + numberOfSafeSquares + " safe squares.");
		}

		sc.close();
	}

	static void movingKnight(boolean[][] board, int i, int j) {
		for (int k = 0; k < 8; k++) {
			int row = i + KNIGHT_MOVE_ROWS[k];
			int col = j + KNIGHT_MOVE_COLS[k];
			if (row >= 0 && row < n && col >= 0 && col < m) {
				if (board[row][col] == SAFE) {
					board[row][col] = UNSAFE;
				}
			}
		}
	}

	static void movingQueen(boolean[][] board, int i, int j, int direct) {
		int row = i + QUEEN_MOVE_ROWS[direct];
		int col = j + QUEEN_MOVE_COLS[direct];
		if (row >= 0 && row < n && col >= 0 && col < m) {
			if (board[row][col] == SAFE) {
				board[row][col] = UNSAFE;
				movingQueen(board, row, col, direct);
			} else {
				if (!isOccupied(row, col)) {
					movingQueen(board, row, col, direct);
				}
			}
		}
	}

	static boolean isOccupied(int i, int j) {
		return queens[i][j] == UNSAFE || knights[i][j] == UNSAFE || pawns[i][j] == UNSAFE;
	}
}
