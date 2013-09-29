package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import clue.Cell;

public class TestAdjacencyList {
	Cell zero, fiveTwentyEight, twoNinetyNine, fourThirtySix, twoFiftyFour, oneFiftyNine;
	
	@SuppressWarnings("unused")
	@Before
	public void setUp() {
		zero = new Cell(0);
		fiveTwentyEight = new Cell(523);
		twoNinetyNine = new Cell(299);
		fourThirtySix = new Cell(436);
		twoFiftyFour = new Cell(254);
		oneFiftyNine = new Cell(159);
	}

	@Test
	public void testAdjacencyListTopLeftCorner() {
		Cell zero = new Cell(0);
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

	@Test
	public void testCalcIndex1() {
		assertEquals(528, fiveTwentyEight.calcIndex(22, 22));
	}

	@Test
	public void testCalcIndex2() {
		assertEquals(254, twoFiftyFour.calcIndex(11, 1));
	}
	
	@Test
	public void testStartTargets1() {
		assert(twoFiftyFour.startTargets(254, 2).contains(208));
		assert(twoFiftyFour.startTargets(254, 2).contains(232));
		assert(twoFiftyFour.startTargets(254, 2).contains(256));
		assert(twoFiftyFour.startTargets(254, 2).contains(278));
		assert(twoFiftyFour.startTargets(254, 2).contains(300));
		assert(twoFiftyFour.startTargets(254, 2).contains(276));
		assert(twoFiftyFour.startTargets(254, 2).contains(230));
		assertEquals(7, twoFiftyFour.startTargets(254, 2).size());
	}

	@Test
	public void testStartTargets2() {
		assert(zero.startTargets(0, 3).contains(3));
		assert(zero.startTargets(0, 3).contains(25));
		assert(zero.startTargets(0, 3).contains(47));
		assert(zero.startTargets(0, 3).contains(69));
		assert(zero.startTargets(0, 3).contains(23));
		assert(zero.startTargets(0, 3).contains(1));
		assertEquals(6, zero.startTargets(0, 3).size());
	}
}
