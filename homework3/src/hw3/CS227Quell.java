package hw3;
import static api.CellState.MOVABLE_NEG;
import static api.CellState.MOVABLE_POS;
import static api.CellState.PEARL;
import static api.CellState.PORTAL;

import java.util.ArrayList;

import api.Cell;
import api.CellState;
import api.Descriptor;
import api.Direction;
import api.StringUtil;

/**
 * Basic game state and operations for a simplified version of the video game 
 * "Quell".
 */
public class CS227Quell
{
  /**
   * Two-dimensional array of Cell objects representing the 
   * grid on which the game is played.
   */
  private Cell[][] grid;
  
  /**
   * Instance of GameSupport to be used in the move() algorithm.
   */
  private GameSupport support;

  
  /**
   * Constructs a game from the given string description.  The conventions
   * for representing cell states as characters can be found in 
   * <code>StringUtil</code>.  
   * @param init
   *   string array describing initial cell states
   * @param support
   *   GameSupport instance to use in the <code>move</code> method
   */
  public CS227Quell(String[] init, GameSupport support)
  {
    grid = StringUtil.createFromStringArray(init);
    this.support = support;

    // TODO - any other initialization you need
  }
  
  /**
   * Returns the number of columns in the grid.
   * @return
   *   width of the grid
   */
  public int getColumns()
  {
    return grid[0].length;
  }
  
  /**
   * Returns the number of rows in the grid.
   * @return
   *   height of the grid
   */
  public int getRows()
  {
    return grid.length;
  }
  
  /**
   * Returns the cell at the given row and column.
   * @param row
   *   row index for the cell
   * @param col
   *   column index for the cell
   * @return
   *   cell at given row and column
   */
  public Cell getCell(int row, int col)
  {
    return grid[row][col];
  }
  
  /**
   * Returns true if the game is over, false otherwise.  The game ends when all pearls
   * are removed from the grid or when the player lands on a cell with spikes.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {	  
	  for(int i = 0; i < grid.length ; i++)
	  {
		  for(int j = 0; j < grid[0].length; j++)
		  {
			  if ((CellState.isSpikes(grid[i][j].getState()) && grid[i][j].isPlayerPresent()) || countPearls() == 0)
			  {
				  return true;
			  }
		  }
	  }
	return false;
	  
  }
  
  /**
   * Returns true if the game is over and 
   * the player did not die on spikes.

   * @return
   * true if player wins, false otherwise
   */
  public boolean won()
  {
	  for(int i = 0; i < grid.length ; i++)
	  {
		  for(int j = 0; j < grid[0].length; j++)
		  {
			  if (isOver() && (!(CellState.isSpikes(grid[i][j].getState()) && grid[i][j].isPlayerPresent())))
			  {
				  return true;
			  }
		  }
	  }

	return false;
	  
  }
  
  /**
   * Returns the current number of moves made in this game.
   * @return
   * number of moves made

   */
  public int getMoves()
  {
	  
  }
  
  /**
   * Returns the current score (number of pearls disappeared) for this game.
   * @return
   * current score
   */
  public int getScore()
  {
	  
  }
  
  /**
   * Performs a move along a cell sequence in the given direction, updating the score, 
   * the move count, and all affected cells in the grid. The method returns an array of 
   * Descriptor objects representing the cells in original cell sequence before 
   * modification, with their movedTo and disappeared status set to 
   * indicate the cells' new locations after modification.
   * @param dir
   * dir - direction of the move
   * @return
   * array of Descriptor objects describing modified cells
   */
  public Descriptor[] move(Direction dir)
  {
	  
  }
  
  /**
   * Returns the number of pearls left in the grid.
   * @return
   * number of pearls in the grid
   */
  public int countPearls()
  {
	  int pearlCounter = 0;
	  for(int i = 0; i < grid.length ; i++)
	  {
		  for(int j = 0; j < grid[0].length; j++)
		  {
			  if (grid[i][j].getState() == CellState.PEARL)
			  {
				  pearlCounter++;
			  }
		  }
	  }
	  return pearlCounter;
	  
  }
  /**
   * Finds a valid cell sequence in the given direction starting with the player's 
   * current position and ending with a boundary cell as defined by the method CellState.isBoundary. 
   * The actual cell locations are obtained by following getNextRow and getNextColumn in the given 
   * direction, and the sequence ends when a boundary cell is found. A boundary cell is defined by 
   * the CellState.isBoundary and is different depending on whether a movable block has been encountered 
   * so far in the cell sequence (the player can move through open gates and portals, but the movable blocks cannot). 
   * It can be assumed that there will eventually be a boundary cell (i.e., the grid has no infinite loops). 
   * The first element of the returned array is the cell containing the player, and the last 
   * element of the array is the boundary cell. This method does not 
   * modify the grid or any aspect of the game state.

   * @param dir
   * dir - direction of the sequence

   * @return
   * array of cells in the cell sequence

   */
  public Cell[] getCellSequence(Direction dir)
  {
	  ArrayList <Cell> cells1 = new ArrayList<Cell>();
	  int column = getCurrentColumn();
	  int row = getCurrentRow();
	  cells1.add(grid[row][column]);
	  Cell[] cells2;
	  while(!CellState.isBoundary(grid[row][column].getState(), false))
	  {
		  int tempRow = getNextRow(row,column,dir,false);
		  int tempColumn = getNextColumn(getCurrentRow(),column,dir,false);
		  row = tempRow;
		  column = tempColumn;
		  cells1.add(grid[row][column]);
	  }
	  if (cells1.get(cells1.size() - 1).getState() == CellState.WALL)
	  {
		  if(cells1.get(cells1.size() - 2).getState() == CellState.PORTAL || cells1.get(cells1.size() - 2).getState() == CellState.CLOSED_GATE || cells1.get(cells1.size() - 2).getState() == CellState.OPEN_GATE)
		  {
			  cells1.remove(cells1.size() - 1);
		  }
	  }
	  
	  cells2 = new Cell[cells1.size()];
	  cells1.toArray(cells2);
	  return cells2;
  }

  /**
   * Sets the given cell sequence and updates the player position. This method effectively 
   * retraces the steps for creating a cell sequence in the given direction, starting with 
   * the player's current position, and updates the grid with the new cells. Exactly one cell 
   * in the given sequence must have the condition isPlayerPresent true. The given cell sequence 
   * can be assumed to be structurally consistent with the existing grid, e.g., no portal or wall cells are moved.
   * @param cells
   * cells - updated cells to replace existing ones in the sequence
   * @param dir
   * dir - direction of the cell sequence
   */
  public void setCellSequence(Cell[] cells, Direction dir)
  {
	  int column = getCurrentColumn();
	  int row = getCurrentRow();
	  int i = 0;
	  
	  if (dir == Direction.DOWN || dir == Direction.UP)
	  {
		  int nextRow = getNextRow(row, column, dir, false);
		  
		  while (nextRow != getCurrentRow() && !CellState.isBoundary(grid[row][column].getState(), false) && i < cells.length)
		  {
			  grid[row][column] = cells[i];
			  row = nextRow;
			  i++;
			  
		  }
	  }
	  else if(dir == Direction.LEFT || dir == Direction.RIGHT)
	  {
		  int nextColumn = getNextColumn(row, column, dir, false);
		  while (nextColumn != getCurrentColumn() && !CellState.isBoundary(grid[row][column].getState(), false) && i < cells.length)
		  {
			  grid[row][column] = cells[i];
			  column = nextColumn;
			  i++;
		  }
	  }
  }
  
  public int getNextRow(int row,int col,Direction dir,boolean doPortalJump)
  {
	  int nextRow = row;
	  
	  if (dir == Direction.UP)
	  {
		  if (doPortalJump == true)
		  {
			  nextRow = grid[row - 1][col].getRowOffset() + row - 1;
			  
		  }
		  else if(row == 0)
		  {
			  nextRow = getRows() - 1;
			  
		  }
		  else
		  {
			  nextRow = row - 1;
		  }
	  }
	  
	  else if (dir == Direction.DOWN)
	  {
		  if (doPortalJump == true)
		  {
			  nextRow = grid[row + 1][col].getRowOffset() + row + 1;
		  }
		  else if (row == getRows() - 1)
		  {
			  nextRow = 0;
		  }
		  else
		  {
			  nextRow = row + 1;
		  }
	  }
	  
	  else if (dir == Direction.LEFT)
	  {
		  if (doPortalJump == true)
		  {
			  nextRow = grid[row][col - 1].getRowOffset() + row;
			 
			
		  }
		  else
		  {
			  nextRow = row;
		  }
	  }
	  else if ( dir == Direction.RIGHT)
	  {
		  if (doPortalJump == true)
		  {
			  nextRow = grid[row][col + 1].getRowOffset() + row;
			  
		  }
		  else
		  {
			  nextRow = row;
		  }
	  }
	  return nextRow;
		  
	  
  }
  
  public int getNextColumn(int row,int col,Direction dir,boolean doPortalJump)
  {
	  int nextColumn = row;
	  
	  if (dir == Direction.LEFT)
	  {
		  if (doPortalJump == true)
		  {
			  nextColumn = grid[row][col - 1].getColumnOffset() + col - 1;
			  
		  }
		  else if (col == 0)
		  {
			  nextColumn = getColumns() - 1;
		  }
		  else
		  {
			  nextColumn = col - 1;
		  }
	  }
	  
	  else if (dir == Direction.RIGHT)
	  {
		  if (doPortalJump == true)
		  {
			  nextColumn = grid[row][col + 1].getColumnOffset() + col + 1;
			  
		  }
		  else if (col == getColumns() - 1)
		  {
			  nextColumn = 0;
			  
		  }
		  else
		  {
			  nextColumn = col + 1;
		  }
	  }
	  
	  else if (dir == Direction.UP)
	  {
		  if (doPortalJump == true)
		  {
			  nextColumn = grid[row - 1][col].getColumnOffset() + col;
			  
		  }
		  else 
		  {
			  nextColumn = col;

		  }
	  }
	  
	  else if (dir == Direction.DOWN)
	  {
		  if (doPortalJump == true)
		  {
			  nextColumn = grid[row + 1][col].getColumnOffset() + col;
			  
		  }
		  else
		  {
			  nextColumn = col;
		  }
	  }
	  return nextColumn;
	  
  }
  
 /**
  * Returns the row index for the player's current location.

  * @return
  * current row index for the player

  */
  public int getCurrentRow()
  {
	  int currentRow = 0;
	  for(int i = 0; i < grid.length ; i++)
	  {
		  for(int j = 0; j < grid[0].length; j++)
		  {
			  if (grid[i][j].isPlayerPresent())
			  {
				  currentRow = i;
			  }
		  }
	  }
	  return currentRow;
	  
  }
  
  /**
   * Returns the column index for the player's current location.

   * @return
   * current column index for the player

   */
  public int getCurrentColumn() 
  {
	  int currentCol = 0;
	  for(int i = 0; i < grid.length ; i++)
	  {
		  for(int j = 0; j < grid[0].length; j++)
		  {
			  if (grid[i][j].isPlayerPresent())
			  {
				  currentCol = j;
			  }
		  }
	  }
	  return currentCol;
	  
  }


 
  /**
   * Performs a move along a cell sequence in the given direction, updating the score, 
   * the move count, and all affected cells in the grid.  The method returns an 
   * array of Descriptor objects representing the cells in original cell sequence before 
   * modification, with their <code>movedTo</code> and <code>disappeared</code>
   * status set to indicate the cells' new locations after modification.  
   * @param dir
   *   direction of the move
   * @return
   *   array of Descriptor objects describing modified cells
   */
  public Descriptor[] move(Direction dir)
  {
    // TODO
    return null;
  }
  
  // TODO - everything else
  public static void main(String[] args) 
  {
	  GameSupport support = new GameSupport();
	  String test = "$.@.o+@+-+.o";
	  Cell[] cells = StringUtil.createFromString(test);
	  StringUtil.printCellArray(cells);
	  support.shiftMovableBlocks(cells, null);
	  support.shiftPlayer(cells, null, Direction.DOWN);
	  StringUtil.printCellArray(cells);
	  
	  System.out.println("\n\n\n");
	  
	  GameSupport support1 = new GameSupport();
	  String test1 = "$.@...o+@+-+.o";
	  Cell[] cells1 = StringUtil.createFromString(test1);
	  StringUtil.printCellArray(cells1);
	  support1.shiftMovableBlocks(cells1, null);
	  support1.shiftPlayer(cells1, null, Direction.DOWN);
	  StringUtil.printCellArray(cells1);
	  
	  System.out.println("\n\n\n");

	  GameSupport support2 = new GameSupport();
	  String test2 = "$.@...o-+@+-+.^";
	  Cell[] cells2 = StringUtil.createFromString(test2);
	  StringUtil.printCellArray(cells2);
	  support2.shiftMovableBlocks(cells2, null);
	  support2.shiftPlayer(cells2, null, Direction.DOWN);
	  StringUtil.printCellArray(cells2);
	  
	  System.out.println("\n\n\n");

	  GameSupport support3 = new GameSupport();
	  String test3 = "$.@...o--+@+-+.^";
	  Cell[] cells3 = StringUtil.createFromString(test3);
	  StringUtil.printCellArray(cells3);
	  support3.shiftMovableBlocks(cells3, null);
	  support3.shiftPlayer(cells3, null, Direction.DOWN);
	  StringUtil.printCellArray(cells3);	  
	  
	  System.out.println("\n\n\n");

	  GameSupport support4 = new GameSupport();
	  String test4 = "$.++--.+--+@+-+.^";
	  Cell[] cells4 = StringUtil.createFromString(test4);
	  StringUtil.printCellArray(cells4);
	  support4.shiftMovableBlocks(cells4, null);
	  support4.shiftPlayer(cells4, null, Direction.DOWN);
	  StringUtil.printCellArray(cells4);
  }
}
