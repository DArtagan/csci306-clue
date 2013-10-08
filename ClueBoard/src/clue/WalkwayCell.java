package clue;

public class WalkwayCell extends BoardCell {
	public WalkwayCell(int i, int rows, int cols) {
		super(i, rows, cols);
	}
	
	public boolean isWalkway() {
		return true;
	}
}
