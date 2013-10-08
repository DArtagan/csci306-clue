package clue;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection;
	private char roomInitial;

	public RoomCell(int i, String config) {
		super(i);
		doorDirection = DoorDirection.NONE;
		this.roomInitial = config.charAt(0);
		String roomSpec = config;
		this.index = i;

		if(roomSpec.length() > 1) {
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

	public boolean isDoorway() {
		if(this.doorDirection != DoorDirection.NONE) return true;
		else return false;
	}

	public boolean isRoom() {
		return true;
	}
}
