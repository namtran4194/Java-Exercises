package others;

import java.io.IOException;
import java.io.InputStream;

public class SuperFastIO {
	
	public static void main(String[] args) throws IOException {
		System.out.println(readInt(System.in));
	}
	
	public static int readInt(InputStream in) throws IOException {
		int result = 0;
		boolean dig = false;

		// read a byte (8bit) and transform it to number

		for (int c = 0; (c = in.read()) != -1;) {
			if (c >= '0' && c <= '9') {
				dig = true;
				result = result * 10 + c - '0';
			} else if (dig)
				break;
		}

		return result;
	}
}
