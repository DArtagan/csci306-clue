package clue;

public class RoomCell {
	public enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public boolean isWalkway() {
		return false;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getRoomInitial() {
		return roomInitial;
	}
}
