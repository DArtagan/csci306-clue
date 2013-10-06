package clue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	// Variables
	private ArrayList<BoardCell> cells;
	protected static int numRows = 0;
	protected static int numCols = 0;
	protected static String [] config;
	protected static Map<Character, String> rooms = new HashMap<Character, String>();
	
	// Methods
	public void loadConfigFiles(String board, String legend) throws FileNotFoundException, BadConfigFormatException {
		// Import Legend
		FileReader reader2 = new FileReader(legend);
		Scanner in2 = new Scanner(reader2);
		while(in2.hasNextLine()) {
			String line = in2.nextLine();
			String[] parts = line.split(", ");
			if(parts.length != 2 || parts[0] == "" || parts[1] == "") throw new BadConfigFormatException("Legend is malformed.");
			this.rooms.put(parts[0].charAt(0), parts[1]);
		}
		
		// Import Clue Board
		FileReader reader1 = new FileReader(board);
		Scanner in1 = new Scanner(reader1);
		String configString = "";
		int col_count = -1;
		int row_count = 0;
		while(in1.hasNextLine()) {
			String line = in1.nextLine();
			configString += line + ",";
			String[] parts = line.split(",");
			if(col_count == -1) {
				col_count = parts.length;
			} else {
				if(col_count != parts.length) throw new BadConfigFormatException("Line length mismatch");
			}
			row_count += 1;
		}
		this.numCols = col_count;
		this.numRows = row_count;
		this.config = configString.split(",");
		
		for(String i: config) {
			char key = i.charAt(0);
			String value = rooms.get(key);
			if(value == null) throw new BadConfigFormatException("Invalid room character in board config.");
		}
	}
	
	public int calcIndex(int row, int col) {
		return (row * Board.numCols) + col;
	}
	
	public RoomCell getRoomCellAt(int row, int col) {
		return new RoomCell(row, col);
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

	public static void main(String[] args) throws BadConfigFormatException {
		Board board = new Board();
		try {
			board.loadConfigFiles("ClueBoard.csv", "legend.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(board.getRooms().get('Y'));
	}

	public BoardCell getCellAt(int i) {
		if(config[i] == "W") return new WalkwayCell(i);
		else return new RoomCell(i);
	}

	public LinkedList<Integer> getAdjList(int calcIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}

	public void calcTargets(int i, int j, int k) {
		// TODO Auto-generated method stub
		
	}

	public void calcAdjacencies() {
		// TODO Auto-generated method stub
		
	}
}
