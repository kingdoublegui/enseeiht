import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;


public class LauncherImpl extends UnicastRemoteObject implements Launcher {

	private static final long serialVersionUID = 1L;
	
	private static final int registryPort = 5000;
	
	private static GroupImpl group = new GroupImpl();
	private static ArrayList<Client> clients = new ArrayList<Client>();
	
    private static int ticket = 0;

	protected LauncherImpl() throws RemoteException {
	}

	synchronized public void addClient(String host, int port, Client c) throws RemoteException {
		System.out.println("received "+host+" "+port);
		group.addClient(host, port);
		clients.add(c);
	}
	
	public static void go() {
		Iterator<Client> it = clients.iterator();
		while (it.hasNext()) {
			Client c = it.next();
			try {
				c.setGroup(group);
				c.startDaemon();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		it = clients.iterator();
		while (it.hasNext()) {
			Client c = it.next();
			try {
				c.start();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

    synchronized static public int getTicket() throws RemoteException {
        return ticket++;
    }

	public static void print() {
		Iterator<Client> it = clients.iterator();
		while (it.hasNext()) {
			Client c = it.next();
			try {
				int d[] = c.getData();
				for (int i=0;i<ClientImpl.nbData;i++) System.out.print(d[i]+" ");
				System.out.println("");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		try {
			LocateRegistry.createRegistry(registryPort);
			Naming.bind("//localhost:"+registryPort+"/Launcher", new LauncherImpl());
			
			new Control();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
