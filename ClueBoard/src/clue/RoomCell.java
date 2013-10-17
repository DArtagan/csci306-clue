package clue;

public class RoomCell extends BoardCell {
	public static enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell(int i, int numRows, int numCols, String config) {
		super(i, numRows, numCols);
		
		// Door handling.
		doorDirection = DoorDirection.NONE;
		roomInitial = config.charAt(0);
		String roomSpec = config;
		if(roomSpec.length() > 1) { // Then we have a door.
			char door = roomSpec.charAt(1);
			if(door == 'U') doorDirection = DoorDirection.UP;
			else if(door == 'R') doorDirection = DoorDirection.RIGHT;
			else if(door == 'D') doorDirection = DoorDirection.DOWN;
			else if(door == 'L') doorDirection = DoorDirection.LEFT;
		}
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getRoomInitial() {
		return roomInitial;
	}

	public boolean isRoom() {
		return true;
	}

	public boolean isDoorway() {
		if(this.doorDirection != DoorDirection.NONE) return true;
		else return false;
	}
}
