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
	private Integer top = null;		// Setting these as Integer so 
	private Integer right = null;	// that they can be null.
	private Integer bottom = null;
	private Integer left = null;
	
	// Constructors
	public Cell(int index) {
		if((index - MAX_COL) >= 0)
			this.top = index - MAX_COL;
		if((index + MAX_COL) < (MAX_ROW*MAX_COL))
			this.bottom = index + MAX_COL;
		if(index > 0)
			this.left = index - 1;
		if(index < (MAX_ROW*MAX_COL - 1))
			this.right = index + 1;
	}
	
	public Cell(int row, int col) {
		
	}
	
	// Methods
	
	public void calcAdjacencies() {
		
	}

	public LinkedList<Integer> startTargets(int start, int steps) {
		return null;
	}

	public int calcIndex(int row, int col) {
		return 0;
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
