
import java.util.Date;
import java.util.concurrent.CyclicBarrier;

import linda.Linda;
import linda.Tuple;

/** Ce test a pour but de déterminer si les appels a take et a read sont 
 * exécutés dans des durées similaires ou non lorsque le serveur est chargé.
 */
public class LindaTestTempsReadTake {

	final static int nbThreads = 4;
	final static CyclicBarrier barrier = new CyclicBarrier(nbThreads);

	public static void main(String[] args) {
		int nTuplesMax = 6000;
		int nTuplesPas = 600;
		long[] resu = new long[2];
		
		System.out.println("Nb Tuples       Tps Read (ms)       Tps Take (ms)");

		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu = test(nTuples, new linda.shm.CentralizedLinda());
			System.out.println(String.format("%9d%20d%20d", nTuples, resu[0], resu[1]));
		}

		for (int nTuples=nTuplesPas; nTuples<= nTuplesMax; nTuples+= nTuplesPas) {
			resu = test(nTuples, new linda.shm.ThreadedCentralizedLinda());
			System.out.println(String.format("%9d%20d%20d", nTuples, resu[0], resu[1]));
		}
	}
	
    public static long[] test(int nTuple, Linda linda) {    	
    	// Création de quelques tuples
    	Tuple t1 = new Tuple(5, 'a', "test", "extremité", 1);
    	Tuple t2 = new Tuple(7, 'b', "test", "extremité", 2);
    	Tuple t3 = new Tuple(2, "test", "milieu", 'c');
    	Tuple t4 = new Tuple(5, '8', 1, 'c', "toto");
    	Tuple t5 = new Tuple(3);
    	Tuple t6 = new Tuple("toto", "titi");

    	
    	
    	// Création du serveur
        // final Linda linda = new tshm.CentralizedLinda();
        //final Linda linda = new linda.shm.ThreadedCentralizedLinda();
        // final Linda linda = new server.LindaClient("//localhost:4000/aaa");
        
        // On écrit des données sur ce serveur

        for (int i=0; i<nTuple; i+=6) {
        	linda.write(t1);
        	linda.write(t2);
        	linda.write(t3);
        	linda.write(t4);
        	linda.write(t5);
			linda.write(t6);
		}
        
        // On fait le test
		long[] resu = new long [2];
        
		long startTime = new Date().getTime();
		for(int j = 0; j < nbThreads; j++){
			new Thread(new Runnable(){
				public void run(){
					for(int i = 0; i < nTuple/(6*nbThreads); i++){
						linda.read(t6);
					}
					try{
						barrier.await();
					}catch(Exception e){}
				}
			}).start();
		}
		long endTime = new Date().getTime();
		resu[0] = endTime-startTime;
		
		startTime = new Date().getTime();
		for(int j = 0; j < nbThreads; j++){
			new Thread(new Runnable(){
				public void run(){
					for(int i = 0; i < nTuple/(6*nbThreads); i++){
						linda.take(t6);
					}
					try{
						barrier.await();
					}catch(Exception e){}
				}
			}).start();
		}
		endTime = new Date().getTime();
		resu[1] = endTime-startTime;
		
		
		return resu;
    }
    
    
}
