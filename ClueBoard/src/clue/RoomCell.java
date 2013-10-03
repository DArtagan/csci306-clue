package clue;

public class RoomCell {
	private enum DoorDirection {NORTH, SOUTH, EAST, WEST, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public boolean isWalkway() {
		return false;
	}
}
