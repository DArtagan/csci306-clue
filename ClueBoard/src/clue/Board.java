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
	
	public int calcIndex(int row, int col) {
		return 0;
	}
	
	public RoomCell getRoomCellAt(int row, int col) {
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
