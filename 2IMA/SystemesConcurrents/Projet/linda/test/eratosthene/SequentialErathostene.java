package test.eratosthene;

import linda.Tuple;

/**
 * Created by thibault on 06/01/17.
 */
public class SequentialErathostene  extends AbstractEratosthene {

    public SequentialErathostene(int size) {
        super(size);
    }

    @Override
    public void removeNonPrimes() {
        for (int curInt = 2; curInt*curInt <= size; curInt++) {
            Tuple primeTuple = linda.tryRead(new Tuple(curInt));
            if (primeTuple != null) {
                int prime = (Integer) primeTuple.element();
                removeMultiples(prime);
            }
        }
    }

}
