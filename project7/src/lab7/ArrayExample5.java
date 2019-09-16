package lab7;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class ArrayExample5 {
	
	public static void main(String[] args)
	{
		System.out.println(Arrays.toString(randomExperiment(10,1000)));
	 
	}

	public static int[] randomExperiment(int max, int iters)
	{

		Random randomNumbers = new Random();
		int nums = 0;
		int count[] = new int[max];
		for(int i = 0; i < iters; i++)
		{
			nums = randomNumbers.nextInt(max);			
			count[nums]++;
			

		}
		return count;

	}

}
