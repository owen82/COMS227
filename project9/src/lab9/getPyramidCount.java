package lab9;

public class getPyramidCount {

	public static void main(String[] args)
	  {
	    int result = getPyramidCount(7);
	    System.out.println(result);
	  }

	public static int getPyramidCount(int outerDigit)
	{
		if(outerDigit == 0)
		{
			return 0;
			
		}
		else
		{
			int layer = getPyramidCount(outerDigit - 1);
			int result = (outerDigit * outerDigit) + (layer);
			return result;
		}
	}

}
