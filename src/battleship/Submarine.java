package battleship;

/**
 * Represents a submarine and is a subclass of abstract class Ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 * 
 */
public class Submarine extends Ship {

    //static variables

    /**
     * Type of ship
     */
    private static final String SHIP_TYPE = "submarine";

    /**
     * Default length of battleship
     */
    private static final int SHIP_LENGTH = 1;

    //constructor

    /**
     * Creates a submarine with default ship length.
     */
    public Submarine() {

        //calls constructor in superclass Ship
        //sets length of ship
        super(Submarine.SHIP_LENGTH);
    }

    //methods

    //getters

    /**
     * Returns the String ship type.
     * @return ship type
     */
    @Override
    public String getShipType() {
        return Submarine.SHIP_TYPE;
    }
}
