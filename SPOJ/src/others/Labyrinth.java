package others;

import java.io.FileInputStream;
import java.util.Scanner;

/** Finding longest path: bfs twice */
public class Labyrinth {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static char[][] maze;
	static int[][] visited;
	static int maxLeng;
	static int xstart;
	static int ystart;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		while (t-- > 0) {
			int m = Integer.parseInt(sc.next());
			int n = Integer.parseInt(sc.next());

			xstart = 0;
			ystart = 0;
			maze = new char[n][m];
			for (int i = 0; i < n; i++) {
				char[] cs = sc.next().toCharArray();
				for (int j = 0; j < m; j++) {
					maze[i][j] = cs[j];
					if (cs[j] == '.') {
						xstart = i;
						ystart = j;
					}
				}
			}

			maxLeng = 0;
			bfs(xstart, ystart);
			findMax();

			bfs(xstart, ystart);
			findMax();

			System.out.println("Maximum rope length is " + (maxLeng - 1) + ".");
		}
		sc.close();
	}

	static void bfs(int x, int y) {
		int n = maze.length;
		int m = maze[0].length;

		visited = new int[n][m];
		int[][] queue = new int[n * m][2];
		int front = -1, rear = -1;
		// dfs
		queue[++rear][0] = x;
		queue[rear][1] = y;
		visited[x][y] = 1;
		while (front != rear) {
			int r = queue[++front][0];
			int c = queue[front][1];
			int leng = visited[r][c];

			for (int k = 0; k < 4; k++) {
				int xx = r + dx[k];
				int yy = c + dy[k];

				if (xx >= 0 && yy >= 0 && xx < n && yy < m && maze[xx][yy] == '.') {
					if (visited[xx][yy] == 0) {
						visited[xx][yy] = leng + 1;
						queue[++rear][0] = xx;
						queue[rear][1] = yy;
					}
				}
			}
		}
	}

	static void findMax() {
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze[0].length; j++)
				if (maxLeng < visited[i][j]) {
					maxLeng = visited[i][j];
					xstart = i;
					ystart = j;
				}
	}

}
