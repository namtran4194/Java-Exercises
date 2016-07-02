package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import adapters.DigitsDocument;

/**
 * The Sudoku game.
 * To solve the number puzzle, each row, each column, and each of the
 * nine 3×3 sub-grids shall contain all of the digits from 1 to 9
 */
public class Sudoku extends JFrame {
	private static final long serialVersionUID = 1L;

	private String currentLevel = "Beginner"; // Current level
	private boolean isNewGame = true; // Create a new game
	private int numberOfCellsRemaining; // Show in status bar

	// Name-constants for the game properties (levels and status bar)
	public static final String STATUS = "Number of cells remaining: ";
	public static final String BEGINNER = "Beginner";
	public static final String INTERMEDIATE = "Intermediate";
	public static final String EXPERT = "Expert";
	public static final String LEGEND = "Legend";

	public static final int GRID_SIZE = 9; // Size of the board
	public static final int SUBGRID_SIZE = 3; // Size of the sub-grid

	// Name-constants for UI control (sizes, colors and fonts)
	public static final int CELL_SIZE = 60; // Cell width/height in pixels
	public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
	public static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
	public static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0); // RGB
	public static final Color OPEN_CELL_TEXT_NO = Color.RED;
	public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	// The game board composes of 9x9 JTextFields,
	// each containing String "1" to "9", or empty String
	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

	// Puzzle to be solved and the mask (which can be used to control the difficulty level).
	// Hardcoded here. Extra credit for automatic puzzle generation with various difficulty levels.
	private int[][] puzzle = new int[GRID_SIZE][GRID_SIZE];
	private boolean[][] masks = new boolean[GRID_SIZE][GRID_SIZE];

	public JLabel message;
	public Timer timer;
	public Container container;
	public JMenuItem beginner, intermediate, expert, legend;

	/**
	  * Constructor to setup the game and the UI Components
	  */

	public Sudoku() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		createMenu();
		getPuzzle();
		statusBar();
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(getToolkit().getImage("resources/sudoku.png"));
		setLocationRelativeTo(null);
		setTitle("Sudoku");
		setVisible(true);
	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu mFile, mOptions, mHelp, mLevels;
		JMenuItem newGame, resetGame, exit;
		JMenuItem construction, about;

		// File
		mFile = new JMenu("File");
		newGame = new JMenuItem("New Game");
		resetGame = new JMenuItem("Reset Game");
		exit = new JMenuItem("Exit");
		mFile.add(newGame);
		mFile.add(resetGame);
		mFile.addSeparator();
		mFile.add(exit);

		// Options
		mOptions = new JMenu("Options");
		mLevels = new JMenu("Levels");
		beginner = new JMenuItem();
		intermediate = new JMenuItem();
		expert = new JMenuItem();
		legend = new JMenuItem();
		mOptions.add(mLevels);
		mLevels.add(beginner);
		mLevels.addSeparator();
		mLevels.add(intermediate);
		mLevels.addSeparator();
		mLevels.add(expert);
		mLevels.addSeparator();
		mLevels.add(legend);

		// Help
		mHelp = new JMenu("Help");
		construction = new JMenuItem("Construction");
		about = new JMenuItem("About");
		mHelp.add(construction);
		mHelp.addSeparator();
		mHelp.add(about);

		// Add to menu bar
		menuBar.add(mFile);
		menuBar.add(mOptions);
		menuBar.add(mHelp);
		setJMenuBar(menuBar);
		contextLevelsMenu();

		// Add event listener
		newGame.addActionListener(new MenuListener());
		resetGame.addActionListener(new MenuListener());
		exit.addActionListener(new MenuListener());
		beginner.addActionListener(new MenuListener());
		intermediate.addActionListener(new MenuListener());
		expert.addActionListener(new MenuListener());
		legend.addActionListener(new MenuListener());
		construction.addActionListener(new MenuListener());
		about.addActionListener(new MenuListener());
	}

	public void getPuzzle() {
		numberOfCellsRemaining = 0;
		if (isNewGame) {
			shufflePuzzle();
			shuffleMasks();
			isNewGame = false;
		}
		InputListener listener = new InputListener();
		JPanel puzzlePanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE, 2, 2));
		JPanel subPanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
		int numCells = 0;
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				tfCells[row][col] = new JTextField(); // Allocate element of array
				subPanel.add(tfCells[row][col]);
				numCells++;
				if (masks[row][col]) {
					tfCells[row][col].setText("");
					tfCells[row][col].setFocusable(true);
					tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
					tfCells[row][col].setDocument(new DigitsDocument(1));
					tfCells[row][col].addKeyListener(listener);
					numberOfCellsRemaining++;
				} else {
					tfCells[row][col].setText(puzzle[row][col] + "");
					tfCells[row][col].setFocusable(false);
					tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
					tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
				}
				// Beautify all the cells
				tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
				tfCells[row][col].setFont(FONT_NUMBERS);
				if (numCells == 9) {
					numCells = 0;
					puzzlePanel.add(subPanel);
					subPanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
				}
			}
		}
		container.add(puzzlePanel, BorderLayout.CENTER);
	}

	// Create the random puzzle
	public void shufflePuzzle() {
		Random r = new Random();
		int firstVal = r.nextInt(8);
		int x = firstVal, v = 1;
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if ((x + col + v) <= 9) {
					puzzle[row][col] = x + col + v;
				} else {
					puzzle[row][col] = x + col + v - 9;
				}
				if (puzzle[row][col] == 10) {
					puzzle[row][col] = 1;
				}
			}
			x += 3;
			if (x >= 9) {
				x -= 9;
			}
			if (row == 2) {
				v = 2;
				x = firstVal;
			}
			if (row == 5) {
				v = 3;
				x = firstVal;
			}
		}
	}

	public void shuffleMasks() {
		Random random = new Random();
		int randomRow = -1, randomCol = -1;
		// Reset masks
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				masks[row][col] = false;
			}
		}

		// Set the number of empty cells
		boolean temp;
		int cellsLevel = 0;
		switch (currentLevel) {
		case "Beginner":
			cellsLevel = 4;
			break;
		case "Intermediate":
			cellsLevel = 8;
			break;
		case "Expert":
			cellsLevel = 16;
			break;
		case "Legend":
			cellsLevel = 40;
			break;
		default:
			break;
		}

		// Set the empty cells' location
		for (int i = 0; i < cellsLevel; i++) {
			randomRow = random.nextInt(GRID_SIZE);
			randomCol = random.nextInt(GRID_SIZE);
			if (!masks[randomRow][randomCol]) {
				masks[randomRow][randomCol] = true;
			} else {
				i--;
			}
		}

		// Shuffle the empty cells' location
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				randomRow = random.nextInt(GRID_SIZE);
				randomCol = random.nextInt(GRID_SIZE);
				temp = masks[row][col];
				masks[row][col] = masks[randomRow][randomCol];
				masks[randomRow][randomCol] = temp;
			}
		}
	}

	// Status bar, to show number of cells remaining, timer
	public void statusBar() {
		JPanel statusPanel = new JPanel(new GridLayout(1, 2));
		message = new JLabel(STATUS + numberOfCellsRemaining);
		JLabel timeLb = new JLabel();
		timeLb.setHorizontalAlignment(JLabel.RIGHT);
		timer = new Timer(1000, new ActionListener() {
			int time = 0;
			String mesTimer = "Time: ";

			@Override
			public void actionPerformed(ActionEvent arg0) {
				time++;
				timeLb.setText(mesTimer + time + "s");
			}
		});
		timer.start();
		// Add components to content-pane
		statusPanel.add(message);
		statusPanel.add(timeLb);
		container.add(statusPanel, BorderLayout.SOUTH);
	}

	// Reset game when change game level
	public void switchLevel() {
		isNewGame = true;
		switch (currentLevel) {
		case "Beginner":
			currentLevel = "Beginner";
			refresh();
			break;
		case "Intermediate":
			currentLevel = "Intermediate";
			refresh();
			break;
		case "Expert":
			currentLevel = "Expert";
			refresh();
			break;
		case "Legend":
			currentLevel = "Legend";
			refresh();
			break;
		default:
			break;
		}
	}

	// Change level in Menu when choose level
	public void contextLevelsMenu() {
		beginner.setText("Beginner");
		intermediate.setText("Intermediate");
		expert.setText("Expert");
		legend.setText("Legend");
		switch (currentLevel) {
		case "Beginner":
			beginner.setText("• Beginner");
			break;
		case "Intermediate":
			intermediate.setText("• Intermediate");
			break;
		case "Expert":
			expert.setText("• Expert");
			break;
		case "Legend":
			legend.setText("• Legend");
			break;
		default:
			break;
		}
	}

	// Get construction in file to string
	public String getConstruction() {
		String line, data = "";
		InputStream in = ClassLoader.getSystemResourceAsStream("sudoku construction");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			while ((line = reader.readLine()) != null) {
				data += line + "\n";
			}
		} catch (Exception e) {
			data = e.getMessage();
		}
		return data;
	}

	// Get about in file as string
	public String getAbout() {
		String line, data = "";
		InputStream in = ClassLoader.getSystemResourceAsStream("about");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
			while ((line = reader.readLine()) != null) {
				data += line + "\n";
			}
		} catch (Exception e) {
			data = e.getMessage();
		}
		return data;
	}

	// Refresh game component when change game level, select new game, reset game
	public void refresh() {
		container.removeAll();
		getPuzzle();
		statusBar();
		contextLevelsMenu();
		container.validate();
		container.repaint();
	}

	// Handle click menu
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			switch (command) {
			case "New Game":
				isNewGame = true;
				refresh();
				break;
			case "Reset Game":
				isNewGame = false;
				refresh();
				break;
			case "Exit":
				System.exit(1);
				break;
			case "Beginner":
				currentLevel = "Beginner";
				switchLevel();
				break;
			case "Intermediate":
				currentLevel = "Intermediate";
				switchLevel();
				break;
			case "Expert":
				currentLevel = "Expert";
				switchLevel();
				break;
			case "Legend":
				currentLevel = "Legend";
				switchLevel();
				break;
			case "Construction":
				JOptionPane.showMessageDialog(Sudoku.this, getConstruction(), "How to play",
						JOptionPane.INFORMATION_MESSAGE);
				break;
			case "About":
				JOptionPane.showMessageDialog(Sudoku.this, getAbout(), "About Sudoku", JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
				break;
			}
		}

	}

	// Handle typing keyboard
	private class InputListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int rowSelected = -1;
			int colSelected = -1;

			// Get the source object that fired the event
			JTextField source = (JTextField) e.getSource();
			// Scan JTextFileds for all rows and columns, and match with the source object
			boolean found = false;
			for (int row = 0; row < GRID_SIZE && !found; row++) {
				for (int col = 0; col < GRID_SIZE && !found; col++) {
					if (tfCells[row][col] == source) {
						rowSelected = row;
						colSelected = col;
						found = true;
					}
				}
			}

			char c = e.getKeyChar();
			int inputNumder = c - '0';
			boolean cellsChange = false;
			if (inputNumder == puzzle[rowSelected][colSelected]) {
				tfCells[rowSelected][colSelected].setText(String.valueOf(c));
				tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
				tfCells[rowSelected][colSelected].setFocusable(false);
				numberOfCellsRemaining--;
				cellsChange = true;
			} else {
				tfCells[rowSelected][colSelected].setBackground(OPEN_CELL_TEXT_NO);
				tfCells[rowSelected][colSelected].setFocusable(true);
			}

			if (cellsChange) {
				message.setText(STATUS + numberOfCellsRemaining);
			}

			if (numberOfCellsRemaining == 0) {
				timer.stop();
				JOptionPane.showMessageDialog(Sudoku.this, "You win!", "Notification", JOptionPane.INFORMATION_MESSAGE);
				isNewGame = true;
				refresh();
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Sudoku();
	}

}