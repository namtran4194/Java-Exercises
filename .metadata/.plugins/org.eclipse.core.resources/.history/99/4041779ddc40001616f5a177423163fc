package adapters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HighScore {
	private List<Player> listTopPlayer = new ArrayList<>();
	protected File file;

	public HighScore() {
		setPath();
		readData();
	}

	public void readData() {
		Player player;
		String name, path;
		int score;
		String line;
		StringTokenizer tokenizer;
		path = System.getProperty("user.home") + "\\.minesweeper\\highscore";
		File file = new File(path);
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				tokenizer = new StringTokenizer(line, "|");
				name = tokenizer.nextToken();
				score = Integer.parseInt(tokenizer.nextToken());
				player = new Player(name, score);
				listTopPlayer.add(player);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean writeData() {
		Player player;
		String line;
		if (listTopPlayer.isEmpty()) {
			return true;
		} else {
			try (PrintWriter writer = new PrintWriter(new FileWriter(file, true), true)) {
				for (int i = 0; i < listTopPlayer.size(); i++) {
					player = listTopPlayer.get(i);
					line = player.getName() + "|" + player.getScore();
					writer.println(line);
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	public boolean setPath() {
		String path = System.getProperty("user.home");
		path += "\\.minesweeper";
		file = new File(path);
		if (file.mkdirs()) {
			file = new File(file, "highscore");
			if (!file.isFile()) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public List<Player> getListTopPlayer() {
		return listTopPlayer;
	}

	public void setListTopPlayer(List<Player> listTopPlayer) {
		this.listTopPlayer = listTopPlayer;
	}

}
