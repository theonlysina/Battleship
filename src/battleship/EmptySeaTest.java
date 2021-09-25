package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for EmptySea Class.
 */

class EmptySeaTest {
	
	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetShipType() {
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
		assertNotEquals("destroyer", ship.getShipType());
		assertNotEquals("submarine", ship.getShipType());
	}

	@Test
	void testShootAt() {

		// shootAt emptySea objects should always return false
		ship = new EmptySea();
		assertFalse(ship.shootAt(6, 9));
		
		ship = new EmptySea();
		assertFalse(ship.shootAt(5, 5));
		
		ship = new EmptySea();
		assertFalse(ship.shootAt(0, 0));
	}

	@Test
	void testIsSunk() {
		ship = new EmptySea();
		// emptySea is never sunk
		assertFalse(ship.isSunk());
	}

	@Test
	void testToString() {
		ship = new EmptySea();
		assertEquals("-", ship.toString());
		assertNotEquals("x", ship.toString());
		assertNotEquals("s", ship.toString());
	}

}
