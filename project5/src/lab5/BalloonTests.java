package lab5;
import balloon4.Balloon;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BalloonTests 
{
    private static final double EPSILON = 10e-07;
    
    
    @Test
    public void testInitial()
    {
    	Balloon b = new Balloon(10);
    	assertEquals(false, b.isPopped());
    	assertEquals(0, b.getRadius());
    }
    
    @Test
    public void testDeflate()
    {
    	Balloon b = new Balloon(10);
    	b.blow(5);
    	b.deflate();
    	assertEquals(0, b.getRadius());
    	assertEquals(false, b.isPopped());
    	
    }
    
    @Test
    public void testInflate()
    {
    	Balloon b = new Balloon(10);
    	b.blow(5);
    	assertEquals(5, b.getRadius());
    	
    }
    
    @Test
    public void testMaxradius()
    {
    	Balloon b = new Balloon(10);
    	b.blow(15);
    	assertEquals(true, b.isPopped());
    }
    
    @Test
    public void testNegative()
    {
    	Balloon b = new Balloon(-1);
    	b.blow(1);
    	assertEquals(0, b.getRadius());

    }
    
    @Test
    public void testAlreadypopped()
    {
    	Balloon b = new Balloon(10);
    	b.pop();
    	b.blow(2);
    	assertEquals(0, b.getRadius());
    	
    }

    @Test
    public void testBlowtwice()
    {
    	Balloon b = new Balloon(10);
    	b.blow(4);
    	b.blow(5);
    	assertEquals(false, b.isPopped());
    	assertEquals(9, b.getRadius());

    	
    }


}
