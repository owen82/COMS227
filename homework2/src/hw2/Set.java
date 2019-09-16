package hw2;

/**
 * @author owen
 *
 */
public class Set 
{
	/**
	 * number of set p1 won
	 */
	private int p1Setcounter;
	/**
	 * number of set p0 won
	 */
	private int p0Setcounter;
	/**
	 * minimum games to win
	 */
	private int minGtoW;
	/**
	 * current game
	 */
	private TennisGame currentG;
	/**
	 * current server, 0 or 1
	 */
	private int currentServer;
	/**
	 * number of games p1 won
	 */
	private int p1Gamecount;
	/**
	 * number of games p0 won
	 */
	private int p0Gamecount;
	/**
	 * p0 score
	 */
	private int p0Score;
	/**
	 * p1 score
	 */
	private int p1Score;
	/**
	 * current state of game
	 */
	private boolean isGameOver = false;
	/**
	 * current state of set
	 */
	private boolean isSetOver = false;
	/**
	 * state of out of bounds
	 */
	private boolean goingOutOfBounds;

		/**
		 * constructs a set with the given minimum number of games
		 * @param minimumGamesToWin
		 * @param playerServesFirst
		 */
		public Set(int minimumGamesToWin, boolean playerServesFirst) 
		{
			currentG = new TennisGame();
			minGtoW = minimumGamesToWin;
					
			if(playerServesFirst)
			{
				currentServer = 1;
				minGtoW = minimumGamesToWin;
			}
			else
			{
				currentServer = 0;
				minGtoW = minimumGamesToWin;
			}
			
			
		}	
		/**
		 * invokes serve on the current game
		 * updates the winner's count of games won
		 * @param serviceFault
		 */
		public void serve(boolean serviceFault)
		{
			if(serviceFault == true) {
				updateWinScore();
				currentG.isOver();
			}
				
		}
		
		/**
		 * invokes hit on the current game
		 * updates the winner's count of games won
		 * @param fault
		 * @param outOfBounds
		 */
		public void hit(boolean fault, boolean outOfBounds)
		{
			if (fault == true && !currentG.isOver()) {
				isGameOver = true;
				if ( currentServer ==1) {
					p1Score++;
				}
			if (currentServer == 0) {
				p0Score++;
			}
			if (p0Score >= minGtoW && p0Score >= p1Score +2) {
				p0Gamecount++;
				isGameOver = true;
			} else if (p1Score >= minGtoW && p0Score >= p1Score + 2) {
				p0Gamecount++;
				isGameOver = true;
			}
			if (fault == false && outOfBounds == true) {
				goingOutOfBounds = true;
				isGameOver = false;
			}
			if (fault == false && outOfBounds == false) {
				goingOutOfBounds = false;
				isGameOver = false;
			}
			}
			
		}
		
		/**
		 * invokes miss on the current game
		 * updates the winner's count of gameswon
		 */
		public void miss()
		{
			if (!currentG.isOver()) {
				if (goingOutOfBounds == false) {
					if (currentServer == 1) {
						p0Score++;
					} else
						p1Score++;
				} else if (goingOutOfBounds == true) {
					if (currentServer == 1) {
						p1Score++;
					} else
						p0Score++;
				}
			}
			
		}
		
		/**
		 * invokes setScore on the current game
		 * updates the winner's count of games won
		 * @param serverScore
		 * @param receiverScore
		 */
		public void fastForward(int serverScore, int receiverScore){
			if (!currentG.isOver() && isSetOver() == false) {
				if (currentServer == 1) {
				p1Score = serverScore;
				p0Score = receiverScore;
			} else {
				p0Score= serverScore;
				p1Score = receiverScore;
			}
			
			if (serverScore == 4) {
				if (currentServer == 1) {
					p1Gamecount += 1;
					isGameOver = true;
				} else {
					p0Gamecount += 1;
					isGameOver = true;
					
				}
			} else if ( receiverScore == 4) {
				if (currentServer == 0) {
					p1Gamecount += 1;
					isGameOver = true;
				} else {
					p0Gamecount += 1;
					isGameOver = true;
					
				}
			}
			if (p1Gamecount >= minGtoW && p1Gamecount >= p0Gamecount + 2) {
				p1Setcounter = p1Setcounter + 1;
				isSetOver = true;
				
			} else if (p0Gamecount >= minGtoW && p0Gamecount >= p1Gamecount +2 ) {
				p0Setcounter = p0Setcounter + 1;
				isSetOver = true;
			} else {
				isSetOver = false;
			}
		}
	}

		
		/**
		 * starts a new game in this set
		 */
		public void newGame(){
			if (isGameOver == true && isSetOver == false && currentServer == 1) {
				currentServer = 0;
				isGameOver = false;
				p1Score = 0;
				p0Score = 0;
			}
			if (isGameOver == true && isSetOver == false && currentServer == 0) {
				currentServer =1;
				isGameOver = false;
				p1Score = 0;
				p0Score = 0;
			}
		}

		/**
		 * returns true if the current game is over
		 * @return
		 */
		public boolean isCurrentGameOver()
		{
			return isGameOver;
			
		}
		
		/**
		 * returns true if the set is over
		 * @return
		 */
		public boolean isSetOver()
		{
			return isSetOver;
			
		}
		
		/**
		 * returns a string representing the current status of the set
		 * @param useCallString
		 * @return
		 */
		public java.lang.String getCurrentStatus(boolean useCallString){
			if (useCallString == false) {
				if ( currentServer == 1) {
					return "Set: " + p1Gamecount + "-" + p0Gamecount + " Game: " + p1Score + "-" + p0Score;
				} else {
					return "Set: " + p0Gamecount + "-" + p1Gamecount + " Game: " + p0Score + "-" +p1Score;
					
				}
				
			}
			else {
				if (currentServer == 1) {
					return "Set: " + p1Gamecount + "-" + p0Gamecount + " Game: " + currentG.getCallString();
 				} else {
					return "Set: " + p0Gamecount + "-" + p1Gamecount + " Game: " + currentG.getCallString();

 				}
			}
			
		}
		
		/**
		 * returns the player who is the server in current game
		 * @return
		 */
		public int whoIsServing()
		{
			return currentServer;
			
		}

		/**
		 * returns the number of games won by player 0
		 * @return
		 */
		public int player0GamesWon()
		{
			return p0Gamecount;
			
		}
		
		/**
		 * returns the number of games won by player 1
		 * @return
		 */
		public int player1GamesWon()
		{
			return p1Gamecount;
			
		}
		
		private void updateWinScore() {
			if (currentG.serverWon() == true) {
				if (currentServer == 0) {
					p1Score++;
				} else {
					p0Score++;
				}
			} else {
				if (currentG.receiverWon() == true) {
					if ( currentServer ==1) {
						p1Score++;
					} else {
						p0Score++;;
					}
				}
			}
		}
		

}
