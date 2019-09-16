package lab6;

public class FirstVowel {
	
	public static void main(String[] args)
	{
		System.out.println(firstVowel("fs"));

	}

	public static int firstVowel(String s)
	{
		
		for(int i = 0; i < s.length(); i++)
		{
			if("aeiouAEIOU".indexOf(s.charAt(i)) >= 0)
			{
				return i;
			}
		}


	    return -1;
	}

}
