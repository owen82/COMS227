package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordsCount {
	public static void main(String[] args) throws FileNotFoundException {
	File file = new File("../project8/story.txt");
	wordsPerLine(file);
	}
	
private static void wordsPerLine (File file) throws FileNotFoundException {
	

	    Scanner scanner = new Scanner(file);
	    
	    while (scanner.hasNextLine())
	    {
	      String line = scanner.nextLine();
	      
	      wordCounter(line);
	    }
	    
	    scanner.close();
}

	  

	private static void wordCounter(String line) {
		Scanner temp = new Scanner(line);
		int counter = 0;

		while (temp.hasNext()) {
			temp.next();
			counter++;

		}
		System.out.println(counter);
		temp.close();
	}

}