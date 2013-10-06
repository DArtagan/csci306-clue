package clue;

public class RoomCell extends BoardCell {
	public enum DoorDirection {UP, RIGHT, DOWN, LEFT, NONE};
	private DoorDirection doorDirection = DoorDirection.NONE;
	private char roomInitial;
	
	public RoomCell(int row, int col) {
		int index = (row * Board.numCols) + col;
		String roomSpec = Board.config[index];
		roomInitial = roomSpec.charAt(0);
		int test = roomSpec.length();
		if (roomSpec.length() > 1) {
			char door = roomSpec.charAt(1);
			if(door == 'U') this.doorDirection = DoorDirection.UP;
			else if(door == 'R') this.doorDirection = DoorDirection.RIGHT;
			else if(door == 'D') this.doorDirection = DoorDirection.DOWN;
			else if(door == 'L') this.doorDirection = DoorDirection.LEFT;
		} else {
			this.doorDirection = DoorDirection.NONE;
		}
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
