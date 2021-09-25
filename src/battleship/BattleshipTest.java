package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Battleship.
 */

class BattleshipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		assertNotEquals("cruiser", ship.getShipType());
		assertNotEquals("destroyer", ship.getShipType());
		
	}

}