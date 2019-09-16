package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * 
 * @author Dongho Kim
 * 3 x 3 bounding square and initial cell 
 * positions (0, 0),(1, 0), (1, 1), and (1, 2)
 */
public class SnakePiece extends AbstractPiece
{

	private Icon[] i;
	
	/**
	 * Counter to keep track of position sequence
	 */
	private int counter = 0;

	/**
	 * Constructs a SnakePiece in its initial form
	 * @param position
	 * given position
	 * @param icons
	 * given icons
	 * @throws IllegalArgumentException
	 */
	public SnakePiece(Position position, Icon[] icons) throws IllegalArgumentException
	{
		super(position, icons);
		
		i = icons;

		
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(i[0], new Position(0,0));
		cells[1] = new Cell(i[1], new Position(1,0));
		cells[2] = new Cell(i[2], new Position(1,1));
		cells[3] = new Cell(i[3], new Position(1,2));

		setCells(cells);
		
	}
	
	/**
	 * Sequence of positions for SnakePiece transform
	 */
	 private static final Position[] sequence =
	{
		 new Position(0, 0),
		 new Position(0, 1),
		 new Position(0, 2),
		 new Position(1, 2),
		 new Position(1, 1),
		 new Position(1, 0),
		 new Position(2, 0),
		 new Position(2, 1),
		 new Position(2, 2),
		 new Position(1, 2),
		 new Position(1, 1),
		 new Position(1, 0),
	};

	 
	 /**
	   * Transforms this piece without altering its position
	   * according to the rules of the game to be implemented.  
	   * Typical operations would be rotation or reflection. 
	   * No bounds checking is done.
	   * 
	   *  transitions through twelve different states and back to 
	   *  the original, following a snake-like pattern
	   */  
	@Override
	public void transform() 
	{
		Cell[] cells = new Cell[4];

		
		for(int j = 0; j < cells.length; j++)
		{
			// if the value for sequence index is negative, add 12 to make it positive and locate the correct index
			if(counter - j < 0)
			{
				cells[j] = new Cell(i[j], sequence[counter - j + 12]);
			}
			else
			{
				cells[j] = new Cell(i[j], sequence[counter - j]);
			}
		}
		//increments counter every time it transforms
		counter ++;
		//set counter back to 0 after 12th transform
		if(counter == 12)
		{
			counter = 0;
		}
		setCells(cells);
		
	}

}
