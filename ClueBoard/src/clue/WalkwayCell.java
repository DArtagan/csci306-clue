package clue;

public class WalkwayCell extends BoardCell {
	private int index;
	
	public WalkwayCell(int i) {
		super();
		index = i;
	}
	
	public boolean isWalkway() {
		return true;
	}
}
