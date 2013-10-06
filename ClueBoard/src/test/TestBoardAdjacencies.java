package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import clue.BadConfigFormatException;
import clue.Board;

public class TestBoardAdjacencies {
	private static Board board;
	LinkedList<Integer> list;

	@Before
	public static void setUp() {
		board = new Board();
		try {
			board.loadConfigFiles("ClueBoard.csv", "legend.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			e.printStackTrace();
		}
		board.calcAdjacencies();
	}

	// red
	@Test
	public void testAdjacenciesInsideRooms() {
		list = board.getAdjList(board.calcIndex(0, 0));
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
