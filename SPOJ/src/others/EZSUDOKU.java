package others;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/** time limit exceed */
public class EZSUDOKU {

	static int[][] rows;
	static int[][] cols;
	static int[][] boxes;
	static boolean isPossible;
	static int count;

	static Point[] point;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// read input
			rows = new int[8][9];
			cols = new int[8][9];
			boxes = new int[4][9];
			point = new Point[64];

			int[][] board = new int[8][8];
			boolean canMakeIt = true;
			count = 0;
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					int value = sc.nextInt();
					if (canMakeIt) {
						rows[i][value]++;
						cols[j][value]++;
						board[i][j] = value;
						boxes[getBoxIndex(i, j)][value]++;
						if (value > 0)
							if (rows[i][value] > 1 && cols[j][value] > 1 && boxes[getBoxIndex(i, j)][value] > 2)
								canMakeIt = false;
						if (value == 0)
							point[count++] = new Point(i, j);
					}
				}
			}

			// calculate
			isPossible = false;
			if (canMakeIt)
				Try(board, 0);

			// print
			System.out.println(String.valueOf("Test case #" + t + ": " + (isPossible ? "YES" : "NO")));
			if (isPossible) {
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++)
						System.out.print(String.valueOf((board[i][j] + " ")));
					System.out.println();
				}
			}
		}
		sc.close();
	}

	static void Try(int[][] board, int x, int y) {
		if (x == 8) {
			if (!isPossible) {
				boolean possible = true;
				for (int index = 0; index < board.length && possible; index++)
					for (int i = 1; i <= 8; i++)
						if (rows[index][i] > 1 && cols[index][i] > 1)
							possible = false;

				if (possible) {
					for (int k = 0; k < 4 && possible; k++)
						for (int i = 1; i <= 8; i++)
							if (boxes[k][i] > 2)
								possible = false;
				}

				isPossible = possible;
			}
			return;
		}

		if (board[x][y] == 0) {
			for (int i = 1; i <= 8 && !isPossible; i++)
				if (rows[x][i] == 0 && cols[y][i] == 0 && boxes[getBoxIndex(x, y)][i] < 2) {
					board[x][y] = i;
					rows[x][i]++;
					cols[y][i]++;
					boxes[getBoxIndex(x, y)][i]++;

					if (y == 7)
						Try(board, x + 1, 0);
					else
						Try(board, x, y + 1);

					if (!isPossible) {
						board[x][y] = 0;
						rows[x][i]--;
						cols[y][i]--;
						boxes[getBoxIndex(x, y)][i]--;
					}
				}
		} else {
			if (y == 7)
				Try(board, x + 1, 0);
			else
				Try(board, x, y + 1);
		}
	}

	static int readInt(InputStream in) throws IOException {
		int result = 0;
		boolean dig = false;

		for (int c = 0; (c = in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				result = result * 10 + c - '0';
			} else if (dig)
				break;
		}
		return result;
	}

	static int getBoxIndex(int i, int j) {
		int k;
		if (i < 4 && j < 4)
			k = 0;
		else if (i < 4 && j < 8)
			k = 1;
		else if (i < 8 && j < 4)
			k = 2;
		else
			k = 3;
		return k;
	}

	static void Try(int[][] board, int pos) {
		if (pos == count) {
			isPossible = true;
			return;
		}

		int x = point[pos].x;
		int y = point[pos].y;
		for (int i = 1; i <= 8 && !isPossible; i++)
			if (rows[x][i] == 0 && cols[y][i] == 0 && boxes[getBoxIndex(x, y)][i] < 2) {
				board[x][y] = i;
				rows[x][i]++;
				cols[y][i]++;
				boxes[getBoxIndex(x, y)][i]++;

				Try(board, pos + 1);

				if (!isPossible) {
					board[x][y] = 0;
					rows[x][i]--;
					cols[y][i]--;
					boxes[getBoxIndex(x, y)][i]--;
				}
			}
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}