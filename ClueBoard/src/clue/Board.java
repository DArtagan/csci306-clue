package clue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private ArrayList<BoardCell> cells;
	private Map<Character,String> rooms;
	private int numRows;
	private int numCols;
	private String [] config;
	
	public void loadConfigFiles() throws FileNotFoundException {
		FileReader reader = new FileReader("ClueBoard.csv");
		Scanner in = new Scanner(reader);
		String configString = "";
		while(in.hasNextLine()) {
			configString += in.nextLine();
		}
		this.config = configString.split(",");
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

	public static void main(String[] args) {
		Board board = new Board();
		try {
			board.loadConfigFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
