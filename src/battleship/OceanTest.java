package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Ocean Class.
 */

class OceanTest {

	Ocean ocean;
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		// 1. test other positions of the destroyer and submarine
		assertTrue(ocean.isOccupied(0, 5));
		assertTrue(ocean.isOccupied(0, 0));
		
		// the surrounding position should not be occupied
		assertFalse(ocean.isOccupied(2, 5));
		assertFalse(ocean.isOccupied(0, 1));
		assertFalse(ocean.isOccupied(1, 4));
		
		// place another Cruiser and Battleship for testing
		Ship cruiser = new Cruiser();
		row = 5;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
				
		Ship battleship = new Battleship();
		row = 9;
		column = 3;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// 2. test that the ocean is occupied at positions of the cruiser and battleship
		assertTrue(ocean.isOccupied(5, 9)); // vertical cruiser
		assertTrue(ocean.isOccupied(4, 9));
		assertTrue(ocean.isOccupied(3, 9));
		
		assertTrue(ocean.isOccupied(9, 3)); // horizontal battleship
		assertTrue(ocean.isOccupied(9, 2));
		assertTrue(ocean.isOccupied(9, 1));
		assertTrue(ocean.isOccupied(9, 0));
		
		// the surrounding position should not be occupied
		assertFalse(ocean.isOccupied(9, 5));
		assertFalse(ocean.isOccupied(9, 4));
		assertFalse(ocean.isOccupied(4, 8));
		assertFalse(ocean.isOccupied(2, 9));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//1. now that destroyer is sunk, shoot at the sunken-destroyer locations should return false
		assertTrue(destroyer.isSunk());
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(ocean.shootAt(1, 5));
		
		//2. test shooting out of bounds should return false
		assertFalse(ocean.shootAt(-1, 5));
		assertFalse(ocean.shootAt(0, 10));
		assertFalse(ocean.shootAt(167, 5));
		assertFalse(ocean.shootAt(1, -10));
		
		//3. place a submarine and shoot at it
		Submarine submarine = new Submarine();
		row = 9;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(9, 5)); //miss
		assertTrue(ocean.shootAt(9, 6)); // hit submarine
		assertTrue(submarine.isSunk()); // submarine is sunk
		assertFalse(ocean.shootAt(9, 6)); // shooting the same place -> false
		
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		// 1. shots out of bounds should not increase shotsFired count
		assertFalse(ocean.shootAt(0, 10));
		assertFalse(ocean.shootAt(-1, 10));
		assertFalse(ocean.shootAt(11, 6));
		assertFalse(ocean.shootAt(5, -50));
		assertEquals(6, ocean.getShotsFired());
		
		// 2. shoot at the submarine
		assertFalse(ocean.shootAt(0, 1)); //miss
		assertFalse(submarine.isSunk());
		assertTrue(ocean.shootAt(0, 0)); //hit
		assertTrue(submarine.isSunk());
		assertEquals(8, ocean.getShotsFired());
		
		// place another Battleship for testing
				
		Ship battleship = new Battleship();
		row = 9;
		column = 3;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		// 2. shoot at the battleship
		
		assertFalse(ocean.shootAt(9, 4)); // miss
		assertTrue(ocean.shootAt(9, 3)); //hit
		assertFalse(ocean.shootAt(8, 2)); // miss
		assertTrue(ocean.shootAt(9, 1)); // hit
		assertTrue(ocean.shootAt(9, 0)); //hit
		assertEquals(13, ocean.getShotsFired());
		assertFalse(battleship.isSunk());
		
		assertTrue(ocean.shootAt(9, 2)); //hit
		assertTrue(battleship.isSunk());
		assertEquals(14, ocean.getShotsFired());
		
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		// create another submarine for testing
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		// 1. hitting the destroyer until it sinks
		assertFalse(ocean.shootAt(1, 6)); //miss
		assertFalse(ocean.shootAt(2, 5)); //miss
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount()); //hitCount stays the same
		assertNotEquals(3, ocean.getHitCount());
		
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		// shooting at the sunken destroyer should not increase hitCount
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(ocean.shootAt(1, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		// 2. hitting the submarine until it sinks
		assertFalse(ocean.shootAt(1, 0)); //miss
		assertFalse(ocean.shootAt(0, 1)); //miss
		assertFalse(submarine.isSunk());
		assertEquals(2, ocean.getHitCount()); //hitCount stays the same
		assertNotEquals(3, ocean.getHitCount());
		
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(3, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		// 1. keep shooting at the destroyer until it sinks
		assertFalse(ocean.shootAt(2, 5)); // miss
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 5)); // hit
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());

		// create more ships
		Ship submarine = new Submarine();
		row = 0;
		column = 2;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine2 = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine2.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine3 = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine3.placeShipAt(row, column, horizontal, ocean);
		
		// shoot at the submarines
		assertFalse(ocean.shootAt(1, 2)); //miss
		assertFalse(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 2)); // hit
		assertTrue(submarine.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(5, 5)); //hit 2
		assertTrue(submarine2.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		
		assertFalse(ocean.shootAt(8, 9)); // miss 3
		assertFalse(submarine3.isSunk());
		assertEquals(4, ocean.getHitCount());
		assertEquals(3, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(9, 9)); //hit 3
		assertTrue(submarine3.isSunk());
		assertEquals(5, ocean.getHitCount());
		assertEquals(4, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		// place ships for testing
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 2;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine2 = new Submarine();
		row = 5;
		column = 5;
		horizontal = true;
		submarine2.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine3 = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		submarine3.placeShipAt(row, column, horizontal, ocean);
		
		Ship cruiser = new Cruiser();
		row = 9;
		column = 5;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		Ship battleship = new Battleship();
		row = 3;
		column = 7;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("empty", shipArray[9][8].getShipType());
		assertEquals("submarine", shipArray[9][9].getShipType());
		assertEquals("empty", shipArray[5][6].getShipType()); // check around submarine
		assertEquals("submarine", shipArray[5][5].getShipType());
		assertEquals("submarine", shipArray[0][2].getShipType());
		assertEquals("battleship", shipArray[3][7].getShipType());
		assertEquals("battleship", shipArray[3][6].getShipType());
		assertEquals("battleship", shipArray[3][5].getShipType());
		assertEquals("battleship", shipArray[3][4].getShipType());
		assertEquals("cruiser", shipArray[9][5].getShipType());
		assertEquals("empty", shipArray[9][6].getShipType()); // check around cruiser
		assertEquals("empty", shipArray[9][4].getShipType());
		assertEquals("empty", shipArray[6][5].getShipType());
		assertEquals("cruiser", shipArray[8][5].getShipType());
		assertEquals("cruiser", shipArray[7][5].getShipType());
		assertEquals("empty", shipArray[3][3].getShipType()); // check next to battleship stern
	}

}
