package hust.namtran.Array;

import java.util.Scanner;

public class GradesAverage {

	public static void main(String[] args) {
		int numberOfStudent;
		int[] grades;
		int input;
		int sum = 0;
		double average;
		int i = 0;

		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of students: ");
		numberOfStudent = in.nextInt();
		grades = new int[numberOfStudent];
		while (numberOfStudent > 0) {
			System.out.print("Enter the grade for student " + (i + 1) + ": ");
			input = in.nextInt();
			if (input >= 0 && input <= 100) {
				grades[i] = input;
				numberOfStudent--;
				i++;
			} else {
				System.out.println("Invalid grade, try again...");
			}
		}

		for (int j = 0; j < grades.length; j++) {
			sum += grades[j];
		}
		// display
		average = (double) sum / (grades.length);
		System.out.println("The average is: " + average);
		in.close();
	}

}
