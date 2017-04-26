package shm.server;

import linda.Callback;
import linda.Tuple;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by thibault on 18/01/17.
 */
public interface RemoteCallback extends Remote {
    void call(Tuple tuple) throws RemoteException;
}
