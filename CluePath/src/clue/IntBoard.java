package clue;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	// Constants
	int MAX_ROW = 23;	// One beyond the board row size
	int MAX_COL = 23;	// One beyond the board col size
	
	// Variables
	private Map<Integer, LinkedList<Integer>> adjMap;
	private boolean[] visited;
	
	// Constructors
	public IntBoard() {
		
	}

	// Methods
	public void calcAdjacencies() {

	}

	public void startTargets(int row, int col, int numSteps) {

	}
	
	private LinkedList<Integer> calcTargets(int thisCell, int numSteps) {
		if(steps == 0) {
			list.add(start);
			return list;
		} else {
			--steps;
			for (int cellIndex : list) {
				if(cellIndex != -1) { 
					list.addAll(calcTargets(cell.right, steps, start, list));
				}
			}
			return list;
		}
	}
	
	public Set<Integer> getTargets() {
		return null;
	}

	public LinkedList<Integer> getAdjList(int cell) {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if ((cell - MAX_COL) >= 0)
			adjList.add(cell - MAX_COL);
		if ((cell + MAX_COL) < (MAX_ROW*MAX_COL))
			adjList.add(cell + MAX_COL);
		if ((cell % MAX_COL) > 0)
			adjList.add(cell - 1);
		if (((cell + 1) % MAX_COL) > 0)
			adjList.add(cell + 1);
		return adjList;
	}
		
	public int calcIndex(int row, int col) {
		if (row < 0 || col < 0) {
			return -1;
		}
		return (row * MAX_ROW) + col;
	}
}
