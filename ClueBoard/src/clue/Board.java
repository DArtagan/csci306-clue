package clue;

import java.util.ArrayList;
import java.util.Map;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numCols;
	
	public void loadConfigFiles() {
		
	}
	
	public int calcIndex() {
		return 0;
	}
	
	public RoomCell GetRoomCellAt() {
		return null;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumCols() {
		return numCols;
	}

}
