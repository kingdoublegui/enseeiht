package shm.server;

import linda.Linda;
import linda.Tuple;

import java.io.Console;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by thibault on 18/01/17.
 */
public interface DecentralizedRemoteLinda extends Remote {
    void newTuple(Tuple t) throws RemoteException;
    void debug(String prefix, Console console) throws RemoteException;
    Linda getLinda() throws RemoteException;
    boolean hasWaitingRead(Tuple t) throws RemoteException;
}
