/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends Karel {
	
	// You fill in this part
	/*Step 1: Karel moves to newspaper.
	 *Step 2: Karel picks up newspaper.
	 *Step 3: Karel returns to initial position.
	 */	
	
//Karel collects the newspaper and returns to its start position.
	public void run() {
		moveToPaper();
		getPaper();
		returnToStart();
	}
	
	//Define:Karel moves to the newspaper.
	private void moveToPaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	//Define: Karel turns 90 degrees to the right.
	private void turnRight() {
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	//Define: Karel picks up the newspaper.
	private void getPaper() {
		pickBeeper();
	}
	
	//Define: Karel returns to its start position.
	private void returnToStart() {
		turnAround();
		move();
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnRight();
		turnRight();
	}
	
	//Define: Karel turns around 180 degrees.
	public void turnAround() {
		turnLeft();
		turnLeft();
	}


}	