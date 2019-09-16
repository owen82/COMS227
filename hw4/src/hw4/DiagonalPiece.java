package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * 
 * @author Dongho Kim
 * 2 x 2 bounding square and initial cell 
 * positions (0, 0), (1, 0),and (1, 1)
 */
public class DiagonalPiece extends AbstractPiece
{

	private Icon[] i;
	
	/**
	 * Constructs a diagonal piece in its initial form
	 * @param position
	 * given positions
	 * @param icons
	 * given icons
	 * @throws IllegalArgumentException
	 */
	public DiagonalPiece(Position position, Icon[] icons) throws IllegalArgumentException
	{
		super(position, icons);
		
		i = icons;

		Cell[] cells = new Cell[2];
		cells[0] = new Cell(i[0], new Position(0,0));
		cells[1] = new Cell(i[1], new Position(1,1));
		setCells(cells);
	}
	


	/**
	   * Transforms this piece without altering its position
	   * according to the rules of the game to be implemented.  
	   * Typical operations would be rotation or reflection. 
	   * No bounds checking is done.
	   * 
	   * flips the cells across the vertical centerline,
	   */    
	@Override
	public void transform() 
	{
		Cell[] cellCopy = getCells();

		if(cellCopy[0].getRow() == 0 && cellCopy[0].getCol() == 0)
		{			
			Cell[] cells = new Cell[2];
			cells[0] = new Cell(i[0], new Position(0,1));
			cells[1] = new Cell(i[1], new Position(1,0));
			setCells(cells);
		}
		else if(cellCopy[0].getRow() == 0 && cellCopy[0].getCol() == 1)
		{			
			Cell[] cells = new Cell[2];
			cells[0] = new Cell(i[0], new Position(0,0));
			cells[1] = new Cell(i[1], new Position(1,1));
			setCells(cells);
		}
		
	

	}


}