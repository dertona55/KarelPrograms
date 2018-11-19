/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// In this program, Karel places a single beeper at the midpoint (center space) of 1st Street. The program allows Karel to do this in any size world.
	
	/* ALGORITHM: 
	 * 1. Karel places one beeper in the midpoint space of 1st Street. /putBeeperInMidpoint()
	 * 2. Karel remove extraneous beepers. /removeExtraBeepers() 
	 */
	
	/* STEP REFINEMENT:
	 ** 1. Determine where the midpoint space is on 1st Street.  /putBeeperInMidpoint()
	 ** 	A.Place a beeper in the first column. ***
	 **		B. Determine where the last column is.  //findLastColumn()		 
	 **				- Move while front is clear ***
	 **				- When front isn't clear, place beeper ***
	 **		C. Reposition Karel to be ready to move in the other direction. //repositionKarel();
	 *		D. Determine where the first column without a beeper is  //findFirstColWithBeeper()
	 * 				While moving west, check for beepers until a beeper is present.
	 * 				- Move *** (Because Karel just left a beeper in the space before, Karel knows this space is not the midpoint.) 
	 * 				- Move ***
	 * 				- Check if beeper is present *** (If beeper is present, Karel knows the prior space is the midpoint.) 
	 **		E. Determine where the last column with a beeper is. //findLastColWithBeeper()	
	 *  			While moving east, check for beepers until a beeper is present.
	 * 				- Move ***
	 * 				- Move ***
	 * 				- Check if beeper is present ***		
	 **		F. Repeat this pattern (c. and d.) until there is a beeper on each side of Karel. //findFirstColWithBeeper() & //findLastColWithBeeper()
	 *	3. Mark the midpoint space to make removing extra beepers easier. /markMidpoint()
	 *  4. Remove extraneous beepers. /removeExtraBeepers()
	 *  5. Remove color from midpoint.
	 */
	
	// Karel places a single beeper at the midpoint (center space) of 1st Street.
	public void run() 
	{
		putBeeperInMidpoint();
		markMidpoint();
		removeExtraBeepers();
		removeColorFromMidpoint();
	}
	
	//FROM RUN()
	//Determine where the midpoint space is on 1st Street.
	//Precondition: Karel is at Start, facing east.
	//Postcondition: Karel is on the midpoint of 1st Street.
	private void putBeeperInMidpoint() 
	{
		putBeeper();
		findLastColumn();
		repositionKarel();
		//WHILE LOOP: findFirstColWithBeeper() & findLastColWithBeeper() are in a loop
		while (noBeepersPresent()) 
		{
			findFirstColWithBeeper();
			repositionKarel();
			findLastColWithBeeper();
			repositionKarel();
		}
		moveToMidpoint();
	}
	
	// Determine where the last column is. ///findLastColumn()	
		//Precondition: Karel is at Start, facing east, with a beeper on Start.
		//Postcondition: Karel is at the end of 1st St. facing east, with a beeper on its space.
	private void findLastColumn() 
	{
		while (frontIsClear()) 
		{
			move();
		}
		putBeeper();
	}
	
	//Reposition Karel to be ready to move in the other direction. 
	/*Karel turns around and moves one space, so that it begins on a space with no beeper, 
	unless it's on the midpoint.///repositionKarel();
	*/
		//Precondition: Karel is facing either east or west.
		//Postcondition: Karel is facing the opposite direction of the precondition.
	private void repositionKarel() 
	{
		turnAround();
		move();
	}
	
	//Determine where the first column without a beeper is  ///findFirstColWithBeeper()
	/*While moving west, check for beepers until a beeper is present.
	*Because Karel just left a beeper in the space before, Karel knows this space is not the midpoint. 
	* If a beeper is present, Karel knows the prior space is the midpoint.
	*/
		//Precondition: Karel is at the end of 1st St. facing west, with a beeper on its space.
		//??? Check to see if Pre changed in 
		//Postcondition: Karel is on the farthest space from start with a beeper.
	private void findFirstColWithBeeper() 
	{
		if (noBeepersPresent()) 
		{
			putBeeper(); //THIS NEEDS TO BE CONDITIONAL
			move();
		} 		
		while (noBeepersPresent()) 
		{
			move();
		}
	}
	
	// Determine where the last column with a beeper is. ///findLastColWithBeeper()	
	/*While moving east, check for beepers until a beeper is present.
	 * Because Karel just left a beeper in the space before, Karel knows this space is not the midpoint. 
	* If a beeper is present, Karel knows the prior space is the midpoint.
	 */
		//Precondition:  Karel is on the farthest space with a beeper from start.
		//Postcondition: Karel is on the farthest space with a beeper from the end of 1st St.
	private void findLastColWithBeeper() 
	{
		if (noBeepersPresent()) 
		{
			putBeeper(); //THIS NEEDS TO BE CONDITIONAL
			move();
		} 		
		while (noBeepersPresent()) 
		{
			move();
		}
	}

	
	//Move to midpoint. ///moveToMidpoint()
		//Precondition: All corners of 1st St. have beepers, and Karel is one space away from the midpoint. 
		//Postcondition: Karel is on the midpoint space.
	private void moveToMidpoint() 
	{
		turnAround();
		move();
	}
	
	//FROM RUN()
	//Mark the midpoint space. Marking the midpoint helps prevent Karel from accidentally removing that beeper while removing all other beepers.
		//Precondition: Karel is on the midpoint space, with a beeper present and color is null.
		//Postcondition: Karel is on the midpoint space, with a beeper present and color is blue.
	private void markMidpoint() 
	{
		paintCorner(YELLOW);
	}
	
	//FROM RUN()
	//Remove all extra beepers. Only the beeper on the 1st St midpoint should remain.
		//Precondition: Karel is on the midpoint space of 1st St, with a beeper present.
		//Postcondition: Karel is at Start, and the only beeper remaining is the one on the 1st St midpoint. The midpoint beeper is still yellow.
	private void removeExtraBeepers() 
	{
		removeExtraBeepersGoingEast();
		repositionKarel();
		removeExtraBeepersGoingWest();
		repositionKarel();
	}
	
	//Remove extra beepers going east.
	private void removeExtraBeepersGoingEast() 
	{
		if (frontIsClear()) 
		{
			move();
			pickBeeper();
		}
		while (frontIsClear()) 
		{
			move();
			if (beepersPresent()) 
			{
				if (cornerColorIs(null)) 
				{
					pickBeeper();
				}				
			}			
		}			
	}
	
	//Remove extra beepers going west.
	private void removeExtraBeepersGoingWest() 
	{
		while (frontIsClear()) 
		{
			move();
			if (beepersPresent()) 
			{
				if (cornerColorIs(null)) 
				{
					pickBeeper();								
				}
			}
		}
	}
	
	//Remove color from midpoint.
		//Precondition: Karel is facing east. The only beeper remaining is at the midpoint, and the midpoint color is still yellow.
		//Postcondition: Karel is at standing at the mdipoint. The midpoint beeper is the only one remaining. The midpoint corner color is null.


	private void removeColorFromMidpoint() 
	{
		while (noBeepersPresent()) 
		{
			move();
		}
		paintCorner(null);
	}
	
	
	

}