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

	// Methods
	public void calcAdjacencies() {
		
	}

	public LinkedList<Integer> startTargets(int start, int steps) {
		this.targetList = new HashSet<Integer>();
		steps = steps + 1;
		this.targetList = calcTargets(start, steps, this.targetList);
		this.targetList.remove(start);
		return new LinkedList<Integer>(targetList);
	}
	
	private HashSet<Integer> calcTargets(int start, int steps, HashSet<Integer> list) {
		steps = steps - 1;
		Cell cell = new Cell(start);
		if(steps == 0) {
			list.add(start);
		} else {
			if(cell.top != null) list = calcTargets(cell.top, steps, list);
			if(cell.right != null) list = calcTargets(cell.right, steps, list);
			if(cell.bottom != null) list = calcTargets(cell.bottom, steps, list);
			if(cell.left != null) list = calcTargets(cell.left, steps, list);
		}
		
		return list;
	}

	public int calcIndex(int row, int col) {
		return (row * MAX_ROW) + col;
	}
	
	public Set<Integer> getTargets() {
		return null;
	}
	
	public LinkedList<Integer> getAdjList() {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if(this.top != null) adjList.add(this.top);
		if(this.right != null) adjList.add(this.right);
		if(this.bottom != null) adjList.add(this.bottom);
		if(this.left != null) adjList.add(this.left);
		return adjList;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Cell twoFiftyFour = new Cell(0);
		list = twoFiftyFour.startTargets(0, 4);
		System.out.println(list);
	}
}
