package test;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

import linda.*;

/** Cette classe teste le temps d'execution d'une commande linda (ici Read) 
 * lorsque de nombreux processus accèdent au serveur en meme temps.
 */
public class LindaTestPerfProcess {

	public static void main(String[] args) {
		int nTuplesMax = 50000;
		int nProcessMax = 1000;
		int nTuplesPas = 5000;
		int nProcessPas = 100;
		int nTests = 10;
		long resu;
		
		System.out.println("Temps moyen d'éxécution de read par plusieurs threads simultanés en fonction  du nombre de tuples sur le serveur");
		
		System.out.println("");
		
		  System.out.print("     nTuples");
		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			System.out.print(String.format("%10d", nTuples));
		}
		System.out.println("");
		System.out.print("nProcess");
		
		for (int nProcess = nProcessPas; nProcess <= nProcessMax; nProcess += nProcessPas) {
			System.out.println("");
			System.out.print(String.format("%8d    ", nProcess));
			for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
				resu = testMoyenne(nTuples, nProcess, nTests);
				System.out.print(String.format("%10d", resu));
			}
		}
	}
	
	// Effectue une moyenne des résultats de nTests tests
	public static long testMoyenne(int nTuple, int nProcess, int nTests) {
		long resuMoyenne = 0;
		
		// Effectuer nTests tests
		for (int i = 0; i < nTests; i++) {
			resuMoyenne += test(nTuple, nProcess);
		}
		resuMoyenne /= nTests;
		
		return resuMoyenne;
	}
	
    public static long test(int nTuple, int nProcess) {
    	Thread[] process = new Thread[nProcess];
    	long[] tExec = new long[nProcess];
    	long tMoyen = 0;
    	final CyclicBarrier gate = new CyclicBarrier(nProcess+1);
    	
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
        
        // On crée de nombreux process
        for (int i=0; i<nProcess; i++) {
        	process[i] = new IndexedThreadRead(i, linda, t6, tExec, gate);
        	process[i].start();
        }
        
        // La barrière débloquera tous les processus en meme temps 
        try {
			gate.await();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        
    	// La barrière se débloquera lorsque tous les process ont fini l'exécution de Read
        try {
        	gate.await();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        // On fait la moyenne des résultats
        for (int i=0; i<nProcess; i++) {
        	tMoyen += tExec[i];
        }
        
        tMoyen /= nProcess;
        
        return tMoyen;
    }
    
    
}

class IndexedThreadRead extends Thread {
    private final int index;
    private final Linda linda;
    private final Tuple tuple;
    private long[] tExec;
    private CyclicBarrier gate;
    
    public IndexedThreadRead(int index, Linda linda, Tuple t, long[] resultats, CyclicBarrier gate) {
        this.index = index;
        this.linda = linda;
        this.tuple = t;
        this.tExec = resultats;
        this.gate = gate;
    }

    public void run() {
    	try {
			gate.await();
			
			long startTime = new Date().getTime();
			linda.read(tuple);
			long endTime = new Date().getTime();
			
			tExec[index] = (endTime-startTime);
			// System.out.println(index + " done in " + tExec[index] + " ms");
			
			// Permet d'attendre que tous les process aient finis
			gate.await();
			
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
}