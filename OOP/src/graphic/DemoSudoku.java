package graphic;

import java.util.Random;
import java.util.Scanner;

/**
 * Test Sudoku game, puzzle generation
 * */
public class DemoSudoku {
	public static void main(String[] args) {
		int p = 1;
		Random r = new Random();
		int firstval = r.nextInt(8);
		while (p == 1) {
			int x = firstval, v = 1;
			int a[][] = new int[9][9];
			int b[][] = new int[9][9];
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if ((x + j + v) <= 9)
						a[i][j] = j + x + v;
					else
						a[i][j] = j + x + v - 9;
					if (a[i][j] == 10)
						a[i][j] = 1;
					System.out.print(a[i][j] + " ");
				}
				x += 3;
				if (x >= 9)
					x = x - 9;
				System.out.println();
				if (i == 2) {
					v = 2;
					x = firstval;
				}
				if (i == 5) {
					v = 3;
					x = firstval;
				}

			}
			int eorh;
			Scanner in = new Scanner(System.in);
			System.out.println(
					"hey lets play a game of sudoku:take down the question and replace the 0's with your digits and complete the game by re entering your answer");
			System.out.println("enter your option 1.hard  2.easy");
			eorh = in.nextInt();
			switch (eorh) {
			case 1:
				b[0][0] = a[0][0];
				b[8][8] = a[8][8];
				b[0][3] = a[0][3];
				b[0][4] = a[0][4];
				b[1][2] = a[1][2];
				b[1][3] = a[1][3];
				b[1][6] = a[1][6];
				b[1][7] = a[1][7];
				b[2][0] = a[2][0];
				b[2][4] = a[2][4];
				b[2][8] = a[2][8];
				b[3][2] = a[3][2];
				b[3][8] = a[3][8];
				b[4][2] = a[4][2];
				b[4][3] = a[4][3];
				b[4][5] = a[4][5];
				b[4][6] = a[4][6];
				b[5][0] = a[5][0];
				b[5][6] = a[5][6];
				b[6][0] = a[6][0];
				b[6][4] = a[6][4];
				b[6][8] = a[6][8];
				b[7][1] = a[7][1];
				b[7][2] = a[7][2];
				b[7][5] = a[7][5];
				b[7][6] = a[7][6];
				b[8][4] = a[8][4];
				b[8][5] = a[8][5];
				b[0][0] = a[0][0];
				b[8][8] = a[8][8];

				break;
			case 2:
				b[0][3] = a[0][3];
				b[0][4] = a[0][4];
				b[1][2] = a[1][2];
				b[1][3] = a[1][3];
				b[1][6] = a[1][6];
				b[1][7] = a[1][7];
				b[1][8] = a[1][8];
				b[2][0] = a[2][0];
				b[2][4] = a[2][4];
				b[2][8] = a[2][8];
				b[3][2] = a[3][2];
				b[3][5] = a[3][5];
				b[3][8] = a[3][8];
				b[4][0] = a[4][0];
				b[4][2] = a[4][2];
				b[4][3] = a[4][3];
				b[4][4] = a[4][4];
				b[4][5] = a[4][5];
				b[4][6] = a[4][6];
				b[5][0] = a[5][0];
				b[5][1] = a[5][1];
				b[5][4] = a[5][4];
				b[5][6] = a[5][6];
				b[6][0] = a[6][0];
				b[6][4] = a[6][4];
				b[6][6] = a[6][6];
				b[6][8] = a[6][8];
				b[7][0] = a[7][0];
				b[7][1] = a[7][1];
				b[7][2] = a[7][2];
				b[7][5] = a[7][5];
				b[7][6] = a[7][6];
				b[8][2] = a[8][2];
				b[8][4] = a[8][4];
				b[8][5] = a[8][5];
				break;
			default:
				System.out.println("entered option is incorrect");
				break;
			}

			for (int y = 0; y < 9; y++) {
				for (int z = 0; z < 9; z++) {
					System.out.print(b[y][z] + " ");
				}
				System.out.println("");
			}
			System.out.println("enter your answer");
			int c[][] = new int[9][9];
			for (int y = 0; y < 9; y++) {
				for (int z = 0; z < 9; z++) {
					c[y][z] = in.nextInt();
				}
			}
			for (int y = 0; y < 9; y++) {
				for (int z = 0; z < 9; z++)
					System.out.print(c[y][z] + " ");
				System.out.println();
			}
			int q = 0;
			for (int y = 0; y < 9; y++) {
				for (int z = 0; z < 9; z++)
					if (a[y][z] == c[y][z])
						continue;
					else {
						q++;
						break;
					}
			}
			if (q == 0)
				System.out.println("the answer you have entered is correct well done");
			else
				System.out.println("oh  wrong answer better luck next time");
			System.out.println("do you want to play a different game of sudoku(1/0)");
			p = in.nextInt();
			firstval = r.nextInt(8);

			in.close();
			/*
			 * if (firstval > 8) firstval -= 9;
			 */
		}

	}
}
