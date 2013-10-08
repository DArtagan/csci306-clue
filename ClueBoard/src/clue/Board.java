package clue;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Board {
	protected static int numRows;
	protected static int numCols;
	private String[] config;
	private Map<Character, String> rooms;

	public Board() {
		rooms = new HashMap<Character, String>();
	}

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
					// isDoorway already checks that direction is not NONE, 
					// default case should never happen.
			}
			return adjCells;
		} else if(cell.isRoom()) {
			return adjCells;
			// Room cells (that are not doors) don't have any adjacencies.
		}

		adjList.add(cell.top);
		adjList.add(cell.right);
		adjList.add(cell.bottom);
		adjList.add(cell.left);
		// Here, we use the fact that LinkedLists and arrays are both ordered
		// to associate each link with its proper door direction. If we are
		// moving up, we may only enter doors with a direction of DOWN, etc.
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
		// This is the initial setup function that calls our recursive calcTargets().
		HashSet<Integer> targetList = new HashSet<Integer>();
		HashSet<Integer> visitedList = new HashSet<Integer>();
		steps = steps + 1;
		if(getCellAt(index).isDoorway()) {
			targetList = calcTargets(getAdjList(index).get(0), steps - 1, targetList, visitedList);
			targetList.remove(index);
		} else {
			targetList = calcTargets(index, steps, targetList, visitedList);
		}
		// Ssh, this was a Set of cells all along.
		HashSet<BoardCell> targetCells = new HashSet<BoardCell>();
		for(int target : targetList) {
			targetCells.add(getCellAt(target));
		}
		return targetCells;
	}

	private HashSet<Integer> calcTargets(int start, int steps, HashSet<Integer> list, HashSet<Integer> visited) {
		// This is our recursive function that creates the targets list.
		steps = steps - 1;
		visited.add(start);
		if(getCellAt(start).isDoorway()) {
			list.add(start);
		} else if(steps == 0) {
			list.add(start);
		} else {
			for(Integer adjCell : getAdjList(start)) {
				HashSet<Integer> visitedTemp = new HashSet<Integer>(visited);
				if(!visited.contains(adjCell)) {
					list = calcTargets(adjCell, steps, list, visitedTemp);
				}
			}
		}
		return list;
	}
}
