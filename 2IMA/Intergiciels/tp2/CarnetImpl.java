import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.Map;
import java.util.HashMap;

public class CarnetImpl extends UnicastRemoteObject implements Carnet {

    public static int PORT = 4000;
    public static int NB_CARNETS = 2;
    
    private Map<String, SFiche> fiches = new HashMap<String, SFiche>();
    private int iCarnet;

    public CarnetImpl(int iCarnet) throws RemoteException {
        this.iCarnet = iCarnet;
    }

	public void Ajouter(SFiche sf) throws RemoteException {
        fiches.put(sf.getNom(), sf);
    }

	public RFiche Consulter(String n, boolean forward) throws RemoteException {
        SFiche fiche = fiches.get(n);
        if (fiche == null && forward) {
            //Get it on the other server
            String urlServer = "//localhost:" + PORT + "/carnet"
                + carnetSuivant();
            Carnet carnet = null;
            try {
                carnet = (Carnet) Naming.lookup(urlServer);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return (carnet != null ) ? carnet.Consulter(n, false): null;
        } else {
            if (fiche == null) {
                return null;
            }
            return new RFicheImpl(fiche.getNom(), fiche.getEmail());
        }
    }

    private int carnetSuivant() throws RemoteException {
        return (iCarnet+1)%NB_CARNETS;
    }

    public static void main(String[] args) {
        int port;
        String url;
        int iCarnet;

        try {
            Integer i = new Integer(PORT);
            port = i.intValue();
            iCarnet = new Integer(args[0]).intValue();
        } catch (Exception e) {
            System.out.println("Please enter: java CarnetImpl <port>");
            return;
        }

        try {
            Registry registry = LocateRegistry.createRegistry(port);
            Carnet carnet = new CarnetImpl(iCarnet);
            url = "//localhost:" + port + "/carnet" + iCarnet;
            Naming.rebind(url, carnet);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

    }

}
