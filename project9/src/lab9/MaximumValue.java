package lab9;

public class MaximumValue 
{

	  
	  public static void main(String[] args)
	  {
	    int[] test = {3, 4, 5, 1, 2, 3, 2, 9}; // sum should be 20
	    int result = arrayMax(test);
	    System.out.println(result);
	  }

	  public static int arrayMax(int[] arr)
	  {
	    return arrayMaxVal(arr, 0, arr.length - 1);
	  }
	  
	 
	  private static int arrayMaxVal(int[] arr, int start, int end)
	  {
	    if (start == end)
	    {
	      return arr[start];
	    }
	    else
	    {
	      int mid = (start + end) / 2;
	      int leftSum = arrayMaxVal(arr, start, mid);
	      int rightSum = arrayMaxVal(arr, mid + 1, end);
	      return Math.max(leftSum, rightSum);
	    }
	  }
}