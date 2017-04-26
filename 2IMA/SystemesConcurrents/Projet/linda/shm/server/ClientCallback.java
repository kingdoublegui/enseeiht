package shm.server;

import linda.Callback;
import linda.Tuple;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Created by thibault on 18/01/17.
 */
public class ClientCallback implements Callback, Serializable {

    private RemoteCallback callback;

    public ClientCallback(RemoteCallback callback) {
        this.callback = callback;
    }

    public void call(Tuple t) {
        try {
            callback.call(t);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}