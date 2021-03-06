package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import adapter.DigitsDocument;

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
	private static final String STATUS = "Number of cells remaining: ";
	private static final String BEGINNER = "Beginner";
	private static final String INTERMEDIATE = "Intermediate";
	private static final String EXPERT = "Expert";
	private static final String LEGENDARY = "Legendary";
	private static final int GRID_SIZE = 9; // Size of the board
	private static final int SUBGRID_SIZE = 3; // Size of the sub-grid
	// Name-constants for UI control (sizes, colors and fonts)
	private static final int CELL_SIZE = 60; // Cell width/height in pixels
	private static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
	private static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE; // Board width/height in pixels
	private static final Color OPEN_CELL_BGCOLOR = Color.YELLOW;
	private static final Color OPEN_CELL_TEXT_NO = Color.RED;
	private static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
	private static final Color CLOSED_CELL_TEXT = Color.BLACK;
	private static final Font FONT_NUMBERS = new Font("Verdana", Font.PLAIN, 20);
	private static final Font FONT_MENU = new Font("Verdana", Font.CENTER_BASELINE, 13);
	private static final Font FONT_STATUS_BAR = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 15);
	// The game board composes of 9x9 JTextFields,
	// each containing String "1" to "9", or empty String
	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
	// Puzzle to be solved and the mask (which can be used to control the difficulty level).
	// Hardcoded here. Extra credit for automatic puzzle generation with various difficulty levels.
	private int[][] puzzle = new int[GRID_SIZE][GRID_SIZE];
	private boolean[][] masks = new boolean[GRID_SIZE][GRID_SIZE];

	private JPanel puzzlePanel;
	private JLabel message;
	private Timer timer;
	private int second;
	private Container container;
	private JMenuItem beginner, intermediate, expert, legendary;

	/**
	 * Constructor to setup the game and the UI Components
	 */
	public Sudoku() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		createMenu();
		statusBar();
		initGame();
		pack();
		Image icon = null;
		try {
			icon = ImageIO.read(ClassLoader.getSystemResource("sudoku.png"));
		} catch (IllegalArgumentException | IOException e) {
			JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(icon);
		setTitle("Sudoku");
		setVisible(true);
	}

	/**
	 * Initialize and re-initialize a new game
	 */
	public void initGame() {
		numberOfCellsRemaining = 0;
		if (isNewGame) {
			shufflePuzzle();
			shuffleMasks();
			isNewGame = false;
		}
		InputListener listener = new InputListener();
		puzzlePanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
		JPanel subPanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
		int numCells = 0, blockPosition = 0;
		for (int row = 0; row < GRID_SIZE; row++)
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
				tfCells[row][col].setHorizontalAlignment(SwingConstants.CENTER);
				tfCells[row][col].setFont(FONT_NUMBERS);
				if (numCells == 9) {
					numCells = 0;
					blockPosition++;
					setBorder(subPanel, blockPosition);
					puzzlePanel.add(subPanel);
					subPanel = new JPanel(new GridLayout(SUBGRID_SIZE, SUBGRID_SIZE));
				}
			}
		container.add(puzzlePanel, BorderLayout.CENTER);
		// Status bar
		if (!timer.isRunning())
			timer.start();
		message.setText(STATUS + numberOfCellsRemaining);
		contextLevelsMenu();
	}

	/**
	 * Set border for each block
	 */
	public void setBorder(JPanel subPanel, int position) {
		Border twoLines = BorderFactory.createMatteBorder(0, 0, 3, 3, Color.BLACK);
		Border bottom = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK);
		Border right = BorderFactory.createMatteBorder(0, 0, 0, 3, Color.BLACK);
		if (position == 3 || position == 6)
			subPanel.setBorder(bottom);
		else if (position == 7 | position == 8)
			subPanel.setBorder(right);
		else if (position == 9)
			return;
		else
			subPanel.setBorder(twoLines);
	}

	/**
	 * Create the random puzzle
	 */
	public void shufflePuzzle() {
		Random r = new Random();
		int firstVal = r.nextInt(8);
		int x = firstVal, v = 1;
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (x + col + v <= 9)
					puzzle[row][col] = x + col + v;
				else
					puzzle[row][col] = x + col + v - 9;
				if (puzzle[row][col] == 10)
					puzzle[row][col] = 1;
			}
			x += 3;
			if (x >= 9)
				x -= 9;
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

	/**
	 * Control the difficulty level
	 */
	public void shuffleMasks() {
		Random random = new Random();
		int randomRow = -1, randomCol = -1;
		// Reset masks
		for (int row = 0; row < GRID_SIZE; row++)
			for (int col = 0; col < GRID_SIZE; col++)
				masks[row][col] = false;
		// Set the number of empty cells
		boolean temp;
		int cellsLevel = 0;
		switch (currentLevel) {
			case BEGINNER:
				cellsLevel = 8;
				break;
			case INTERMEDIATE:
				cellsLevel = 16;
				break;
			case EXPERT:
				cellsLevel = 24;
				break;
			case LEGENDARY:
				cellsLevel = 32;
				break;
			default:
				break;
		}
		// Set the empty cells' location
		for (int i = 0; i < cellsLevel; i++) {
			randomRow = random.nextInt(GRID_SIZE);
			randomCol = random.nextInt(GRID_SIZE);
			if (!masks[randomRow][randomCol])
				masks[randomRow][randomCol] = true;
			else
				i--;
		}
		// Shuffle the empty cells' location
		for (int row = 0; row < GRID_SIZE; row++)
			for (int col = 0; col < GRID_SIZE; col++) {
				randomRow = random.nextInt(GRID_SIZE);
				randomCol = random.nextInt(GRID_SIZE);
				temp = masks[row][col];
				masks[row][col] = masks[randomRow][randomCol];
				masks[randomRow][randomCol] = temp;
			}
	}

	/**
	 * Create menu bar and the components
	 */
	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu mFile, mOptions, mHelp, mLevel;
		JMenuItem newGame, resetGame, exit;
		JMenuItem instructions, about;
		// Menu File
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);
		mFile.setToolTipText("Create, reset or exit the game");
		newGame = new JMenuItem("New Game");
		resetGame = new JMenuItem("Reset Game");
		exit = new JMenuItem("Exit");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		resetGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		mFile.add(newGame);
		mFile.add(resetGame);
		mFile.addSeparator();
		mFile.add(exit);
		// Menu Options
		mOptions = new JMenu("Options");
		mOptions.setMnemonic(KeyEvent.VK_T);
		mOptions.setToolTipText("Change the game level and see high scores");
		mLevel = new JMenu("Level");
		mLevel.setMnemonic(KeyEvent.VK_L);
		mLevel.setToolTipText("Change the difficulty of the game");
		beginner = new JMenuItem();
		intermediate = new JMenuItem();
		expert = new JMenuItem();
		legendary = new JMenuItem();
		beginner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		intermediate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		legendary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		mOptions.add(mLevel);
		mLevel.add(beginner);
		mLevel.addSeparator();
		mLevel.add(intermediate);
		mLevel.addSeparator();
		mLevel.add(expert);
		mLevel.addSeparator();
		mLevel.add(legendary);
		// Menu Help
		mHelp = new JMenu("Help");
		mHelp.setMnemonic(KeyEvent.VK_H);
		mHelp.setToolTipText("See instructions and about of the game and the author");
		instructions = new JMenuItem("Instructions");
		about = new JMenuItem("About");
		mHelp.add(instructions);
		mHelp.addSeparator();
		mHelp.add(about);
		// Add to menu bar
		menuBar.add(mFile);
		menuBar.add(mOptions);
		menuBar.add(mHelp);
		setJMenuBar(menuBar);
		// Add event listener
		ActionListener listener = new MenuListener();
		newGame.addActionListener(listener);
		resetGame.addActionListener(listener);
		exit.addActionListener(listener);
		beginner.addActionListener(listener);
		intermediate.addActionListener(listener);
		expert.addActionListener(listener);
		legendary.addActionListener(listener);
		instructions.addActionListener(listener);
		about.addActionListener(listener);
		// Set font
		mFile.setFont(FONT_MENU);
		mOptions.setFont(FONT_MENU);
		mHelp.setFont(FONT_MENU);
		newGame.setFont(FONT_MENU);
		resetGame.setFont(FONT_MENU);
		exit.setFont(FONT_MENU);
		mLevel.setFont(FONT_MENU);
		beginner.setFont(FONT_MENU);
		intermediate.setFont(FONT_MENU);
		expert.setFont(FONT_MENU);
		legendary.setFont(FONT_MENU);
		instructions.setFont(FONT_MENU);
		about.setFont(FONT_MENU);
	}

	/**
	 * Status bar to show number of cells remaining, timer
	 */
	public void statusBar() {
		JPanel statusPanel = new JPanel(new GridLayout(1, 2));
		statusPanel.setBackground(Color.WHITE);
		message = new JLabel();
		JLabel timeLb = new JLabel();
		timeLb.setHorizontalAlignment(SwingConstants.RIGHT);
		message.setForeground(Color.RED);
		timeLb.setForeground(Color.RED);
		message.setFont(FONT_STATUS_BAR);
		timeLb.setFont(FONT_STATUS_BAR);
		timer = new Timer(1000, new ActionListener() {
			String mesTimer = "Time: ";

			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeLb.setText(mesTimer + setTime(second));
				second++;
			}

			public String setTime(int second) {
				int m = second / 60;
				int s = second % 60;
				String mString, sString;
				if (m < 10)
					mString = "0" + m;
				else
					mString = String.valueOf(m);
				if (s < 10)
					sString = "0" + s;
				else
					sString = String.valueOf(s);
				return mString + ":" + sString;
			}
		});
		// Add components to content-pane
		statusPanel.add(message);
		statusPanel.add(timeLb);
		container.add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	 * Reset game when change game level
	 */
	public void switchLevel() {
		isNewGame = true;
		switch (currentLevel) {
			case BEGINNER:
				currentLevel = BEGINNER;
				refresh();
				break;
			case INTERMEDIATE:
				currentLevel = "Intermediate";
				refresh();
				break;
			case EXPERT:
				currentLevel = EXPERT;
				refresh();
				break;
			case LEGENDARY:
				currentLevel = LEGENDARY;
				refresh();
				break;
			default:
				break;
		}
	}

	/**
	 * Change level context in Menu when choose level
	 */
	public void contextLevelsMenu() {
		beginner.setText(BEGINNER);
		intermediate.setText(INTERMEDIATE);
		expert.setText(EXPERT);
		legendary.setText(LEGENDARY);
		switch (currentLevel) {
			case BEGINNER:
				beginner.setText("• Beginner");
				break;
			case INTERMEDIATE:
				intermediate.setText("• Intermediate");
				break;
			case EXPERT:
				expert.setText("• Expert");
				break;
			case LEGENDARY:
				legendary.setText("• Legendary");
				break;
			default:
				break;
		}
	}

	/**
	 * Refresh game component when change game level, select new game, reset
	 * game
	 */
	public void refresh() {
		container.remove(puzzlePanel);
		initGame();
		contextLevelsMenu();
		container.validate();
		container.repaint();
	}

	/**
	 * Handle when menu clicked
	 */
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
				case BEGINNER:
					currentLevel = BEGINNER;
					switchLevel();
					break;
				case INTERMEDIATE:
					currentLevel = INTERMEDIATE;
					switchLevel();
					break;
				case EXPERT:
					currentLevel = EXPERT;
					switchLevel();
					break;
				case LEGENDARY:
					currentLevel = LEGENDARY;
					switchLevel();
					break;
				case "Instructions":
					new Infomation().instructions();
					break;
				case "About":
					new Infomation().about();
					break;
				default:
					break;
			}
		}

	}

	/**
	 * Handle typing keyboard
	 */
	private class InputListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int rowSelected = -1;
			int colSelected = -1;

			// Get the source object that fired the event
			JTextField source = (JTextField) e.getSource();
			// Scan JTextFileds for all rows and columns, and match with the source object
			boolean found = false;
			for (int row = 0; row < GRID_SIZE && !found; row++)
				for (int col = 0; col < GRID_SIZE && !found; col++)
					if (tfCells[row][col] == source) {
						rowSelected = row;
						colSelected = col;
						found = true;
					}

			char c = e.getKeyChar();
			int inputNumder = c - '0';
			if (inputNumder == puzzle[rowSelected][colSelected]) {
				tfCells[rowSelected][colSelected].setText(String.valueOf(c));
				tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
				tfCells[rowSelected][colSelected].setFocusable(false);
				numberOfCellsRemaining--;
				message.setText(STATUS + numberOfCellsRemaining);
			} else {
				tfCells[rowSelected][colSelected].setBackground(OPEN_CELL_TEXT_NO);
				tfCells[rowSelected][colSelected].setFocusable(true);
			}

			if (numberOfCellsRemaining == 0) {
				timer.stop();
				JOptionPane.showMessageDialog(Sudoku.this, "You win!", "Congratulation",
						JOptionPane.INFORMATION_MESSAGE);
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
