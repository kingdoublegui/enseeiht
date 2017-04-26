package test;

import linda.Linda;
import linda.Tuple;

/** Cette classe cherche a déterminer la capacité de stockage du serveur linda.
 */

public class LindaTestCapaciteMemoire {
	public static void main(String[] args) {
		Tuple t;
		int nTuple = 0;
		int pas =  100000;
		int NMAX = 100000000;
		
        // final Linda linda = new linda.tshm.CentralizedLinda();
        final Linda linda = new shm.CentralizedLinda();
        // final Linda linda = new server.LindaClient("//localhost:4000/aaa");
        
        while (nTuple < NMAX) {
        	try {
            	t = new Tuple(nTuple, nTuple+15, nTuple + "longue chaine de caractère pour prendre le plus vite possible de la place sur le serveur");
            	
            	for (int i=0; i<pas; i++) {
            		linda.write(t);
            	}
            	nTuple +=pas;
            	if (linda.tryRead(t) == null){
            		throw new OutOfMemoryError();
            	}
            	System.out.println(nTuple + " tuples écrits");
        	} catch (OutOfMemoryError e) {
        		System.out.println("Nombre max de tuples accepté par le serveur : " + nTuple);
        	}
        }
        if (nTuple == NMAX) {
            System.out.println(nTuple + " écrits sans erreurs");
        }
	}
}
