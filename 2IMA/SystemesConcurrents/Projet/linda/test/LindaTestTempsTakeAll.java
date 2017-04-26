package test;

import java.util.Date;

import linda.Linda;
import linda.Tuple;

/** Cette classe cherche à déterminer le temps que met le serveur à 
 * renvoyer une grosse collection lors d'un appel à TakeAll.
 *
 */
public class LindaTestTempsTakeAll {
	public static void main(String[] args) {
		int nTuplesMax = 1000;
		int nTuplesPas = 100;
		int nTests = 10;
		long resu;
		
		System.out.println("Nb Tuples       Tps (ms)");

		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu = testMoyenne(nTuples, nTests);
			System.out.println(String.format("%9d%15d", nTuples, resu));
		}
	}
	
	// Effectue une moyenne des résultats de nTests tests
	public static long testMoyenne(int nTuple, int nTests) {
		long resuMoyenne = 0;
		
		// Effectuer nTests tests
		for (int i = 0; i < nTests; i++) {
			resuMoyenne += test(nTuple);
		}
		resuMoyenne /= nTests;
		
		return resuMoyenne;
	}
	
    public static long test(int nTuple) {    	
    	// Création de quelques tuples
    	Tuple t1 = new Tuple(2, "test", "milieu", 'c');
    	Tuple t2 = new Tuple(5, '8', 1, 'c', "toto");

    	
    	
    	// Création du serveur
        // final Linda linda = new tshm.CentralizedLinda();
        final Linda linda = new shm.CentralizedLinda();
        // final Linda linda = new server.LindaClient("//localhost:4000/aaa");
        
        // On écrit des données sur ce serveur
        for (int i=0; i<nTuple; i+=2) {
        	linda.write(t1);
        	linda.write(t2);
        }
    	
        // On fait le test
        
		long startTime = new Date().getTime();
		linda.takeAll(t1);
		long endTime = new Date().getTime();
		
		return endTime-startTime;
    }
}
