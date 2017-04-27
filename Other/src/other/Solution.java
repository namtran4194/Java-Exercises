package other;

public class Solution {
	public static void main(String[] args) {
		int a = 0;
		for (int i = 0; i < 1000; i++) {
			if (i % 2 == 0)
				a += i;
			else
				a -= i;
		}
		System.out.println(a);
	}
}
