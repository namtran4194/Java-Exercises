package svmc1609;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountThePairs1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int nums[] = new int[n];
		Map<Integer, Boolean> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], true);
			}
		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			if (map.containsKey(nums[i] + k)) {
				if (map.get(nums[i] + k))
					count++;
			}
			if (map.containsKey(nums[i] - k)) {
				if (map.get(nums[i] - k))
					count++;
			}
			map.remove(nums[i]);
		}

		System.out.println(count);

		sc.close();
	}

}
