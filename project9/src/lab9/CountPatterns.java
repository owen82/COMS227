package lab9;

public class CountPatterns 
{

  public static void main(String[] args)
  {
    System.out.println(countPatterns(5));
  }
  
  
  public static int countPatterns(int n)
  {
    if (n == 1 || n == 2)
    {
      return 1;
    }
    else if (n == 3)
    {
      return 2;
    }
    else
    {
      return countPatterns(n - 1) + countPatterns(n - 3);
    }
  }
}
	

