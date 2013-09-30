/* 
 * Note: We decided (and cleared with the professor) that we wanted to
 * implement our clue board as a collection of linked cells.
 * Thus, our class is titled "Cell" and not "IntBoard".
 */

package clue;

import java.util.LinkedList;
import java.util.Set;

public class Cell {
	// Constants
	int MAX_ROW = 23;	// One beyond the board row size
	int MAX_COL = 23;	// One beyond the board col size
	
	// Variables
	private Integer index = null;
	private Integer top = null;		// Setting these as Integer so 
	private Integer right = null;	// that they can be null.
	private Integer bottom = null;
	private Integer left = null;
	
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
	
	public Cell(int row, int col) {
		
	}
	
	// Methods
	public void calcAdjacencies() {
		
	}

	public LinkedList<Integer> startTargets(int start, int steps) {
		LinkedList<Integer> stepList = new LinkedList<Integer>();
		stepList.addAll(calcTargets(start, steps, stepList));
		return stepList;
	}
	
	public LinkedList<Integer> calcTargets(int start, int steps, LinkedList<Integer> list) {
		if(steps == 0) {
			return list;
		} else {
			list.addAll(calcTargets((new Cell(start)).top, steps, list));
			list.addAll(calcTargets((new Cell(start)).right, steps, list));
			list.addAll(calcTargets((new Cell(start)).bottom, steps, list));
			list.addAll(calcTargets((new Cell(start)).left, steps, list));
			return list;
		}
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
}
