package outils;

import linda.*;

public class Semaphore {

	private Linda linda;
	private Tuple t;
	
	public Semaphore(Integer nbJetons) {
		linda = new shm.CentralizedLinda();	
		
		t = new Tuple();
		
		for (int i=0; i<nbJetons; i++) {
			this.V();
		}
	}

	public Semaphore() {
		this(0);		
	}
	
	public void V() {
		linda.write(t);
	}
	
	public void P() {
		linda.take(t);
	}
}
