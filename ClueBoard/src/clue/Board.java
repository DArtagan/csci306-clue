package clue;

import java.io.FileReader;
import java.io.IOException;
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

	// Constructor
	public Board() {
		rooms = new HashMap<Character, String>();
	}

	// Methods
	public void loadConfigFiles(String board, String legend) throws IOException, BadConfigFormatException {
		// Import Legend
		FileReader legendReader = new FileReader(legend);
		Scanner legendIn = new Scanner(legendReader);
		while(legendIn.hasNextLine()) {
			String line = legendIn.nextLine();
			String[] parts = line.split(", ");
			if(parts.length != 2 || parts[0] == "" || parts[1] == "") {
				legendIn.close();
				legendReader.close();
				throw new BadConfigFormatException("Legend is malformed.");
			}
			this.rooms.put(parts[0].charAt(0), parts[1]);
		}
		legendIn.close();
		legendReader.close();

		// Import Clue Board
		FileReader boardReader = new FileReader(board);
		Scanner boardIn = new Scanner(boardReader);
		String configString = "";
		int col_count = -1;
		int row_count = 0;
		while(boardIn.hasNextLine()) {
			String line = boardIn.nextLine();
			configString += line + ",";
			String[] parts = line.split(",");
			if(col_count == -1) {
				col_count = parts.length;
			} else {
				if(col_count != parts.length) {
					boardIn.close();
					boardReader.close();
					throw new BadConfigFormatException("Line length mismatch");
				}
			}
			row_count += 1;
		}
		boardIn.close();
		boardReader.close();
		numCols = col_count;
		numRows = row_count;
		String[] tempConfig = configString.split(",");

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

	public BoardCell getCellAt(int i) {
		if(config[i].equals("W")) {
			return new WalkwayCell(i);
		}
		else {
			return new RoomCell(i, this.config[i]);
		}
	}

	public LinkedList<Integer> getAdjList(int index) {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		LinkedList<Integer> adjCells = new LinkedList<Integer>();
		BoardCell cell = getCellAt(index);
		if(cell.isDoorway()) {
			switch(((RoomCell) cell).getDoorDirection()) {
				case UP:
					adjCells.add(cell.top);
					break;
				case RIGHT:
					adjCells.add(cell.right);
					break;
				case DOWN:
					adjCells.add(cell.bottom);
					break;
				case LEFT:
					adjCells.add(cell.left);
					break;
				default:
					break;
					// Don't put a doorway into nothing... :(
			}
			return adjCells;
		} else if(cell.isRoom()) {
			return adjCells;
			// Room cells don't have any adjacencies.
		}

		adjList.add(cell.top);
		adjList.add(cell.right);
		adjList.add(cell.bottom);
		adjList.add(cell.left);
		
		RoomCell.DoorDirection[] cardinals = {RoomCell.DoorDirection.DOWN, RoomCell.DoorDirection.LEFT, RoomCell.DoorDirection.UP, RoomCell.DoorDirection.RIGHT};
		for(int i = 0; i<cardinals.length; ++i) {
			if(adjList.get(i) == null) {
				continue;
			}
			BoardCell cellTemp = getCellAt(adjList.get(i));
			if(cellTemp.isWalkway() || (cellTemp.isDoorway() && ((RoomCell) cellTemp).getDoorDirection() == cardinals[i])) {
				// The cell is either a walkway, or a door with the correct direction.
				// Just trust us, don't try to think about it.
				adjCells.add(cellTemp.index);
			}
		}
		return adjCells;
	}

	public HashSet<BoardCell> getTargets(int index, int steps) {
		HashSet<Integer> targetList = new HashSet<Integer>();
		HashSet<Integer> visitedList = new HashSet<Integer>();
		steps = steps + 1;
		targetList = calcTargets(index, steps, targetList, visitedList);
		// Ssh, this was a Set of cells all along.
		HashSet<BoardCell> targetCells = new HashSet<BoardCell>();
		for(int target : targetList) {
			targetCells.add(getCellAt(target));
		}
		return targetCells;
	}

	private HashSet<Integer> calcTargets(int start, int steps, HashSet<Integer> list, HashSet<Integer> visited) {
		steps = steps - 1;
		visited.add(start);
		if(steps == 0) {
			list.add(start);
		} else {
			LinkedList<Integer> adjCells = new LinkedList<Integer>();
			for(Integer adjCell : getAdjList(start)) {
				if(!visited.contains(adjCell)) {
					adjCells.add(adjCell);
				}
			}
			/*for(int i = 0; i<cardinals.length; ++i) {
				HashSet<Integer> visitedTemp = new HashSet<Integer>(visited);
				BoardCell cellTemp = getCellAt(adjCells.get(i));
				System.out.printf("Index: %s, isWalkway: %s, isDoorway: %s, direction: %s\n", cellTemp.index, cellTemp.isWalkway(), cellTemp.isDoorway(), ((RoomCell) cellTemp).getDoorDirection());
				if (!visited.contains(adjCells.get(i)) &&
					(cellTemp.isWalkway() || (cellTemp.isDoorway() && ((RoomCell) cellTemp).getDoorDirection() == cardinals[i])))
					list = calcTargets(adjCells.get(i), steps, list, visitedTemp);
			}
			/*HashSet<Integer> visited1 = new HashSet<Integer>(visited);
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
			}*/
		}
		return list;
	}
}
