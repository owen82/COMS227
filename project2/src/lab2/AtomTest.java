package lab2;

public class AtomTest 
{

	public static void main(String[] args) 
	{
		Atom a;
		a = new Atom(92,146,92);
		System.out.println(a.getAtomicMass());
		System.out.println(a.getAtomicCharge());
		
		a.decay();
		System.out.println(a.getAtomicMass());
		System.out.println(a.getAtomicCharge());		

	}

}
