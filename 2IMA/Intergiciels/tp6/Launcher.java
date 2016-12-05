import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Launcher extends Remote {
	public void addClient(String host, int port, Client c) throws RemoteException;
}
