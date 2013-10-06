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
		
	}

	// green
	@Test
	public void testAdjacenciesRoomEntrances() {
		
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
