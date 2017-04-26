package test;

import java.util.Date;

import linda.Linda;
import linda.Tuple;

/** Ce test a pour but de déterminer si les appels a take et a read sont 
 * exécutés dans des durées similaires ou non lorsque le serveur est chargé.
 */
public class LindaTestTempsReadTake {
	public static void main(String[] args) {
		int nTuplesMax = 500000;
		int nTuplesPas = 50000;
		int nTests = 10;
		long[] resu = new long[2];
		
		System.out.println("Nb Tuples       Tps Read (ms)       Tps Take (ms)");

		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu = testMoyenne(nTuples, nTests);
			System.out.println(String.format("%9d%20d%20d", nTuples, resu[0], resu[1]));
		}
	}
	
	// Effectue une moyenne des résultats de nTests tests
	public static long[] testMoyenne(int nTuple, int nTests) {
		long[] resuMoyenne = new long[2];
		long[] resuTest = new long[2];
		
		// Effectuer nTests tests
		for (int i = 0; i < nTests; i++) {
			resuTest = test(nTuple);
			resuMoyenne[0] += resuTest[0];
			resuMoyenne[1] += resuTest[1];
		}
		resuMoyenne[0] /= nTests;
		resuMoyenne[1] /= nTests;
		
		return resuMoyenne;
	}
	
    public static long[] test(int nTuple) {    	
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

        for (int i=0; i<nTuple; i+=5) {
        	linda.write(t1);
        	linda.write(t2);
        	linda.write(t3);
        	linda.write(t4);
        	linda.write(t5);
        }
        linda.write(t6);
        
        // On fait le test
		long[] resu = new long [2];
        
		long startTime = new Date().getTime();
		linda.read(t6);
		long endTime = new Date().getTime();
		resu[0] = endTime-startTime;
		
		startTime = new Date().getTime();
		linda.take(t6);
		endTime = new Date().getTime();
		resu[1] = endTime-startTime;
		
		
		return resu;
    }
    
    
}