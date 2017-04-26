package shm;

import linda.Tuple;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by thibault on 08/01/17.
 */
public class TupleSpace implements Collection<Tuple> {

    public static final int DEFAULT_NSPACE = 4;

    private List<List<Tuple>> tuples = new ArrayList<>();
    private GetThread[] getThreads;
    private static CyclicBarrier barrier;

    public TupleSpace() {
        this(DEFAULT_NSPACE);
    }

    public TupleSpace(int nspace) {
        super();
        getThreads = new GetThread[nspace];
        barrier = new CyclicBarrier(nspace+1);
        for (int iSpace = 0; iSpace < nspace; iSpace++) {
            tuples.add(new ArrayList<>());
        }
    }

    @Override
    public int size() {
        int size = 0;
        for (List<Tuple> t : tuples) {
            size += t.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private static boolean contains = false;
    @Override
    public boolean contains(Object o) {
        contains = false;
        barrier.reset();

        for (List<Tuple> t : tuples) {
            new ContainsThread(t, o, barrier).start();
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return contains;
    }

    private class ContainsThread extends Thread {

        private List<Tuple> tuples;
        private Object o;
        private CyclicBarrier barrier;

        public ContainsThread(List<Tuple> tuples, Object o, CyclicBarrier barrier) {
            this.tuples = tuples;
            this.o = o;
            this.barrier = barrier;
        }

        public void run() {
            contains = contains || tuples.contains(o);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Iterator<Tuple> iterator() {
        return new Iterator<Tuple>() {

            private int currentIndex = 0;
            private int currentSpace = 0;
            private int currentIndexSpace = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size();
            }

            @Override
            public Tuple next() {
                emptySpaces();
                currentIndex++;
                return tuples.get(currentSpace).get(currentIndexSpace++);
            }

            @Override
            public void remove() {
                emptySpaces();
                currentIndex++;
                tuples.get(currentSpace).remove(currentIndexSpace++);
            }

            // What are we livin' for
            private void emptySpaces() {
                while (currentIndexSpace == tuples.get(currentSpace).size()) {
                    currentIndexSpace = 0;
                    currentSpace++;
                }
            }
        };
    }

    @Override
    public Object[] toArray() {
        ArrayList<Tuple> res = new ArrayList<>();
        for (List<Tuple> t : tuples) {
            res.addAll(t);
        }
        return res.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Tuple serializables) {
        return selectRandomSpace().add(serializables);
    }

    @Override
    public boolean remove(Object o) {
        for (List<Tuple> t : tuples) {
            if (t.contains(o)) {
                return t.remove(o);
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Tuple> collection) {
        boolean res = false;
        for (Tuple t : collection) {
            res = res || add(t);
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean res = false;
        for (Object o : collection) {
            res = res || remove(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        tuples = null;
    }

    private static Tuple tuple;
    public Tuple get(Tuple template) {
        tuple = null;
        //barrier.reset();

        for (List<Tuple> t : tuples) {
            for (Tuple tu : t) {
                if (tu.matches(template)) {
                    tuple = tu;
                }
            }
            //new GetThread(t, template, barrier).start();
        }

//        try {
//            barrier.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (BrokenBarrierException e) {
//            e.printStackTrace();
//        }
        return tuple;
    }

    private class GetThread extends Thread {

        private List<Tuple> tuples;
        private Tuple template;
        private CyclicBarrier barrier;

        public GetThread(List<Tuple> tuples, Tuple template, CyclicBarrier barrier) {
            this.tuples = tuples;
            this.template = template.deepclone();
            this.barrier = barrier;
        }

        public void run() {
            for(Tuple t : tuples){
                if(t.matches(template)){
                    tuple = t.deepclone();
                    break;
                }
            }
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

    }

    private List<Tuple> selectRandomSpace() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, tuples.size());
        return tuples.get(randomNum);
    }

    public String toString() {
        String s = "";
        for (List<Tuple> t : tuples) {
            for (Tuple tuple : t) {
                s += t.toString() + " ";
            }
        }
        return s;
    }
}
