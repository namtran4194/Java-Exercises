package hust.namtran.KeyboardandFileInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {

	public static void main(String[] args) throws FileNotFoundException {
		int num1;
		double num2;
		String name;
		double sum;

		// Setup a Scanner to read from a text file
		Scanner in = new Scanner(new File("in.txt"));
		num1 = in.nextInt(); // use nextInt() to read int
		num2 = in.nextDouble(); // use nextDouble() to read double
		name = in.next(); // use next() to read String

		// Display
		sum = (num1 + num2) / 2;
		System.out.println("Hi! " + name + ", the sum of " + num1 + " and " + num2 + " is " + sum);
		// Close the input stream
		in.close();
	}

}
