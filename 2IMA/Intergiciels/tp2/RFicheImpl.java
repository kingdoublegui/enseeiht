import java.rmi.*;

public class RFicheImpl implements RFiche {
    private String name;
    private String mail;

    public RFicheImpl (String name, String mail) throws RemoteException {
        this.name = name;
        this.mail = mail;
    }

	public String getNom () throws RemoteException {
        return name;
    }

	public String getEmail () throws RemoteException {
        return mail;
    }

}



