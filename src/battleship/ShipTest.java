package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Ship Class.
 */

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		// 1. cruiser length should not be 4
		ship = new Cruiser();
		assertNotEquals(4, ship.getLength());
		
		// 2. destroyer length should be 2
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// 3. submarine length should not be 2
		ship = new Submarine();
		assertNotEquals(2, ship.getLength());
		
		// 4. EmptySea length should be 1
		ship = new EmptySea();
		assertEquals(1, ship.getLength());
		
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		// 1. Vertical Cruiser
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// 2. Horizontal Destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 1;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(3, destroyer.getBowRow());
		assertEquals(row, destroyer.getBowRow());
		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		// 1. Vertical Cruiser
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		// 2. Horizontal Destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 1;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(3, destroyer.getBowColumn());
		assertEquals(column, destroyer.getBowColumn());
		
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		// 1. Hitting vertical cruiser
		ship = new Cruiser();
		boolean[] hitsCruiser = new boolean[4];
		assertArrayEquals(hitsCruiser, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		// 2. Hitting horizontal destroyer
		ship = new Destroyer();
		boolean[] hitsDestroyer = new boolean[4];
		assertArrayEquals(hitsDestroyer, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
	}

	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// 1. Vertical Cruiser
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		
		// 2. Horizontal Destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 1;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.isHorizontal());
		
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// 1. Cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		// 2. Submarine
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		// 3. EmptySea
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
		
	}

	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		// 1. Horizontal cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 9;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		
		// 2. Vertical Destroyer
		Ship destroyer = new Destroyer();
		row = 8;
		column = 0;
		horizontal = false;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		// 1. Horizontal cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 9;
		horizontal = true;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
		
		// 2. Vertical Destroyer
		Ship destroyer = new Destroyer();
		row = 8;
		column = 0;
		horizontal = false;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		// 1. Horizontal cruiser
		Ship cruiser = new Cruiser();
		row = 2;
		column = 9;
		horizontal = true;
		cruiser.setHorizontal(horizontal);
		assertTrue(cruiser.isHorizontal());
		
		// 2. Vertical Submarine
		Ship submarine = new Submarine();
		row = 8;
		column = 0;
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());

	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// placing horizontal battleship at (0,4) for further testing
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// 1. try placing a vertical cruiser touching diagonal-left to battleship (illegal)
		Ship cruiser = new Cruiser();
		row = 3;
		column = 0;
		horizontal = false;
		assertFalse(cruiser.okToPlaceShipAt(row, column, horizontal, ocean));
		
		// 2. placing a legal vertical cruiser
		row = 4;
		assertTrue(cruiser.okToPlaceShipAt(row, column, horizontal, ocean));
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		// 3. try placing a vertical destroyer out of bounds (illegal)
		Ship destroyer = new Destroyer();
		row = 0;
		column = 8;
		assertFalse(destroyer.okToPlaceShipAt(row, column, horizontal, ocean));
		
		// 4. placing a legal horizontal destroyer
		destroyer = new Destroyer();
		row = 9;
		column = 9;
		assertTrue(destroyer.okToPlaceShipAt(row, column, horizontal, ocean));
		destroyer.placeShipAt(row, column, horizontal, ocean);
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		// 1. place a vertical destroyer (legal)
		Destroyer destroyer1 = new Destroyer();
		row = 4;
		column = 2;
		horizontal = false;
		boolean ok3 = destroyer1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
		destroyer1.placeShipAt(row, column, horizontal, ocean);
		
		// 1.5 test placing an illegal destroyer right above the first destroyer
		Destroyer destroyer2 = new Destroyer();
		row = 2;
		column = 4;
		horizontal = false;
		boolean ok4 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "OK to place ship here.");
		
		// 2. test placing an illegal horizontal cruiser 
		// such that the cruiser's stern will touch diagonal-below the first battleship
		Cruiser cruiser = new Cruiser();
		row = 1;
		column = 7;
		horizontal = true;
		boolean ok5 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok5, "OK to place ship here");
		
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		// 1. Test placing a vertical cruiser; 
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		
		// stern is at (7,5) --> should not be empty
		assertNotEquals("empty", ocean.getShipArray()[7][5].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[8][5]);
		// length of cruiser is only 3 so (6,5) should be empty
		assertEquals("empty", ocean.getShipArray()[6][5].getShipType());

		// 2. Test placing a horizontal submarine
		Ship submarine = new Submarine();
		row = 3;
		column = 7;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertTrue(submarine.isHorizontal());
		
		// 8 positions around the submarine should be empty
		assertEquals("empty", ocean.getShipArray()[3][6].getShipType());
		assertEquals("empty", ocean.getShipArray()[3][8].getShipType());
		assertEquals("empty", ocean.getShipArray()[2][6].getShipType());
		assertEquals("empty", ocean.getShipArray()[4][6].getShipType());
		assertEquals("empty", ocean.getShipArray()[2][8].getShipType());
		assertEquals("empty", ocean.getShipArray()[4][8].getShipType());
		assertEquals("empty", ocean.getShipArray()[2][7].getShipType());
		assertEquals("empty", ocean.getShipArray()[4][7].getShipType());
		
		assertEquals(submarine, ocean.getShipArray()[3][7]);
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		// 0. shoot battleship mid-body successfully
		assertTrue(battleship.shootAt(0, 7));
		boolean[] hitArray1 = {false, false, true, false};
		assertArrayEquals(hitArray1, battleship.getHit());
		
		// place a cruiser for further testing 
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		

		// 1. Shooting cruiser's stern successfully
		assertTrue(cruiser.shootAt(7, 5));
		boolean[] hitArray2 = {false, false, true, false};
		assertArrayEquals(hitArray2, battleship.getHit());
		
		// 2. Shooting cruiser unsuccessfully
		assertFalse(cruiser.shootAt(6, 5));
		boolean[] hitArray3 = {false, false, true, false};
		assertArrayEquals(hitArray3, battleship.getHit());
		
		// Shoot to sink cruiser
		assertTrue(cruiser.shootAt(9, 5));
		assertTrue(cruiser.shootAt(8, 5));
		boolean[] hitArray4 = {true, true, true, false};
		assertArrayEquals(hitArray4, cruiser.getHit());
		
		// 3. Shooting sunken cruiser should return false; cruiser's hit array stays the same
		assertFalse(cruiser.shootAt(6, 5));
		assertArrayEquals(hitArray4, cruiser.getHit());
		
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		// 1. shoot the submarine so that it would be sunk
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());
		
		// place a vertical battleship into the ocean
		Ship battleship = new Battleship();
		row = 3;
		column = 7;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.isSunk()); //battleship should not be sunk
		
		// 2. shoot battleship until it is sunk
		assertFalse(battleship.shootAt(4, 7)); // should be a miss
		assertTrue(battleship.shootAt(3, 7));
		assertTrue(battleship.shootAt(2, 7));
		assertTrue(battleship.shootAt(0, 7));
		assertFalse(battleship.isSunk()); //it should still not be sunk yet
		
		assertTrue(battleship.shootAt(1, 7));
		assertTrue(battleship.isSunk()); // now it should be sunk
		
		// place a horizontal cruiser into the ocean
		Ship cruiser = new Cruiser();
		row = 6;
		column = 5;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isSunk()); //cruiser should not be sunk
		
		// 2. shoot cruiser until it is sunk
		assertFalse(cruiser.shootAt(6, 2)); // should be a miss
		assertTrue(cruiser.shootAt(6, 3));
		assertTrue(cruiser.shootAt(6, 4));
		
		assertFalse(cruiser.isSunk()); //it should still not be sunk yet
		
		assertTrue(cruiser.shootAt(6, 5));
		assertTrue(cruiser.isSunk()); // now it should be sunk
		
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		// 1. keep shooting at the vertical battleship
		battleship.shootAt(6, 1); // should be a miss
		assertNotEquals("s", battleship.toString());
		
		battleship.shootAt(7, 1);
		assertNotEquals("s", battleship.toString());
		assertEquals("x", battleship.toString());
		
		battleship.shootAt(8, 1);
		assertNotEquals("x", battleship.toString());
		assertEquals("s", battleship.toString()); // now it should be sunk
		
		// 2. place a horizontal submarine into the ocean and shoot at it
		Ship submarine = new Submarine();
		
		row = 3;
		column = 6;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(3, 6);
		assertEquals("s", battleship.toString()); // submarine should be sunk right away
		
		// 2. place a horizontal destroyer into the ocean and shoot at it
		Ship destroyer = new Destroyer();
		row = 9;
		column = 4;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		destroyer.shootAt(9, 4); // first shoot should not sink the ship
		assertEquals("x", destroyer.toString());
		
		destroyer.shootAt(9, 3);
		assertEquals("s", destroyer.toString()); // destroyer should now be sunk
		
	}
}
