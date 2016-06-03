package hust.namtran.Method;

import java.util.Scanner;

public class OddTest {

	public static void main(String[] args) {
		int number;

		Scanner in = new Scanner(System.in);
		System.out.print("Enter a integer number: ");
		number = in.nextInt();
		if (number >= 0) {
			if (isOdd(number)) {
				System.out.println("ODD");
			} else {
				System.out.println("EVEN");
			}
		} else {
			System.out.println("error: please enter a positive number");
		}
		in.close();
	}

	public static boolean isOdd(int number) {
		boolean result = false;
		if (number % 2 == 1) {
			result = true;
		}
		return result;
	}

}
