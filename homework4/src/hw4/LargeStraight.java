package hw4;
import hw4.api.*;
public class LargeStraight extends Abstract
{
	
	
	/**
	 * Constructs a LargeStraight category with the given display name and score.
	 * @param name name of this category
	 * @param points points awarded for a hand that satisfies this category
	 * @author owen
	 */
	public LargeStraight(java.lang.String name, int points) 
	{
		super(name, points);
	}
	/**
	 * Description copied from interface: ScoringCategory
Determines whether the given hand satisfies the defined criteria for this scoring category. The criteria are determined by the concrete type implementing the interface. This method does not modify the state of this category and does not modify the hand.
Specified by:
isSatisfiedBy in interface ScoringCategory
	 * @param hand hand to check
	 * @return true if the given hand satisfies the defined criteria for the category, false otherwise
	 */
	public boolean isSatisfiedBy(Hand hand) 
	{
		int[] diceVal = hand.getAllValues();
		int numOfDice = hand.getNumDice();
		
		for (int i = 0; i < numOfDice - 1; i++)
		{
			if (diceVal[i] == diceVal[i + 1] - 1)
			{
				
			}
			else
			{
				return false;
			}
		}
		return true;
	}

}
