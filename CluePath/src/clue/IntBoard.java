/* 
 * Note: We decided (and cleared with the professor) that we wanted to
 * implement our clue board as a collection of linked cells.
 * Thus, our class is titled "Cell" and not "IntBoard".
 */

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
		/*this.index = i;
		if((this.index - MAX_COL) >= 0)
			this.top = this.index - MAX_COL;
		if((this.index + MAX_COL) < (MAX_ROW*MAX_COL))
			this.bottom = this.index + MAX_COL;
		if((this.index % MAX_COL) > 0)
			this.left = this.index - 1;
		if(((this.index + 1) % MAX_COL) > 0)
			this.right = this.index + 1;*/
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
		return adjList;
	}
		
	public int calcIndex(int row, int col) {
		return (row * MAX_ROW) + col;
	}
}
