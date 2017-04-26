package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import linda.*;

public class LindaReadTest {
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
	public void testReadTuple() {
		linda.write(tuple);
		assertEquals(linda.read(tuple),tuple);
	}

	@Test
	public void testReadMotif1() {
		linda.write(tuple);
		assertEquals(linda.read(motif), tuple);
	}
	
	@Test
	public void testReadMotif2() {
		linda.write(motif);
		assertEquals(linda.read(motif), motif);
	}
	
	@Test
	public void testReadVide() {
		linda.write(tupleVide);
		assertEquals(linda.read(tupleVide), tupleVide);
	}
	
	@Test
	public void testReadDontRemove() {
		linda.write(tuple);
		linda.read(tuple);
		assertNotNull(linda.tryRead(tuple));
	}
	
	@Test
	public void testReadBlock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                    linda.read(new Tuple(motif));
    	                } catch (Exception e) {
    	                    ;
    	                }
    	            }
    	        };
            th.start();
            Thread.sleep(200);
            assertEquals(th.getState(),Thread.State.WAITING);
	}

	@Test
	public void testReadUnblock() throws InterruptedException {
        Thread th = 
    	        new Thread() {
    	            public void run() {
    	                try {
    	                	linda.read(motif);
    	                    //assertTrue(linda.read(new Tuple(motif)).equals(tuple));
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
	public void testReadNullThrowsException() {
		linda.read(null);
	}
	
	@Test
	public void testReadNullDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.read(null);
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
