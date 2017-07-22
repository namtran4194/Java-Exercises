package other;

public class SwapNumbers {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		swap3(a, b);
	}

	static void swap1(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
		System.out.println(a + "-" + b);
	}

	static void swap2(int a, int b) {
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a + "-" + b);
	}

	static void swap3(int a, int b) {
		a ^= b;
		b ^= a;
		a ^= b;
		System.out.println(a + "-" + b);
	}
}
