package clue;

public class WalkwayCell extends BoardCell {
	private int index;
	
	public WalkwayCell(int i) {
		super(i);
		index = i;
	}
	
	public boolean isWalkway() {
		return true;
	}
}
