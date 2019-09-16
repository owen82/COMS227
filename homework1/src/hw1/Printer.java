package hw1;

public class Printer 
{
	private double currentink;
	private int sheetsprinted;
	private int capacity;
	private int numofsheets;
	/**
	* Capacity, in ounces, of a new ink cartridge. */
	public static final double INK_CAPACITY = 2.0;
	/**
	* Amount of ink, in ounces, used per printed page. */
	
	public static final double INK_USAGE = 0.0023;
	/**
	 * creates a printer with the given capacity
	 * @param givenCapacity
	 */
	public Printer(int givenCapacity)
	{
		currentink = INK_CAPACITY;
		capacity = givenCapacity;
		
		
	}
	/**
	 * creates a printer with given capacity and number of sheets
	 * @param givenCapacity
	 * @param givenNumberOfSheets
	 */
	public Printer(int givenCapacity, int givenNumberOfSheets)
	{
		currentink = INK_CAPACITY;
		numofsheets = givenNumberOfSheets;
		capacity = numofsheets;
		capacity = givenCapacity;
		
		
		if (numofsheets > capacity)
		{
			numofsheets = capacity;
		}
	}
	/**
	 * refilling a printer with given number of additional papers
	 * @param additionalSheets
	 */
	public void addPaper(int additionalSheets)
	{
		numofsheets += additionalSheets;
		if (numofsheets > capacity)
		{
			numofsheets = capacity;
		}
	}
	/**
	 * getting amount of paper that is inside the printer
	 * @return
	 */
	public int getCurrentPaper()
	{
		return numofsheets;
	}
	/**
	 * getting amount of paper that was printed
	 * @return
	 */
	public int getTotalPaperUse()
	{
		return sheetsprinted;
	}
	/**
	 * if printer ran out of ink
	 * @return
	 */
	public boolean isInkOut()
	{
		return currentink < INK_USAGE;
	}
	/**
	 * refilling ink
	 */
	public void replaceInk()
	{
		currentink = INK_CAPACITY;
	}
	/**
	 * prints the given number of pages, one sided
	 * @param numberOfPages
	 */
	public void print(int numberOfPages)
	{		
		if (numofsheets < numberOfPages)
		{
			numberOfPages = numofsheets;
		}
		currentink -= INK_USAGE * numberOfPages;
		numofsheets -= numberOfPages;
		sheetsprinted += numberOfPages;
	}
	/**
	 * prints the given number of pages, twosided
	 * @param numberOfPages
	 */
	public void printTwoSided(int numberOfPages)
	{
		double exactNumOfPages = numberOfPages / 2.0;
		numberOfPages = (int) Math.ceil(exactNumOfPages);
		if (numofsheets < numberOfPages)
		{
			numberOfPages = numofsheets;
		}
		currentink -= INK_USAGE * numberOfPages;
		numofsheets -= numberOfPages;
		sheetsprinted += numberOfPages;
	}
	
	
}
