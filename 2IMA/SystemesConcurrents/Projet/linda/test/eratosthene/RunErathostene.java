package test.eratosthene;

/**
 * Created by thibault on 06/01/17.
 */
public class RunErathostene {

    public static void main(String args[]) {
        int limit = 1000;
        //Eratosthene eratosthene = new SequentialEratosthene(limit);
        Eratosthene eratosthene = new ParallelErathostene(limit);
        eratosthene.removeNonPrimes();
        for (int i : eratosthene.getSieve()) {
            System.out.println(i);
        }
        System.out.println("Total: " + eratosthene.getSieve().length);
    }

}
