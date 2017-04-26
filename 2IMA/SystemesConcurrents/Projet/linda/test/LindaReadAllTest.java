package test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import linda.Linda;
import linda.Tuple;

public class LindaReadAllTest {
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
		//linda = new tshm.CentralizedLinda();
		// linda = new shm.CentralizedLinda();	
        linda = new shm.server.LindaClient("//localhost:4000/Linda");
	}
	
	@After
	public void cleanUp() {
        linda.takeAll(motif);
        linda.takeAll(tupleVide);
	}

	@Test(timeout=1000)
	public void testReadAllTuple() {
		linda.write(tuple);
		Collection<Tuple> result = linda.readAll(tuple);
		assertTrue(result.contains(tuple));
		assertEquals(result.size(),1);
	}

	@Test(timeout=1000)
	public void testReadAll2Tuple() {
		linda.write(tuple);
		linda.write(tuple);
		Collection<Tuple> result = linda.readAll(tuple);
		for(Tuple t : result) {
			assertEquals(t, tuple);
		}
		assertEquals(result.size(),2);
	}
	
	@Test(timeout=1000)
	public void testReadAllMotif1() {
		linda.write(tuple);
		Collection<Tuple> result = linda.readAll(motif);
		assertTrue(result.contains(tuple));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testReadAllMotif2() {
		linda.write(motif);
		Collection<Tuple> result = linda.readAll(motif);
		assertTrue(result.contains(motif));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testReadMotifAllMixte() {
		linda.write(tuple);
		linda.write(motif);
		Collection<Tuple> result = linda.readAll(motif);
		assertTrue(result.contains(tuple));
		assertTrue(result.contains(motif));
		assertEquals(result.size(),2);
	}
	
	@Test(timeout=1000)
	public void testReadAllVide() {
		linda.write(tupleVide);
		Collection<Tuple> result = linda.readAll(tupleVide);
		assertTrue(result.contains(tupleVide));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testReadAllReturnsEmpty() {
		linda.write(motif);
		assertTrue(linda.readAll(tuple).size()==0);
	}
	
	@Test(timeout=1000)
	public void testReadAllDontRemove() {
		linda.write(tuple);
		linda.readAll(tuple);
		assertNotNull(linda.tryRead(tuple));
	}
	
	@Test(timeout=1000)
	public void testReadAllDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.readAll(new Tuple(motif));
	                } catch (Exception e) {
	                    ;
	                }
	            }
	        };
        th.start();
        Thread.sleep(100);
        assertEquals(th.getState(),Thread.State.TERMINATED);
	}
	
	@Test(timeout=1000, expected=NullPointerException.class)
	public void testReadAllNullThrowsException() {
		linda.readAll(null);
	}
}
