/* 
 * Note: We decided (and cleared with the professor) that we wanted to
 * implement our clue board as a collection of linked cells.
 * Thus, our class is titled "Cell" and not "IntBoard".
 */

package clue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Cell {
	// Constants
	int MAX_ROW = 23;	// One beyond the board row size
	int MAX_COL = 23;	// One beyond the board col size
	
	// Variables - initialize to impossible values
	private Integer index = null;
	private Integer top = null;
	private Integer right = null;
	private Integer bottom = null;
	private Integer left = null;
	private HashSet<Integer> targetList;
	
	// Constructors
	public Cell(int i) {
		this.index = i;
		if((this.index - MAX_COL) >= 0)
			this.top = this.index - MAX_COL;
		if((this.index + MAX_COL) < (MAX_ROW*MAX_COL))
			this.bottom = this.index + MAX_COL;
		if((this.index % MAX_COL) > 0)
			this.left = this.index - 1;
		if(((this.index + 1) % MAX_COL) > 0)
			this.right = this.index + 1;
	}

	public Set<Integer> getTargets(int steps) {
		this.targetList = new HashSet<Integer>();
		HashSet<Integer> visitedList = new HashSet<Integer>();
		steps = steps + 1;
		this.targetList = calcTargets(this.index, steps, this.targetList, visitedList);
		return this.targetList;
	}
	
	private HashSet<Integer> calcTargets(int start, int steps, HashSet<Integer> list, HashSet<Integer> visited) {
		steps = steps - 1;
		Cell cell = new Cell(start);
		visited.add(start);
		if(steps == 0) {
			list.add(start);
		} else {
			for (int adjCell : cell.getAdjList()) {
				HashSet<Integer> visitedTemp = new HashSet<Integer>(visited);
				if (!visited.contains(adjCell)) 
					list = calcTargets(adjCell, steps, list, visitedTemp);
			}
		}
		return list;
	}
	
	public LinkedList<Integer> getAdjList() {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if(this.top != null) adjList.add(this.top);
		if(this.right != null) adjList.add(this.right);
		if(this.bottom != null) adjList.add(this.bottom);
		if(this.left != null) adjList.add(this.left);
		return adjList;
	}

	public int calcIndex(int row, int col) {
		return (row * MAX_ROW) + col;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public static void main(String[] args) {
		Cell zero = new Cell(0);
		System.out.println(zero.getTargets(4));
	}
}
