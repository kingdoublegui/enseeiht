package test;

import java.util.Date;

import linda.Linda;
import linda.Tuple;

/** Ce test a pour but de déterminer si l'ordre d'écriture des données sur le serveur influe 
 * le temps d'accès à ces données
 */

public class LindaTestImpactPosition {
	public static void main(String[] args) {
		int nTuplesMax = 500000;
		int nTuplesPas = 50000;
		int nTests = 10;
		long[] resu = new long[3];
		
		System.out.println("Nb Tuples        Tps 1er (ms)    Tps dernier (ms)     Tps milieu (ms)");

		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu = testMoyenne(nTuples, nTests);
			System.out.println(String.format("%9d%20d%20d%20d", nTuples, resu[0], resu[1], resu[2]));
		}
	}
	
	// Effectue une moyenne des résultats de nTests tests
	public static long[] testMoyenne(int nTuple, int nTests) {
		long[] resuMoyenne = new long[3];
		long[] resuTest = new long[3];
		
		// Effectuer nTests tests
		for (int i = 0; i < nTests; i++) {
			resuTest = test(nTuple);
			resuMoyenne[0] += resuTest[0];
			resuMoyenne[1] += resuTest[1];
			resuMoyenne[2] += resuTest[2];
		}
		resuMoyenne[0] /= nTests;
		resuMoyenne[1] /= nTests;
		resuMoyenne[2] /= nTests;
		
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
        int i=1;
    	linda.write(t1);
        for (; i<nTuple/2; i+=3) {
        	linda.write(t4);
        	linda.write(t5);
        	linda.write(t6);
        }
    	linda.write(t3);
    	i++;
        for (; i<nTuple-1; i+=3) {
        	linda.write(t4);
        	linda.write(t5);
        	linda.write(t6);
        }        
    	linda.write(t2);
    	
    	
        // On fait le test
		long[] resu = new long [3];
        
		long startTime = new Date().getTime();
		linda.read(t1);
		long endTime = new Date().getTime();
		resu[0] = endTime-startTime;
		
		startTime = new Date().getTime();
		linda.take(t2);
		endTime = new Date().getTime();
		resu[1] = endTime-startTime;
		
		startTime = new Date().getTime();
		linda.take(t3);
		endTime = new Date().getTime();
		resu[2] = endTime-startTime;
		
		return resu;
    }    
}