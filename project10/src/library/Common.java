package library;

import java.util.Date;

public abstract class Common implements Item
{
	private Patron checkedOutTo;
	private Date dueDate;
	private String title;
	


	public Common(String givenTitle, Date givenDueDate, Patron givenCheckOut)
	{
		title = givenTitle;
		dueDate = givenDueDate;
		checkedOutTo = givenCheckOut;

	}


	public Patron getPatron()
	{
	    return checkedOutTo;
	}
	
	public Date getDueDate()
	  {
	    return dueDate;
	  }
	
	  public boolean isOverdue(Date now)
	  {
	    if (!isCheckedOut())
	    {
	      return false;
	    }
	    return now.after(dueDate);
	  }

	  public int compareTo(Item other)
	  {
	    return title.compareTo(other.getTitle());
	  }

	  public String getTitle()
	  {
	   return title;
	  }

	  public boolean isCheckedOut()
	  {
	    return dueDate != null;
	  }
	  
	  public abstract void checkIn();
	  

}
