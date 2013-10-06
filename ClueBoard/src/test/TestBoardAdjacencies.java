package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clue.BadConfigFormatException;
import clue.Board;

public class TestBoardAdjacencies {
	private static Board board;
	LinkedList<Integer> list;

	@Before
	public void setUp() {
		board = new Board();
		try {
			board.loadConfigFiles("ClueBoard.csv", "legend.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		board.calcAdjacencies();
		list = null;
	}

	// red
	@Test
	public void testAdjacenciesInsideRooms() {
		list = board.getAdjList(board.calcIndex(1, 1));
		Assert.assertEquals(0, list.size());

		list = board.getAdjList(board.calcIndex(4, 18));
		Assert.assertEquals(0, list.size());

		list = board.getAdjList(board.calcIndex(11, 6));
		Assert.assertEquals(0, list.size());

		list = board.getAdjList(board.calcIndex(22, 11));
		Assert.assertEquals(0, list.size());
	}

	// purple
	@Test
	public void testAdjacenciesRoomExits() {
		list = board.getAdjList(board.calcIndex(5, 12));
		Assert.assertTrue(list.contains(board.calcIndex(6, 12)));
		Assert.assertEquals(1, list.size());

		list = board.getAdjList(board.calcIndex(10, 18));
		Assert.assertTrue(list.contains(board.calcIndex(10, 17)));
		Assert.assertEquals(1, list.size());

		list = board.getAdjList(board.calcIndex(11, 7));
		Assert.assertTrue(list.contains(board.calcIndex(11, 8)));
		Assert.assertEquals(1, list.size());

		list = board.getAdjList(board.calcIndex(16, 16));
		Assert.assertTrue(list.contains(board.calcIndex(15, 16)));
		Assert.assertEquals(1, list.size());
	}

	// green
	@Test
	public void testAdjacenciesRoomEntrances() {
		list = board.getAdjList(board.calcIndex(6, 13));
		assertTrue(list.contains(board.calcIndex(5, 13)));
		assertTrue(list.contains(board.calcIndex(6, 14)));
		assertTrue(list.contains(board.calcIndex(7, 13)));
		assertTrue(list.contains(board.calcIndex(6, 12)));
		assertEquals(4, list.size());

		list = board.getAdjList(board.calcIndex(10, 17));
		assertTrue(list.contains(board.calcIndex(9, 17)));
		assertTrue(list.contains(board.calcIndex(10, 18)));
		assertTrue(list.contains(board.calcIndex(11, 17)));
		assertTrue(list.contains(board.calcIndex(10, 16)));
		assertEquals(4, list.size());

		list = board.getAdjList(board.calcIndex(15, 14));
		assertTrue(list.contains(board.calcIndex(14, 14)));
		assertTrue(list.contains(board.calcIndex(15, 15)));
		assertTrue(list.contains(board.calcIndex(15, 13)));
		assertEquals(3, list.size());

		list = board.getAdjList(board.calcIndex(14, 8));
		assertTrue(list.contains(board.calcIndex(13, 8)));
		assertTrue(list.contains(board.calcIndex(14, 9)));
		assertTrue(list.contains(board.calcIndex(15, 8)));
		assertTrue(list.contains(board.calcIndex(14, 7)));
		assertEquals(4, list.size());

		list = board.getAdjList(board.calcIndex(4, 15));
		assertTrue(list.contains(board.calcIndex(3, 15)));
		assertTrue(list.contains(board.calcIndex(5, 15)));
		assertEquals(2, list.size());
	}

	// light purple
	@Test
	public void testAdjacenciesWalkways() {
		list = board.getAdjList(board.calcIndex(0, 15));
		assertTrue(list.contains(board.calcIndex(1, 15)));
		assertEquals(1, list.size());

		list = board.getAdjList(board.calcIndex(5, 18));
		assertTrue(list.contains(board.calcIndex(5, 19)));
		assertTrue(list.contains(board.calcIndex(6, 18)));
		assertTrue(list.contains(board.calcIndex(5, 17)));
		assertEquals(3, list.size());

		list = board.getAdjList(board.calcIndex(7, 22));
		assertTrue(list.contains(board.calcIndex(6, 22)));
		assertTrue(list.contains(board.calcIndex(7, 21)));
		assertEquals(2, list.size());

		list = board.getAdjList(board.calcIndex(10, 16));
		assertTrue(list.contains(board.calcIndex(9, 16)));
		assertTrue(list.contains(board.calcIndex(10, 17)));
		assertTrue(list.contains(board.calcIndex(11, 16)));
		assertTrue(list.contains(board.calcIndex(10, 15)));
		assertEquals(4, list.size());

		list = board.getAdjList(board.calcIndex(16, 0));
		assertTrue(list.contains(board.calcIndex(15, 0)));
		assertTrue(list.contains(board.calcIndex(16, 1)));
		assertEquals(2, list.size());

	}

	// light blue
	@Test
	public void testTargetsOneStep() {
		
	}

	// light blue
	@Test
	public void testTargetsThreeSteps() {
		
	}

	// light blue
	@Test
	public void testTargetsSixSteps() {
		
	}

	// light blue
	@Test
	public void testTargetsIntoRoom() {
		
	}

	// light blue
	@Test
	public void testRoomExits() {
		
	}
}
