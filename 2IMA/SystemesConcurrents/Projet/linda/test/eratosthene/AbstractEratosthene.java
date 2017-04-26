package test.eratosthene;

import linda.Linda;
import linda.Tuple;

import java.util.Collection;

/**
 * Created by thibault on 06/01/17.
 */
public abstract class AbstractEratosthene implements Eratosthene {

    protected Linda linda;
    protected int size;

    public AbstractEratosthene(int size) {
        this.size = size;
        linda = new shm.CentralizedLinda();
        // 2 is the first prime.
        for (int iInt = 2; iInt <= size; iInt++) {
            linda.write(new Tuple(iInt));
        }
    }

    @Override
    public int[] getSieve() {
        Collection<Tuple> tuples = linda.readAll(Tuple.valueOf("[ ?Integer ]"));

        int[] sieve = new int[tuples.size()];
        int iTuple = 0;
        for (Tuple tuple : tuples) {
            sieve[iTuple] = (Integer) tuple.element();
            iTuple++;
        }
        return sieve;
    }

    @Override
    public void removeMultiples(int n) {
        for (int curInt = 2*n; curInt <= size; curInt += n) {
            linda.tryTake(new Tuple(curInt));
        }
    }
}
