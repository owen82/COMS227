package lab7;

import java.util.Scanner;
public class array4
{
  public static void main(String [] args)
  {
    int [] num = new int[15]; int [] positives = new int [15];
    getInput(num);
    positives = getPositive(num);
    printResults(num, positives);
  }
  public static void getInput(int [] num){
    int x;
    Scanner kbd = new Scanner(System.in);
    System.out.println("Enter 15 integers");
    for(x = 0; x < 15; x++)
num[x] = kbd.nextInt();

}
  public static int[] getPositive(int [] num)
  {
	  int position = 0;  
	  int [] positive = new int [15];
	  
	  for(int i =0; i < num.length; i++){  

	    if(num[i] >=0) {
	      positive[position] = num[i];  
	      position++;  }}
	  return positive;
  }


public static void printResults(int [] num, int [] positives){
System.out.println("You entered"); int x;
for(x = 0; x < num.length; x++)
System.out.println(num[x]);
System.out.println("Your positive integers are ");
for(x = 0; x < positives.length; x++)
System.out.println(positives[x]);}}