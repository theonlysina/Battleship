package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Cruiser.
 */

class CruiserTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetShipType() {
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		assertNotEquals("destroyer", ship.getShipType());
		assertNotEquals("empty", ship.getShipType());
		
	}

}
