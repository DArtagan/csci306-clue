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
	
	// Variables - initialize to impossible values
	private int index = -1;
	private int top = -1;
	private int right = -1;
	private int bottom = -1;
	private int left = -1;
	
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
	
	public LinkedList<Integer> calcTargets(int start, int steps) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		if(this.top != -1) list.addAll(calcTargets((new Cell(start)).top, steps, list));
		if(this.right != -1) list.addAll(calcTargets((new Cell(start)).right, steps, list));
		if(this.bottom != -1) list.addAll(calcTargets((new Cell(start)).bottom, steps, list));
		if(this.left != -1) list.addAll(calcTargets((new Cell(start)).left, steps, list));
		return list;
	}
	
	private LinkedList<Integer> calcTargets(int start, int steps, LinkedList<Integer> list) {
		System.out.println(steps);
		if(steps == 0) {
			return null;
		} else {
			--steps;
			if(this.top != -1) list.addAll(calcTargets((new Cell(start)).top, steps, list));
			if(this.right != -1) list.addAll(calcTargets((new Cell(start)).right, steps, list));
			if(this.bottom != -1) list.addAll(calcTargets((new Cell(start)).bottom, steps, list));
			if(this.left != -1) list.addAll(calcTargets((new Cell(start)).left, steps, list));
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
		if(this.top != -1) adjList.add(this.top);
		if(this.right != -1) adjList.add(this.right);
		if(this.bottom != -1) adjList.add(this.bottom);
		if(this.left != -1) adjList.add(this.left);
		return adjList;
	}
}
