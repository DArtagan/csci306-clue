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
		
		LinkedList<Integer> list;
	}

	@Test
	public static void testAdjacenciesInsideRooms() {
		
	}

	@Test
	public static void testAdjacenciesRoomExits() {
		
	}

	@Test
	public static void testAdjacenciesThatIncludeDoorways() {
		
	}

	@Test
	public static void testAdjacenciesWalkways() {
		
	}

	@Test
	public static void testTargetsOneStep() {
		
	}

	@Test
	public static void testTargetsThreeSteps() {
		
	}

	@Test
	public static void testTargetsSixSteps() {
		
	}

	@Test
	public static void testTargetsIntoRoom() {
		
	}

	@Test
	public static void testRoomExits() {
		
	}
}
