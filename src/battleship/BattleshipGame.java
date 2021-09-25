package battleship;

import java.util.Scanner;

import com.sun.tools.javac.Main;

/**
 * Represents the game play for the battleship game.
 * Name: SINA ALIPOUR-NAZARI
 * PennID: 20359038
 * PennKey: snazari
 * Statement: Completed in collaboration with Alexandros Khor and Mai N. Nguyen
 */

public class BattleshipGame {
	/**
	 * Game launch.
	 * Creates an instance of Ocean.
	 * @param args arguments to main method
	 */
	private void run() {
		
		// Create an ocean and place ships 
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		// create scanner for user input
		Scanner scanner = new Scanner(System.in);
		while (!ocean.isGameOver()) {
			
			// print the ocean
			ocean.print();
			
			// get user input (row & column)
			System.out.println("Please enter the row and column (as row, col): ");
			String rowColPair = scanner.nextLine();
			String[] rowColPairArray = rowColPair.split(",");
			
			// getting rid of leading and trailing spaces in inputs
			String rowStr = rowColPairArray[0].trim();
			String colStr = rowColPairArray[1].trim();
			
			try {
				
				// parse given row and column to integers
				int row = Integer.parseInt(rowStr);
				int column = Integer.parseInt(colStr);
				
				//fire a shot
				boolean shootSuccess = ocean.shootAt(row, column);
				
				/*
				 * If a non-sunk ship is hit, we don't tell the user what kind of ship that has been hit
				 * Else if a ship is hit and sinks, print out a message. 
				 */
				// if the shoot is a success, print Hit
				if (shootSuccess) {
					System.out.println("Hit!");
					
					// get the location/ship
					Ship[][] shipArray = ocean.getShipArray();
					Ship ship = shipArray[row][column];
					
					// if the ship is sunk after the shot is hit, inform the user
					if (ship.isSunk()) {
						System.out.println("You sank a ship - (" + ship.getShipType() + ")");
					}
				
				// else, it would be a miss
				} else {
					System.out.println("miss");
				}
			
			// catching invalid inputs from user
			} catch (Exception e) {
				System.out.println("Please enter valid inputs for row and column.");
			}
		}
		/*
		 * When all ships have been sunk, print out a message that the game is over.
		 */
		
		// message saying all ships have been sunk and summarize results
		System.out.println("Game over. All ships have been sunk!");
		System.out.println("It took you " + ocean.getShotsFired() + " shots to sink all ships.");
		ocean.print(); // print ocean for the last time


		//ask users if they want to play again
		while (true) {

			System.out.println("Would you like to play again? (y/Y for Yes, n/N for No): ");
			String playAgain = scanner.next();
			
			// if user answers yes, prompt them to play the game again
			if (playAgain.toLowerCase().startsWith("y")) {
				run();
			}
			
			// if user answers no, quit the program
			else if (playAgain.toLowerCase().startsWith("n")) {
				break;
			}
			
			// for invalid inputs, prompt them the play again question again
			else {
				System.out.println("Please enter a valid yes/no response. ");
			}
		}
		scanner.close();
	}
	
	public static void main(String[] args) {
		
		// initialize a new game 
		BattleshipGame game = new BattleshipGame();
		
		// print instructions
		String gameRules = "Welcome to BattleShip game! Here are the game rules: \n"
				+ "10 random ships are placed across the Ocean grid (10x10), which includes "
				+ "1 Battleship (length of 4), 2 Cruisers (length of 3), 3 Destroyers (length of 2), and 4 Submarine (length of 1) \n"
				+ "Your goal is to find the ships and sink them all by entering the locations you want to shoot the ships at.\n"
				+ "Good luck!";
		System.out.println(gameRules);
		System.out.println(); // blank line
		
		// start game
		game.run();
	}
}