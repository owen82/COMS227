package lab2;

public class StringTest
{
	public static void main(String[] args)
	{
		String message;
		message = "Hello, world!";
		System.out.println(message);
		int theLength = message.length();
		System.out.println(theLength);
		System.out.println(message.toUpperCase());
		System.out.println(message.substring(0, 5));
		System.out.println(message.replace("o","*"));;
	}
}
