package battleship;

/**
 * Represents an empty sea space and is a subclass of abstract class Ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 * 
 */
public class EmptySea extends Ship {

    //static variables

    /**
     * Type of ship.
     */
    private static final String SHIP_TYPE = "empty";

    /**
     * Default length of empty sea.
     */
    private static final int SHIP_LENGTH = 1;

    //constructor
    
    /**
     * Creates an empty sea with default length.
     */
    public EmptySea() {

        //calls constructor in superclass Ship
        //sets length of ship
        super(EmptySea.SHIP_LENGTH);
    }

    //methods

    /**
     * This method overrides shootAt(int row, int column) that is inherited from
     * Ship, and always returns false to indicate that nothing was hit.
     * @param row coordinate of ship
     * @param column coordinate of ship
     * @return boolean false
     */
    @Override
    boolean shootAt(int row, int column) {

        //used to update the hit count
        super.shootAt(row, column);
        return false; 
    }

    /**
     * This method overrides isSunk() that is inherited from Ship, and always returns
     * false to indicate that nothing was sunk.
     * @return boolean false
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * Returns the single-character "-" String to use in the Ocean's print method.
     * Note: this is the character to be displaced if a shot has been fired, but nothing has been hit.
     * @return character String
     */
    @Override
    public String toString() {
        return "-";
    }

    //getters

    /**
     * Returns the String ship type.
     * @return ship type
     */
    @Override
    public String getShipType() {
        return EmptySea.SHIP_TYPE;
    }
}
