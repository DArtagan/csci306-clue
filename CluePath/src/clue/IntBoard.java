package clue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
	private Set<Integer> targets;
	
	// Constructors
	public IntBoard() {
		adjMap = new HashMap<Integer, LinkedList<Integer>>(); 
		visited = new boolean[MAX_COL * MAX_ROW];
		targets = new HashSet<Integer>();
		calcAdjacencies();
	}

	// Methods
	public void calcAdjacencies() {
		for (int i = 0; i < (MAX_ROW * MAX_COL); ++i) {
			LinkedList<Integer> adjList = getAdjList(i);
			adjMap.put(i, adjList);
		}
	}

	public void calcTargets(int row, int col, int numSteps) {
		Arrays.fill(visited, false);
		visited[calcIndex(row, col)] = true;
		calcTargets(calcIndex(row, col), numSteps);
	}
	
	private void calcTargets(int thisCell, int numSteps) {
		LinkedList<Integer> adjacentCells = new LinkedList<Integer>();
		for (int adjCell : adjMap.get(thisCell)) {
			if (!visited[adjCell]) {
				adjacentCells.add(adjCell);
			}
		}
		for (int adjCell : adjacentCells) {
			visited[adjCell] = true;
			if (numSteps == 1) {
				targets.add(adjCell);
			} else {
				calcTargets(adjCell, numSteps-1);
			}
			visited[adjCell] = false;
		}
	}
	
	public Set<Integer> getTargets() {
		return targets;
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
