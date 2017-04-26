package shm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import linda.AsynchronousCallback;
import linda.Callback;
import linda.Linda;
import linda.Tuple;

/** Shared memory implementation of Linda. */
public class CentralizedLinda implements Linda {
	
	private final int QUEUE_SIZE = 10;
	
	private List<Tuple> tuples = new ArrayList<>();
	protected Lock monitor = new ReentrantLock();
	private Map<Tuple, BlockingQueue<Condition>> waitingReads  = new HashMap<>();
	private Map<Tuple, BlockingQueue<Condition>> waitingTakes  = new HashMap<>();
	private Map<Tuple, BlockingQueue<Callback>> waitingReadCallbacks = new HashMap<>();
	private Map<Tuple, BlockingQueue<Callback>> waitingTakeCallbacks = new HashMap<>();

    /** Adds a tuple t to the tuplespace. */
    public void write(Tuple t){
    	if(t == null){
    		throw new NullPointerException();
    	}
    	monitor.lock();
		tuples.add(t.deepclone());
		notifyNewTuple(t);
		monitor.unlock();
	}

    /** Returns a tuple matching the template and removes it from the tuplespace.
     * Blocks if no corresponding tuple is found. */
    public Tuple take(Tuple template){
    	Tuple res = null;
		Condition c;
		monitor.lock();
		while((res = tryTake(template)) == null){
			c = addWaitingTake(template);
			try {
				c.await();
			}catch(InterruptedException e){}
		}
		monitor.unlock();
		return res;
	}

    /** Returns a tuple matching the template and leaves it in the tuplespace.
     * Blocks if no corresponding tuple is found. */
    public Tuple read(Tuple template){
		Tuple res = null;
		Condition c;
		monitor.lock();
		while((res = tryRead(template)) == null){
			c = addWaitingRead(template);
			try {
				c.await();
			}catch(InterruptedException e){}
		}
		monitor.unlock();
		return res;
	}

    /** Returns a tuple matching the template and removes it from the tuplespace.
     * Returns null if none found. */
    public Tuple tryTake(Tuple template){
		monitor.lock();
		Tuple res = tryRead(template);
		if(res != null){
			tuples.remove(res);
		}
		monitor.unlock();
		return res;
	}

    /** Returns a tuple matching the template and leaves it in the tuplespace.
     * Returns null if none found. */
    public Tuple tryRead(Tuple template){
    	if(template == null){
    		throw new NullPointerException();
    	}
		Tuple res = null;
		monitor.lock();
		for(Tuple t : tuples){
			if(t.matches(template)){
				res = t.deepclone();
			}
		}
		monitor.unlock();
		return res;
	}

    /** Returns all the tuples matching the template and removes them from the tuplespace.
     * Returns an empty collection if none found (never blocks).
     * Note: there is no atomicity or consistency constraints between takeAll and other methods;
     * for instance two concurrent takeAll with similar templates may split the tuples between the two results.
     */
    public Collection<Tuple> takeAll(Tuple template){
		Collection<Tuple> res = new ArrayList<Tuple>();
		Tuple t = tryTake(template);
		while(t != null){
			res.add(t);
			t = tryTake(template);
		}
		return res;
	}

    /** Returns all the tuples matching the template and leaves them in the tuplespace.
     * Returns an empty collection if none found (never blocks).
     * Note: there is no atomicity or consistency constraints between readAll and other methods;
     * for instance (write([1]);write([2])) || readAll([?Integer]) may return only [2].
     */
    public Collection<Tuple> readAll(Tuple template){
    	if(template == null){
    		throw new NullPointerException();
    	}
		Collection<Tuple> res = new ArrayList<Tuple>();
		monitor.lock();
		for(Tuple t : tuples){
			if(t.matches(template)){
				res.add(t);
			}
		}
		monitor.unlock();
		return res;
	}

    /** Registers a callback which will be called when a tuple matching the template appears.
     * If the mode is Take, the found tuple is removed from the tuplespace.
     * The callback is fired once. It may re-register itself if necessary.
     * If timing is immediate, the callback may immediately fire if a matching tuple is already present; if timing is future, current tuples are ignored.
     * Beware: a callback should never block as the calling context may be the one of the writer (see also {@link AsynchronousCallback} class).
     * Callbacks are not ordered: if more than one may be fired, the chosen one is arbitrary.
     * Beware of loop with a READ/IMMEDIATE re-registering callback !
     *
     * @param mode read or take mode.
     * @param timing (potentially) immediate or only future firing.
     * @param template the filtering template.
     * @param callback the callback to call if a matching tuple appears.
     */
    public void eventRegister(eventMode mode, eventTiming timing, Tuple template, Callback callback){
    	if(template == null){
    		throw new NullPointerException();
    	}
    	new Thread(() -> {
	    		Tuple t = null;
		    	if(mode == eventMode.READ && timing == eventTiming.IMMEDIATE){
		    		t = read(template);
		    		runCallback(callback, t);
		    	}else if(mode == eventMode.READ && timing == eventTiming.FUTURE){
		    		addWaitingReadCallback(callback, template);
		    	}else if(mode == eventMode.TAKE && timing == eventTiming.IMMEDIATE){
		    		t = take(template);
		    		callback.call(t);
		    	}else if(mode == eventMode.TAKE && timing == eventTiming.FUTURE){
		    		addWaitingTakeCallback(new TakeCallback(callback), template);
		    	}
    		}
		).start();
	}

    /** To debug, prints any information it wants (e.g. the tuples in tuplespace or the registered callbacks), prefixed by <code>prefix</code. */
    public void debug(String prefix){
		for(Tuple t : tuples){
			System.out.println(prefix + t.toString());
		}
	}
    
    private Condition addWaitingRead(Tuple template){
    	Condition c = monitor.newCondition();
		waitingReads.putIfAbsent(template, new ArrayBlockingQueue<>(QUEUE_SIZE, true));
    	waitingReads.get(template).add(c);
    	return c;
    }
    
    private Condition addWaitingTake(Tuple template){
    	Condition c = monitor.newCondition();
		waitingTakes.putIfAbsent(template, new ArrayBlockingQueue<>(QUEUE_SIZE, true));
    	waitingTakes.get(template).add(c);
    	return c;
    }
    
    private void addWaitingReadCallback(Callback callback, Tuple template){
		waitingReadCallbacks.putIfAbsent(template, new ArrayBlockingQueue<>(QUEUE_SIZE, true));
		waitingReadCallbacks.get(template).add(callback);
    }
    
    private void addWaitingTakeCallback(Callback callback, Tuple template){
		waitingTakeCallbacks.putIfAbsent(template, new ArrayBlockingQueue<>(QUEUE_SIZE, true));
    	waitingTakeCallbacks.get(template).add(callback);
    }
    
    protected void notifyNewTuple(Tuple t){
    	boolean notified = false;
    	Condition c;
    	Callback cb;
    	Iterator<Tuple> it;
    	Tuple templateTake;
    	/* Réveil des reads en attente */
    	for(Tuple template : waitingReads.keySet()){
			if(t.matches(template)){
				while((c = waitingReads.get(template).poll()) != null){
					c.signal();
				}
			}
		}
    	/* Réveil des readCallbacks en attente */
    	for(Tuple template : waitingReadCallbacks.keySet()){
			if(t.matches(template)){
				while((cb = waitingReadCallbacks.get(template).poll()) != null){
					runCallback(cb, t);
				}
			}
		}
    	/* Réveil d'un take en attente (priorité au takes direct) */
    	it = waitingTakes.keySet().iterator();
    	while(!notified && it.hasNext()){
    		templateTake = it.next();
    		if(t.matches(templateTake)){
    			Condition lock = waitingTakes.get(templateTake).poll();
    			if (lock != null) {
					lock.signal();
				}
				notified = true;
			}
    	}
    	it = waitingTakeCallbacks.keySet().iterator();
    	while(!notified && it.hasNext()){
    		templateTake = it.next();
    		if(t.matches(templateTake)){
    			runCallback(waitingTakeCallbacks.get(templateTake).poll(), t);
				notified = true;
			}
    	}
    }
    
    private void runCallback(Callback c, Tuple t){
    	new Thread(() -> {
    		c.call(t);
		}).start();
    }
    
    private class TakeCallback implements Callback {
    	
    	private Callback c;
    	
    	public TakeCallback(Callback c){
    		this.c = c;
    	}
    	
		public void call(Tuple t) {
			monitor.lock();
			tuples.remove(t);
			monitor.unlock();
			c.call(t);
		}
    }
  
}
