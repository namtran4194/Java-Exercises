package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import adapter.HighScores;
import adapter.Player;
import adapter.Point;

/**
 * The Mine Sweeper Game.
 * Left-click to reveal a cell.
 * Right-click to plant/remove a flag for marking a suspected mine.
 * You win if all the cells not containing mines are revealed.
 * You lose if you reveal a cell containing a mine.
 */
public class MineSweeper extends JFrame {
	private static final long serialVersionUID = 1L;

	private String currentLevel = BEGINNER; // Current level
	private int numRows = 8, numCols = 8;
	private int cellSize = 60; // Cell width and height, in pixels
	private int canvasWidth, canvasHeigh; // Game board width/heigh
	// Name-constants for the game properties
	private static final int LEVEL_CHANGED = 0;
	private static final int SCORE_CHANGED = 1;
	private static final int CELLS_CHANGED = 2;
	private static final int FLAGS_CHANGED = 3;
	private static final String BEGINNER = "Beginner";
	private static final String INTERMEDIATE = "Intermediate";
	private static final String EXPERT = "Expert";
	// Name-constants for UI control (sizes, colors and fonts)
	private static final Color BGCOLOR_NOT_REVEALED = Color.GREEN; // Background
	private static final Color BGCOLOR_REVEALED_MINE = Color.RED; // Background of mine's cell
	private static final Color BGCOLOR_REVEALED = Color.DARK_GRAY; // Background of selected cell
	private static final Color FGCOLOR_REVEALED = Color.LIGHT_GRAY; // Foreground of selected cell
	private static final Font FONT_NUMBERS = new Font("Verdana", Font.TYPE1_FONT, 20);
	private static final Font FONT_TOP_PANEL = new Font("Comic Sans MS", Font.ROMAN_BASELINE, 15);
	private static final Font FONT_STATUS_BAR = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 13);
	private static final Font FONT_MENU = new Font("Verdana", Font.CENTER_BASELINE, 13);
	private List<Point> listCells = new ArrayList<>();
	// Buttons for user interaction
	private JButton[][] btnCells;
	// Number of mines in this game. Can vary to control the difficulty level.
	private int numMines = 10;
	private int cellsLeft, numFlags;
	// Image of mine and flag
	private Image minesImage, flagsImage;
	// Location of mines. True if mine is present on this cell.
	private boolean[][] mines;
	// User can right-click to plant/remove a flag to mark a suspicious cell
	private boolean[][] flags;
	// Other attribute and component
	private Container container;
	private JPanel gamePanel;
	private Timer timer;
	private int score;
	private int second;
	private JLabel cellsLb, minesLb, flagsLb, timeLb, levelLb, scoreLb;
	private JMenuItem beginner, intermediate, expert;

	/**
	 * Constructor to setup the game and the UI Components
	 */
	public MineSweeper() {
		container = getContentPane();
		container.setLayout(new BorderLayout());
		// Create status bar, menu bar...
		createMenu();
		topPanel();
		statusBar();
		Image icon = null;
		// Get image
		try {
			minesImage = ImageIO.read(ClassLoader.getSystemResourceAsStream("mines.png"));
			flagsImage = ImageIO.read(ClassLoader.getSystemResource("flags.png"));
			icon = ImageIO.read(getClass().getClassLoader().getResource("minesweeper.png"));
		} catch (IllegalArgumentException | IOException e) {
			notification(this, e.toString(), "Error", 0);
			System.exit(1);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon);
		setTitle("Minesweeper");
		setVisible(true);
		// Initialize for a new game
		initGame();
	}

	/**
	 * Initialize and re-initialize a new game
	 */
	public void initGame() {
		// Initialize top panel
		if (cellsLeft != 0 && cellsLeft != numRows * numCols - numMines)
			score = 0;
		levelLb.setText("Difficulty: " + currentLevel);
		scoreLb.setText("Your score: " + score);
		// Menu bar, need to be located here
		contextLevelMenu();
		// Construct ROWS*COLS JButtons and add to the content-pane
		gamePanel = new JPanel(new GridLayout(numRows, numCols, 2, 2), true);
		CellMouseListener listener = new CellMouseListener();
		btnCells = new JButton[numRows][numCols];
		for (int row = 0; row < numRows; row++)
			for (int col = 0; col < numCols; col++) {
				btnCells[row][col] = new JButton();
				btnCells[row][col].addMouseListener(listener);
				gamePanel.add(btnCells[row][col]);
			}
		container.add(gamePanel, BorderLayout.CENTER);
		// Set size, location for game window
		canvasWidth = cellSize * numCols;
		canvasHeigh = cellSize * numRows;
		container.setPreferredSize(new Dimension(canvasWidth, canvasHeigh));
		pack();
		setLocationRelativeTo(null);
		// Reset cells, mines and flags
		mines = new boolean[numRows][numCols];
		flags = new boolean[numRows][numCols];
		for (int row = 0; row < numRows; row++)
			for (int col = 0; col < numCols; col++) {
				// Set all cells to un-revealed
				btnCells[row][col].setEnabled(true);
				btnCells[row][col].setFocusable(false);
				btnCells[row][col].setForeground(BGCOLOR_REVEALED_MINE);
				btnCells[row][col].setBackground(BGCOLOR_NOT_REVEALED);
				btnCells[row][col].setFont(FONT_NUMBERS);
				btnCells[row][col].setMargin(new Insets(0, 0, 0, 0));
				btnCells[row][col].setIcon(null); // clear all the flags icon
				btnCells[row][col].setText(""); // display blank
				flags[row][col] = false; // clear all the flags
				mines[row][col] = false; // clear all the mines
			}
		// Set the number of mines and the mines' location
		cellsLeft = numRows * numCols - numMines;
		numFlags = 0;
		Random random = new Random();
		int minesPlaced = 0;
		while (minesPlaced < numMines) {
			int row = random.nextInt(numRows);
			int col = random.nextInt(numCols);
			if (!mines[row][col]) {
				mines[row][col] = true;
				minesPlaced++;
			}
		}
		// Initialize status bar
		cellsLb.setText("Cells remaining: " + cellsLeft);
		minesLb.setText("Mines: " + numMines);
		flagsLb.setText("Flags: " + numFlags);
		timer.start();
	}

	/**
	 * Refresh the current game when select Reset Game in menu
	 */
	public void reset() {
		// Initialize status bar
		score = 0;
		numFlags = 0;
		cellsLeft = numRows * numCols - numMines;
		scoreLb.setText("Your score: " + score);
		cellsLb.setText("Cells remaining: " + cellsLeft);
		flagsLb.setText("Flags: " + numFlags);
		for (int row = 0; row < numRows; row++)
			for (int col = 0; col < numCols; col++) {
				// Set all cells to un-revealed
				btnCells[row][col].setEnabled(true);
				btnCells[row][col].setFocusable(false);
				btnCells[row][col].setForeground(BGCOLOR_REVEALED_MINE);
				btnCells[row][col].setBackground(BGCOLOR_NOT_REVEALED);
				btnCells[row][col].setIcon(null); // clear all the flags icon
				btnCells[row][col].setText(""); // display blank
				flags[row][col] = false; // clear all the flags
			}
	}

	/**
	 * Create menu bar and the components
	 */
	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu mFile, mOptions, mHelp, mLevel;
		JMenuItem newGame = null, resetGame = null, exit = null;
		JMenuItem highScores = null;
		JMenuItem instructions = null, about = null;
		// File
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);
		mFile.setToolTipText("Create, reset or exit the game");
		createMenuItem(mFile, newGame, "New Game", false, KeyEvent.VK_N);
		createMenuItem(mFile, resetGame, "Reset Game", true, KeyEvent.VK_R);
		createMenuItem(mFile, exit, "Exit", false, 0);

		// Options
		mOptions = new JMenu("Options");
		mOptions.setMnemonic(KeyEvent.VK_T);
		mOptions.setToolTipText("Change the game level and see high scores");
		mLevel = new JMenu("Level");
		mLevel.setToolTipText("Change the difficulty of the game");
		mLevel.setMnemonic(KeyEvent.VK_L);
		mOptions.add(mLevel);
		mOptions.addSeparator();
		createMenuItem(mOptions, highScores, "High Scores", false, KeyEvent.VK_O);
		// Add the item to the level menu
		beginner = new JMenuItem();
		beginner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		mLevel.add(beginner);
		mLevel.addSeparator();
		intermediate = new JMenuItem();
		intermediate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		mLevel.add(intermediate);
		mLevel.addSeparator();
		expert = new JMenuItem();
		expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		mLevel.add(expert);
		// Help
		mHelp = new JMenu("Help");
		mHelp.setMnemonic(KeyEvent.VK_H);
		mHelp.setToolTipText("See instructions and about of the game and the author");
		createMenuItem(mHelp, instructions, "Instructions", true, 0);
		createMenuItem(mHelp, about, "About", false, 0);
		// Add to menu bar
		menuBar.add(mFile);
		menuBar.add(mOptions);
		menuBar.add(mHelp);
		setJMenuBar(menuBar);
		// Add event listener
		ActionListener listener = new MenuListener();
		beginner.addActionListener(listener);
		intermediate.addActionListener(listener);
		expert.addActionListener(listener);
		// Set Font
		mFile.setFont(FONT_MENU);
		mOptions.setFont(FONT_MENU);
		mHelp.setFont(FONT_MENU);
		mLevel.setFont(FONT_MENU);
		beginner.setFont(FONT_MENU);
		intermediate.setFont(FONT_MENU);
		expert.setFont(FONT_MENU);
	}

	/**
	 * Create the JMenuItem with text, font and event
	 *
	 * @param menu the JMenu for JMenuItem
	 * @param item the JMenuItem of JMenu
	 * @param text the specified text of the JMenuItem
	 * @param isSeparator if true, JMenu will add a separator
	 */
	public void createMenuItem(JMenu menu, JMenuItem item, String text, boolean isSeparator, int keyCode) {
		if (text == null)
			item = new JMenuItem();
		else
			item = new JMenuItem(text);
		item.setFont(FONT_MENU);
		item.addActionListener(new MenuListener());
		menu.add(item);
		if (isSeparator)
			menu.addSeparator();
		if (keyCode == 0)
			return;
		item.setAccelerator(KeyStroke.getKeyStroke(keyCode, ActionEvent.CTRL_MASK));
	}

	/**
	 * Change level state and attribute in Menu when choose level
	 */
	public void contextLevelMenu() {
		beginner.setText(BEGINNER);
		intermediate.setText(INTERMEDIATE);
		expert.setText(EXPERT);
		switch (currentLevel) {
			case BEGINNER:
				beginner.setText("• Beginner");
				cellSize = 60;
				numRows = 8;
				numCols = 8;
				numMines = 10;
				break;
			case INTERMEDIATE:
				intermediate.setText("• Intermediate");
				cellSize = 40;
				numRows = 16;
				numCols = 16;
				numMines = 40;
				break;
			case EXPERT:
				expert.setText("• Expert");
				cellSize = 40;
				numRows = 16;
				numCols = 30;
				numMines = 99;
				break;
			default:
				break;
		}
	}

	/**
	 * Show level and score
	 */
	public void topPanel() {
		JPanel topPanel = new JPanel(new GridLayout(1, 2));
		topPanel.setBackground(Color.WHITE);
		levelLb = new JLabel();
		scoreLb = new JLabel();
		levelLb.setForeground(Color.BLUE);
		scoreLb.setHorizontalAlignment(SwingConstants.RIGHT);
		scoreLb.setForeground(Color.BLUE);
		container.add(topPanel, BorderLayout.NORTH);
		// set font
		levelLb.setFont(FONT_TOP_PANEL);
		scoreLb.setFont(FONT_TOP_PANEL);
		topPanel.add(levelLb);
		topPanel.add(scoreLb);
	}

	/**
	 * Status bar to show number of cells remaining, flags, mines, timer
	 */
	public void statusBar() {
		JPanel statusPanel = new JPanel(new GridLayout(1, 4));
		statusPanel.setBackground(Color.WHITE);
		cellsLb = new JLabel();
		minesLb = new JLabel();
		flagsLb = new JLabel();
		timeLb = new JLabel();
		// Text color
		cellsLb.setForeground(Color.RED);
		minesLb.setForeground(Color.RED);
		flagsLb.setForeground(Color.RED);
		timeLb.setForeground(Color.RED);
		// Set font
		cellsLb.setFont(FONT_STATUS_BAR);
		minesLb.setFont(FONT_STATUS_BAR);
		flagsLb.setFont(FONT_STATUS_BAR);
		timeLb.setFont(FONT_STATUS_BAR);
		// Text orientation
		minesLb.setHorizontalAlignment(SwingConstants.CENTER);
		flagsLb.setHorizontalAlignment(SwingConstants.CENTER);
		timeLb.setHorizontalAlignment(SwingConstants.RIGHT);
		timer = new Timer(1000, new ActionListener() {
			String mesTimer = "Time: ";

			@Override
			public void actionPerformed(ActionEvent e) {
				timeLb.setText(mesTimer + setTime(second));
				second++;
			}

			public String setTime(int second) {
				int m = second / 60;
				int s = second % 60;
				String mString, sString;
				if (m < 10) {
					mString = "0" + m;
				} else {
					mString = String.valueOf(m);
				}
				if (s < 10) {
					sString = "0" + s;
				} else {
					sString = String.valueOf(s);
				}
				return mString + ":" + sString;
			}
		});
		// Add components to content-pane
		statusPanel.add(cellsLb);
		statusPanel.add(minesLb);
		statusPanel.add(flagsLb);
		statusPanel.add(timeLb);
		container.add(statusPanel, BorderLayout.SOUTH);
	}

	/**
	 * Change the number of cells/flags in status bar
	 */
	public void setTextChanged(int type) {
		switch (type) {
			case LEVEL_CHANGED:
				levelLb.setText("Level: " + currentLevel);
				break;
			case SCORE_CHANGED:
				scoreLb.setText("Your score: " + score);
				break;
			case CELLS_CHANGED:
				cellsLb.setText("Cells remaining: " + cellsLeft);
				break;
			case FLAGS_CHANGED:
				flagsLb.setText("Flags: " + numFlags);
				break;
			default:
				break;
		}
	}

	/**
	 * Return the number of mines near the selected location
	 */
	public int minesNear(int row, int col) {
		int mines = 0;
		// check mines in all directions
		// can use for loops instead
		mines += mineAt(row - 1, col - 1);
		mines += mineAt(row - 1, col);
		mines += mineAt(row - 1, col + 1);
		mines += mineAt(row, col - 1);
		mines += mineAt(row, col + 1);
		mines += mineAt(row + 1, col - 1);
		mines += mineAt(row + 1, col);
		mines += mineAt(row + 1, col + 1);
		return mines;
	}

	/**
	 * Open all the mines near blank cell
	 */
	public void openCellsAround(int row, int col) {
		int rowSelected, colSelected;
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				rowSelected = row + i;
				colSelected = col + j;
				if (rowSelected >= 0 && rowSelected < numRows && colSelected >= 0 && colSelected < numCols)
					if (btnCells[rowSelected][colSelected].isEnabled() && !flags[rowSelected][colSelected]) {
						btnCells[rowSelected][colSelected].setBackground(BGCOLOR_REVEALED);
						btnCells[rowSelected][colSelected].setForeground(FGCOLOR_REVEALED);
						btnCells[rowSelected][colSelected].setEnabled(false);
						cellsLeft--;
						score++;
						setTextChanged(CELLS_CHANGED);
						setTextChanged(SCORE_CHANGED);
						if (minesNear(rowSelected, colSelected) == 0)
							listCells.add(new Point(rowSelected, colSelected));
						else
							btnCells[rowSelected][colSelected].setText(minesNear(rowSelected, colSelected) + "");
					}
			}
		processCellsWaiting();
	}

	/**
	 * process the list cells that each cell is zero-cell
	 */
	public void processCellsWaiting() {
		int rowSelected, colSelected;
		Point point;
		if (listCells.size() > 0) {
			point = listCells.get(0);
			listCells.remove(0);
			rowSelected = point.getRowSelected();
			colSelected = point.getColSelected();
			openCellsAround(rowSelected, colSelected);
		}
	}

	/**
	 * return 1 if there's a mine at row,col or 0 if there isn't
	 */
	public int mineAt(int row, int col) {
		if (row >= 0 && row < numRows && col >= 0 && col < numCols && mines[row][col])
			return 1;
		else
			return 0;
	}

	/**
	 * Show message dialog
	 */
	public void notification(Component parent, Object message, String title, int messageType) {
		JOptionPane.showMessageDialog(parent, message, title, messageType);
	}

	/**
	 * Add a player in file
	 *
	 * @param player has high score
	 */
	public void addPlayerToTop(Player player) {
		HighScores hScore = new HighScores();
		List<Player> list = hScore.getListTopPlayer();
		if (list.size() < 10)
			list.add(player);
		else {
			list.remove(list.size() - 1);
			list.add(player);
		}
		// Sort by decreasing
		Collections.sort(list, (o1, o2) -> {
			int score1 = o1.getScore();
			int score2 = o2.getScore();
			if (score1 > score2)
				return -1;
			else if (score1 == score2)
				return 0;
			else
				return 1;
		});
		hScore.setListTopPlayer(list);
		hScore.writeData();
	}

	/**
	 * Check score whether it's high score or not
	 */
	public boolean isHighScore(int score) {
		if (score == 0)
			return false;
		HighScores hScore = new HighScores();
		List<Player> list = hScore.getListTopPlayer();
		int numPlayer = list.size();
		if (numPlayer < 10)
			return true;
		else if (list.get(numPlayer - 1).getScore() < score)
			return true;
		return false;
	}

	/**
	 * Show input dialog and type name player
	 */
	public void enterName() {
		String name = JOptionPane.showInputDialog(this, "Enter your name", "New high score",
				JOptionPane.QUESTION_MESSAGE);
		if (name != null) {
			Player player = new Player(name, score);
			addPlayerToTop(player);
		}
	}

	/**
	 * Handle event when mouse clicked
	 * Determine the (row, col) of the JButton that triggered the event
	 */
	private class CellMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = -1;
			int colSelected = -1;
			// Get the source object that fired the Event
			JButton source = (JButton) e.getSource();
			// Scan all rows and columns and match with the source object
			boolean found = false;
			for (int row = 0; row < numRows && !found; row++)
				for (int col = 0; col < numCols && !found; col++)
					if (source == btnCells[row][col]) {
						rowSelected = row;
						colSelected = col;
						found = true;
					}
			// Left-click to reveal a cell; Right-click to plant/remove the flag
			if (e.getButton() == MouseEvent.BUTTON1 && !flags[rowSelected][colSelected]) {// Left-button clicked
				if (mines[rowSelected][colSelected]) {
					timer.stop();
					btnCells[rowSelected][colSelected].setBackground(BGCOLOR_REVEALED_MINE);
					btnCells[rowSelected][colSelected].setIcon(new ImageIcon(minesImage));
					notification(MineSweeper.this, "Game over!", "Oops", 0);
					if (isHighScore(score))
						enterName();
					container.remove(gamePanel);
					initGame();
					container.validate();
					container.repaint();
				} else {
					btnCells[rowSelected][colSelected].setBackground(BGCOLOR_REVEALED);
					btnCells[rowSelected][colSelected].setForeground(FGCOLOR_REVEALED);
					btnCells[rowSelected][colSelected].setEnabled(false);
					cellsLeft--;
					score++;
					setTextChanged(CELLS_CHANGED);
					setTextChanged(SCORE_CHANGED);
					if (minesNear(rowSelected, colSelected) != 0)
						btnCells[rowSelected][colSelected].setText(minesNear(rowSelected, colSelected) + "");
					else
						openCellsAround(rowSelected, colSelected);
				}
			} else if (e.getButton() == MouseEvent.BUTTON3)
				if (btnCells[rowSelected][colSelected].isEnabled())
					if (!flags[rowSelected][colSelected]) {
						numFlags++;
						setTextChanged(FLAGS_CHANGED);
						flags[rowSelected][colSelected] = true;
						btnCells[rowSelected][colSelected].setIcon(new ImageIcon(flagsImage));
					} else {
						numFlags--;
						setTextChanged(FLAGS_CHANGED);
						flags[rowSelected][colSelected] = false;
						btnCells[rowSelected][colSelected].setIcon(null);
					}
			if (cellsLeft == 0) {
				timer.stop();
				notification(MineSweeper.this, "You win!", "Congratulation", 1);
				container.remove(gamePanel);
				initGame();
				container.validate();
				container.repaint();
			}
		}
	}

	/**
	 * Handle event when menu clicked
	 */
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String itemClicked = e.getActionCommand();
			switch (itemClicked) {
				case "New Game":
					container.remove(gamePanel);
					initGame();
					break;
				case "Reset Game":
					reset();
					break;
				case "Exit":
					System.exit(1);
					break;
				case "High Scores":
					new HSDialog();
					break;
				case BEGINNER:
					container.remove(gamePanel);
					currentLevel = BEGINNER;
					initGame();
					break;
				case INTERMEDIATE:
					container.remove(gamePanel);
					currentLevel = INTERMEDIATE;
					initGame();
					break;
				case EXPERT:
					container.remove(gamePanel);
					currentLevel = EXPERT;
					initGame();
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MineSweeper();
			}
		});
	}

}
