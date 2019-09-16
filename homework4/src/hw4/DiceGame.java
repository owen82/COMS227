package hw4;

import java.util.ArrayList;

import hw4.api.Die;
import hw4.api.ScoringCategory;

/**
 * Game state for a dice game such as Yahtzee. The game consists
 * of a list of <code>ScoringCategory</code> objects, each of which is responsible
 * for keeping track of the dice used to satisfy it and of its own 
 * contribution to the total score. Clients interact directly with the
 * category objects, which are obtained using method <code>getCategories()</code>.
 * The total score for the game may be obtained via the <code>getScore</code>
 * method.  This class also keeps track of several game
 * attributes: the number of dice being used in the game, the maximum
 * value (number of "sides") of the dice, and the number of times the
 * dice may be re-rolled in each round.
 * @author owen
 */
public class DiceGame
{
	private ArrayList <ScoringCategory> categories = new ArrayList <ScoringCategory>();

	private int numDice;
	private int maxDieValue;
	private int numRolls;

  
  /**
   * Constructs a new DiceGame based on the given parameters.  
   * Initially the list of categories is empty.
   * @param numDice
   *   number of dice used in this game
   * @param maxDieValue
   *   maximum face value (number of faces) for each die
   * @param numRolls
   *   number of times the dice can be rolled in each round
   */
  public DiceGame(int numDice, int maxDieValue, int numRolls) 
  {
	  this.numDice = numDice;
	  this.numRolls = numRolls;
	  this.maxDieValue = maxDieValue;
  }
  
  /**
   * Adds a scoring category to this game.
   * @param category
   *   scoring category to add
   */
  public void addCategory(ScoringCategory category)
  {
	  categories.add(category);
  }
  
  
  /**
   * Returns the list of categories in this game.
   * @return
   *   list of categories 
   */
  public ArrayList<ScoringCategory> getCategories()
  {
    return categories;
  }
  
  /**
   * Returns a new Hand corresponding to the number of dice,
   * maximum die value, and number of rolls for this game.
   * Initially all dice in the hand are available to be rolled.
   * @return
   *   new Hand based on this game's parameters
   */
  public Hand createNewHand()
  {
	  Hand newHand = new Hand (numDice, maxDieValue, numRolls);
	  
	  return newHand;
  }
  
  /**
   * Returns the current total score for all categories.
   * @return
   *   total score for all categories
   */
  public int getScore()
  {
	  int totalScore = 0;
	  for(int i = 0; i < categories.size(); i++)
	  {
		  totalScore = totalScore + categories.get(i).getScore();
	  }
        return totalScore;
  }

}
