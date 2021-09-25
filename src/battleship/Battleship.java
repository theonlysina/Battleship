package battleship;

/**
 * Represents a battleship and is a subclass of abstract class Ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 */

public class Battleship extends Ship {
    
    //static variables

    /**
     * Type of ship.
     */
    private static final String SHIP_TYPE = "battleship";

    /**
     * Default length of battleship.
     */
    private static final int SHIP_LENGTH = 4;

    //constructor

    /**
     * Creates a battleship with default ship length.
     */
    public Battleship() {

        //calls constructor in superclass Ship
        //sets length of ship
        super(Battleship.SHIP_LENGTH);
    }

    //methods

    //getters

    /**
     * Returns the String ship type.
     * @return ship type
     */
    @Override
    public String getShipType() {
        return Battleship.SHIP_TYPE;
    }
}
