package hw4;

import hw4.api.ScoringCategory;

public abstract class Abstract implements ScoringCategory
{

	private Hand hand;
	private String name;
	private int score;
	private int potentialScore;
	private boolean isCurrentlyFilled = false;
	
	public Abstract()
	{
		
	}
	
	public Abstract(Hand hand)
	{
		this.hand = hand;
	}
	
	public Abstract(String name)
	{
		this.name = name;
	}
	
	public Abstract(String name, int potentialScore)
	{
		this.name = name;
		this.potentialScore = potentialScore;
	}
	
	public boolean isSatisfiedBy(Hand hand) {
		return false;

	}

	/**
	 * Description copied from interface: ScoringCategory Determines whether this
	 * category is filled. Specified by: isFilled in interface ScoringCategory
	 * 
	 * @return true if this category has been permanently filled, false otherwise
	 */
	public boolean isFilled() 
	{
		if(isCurrentlyFilled == true)
		{
			return true;
		}
		return false;

	}

	/**
	 * Description copied from interface: ScoringCategory If the category has been
	 * filled, returns the score for the permanently saved hand that was used to
	 * fill it; otherwise returns 0. Specified by: getScore in interface
	 * ScoringCategory
	 * 
	 * @return score for the category or zero if not filled
	 */
	public int getScore() 
	{
		if(isFilled()) 
		{
			return score;
		}
			return 0;
	}

	/**
	 * Description copied from interface: ScoringCategory Returns the Hand that was
	 * used to fill this category, or null if not filled. Specified by: getHand in
	 * interface ScoringCategory
	 * 
	 * @return hand filling this category, or null if not filled
	 */
	public Hand getHand() 
	{
		if(isCurrentlyFilled != true)
		{
			return null;
		
		}
		else 
		{
			return hand;
		}

	}

	/**
	 * Description copied from interface: ScoringCategory Returns the name for this
	 * category. Specified by: getDisplayName in interface ScoringCategory
	 * 
	 * @return name for this category
	 */
	public java.lang.String getDisplayName() 
	{
		return name;
	}

	/**
	 * Description copied from interface: ScoringCategory Permanently sets the hand
	 * being used to fill this category. The score is set to the value of
	 * getPotentialScore for the given hand. Throws IllegalStateException if the
	 * category has already been filled or if the given hand is not complete (as
	 * defined by the Hand.isComplete method). Specified by: fill in interface
	 * ScoringCategory
	 * 
	 * @param hand hand to be used to satisfy this category
	 */
	public void fill(Hand hand) 
	{
		 if (isFilled() || !hand.isComplete())
		 {
			 throw new IllegalStateException();
		 }
		 score = getPotentialScore(hand);
		 isCurrentlyFilled = true;

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
	public int getPotentialScore(Hand hand) 
	{
		if(!isSatisfiedBy(hand))
		{
			return 0;
		}
		else
		{
			return potentialScore;
		}

	}
	
	
	
	

}
