package test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import linda.Linda;
import linda.Tuple;

public class LindaTakeAllTest {
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
		//linda = new shm.CentralizedLinda();	
        linda = new shm.server.LindaClient("//localhost:4000/Linda");
	}

	@After
	public void cleanUp() {
        linda.takeAll(motif);
        linda.takeAll(tupleVide);
	}

	@Test(timeout=1000)
	public void testTakeAllTuple() {
		linda.write(tuple);
		Collection<Tuple> result = linda.takeAll(tuple);
		assertTrue(result.contains(tuple));
		assertEquals(result.size(),1);
	}

	@Test(timeout=1000)
	public void testTakeAll2Tuple() {
		linda.write(tuple);
		linda.write(tuple);
		Collection<Tuple> result = linda.takeAll(tuple);
		for(Tuple t : result) {
			assertEquals(t, tuple);
		}
		assertEquals(result.size(),2);
	}
	
	@Test(timeout=1000)
	public void testTakeAllMotif1() {
		linda.write(tuple);
		Collection<Tuple> result = linda.takeAll(motif);
		assertTrue(result.contains(tuple));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testTakeAllMotif2() {
		linda.write(motif);
		Collection<Tuple> result = linda.takeAll(motif);
		assertTrue(result.contains(motif));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testReadMotifAllMixte() {
		linda.write(tuple);
		linda.write(motif);
		Collection<Tuple> result = linda.takeAll(motif);
		assertTrue(result.contains(tuple));
		assertTrue(result.contains(motif));
		assertEquals(result.size(),2);
	}
	
	@Test(timeout=1000)
	public void testTakeAllVide() {
		linda.write(tupleVide);
		Collection<Tuple> result = linda.takeAll(tupleVide);
		assertTrue(result.contains(tupleVide));
		assertEquals(result.size(),1);
	}
	
	@Test(timeout=1000)
	public void testTakeAllReturnsEmpty() {
		linda.write(motif);
		assertTrue(linda.takeAll(tuple).size()==0);
	}
	
	@Test(timeout=1000)
	public void testTakeAllRemove() {
		linda.write(tuple);
		linda.takeAll(tuple);
		assertNull(linda.tryRead(tuple));
	}
	
	@Test(timeout=1000)
	public void testTakeAllDontBlock() throws InterruptedException {
        Thread th = 
	        new Thread() {
	            public void run() {
	                try {
	                    linda.takeAll(new Tuple(motif));
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
	public void testTakeAllNullThrowsException() {
		linda.takeAll(null);
	}
}