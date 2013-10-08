package clue;

public class WalkwayCell extends BoardCell {
	public WalkwayCell(int i) {
		super(i);
	}
	
	public boolean isWalkway() {
		return true;
	}
}
