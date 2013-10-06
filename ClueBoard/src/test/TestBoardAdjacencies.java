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
		//list = board.getAdjList(board.calcIndex(4, 4));
		//Assert.assertTrue(list.contains(board.calcIndex(4, 3)));
		//Assert.assertTrue(list.contains(board.calcIndex(4, 5)));
		//Assert.assertTrue(list.contains(board.calcIndex(5, 4)));
		//assertTrue(list.contains(board.calcIndex(5, 4)));
		//assertEquals(4, list.size());
	}

	// light purple
	@Test
	public void testAdjacenciesWalkways() {
		
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
