package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clue.Cell;

public class TestAdjacencyList {
	Cell zero, fiveTwentyEight, twoNinetyNine, fourThirtySix, twoFiftyFour, oneFiftyNine;
	Set<Integer> list;
	
	@Before
	public void setUp() {
		zero = new Cell(0);
		fiveTwentyEight = new Cell(528);
		twoNinetyNine = new Cell(299);
		fourThirtySix = new Cell(436);
		twoFiftyFour = new Cell(254);
		oneFiftyNine = new Cell(159);
		Set<Integer> list = new HashSet<Integer>();
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
	public void testCalcIndex528() {
		assertEquals(528, fiveTwentyEight.calcIndex(22, 22));
	}

	@Test
	public void testCalcIndex254() {
		assertEquals(254, twoFiftyFour.calcIndex(11, 1));
	}
	
	@Test
	public void testCalcIndex0() {
		assertEquals(0, zero.calcIndex(0, 0));
	}
	
	@Test
	public void testStartTargets1() {
		list = twoFiftyFour.getTargets(2);
		assert(list.contains(208));
		assert(list.contains(232));
		assert(list.contains(256));
		assert(list.contains(278));
		assert(list.contains(300));
		assert(list.contains(276));
		assert(list.contains(230));
		assertEquals(7, list.size());
	}

	@Test
	public void testStartTargets2() {
		list = zero.getTargets(3);
		assert(list.contains(3));
		assert(list.contains(25));
		assert(list.contains(47));
		assert(list.contains(69));
		assert(list.contains(23));
		assert(list.contains(1));
		assertEquals(6, list.size());
	}
}
