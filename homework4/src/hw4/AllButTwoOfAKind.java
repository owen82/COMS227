package hw4;
import hw4.api.*;
public class AllButTwoOfAKind extends Abstract
{
	private int number;

	/**
	 * Constructs an AllButTwoOfAKind with the given display name.
	 * 
	 * @param name name of this category
	 * @author owen
	 */
	public AllButTwoOfAKind(java.lang.String name) 
	{
		super(name);
	}
	

	/**
	 * Description copied from interface: ScoringCategory Determines whether the
	 * given hand satisfies the defined criteria for this scoring category. The
	 * criteria are determined by the concrete type implementing the interface. This
	 * method does not modify the state of this category and does not modify the
	 * hand. Specified by: isSatisfiedBy in interface ScoringCategory
	 * 
	 * @param hand hand to check
	 * @return true if the given hand satisfies the defined criteria for the
	 *         category, false otherwise
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) 
	{
		int [] diceVal = hand.getAllValues();
		int numOfDice = hand.getNumDice();
		
		if(((diceVal[0] == diceVal[numOfDice - 3])) || (diceVal[2] == diceVal[numOfDice - 1])
				|| (diceVal[1] == diceVal[numOfDice - 2]))
		{
			number = diceVal[numOfDice / 2];
			return true;
		}
		else
		{
			return false;
		}


	}


	/**
	 * Description copied from interface: ScoringCategory Returns the potential
	 * score that would result from using the given hand to fill this category.
	 * Always returns zero if the isSatisfiedBy() method returns false for the given
	 * hand. This method does not modify the state of this category and does not
	 * modify the hand. Specified by: getPotentialScore in interface ScoringCategory
	 * 
	 * @param hand hand to check
	 * @return potential score for the given hand
	 */
	@Override
	public int getPotentialScore(Hand hand) 
	{
		if(!isSatisfiedBy(hand))
		{
			return 0;
		}
		else
		{
			return number * (hand.getNumDice() - 2);
		}

	}
}
