/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	
	/* Karel creates a checkerboard pattern of beepers inside an empty rectangular world.
	 * High-level steps:
	 * 1. Place beepers in every other corner: createBeeperCheckerboard() 
	 */
	 
	// Karel creates a checkerboard by placing beepers in every other corner of the board. 
	public void run() {
		while (frontIsClear()) {	
			createBeeperCheckerboard();
		}
	}
	
	// Karel creates a checkerboard by placing beepers in alternate corners of the board. 
	//Karel is always moving east in an odd row and moving west in an even row, regardless of the size of its world.
	/* Decomposition of createBeeperCheckboard():
	 * 1. Karel places beepers in a row while moving east: putBeepersInRowMovingEast()
	 * 2. Karel checks for a beeper at the end of the east-moving (odd) row, and places one in the next row if necessary: checkForBeeperInRowMovingEast()
	 * 3. Karel repositions for west-moving row: repositionForRowToWest()
	 * 4. Karel places beepers in a row while moving west: putBeepersInRowMovingWest()
	 * 5. Karel checks for a beeper at teh end of the west-moving (even) row, and places one in the next row if necessary: checkForBeeperInRowMovingWest()
	 */
	public void createBeeperCheckerboard() {
		
		putBeepersInRowMovingEast(); 		
		checkForBeeperAtEndOfRowMovingEast();
		repositionForRowMovingWest();
		putBeepersInRowMovingWest(); 
		checkForBeeperAtEndOfRowMovingWest();	
	}
	
	/* NOTES FOR DEBUGGING WORLD WITH ODD # OF HORIZONTAL ROWS
	 * in putBeepersInRowMovingEast(), the front is blocked on the last space where a beeper is needed (because it's an odd number).
	 * Try chaning the 'while' to an 'if-else' (else frontIsBlocked(), do.... (this may be the same if statements repeated from the rest of the method).
	 * This is probably also true when going west, but haven't tested this yet. 
	 */
	 
	// Karel places a beeper on every other space in a row while moving east: putBeepersInRowMovingEast()
	// Precondition: Karel is facing east at the beginning of an odd row. 
	// Postcondition: Karel is facing east at the end of the same row. 
	private void putBeepersInRowMovingEast() {
		while (frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();	
			if (frontIsClear()) {
				move();
			} 
		}		
	}
	
	/*After placing beepers in a east-moving row, Karel checks to see if there is a beeper on the last grid space, so that it knows whether to place a beeper at the beginning
	*of the next row. Karel then moves to the next row and places the beeper, if the beeper is needed.
	*/
	//Precondition: Karel is facing east at the end of an odd row.
	//Postcondition: Karel is facing north at the end of the row above. 
	private void checkForBeeperAtEndOfRowMovingEast() {
		if (leftIsClear()) {
			if (noBeepersPresent()) {
				turnLeft();
				if (frontIsClear()) {
					if (leftIsClear()) {
						move();
						putBeeper();
					}
				}
			} else {
				turnLeft();
				if (frontIsClear()) {
					move();
				}
			}
		}

	}		
	
	//Karel repositions for a west-moving row, which requires that it turn left.
	//Precondition: Karel is facing north at the end of an even row.
	//Postcondition: Karel is facing west at the end of the same row.
	private void repositionForRowMovingWest() { //repositionForRowToWestWithBeeper
		turnLeft(); 
	}

	//Karel places a beeper on every other space in a row while moving west: pubBeepersinRowMovingWest()
	//Precondition: Karel is facing west at the end of an even row.   
	//Postcondition: Karel is facing west at the beginning of the same row.
	private void putBeepersInRowMovingWest() {
		while (frontIsClear()) {
			if(noBeepersPresent()) {
				putBeeper();
			}
			move();	
			if (frontIsClear()) {
				move();
				if (noBeepersPresent()) {
					putBeeper();
				}
			} 
		}		
	}


	/*After placing beepers in a west-moving row, Karel checks to see if there is a beeper on the last grid space, so that it knows whether to place a beeper at the end
	*of the next row. Karel then moves to the next row and places the beeper, if the beeper is needed.
	*/
	//Precondition: Karel is facing west at the beginning of an even row.
	//Postcondition: Karel is facing east at the beginning of the same row.
	private void checkForBeeperAtEndOfRowMovingWest() 
	{
		if (frontIsBlocked()) 
			if (rightIsClear())
			{
				turnRight();
				move();
				turnRight();
				if (noBeepersPresent()) 
				putBeeper();
					
			}
							
	}	

}