package adapter;

/**
 * Save a cells' location in Minesweeper game
 */
public class Point {
	private int rowSelected;
	private int colSelected;

	public Point(int rowSelected, int colSelected) {
		this.rowSelected = rowSelected;
		this.colSelected = colSelected;
	}

	public int getRowSelected() {
		return rowSelected;
	}

	public int getColSelected() {
		return colSelected;
	}
}
