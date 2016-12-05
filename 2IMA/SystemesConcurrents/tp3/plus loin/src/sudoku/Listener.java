// source : https://github.com/aradzie/sudoku

package sudoku;

import java.io.Flushable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/** Board notifies listener every time it finds a solution. */
public interface Listener {
    /**
     * Found new solution.
     *
     * @param cells Board cells.
     * @return Whether to keep searching.
     */
    boolean solution(byte[] cells);

    /** Counts number of solutions. */
    class Counter implements Listener {
        private final AtomicInteger count = new AtomicInteger();

        @Override
        public boolean solution(byte[] cells) {
            count.incrementAndGet();
            return true;
        }

        /** @return Number of solutions. */
        public int count() {
            return count.get();
        }
    }

    /** Prints solution to the specified appendable. */
    class Printer implements Listener {
        private final Appendable out;

        public Printer(Appendable out) {
            this.out = out;
        }

        public Appendable getOut() {
            return out;
        }

        @Override
        public synchronized boolean solution(byte[] cells) {
            try {
                for (int n = 0; n < Board.CELLS; n++) {
                    if (n % 9 == 0) {
                        out.append('\n');
                    } else {
                        out.append(' ');
                    }
                    if (cells[n] > 0) {
                        out.append((char) ('0' + cells[n]));
                    } else {
                        out.append('.');
                    }
                }
                out.append('\n');
                if (out instanceof Flushable) {
                    ((Flushable) out).flush();
                }
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
            return true;
        }

    }
}
