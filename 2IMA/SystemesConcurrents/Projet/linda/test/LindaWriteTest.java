package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import linda.*;

public class LindaWriteTest {
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
		// linda = new linda.tshm.CentralizedLinda();
		// linda = new shm.ThreadedCentralizedLinda();	
        linda = new shm.server.LindaClient("//localhost:4000/Linda");
        linda.takeAll(motif);
        linda.takeAll(tupleVide);
	}
	
	@After
	public void cleanUp() {
        linda.takeAll(motif);
        linda.takeAll(tupleVide);
	}
	
	@Test
	public void testWriteTuple() {
		linda.write(tuple);
		assertEquals(linda.tryRead(tuple),tuple);
	}
	
	@Test
	public void testMultiEnsemble() {
		linda.write(tuple);
		linda.write(tuple);
		assertEquals(linda.takeAll(tuple).size(),2);
	}
	
	@Test
	public void testWriteMotif() {
		linda.write(motif);
		assertEquals(linda.tryRead(motif), motif);
	}
	
	@Test
	public void testWriteVide() {
		linda.write(tupleVide);
		assertEquals(linda.tryRead(tupleVide), tupleVide);
	}
	
	
	@Test(timeout=1000, expected=NullPointerException.class)
	public void testWriteNullThrowsException() {
		linda.write(null);
	}

	@Test
	public void testWriteNullStoresNothing() {
		try {
			linda.write(null);
		} catch (Exception e) {
			;
		}
		
		// Check that nothing is stored on the server
	    PrintStream origOut = System.out;
	    
	    // Start capturing
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(buffer));

	    // Run debug, which is supposed to output something
	    linda.debug(null);

	    // Stop capturing
	    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

	    // Use captured content
	    String debug = buffer.toString();
	    buffer.reset();
	    assertTrue(debug.equals(""));
	    
	    System.setOut(origOut);
	}
}
