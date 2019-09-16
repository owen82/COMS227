package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * 
 * @author Dongho Kim
 * 4 x 4 bounding square with the icons at
 *  (0, 0), (0, 1), (1, 1), and (2, 1) initially
 */
public class LPiece extends AbstractPiece
{

	private Icon[] i;

	/**
	 * Constructs a LPiece in its initial form
	 * @param position
	 * given position
	 * @param icons
	 * given icons
	 * @throws IllegalArgumentException
	 */
	public LPiece(Position position, Icon[] icons) throws IllegalArgumentException
	{
		super(position, icons);
		
		i = icons;

		
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(i[0], new Position(0,0));
		cells[1] = new Cell(i[1], new Position(0,1));
		cells[2] = new Cell(i[2], new Position(1,1));
		cells[3] = new Cell(i[3], new Position(2,1));
		setCells(cells);
	}


	/**
	   * Transforms this piece without altering its position
	   * according to the rules of the game to be implemented.  
	   * Typical operations would be rotation or reflection. 
	   * No bounds checking is done.
	   * 
	   *  flips the cells across the vertical centerline.
	   */  
	@Override
	public void transform() 
	{
		
		Cell[] cellCopy = getCells();

		if(cellCopy[0].getRow() == 0 && cellCopy[0].getCol() == 0)
		{			
			Cell[] cells = new Cell[4];
			cells[0] = new Cell(i[0], new Position(0,2));
			cells[1] = new Cell(i[1], new Position(0,1));
			cells[2] = new Cell(i[2], new Position(1,1));
			cells[3] = new Cell(i[3], new Position(2,1));
			setCells(cells);
		}
		else if(cellCopy[0].getRow() == 0 && cellCopy[0].getCol() == 2)
		{
			Cell[] cells = new Cell[4];
			cells[0] = new Cell(i[0], new Position(0,0));
			cells[1] = new Cell(i[1], new Position(0,1));
			cells[2] = new Cell(i[2], new Position(1,1));
			cells[3] = new Cell(i[3], new Position(2,1));
			setCells(cells);
		}
	

	}
	
	

}
