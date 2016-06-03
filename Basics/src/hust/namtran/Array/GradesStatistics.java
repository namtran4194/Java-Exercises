package hust.namtran.Array;

import java.util.Arrays;
import java.util.Scanner;

public class GradesStatistics {
	public static int[] grades;

	public static void main(String[] args) {
		readGrades();
		printArray(grades);
		System.out.println("The average is: " + average(grades));
		System.out.println("The median is: " + median(grades));
		System.out.println("The minimum is: " + min(grades));
		System.out.println("The maximum is: " + max(grades));
		System.out.printf("The standard deviation is: %.2f", standardDeviation(grades));
	}

	private static void readGrades() {
		int grade, numberOfStudents, i = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of students: ");
		numberOfStudents = in.nextInt();
		grades = new int[numberOfStudents];
		while (numberOfStudents > 0) {
			System.out.print("Enter the grade for student " + (i + 1) + ": ");
			grade = in.nextInt();
			if (grade >= 0 && grade <= 100) {
				grades[i] = grade;
				numberOfStudents--;
				i++;
			} else {
				System.out.println("error: invalid grade");
			}
		}
		in.close();
	}

	private static void printArray(int[] array) {
		System.out.print("{");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i != array.length - 1) {
				System.out.print(",");
			}
		}
		System.out.println("}");
	}

	private static double average(int[] array) {
		int sum = 0;
		double average;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		average = (double) sum / array.length;
		return average;
	}

	private static int max(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (max < array[i])
				max = array[i];
		}
		return max;
	}

	private static int min(int[] array) {
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (min > array[i])
				min = array[i];
		}
		return min;
	}

	private static double median(int[] array) {
		Arrays.sort(array); // sắp xếp tăng dần
		double median;
		int size = array.length;
		if (size % 2 == 1) {
			median = array[size / 2 + 1];
		} else {
			median = (double) (array[size / 2 - 1] + array[size / 2]) / 2;
		}
		return median;
	}

	private static double standardDeviation(int[] array) {
		double total = 0;
		for (int i = 0; i < array.length; i++) {
			total += Math.pow(array[i], 2);
		}
		return Math.sqrt((double) 1 / array.length * total - Math.pow(average(array), 2));
	}

}
