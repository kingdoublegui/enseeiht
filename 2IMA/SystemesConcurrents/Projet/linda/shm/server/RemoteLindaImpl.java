package shm.server;

import linda.Callback;
import linda.Linda;
import linda.Tuple;
import shm.DecentralizedLinda;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;


public class RemoteLindaImpl extends UnicastRemoteObject implements RemoteLinda, Linda {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DecentralizedLinda linda;

	public RemoteLindaImpl() throws RemoteException {
		linda = new DecentralizedLinda();
	}

	public static void main(String args[]) {
		int port;

		if (args.length != 1) {
			System.out.println("Syntaxe incorrecte : java RemoteLindaImpl port");
		} else {
			port = Integer.parseInt(args[0]);
			try {
				RemoteLindaImpl linda = new RemoteLindaImpl();
				Registry registry = LocateRegistry.createRegistry(port);
				System.out.println("//localhost:" + port + "/Linda");
				registry.bind("Linda", linda);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void write(Tuple t) {
		linda.write(t);
	}

	@Override
	public Tuple take(Tuple template) {
		return linda.take(template);
	}

	@Override
	public Tuple read(Tuple template) {
		return linda.read(template);
	}

	@Override
	public Tuple tryTake(Tuple template) {
		return linda.tryTake(template);
	}

	@Override
	public Tuple tryRead(Tuple template) {
		return linda.tryRead(template);
	}

	@Override
	public Collection<Tuple> takeAll(Tuple template) {
		return linda.takeAll(template);
	}

	@Override
	public Collection<Tuple> readAll(Tuple template) {
		return linda.readAll(template);
	}

	@Override
	public void eventRegister(eventMode mode, eventTiming timing, Tuple template, Callback callback) {
		linda.eventRegister(mode, timing, template, callback);
	}
	
	
	@Override
	public void debug(String prefix) {
		linda.debug(prefix);
	}
	
}
