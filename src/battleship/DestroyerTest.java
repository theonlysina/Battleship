package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Destroyer Class.
 */

class DestroyerTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetShipType() {
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		assertNotEquals("cruiser", ship.getShipType());
		assertNotEquals("battleship", ship.getShipType());
	}

}
