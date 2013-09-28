package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import clue.Cell;

public class TestAdjacencyList {
	Cell zero, fiveTwentyEight, twoNinetyNine, fourThirtySix, twoFiftyFour, oneFiftyNine;
	
	@Before
	public void setUp() {
		Cell zero = new Cell(0);
		Cell fiveTwentyEight = new Cell(0);
		Cell twoNinetyNine = new Cell(0);
		Cell fourThirtySix = new Cell(0);
		Cell twoFiftyFour = new Cell(0);
		Cell oneFiftyNine = new Cell(0);
		
	}

	@Test
	public void testAdjacencyListTopLeftCorner() {
		LinkedList<Integer> list = zero.getAdjList();
		assert(list.contains(1));
		assert(list.contains(23));
		assertEquals(2, list.size());
	}

	@Test
	public void testAdjacencyListRightEdge() {
		LinkedList<Integer> list = fourThirtySix.getAdjList();
		assert(list.contains(413));
		assert(list.contains(435));
		assert(list.contains(459));
		assertEquals(3, list.size());
	}

	@Test
	public void testAdjacencyListLeftEdge() {
		LinkedList<Integer> list = twoNinetyNine.getAdjList();
		assert(list.contains(276));
		assert(list.contains(300));
		assert(list.contains(322));
		assertEquals(3, list.size());
	}

	@Test
	public void testAdjacencyListBottomRightCorner() {
		LinkedList<Integer> list = fiveTwentyEight.getAdjList();
		assert(list.contains(505));
		assert(list.contains(527));
		assertEquals(2, list.size());
	}

	@Test
	public void testSecondColumnMiddleOfGrid() {
		LinkedList<Integer> list = twoFiftyFour.getAdjList();
		assert(list.contains(231));
		assert(list.contains(255));
		assert(list.contains(277));
		assert(list.contains(253));
		assertEquals(4, list.size());
	}

	@Test
	public void testSecondFromLastColumnMiddleOfGrid() {
		LinkedList<Integer> list = oneFiftyNine.getAdjList();
		assert(list.contains(136));
		assert(list.contains(160));
		assert(list.contains(182));
		assert(list.contains(158));
		assertEquals(4, list.size());
	}
}
