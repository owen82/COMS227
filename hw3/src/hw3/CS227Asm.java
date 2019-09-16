package hw3;
/**
 * @author Dongho Kim
 */
import java.util.ArrayList;
import java.util.Scanner;

import api.Instruction;
import api.NVPair;
import api.SymbolTable;

public class CS227Asm {
	/**
	 * Instance variable for constructor parameter
	 */
	private ArrayList<String> programInfoArray = new ArrayList<String>();
	
	/**
	 * SymbolTable set of data
	 */
	private SymbolTable dataset = new SymbolTable();
	
	/**
	 * SymbolTable set of labels
	 */
	private SymbolTable labelset = new SymbolTable();
	
	/**
	 * Instruction stream arraylist
	 */
	private ArrayList<Instruction> instructionset = new ArrayList<Instruction>();
	
	/**
	 * Final machineCode arraylist
	 */
	private ArrayList<String> machineCode = new ArrayList<String>();



	public CS227Asm() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs an assembler for the given assembly language program, 
	 * given as an ArrayList of strings (one per line of the program). 
	 * Initially both symbol tables and the instruction stream are empty.
	 * @param program - the assembly language program to be translated
	 */
	public CS227Asm(java.util.ArrayList<java.lang.String> program)
	{
		programInfoArray = program;
	}

	/**
	 * Returns the symbol table for data (variables).
	 * @return - the symbol table for data
	 */
	public SymbolTable getData()
	{
		return dataset;
	}

	/**
	 * Returns the symbol table for labels (jump targets).
	 * @return - the symbol table for labels
	 */
	public SymbolTable getLabels()
	{
		return labelset;
		
	}
	
	/**
	 * Returns the instruction stream. The index of each instruction 
	 * in the list is its memory location in the generated code.
	 * @return - list of machine instructions
	 */
	public java.util.ArrayList<Instruction> getInstructionStream()
	{
		return instructionset;
		
	}
	
	/**
	 * Assembles the source program represented by this assembler instance 
	 * and returns the generated machine code and data as an array of strings.
	 * @return - list of strings containing the machine code and data for 
	 * the program represented by this assembler
	 */
	public java.util.ArrayList<java.lang.String> assemble()
	{
		parseData();
		parseLabels();
		parseInstructions();
		setOperandValues();
		addLabelAnnotations();
		return writeCode();
		
	}
	
	/**
	 * Creates the symbol table for the data section of this assembler's program.
	 */
	public void parseData()
	{
		for(int i = 0; i < programInfoArray.size(); i++)
		{
			if(programInfoArray.get(i).equals("data:"))
			{	
				for(int j = i+1; j < programInfoArray.size(); j++) 	//loop through given program array
				{
					if(programInfoArray.get(j).equals("labels:"))
					{
						break;	//break if "labels:" comes after "data:" because its empty
					}
					else
					{
						String parsedData = programInfoArray.get(j);
						Scanner s = new Scanner(parsedData);
						dataset.add(s.next(), s.nextInt());
					}
				}
			}
		}
	}
	
	/**
	 * Creates the symbol table for the label section of this assembler's program, 
	 * leaving all label values as zeros.
	 */
	public void parseLabels()
	{
		for(int i = 0; i < programInfoArray.size(); i++)	//loop through given program array
		{
			if(programInfoArray.get(i).equals("labels:"))
			{	
				for(int j = i+1; j < programInfoArray.size(); j++)
				{
					if(programInfoArray.get(j).equals("instructions:"))
					{
						break;
					}
					else
					{
						labelset.add(programInfoArray.get(j).trim().toString());	//get rid of whitespaces
					}				
				}
			}
		}
		
	}
	
	/**
	 * Creates instruction stream from the instruction section of this assembler's 
	 * program and fills in label addresses in the symbol table for labels.
	 */
	public void parseInstructions()
	{
		int counter = 0;
		for(int i = 0; i < programInfoArray.size(); i++)
		{
			if(programInfoArray.get(i).equals("instructions:")) //loop through program arraylist until "instructions:" is found
			{	
				for(int j = i+1; j < programInfoArray.size(); j++)
				{
					if(programInfoArray.get(j).equals("halt"))		//loop through program arraylist until "halt"
					{
						instructionset.add(new Instruction(programInfoArray.get(j))); //add halt and stop
						break;
					}
					else if(!(programInfoArray.get(j).equals("halt"))) 
					{
						if(!(labelset.containsName(programInfoArray.get(j).trim().toString()))) //if it is not a label, add to instruction stream and increment counter
						{
							instructionset.add(new Instruction(programInfoArray.get(j).trim().toString()));
							counter++;

						}
						else if(labelset.containsName(programInfoArray.get(j).trim().toString())) //if it is a label, add to labelset and set its value as current counter
						{
							labelset.findByName(programInfoArray.get(j).trim().toString()).setValue(counter);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Fills in the correct operand value for all instructions 
	 * (either a data address or jump target address, depending on the instruction).
	 */
	public void setOperandValues()
	{
		for(int i = 0; i < instructionset.size(); i++)
		{
			if(instructionset.get(i).requiresJumpTarget())
			{
				String label = instructionset.get(i).getOperandString();

				for(int j = 0; j < labelset.size(); j++)
				{
					if(labelset.getByIndex(j).getName().equals(label))
					{
						instructionset.get(i).setOperand(labelset.getByIndex(j).getValue());
					}
				}
			}
			else if(instructionset.get(i).requiresDataAddress())
			{
				String label = instructionset.get(i).getOperandString();

				for(int k = 0; k < dataset.size(); k++)
				{
					if(dataset.getByIndex(k).getName().equals(label))
					{
						instructionset.get(i).setOperand(instructionset.size() + k);
					}
				}
				
			}

		}
		
	}
	
	/**
	 * For each instruction in the instruction stream that is a jump target, 
	 * adds the label to the instruction's description.
	 * (See the method addLabelToDescription in the Instruction class.)
	 */
	public void addLabelAnnotations()
	{
		for(int k = 0; k < labelset.size(); k++)
		{
			instructionset.get(labelset.getByIndex(k).getValue()).addLabelToDescription(labelset.getByIndex(k).getName());
		}
	}
	
	/**
	 * Generates the machine code and data for this assembler's program, 
	 * terminated by the string "-99999". Strings for instructions are produced 
	 * by the Instruction method toString, and strings for data have the value 
	 * formatted as a four-digit signed integer, followed by a space, followed by the variable name.
	 * @return - list of strings containing the machine code and data for this assembler's program
	 */
	public java.util.ArrayList<java.lang.String> writeCode()
	{
		for(int i = 0; i < instructionset.size(); i++)
		{
			machineCode.add(instructionset.get(i).toString());
		}
		for(int j = 0; j < dataset.size(); j++)
		{

			machineCode.add(String.format("%+05d", dataset.getByIndex(j).getValue()) 
					+ " " + dataset.getByIndex(j).getName());
		}
		
		machineCode.add("-99999");
		return machineCode;
		
	}
}
