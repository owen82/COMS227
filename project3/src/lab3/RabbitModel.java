package lab3;
/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  // TODO - add instance variables as needed
  private int population;
  private int lastYear;
  private int yearBefore;
 
  public RabbitModel()
  {
	  lastYear = 1;
	  yearBefore = 0;
	  population = 1;
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    
	  return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
	 population = lastYear + yearBefore;
	 yearBefore = yearBefore + lastYear;
	 lastYear = lastYear + yearBefore;
	 
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
	  lastYear = 1;
	  yearBefore = 0;
	  population = 1;
	  
  }
}