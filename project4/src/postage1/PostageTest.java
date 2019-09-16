package postage1;

import java.util.Scanner;


public class PostageTest 
{
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter a number: ");
	    double first = scanner.nextDouble();
	    System.out.println(PostageUtil.computePostage(first));

	}

}
