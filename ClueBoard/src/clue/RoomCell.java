package clue;

public class RoomCell {
	public enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell() {
		
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getRoomInitial() {
		return roomInitial;
	}
	
	public boolean isDoorway() {
		return true;
	}
	
	public boolean isRoom() {
		return true;
	}
}
