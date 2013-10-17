package clue;

public class WalkwayCell extends BoardCell {
	public WalkwayCell(int i, int numRows, int numCols) {
		super(i, numRows, numCols);
	}
	
	public boolean isWalkway() {
		return true;
	}
}
