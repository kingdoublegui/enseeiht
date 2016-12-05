import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Client extends Remote {
	public void setGroup (Group g) throws RemoteException;
	public void startDaemon () throws RemoteException;
	public void start () throws RemoteException;
	public int[] getData() throws RemoteException;
}
