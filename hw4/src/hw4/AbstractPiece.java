
package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/**
 * @author Dongho Kim
 * 
 * Abstract class for the various kinds of block pieces in the game.
 * Each piece has a position, a size, and a set of Cells.  The position is the upper-left 
 * corner of the piece's bounding box, which has a fixed size. The initial position of
 * the bounding box and the relative positions of the cells  
 * are assigned in a constructor, and thereafter the 
 * position can be modified by the shiftXXX() methods. The constructor also
 * establishes a relative ordering of the cells.  The getCells() and 
 * getCellsAbsolute methods always 
 * return the cells in this ordering, and the cycle() method always uses this ordering
 * for shifting the icons. No bounds checking is ever done in implementations of this interface; 
 * therefore, the piece position and the cell positions can have negative coordinates.  
 */



public abstract class AbstractPiece implements Piece
{

	private Cell[] cells;
	private Position p;

	/**
	 * Constructor that retrieves arguments for subclasses.
	 * @param position
	 * given position
	 * @param icons
	 * given icons
	 */
	protected AbstractPiece(Position position, Icon[] icons) 
	{
		p = position;
	}
	
	
	/**
	 * Returns a deep copy of this object having the correct runtime type.
	 * @return
	 * a deep copy of this object
	 */
	@Override
	public Piece clone()
	{
	    try
	    {
	      // call the Object clone() method to create a shallow copy
	      AbstractPiece s = (AbstractPiece) super.clone();

	      // then make it into a deep copy (note there is no need to copy the position,
	      // since Position is immutable, but we have to deep-copy the cell array
	      // by making new Cell objects      
	      s.cells = new Cell[cells.length];
	      for (int i = 0; i < cells.length; ++i)
	      {
	        s.cells[i] = new Cell(cells[i]);
	      }
	      return s;
	    }
	    catch (CloneNotSupportedException e)
	    {
	      // can't happen, since we know the superclass is cloneable
	      return null;
	      
	    }    

	
	}
	
	/**
	 * Sets the cells in this piece, making a deep copy of the given array.
	 * @param givenCells - new cells for this piece
	 */
	@Override	
	public void setCells(Cell[] givenCells)
	{
	    // deep copy the given array
	    cells = new Cell[givenCells.length];
	    for(int i = 0; i < givenCells.length; i++)
	    {
		    cells[i] = new Cell(givenCells[i]);

	    }
	  }
	
	/**
	 * Returns a new array of Cell objects representing the icons in this
	 *  piece with their absolute positions (relative positions 
	 *  plus position of bounding box).
	 *  @return copy of the cells in this piece, with absolute positions
	 */
		@Override
		public Cell[] getCellsAbsolute()
		  {
		    Cell[] ret = new Cell[cells.length];
		    for(int i = 0; i < cells.length; i++)
		    {
		    	int row = cells[i].getRow() + getPosition().row();
			    int col = cells[i].getCol() + getPosition().col();
			    Icon b = cells[i].getIcon();
			    ret[i] = new Cell(b, new Position(row, col));
		    }
		    
		    return ret;
		  }
		
		/**
		 * Returns a deep copy of the Cell objects in this piece. 
		 * The cell positions are relative to the upper-left corner of its bounding box.
		 * @return copy of the cells in this piece
		 */
		@Override
		public Cell[] getCells()
		  {
		    // deep copy this object's cell array
		    Cell[] copy = new Cell[cells.length];
		    for(int i = 0; i < cells.length; i++)
		    {
		    	copy[i] = new Cell(cells[i]);
		    }
		    return copy;
		  }
		
	
		/**
		   * Returns the position of this piece (upper-left corner of its bounding box).
		   * @return
		   *   position of this shape
		   */	
	@Override
	public Position getPosition()
	{
		return p;
	}
	
	
	/**
	   * Cycles the icons within the cells of this piece.  Each 
	   * icon is shifted forward to the next cell (in the original ordering
	   * of the cells).  The last icon wraps around to the first cell.  
	   */
	@Override
	public void cycle()
	{
		Cell[] cellCopy = getCells();
		Cell temp = new Cell(cellCopy[cellCopy.length - 1].getIcon(), null);

		for(int i = cellCopy.length - 1; i >= 1 ; i--)
		{
			cellCopy[i].setIcon(cellCopy[i - 1].getIcon());

		}
		cellCopy[0].setIcon(temp.getIcon());
		setCells(cellCopy);


	}
	
	/**
	   * Shifts the position of this piece down (increasing the row) 
	   * by one.  No bounds checking is done.
	   */
	  @Override
	  public void shiftDown()
	  {
		p = new Position(p.row() + 1, p.col());
	  }
	  
	  /**
	   * Shifts the position of this piece left (decreasing the column) 
	   * by one.  No bounds checking is done.
	   */
	  @Override
	  public void shiftLeft()
	  {
		  p = new Position(p.row(), p.col() - 1);

	  }
	  
	  /**
	   * Shifts the position of this piece right (increasing the column) 
	   * by one.  No bounds checking is done. 
	   */ 
	  @Override
	  public void shiftRight()
	  {
		  p = new Position(p.row(), p.col() + 1);

	  }



}
