package hust.namtran.StringandChar;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		String input;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a string: ");
		input = in.next();
		// Display
		System.out.print("The reverse of the String " + input + " is ");
		for (int i = input.length() - 1; i >= 0; i--) {
			System.out.print(input.charAt(i));
		}
		in.close();
	}

}
