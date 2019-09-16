package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import api.AbstractGame;
import api.Generator;
import api.Position;


/**
 * @author Dongho Kim
 * A partial implementation of the Tetris-like falling shape games. 
 */
public class BlockAddiction extends AbstractGame
{
	
	/**
	 * Constructs a grid for Tetris-like game, with given values.
	 * @param height
	 * given height
	 * @param width
	 * given width
	 * @param gen
	 * given generator
	 * @param preFillRows
	 * given number of pre filled rows
	 */
	public BlockAddiction(int height, int width, Generator gen, int preFillRows)	
	{
		super(height, width, gen);
		if(preFillRows > 0 )
		{
			Random rand = new Random();
			BasicGenerator gen1 = new BasicGenerator(rand);
			for(int i = getHeight() - 1; i > getHeight() - 1 - preFillRows; i--)
			{
				for(int k = i % 2; k < getWidth(); k = k + 2)
				{
					setBlock(i,k,gen1.randomIcon());
				}
			}
		}
	}
	/**
	 * Constructs a grid for Tetris-like game, with given values.
	 * @param height
	 * given height
	 * @param width
	 * given width
	 * @param gen
	 * given generator
	 */
	public BlockAddiction(int height, int width, Generator gen)
	{
		super(height, width, gen);
		
	}

	/**
	 * identify the positions of icons in all "collapsible sets", 
	 * that is, sets of three or more adjacent icons with matching color.
	 * @return positions of icons in collapsible sets.
	 */
	 @Override
	  public List<Position> determinePositionsToCollapse() 
	 {
	    List<Position> positions = new ArrayList<>();
	    for (int row = 0; row < getHeight(); ++row)
	    {
	    	
 	        for (int col = 0; col < getWidth(); ++col)
	        {
	        	if(getIcon(row,col) != null)
	        	{
	        		
	        		Position [] list = { new Position(row, col + 1), new Position(row - 1, col),
	        				new Position(row + 1, col), new Position(row, col - 1)};
	        		ArrayList<Position> temp1 = new ArrayList<Position>();
	        		for (int n = 0; n < 4; n++)
	        		{
	        			Position temp = list[n];
	        			if(temp.row() >= 0 && temp.col() >= 0 && temp.row() < getHeight() && 
	        					temp.col() < getWidth()) 
	        			{
	        				if(getIcon(row,col).matches(getIcon(temp.row(), temp.col())))
	        				{
	        					temp1.add(temp);
	        				}
	        			}
	        				
	        		}
	        		if(!(temp1.size() < 2))
	        		{
	        			positions.add(new Position(row,col));
	        			for (int j = 0; j < temp1.size(); j++)
	        			{
	        				positions.add(temp1.get(j));
	        			}
	        			temp1.clear();
	        		}
	        	}
	        }
	      
	    }
	 		Collections.sort(positions);
	 		return positions;
	  	}
	 


}
