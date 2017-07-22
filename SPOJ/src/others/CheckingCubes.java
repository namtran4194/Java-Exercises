package others;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * oldPos: lưu vị trí của số đã chọn lúc trước số chọn sau phải có vị trí lớn
 * hơn hoặc bằng vị trí số chọn trước => các cách sẽ không bị lặp lại
 */
public class CheckingCubes {
	static int MAX = 125000;
	static int[] allCubes;

	static int count;
	static int startIndex;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		generateCubes();

		// read input
		int number = sc.nextInt();

		// calculate
		count = 0;
		for (int i = 0; i < allCubes.length; i++) {
			if (number >= allCubes[i])
				startIndex = i;
			else
				break;
		}
		Try(number, 0, 0, startIndex);

		// print
		System.out.println(count);

		sc.close();
	}

	static void Try(int number, int sum, int k, int oldPos) {
		if (k == 5) {
			if (sum == number)
				count++;
			return;
		}

		for (int i = startIndex; i >= 0; i--)
			if (i <= oldPos)
				if (sum + allCubes[i] <= number)
					Try(number, sum + allCubes[i], k + 1, i);
	}

	static void generateCubes() {
		allCubes = new int[51];
		for (int i = 0;; i++) {
			allCubes[i] = cube(i);
			if (allCubes[i] >= MAX)
				break;
		}
	}

	static int cube(int n) {
		return n * n * n;
	}
}
