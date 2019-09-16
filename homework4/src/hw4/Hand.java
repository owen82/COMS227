package hw4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

import hw4.api.Die;


/**
 * This class represents values of a group of dice for a dice game such as Yahtzee in which 
 * multiple rolls per turn are allowed. The number of faces on the dice, 
 * the number of dice in the Hand, and the maximum number of rolls are configurable 
 * via the constructor. At any time some of the dice may be <em>available</em>
 * to be rolled, and the other dice are <em>fixed</em>.  Calls to the 
 * <code>roll()</code> method roll the available
 * dice only.  After the maximum number of rolls, all dice are automatically
 * fixed; before that, the client can select which dice to "keep" (change from
 * available to fixed) and which dice to "free" (change from fixed to
 * available).
 * <p>
 * Note that valid die values range from 1 through the given
 * <code>maxValue</code>. 
 * @author owen
 */
public class Hand
{
	private ArrayList <Die> initialHand = new ArrayList <Die>();
	private ArrayList <Die> currentHand = new ArrayList <Die>();
	private Comparator <Die> c = new DieComparator();
	private int rollCounter;
	private int numDice;
	private int maxValue;
	private int maxRolls;

  /**
   * Constructs a new Hand in which each die initially has 
   * the value 1.
   * @param numDice
   *   number of dice in this group
   * @param maxValue
   *   largest possible die value, where values range from 1
   *   through <code>maxValue</code>
   * @param maxRolls
   *   maximum number of total rolls
   */
  public Hand(int numDice, int maxValue, int maxRolls)
  {
	  this.numDice = numDice;
	  this.maxValue = maxValue;
	  this.maxRolls = maxRolls;
	  
	  for(int i = 0; i < numDice; i++)
	  {
		  initialHand.add(new Die(1, maxValue));
	  }
  }   
  
  /**
   * Constructs a new Hand in which each die initially has 
   * the value given by the <code>initialValues</code> array.
   * If the length of the array is greater than the number of dice, the
   * extra values are ignored.  If the length of the array is smaller
   * than the number of dice, remaining dice
   * will be initialized to the value 1.
   * <p>
   * This version of the constructor is primarily intended for testing.
   * @param numDice
   *   number of dice in this hand
   * @param maxValue
   *   largest possible die value, where values range from 1
   *   through <code>maxValue</code>
   * @param maxRolls
   *   maximum number of total rolls
   * @param initialValues
   *   initial values for the dice
   */
  public Hand(int numDice, int maxValue, int maxRolls, int[] initialValues)
  {
	  this.numDice = numDice;
	  this.maxValue = maxValue;
	  this.maxRolls = maxRolls;
	  
	  
	  if(initialValues.length > numDice)
	  {
		  for(int i = 0; i < numDice; i++)
		  {
			  currentHand.set(i, new Die(initialValues [i], maxValue));
		  }
	  }
	  else if(initialValues.length < numDice)
	  {
		  for(int i = 0; i < numDice; i++)
		  {
			  currentHand.add(new Die(1, maxValue));
		  }
		  
		  for(int i = 0; i < numDice; i++)
		  {
			  currentHand.set(i, new Die(initialValues [i], maxValue));
		  }
		  
	  }
	  Die [] currentDieArraySorted = (Die[]) currentHand.toArray();
	  Arrays.sort(currentDieArraySorted, c);
  }  
  
  /**
   * Returns the number of dice in this hand.
   * @return
   *   number of dice in this hand
   */
  public int getNumDice()
  {
    return numDice;
  }
  
  /**
   * Returns the maximum possible die value (i.e., number of faces on each die).
   * Valid values start at 1.
   * @return
   *   maximum possible die value
   */
  public int getMaxValue()
  {
    return maxValue;
  }
  
  /**
   * Rolls all available dice using the given random number generator.
   * If the number of rolls has reached the maximum, all dice are
   * marked as fixed.
   * @param rand
   *   random number generator to be used for rolling dice
   */
  public void roll(Random rand)
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(rollCounter == maxRolls)
		  {
			  for(int j = 0; j < currentHand.size(); j++)
			  {
				  currentHand.get(j).setAvailable(false);
			  }

		  }
		  if(currentHand.get(i).isAvailable() == true)
		  {
			  currentHand.get(i).roll(rand);
			  rollCounter++;
		  }
	  }
  }

  /**
   * Selects a single die value to be changed from available to fixed.
   * If there are multiple available dice with the given value, 
   * only one is changed to be fixed. Has no effect if the given value is 
   * not among the values in the available dice.  Has no effect if
   * the number of rolls has reached the maximum.
   * @param value
   *   die value to be changed from available to fixed
   */
  public void keep(int value)
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(maxRolls == rollCounter) 
		  {
			  return;
		  }
		  else if(currentHand.get(i).value() == value)
		  {
			  currentHand.get(i).setAvailable(false);
			  break;
		  }
	  }
  }

  /**
   * Selects a die value to be changed from the fixed to
   * available (i.e. so it will be re-rolled in the
   * next call to <code>roll()</code>). If there are multiple fixed dice 
   * with the given value, only one is changed be available. 
   * Has no effect if the given value is 
   * not among the values in the fixed dice. Has no effect if
   * the number of rolls has reached the maximum.
   * @param value
   *   die value to be moved
   */
  public void free(int value)
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(maxRolls == rollCounter) 
		  {
			  return;
		  }
		  else if(currentHand.get(i).value() == value)
		  {
			  currentHand.get(i).setAvailable(true);
			  break;
		  }
	  }
  }
  
  /**
   * Causes all die values to be changed from available to fixed.
   * Has no effect if the number of rolls has reached the maximum.
   */
  public void keepAll()
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(maxRolls == rollCounter) 
		  {
			  return;
		  }
		  
		  currentHand.get(i).setAvailable(false);
		  
	  }
	  
  }
  
  
  /**
   * Causes all die values to be changed from fixed to available. 
   * Has no effect if the number of rolls has reached the maximum.
   */
  public void freeAll()
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(maxRolls == rollCounter) 
		  {
			  return;
		  }
		  
		  currentHand.get(i).setAvailable(true);
		  
	  }
	  
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this hand.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete()
  {
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(currentHand.get(i).isAvailable() == true)
		  {
			  return false;
		  }
		  
	  }
	  return true;
  }

  /**
   * Returns an array containing just the fixed dice in this hand, sorted
   * according to <code>DieComparator</code>.
   * @return
   *   array of the fixed dice
   */
  public Die[] getFixedDice()
  {
	  ArrayList <Die> tempFixedHand = new ArrayList <Die>();
	  
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(currentHand.get(i).isAvailable() == false)
		  {
			  tempFixedHand.add(currentHand.get(i));
		  }
	  }
	  Die [] sortedFixedDice = (Die[]) tempFixedHand.toArray();
	  Arrays.sort(sortedFixedDice, c);

	 return sortedFixedDice;
  }

  /**
   * Returns an array containing just the available dice in this hand, sorted
   * according to <code>DieComparator</code>.
   * @return
   *   array of the available dice
   */
  public Die[] getAvailableDice()
  {
	  ArrayList <Die> tempAvailableHand = new ArrayList <Die>();
	  
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  if(currentHand.get(i).isAvailable() == true)
		  {
			  tempAvailableHand.add(currentHand.get(i));
		  }
	  }
	  Die [] sortedAvailableDice = (Die[]) tempAvailableHand.toArray();
	  Arrays.sort(sortedAvailableDice, c);

	 return sortedAvailableDice;
  }
  
 
  /**
   * Returns all die values in this hand, in ascending order.
   * @return
   *   all die values in this hand
   */
  public int[] getAllValues()
  {
	  int [] allValues = new int[currentHand.size()];
	  
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  allValues[i] = currentHand.get(i).value();
	  }
	  
	  Arrays.sort(allValues);
	  return allValues;
	  
  }
  
  /**
   * Returns an array of all the dice in this hand, sorted
   * according to <code>DieComparator</code>
   * @return
   *  array of all dice 
   */
  public Die[] getAllDice()
  {
	  ArrayList <Die> tempAlldice = new ArrayList <Die>();
	  
	  for(int i = 0; i < currentHand.size(); i++)
	  {
		  
			  tempAlldice.add(currentHand.get(i));
		  
	  }
	  Die [] sortedAllDice = (Die[]) tempAlldice.toArray();
	  Arrays.sort(sortedAllDice, c);

	 return sortedAllDice;
  }
    
}
