package hw4;

public class Chance extends Abstract
{
/**
 * Scoring category that is satisfied by any hand. The score is the sum of all die values.
 * @param name
 * @author owen
 */
	public Chance(String name) 
	{
		super(name);
	}
	
	@Override
	public int getPotentialScore(Hand hand)
	{
		int[] diceVal = hand.getAllValues();
		int numOfDice = diceVal.length;
		int score = 0;
		
		for(int i = 0; i < numOfDice; i++)
		{
			score = score + diceVal[i];
			
		}
		return score;
	}

}
