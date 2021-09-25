package battleship;

/**
 * Represents a destroyer and is a subclass of abstract class Ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 * 
 */
public class Destroyer extends Ship {

    //static variables

    /**
     * Type of ship
     */
    private static final String SHIP_TYPE = "destroyer";

    /**
     * Default length of battleship
     */
    private static final int SHIP_LENGTH = 2;

    //constructor

    /**
     * Creates a destroyer with default ship length.
     */
    public Destroyer() {

        //calls constructor in superclass Ship
        //sets length of ship
        super(Destroyer.SHIP_LENGTH);
    }

    //methods

    //getters

    /**
     * Returns the String ship type.
     * @return ship type
     */
    @Override
    public String getShipType() {
        return Destroyer.SHIP_TYPE;
    }
}
