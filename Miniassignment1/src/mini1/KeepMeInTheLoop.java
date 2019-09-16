package mini1;
import java.util.Scanner;

public class KeepMeInTheLoop 
{
	/**
	 * Private constructor disables instantiation.
	 */
	 private KeepMeInTheLoop()
	 {
		 
	 }

		public static int findNth(java.lang.String s, char ch, int n) 
		{
			int count = 0;
			int index = 0;
			for (int i = 0; i < s.length(); i += 1 )
			{
				if (ch == s.charAt(i))
				{
					count += 1;
				}
				if (count == n) 
				{
					index = i;
					break;
				}
			}
			if (count < n || n < 0)
			{
				return -1;	 
			}
			return index;
		}
		
		public static String doubleConsonants(String s) 
		{
			String result = "";
			for (int i = 0; i < s.length(); i++) 
			{
				char ch = s.charAt(i);
				result += ch;
				if ("aeiouAEIOU".indexOf(ch) == -1) 
				{
					if (!s.substring(Math.max(0, i - 1), Math.min(s.length(), i + 2)).contains(ch + "" + ch)) 
					{
						result += ch;
					}

				}
			}
			return result;
		}
		
		
		public static int findStoppingTime(int n)
		{
			int count = 0;
			if (n <= 0)
			{
				return -1;
			}
			else
			{
				while (n != 1)
					if (n % 2 == 0)
					{
						n = n / 2;
						count += 1;
					}
					else
					{
						n = n * 3 + 1;
						count += 1;
					}
				
			}
			return count;
			
		}
		
		public static int howLong(double balance, double monthlyCost, double interestRate, double increase) 
		{
			int numMonths = 0;
			while (balance >= monthlyCost) 
			{
				balance -= monthlyCost;
				balance *= 1 + (interestRate / 12);
				monthlyCost += increase;
				numMonths++;
			}
			return numMonths;
		}
		
		
	    public static boolean isIBeforeE(String s) 
	    {
	        for (int i = 0; i < s.length(); i++) 
	        {
	            char previous = s.charAt(Math.max(0, i - 1));
	            char current = s.charAt(i);
	            char next = s.charAt(Math.min(s.length() - 1, i + 1));
	            if (current == 'e' && next == 'i' && previous != 'c')
	            {
	                return false;
	            }
	             else if (current == 'i' && next == 'e' && previous == 'c') 
	            {
	                return false;
	            }
	        }
	        return true;
	    }
	    
	    public static int findSecondLargest(String nums) 
	    {
			Scanner scanner = new Scanner(nums);
			
			int largest = Integer.MIN_VALUE;
			int secondLargest = Integer.MIN_VALUE;
			
			while (scanner.hasNext()) 
			{
				int current = scanner.nextInt();
				if (current > largest) 
				{
					secondLargest = largest;
					largest = current;
				} 
				else if (current > secondLargest) 
				{
					secondLargest = current;
				}
			}

			return secondLargest;
		}
	    
	    public static boolean isPermutation(String s, String t) 
	    {
			StringBuilder sb = new StringBuilder(t);
			
			for (int i = 0; i < s.length(); i++) 
			{
				char ch = s.charAt(i);
				if (sb.indexOf(ch + "") >= 0) 
				{
					sb.deleteCharAt(sb.indexOf(ch + ""));
				}
			}

			return sb.length() == 0;
		}
	    
	    public static boolean containsWithGaps(String source, String target) 
	    {
			if (target == "" ) 
			{
				return true;
			}
			
			int targetIndex = 0;
			for (int i = 0; i < source.length(); i++) 
			{
				if (target.length() > targetIndex) 
				{
					if (source.charAt(i) == target.charAt(targetIndex)) 
					{
						targetIndex++;
						if (targetIndex == target.length()) 
						{
							return true;
						}
					}
				}
			}
			return false;
		}




		
		
}
