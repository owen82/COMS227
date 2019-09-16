package hw2;

import api.Outcome;

public class CricketTest {

	  public static void main(String[] args)
	  {
		  
		  CricketGame g = new CricketGame(2, 3, 4, 6);
		  g.bowl(Outcome.HIT);
		  g.tryRun();
		  g.tryRun();
		  g.safe();
		  
		  g.bowl(Outcome.HIT);
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.tryRun();
		  g.runOut();
		  
		  g.bowl(Outcome.HIT);
		  g.runOut();
		  
		  g.bowl(Outcome.BOUNDARY_FOUR);
		  g.bowl(Outcome.WICKET);
		  
		  g.bowl(Outcome.HIT);
		  g.tryRun();
		  g.safe();




		  
		  





		
	
		  
		  System.out.println(g.getScore(true));

		  System.out.println(g.getScore(false));

		  
//		  For CricketGame(2, 3, 100, 4), after after bowl(WICKET)
//		  * 23, should have 1 over, 0 bowls. (bowls incorrect)
//		  expected:<0> but was:<1>
	  }
	  

	

}



