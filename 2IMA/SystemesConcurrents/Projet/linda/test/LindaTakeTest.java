package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import linda.*;

public class LindaTakeTest {
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
		//linda = new shm.CentralizedLinda();	
        linda = new shm.server.LindaClient("//localhost:4000/Linda");
	}
	
	@After
	public void cleanUp() {
        linda.takeAll(motif);
        linda.takeAll(tupleVide);
	}
	
	@Test
	public void testTakeTuple() {
		linda.write(tuple);
		assertEquals(linda.take(tuple),tuple);
	}

	@Test
	public void testTakeMotif1() {
		linda.write(tuple);
		assertEquals(linda.take(motif), tuple);
	}
	
	@Test
	public void testTakeMotif2() {
		linda.write(motif);
		assertEquals(linda.take(motif), motif);
	}
	
	@Test
	public void testTakeVide() {
		linda.write(tupleVide);
		assertEquals(linda.take(tupleVide), tupleVide);
	}
	
	@Test
	public void testTakeRemove() {
		linda.write(tuple);
		linda.take(tuple);
		assertNull(linda.tryRead(tuple));
	}
	
	@Test
	public void testTakeBlock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                    linda.take(new Tuple(motif));
    	                } catch (Exception e) {
    	                    ;
    	                }
    	            }
    	        };
            th.start();
            Thread.sleep(200);
            assertEquals(th.getState(),Thread.State.RUNNABLE);
	}

	@Test
	public void testTakeUnblock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                	linda.take(motif);
    	                    //assertTrue(linda.take(new Tuple(motif)).equals(tuple));
    	                } catch (Exception e) {
    	                    ;
    	                }
    	            }
    	        };
            th.start();
            Thread.sleep(100);
            linda.write(tuple);
            Thread.sleep(100);
            assertEquals(th.getState(), Thread.State.TERMINATED);
	}
	
	@Test(timeout=1000, expected=NullPointerException.class)
	public void testTakeNullThrowsException() {
		linda.take(null);
	}
	
	@Test
	public void testTakeNullDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.take(null);
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
