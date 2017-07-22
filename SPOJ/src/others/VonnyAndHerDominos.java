package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class VonnyAndHerDominos {

	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	static boolean[][] dominos = new boolean[7][7];

	static int n = 7;
	static int m = 8;

	static int count;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		generateDominos();

		int t = sc.nextInt();
		while (t-- > 0) {
			// read input
			int[][] board = new int[n][m];
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					board[i][j] = sc.nextInt();

			// calculate
			count = 0;
			boolean[][] visited = new boolean[n][m];
			Try(board, visited, 0, 0);

			// print
			System.out.println(count);
		}
		sc.close();
	}

	static void Try(int[][] board, boolean visited[][], int x, int y) {
		if (x == n) {
			count++;
			return;
		}

		if (!visited[x][y]) {
			int v1 = board[x][y];
			for (int i = 0; i < 2; i++) {
				int xx = x + dx[i];
				int yy = y + dy[i];

				if (xx >= 0 && yy >= 0 && xx < n && yy < m)
					if (!visited[xx][yy]) {
						int v2 = board[xx][yy];
						if (v1 <= v2 && dominos[v1][v2]) {
							dominos[v1][v2] = false;
							visited[x][y] = visited[xx][yy] = true;

							if (y == m - 1)
								Try(board, visited, x + 1, 0);
							else
								Try(board, visited, x, y + 1);

							dominos[v1][v2] = true;
							visited[x][y] = visited[xx][yy] = false;
						} else if (dominos[v2][v1]) {
							dominos[v2][v1] = false;
							visited[x][y] = visited[xx][yy] = true;

							if (y == m - 1)
								Try(board, visited, x + 1, 0);
							else
								Try(board, visited, x, y + 1);

							dominos[v2][v1] = true;
							visited[x][y] = visited[xx][yy] = false;
						}
					}
			}
		} else {
			if (y == m - 1)
				Try(board, visited, x + 1, 0);
			else
				Try(board, visited, x, y + 1);
		}
	}

	static void generateDominos() {
		for (int i = 0; i < 7; i++)
			for (int j = i; j < 7; j++)
				dominos[i][j] = true;
	}

}
