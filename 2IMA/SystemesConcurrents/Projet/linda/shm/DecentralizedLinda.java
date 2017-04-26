package shm;

import linda.Linda;
import linda.Tuple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/** Shared memory implementation of Linda. */
public class DecentralizedLinda extends CentralizedLinda {

	public static final int DEFAULT_NSPACES = 8;

	private List<Linda> lindas = new LinkedList<>();

	public DecentralizedLinda() {
		this(DEFAULT_NSPACES);
	}

	public DecentralizedLinda(int nSpaces) {
		for (int iLinda = 0; iLinda < nSpaces; iLinda++) {
			this.add(new CentralizedLinda());
		}
	}

	public void add(Linda linda) {
		lindas.add(linda);
	}
	/**
	 * Adds a tuple t to the tuplespace.
	 *
	 * @param t
	 */
	@Override
	public void write(Tuple t) {
		monitor.lock();
		selectRandomLinda().write(t);
		notifyNewTuple(t);
		monitor.unlock();
	}

	/**
	 * Returns a tuple matching the template and removes it from the tuplespace.
	 * Returns null if none found.
	 *
	 * @param template
	 */
	@Override
	public Tuple tryTake(Tuple template) {
		Tuple tuple = null;
		monitor.lock();
		for (Linda linda : lindas) {
			tuple = linda.tryTake(template);
			if (tuple != null) {
				break;
			}
		}
		monitor.unlock();
		return tuple;
	}

	/**
	 * Returns a tuple matching the template and leaves it in the tuplespace.
	 * Returns null if none found.
	 *
	 * @param template
	 */
	@Override
	public Tuple tryRead(Tuple template) {
		Tuple tuple = null;
		monitor.lock();
		for (Linda linda : lindas) {
			tuple = linda.tryRead(template);
			if (tuple != null) {
				break;
			}
		}
		monitor.unlock();
		return tuple;
	}

	/**
	 * Returns all the tuples matching the template and removes them from the tuplespace.
	 * Returns an empty collection if none found (never blocks).
	 * Note: there is no atomicity or consistency constraints between takeAll and other methods;
	 * for instance two concurrent takeAll with similar templates may split the tuples between the two results.
	 *
	 * @param template
	 */
	@Override
	public Collection<Tuple> takeAll(Tuple template) {
		monitor.lock();
		Collection<Tuple> tuples = new ArrayList<>();
		for (Linda linda : lindas) {
			tuples.addAll(linda.takeAll(template));
		}
		monitor.unlock();
		return tuples;
	}

	/**
	 * Returns all the tuples matching the template and leaves them in the tuplespace.
	 * Returns an empty collection if none found (never blocks).
	 * Note: there is no atomicity or consistency constraints between readAll and other methods;
	 * for instance (write([1]);write([2])) || readAll([?Integer]) may return only [2].
	 *
	 * @param template
	 */
	@Override
	public Collection<Tuple> readAll(Tuple template) {
		monitor.lock();
		Collection<Tuple> tuples = new ArrayList<>();
		for (Linda linda : lindas) {
			tuples.addAll(linda.readAll(template));
		}
		monitor.unlock();
		return tuples;
	}

	/**
	 * To debug, prints any information it wants (e.g. the tuples in tuplespace or the registered callbacks), prefixed by <code>prefix</code.
	 *
	 * @param prefix
	 */
	@Override
	public void debug(String prefix) {
		monitor.lock();
		for (Linda linda : lindas) {
			linda.debug(prefix);
		}
		monitor.unlock();
	}

	private Linda selectRandomLinda() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, lindas.size());
		return lindas.get(randomNum);
	}

}
