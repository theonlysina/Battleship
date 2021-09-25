package battleship;

import java.util.Random;

/**
 * Represents an ocean with a fleet of ships, containing a 10x10 array of Ships.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 */

public class Ocean {

    //static variables

    /**
     * Default number of ships in fleet per type.
     */
    private static int NUM_BATTLESHIPS = 1;
    private static int NUM_CRUISERS = 2;
    private static int NUM_DESTROYERS = 3;
    private static int NUM_SUBMARINES = 4;

    //instance variables

    /**
     * Array of ships/empty sea used to determine ships location.
     */
    private Ship[][] ships = new Ship [10][10];

    /**
     * Total number of shots fired.
     */
    private int shotsFired;

    /**
     * Number of times a shot hit a ship.
     */
    private int hitCount;

    /**
     * Number of ships sunk (10 ships total).
     */
    private int shipsSunk;

    //constructor

    /**
     * Creates an "empty" ocean (and fills the ships array with EmptySea objects).
     * Initializes any game variables (ex. shotsFired etc.).
     */
    public Ocean() {

        //initialize default values for new Ocean instance
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;

        //fill ocean with EmptySea objects
        this.fillEmptyOcean();
    }

    /**
     * Fills Ocean class with EmptySea objects in a 10x10 array.
     */
    private void fillEmptyOcean() {
        
        //increments through 10x10 ocean array
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                //create a new instance of EmptySea to fill ocean array
                Ship sea = new EmptySea();

                //utilize the placeShipAt method to put EmptySea objects into ocean array
                sea.placeShipAt(i, j, false, this);
            }
        }
    }

    //methods

    /**
     * Places all ten ships randomly on the (initially empty) ocean.
     * Place larger ships before smaller ones.
     * Utilizes the Random class.
     */
    void placeAllShipsRandomly() {
        
        //utilize the Random library to generate random numbers/booleans
        Random random = new Random();

        //intialize local variables to be used in method
        int randomRow;
        int randomColumn;
        boolean horizontal;

        //randomly place battleships onto ocean
        for (int i = 0; i < Ocean.NUM_BATTLESHIPS; i++) {

            //create new battleship instance
            //create random integers/boolean for row, column and horizontal
            Ship ship = new Battleship();
            randomRow = random.nextInt(10);
            randomColumn = random.nextInt(10);
            horizontal = random.nextBoolean();

            //while randomly finding locations in array to place ship, check that location is legal
            while (!ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this)) {

                //if not legal then generate new random integers for next location
                randomRow = random.nextInt(10);
                randomColumn = random.nextInt(10);
                horizontal = random.nextBoolean();
            }

            //place ship instance after location is valid for placement
            ship.placeShipAt(randomRow, randomColumn, horizontal, this);
        }

        //randomly place cruisers onto ocean
        for (int i = 0; i < Ocean.NUM_CRUISERS; i++) {

            //create new cruiser instance
            //create random integers/boolean for row, column and horizontal
            Ship ship = new Cruiser();
            randomRow = random.nextInt(10);
            randomColumn = random.nextInt(10);
            horizontal = random.nextBoolean();

            //while randomly finding locations in array to place ship, check that location is legal
            while (!ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this)) {

                //if not legal then generate new random integers for next location
                randomRow = random.nextInt(10);
                randomColumn = random.nextInt(10);
                horizontal = random.nextBoolean();
            }

            //place ship instance after location is valid for placement
            ship.placeShipAt(randomRow, randomColumn, horizontal, this);
        }
        
        //randomly place destroyers onto ocean
        for (int i = 0; i < Ocean.NUM_DESTROYERS; i++) {

            //create new destroyer instance
            //create random integers/boolean for row, column and horizontal
            Ship ship = new Destroyer();
            randomRow = random.nextInt(10);
            randomColumn = random.nextInt(10);
            horizontal = random.nextBoolean();

            //while randomly finding locations in array to place ship, check that location is legal
            while (!ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this)) {

                //if not legal then generate new random integers for next location
                randomRow = random.nextInt(10);
                randomColumn = random.nextInt(10);
                horizontal = random.nextBoolean();
            }

            //place ship instance after location is valid for placement
            ship.placeShipAt(randomRow, randomColumn, horizontal, this);
        }
        
        //randomly place submarines onto ocean
        for (int i = 0; i < Ocean.NUM_SUBMARINES; i++) {

            //create new submarine instance
            //create random integers/boolean for row, column and horizontal
            Ship ship = new Submarine();
            randomRow = random.nextInt(10);
            randomColumn = random.nextInt(10);
            horizontal = random.nextBoolean();

            //while randomly finding locations in array to place ship, check that location is legal
            while (!ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this)) {

                //if not legal then generate new random integers for next location
                randomRow = random.nextInt(10);
                randomColumn = random.nextInt(10);
                horizontal = random.nextBoolean();
            }

            //place ship instance after location is valid for placement
            ship.placeShipAt(randomRow, randomColumn, horizontal, this);
        }  
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     * @param row coordinates in ocean array
     * @param column coordinates in ocean array
     * @return boolean
     */
    boolean isOccupied(int row, int column) {
        
        //checks at given row, column if the ship type is anything but empty
        if (!this.getShipArray()[row][column].getShipType().equals("empty")) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given location contains a "real" ship, still afloat
     * and not an EmptySea object, false if it does not.
     * Updates the number of shots fired and the number of hits.
     * Note: Returns true if location a "real" ship even if shot multiple times.
     * Once a ship has been "sunk", additional shots at its location should return false.
     * @param row coordinates in ocean array
     * @param column coordinates in ocean array
     * @return boolean
     */
    boolean shootAt(int row, int column) {
        
        //initialize local variables
        boolean returnValue = false;
        
        //return false if given row, column is outside the range of the ocean array
        if (row < 0 || column < 0 || row > 9 || column > 9) {
            return returnValue;
        }

        //retrieves ship object from ocean array from given row, column
        Ship ship = this.getShipArray()[row][column];

        //increment shotsFired by 1
        this.shotsFired++;

        //checks ship and if it is not sunk, then it will attempt to shoot at the given row, column
        if (!ship.isSunk()) {
            if (ship.shootAt(row, column)) {
                this.hitCount++;
            }

            //checks if after hitting whether the ship has sunk
            if (ship.isSunk()) {
                this.shipsSunk++;
            }

            //if location shot is occupied by a "real" ship, then return true
            if (this.isOccupied(row, column)) {
                returnValue = true;
            }
        }


        return returnValue;
    }

    /**
     * Returns the number of shots fired in the game.
     * @return integer, shotsFired
     */
    int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * Returns the number of hits recorded in the game.
     * All hits are counted, not just the first time a given square is hit.
     * @return integer, hitCount
     */
    int getHitCount() {
        return this.hitCount;
    }

    /**
     * Returns the number of ships sunk in the game.
     * @return integer, shipsSunk
     */
    int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * Returns true if all ships ahve been sunk, otherwise false.
     * @return boolean true if game is over, else false.
     */
    boolean isGameOver() {

        //conditional if number of ships sunk is greater than/equals to 10 then game is over (returns true)
        if (this.getShipsSunk() >= 10) {
            return true;
        }
        return false;
    }

    /**
     * Gets the 10x10 array of Ships.
     * Method is used in Ship class, to view contents in array and modify it in placeShipAt() method.
     * @return Ships array object
     */
    Ship[][] getShipArray() {
        return this.ships;
    }

    /**
     * Prints the Ocean.
     * Row numbers are displayed along the left edge of the array.
     * Column numbers are displaced along the top edge of the array.
     * Numbers range from 0 to 9, not 1 to 10. Top left corner square is 0,0.
     * 'x': Use 'x' to indicate a location that you have fired upon and hit a (real) ship.
     * '-': Use '-' to indicate a location that you have fired upon and found nothing there.
     * 's': Use 's' to indicate a location containing a sunken ship.
     * '.': Use '.' to indicate a location that you have never fired upon.
     */
    void print() {
        
        //prints first line with numbers 0 to 9 as a point of reference
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        //loop across rows in the ocean array and retrieves the ship at each row, column index
        for (int i = 0; i < 10; i++) {

            //prints row number as point of reference
            System.out.print(i + " ");

            //loop across columns in the ocean array and retrieves the ship at each row, column index
            for (int j = 0; j < 10; j++) {

                //retrieves ship from ocean array
                Ship ship = this.getShipArray()[i][j];

                //conditional dependent on if ship is horizontal or vertical
                if (ship.isHorizontal()) {

                    //if ship is in horizontal orientation, uses the bow column and given column to determine index of hit array
                    int index = ship.getBowColumn() - j;

                    //if index at hit array returns true, prints ship string
                    if (ship.getHit()[index]) {
                        System.out.print(ship + " ");
                    } else {

                        //if nothing was hit, return a period
                        System.out.print(". ");
                    }
                } else {

                    //if ship is in vertical orientation, uses the bow row and given column to determine index of hit array
                    int index = ship.getBowRow() - i;

                    //if index at hit array returns true, prints ship string
                    if (ship.getHit()[index]) {
                        System.out.print(ship + " ");
                    } else {

                        //if nothing was hit, return a period
                        System.out.print(". ");
                    }
                }
            }
            System.out.println(""); //blank line
        }
    }
}
