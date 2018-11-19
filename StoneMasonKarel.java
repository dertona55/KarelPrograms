/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	 /* 1. Ascend column
	  * 2. Move
	  * 3. Descend column
	  * 4. Move 
	 */
	
	// Define run()
	public void run() {
			while (frontIsClear()) {
				placeStones();			
			}
			placeStones();
	}
		
	// Define placeStones()
	private void placeStones() {
		ascendColumn();
		descendColumn();
		walk();		
	}
				
	// Define ascendColumn()
	private void ascendColumn() {
		turnLeft();
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();			
			}
			move();
		} 
		turnAround();
	}
	
		
	//Define descendColumn()
	private void descendColumn() {		
		while (frontIsClear()) {
			if (noBeepersPresent()) {
					putBeeper();
			}
			move();
		}		
		if (noBeepersPresent() ) {
				putBeeper();
		}		
		turnLeft();
	}
		
		//How do I exit 'while' statement?
		
	//Define walk()
	private void walk() {
		if (frontIsClear()) {
			move();
			move();
			move();
			move();
		}
	}
}