package others;

import java.io.FileInputStream;
import java.util.Scanner;

/**time limit exceed*/
public class AlicesCube {

	static int[][] hypercubes = { { 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
			{ 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, { 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0 },
			{ 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 },
			{ 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0 },
			{ 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0 }, };

	static int[][] minDistance = new int[16][16];
	static int[] in;
	static int moves;

	static int[] queue = new int[16];
	static int front, rear;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		precompute();

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			// read input
			in = new int[16];
			int count = 0;

			for (int i = 0; i < in.length; i++) {
				int v = sc.nextInt();
				if (i < 8 && v == 1) {
					count++;
					in[i]++;
				} else if (i >= 8 && v == 0) {
					count++;
					in[i]++;
				}
			}

			int[] order = new int[count];
			if (count > 0) {
				int j = 0;
				for (int i = 0; i < in.length; i++) {
					if (i < 8 && in[i] > 0)
						order[j++] = i;
					else if (i >= 8 && in[i] > 0)
						order[j++] = i;
				}
			}

			// calculate
			moves = Integer.MAX_VALUE;
			if (count > 0) {
				int[] out = new int[count];
				Try(order, out, 0);
			}

			// print
			if (moves == Integer.MAX_VALUE)
				moves = 0;
			System.out.println("Case #" + t + ": " + (moves < 4 ? moves : "more"));
		}
		sc.close();
	}

	static void Try(int[] order, int[] out, int k) {
		if (k == order.length) {
			int temp = 0;
			for (int i = 0; i < out.length; i++)
				temp += minDistance[out[i++]][out[i]];
			moves = Math.min(moves, temp);
			return;
		}

		for (int i = 0; i < order.length; i++)
			if (in[order[i]] > 0) {
				out[k] = order[i];
				in[order[i]]--;
				Try(order, out, k + 1);
				in[order[i]]++;
			}
	}

	static void precompute() {
		for (int i = 0; i < 16; i++)
			for (int j = i + 1; j < 16; j++) {
				minDistance[i][j] = BFS(i, j);
				minDistance[j][i] = minDistance[i][j];
			}
	}

	static int BFS(int start, int end) {
		int[] check = new int[16];
		front = rear = -1;
		queue[++rear] = start;

		check[start] = 1;
		while (front != rear) {
			int u = queue[++front];
			int value = check[u];
			for (int v = 0; v < 16; v++) {
				if (check[v] == 0 && hypercubes[u][v] == 1)
					if (v != end) {
						check[v] = value + 1;
						queue[++rear] = v;
					} else
						return value;
			}
		}
		return -1;
	}

}
