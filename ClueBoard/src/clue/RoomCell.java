package clue;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell(int i, int rows, int cols, String config) {
		super(i, rows, cols);
		doorDirection = DoorDirection.NONE;
		this.roomInitial = config.charAt(0);
		String roomSpec = config;

		if(roomSpec.length() > 1) { // Then we have a door.
			char door = roomSpec.charAt(1);
			if(door == 'U') this.doorDirection = DoorDirection.UP;
			else if(door == 'R') this.doorDirection = DoorDirection.RIGHT;
			else if(door == 'D') this.doorDirection = DoorDirection.DOWN;
			else if(door == 'L') this.doorDirection = DoorDirection.LEFT;
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
