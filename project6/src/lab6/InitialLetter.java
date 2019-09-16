package lab6;
import java.util.Scanner;

public class InitialLetter 
{

	public static void main(String[] args) 
	{
		String result = initialLetter("Cher dfsa Osldkf Kdn s");
		System.out.println(result);
	}
	
	public static String initialLetter(String name) 
	{
		String result = "";
		Scanner in = new Scanner(name);
		result += name.charAt(0);

		for (int i = 0; i < name.length(); i += 1)
		{
			char c = name.charAt(i);
			
			if (c == ' ')
			{
				result += name.charAt(i+1);
			}
		}
		return result;
	}

}
