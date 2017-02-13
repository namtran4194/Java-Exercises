package ai;

import adapter.Board;
import adapter.Cell;
import adapter.Seed;
import ui.GameMain;

public abstract class AIPlayer {
	protected int ROWS = GameMain.ROWS; // number of rows
	protected int COLS = GameMain.COLS; // number of columns

	protected Cell[][] cells; // the board's ROWS-by-COLS array of Cells
	protected Seed mySeed; // computer's seed
	protected Seed oppSeed; // opponent's seed

	public AIPlayer(Board board) {
		cells = board.cells;
	}

	public void setSeed(Seed seed) {
		this.mySeed = seed;
		oppSeed = (mySeed == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
	}

	/** Abstract method to get next move. Return int[2] of {row, col} */
	abstract int[] move(); // to be implemented by subclasses
}
