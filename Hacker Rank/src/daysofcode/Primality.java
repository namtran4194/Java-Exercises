package daysofcode;

public class Primality {

	/**
	 * Worst: O(n) algorithm
	 */
	static boolean isPrimeWorst(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Worst: O(n) algorithm
	 */
	static boolean isPrimeLessWorst(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Best: O( n^(1/2) ) Algorithm
	 */
	static boolean isPrimeGood(int n) {
		if (n == 1) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Best: O( n^(1/2) ) Algorithm
	 */
	static boolean isPrimeBest(int n) {
		if (n == 2) {
			return true;
		} else if (n == 1 || (n & 1) == 0) {
			return true;
		}
		for (int i = 3; i * i <= n; i += 2) { // i <= Math.sqrt(n)
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
