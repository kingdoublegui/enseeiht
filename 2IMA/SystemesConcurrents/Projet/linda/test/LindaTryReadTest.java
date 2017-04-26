package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import linda.*;

public class LindaTryReadTest {
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
	public void testTryReadTuple() {
		linda.write(tuple);
		assertEquals(linda.tryRead(tuple),tuple);
	}

	@Test
	public void testTryReadMotif1() {
		linda.write(tuple);
		assertEquals(linda.tryRead(motif), tuple);
	}
	
	@Test
	public void testTryReadMotif2() {
		linda.write(motif);
		assertEquals(linda.tryRead(motif), motif);
	}
	
	@Test
	public void testTryReadVide() {
		linda.write(tupleVide);
		assertEquals(linda.tryRead(tupleVide), tupleVide);
	}
	
	@Test
	public void testTryReadReturnsNull() {
		linda.write(motif);
		assertNull(linda.tryRead(tupleVide));
	}
	
	@Test
	public void testTryReadDontRemove() {
		linda.write(tuple);
		linda.tryRead(tuple);
		assertNotNull(linda.tryRead(tuple));
	}
	
	@Test
	public void testTryReadDontBlock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                    linda.tryRead(new Tuple(motif));
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
	public void testTryReadNullThrowsException() {
		linda.tryRead(null);
	}
	
	@Test
	public void testTryReadNullDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.tryRead(null);
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
