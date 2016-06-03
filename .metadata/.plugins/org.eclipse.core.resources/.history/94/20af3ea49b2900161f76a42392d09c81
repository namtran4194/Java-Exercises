package hust.namtran.Algorithms;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println(gcd(11, 11));
	}

	/*
	 * Tìm ước chung lớn nhất
	 */
	private static int gcd(int a, int b) {
		int upper, lower;
		int temp;

		if (a >= b) {
			upper = a;
			lower = b;
		} else {
			upper = b;
			lower = a;
		}

		while (lower != 0) {
			temp = lower;
			lower = upper % lower;
			upper = temp;
		}

		return upper;
	}

}
