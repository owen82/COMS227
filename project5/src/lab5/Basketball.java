package lab5;
public class Basketball
{
	private double diameter;
	private boolean isInflated;
	
  public Basketball(double givenDiameter)
  {
	  diameter = givenDiameter;
  }

  public boolean isDribbleable()
  {
    return isInflated;
  }

  public double getDiameter()
  {
    return diameter;
  }

  public double getCircumference()
  {
    return 2 * Math.PI * diameter / 2;
  }

  public void inflate()
  {
	  isInflated = true;
  }
}
