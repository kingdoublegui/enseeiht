package test;

import java.util.Date;

import linda.*;

/** Cette classe cherche a déterminer le temps d'accès a un tuple particulier lorsque 
 * le serveur contient de très nombreux éléments stockés
 */

public class LindaTestPerfMemoire {

	public static void main(String[] args) {
		int nTuplesMax = 1000000;
		int nTuplesPas = 50000;
		int nTests = 10;
		long[] resu = new long[nTuplesMax/nTuplesPas+1];
		
		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu[nTuples/nTuplesPas] = testMoyenne(nTuples, nTests);
			System.out.println("Temps pour "+ nTuples + " tuples : " + resu[nTuples/nTuplesPas]+" ms");
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
    	Tuple t1 = new Tuple(5, 'a', "test", "extremité", 1);
    	Tuple t2 = new Tuple(7, 'b', "test", "extremité", 2);
    	Tuple t3 = new Tuple(2, "test", "milieu", 'c');
    	Tuple t4 = new Tuple(5, '8', 1, 'c', "toto");
    	Tuple t5 = new Tuple(3);
    	Tuple t6 = new Tuple("toto", "titi");

    	
    	
    	// Création du serveur
        // final Linda linda = new tshm.CentralizedLinda();
        final Linda linda = new shm.CentralizedLinda();
        // final Linda linda = new server.LindaClient("//localhost:4000/aaa");
        
        // On écrit des données sur ce serveur
        linda.write(t6);
        for (int i=0; i<nTuple; i+=5) {
        	linda.write(t1);
        	linda.write(t2);
        	linda.write(t3);
        	linda.write(t4);
        	linda.write(t5);
        }
        
        // On fait le test
		long startTime = new Date().getTime();
		linda.read(t6);
		long endTime = new Date().getTime();
		
		return (endTime-startTime);
    }
    
    
}