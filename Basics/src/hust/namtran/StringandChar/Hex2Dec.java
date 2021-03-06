package hust.namtran.StringandChar;

import java.util.Scanner;

public class Hex2Dec {
	/*
	 * convert an input hex string into its equivalent decimal number
	 */
	public static void main(String[] args) {
		String hexStr;
		int decimal = 0;
		char hexChar;
		int order;

		System.out.print("Enter a Hexadecimal string: ");
		Scanner in = new Scanner(System.in);
		hexStr = in.next().toLowerCase();
		for (int i = 0; i < hexStr.length(); i++) {
			hexChar = hexStr.charAt(i);
			order = hexStr.length() - 1 - i;
			if (hexChar >= '0' && hexChar <= '9') {
				decimal += (hexChar - '0') * Math.pow(16, order);
			} else if (hexChar >= 'a' && hexChar <= 'f') {
				decimal += (hexChar - 'a' + 10) * Math.pow(16, order);
			} else { // error
				System.out.println("error: invalid hexadecimal string \"" + hexStr + "\"");
				System.exit(1); // quit the program
			}
		}
		// display
		System.out.println("The equivalent decimal number for hexadecimal " + hexStr + " is: " + decimal);
		in.close();
	}

}
