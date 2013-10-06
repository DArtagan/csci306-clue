package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clue.BadConfigFormatException;
import clue.Board;
import clue.BoardCell;

public class TestBoardAdjacencies {
	private static Board board;
	LinkedList<Integer> list;
	Set<BoardCell> targets;

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
		targets = null;
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
		board.calcTargets(4, 10, 1);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 10))));
		assertEquals(2, targets.size());
		
		board.calcTargets(14, 13, 1);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 13))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 14))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 12))));
		assertEquals(3, targets.size());
	}

	// light blue
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(4, 10, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 10))));
		assertEquals(3, targets.size());
		
		board.calcTargets(14, 13, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 14))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 14))));
		assertEquals(4, targets.size());

		board.calcTargets(8, 9, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 8))));
		assertEquals(6, targets.size());
		
		board.calcTargets(15, 4, 2);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 2))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 4))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 5))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 3))));
		assertEquals(4, targets.size());
	}

	// light blue
	@Test
	public void testTargetsThreeSteps() {
		board.calcTargets(4, 10, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(1, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 10))));
		assertEquals(4, targets.size());
		
		board.calcTargets(14, 13, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 14))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 15))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 16))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 15))));
		assertEquals(7, targets.size());

		board.calcTargets(8, 9, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 6))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 8))));
		assertEquals(10, targets.size());
		
		board.calcTargets(15, 4, 3);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 1))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 3))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 5))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 2))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 4))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 4))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 6))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 5))));
		assertEquals(8, targets.size());
	}

	// light blue
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(4, 10, 4);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(0, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 11))));
		assertEquals(7, targets.size());
		
		board.calcTargets(14, 13, 4);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 15))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 14))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 16))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 15))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 17))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 13)))); // Door
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 14))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 16))));
		assertEquals(12, targets.size());

		board.calcTargets(8, 9, 4);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 11))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 6))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 10))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 12))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 5))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(10, 9))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(11, 8))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(12, 9))));
		assertEquals(14, targets.size());
		
		board.calcTargets(15, 4, 4);
		targets = board.getTargets();
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 4))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(13, 6))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 5))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(14, 7))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 0))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 2))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 1))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 3))));
		assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 2))));
		assertEquals(9, targets.size());
	}

	// black
	@Test
	public void testTargetsIntoRoom() {

		}

	// light green
	@Test
	public void testTargetsRoomExits() {
		board.calcTargets(6, 1, 1);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 1))));
		
		board.calcTargets(6, 1, 2);
		targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 1))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 0))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 2))));
		
		board.calcTargets(0, 11, 1);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(0, 10))));
		
		board.calcTargets(0, 11, 3);
		targets = board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 10))));
	}
}
