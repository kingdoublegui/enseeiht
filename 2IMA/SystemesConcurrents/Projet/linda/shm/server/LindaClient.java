package shm.server;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import linda.Callback;
import linda.Linda;
import linda.Tuple;

// Version client de linda
public class LindaClient implements Linda {

	private String host;
	private int port;
	private String lindaImpl;
	private RemoteLinda linda;

	public LindaClient(String url) {

		try {
			analyseURL(url);
		} catch (MalformedURLException e1) {
			throw new RuntimeException("URL invalide");
		}

		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(host, port);
			linda = (RemoteLinda) registry.lookup(lindaImpl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void analyseURL(String url) throws MalformedURLException {
		this.host = "localhost";
		this.port = 4000;
		this.lindaImpl = "Linda";
	}

	@Override
	public void write(Tuple t) {
		try {
			linda.write(t);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Tuple take(Tuple template) {
		try {
			return linda.take(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tuple read(Tuple template) {
		try {
			return linda.read(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tuple tryTake(Tuple template) {
		try {
			return linda.tryTake(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Tuple tryRead(Tuple template) {
		try {
			return linda.tryRead(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Tuple> takeAll(Tuple template) {
		try {
			return linda.takeAll(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Tuple> readAll(Tuple template) {
		try {
			return linda.readAll(template);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void eventRegister(eventMode mode, eventTiming timing, Tuple template, Callback callback) {
		try {
			ClientCallback clientCallback = new ClientCallback(new RemoteCallbackImpl(callback));
			linda.eventRegister(mode, timing, template, clientCallback);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void debug(String prefix) {
		try {
			linda.debug(prefix);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}



}