package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * 
 * @author owen
 *  3 x 3 bounding square with the icons down the center in the
 *  order (0, 1), (1, 1), and (2, 1)
 */
public class IPiece extends AbstractPiece
{

	private Icon[] i;
	
	/**
	 * Constructs a IPiece in its initial form
	 * @param position
	 * given position
	 * @param icons
	 * given icons
	 * @throws IllegalArgumentException
	 */
	public IPiece(Position position, Icon[] icons) throws IllegalArgumentException
	{
		super(position, icons);
		
		i = icons;

		
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(i[0], new Position(0,1));
		cells[1] = new Cell(i[1], new Position(1,1));
		cells[2] = new Cell(i[2], new Position(2,1));
		setCells(cells);
	}
	

	/**
	 * Does nothing
	 */
	@Override
	public void transform() 
	{

	

	}

}
	
	
