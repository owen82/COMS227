package hw4;
import hw4.api.*;
public class AllPrimes extends Abstract{

	private static String name = "All Primes";
	public AllPrimes() 
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
	 * @author owen
	 */
	@Override
	public boolean isSatisfiedBy(Hand hand) 
	{
		int[] diceVal = hand.getAllValues();
		int numOfDice = hand.getNumDice();
		
		for(int i = 0; i < numOfDice; i++) 
		{
			if(!isPrime(diceVal[i])) 
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean isPrime(int n) 
	{
	    for(int i = 2; i < n; i++) 
	    {
	        if(n % i == 0)
	            return false;
	    }
	    return true;
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
		int[] dVal = hand.getAllValues();
		int numOfDice = dVal.length;
		int score = 0;
		
		if(!isSatisfiedBy(hand)){
			return 0;
		}
		else 
		{
			for(int i = 0; i < numOfDice; i++)
			{
				score = score + dVal[i];
			}

		}
		return score;

	}

}
