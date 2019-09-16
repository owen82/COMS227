package lab8;

import java.awt.Point;
import java.util.ArrayList;
import plotter.Plotter;
import plotter.Polyline;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;



public class PolylinePlotter 
{
	public static ArrayList<Polyline> abc = new ArrayList<Polyline>();

	private static ArrayList<Polyline> readFile(String filename)throws FileNotFoundException
	{
		File file = new File(filename);   

		Scanner scanner = new Scanner(file);


		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			line = line.trim();
			
			if(!line.isEmpty() && !line.startsWith("#"))
			{
				Scanner temp = new Scanner(line);
				String first = temp.next();
				boolean isStringNumber = first.matches("[0-9]+");
				String last = "";
				int n = 0;
				
				if(isStringNumber)
				{
					n = Integer.parseInt(first);
					last = temp.next();
				}
				else
				{
					last = first;
				}
				
		System.out.println(first+" "+last);
		Polyline p1 = new Polyline(last,n);


		double total = 0.0;

		while (temp.hasNextInt())
		{
			int value = temp.nextInt();
			int value2=0;
			if(temp.hasNextInt())
				value2 = temp.nextInt();
				p1.addPoint(new Point(value, value2));
		}
		abc.add(p1);

			}
		}

	  scanner.close();

	  return abc;

	}

	  public static void main(String args[]) throws FileNotFoundException
	  {

	  List<Polyline> arrayListvals = readFile("hello.txt");
	  Plotter p = new Plotter();
	  for(Polyline p2:abc)
	  {
		  p.plot(p2);

	  }

	  }

	  }