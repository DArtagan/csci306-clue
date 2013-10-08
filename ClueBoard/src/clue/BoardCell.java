/* 
 * Note: We decided (and cleared with the professor) that we wanted to
 * implement our clue board as a collection of linked cells.
 * Thus, our class is titled "Cell" and not "IntBoard".
 */

package clue;

import java.util.LinkedList;

public class BoardCell {
	// Constants
	static final int MAX_ROW = Board.numRows;	// One beyond the board row size
	static final int MAX_COL = Board.numCols;	// One beyond the board col size

	// Variables - initialize to impossible values
	protected Integer index = null;
	protected Integer top = null;
	protected Integer right = null;
	protected Integer bottom = null;
	protected Integer left = null;

	// Constructors
	public BoardCell(int i) {
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

	public boolean isWalkway() {
		return false;
	}

	public boolean isRoom() {
		return false;
	}

	public boolean isDoorway() {
		return false;
	}

	public LinkedList<Integer> getAdjList() {
		LinkedList<Integer> adjList = new LinkedList<Integer>();
		if(this.top != null) adjList.add(this.top);
		if(this.right != null) adjList.add(this.right);
		if(this.bottom != null) adjList.add(this.bottom);
		if(this.left != null) adjList.add(this.left);
		return adjList;
	}

	public int getIndex() {
		return this.index;
	}
}
