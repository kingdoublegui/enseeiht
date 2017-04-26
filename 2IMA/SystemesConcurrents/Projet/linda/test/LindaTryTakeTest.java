package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import linda.*;

public class LindaTryTakeTest {
	private static Linda linda;
	private static Tuple motif;
	private static Tuple tuple;
	private static Tuple tupleVide;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		motif = new Tuple(Character.class, String.class, Integer.class);
		tuple = new Tuple('a', "toto", 4);
		tupleVide = new Tuple();
	}

	@Before
	public void setUp() {
		// linda = new tshm.CentralizedLinda();
		linda = new shm.CentralizedLinda();	
        // linda = new server.LindaClient("//localhost:4000/aaa");
	}
	
	@Test
	public void testTryTakeTuple() {
		linda.write(tuple);
		assertEquals(linda.tryTake(tuple),tuple);
	}

	@Test
	public void testTryTakeMotif1() {
		linda.write(tuple);
		assertEquals(linda.tryTake(motif), tuple);
	}
	
	@Test
	public void testTryTakeMotif2() {
		linda.write(motif);
		assertEquals(linda.tryTake(motif), motif);
	}
	
	@Test
	public void testTryTakeVide() {
		linda.write(tupleVide);
		assertEquals(linda.tryTake(tupleVide), tupleVide);
	}
	
	@Test
	public void testTryTakeRemove() {
		linda.write(tuple);
		linda.tryTake(tuple);
		assertNull(linda.tryTake(tuple));
	}
	
	@Test
	public void testTryTakeReturnsNull() {
		linda.write(motif);
		assertNull(linda.tryTake(tupleVide));
	}
	
	@Test
	public void testTryTakeDontBlock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                    linda.tryTake(new Tuple(motif));
    	                } catch (Exception e) {
    	                    ;
    	                }
    	            }
    	        };
            th.start();
            Thread.sleep(200);
            assertEquals(th.getState(),Thread.State.TERMINATED);
	}
	
	@Test(timeout=1000, expected=NullPointerException.class)
	public void testTryTakeNullThrowsException() {
		linda.tryTake(null);
	}
	
	@Test
	public void testTryTakeNullDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.tryTake(null);
	                } catch (Exception e) {
	                    ;
	                }
	            }
	        };
        th.start();
        Thread.sleep(100);
        assertEquals(th.getState(), Thread.State.TERMINATED);
	}
}
