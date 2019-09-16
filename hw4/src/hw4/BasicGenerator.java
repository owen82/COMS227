
package hw4;

import java.util.Random;

import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;
import examples.SamplePiece;

/**
 * @author Dongho Kim
 * Generator for Piece objects in BlockAddiction. Icons are 
 * always selected uniformly at random, and the Piece types
 * are generated with the following probabilities:
 * <ul>
 * <li>LPiece - 10%
 * <li>DiagonalPiece - 25%
 * <li>CornerPiece - 15%
 * <li>SnakePiece - 10%
 * <li>IPiece - 40%
 * </ul>
 * The initial position of each piece is based on its
 * vertical size as well as the width of the grid (given
 * as an argument to getNext).  The initial column is always
 * width/2 - 1.  The initial row is:
 *  * <ul>
 * <li>LPiece - row = -2
 * <li>DiagonalPiece - row = -1
 * <li>CornerPiece - row = -1
 * <li>SnakePiece - row = -1
 * <li>IPiece - row = -2
 * </ul>
 * 
 */
public class BasicGenerator implements Generator
{
  private Random rand;

  /**
   * Constructs a BasicGenerator that will use the given
   * Random object as its source of randomness.
   * @param givenRandom
   *   instance of Random to use
   */
  public BasicGenerator(Random givenRandom)
  {
    rand = givenRandom;
  }

  
  /**
   * Returns a new Piece instance according to this generator's 
   * strategy.
   * @param width
   *   the width the game grid
   * @return 
   *   a new Piece 
   */
  @Override
  public Piece getNext(int width)
  {
    int col = width / 2 - 1;
    
    // TODO: change this to return LPiece, IPiece, etc, with the given
    // probabilities
   Piece a = null;
   int probability = rand.nextInt(99)+1;
   if(probability <= 10)
   {
	   Icon [] temp = new Icon[4];
	   for(int i = 0; i < 4; i++)
	   {
		   temp[i] = randomIcon();
	   }
	   a = new LPiece(new Position(-2,col), temp);
   }
   else if(probability <= 35)
   {
	   Icon [] temp = new Icon[2];
	   for(int i = 0; i < 2; i++)
	   {
		   temp[i] = randomIcon();
	   }
	   a = new DiagonalPiece(new Position(-1,col), temp);
   }
   else if(probability <= 50)
   {
	   Icon [] temp = new Icon[3];
	   for(int i = 0; i < 3; i++)
	   {
		   temp[i] = randomIcon();
	   }
	   a = new CornerPiece(new Position(-1,col), temp);
   }
   else if(probability <= 60)
   {
	   Icon [] temp = new Icon[4];
	   for(int i = 0; i < 4; i++)
	   {
		   temp[i] = randomIcon();
	   }
	   a = new SnakePiece(new Position(-1,col), temp);
   }
   else if(probability <= 100)
   {
	   Icon [] temp = new Icon[3];
	   for(int i = 0; i < 3; i++)
	   {
		   temp[i] = randomIcon();
	   }
	   a = new IPiece(new Position(-2,col), temp);
   }
   return a;
    
  }

  /**
   * Returns one of the possible Icon types for this generator,
   * selected at random.
   * @return
   *   randomly selected Icon
   */
  @Override
  public Icon randomIcon()
  {
    return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);
  }

}
