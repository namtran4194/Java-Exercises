package adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Test {

	public static void main(String[] args) {
		String path = System.getProperty("user.home");
		path += "\\.minesweeper";
		File file = new File(path);
		file.mkdirs();
		file = new File(file, "highscore");
		try (PrintWriter writer = new PrintWriter(new FileWriter(file, true), true)) {

		} catch (Exception e) {

		}
		if (file.isFile()) {
			System.out.println(path);
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
		} catch (Exception e) {
		}
	}

}