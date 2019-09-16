package hw2;

/**
 * @author owen
 *
 */
public class TennisGame 
{
		/**
		 * serverScore
		 */
		private int serverScore;
		/**
		 * receiverScore
		 */
		private int receiverScore;
		/**
		 * serviceFault
		 */
		private boolean sF;
		/**
		 * numberOfFaults
		 */
		private int nF;
		/**
		 * headedOutOfBounds
		 */
		private boolean hOB;
		/**
		 * ballDirection
		 */
		private BallDirection bD;
		
	
		/**
		 * constructs a new TennisGame
		 * both players have 0 points
		 * initially ball is not in play
		 */
		public TennisGame()
		{
			serverScore = 0;
			receiverScore = 0;
			bD = BallDirection.NOT_IN_PLAY;
		}

	
		/**
		 * directly sets the scores
		 * @param newServerScore
		 * @param newReceiverScore
		 */
		public void setScore(int newServerScore, int newReceiverScore)
		{
			serverScore = newServerScore;
			receiverScore = newReceiverScore;
			bD = BallDirection.NOT_IN_PLAY;
			
		}
		
		/**
		 * returns the current points for the receiver
		 * @return
		 */
		public int getReceiverPoints()
		{
			return receiverScore;
		}
		
		/**
		 * returns the current points for the server
		 * @return
		 */
		public int getServerPoints()
		{
			return serverScore;
			
		}
		
		/**
		 * returns the current status of ball
		 * @return
		 */
		public BallDirection getBallStatus()
		{
			return bD;
			
		}
		
		/**
		 * returns true if the game is over
		 * @return
		 */
		public boolean isOver()
		{
			if(serverWon() || receiverWon())
			{
				return true;
			}
			else
			{
			return false;
			}
			
		}
		
		/**
		 * returns true if server won
		 * @return
		 */
		public boolean serverWon()
		{
			if(serverScore - 3 > 0 && serverScore - receiverScore >= 2)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
			
		}
		
		/**
		 * returns true if receiver won
		 * @return
		 */
		public boolean receiverWon()
		{
			if(receiverScore - 3 > 0 && receiverScore - serverScore >= 2)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		
		/**
		 * simulates the server serving the ball
		 * @param serviceFault
		 */
		public void serve(boolean serviceFault)
		{
			sF = serviceFault;
			if(!sF)
			{
				bD = BallDirection.TOWARD_RECEIVER;
			}
			if(sF && (bD == BallDirection.NOT_IN_PLAY) && !isOver())
			{
				nF += 1;
			}
			
			if(nF == 2 && bD == BallDirection.NOT_IN_PLAY && !isOver())
			{
				++receiverScore;
				nF = 0;
			}
		}
		
		/**
		 * simulates a hit of the ball
		 * @param fault
		 * @param headedOutOfBounds
		 */
		public void hit(boolean fault, boolean headedOutOfBounds){
			if (fault == true && bD != BallDirection.NOT_IN_PLAY) {
				if(bD == BallDirection.TOWARD_RECEIVER) {
					serverScore = serverScore + 1;
					bD = BallDirection.NOT_IN_PLAY;
					hOB = false;
				} else {
					receiverScore = receiverScore + 1;
					bD = BallDirection.NOT_IN_PLAY;
					hOB = false;
					  	
				}
			} else if (fault == false && headedOutOfBounds == true && bD != BallDirection.NOT_IN_PLAY) {
					if(bD == BallDirection.TOWARD_RECEIVER) {
						bD = BallDirection.TOWARD_SERVER;
						hOB = true;
					} else {
						bD = BallDirection.TOWARD_RECEIVER;
						hOB = true;
					}
			} else if (fault == false && headedOutOfBounds == false && bD != BallDirection.NOT_IN_PLAY) {
				if (bD == BallDirection.TOWARD_RECEIVER) {
					bD = BallDirection.TOWARD_SERVER;
					hOB = false;
				} else {
					bD = BallDirection.TOWARD_RECEIVER;
					hOB = false;
				}
			} else if (bD == BallDirection.NOT_IN_PLAY) {
				
				bD = BallDirection.NOT_IN_PLAY;
					
					
				}
					
					
			}
			
//			if(fault && bD == BallDirection.TOWARD_RECEIVER)
//			{
//				serverScore = serverScore + 1;
//				bD = BallDirection.NOT_IN_PLAY;
//			}
//			else if(fault && bD == BallDirection.TOWARD_SERVER)
//			{
//				receiverScore = receiverScore + 1;
//				bD = BallDirection.NOT_IN_PLAY;
//			}
//			
//			if(!fault && bD == BallDirection.TOWARD_RECEIVER)
//			{
//				bD = BallDirection.TOWARD_SERVER;
//			}
//			else if(!fault && bD == BallDirection.TOWARD_SERVER)
//			{
//				bD = BallDirection.TOWARD_RECEIVER;
//			}
//			
//			if(!fault && headedOutOfBounds)
//			{
//				hOB = true;
//			}
			
		
		
		/**
		 * simulates a miss of the ball
		 */
		public void miss()
		{
			if(hOB && bD == BallDirection.TOWARD_RECEIVER)
			{
				++receiverScore;
			}
			else if(hOB && bD == BallDirection.TOWARD_SERVER)
			{
				++serverScore;
			}
			bD = BallDirection.NOT_IN_PLAY;
					
			if(!hOB && bD == BallDirection.TOWARD_RECEIVER)
			{
				++serverScore;
			}
			else if(!hOB && bD == BallDirection.TOWARD_SERVER)
			{
				++receiverScore;
			}
			bD = BallDirection.NOT_IN_PLAY;
		}
		
		/**
		 * returns a string representing raw points
		 * @return
		 */
		public java.lang.String getScore()
		{
			String x = serverScore + "";
			String y = receiverScore + "";
			return x + "-" + y;
			
		}

		/**
		 * returns a string representing the score
		 * @return
		 */
		public java.lang.String getCallString()
		{
			if (serverScore >= 4 && serverScore == receiverScore + 1) {
				return "advantage in";
			} else if (receiverScore >= 4 && receiverScore == serverScore + 1) {
				return "advantage out";
			} else if (serverScore == receiverScore && serverScore >= 3) {
				return "deuce";
			} else if (serverScore == receiverScore && serverScore < 3) {
				if (serverScore == 0) {
					return "love-all";
				} else if (serverScore == 1) {
					return "15-all";
				} else {
					return "30-all";
				}
			} else {
				if (serverScore == 3 && receiverScore == 0) {
					return "40-love";
				} else if (serverScore == 2 && receiverScore == 0) {
					return "30-love";
				} else if (serverScore == 1 && receiverScore == 0) {
					return "15-love";
				} else if (serverScore == 3 && receiverScore == 1) {
					return "40-15";
				} else if (serverScore == 2 && receiverScore == 1) {
					return "30-15";
				} else if (serverScore == 0 && receiverScore == 1) {
					return "love-15";
				} else if (serverScore == 3 && receiverScore == 2) {
					return "40-30";
				} else if (serverScore == 1 && receiverScore == 2) {
					return "15-30";
				} else if (serverScore == 0 && receiverScore == 2) {
					return "love-30";
				} else if (serverScore == 2 && receiverScore == 3) {
					return "30-40";
				} else if (serverScore == 1 && receiverScore == 3) {
					return "15-40";
				} else if (serverScore == 0 && receiverScore == 3) {
					return "love-40";
				} else {
					return serverScore + "-" + receiverScore;
					
				}
		}
//			String x = serverScore + "";
//			String y = receiverScore + "";
//			String result = x + "-" + y;
//			if(serverScore >= 4 && serverScore - receiverScore == 1)
//			{
//				result = "advantage in";
//			}
//			else if(receiverScore >= 4 && receiverScore - serverScore == 1)
//			{
//				result = "advantage out";
//			}
//			else if(serverScore == receiverScore)
//			{
//				result = "deuce";
//			}
//			else if(serverScore == receiverScore && serverScore == 0)
//			{
//				result = "love-all";
//			}
//			else if(serverScore == receiverScore && serverScore == 1)
//			{
//				result = "15-all";
//				
//			}
//			else if(serverScore == receiverScore && serverScore ==2)
//			{
//				result = "30-all";
//			}
//			else if(serverScore == 0)
//			{
//				x = "love";
//			}
//			else if(serverScore == 1)
//			{
//				x = "15";
//			}
//			else if(serverScore == 2)
//			{
//				x = "30";
//			}
//			
//			if(receiverScore == 0)
//			{
//				y = "love";
//			}
//			else if(receiverScore == 1)
//			{
//				y = "15";
//			}
//			else if(receiverScore == 2)
//			{
//				y = "30";
//			}
//
//
//			return result;

			
		}



}
