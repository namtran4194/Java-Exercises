package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class MakingJumps {

	static int[] dx = { 2, 2, -2, -2, 1, 1, -1, -1 };
	static int[] dy = { 1, -1, 1, -1, 2, -2, 2, -2 };

	static int count;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = 1;
		while (true) {
			// read input
			int n = sc.nextInt();
			if (n == 0)
				break;

			int[][] board = new int[n][10];
			int numOfCells = 0;
			int ystart = -1;
			for (int i = 0; i < n; i++) {
				int start = sc.nextInt();
				int count = sc.nextInt();
				if (i == 0)
					ystart = start;
				for (int j = start; j < start + count; j++) {
					board[i][j] = 1;
					numOfCells++;
				}
			}
			// calculate
			count = 0;
			board[0][0] = 0;
			Try(board, 0, ystart, 1);

			// print
			System.out.print("Case " + (t++) + ", " + (numOfCells - count));
			if (numOfCells - count == 1)
				System.out.println(" square can not be reached.");
			else
				System.out.println(" squares can not be reached.");
		}
		sc.close();
	}

	static void Try(int[][] board, int x, int y, int moves) {
		for (int i = 0; i < 8; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];

			if (xx >= 0 && yy >= 0 && xx < board.length && yy < board[0].length)
				if (board[xx][yy] == 1) {
					board[xx][yy] = 0;
					Try(board, xx, yy, moves + 1);
					board[xx][yy] = 1;
				}
		}

		count = Math.max(count, moves);
	}

}
