package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import clue.BadConfigFormatException;
import clue.Board;
import clue.RoomCell;

public class TestBoardFunctions {
	Board board;
	RoomCell room;
	int rows;
	int columns;

	@Before
	public void setUp() throws FileNotFoundException, BadConfigFormatException {
		board = new Board();
		board.loadConfigFiles("ClueBoard.csv", "legend.txt");
		rows = 23;
		columns = 23;
	}

	@Test
	public void testRoomsMapNumberOfRooms() {
		int numRooms = 11;
		assertEquals(numRooms, board.getRooms().size());
	}

	@Test
	public void testMappingWorks() {
		String roomName = "Laboratory";
		char roomInitial = 'Y';
		assertEquals(roomName, board.getRooms().get(roomInitial));
		
		roomName = "Closet";
		roomInitial = 'X';
		assertEquals(roomName, board.getRooms().get(roomInitial));
		
		roomName = "Library";
		roomInitial = 'I';
		assertEquals(roomName, board.getRooms().get(roomInitial));
	}

	@Test
	public void testCorrectNumberOfRowsAndColumns() {
		assertEquals(rows, board.getNumRows());
		assertEquals(columns, board.getNumCols());
	}

	@Test
	public void testDoorHasCorrectDirection() {
		room = board.getRoomCellAt(6, 1);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());

		room = board.getRoomCellAt(0, 11);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());

		room = board.getRoomCellAt(15, 13);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());

		room = board.getRoomCellAt(15, 8);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());

		room = board.getRoomCellAt(10, 10);
		assertTrue(room.isDoorway());
		assertEquals(RoomCell.DoorDirection.NONE, room.getDoorDirection());
	}

	@Test
	public void testRoomAssignment() {
		char roomInitial = 'F';
		assertEquals(roomInitial, board.getRoomCellAt(6, 5).getRoomInitial());
		
		roomInitial = 'S';
		assertEquals(roomInitial, board.getRoomCellAt(12, 3).getRoomInitial());
		
		roomInitial = 'H';
		assertEquals(roomInitial, board.getRoomCellAt(9, 20).getRoomInitial());
	}

	@Test
	public void testCalcIndex() {
		assertEquals(0, board.calcIndex(0, 0));
		assertEquals(528, board.calcIndex(rows-1, columns-1));
		assertEquals(342, board.calcIndex(14, 20));
	}

	@Test (expected = BadConfigFormatException.class)
	public void testBadConfigFormatException() throws BadConfigFormatException, FileNotFoundException {
		// board has bad config files!
		board.loadConfigFiles("CR_ClueLayoutBadColumns.csv", "legend.txt");
	}
}
