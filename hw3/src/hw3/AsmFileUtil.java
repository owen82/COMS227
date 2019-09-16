package hw3;
/**
 * @author Dongho Kim
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AsmFileUtil {

	
	public AsmFileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Reads the given file and assembles the program, returning 
	 * the machine code as a list of strings (including descriptions).
	 * @param filename
	 * @return - assembled machine code as a list of strings
	 * @throws java.io.FileNotFoundException
	 */
	public static java.util.ArrayList<java.lang.String> 
	assembleFromFile(java.lang.String filename) 
			throws java.io.FileNotFoundException
	{
		ArrayList<String> assembledArr = new ArrayList<String>();
		File file = new File(filename);
		Scanner s = new Scanner(file);
		CS227Asm asm = new CS227Asm(assembledArr);
		
		while(s.hasNextLine())
		{
			String s1 = s.nextLine();
			assembledArr.add(s1);
		}
		
		return asm.assemble();
		
	}
	
	/**
	 * Reads the given file and assembles the program, returning the machine 
	 * code as an array of integer values (not including the sentinel value).
	 * @param filename
	 * @return - assembled machine code as an array of integer values
	 * @throws java.io.FileNotFoundException
	 */
	public static int[] createMemoryImageFromFile(java.lang.String filename)
            throws java.io.FileNotFoundException
	{
		ArrayList<String> machineCodes = new ArrayList<String>();
		machineCodes = assembleFromFile(filename);
		int[] intMemory = new int[machineCodes.size()];
		
		for(int i = 0; i < machineCodes.size(); i++)
		{
			String line = machineCodes.get(i);
			Scanner s = new Scanner(line);
			intMemory[i] = Integer.parseInt(s.next());
			
		}
		
		
		return intMemory;
	}
	
	/**
	 * Reads the given file and assembles the program, writing the machine 
	 * code to a file. Descriptions are included only if the annotated parameter 
	 * is true, otherwise each line in the output file includes just the first 
	 * five characters (representing the integer value of the instruction). 
	 * The name of the output file is the same as the name of the input file, with 
	 * the file extension (portion after the last dot) removed and is replaced 
	 * with ".mach227". For example, given the filename "test1.asm227", the output 
	 * file would be named "test1.mach227".
	 * @param filename
	 * @param annotated
	 * @throws java.io.FileNotFoundException
	 */
	public static void assembleAndWriteFile(java.lang.String filename,
            boolean annotated)
     throws java.io.FileNotFoundException
     {
		File file = new File(filename);
		String name = filename.substring(0, filename.indexOf("."));
		PrintWriter pw = new PrintWriter(name +  ".mach227");

		if(annotated)
		{
			ArrayList<String> machineCodes = new ArrayList<String>();
			machineCodes = assembleFromFile(filename);
			
			for(int i = 0; i < machineCodes.size(); i++)
			{
				String line = machineCodes.get(i);
				pw.println(line);
			}
			pw.close();
			
			
		}
		else if(!(annotated))
		{
			ArrayList<String> assembledArr = new ArrayList<String>();
			assembledArr = assembleFromFile(filename);
			
			for(int i = 0; i < assembledArr.size(); i++)
			{
				String line = assembledArr.get(i);
				Scanner s = new Scanner(line);
				pw.println(s.next());
				
			}
			pw.close();
			
			
			
		}
     }

}
