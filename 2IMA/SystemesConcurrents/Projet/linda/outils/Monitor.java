package outils;

public class Monitor {
	private Semaphore mutex = new Semaphore(1);
	private Semaphore next = new Semaphore(0);
	private int next_count = 0;
	
	public void lock() {
		mutex.P();
	}
	
	public void unlock() {
		if (next_count > 0) {
			next.V();
		} else {
			mutex.V();
		}
	}
	
	public class Condition {
		public int count = 0;
		private Semaphore queue = new Semaphore(0);
		
		public Condition await(Condition x) {
			x.count++;
			if (next_count > 0) {
				x.queue.V();
			} else {
				mutex.V();
			}
			x.queue.P();
			x.count--;
			return x;
		}

		public Condition signal(Condition x) {
			if (x.count > 0) {
				next_count++;
				x.queue.V();
				next.P();
				next_count--;
			}
			return x;
		}
	}
}
