package mini2;
import java.lang.Object;
import java.lang.reflect.Array;
import java.util.Arrays;


public class HipHipArray 
{

	private HipHipArray() 
	{
		
	}
	
	public static int[] makeHistogram(double[] data, int numBins, double min, double max)

    {
		double binSize = (max-min)/numBins;
		
		int arr[] = new int[numBins];
		
		for(int i=0;i<arr.length;i++)
			arr[i] = 0;
		
		for(int i=0;i<data.length;i++)
		{
			if(data[i] >= min && data[i] <max)
			{
				int index = (int)((data[i] - min) / binSize);
				arr[index]++;
			}
		}
		return arr; 
    }
	
	public static boolean isPermutation(int[] arr)
	{
		int[] temp = new int[arr.length];
		for(int i=0; i < arr.length; i++)
		{
			temp[i] = arr[i];
		}
		Arrays.sort(temp);
		int n = arr.length;
		
		for (int i = 0; i < n; i++)
		{
			if(temp[i] == i)
			{
				;
			}
			else
			{
				return false;
			}
		}
		
		
		
		return true;
	}
	
	public static int[] createPalindrome(int[] arr)
	{
		int n = arr.length;
		int counter = 0;
		int counter1 = 0;
		int []empty = new int [0];

		
		if (n==0)
		{

			return empty;
		}
		else
		{
			int [] palindrome = new int [2*n -1];

			
			for (int i=0; i < n ; i++)
			{
			palindrome[i] = arr[i];
			counter++;
			}
		
			for (int i = n-2; i >= 0; i-- )
			{
			palindrome[counter + counter1] = arr[i];
			counter1++;

			}
			
		return palindrome;
		}
		
	}
	
	public static boolean[] makeSieve(int size, int[] divisors)
	{
		 boolean arr[] = new boolean[size];
		 for(int i = 1; i< size;i++)
			 arr[i] = true;
		 
		 for (int k = 0; k < divisors.length; k ++) 
		 {
	       for(int p = 2; p * divisors[k] < size; p++) 
	       {
	           arr[divisors[k] * p] = false;
	       }

	}
		 return arr;
		
	}
	
	public static void shift(int[] arr, int amount)
	{
		if(amount > 0)
		{
			if(amount >= arr.length)
			{
				for(int i = 0;i<arr.length;i++)
				{
					arr[i] = 0;
				}
			}
			      else 
			      {
			    	  int k = 0;
			    	  for(int i = amount;i<arr.length;i++)
			        {
			          arr[i] = arr[k++];
			        }
			        for(int i = 0;i<amount;i++)
			        {
			          arr[i]=0;
			        }
			      }
			    }
			    else{
			      amount = amount * -1;
			      if(amount >= arr.length)
			      {
			        for(int i = 0;i<arr.length;i++)
			        {
			          arr[i] = 0;
			        }
			      }
			      else {
			        int k = 0;
			        for(int i = amount;i<arr.length;i++)
			        {
			          arr[k++] = arr[i];
			        }
			        for(int i = k;i<arr.length;i++)
			        {
			          arr[i]=0;
			        }
			      }
			    }
			  
	}
	
	public static void rotate(int[] arr,int amount)
	{
		int[] copy = new int[arr.length];
		for(int i = 0; i < arr.length; ++i) 
		{
			copy[i] = arr[i];
		}
		amount %= arr.length;
		for(int i = 0; i < arr.length; ++i) 
		{
			arr[i] = copy[(i - amount + arr.length) % arr.length];
		}
	}
		
	
	
	public static java.lang.String[] removeDups(java.lang.String[] arr)
	{
		int count = 0;
        boolean found;
        for(int i = 0; i < arr.length; ++i) 
        {
            found = false;
            for(int j = 0; j < i; ++j) 
            {
                if(arr[i].equals(arr[j])) 
                {
                    found = true;
                }
            }
            if(!found) 
            {
                ++count;
            }
        }
        java.lang.String[] result = new java.lang.String[count];
        int index = 0;
        for(int i = 0; i < arr.length; ++i) 
        {
            found = false;
            for(int j = 0; j < i; ++j) 
            {
                if(arr[i].equals(arr[j])) 
                {
                    found = true;
                }
            }
            if(!found) {
                result[index++] = arr[i];
            }
        }
        return result;
    }
		
	
	public static int[] longestRun(int[] arr)
	{
		int index = 0, maxIndex = 0, length = 0, maxLength = 0;
		for(int i = 0; i < arr.length; ++i) 
		{
        if(i == 0) 
        {
            index = 0;
            length = 1;
        } else {
            if(arr[i] >= arr[i-1]) 
            {
                length++;
            } else {
                if(length > maxLength) 
                {
                    maxIndex = index;
                    maxLength = length;
                }
                length = 1;
                index = i;
            }
        }
    }
    if(length > maxLength) 
    {
        maxIndex = index;
        maxLength = length;
    }
    int[] result = new int[maxLength];
    for(int i = 0; i < maxLength; ++i) 
    {
        result[i] = arr[maxIndex+i];
    }
    return result;

	
	}


}
