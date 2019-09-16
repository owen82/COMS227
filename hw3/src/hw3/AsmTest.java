package hw3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import mini2.CS227Comp;

public class AsmTest 
{

	public AsmTest() 
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException
	 {
		AsmFileUtil.assembleAndWriteFile("collatz.asm227",true);		

	 }
}
