package battleship;

/**
 * Represents a cruiser and is a subclass of abstract class Ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 * 
 */
public class Cruiser extends Ship {
    
    //static variables

    /**
     * Type of ship
     */
    private static final String SHIP_TYPE = "cruiser";

    /**
     * Default length of battleship
     */
    private static final int SHIP_LENGTH = 3;

    //constructor

    /**
     * Creates a cruiser with default ship length.
     */
    public Cruiser() {

        //calls constructor in superclass Ship
        //sets length of ship
        super(Cruiser.SHIP_LENGTH);
    }

    //methods

    //getters

    /**
     * Returns the String ship type.
     * @return ship type
     */
    @Override
    public String getShipType() {
        return Cruiser.SHIP_TYPE;
    }
}
