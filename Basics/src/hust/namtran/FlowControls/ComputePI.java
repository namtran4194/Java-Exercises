package hust.namtran.FlowControls;

public class ComputePI {
	/*
	 * Compute π using loop
	 */
	public static void main(String[] args) {
		double sum = 0.0;
		int maxDenominator = 10000000;
		for (int denominator = 1; denominator <= maxDenominator; denominator += 2) { // 1, 3, 5, 7,...
			if (denominator % 4 == 1)
				sum += (double) 1 / denominator;
			else if (denominator % 4 == 3)
				sum -= (double) 1 / denominator;
			else
				System.out.println("The computer has gone crazy?!");
		}
		System.out.println("The value of π is " + 4 * sum);
		System.out.println("Math.PI is " + Math.PI);
	}

}
