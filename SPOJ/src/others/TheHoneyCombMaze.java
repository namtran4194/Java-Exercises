package others;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Nếu k == 0, 1, 2 thì chỉ cần 1 trong 4 vị trí xung quanh đi được thì output
 * là true. Nếu vị trí bắt đầu là dấu '*'(không đi vào đc) thì output luôn
 * false.
 */
public class TheHoneyCombMaze {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int xstart;
	static int ystart;

	static boolean isPossible;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		// read input
		boolean canMakeIt = true;
		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		if (n == 1 && m == 1)
			canMakeIt = false;

		int[][] maze = new int[n][m];
		int k = Integer.parseInt(sc.next());

		xstart = Integer.parseInt(sc.next()) - 1;
		ystart = Integer.parseInt(sc.next()) - 1;

		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				if (sc.next().equals("*"))
					maze[i][j] = 0;
				else
					maze[i][j] = 1;
			}

		// calculate
		if (maze[xstart][ystart] == 0)
			canMakeIt = false;

		if (canMakeIt && k < 3) {
			canMakeIt = false;
			for (int i = 0; i < 4; i++) {
				int xx = xstart + dx[i];
				int yy = ystart + dy[i];

				if (xx >= 0 && yy >= 0 && xx < maze.length && yy < maze[0].length)
					if (maze[xx][yy] == 1)
						canMakeIt = true;
			}
		}

		isPossible = false;
		if (k > 2 && canMakeIt) {
			maze[xstart][ystart] = 0;
			Try(maze, xstart, ystart, k);
		} else
			isPossible = canMakeIt;

		// print
		System.out.println(isPossible ? "YES" : "NO");
		sc.close();
	}

	static void Try(int[][] maze, int x, int y, int k) {
		if (k == 0) {
			if (!isPossible && x == xstart && y == ystart)
				isPossible = true;
			return;
		}

		for (int i = 0; i < 4 && !isPossible; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];

			if (xx >= 0 && yy >= 0 && xx < maze.length && yy < maze[0].length)
				if (maze[xx][yy] == 1) {
					maze[xx][yy] = 0;
					Try(maze, xx, yy, k - 1);
					maze[xx][yy] = 1;
				} else if (k == 1 && xx == xstart && yy == ystart)
					Try(maze, xx, yy, k - 1);
		}
	}

}
