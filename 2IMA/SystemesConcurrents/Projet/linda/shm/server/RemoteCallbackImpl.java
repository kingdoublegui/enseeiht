package shm.server;

import linda.Callback;
import linda.Tuple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by thibault on 18/01/17.
 */
public class RemoteCallbackImpl extends UnicastRemoteObject implements RemoteCallback {

    Callback callback;

    public RemoteCallbackImpl(Callback callback) throws RemoteException {
        this.callback = callback;
    }

    @Override
    public void call(Tuple tuple) throws RemoteException {
        new Thread() {
            public void run() {
                callback.call(tuple);
            }
        }.start();
    }
}
