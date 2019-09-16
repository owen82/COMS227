package hw1;


/**
 * Examples of test cases described in the "How to get started" section of the
 * homework pdf.
 */
public class SimpleTest
{
  public static void main(String[] args)
  {
    // keep track of total miles
    UberDriver d = new UberDriver(1.00, 0.20);    
    d.drive(10, 25);
    d.drive(7,  35);
    System.out.println(d.getTotalMiles()); // expected 17
    
    // keep track of total minutes
    d = new UberDriver(1.00, 0.20);    
    d.drive(10, 25);
    d.drive(7,  35);
    System.out.println(d.getTotalMinutes()); // expected 60
    
    // waitAround should affect minutes, but not miles
    d = new UberDriver(1.00, 0.20);    
    d.drive(10, 25);
    d.waitAround(5);
    System.out.println(d.getTotalMiles()); // expected 10
    System.out.println(d.getTotalMinutes()); // expected 30
    
    // driving 10 miles at 48 mph takes 12.5 minutes
    d = new UberDriver(1.00, 0.20);    
    d.driveAtSpeed(10, 48);
    System.out.println(d.getTotalMinutes()); // expected 13 (rounded up)
    System.out.println(d.getTotalMiles()); // expected 10
    
    // passenger count
    d = new UberDriver(1.00, 0.20);
    d.pickUp();
    d.pickUp();
    System.out.println(d.getPassengerCount()); // expected 2
    d.dropOff();
    System.out.println(d.getPassengerCount()); // expected 1
    
    // passenger count should not go above MAX_PASSENGERS or below 0
    d = new UberDriver(1.00, 0.20);
    d.pickUp();
    d.pickUp();
    d.pickUp();
    d.pickUp();
    d.pickUp();
    d.pickUp();
    System.out.println(d.getPassengerCount()); // expected 4
    
    // try a calculation of driver credits
    d = new UberDriver(1.00, 0.20);
    d.drive(5, 15); // has no effect on driver credits
    d.pickUp();
    d.drive(10, 25);
    System.out.println(d.getTotalCredits());  // expected 15.0

    // try with different rates
    d = new UberDriver(2.50, 0.30);
    d.pickUp();
    d.drive(10, 25);
    System.out.println(d.getTotalCredits());  // expected 32.5
    
    // multiple passengers
    d = new UberDriver(1.00, 0.20); 
    d.pickUp();
    d.drive(10, 25);
    d.pickUp();
    d.drive(7, 35);
    System.out.println(d.getTotalCredits());  // expected 43.0
    
    // profit
    d = new UberDriver(1.00, 0.20);
    d.pickUp();
    d.drive(10, 25);
    System.out.println(d.getProfit());  // expected 15.0 - 5.0 = 10.0
    System.out.println(d.getAverageProfitPerHour());  // 60 * 10.0 / 25 = 24.0
    
    // profit with multiple passengers and some idle time
    d = new UberDriver(1.00, 0.20);
    d.waitAround(30);
    d.pickUp();
    d.drive(10, 25);
    d.pickUp();
    d.drive(7, 35);
    System.out.println(d.getProfit());  // expected 43.0 - 8.5 = 34.5
    System.out.println(d.getAverageProfitPerHour());  // expected 23.0
  }

}