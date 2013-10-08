package clue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Board {
	// Variables
	protected static int numRows = 0;
	protected static int numCols = 0;
	private String [] config;
	protected Map<Character, String> rooms;
	
	public Board() {
		rooms = new HashMap<Character, String>();
	}
	
	// Methods
	public void loadConfigFiles(String board, String legend) throws FileNotFoundException, BadConfigFormatException {
		// Import Legend
		FileReader reader2 = new FileReader(legend);
		Scanner in2 = new Scanner(reader2);
		while(in2.hasNextLine()) {
			String line = in2.nextLine();
			String[] parts = line.split(", ");
			if(parts.length != 2 || parts[0] == "" || parts[1] == "") {
				in2.close();
				throw new BadConfigFormatException("Legend is malformed.");
			}
			this.rooms.put(parts[0].charAt(0), parts[1]);
		}
		in2.close();
		
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
				if(col_count != parts.length) {
					in1.close();
					throw new BadConfigFormatException("Line length mismatch");
				}
			}
			row_count += 1;
		}
		in1.close();
		numCols = col_count;
		numRows = row_count;
		String [] tempConfig = configString.split(",");
		
		for(String i: tempConfig) {
			char key = i.charAt(0);
			String value = rooms.get(key);
			if(value == null) throw new BadConfigFormatException("Invalid room character in board config.");
		}
		this.config = tempConfig;
	}
	
	public int calcIndex(int row, int col) {
		return (row * numCols) + col;
	}
	
	public RoomCell getRoomCellAt(int row, int col) {
		int index = this.calcIndex(row, col);
		return new RoomCell(index, this.config[index]);
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
		else return new RoomCell(i, this.config[i]);
	}

	public LinkedList<Integer> getAdjList(int calcIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashSet<BoardCell> getTargets(int index, int steps) {
		HashSet<Integer> targetList = new HashSet<Integer>();
		HashSet<Integer> visitedList = new HashSet<Integer>();
		steps = steps + 1;
		targetList = calcTargets(index, steps, targetList, visitedList);
		// We want a set of cells, so make them be.
		HashSet<BoardCell> targetCells = new HashSet<BoardCell>();
		for(int target : targetList) {
			targetCells.add(getCellAt(target));
		}
		return targetCells;
	}

	private boolean youCanGoHere(BoardCell cell, RoomCell.DoorDirection direction, HashSet<Integer> visited) {
		// The cell is either a walkway, or a door with the correct direction.
		return ((!visited.contains(cell.index)) &&
		   (cell.isWalkway() || (cell.isDoorway() && ((RoomCell) cell).getDoorDirection() == direction)));
	}

	private HashSet<Integer> calcTargets(int start, int steps, HashSet<Integer> list, HashSet<Integer> visited) {
		steps = steps - 1;
		BoardCell cell = getCellAt(start);
		visited.add(start);
		if(steps == 0) {
			list.add(start);
		} else {
			HashSet<Integer> visited1 = new HashSet<Integer>(visited);
			if(cell.top != null && youCanGoHere(getCellAt(cell.top), RoomCell.DoorDirection.DOWN, visited1)) {
				list = calcTargets(cell.top, steps, list, visited1);
			}

			HashSet<Integer> visited2 = new HashSet<Integer>(visited);
			if(cell.right != null && youCanGoHere(getCellAt(cell.top), RoomCell.DoorDirection.LEFT, visited2)) {
				list = calcTargets(cell.top, steps, list, visited1);
			}

			HashSet<Integer> visited3 = new HashSet<Integer>(visited);
			if(cell.bottom != null && youCanGoHere(getCellAt(cell.top), RoomCell.DoorDirection.UP, visited3)) {
				list = calcTargets(cell.top, steps, list, visited1);
			}

			HashSet<Integer> visited4 = new HashSet<Integer>(visited);
			if(cell.left != null && youCanGoHere(getCellAt(cell.top), RoomCell.DoorDirection.RIGHT, visited4)) {
				list = calcTargets(cell.top, steps, list, visited1);
			}
		}
		return list;
	}

	public void calcAdjacencies() {
		// TODO Auto-generated method stub
		
	}
}
