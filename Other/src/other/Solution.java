package other;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(sc.nextLine());

			char[][] input = new char[n][n];
			for (int i = 0; i < n; i++) {
				char[] cs = sc.nextLine().toCharArray();
				for (int j = 0; j < n; j++)
					input[i][j] = cs[j];
			}

			int maxLen = 0;
			// canning rows
			for (int row = 0; row < n; row++)
				for (int i = 1; i < n - 1; i++) {
					int currentLen = 0;
					// even length
					int left = i;
					int right = i + 1;
					currentLen = palindromeInRow(input, n, row, left, right);
					maxLen = Math.max(maxLen, currentLen);

					// odd length
					left = i - 1;
					right = i + 1;
					currentLen = palindromeInRow(input, n, row, left, right) + 1;
					maxLen = Math.max(maxLen, currentLen);
				}

			// scanning columns
			for (int col = 0; col < n; col++)
				for (int i = 1; i < n - 1; i++) {
					int currentLen = 0;
					// even length
					int left = i;
					int right = i + 1;
					currentLen = palindromeInColumn(input, n, col, left, right);
					maxLen = Math.max(maxLen, currentLen);
					// odd length
					left = i - 1;
					right = i + 1;
					currentLen = palindromeInColumn(input, n, col, left, right) + 1;
					maxLen = Math.max(maxLen, currentLen);
				}

			// print
			System.out.println("#" + t + " " + maxLen);
		}
		sc.close();
	}

	static int palindromeInRow(char[][] input, int n, int row, int left, int right) {
		int currentLen = 0;
		while (left >= 0 && right < n) {
			if (input[row][left--] == input[row][right++])
				currentLen += 2;
			else
				break;
		}
		return currentLen;
	}

	static int palindromeInColumn(char[][] input, int n, int col, int left, int right) {
		int currentLen = 0;
		while (left >= 0 && right < n) {
			if (input[left--][col] == input[right++][col])
				currentLen += 2;
			else
				break;
		}
		return currentLen;
	}

}
