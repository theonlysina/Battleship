package battleship;

/**
 * Abstract class Ship represents a generic ship.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 */

public abstract class Ship {

    //instance variables

    /**
     * Row coordinate the contains the bow (front part of the ship).
     */
    private int bowRow;

    /**
     * Column coordinate the contains the bow (front part of the ship).
     */
    private int bowColumn;

    /**
     * Length of the ship.
     */
    private int length;

    /**
     * Boolean that represents whether a ship is placed horizontally or vertically.
     */
    private boolean horizontal;

    /**
     * Array of 4 booleans that indicate whether the part of the ship has been hit or not.
     */
    private boolean[] hit;

    //constructor

    /**
     * Called by subclasses of Ship to create instances of different types of ships.
     * Initializes the hit array and sets length of ship.
     * @param length of ship
     */
    public Ship(int length) {

        //intializes the length of the ship
        this.length = length;

        //intializes hit array
        this.hit = new boolean[4];
    }

    //methods

    //getters

    /**
     * Gets the ship length.
     * @return length of ship
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Gets row coordinate corresponding to the position of the bow.
     * @return integer, bowRow
     */
    public int getBowRow() {
        return this.bowRow;
    }

    /**
     * Gets column coordinate corresponding to the position of the bow.
     * @return integer, bowColumn
     */
    public int getBowColumn() {
        return this.bowColumn;
    }
    
    /**
     * Gets hit array.
     * @return boolean array, getHit
     */
    public boolean[] getHit() {
        return this.hit;
    }

    /**
     * Gets ships orientation, whether it is horizontal or vertical.
     * @return boolean, isHorizontal
     */
    public boolean isHorizontal() {
        return this.horizontal;
    }

    //setters

    /**
     * Sets the value of bowRow.
     * @param row coordinate of ship
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * Sets the value of bowColumn.
     * @param column coordinate of ship
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * Sets the boolean value of the variable horizontal.
     * @param horizontal orientation of ship
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    //abstract methods

    /**
     * Returns the type of ship as a String. 
     * Every specific type of Ship (ex. Battleship, Cruiser etc.) has to override
     * and implement this method and return the corresponding ship type.
     * @return String, ship type
     */
    public abstract String getShipType();

    //other methods

    /**
     * Returns true if it is okay to put a ship of this length with its bow in this location, false otherwise.
     * Based on the given row, column and orientation.
     * Ship must not overlap with another ship or touch another ship (vertically, horizontally, diagonally).
     * Ship must not "stick out" beyond the array.
     * Does not change either the ship or Ocean, it just provides feedback if it is a legal move.
     * @param row coordinate of ship
     * @param column coordinate of ship
     * @param horizontal orientation of ship
     * @param ocean object with 10x10 array
     * @return boolean
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        
        //call ships array from Ocean class
        Ship[][] ships = ocean.getShipArray();

        //initialize local variables for use in method
        int shipEnd;
        int shipLength = this.getLength();
        int columnStartIndex;
        int columnEndIndex;
        int rowStartIndex;
        int rowEndIndex;

        //conditional between if a ship is horizontal or vertical orientation
        if (horizontal) {

            //initialize column coordinate that calls out the end position of the ship
            shipEnd = column - (shipLength - 1);

            //if end of ship has a coordinate that is outside of ocean array, return false
            if (shipEnd < 0) {
                return false;
            }
            
            //assign a start and end index to be used when iterating across rows
            //conditionals are dependent on if row coordinate is at either row 0, 9 or between 1 and 8
            if (row == 0) {
                rowStartIndex = row;
                rowEndIndex = row + 1;
            } else if (row == 9) {
                rowStartIndex = row - 1;
                rowEndIndex = row;
            } else {
                rowStartIndex = row - 1;
                rowEndIndex = row + 1;
            }

            //assign a start and end index to be used when iterating across columns
            //conditionals are dependent on if column coordinate is at either row 0, 9 or between 1 and 8
            if (shipEnd == 0) {
                columnStartIndex = shipEnd;
                columnEndIndex = column + 1;
            } else if (column == 9) {
                columnStartIndex = shipEnd - 1;
                columnEndIndex = column;
            } else {
                columnStartIndex = shipEnd - 1;
                columnEndIndex = column + 1;
            }
        } else {

            //initialize row coordinate that calls out the end position of the ship
            shipEnd = row - (shipLength - 1);

            //if end of ship has a coordinate that is outside of ocean array, return false
            if (shipEnd < 0) {
                return false;
            }

            //assign a start and end index to be used when iterating across rows
            //conditionals are dependent on if row coordinate is at either row 0, 9 or between 1 and 8
            if (shipEnd == 0) {
                rowStartIndex = shipEnd;
                rowEndIndex = row + 1;
            } else if (row == 9) {
                rowStartIndex = shipEnd - 1;
                rowEndIndex = row;
            } else {
                rowStartIndex = shipEnd - 1;
                rowEndIndex = row + 1;
            }

            //assign a start and end index to be used when iterating across columns
            //conditionals are dependent on if column coordinate is at either row 0, 9 or between 1 and 8
            if (column == 0) {
                columnStartIndex = column;
                columnEndIndex = column + 1;
            } else if (column == 9) {
                columnStartIndex = column - 1;
                columnEndIndex = column;
            } else {
                columnStartIndex = column - 1;
                columnEndIndex = column + 1;
            }
        }

        //using the start/end index for rows and columns, iterate and determine if all ship return types are "empty"
        //if any coordinate of a ship is not empty, then it is not a legal move and is not okay to place a ship
        for (int i = rowStartIndex; i <= rowEndIndex; i++) {
            for (int j = columnStartIndex; j <= columnEndIndex; j++) {
                if (!ships[i][j].getShipType().equals("empty")) {
                    return false;
                }
            }
        }

        //if code is executed up to this point, then that means coordinates to place ship is a legal move and it is okay to place ship
        return true;
    }

    /**
     * "Puts" the ship in the ocean. This involves giving values to the bowRow, bowColumn and
     * horizontal instance variables in the ship, and it also involves putting a reference to the ship
     * in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
     * Note: This will be as many as four identical references; reference is to whole ship.
     * Horizontal ships face east (bow at right end). Vertical ships face south (bow at bottom end).
     * @param row coordinate of ship
     * @param column coordinate of ship
     * @param horizontal orientation of ship
     * @param ocean object with 10x10 array
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        //initialize instance variables for this ship instance
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);
        
        //initialize local variables for use in this method
        int shipLength = this.getLength();
        int shipEnd;

        //execute conditional dependent on orientation (horizontal or vertical) of ship
        //code block inside conditional places ship objects into ocean array using the given row and column coordinates
        if (this.isHorizontal()) {

            //initialize column coordinate that calls out the end position of the ship
            shipEnd = column - (shipLength - 1);

            //runs a for loop across the length of the ship
            //places this ship subclass instance into the ocean 10x10 array
            for (int i = shipEnd; i <= column; i++) {
                ocean.getShipArray()[row][i] = this;
            }
        } else if (!this.isHorizontal()) {

            //initialize row coordinate that calls out the end position of the ship
            shipEnd = row - (shipLength - 1);
            
            //runs a for loop across the length of the ship
            //places this ship subclass instance into the ocean 10x10 array
            for (int i = shipEnd; i <= row; i++) {
                ocean.getShipArray()[i][column] = this;
            }
        }
    }
    
    /**
     * If a part of the ship occupies the given row and column, and the ship hasn't been sunk,
     * mark that part of the ship as "hit" (in the hit array, index 0 indicates the bow) and return
     * true; otherwise return false.
     * @param row coordinate of ship
     * @param column coordinate of ship
     * @return boolean, if ship was hit true, else false
     */
    boolean shootAt(int row, int column) {
        
        //initialize local variable used in this method
        int shipLength = this.getLength();

        //code block within conditional will run if ship is not sunk
        if (!this.isSunk()) {

            //execute conditional dependent on orientation (horizontal or vertical) of ship
            if (this.isHorizontal()) {

                //initialize column coordinate that calls out the end position of the ship
                int shipEnd = this.getBowColumn() - (shipLength - 1);

                //conditional executes if given row coordinate matches ship instance row coordinate
                if (row == this.getBowRow()) {

                    //iterates across the length of the ship
                    for (int i = this.getBowColumn(); i >= shipEnd; i--) {

                        //if given column matches a part of the ships column coordinates, it updates the hit array
                        if (i == column) {
                            this.hit[this.getBowColumn() - column] = true;
                            return true;
                        }
                    }
                }
            } else if (!this.isHorizontal()) {

                //initialize row coordinate that calls out the end position of the ship
                int shipEnd = this.getBowRow() - (shipLength - 1);

                //conditional executes if given column coordinate matches ship instance column coordinate
                if (column == this.getBowColumn()) {

                    //iterates across the length of the ship
                    for (int i = this.getBowRow(); i >= shipEnd; i--) {

                        //if given row matches a part of the ships row coordinates, it updates the hit array
                        if (i == row) {
                            this.hit[this.getBowRow() - row] = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return true if every part of the ship has been hit, else false.
     * @return boolean of whether all parts of ship has been hit
     */
    boolean isSunk() {
        
        //initialize local variable
        int sunk = 0;

        //counts the number of boolean true found in hit array
        for (boolean s : this.hit) {
            if (s) {
                sunk += 1;
            } 
        }

        //if boolean count matches length of ship, then ship is sunk
        if (sunk == this.length) {
            return true;
        }
        return false;
    }

    /**
     * Returns a single-character String to use in the Ocean's print method.
     * This method should return "s" if the ship has been sunk, and "x" if it has not been sunk.
     * This method can be used to print out locations that have been shot at; it should not be used
     * to print locations that have not been shot at.
     * Subclasses will not need to override method due to method is universally the same for all types of Ships.
     */
    @Override
    public String toString() {
        
        //return a specific string depending if ship is sunk or not
        if (this.isSunk()) {
            return "s";
        }
        return "x";
    }
}
