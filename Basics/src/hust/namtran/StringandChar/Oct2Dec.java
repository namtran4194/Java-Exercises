package hust.namtran.StringandChar;

import java.util.Scanner;

public class Oct2Dec {

	public static void main(String[] args) {
		String octStr;
		int decimal = 0;
		int order;
		char octChar;

		System.out.print("Enter a Octa string: ");
		Scanner in = new Scanner(System.in);
		octStr = in.next();

		for (int i = 0; i < octStr.length(); i++) {
			order = octStr.length() - 1 - i;
			octChar = octStr.charAt(i);
			if (octChar >= '0' && octChar <= '7') {
				decimal += (octChar - '0') * Math.pow(8, order);
			} else {
				System.out.println("error: invalid octa string \"" + octStr + "\"");
				System.exit(1);
			}
		}

		// display
		System.out.println("The equivalent decimal number for octa \"" + octStr + "\" is: " + decimal);
		in.close();
	}

}
