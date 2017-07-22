package others;

import java.io.FileInputStream;
import java.util.Scanner;

public class SpikyMazes {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static int EMPTY = 0;
	static int WALL = 1;
	static int ENTRANCE = 2;
	static int SPIKES = 3;
	static int TREASURE = 4;

	static boolean isPossible;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		int k = Integer.parseInt(sc.next()) / 2;

		int[][] maze = new int[n][m];
		int xstart = -1;
		int ystart = -1;
		for (int i = 0; i < n; i++) {
			char[] cs = sc.next().toCharArray();
			for (int j = 0; j < m; j++) {
				char c = cs[j];
				maze[i][j] = charToInt(c);
				if (maze[i][j] == ENTRANCE) {
					xstart = i;
					ystart = j;
				}
			}
		}

		// calculate
		isPossible = false;
		Try(maze, k, xstart, ystart);

		// print
		System.out.println(isPossible ? "SUCCESS" : "IMPOSSIBLE");
		sc.close();
	}

	static void Try(int[][] maze, int spikes, int x, int y) {
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];

			if (xx >= 0 && yy >= 0 && xx < maze.length && yy < maze[0].length)
				if (maze[xx][yy] == EMPTY || maze[xx][yy] == SPIKES) {
					if (maze[xx][yy] == SPIKES)
						if (spikes <= 0)
							continue;
						else
							spikes--;
					maze[xx][yy] = WALL;

					Try(maze, spikes, xx, yy);

					maze[xx][yy] = EMPTY;
					if (maze[xx][yy] == SPIKES)
						spikes++;
				} else if (maze[xx][yy] == TREASURE) {
					isPossible = true;
					return;
				}
		}
	}

	static int charToInt(char c) {
		switch (c) {
		case '.':
			return EMPTY;
		case '#':
			return WALL;
		case '@':
			return ENTRANCE;
		case 's':
			return SPIKES;
		case 'x':
			return TREASURE;
		}
		return -1;
	}

}
