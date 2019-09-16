package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DuplicateRemover 
{

	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<String> words2 = new ArrayList<String>();
		words2.add("owen");
		words2.add("hailey");
		words2.add("owen");
		words2.add("hailey");
		words2.add("rebecca");
		words2.add("rebecca");
		words2.add("gabe");
		words2.add("richard");
		words2.add("gabe");
		words2.add("richard");

		removeDuplicates(words2);
		
		
	}
	
    public static void removeDuplicates(ArrayList words)
    {
    	for (int i = 0; i < words.size(); i++)
    	{	
        	for (int j = i + 1; j < words.size(); j++)
    		{
    			if(words.get(i).equals(words.get(j)))
    			{
    				words.remove(words.get(j));
    				i = 0;
    				j = 0;
    			}
    			
    		}
    	}
		System.out.println(words);

    	
    	
    }


}
