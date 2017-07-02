package other;

public class Solution {
	public static void main(String[] args) {
		int[] a = new int[3];
		int n = 2;
		a[++n % 2] = 1;
		System.out.println(n);
	}
}
