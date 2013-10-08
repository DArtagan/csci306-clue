package clue;

public class WalkwayCell extends BoardCell {
	public WalkwayCell(int i) {
		super(i);
		this.index = i;
	}
	
	public boolean isWalkway() {
		return true;
	}
}
