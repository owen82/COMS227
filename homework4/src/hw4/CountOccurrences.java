package hw4;

import hw4.api.ScoringCategory;
/**
 * Scoring category that is based on counting occurrences of a particular 
 * target value (specified in the constructor). This category is 
 * satisfied by any hand. The score is the sum of just the die 
 * values that match the target value.
 * @author owen
 *
 */
public class CountOccurrences extends Abstract
{
	private int number = 0;

	public CountOccurrences(String name, int number) 
	{
		super(name, number);
		this.number = number;
	}


	@Override
	public boolean isSatisfiedBy(Hand hand) 
	{
		int[] diceVal = hand.getAllValues();
		
		for(int i = 0; i < diceVal.length; i++)
		{
			if(diceVal[i] == this.number)
			{
				return true;
			}
		}
	
		return false;
	}

	@Override
	public int getPotentialScore(Hand hand)
	{
		int[] diceVal = hand.getAllValues();
		int counter = 0;
		
		if(!isSatisfiedBy(hand))
		{
			return 0;
		}
		else
		{
			for(int i = 0; i < diceVal.length; i++)
			{
				if(diceVal[i] == this.number)
				{
					counter++;
				}
			}
			return this.number * counter;
			
		}
		
	}



}
