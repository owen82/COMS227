package hw2;
import api.Defaults;
import api.Outcome;

/**
 * 
 * @author owen
 *
 */

public class CricketGame 
{
	/**
	 * Given bowls per over
	 */
	private int bowlsPerOver;
	
	/**
	 * Given total number of innings
	 */
	private int totalInnings;
	
	/**
	 * Number of batters
	 */
	private int numPlayers;
	
	/**
	 * Given overs per innings
	 */
	private int oversPerInnings;
	
	/**
	 * Side 0 score
	 */
	private int side0Score;
	
	/**
	 * Side 1 score
	 */
	private int side1Score;
	
	/**
	 * Number of out counts
	 */
	private int numOuts;
	
	/**
	 * Number of bowl counts
	 */
	private int numBowls;
	
	/**
	 * Number of over counts
	 */
	private int numOvers;
		
	/**
	 * Completed number of innings
	 */
	private int completedInning;
	
	/**
	 * Boolean value in play or not
	 */
	private boolean inPlay;
	
	/**
	 * Boolean value is player running or not
	 */
	private boolean isRunning;
	
	
	/**
	 * Constructs a CricketGame using the public default values.
	 */
	public CricketGame() 
	{
		
	}
	
	/**
	 * Constructs a CricketGame with the given configuration parameters.
	 * @param givenBowlsPerOver
	 * @param givenOversPerInnings
	 * @param givenTotalInnings
	 * @param givenNumPlayers
	 */
	public CricketGame(int givenBowlsPerOver, int givenOversPerInnings,
			int givenTotalInnings, int givenNumPlayers)
	{
		bowlsPerOver = givenBowlsPerOver;
		oversPerInnings = givenOversPerInnings;
		numPlayers = givenNumPlayers;
		if(givenTotalInnings % 2 != 0)
		{
			totalInnings = givenTotalInnings + 1;
		}
		else
		{
			totalInnings = givenTotalInnings;

		}
		
	}
	/**
	 * Returns the score for one of the two sides.
	 * @param battingSide
	 * @return current batting side score if true
	 */
	public int getScore(boolean battingSide)
	{
		if(battingSide == true)
		{
			if(whichSideIsBatting() == 0)
			{
				return side0Score;
			}
			else
			{
				return side1Score;
			}
		}
		else
		{
			if(whichSideIsBatting() == 0)
			{
				return side1Score;
			}
			else
			{
				return side0Score;
			}
		}		
		
	}
	
	/**
	 * Returns the number of times the bowler has bowled so 
	 * far during the current over, not counting wides or no-balls.
	 * @return number of bowls count
	 */
	public int getBowlCount()
	{
		return numBowls;
		
	}
	
	/**
	 * Returns the number of completed overs for the current innings.
	 * @return number of overs
	 */
	public int getOverCount()
	{
		return numOvers;
		
	}

	/**
	 * Returns the number of players out in the current innings.
	 * @return number of outs
	 */
	public int getOuts()
	{
		return numOuts;
		
	}
	
	/**
	 * Returns true if the game has ended, false otherwise.
	 * @return ture if game has ended
	 */
	public boolean isGameEnded()
	{
		if(completedInning == totalInnings)
		{
			return true;
		}
		else if(completedInning == totalInnings - 1)
		{
			if(whichSideIsBatting() == 1 && side1Score > side0Score)
			{
				return true;
			}
			else if(whichSideIsBatting() == 0 && side0Score > side1Score)
			{
				return true;
			}
			else
			{
				return false;
			}

		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns 0 if side 0 is batting or 1 if side 1 is batting.
	 * @return currently batting side
	 */
	public int whichSideIsBatting()
	{
		return completedInning % 2;

		
		
	}
	
	/**
	 * Returns the number of innings that have been completed.
	 * @return number of innings completed
	 */
	public int getCompletedInnings()
	{
		return completedInning;
		
	}

	/**
	 * Returns true if the ball is currently in play.
	 * @return ture if the ball is currently in play
	 */
	public boolean isInPlay()
	{
		return inPlay;
		
	}

	/**
	 * Returns true if batsmen are currently running.
	 * @return ture if batsmen are currently running
	 */
	public boolean isRunning()
	{
		return isRunning;
		
	}
	
	/**
	 * Bowls the ball and updates the game state depending on the given outcome.
	 * @param outcome
	 */
	public void bowl(Outcome outcome)
	{
		/*
		 * The ball knocks over a delicate thingie behind the batsman, 
		 * called the wicket. Then the batsman is immediately out.
		 */
		if(outcome == Outcome.WICKET && isGameEnded() == false)
		{
			numBowls = numBowls + 1;
			if(isInPlay() == false)
			{
				numOuts = numOuts + 1;

			}
			if(numOuts + 1 == numPlayers)
			{
				numBowls = 0;
				numOuts = 0;
				numOvers = 0;
				completedInning = completedInning + 1;
			}
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
				
			}
			
		}
		
		/*
		 * The ball hits the batsman and the referee thinks the ball would have hit the wicket,
		 *  if only the batsman had not gotten in the way. 
		 * This is called an "LBW" or "leg before wicket". Then the batsman is immediately out.
		 */
		else if(outcome == Outcome.LBW)
		{
			numBowls = numBowls + 1;
			numOuts = numOuts + 1;
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
			}
			if(numOuts + 1 == numPlayers)
			{
				numOuts = 0;
				numOvers = 0;
				completedInning = completedInning + 1;
			}
		}
		
		/*
		 * The batsman hits the ball and it flies in the air
		 *  and one of the players from the other side catches it. 
		 *  Then the batsman is immediately out.
		 */
		else if(outcome == Outcome.CAUGHT_FLY)
		{
			numBowls = numBowls + 1;
			numOuts = numOuts + 1;
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
				
			}
			if(numOuts + 1 == numPlayers)
			{
				numOuts = 0;
				numOvers = 0;
				completedInning = completedInning + 1;
			}
		}
		/*
		 * The ball is bowled so badly that it doesn't go anywhere near the 
		 * batsman. This is called a "wide" and earns one run for the batting team.
		 */
		else if(outcome == Outcome.WIDE)
		{
			if(whichSideIsBatting() == 1)
			{
				side1Score = side1Score + 1;
			}
			else if(whichSideIsBatting() == 0)
			{
				side0Score = side0Score + 1;
			}
		}
		
		/*
		 * The bowler breaks some other arcane rule in the way he bowls the ball (e.g., "throwing" 
		 * it instead of "bowling" it). This is called a "no-ball" and earns one run for the batting team.
		 */
		else if(outcome == Outcome.NO_BALL)
		{
			if(whichSideIsBatting() == 1)
			{
				side1Score = side1Score + 1;
			}
			else if(whichSideIsBatting() == 0)
			{
				side0Score = side0Score + 1;
			}
			
			
			
		}
		
		/*
		 * The batsman hits the ball and it flies outside the boundary of the
		 *  cricket field. This is called a "boundary" and it 
		 *  counts as six runs. (The batsman doesn't actually run.)
		 */
		else if(outcome == Outcome.BOUNDARY_SIX)
		{
			
			numBowls = numBowls + 1;
			if(isInPlay() == false && isGameEnded() == false)
			{
				if(whichSideIsBatting() == 1)
				{
					side1Score = side1Score + 6;
				}
				if(whichSideIsBatting() == 0)
				{
					side0Score = side0Score + 6;
				}
				
			}
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
			}
			
			
			
		}
		
		/*
		 * The batsman hits the ball and it bounces or 
		 * rolls outside the boundary. This is also a "boundary" but is worth four runs.
		 */
		else if(outcome == Outcome.BOUNDARY_FOUR)
		{
			numBowls = numBowls + 1;
			if(whichSideIsBatting() == 1)
			{
				side1Score = side1Score + 4;
			}
			else if(whichSideIsBatting() == 0)
			{
				side0Score = side0Score + 4;
			}
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
			}
			
		}
		
		/*
		 * The batsman hits the ball and none of the above occurs. 
		 * We will say in this case that the ball is "in play".
		 */
		else
		{
			inPlay = true;
			numBowls = numBowls + 1;
			if(numBowls == bowlsPerOver)
			{
				numBowls = 0;
				numOvers = numOvers + 1;
			}
			if(numOvers == oversPerInnings)
			{
				numOvers = 0;
				numOuts = 0;
				numBowls = 0;
				completedInning = completedInning + 1;
			}
		}
	}
	
	/**
	 * Starts the batsmen running from one end of the pitch to the other.
	 */
	public void tryRun()
	{
		if(isRunning == true)
		{
			if(whichSideIsBatting() == 1)
			{
				side1Score = side1Score + 1;
			}
			if(whichSideIsBatting() == 0)
			{
				side0Score = side0Score + 1;
			}
			
		}
		else if(isInPlay() == false || isGameEnded() == true)
		{
			return;
		}
		isRunning = true;
	}
	
	/**
	 * Transitions from ball in play to ball not in play, without an out.
	 */
	public void safe()
	{
		if(isRunning() == true)
		{
			if(whichSideIsBatting() == 1)
			{
				side1Score = side1Score + 1;
			}
			if(whichSideIsBatting() == 0)
			{
				side0Score = side0Score + 1;
			}
		}
		else if(isInPlay() == false || isGameEnded() == true)
		{
			return;
		}
		isRunning = false;
		inPlay = false;
		
		
	}

	/**
	 * Runs the batsman out (i.e., fielders knock over wicket while batsmen are running).
	 */
	public void runOut()
	{
		if(isRunning() == false || isGameEnded() == true)
		{
			return;
		}
		numOuts = numOuts + 1;
		if(numOuts + 1 == numPlayers)
		{
			numOuts = 0;
			numOvers = 0;
			completedInning = completedInning + 1;
		}
		isRunning = false;
		inPlay = false;
	}


}

