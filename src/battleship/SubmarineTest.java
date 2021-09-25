package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents JUnit Test class for Submarine Class.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 * 
 */

class SubmarineTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetShipType() {
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		assertNotEquals("cruiser", ship.getShipType());
		assertNotEquals("empty", ship.getShipType());
		
	}

}