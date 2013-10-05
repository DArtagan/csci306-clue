package clue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	// Constants
	int MAX_ROW = 23;	// One beyond the board row size
	int MAX_COL = 23;	// One beyond the board col size
	
	// Variables
	private ArrayList<BoardCell> cells;
	private int numRows;
	private int numCols;
	private String [] config;
	private Map<Character, String> rooms = new HashMap<Character, String>();
	
	// Methods
	public void loadConfigFiles() throws FileNotFoundException {
		// Import Clue Board
		FileReader reader1 = new FileReader("ClueBoard.csv");
		Scanner in1 = new Scanner(reader1);
		String configString = "";
		while(in1.hasNextLine()) {
			configString += in1.nextLine();
		}
		this.config = configString.split(",");
		
		// Import Legend
		FileReader reader2 = new FileReader("legend.txt");
		Scanner in2 = new Scanner(reader2);
		while(in2.hasNextLine()) {
			String line = in2.nextLine();
			String[] parts = line.split(", ");
			this.rooms.put(parts[0].charAt(0), parts[1]);
		}
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
		System.out.println(board.getRooms().get('Y'));
	}
}
