package mini2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import speccheck.Specified;

public class CS227Comp {
  
  /**
   * Opcode for the read instruction.
   */
  public static final int READ      = 10;

  /**
   * Opcode for the write instruction.
   */
  public static final int WRITE     = 20;

  /**
   * Opcode for the load instruction.
   */
  public static final int LOAD      = 30;

  /**
   * Opcode for the store instruction.
   */
  public static final int STORE     = 40;

  /**
   * Opcode for the add instruction.
   */
  public static final int ADD       = 50;
  
  /**
   * Opcode for the sub instruction.
   */  
  public static final int SUB       = 51;
  
  /**
   * Opcode for the div instruction.
   */  
  public static final int DIV       = 52;
  
  /**
   * Opcode for the mod instruction.
   */
  public static final int MOD       = 53;
  
  /**
   * Opcode for the mul instruction.
   */
  public static final int MUL       = 54;

  /**
   * Opcode for the jump instruction.
   */
  public static final int JUMP      = 60;
  
  /**
   * Opcode for the jumpn (jump if negative) instruction.
   */
  public static final int JUMPN     = 61;
  
  /**
   * Opcode for the jumpz (jump if zero) instruction.
   */
  
  public static final int JUMPZ     = 63;

  /**
   * Opcode for the halt instruction.
   */
  public static final int HALT      = 80;
  
  private int memSize;
  
  private int IC;
  
  private int AC;
  
  private int IR;
  
  private int[] memory1;
  
  private int opcode;
  private int operand;
  
  private boolean isHalted;
  
  

 
  /**
   * Constructs a machine with the given number of words of memory, all words
   * zero, all registers zero, in a halted state.
   * @param memorySize
   *   number of words of memory for this machine
   */
  public CS227Comp(int memorySize)
  {
	  memory1 = new int[memorySize];
	  isHalted = true;
  }

  /**
   * Constructs a machine with the given initial values in the instruction
   * counter and accumulator, and the given memory contents.  The memory
   * size will be that of the given array.
   * @param initialIC
   *   initial value for the instruction counter
   * @param initialAC
   *   initial value for the accumulator
   * @param memoryImage
   *   initial memory contents
   */
  public CS227Comp(int initialIC, int initialAC, int[] memoryImage)
  {
	  IC = initialIC;
	  AC = initialAC;
	  memory1 = memoryImage;
  }
  
  /**
   * Returns the current value in the accumulator.
   * @return
   *   current value in the accumulator
   */
  public int getAC()
  {
    return AC;
  }
  
  /**
   * Returns the current value of the instruction counter.
   * @return
   *   current value of the instruction counter
   */
  public int getIC()
  {
    return IC;
  }
  
  /**
   * Returns the contents of the instruction register (most recently executed
   * instruction)
   * @return
   *   contents of the instruction register
   */
  public int getIR()
  {
    return IR;
  }
  
  /**
   * Returns the opcode for the most recently executed instruction
   * (instruction register divided by 100).
   * @return
   *   opcode for the most recently executed instruction
   */
  public int getOpcode()
  { 
    return opcode;
  }
  
  /**
   * Returns the operand for the most recently executed instruction
   * (instruction register modulo 100).
   * @return
   *   operand for the most recently executed instruction
   */
  public int getOperand()
  {
    return operand;
  }
  
  /**
   * Returns true if the machine is in a halted state, false otherwise.
   * @return
   *   true if the machine is in a halted state, false otherwise
   */
  public boolean isHalted()
  {
    return isHalted;
  }
  
  /**
   * Returns the contents of the memory cell at the given address.
   * @param address
   *   memory address
   * @return
   *   contents of memory cell at the given address
   */
  public int peekMemory(int address)
  {
    return memory1[address];
  }

  /**
   * Returns the number of words of memory this machine has.
   * @return
   *   the number of words of memory
   */
  public int getMemorySize()
  {
    return memory1.length;
  }
  
  /**
   * Executes one instruction at a time, over and over, as long as the
   * machine is not halted.
   */
  public void runProgram()
  {
	  while(!isHalted)
	  {
		  nextInstruction();
	  }
  }
  
  /**
   * Displays complete machine state.  This one is done for you.  Observe the
   * conversions that are used to print the values, as you'll need them
   * elsewhere if you want a uniform look to your output.
   */
  public void dumpCore()
  {
    System.out.printf("REGISTERS:\n");
    System.out.printf("%-20s %+05d\n", "accumulator", getAC());
    System.out.printf("%-20s    %02d\n", "instruction counter",
                      getIC());
    System.out.printf("%-20s %+05d\n", "instruction register",
                      getIR());
    System.out.printf("%-20s    %02d\n", "operation code", getIR() / 100);
    System.out.printf("%-20s    %02d\n", "operand", getIR() % 100);
    System.out.printf("\nMEMORY:\n  ");
    for (int i = 0; i < 10; i++) {
      System.out.printf("%6d", i);
    }
    int row = 0;
    for (int i = 0; i < getMemorySize(); i++)
    {
      if (i % 10 == 0)
      {
        row += 1;
        System.out.printf("\n%2d ", row * 10);
      }
      System.out.printf("%+05d ", peekMemory(i));        
    }
    System.out.println();
  }


  /**
   * Loads the given values into the machine's memory.  If the length of the
   * given array is smaller than this machine's memory size, the remaining
   * cells are filled with zeros; if larger, extra values are ignored.  If
   * any value is encountered in the given array that is not between -9999
   * and 9999, the machine crashes at that point with message "*** Invalid
   * input ***".  The machine's memory size is not modified.  If no invalid
   * inputs are encountered, the machine will be in a non-halted state.
   * @param image
   *   memory image to load
   */
  public void loadMemoryImage(int[] image)
  {
	  if(memory1.length > image.length)
	  {
		  for(int i = 0; i < memory1.length; i++)
		  {
			  memory1[i] = 0;
		  }
		  for(int j = 0; j < image.length; j++)
		  {
			  memory1[j] = image[j];
		  } 
	  }
	  else if(memory1.length < image.length)
	  {
		  for(int k = 0; k < memory1.length; k++)
		  {
			  memory1[k] = image[k];
		  } 
	  }
	  
	  
  }
  
  /**
   * Reads input from the terminal, one value per line, until the sentinel
   * value (-99999) is read. Inputs are stored in successive memory locations
   * starting with address (index) 0. Inputs are decimal integers in the range
   * [-9999,9999].  Any invalid input should immediately crash the machine
   * with error message "*** Invalid input ***".  Each input should be
   * prompted for with the zero-padded, two digit sequential memory index
   * followed by a question mark. (E.g., the first prompt would be 00?, the 
   * second prompt would be 01?, and so on.)  The input (or sentinel) should
   * then be printed out to the console as a four digit (or five, for the sentinel)
   * number, preceded by a "+" or "-", and padded with zeros if needed to make
   * it four digits.  If no invalid inputs are encountered, displays the
   * message "*** Program Loaded ***" and leaves the machine in a non-halted state.
   */
  public void loadProgramFromConsole()
  {
  }

  /**
   * Reads input from the given file.  Input is then loaded
   * into memory according to the specification for loadMemoryImage.
   * @param filename
   *   file from which to read instructions
   */
  public void loadProgramFromFile(String filename) throws FileNotFoundException
  {
	  File file = new File(filename);   

	  Scanner s = new Scanner(file);
	  
	  ArrayList<Integer> memoryFromFile = new ArrayList<Integer>();
	  while(s.next() == "-99999")
	  {
		  memoryFromFile.add(s.nextInt());

	  }
  }
  
  /**
   * Loads the next instruction from memory, parses it for the opcode and
   * operand, and executes the instruction. The "next instruction" is the
   * value in memory whose address (index) is currently in the instruction counter.
   * The opcode is the high-order two digits of the instruction; the operand 
   * is the low-order two digits. Except in case of a jump, the instruction 
   * counter is incremented by one following execution of the instruction.
   * <p>
   * Invalid opcodes crash the machine.
   * <p>
   * Descriptions of all instructions follow:
   *
   * <ul>
   * <li>READ:
   * Executes the read instruction.  Reads a decimal word from the terminal
   * into the address referenced by operand and updates the instruction
   * counter.  Valid words are in the range [-9999,9999].  Out of range words
   * are truncated on the right until within range before being stored; the
   * truncated portion is discarded.  For example, -723471 will be truncated
   * to -7234.
   *
   * <li>WRITE:
   * Displays the value stored in memory at the address referenced by the
   * operand as a four digit number, padded on the left as necessary to make
   * it exactly four digits, and preceded by a "+" or "-". Updates
   * the instruction counter.
   *
   * <li>LOAD:
   * Loads the value stored in memory at the address referenced by operand
   * into the accumulator and updates the instruction counter.
   *
   * <li>STORE:
   * Stores the value in the accumulator into memory at the address
   * referenced by the operand and updates the instruction counter.
   *
   * <li>ADD:
   * Adds the value stored in memory at the address referenced by operand to
   * the accumulator, accounting for overflow, and updates the instruction
   * counter.
   *
   * <li>SUB:
   * Subtracts the value stored in memory at the address referenced by operand
   * from the accumulator, accounting for overflow, and updates the
   * instruction counter.
   *
   * <li>DIV:
   * Divides the accumulator by the value stored in memory at the address
   * referenced by operand, accounting for overflow, and updates the
   * instruction counter.  All division is integer division.  Division by
   * zero crashes the machine.
   *
   * <li>MOD:
   * Calculates the remainder when dividing the accumulator by the value
   * stored in memory at the address referenced by operand, accounting for
   * overflow, stores the result in the accumulator, and updates the 
   * instruction counter.  All division is integer division.  Division 
   * by zero crashes the machine.
   *
   * <li>MUL:
   * Multiplies the accumulator by the value stored in memory at the address 
   * referenced by operand, accounting for overflow, and updates the
   * instruction counter.
   *
   * <li>JUMP:
   * Changes the instruction counter to operand.
   *
   * <li>JUMPN:
   * If the accumulator is negative, changes the instruction counter to
   * operand, otherwise updates the instruction counter normally.
   *
   * <li>JUMPZ:
   * If the accumulator is zero, changes the instruction counter to operand,
   * otherwise updates the instruction counter normally.
   *
   * <li>HALT:
   * Displays the message "*** Program terminated normally ***", halts the
   * machine, and dumps core.
   * </ul>
   * Arithmetic overflow occurs when the accumulator acquires a value outside
   * of the range [-9999,9999].  It is handled by truncating the value of the
   * accumulator to the low order four digits.
   * <p>
   * Instruction counter overflow occurs when when the value of the
   * instruction counter matches or exceeds the memory size.  It is handled
   * by setting the instruction counter to zero.
   * <p>
   * All crashes dump core.
   */
  public void nextInstruction()
  {
	  opcode = memory1[IC] / 100;
	  operand = memory1[IC] % 100;
	  IR = memory1[IC];
	  if(opcode == 30)
	  {
		  AC = memory1[operand];
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
		  
	  }
	  else if(opcode == ADD)
	  {
		  AC = AC + memory1[operand];
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }

	  }
	  else if(opcode == SUB)
	  {
		  AC = AC - memory1[operand];
		  IC++;

		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == DIV)
	  {
		  if(memory1[operand] == 0)
		  {
			  isHalted = true;
			  dumpCore();
			  IC--;
			  return;
		  }
		  AC = AC / memory1[operand];
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == MOD)
	  {
		  if(operand == 0)
		  {
			  isHalted = true;
			  dumpCore();
			  IC--;
			  return;


		  }
		  AC = AC % memory1[operand];
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == MUL)
	  {
		  AC = AC * memory1[operand];
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == STORE)
	  {
		  memory1[operand] = AC;
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == JUMP)
	  {
		  IC = operand;
	  }
	  else if(opcode == JUMPN)
	  {
		  if(AC < 0)
		  {
			  IC = operand;
			  IC--;
		  }
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == JUMPZ)
	  {
		  if(AC == 0)
		  {
			  IC = operand;
			  IC--;
		  }
		  IC++;
		  if(IC == memory1.length)
		  {
			  IC = 0;
		  }
	  }
	  else if(opcode == WRITE)
	  {
		 String value = Integer.toString(memory1[operand]);
		 
		 while(value.length() < 4)
		 {
			 value = "0" + value;
		 }
		 
		 if(memory1[operand] < 0)
		 {
			 value = "-" + value;
		 }
		 else if(memory1[operand] > 0)
		 {
			 value = "+" + value;
		 }
		 System.out.println(value);
		 IC++;
		 if(IC == memory1.length)
		  {
			  IC = 0;
		  }
		  
	  }
	  else if(opcode == READ)
	  {
		  Scanner s = new Scanner(System.in);
		  int newValue = s.nextInt();
		  
		  while(newValue > 9999 || newValue < -9999)
		  {
			  newValue = newValue / 10;
		  }
		  memory1[operand] = newValue;
		  
		 
	  }
	  else if(opcode == HALT)
	  {
		  System.out.println("*** Program terminated normally***");
		  isHalted = true;
		  dumpCore();
	  }
	  else if(opcode != READ || opcode != WRITE || opcode != LOAD || opcode != STORE || opcode != ADD || opcode != SUB ||
			  opcode != DIV ||
					  opcode != MOD || opcode != MUL || opcode != JUMP || opcode != JUMPN || opcode != JUMPZ || opcode != HALT)
	  {
		  isHalted = true;
		  dumpCore();  
	  }
	  
	  
	  while(AC > 9999 || AC < -9999)
	  {
		  AC = AC % 10000;
	  }
	  
  }
}