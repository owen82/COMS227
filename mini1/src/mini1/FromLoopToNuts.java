package mini1;

public class FromLoopToNuts {

	private FromLoopToNuts() {
		// TODO Auto-generated constructor stub
	}
	
	public static int countMatches(java.lang.String s,
            java.lang.String t)
	{
		int matches = 0;

		for (int i = 0; i < Math.min(s.length(), t.length()); i++) 
		{
			if (s.charAt(i) == t.charAt(i)) 
			{
				matches++;
			}
		}

		return matches;		
	}
	
	public static int countSubstrings(java.lang.String t,
            java.lang.String s)
	{
	        int count = 0;
	        for (int i = 0; i < s.length(); i++) 
	        {
	            if (s.substring(i).startsWith(t)) 
	            {
	                count++;
	                i += t.length()-1;
	            }
	        }
	        return count;
	    
	}

	public static int countSubstringsWithOverlap(java.lang.String t,
            java.lang.String s)
	{
		int count = 0;
	    for (int i = 0; i < s.length(); i++) 
	    {
	        if (s.substring(i).startsWith(t)) 
	        {

	            count++;
	        }
	    }
	    return count;	
	}
	
	public static boolean differByOneSwap(java.lang.String s,
            java.lang.String t)
	{
		int counter = 0;
		for (int i = 0; i < Math.min(s.length(), t.length()); i++) 
		{
			if (s.charAt(i) != t.charAt(i)) 
			{
				counter++;
			}
			
		}
		if (s.length() != t.length())
		{
			counter = counter + (Math.max(s.length(), t.length()) - Math.min(s.length(), t.length()));

		}
		if (counter == 2)
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}
	
	public static java.lang.String eliminateRuns(java.lang.String s)
	{
		  StringBuilder sb = new StringBuilder();
	       if (s.isEmpty())
	           return s;

	       sb.append(s.charAt(0));
	       for (int i = 1; i < s.length(); i++) 
	       {

	           if (s.charAt(i - 1) != s.charAt(i))
	               sb.append(s.charAt(i));

	       }
	       return sb.toString();		
	}
	
	public static int findEscapeCount(double x,
            double y,
            int maxIterations)
	{
		double a = 0;
		double b = 0;

		double tempA;
		double tempB;

		int maxItr = maxIterations;

		int count = 0;

		for (int i = 0; i < maxItr; i++) 
		{
			tempA = a * a - b * b + x;
			tempB = 2 * a * b + y;
			a = tempA;
			b = tempB;
			count = i + 1;

			if ((a * a + b * b) > 4) 
			{
				count = i + 1;
				break;
			}
			
		}

		return count;
	}
	
	public static boolean isArithmetic(java.lang.String text)
	{
		String line = text;
		String[] parts = line.split(",");
		int[] ints = new int[parts.length];
		if(line.equals(""))
		{
			return true;
		}
		for (int i = 0; i < parts.length; i++) 
		{
			 try 
			 {
				 ints[i] = Integer.parseInt(parts[i]);
			 } 
			 catch (Exception e) 
			 {
				 return false;
			 }
		}
		
		if(parts.length > 1)
		{
			int key = Math.max(ints[0], ints[1]) - Math.min(ints[0], ints[1]);
			for(int i = 2; i < ints.length; i++)
			{
				if(ints[i] != ints[i-1] + key)
				{
					return false;
				}
			}	
		
		return true;
		}
		else if(parts.length == 0)
		{
			return false;
		}
		
		return true;
		
	}
	
	public static int threeInARow(java.util.Random rand,
            int bound)
	{
	        int n1 = rand.nextInt(bound), n2 = rand.nextInt(bound), n, count = 2;
	        while (true) 
	        {
	            n = rand.nextInt(bound);
	            count++;
	            if (n == n1 && n == n2) 
	            {
	                return count;
	            }
	            n1 = n2;
	            n2 = n;
	        }
	    

	    
	
	}
}
