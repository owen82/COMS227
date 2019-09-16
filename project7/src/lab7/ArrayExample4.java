package lab7;
import java.util.Arrays;

public class ArrayExample4 {
	
	public static void main(String[] args)
	{
	    int[] randomNumbers = { 3,5,-2347,9,12,-1,-23,-234 };
	    int[] result = getPositiveNumbers(randomNumbers);
	    System.out.println(Arrays.toString(result));
	}

	public static int[] getPositiveNumbers(int[] numbers)
	{
		int counter = 0;
		for(int i=0; i < numbers.length; i++)
		{
			int temp = numbers[i];
			if (temp > 0)
			{
			counter++;
			}
			

			
		}
		
		int[] positiveNumbers = new int[counter];
		int index = 0;

		
		for(int i = 0;  i < numbers.length; i++)
		{
			int temp = numbers[i];

			if (temp > 0)
			{
				positiveNumbers[index] = temp;
				index += 1;
			}
		}
		return positiveNumbers;
	}
}
