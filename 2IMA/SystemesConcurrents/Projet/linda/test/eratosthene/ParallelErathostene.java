package test.eratosthene;

import linda.Tuple;

/**
 * Created by thibault on 06/01/17.
 */
public class ParallelErathostene extends AbstractEratosthene {

    private int nThread;

    public ParallelErathostene(int size) {
        this(size, 8);
    }

    public ParallelErathostene(int size, int nThread) {
        super(size);
        this.nThread = nThread;
    }

    @Override
    public void removeNonPrimes() {
        int sliceSize = size/nThread;
        for (int from = 2; from <= size; from += sliceSize) {
            int to = from + sliceSize;
            if (to > size)
                to = size;
            new EratostheneThread(from, to).run();
        }
    }

    private class EratostheneThread {

        private int from;
        private int to;

        public EratostheneThread (int from, int to) {
            this.from = from;
            this.to = to;
        }

        public void run () {
            for (int curInt = from; curInt <= to; curInt++) {
                Tuple primeTuple = linda.tryRead(new Tuple(curInt));
                if (primeTuple != null) {
                    int prime = (Integer) primeTuple.element();
                    removeMultiples(prime);
                }
            }
        }

    }
}
