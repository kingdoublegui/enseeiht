import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClientImpl extends UnicastRemoteObject implements Client {

	private static final long serialVersionUID = 1L;

	private static final int registryPort = 5000;
	private static final String registryHost = "localhost";
	
	public static Group group;
	public static int localPort = 0;
	public static String localHost = null;
	public static final int nbData = 10;
	public static int data[] = new int[nbData];
	
	protected ClientImpl() throws RemoteException {
	}
	
	public void setGroup(Group g) throws RemoteException {
		System.out.println("set group");
		group = g;
	}
	
	public void startDaemon () throws RemoteException {
		System.out.println("start daemon");
		group.init();
		Daemon d = new Daemon();
		d.start();
	}
	
	public void start () throws RemoteException {
		System.out.println("start client");
		Appli a = new Appli();
		a.start();
	}
	
	public int[] getData() throws RemoteException {
		return data;
	}
	
	public static void main(String args[]) {
		try {
			localPort = Integer.parseInt(args[0]);
			localHost = InetAddress.getLocalHost().getHostName();
			for (int i=0;i<nbData;i++) data[i] = 0;
			Launcher l = (Launcher)Naming.lookup("//"+registryHost+":"+registryPort+"/Launcher");
			l.addClient(localHost, localPort, new ClientImpl());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
