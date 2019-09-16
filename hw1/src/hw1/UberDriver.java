package hw1;

/**
 * 
 * @author Dongho Kim
 *
 */

public class UberDriver 
{
	 /**
	 * Maximum number of passengers allowed in the vehicle at one time.
	 */
	 public static final int MAX_PASSENGERS = 4;

	 /**
	 * Cost to operate the vehicle per mile.
	 */
	 public static final double OPERATING_COST = 0.5;
	 
	 /**
	  * represents the value of permilerate.
	  */
	 private double perMileRate;
	 
	 /**
	  * represents the value of perminutesrate.
	  */
	 private double perMinRate;

	 /**
	  * represents the value of total miles driven.
	  */
	 private int totalMiles;
	 
	 /**
	  * represents the value of total minutes driven.
	  */
	 private int totalMins;

	 /**
	  * represents the value of current passenger count.
	  */
	 private int passengerCount;

	 /**
	  * represents the value of credit earned.
	  */
	 private double credits;

	
	
	/**
	 * @param givenPerMileRate - rate per mile for uber
	 * @param givenPerMinuteRate - rate per minute for uber
	 * Constructs an UberDriver with the given per-mile rate and per-minute rate.
	 */
	public UberDriver(double givenPerMileRate, double givenPerMinuteRate)
	{
		perMileRate = givenPerMileRate;
		perMinRate = givenPerMinuteRate;
	}
	
	/**
	 * @return total miles driven
	 * Returns the total miles driven since this UberDriver was constructed.
	 */
	public int getTotalMiles()
	{
		return totalMiles;
	}
	
	/**
	 * @return total minutes driven
	 * Returns the total minutes driven since this UberDriver was constructed.
	 */
	public int getTotalMinutes()
	{
		return totalMins;
		
	}
	
	/**
	 * @param miles - miles driven
	 * @param minutes - minutes driven
	 * Drives the vehicle for the given number of miles over the given number of minutes.
	 */
	public void drive(int miles, int minutes) 
	{
		totalMins = totalMins + minutes;
		totalMiles = totalMiles + miles;
		
		credits = credits + (miles * perMileRate * passengerCount) + (minutes * perMinRate * passengerCount);
		
	}
	
	/**
	 * @param minutes - minutes spent in teh vehicle without moving
	 * Simulates sitting in the vehicle without moving for the given number of minutes.
	 * 	Equivalent to drive(0, minutes).
	 */
	public void waitAround(int minutes)
	{
		totalMins = totalMins + minutes;
		
		credits = credits + (minutes * perMinRate * passengerCount);
	}
	
	/**
	 * @param miles - miles driven
	 * @param averageSpeed - average speed in miles per hour
	 * Drives the vehicle for the given number of miles at the given speed. Equivalent to
	 * drive(miles, m), where m is the actual number of minutes required, rounded to the
	 * nearest integer. Caller of method must ensure that averageSpeed is positive.
	 */
	public void driveAtSpeed(int miles, double averageSpeed)
	{
		totalMiles = totalMiles + miles;
		totalMins = ((int) Math.ceil((totalMins + 60 / (averageSpeed / miles))));
		
		credits = credits + (miles * perMileRate * passengerCount) + 
				(Math.ceil(60 / (averageSpeed / miles)) * perMinRate * passengerCount);

	}
	
	/**
	 * @return current passenger count
	 * Returns the number of passengers currently in the vehicle.
	 */
	public int getPassengerCount()
	{
		return passengerCount;
		
	}
	
	/**
	 * Increases the passenger count by 1, not exceeding MAX_PASSENGERS.
	 */
	public void pickUp()
	{
		if(passengerCount < MAX_PASSENGERS)
		{
		passengerCount = passengerCount + 1;
		}
		
		
		
	}
	
	/**
	 * Decreases the passenger count by 1, not going below zero.
	 */
	public void dropOff()
	{
		if(passengerCount >= 1)
		{
			passengerCount = passengerCount - 1;
		}
		
		
	}
	
	/**
	 * @return total credits earned
	 * Returns this UberDriver's total credits (money earned before deducting operating costs).
	 */
	public double getTotalCredits()
	{
		return credits;
		
	}
	
	/**
	 * @return total profit
	 * Returns this UberDriver's profit (total credits, less operating costs).
	 */
	public double getProfit()
	{
		return credits - (totalMiles * OPERATING_COST);
		
	}
	
	/**
	 * @return average profit per hour worked
	 * Returns this UberDriver's average profit per hour worked. Caller of method must ensure
	 * 	that it is only called when the value of getTotalMinutes() is nonzero.
	 */
	public double getAverageProfitPerHour()
	{
		return 60 * (credits - (totalMiles * OPERATING_COST)) / totalMins;

	}
	


}
