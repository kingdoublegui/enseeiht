package shm.server;

import linda.AsynchronousCallback;
import linda.Callback;
import linda.Linda;
import linda.Tuple;
import outils.Monitor;
import outils.Monitor.Condition;
import shm.CentralizedLinda;

import java.io.Console;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** Shared memory implementation of Linda. */
public class DecentralizedRemoteLindaImpl extends UnicastRemoteObject implements DecentralizedRemoteLinda, Linda {

	private final int QUEUE_SIZE = 10;

	private Linda linda = new CentralizedLinda();
	private Monitor monitor = new Monitor();
	private Map<Tuple, BlockingQueue<Condition>> waitingReads  = new HashMap<>();
	private Registry registry;

	public DecentralizedRemoteLindaImpl() throws RemoteException {
		registry = LocateRegistry.getRegistry(1099);
	}

	public static void main(String args[]) {
		int port = 1099;

		if (args.length != 1) {
			System.out.println("Syntaxe incorrecte : java RemoteLindaImpl port");
		} else {
			//port = Integer.parseInt(args[0]);
			try {
				DecentralizedRemoteLinda linda = new DecentralizedRemoteLindaImpl();
				Registry registry = LocateRegistry.getRegistry(port);
				int size = registry.list().length;
				System.out.println("//localhost:" + port + "/Linda" + size);
				registry.bind("Linda"+size, linda);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<DecentralizedRemoteLinda> getLindas() {
		List<DecentralizedRemoteLinda> lindas = new ArrayList<>();
		try {
			for (String name : registry.list()) {
				DecentralizedRemoteLinda linda = (DecentralizedRemoteLinda) registry.lookup(name);
				//Si le serveur est down il peut y avoir un probleme lors de l'acces au linda
				lindas.add(linda);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lindas;
	}

	/**
	 * Adds a tuple t to the tuplespace.
	 *
	 * @param t
	 */
	@Override
	public void write(Tuple t) {
		linda.write(t);
		notifyNewTuple(t);
	}

	/**
	 * Returns a tuple matching the template and removes it from the tuplespace.
	 * Blocks if no corresponding tuple is found.
	 *
	 * @param template
	 */
	@Override
	public Tuple take(Tuple template) {
		Tuple tuple = null;
		if (template instanceof TupleServer) {
			//On cherche sur ce serveur
			tuple = linda.take(template);
		} else {
			TupleServer ts = new TupleServer(template);
			//On appelle les methodes take des serveurs avec le tupleserver
			for (DecentralizedRemoteLinda linda : getLindas()) {
				// C'est que c'est un _Stub a 200% et non un _Impl
				// Necessite de l'encapsulation
				Linda lindaD = (DecentralizedRemoteLindaImpl) linda;
				//Faire getLinda().take() entrainerait une attente automatique du serveur
				lindaD.take(ts);
			}
		}
		return tuple;
	}

	/**
	 * Returns a tuple matching the template and leaves it in the tuplespace.
	 * Blocks if no corresponding tuple is found.
	 *
	 * @param template
	 */
	@Override
	public Tuple read(Tuple template) {
		Tuple tuple = null;
		Condition c;
		while((tuple = tryRead(template)) == null){
			c = addWaitingRead(template);
			c.await();
		}
		return tuple;
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
		for (DecentralizedRemoteLinda linda : getLindas()) {
			try {
				tuple = linda.getLinda().tryTake(template);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if (tuple != null) {
				break;
			}
		}
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
		for (DecentralizedRemoteLinda linda : getLindas()) {
			try {
				tuple = linda.getLinda().tryRead(template);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if (tuple != null) {
				break;
			}
		}
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
		Collection<Tuple> tuples = new ArrayList<>();
		for (DecentralizedRemoteLinda linda : getLindas()) {
			try {
				tuples.addAll(linda.getLinda().takeAll(template));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
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
		Collection<Tuple> tuples = new ArrayList<>();
		for (DecentralizedRemoteLinda linda : getLindas()) {
			try {
				tuples.addAll(linda.getLinda().readAll(template));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return tuples;
	}

	/**
	 * Registers a callback which will be called when a tuple matching the template appears.
	 * If the mode is Take, the found tuple is removed from the tuplespace.
	 * The callback is fired once. It may re-register itself if necessary.
	 * If timing is immediate, the callback may immediately fire if a matching tuple is already present; if timing is future, current tuples are ignored.
	 * Beware: a callback should never block as the calling context may be the one of the writer (see also {@link AsynchronousCallback} class).
	 * Callbacks are not ordered: if more than one may be fired, the chosen one is arbitrary.
	 * Beware of loop with a READ/IMMEDIATE re-registering callback !
	 *
	 * @param mode     read or take mode.
	 * @param timing   (potentially) immediate or only future firing.
	 * @param template the filtering template.
	 * @param callback the callback to call if a matching tuple appears.
	 */
	@Override
	public void eventRegister(eventMode mode, eventTiming timing, Tuple template, Callback callback) {
		linda.eventRegister(mode, timing, template, callback);
		//On ajout le tuple dans la liste d'attente appropriee
		//Ce qui permet d'etre demandee par la suite
		//S'inspirer de CentralizedLinda
	}

	/**
	 * To debug, prints any information it wants (e.g. the tuples in tuplespace or the registered callbacks), prefixed by <code>prefix</code.
	 *
	 * @param prefix
	 */
	@Override
	public void debug(String prefix) {
		List<DecentralizedRemoteLinda> lindas = getLindas();
		for (DecentralizedRemoteLinda linda : lindas) {
			Console console = System.console();
			try {
				linda.debug(prefix, console);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void debug(String prefix, Console console) throws RemoteException {
		linda.debug(prefix);
	}

	@Override
	public Linda getLinda() throws RemoteException {
		return linda;
	}

	public void notifyNewTuple(Tuple t) {
		List<DecentralizedRemoteLinda> lindas = getLindas();
		for (DecentralizedRemoteLinda linda : lindas) {
			try {
				linda.newTuple(t);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void newTuple(Tuple t) throws RemoteException {
		//si le linda attends dessus, on effectue l'action appropriee
		for (DecentralizedRemoteLinda linda : getLindas()) {
			boolean reads = linda.hasWaitingRead(t);
		}
	}

	private Condition addWaitingRead(Tuple template){
		Condition c = monitor.newCondition();
		waitingReads.putIfAbsent(template, new ArrayBlockingQueue<>(QUEUE_SIZE, true));
		waitingReads.get(template).add(c);
		return c;
	}

	public boolean hasWaitingRead(Tuple t)  throws RemoteException {
		Condition c;
		boolean r = false;
		for(Tuple template : waitingReads.keySet()){
			if(t.matches(template)){
				r = true;
				while((c = waitingReads.get(template).poll()) != null){
					c.signal();
				}
			}
		}
		return r;
	}

	private class TupleServer extends Tuple {
		public TupleServer() {
			super();
		}

		public TupleServer(Tuple t) {
			super(t);
		}
	}

}
