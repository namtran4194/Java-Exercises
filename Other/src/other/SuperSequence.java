package other;

public class SuperSequence {

	public static void main(String[] args) {
		String s1 = "ananas";
		String s2 = "banana";
		System.out.println(shortestCommonSequence(s1, s2, longestCommonSubsequence(s1, s2)));
	}

	static char[] shortestCommonSequence(String s1, String s2, char[] lcs) {
		int n = s1.length();
		int m = s2.length();
		int leng = n + m - lcs.length;

		char[] scs = new char[leng];
		int i = 0, j = 0, k = 0;
		int index = 0;
		while (i < n && j < m && k < lcs.length) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(j);

			if (c1 == c2) {
				scs[index++] = c1;
				i++;
				j++;
				k++;
			} else if (c1 == lcs[k]) {
				scs[index++] = c2;
				j++;
			} else if (c2 == lcs[k]) {
				scs[index++] = c1;
				i++;
			} else {
				scs[index++] = c1;
				scs[index++] = c2;
				i++;
				j++;
			}
		}

		while (i < n)
			scs[index++] = s1.charAt(i++);

		while (j < m)
			scs[index++] = s2.charAt(j++);

		return scs;
	}

	static char[] longestCommonSubsequence(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();

		int[][] length = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 || j == 0)
					length[i][j] = 0;
				else if (s1.charAt(i - 1) == s2.charAt(j - 1))
					length[i][j] = 1 + length[i - 1][j - 1];
				else
					length[i][j] = Math.max(length[i - 1][j], length[i][j - 1]);
			}
		}

		int index = length[n][m];
		char[] lcs = new char[index];
		int i = n, j = m;
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				lcs[--index] = s1.charAt(--i);
				j--;
			} else if (length[i - 1][j] > length[i][j - 1])
				i--;
			else
				j--;
		}
		return lcs;
	}
}
