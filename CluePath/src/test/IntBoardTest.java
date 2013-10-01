package test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clue.IntBoard;

public class IntBoardTest {
	IntBoard board;
	LinkedList<Integer> list;
	Set<Integer> targets;
	
	@Before
	public void setUp() {
		board = new IntBoard();
		list = new LinkedList<Integer>();
	}

	@Test
	public void testAdjacencyListTopLeftCorner() {
		list = board.getAdjList(0);
		assert(list.contains(1));
		assert(list.contains(23));
		assertEquals(2, list.size());
	}

	@Test
	public void testAdjacencyListRightEdge() {
		list = board.getAdjList(436);
		assert(list.contains(413));
		assert(list.contains(435));
		assert(list.contains(459));
		assertEquals(3, list.size());
	}

	@Test
	public void testAdjacencyListLeftEdge() {
		list = board.getAdjList(299);
		assert(list.contains(276));
		assert(list.contains(300));
		assert(list.contains(322));
		assertEquals(3, list.size());
	}

	@Test
	public void testAdjacencyListBottomRightCorner() {
		list = board.getAdjList(528);
		assert(list.contains(505));
		assert(list.contains(527));
		assertEquals(2, list.size());
	}

	@Test
	public void testSecondColumnMiddleOfGrid() {
		list = board.getAdjList(254);
		assert(list.contains(231));
		assert(list.contains(255));
		assert(list.contains(277));
		assert(list.contains(253));
		assertEquals(4, list.size());
	}

	@Test
	public void testSecondFromLastColumnMiddleOfGrid() {
		list = board.getAdjList(159);
		assert(list.contains(136));
		assert(list.contains(160));
		assert(list.contains(182));
		assert(list.contains(158));
		assertEquals(4, list.size());
	}

	@Test
	public void testCalcIndex1() {
		assertEquals(528, board.calcIndex(22, 22));
	}

	@Test
	public void testCalcIndex2() {
		assertEquals(254, board.calcIndex(11, 1));
	}
	
	@Test
	public void testTargets254_2() {
		board.startTargets(11, 1, 2);
		targets = board.getTargets();
		assert(targets.contains(208));
		assert(targets.contains(232));
		assert(targets.contains(256));
		assert(targets.contains(278));
		assert(targets.contains(300));
		assert(targets.contains(276));
		assert(targets.contains(230));
		assertEquals(7, targets.size());
	}

	@Test
	public void testTargets0_3() {
		board.startTargets(0, 0, 3);
		targets = board.getTargets();
		assert(targets.contains(3));
		assert(targets.contains(25));
		assert(targets.contains(47));
		assert(targets.contains(69));
		assert(targets.contains(23));
		assert(targets.contains(1));
		assertEquals(6, targets.size());
	}
}
