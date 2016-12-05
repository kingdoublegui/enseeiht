// source : https://github.com/aradzie/sudoku
package sudoku;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

import static java.lang.Integer.bitCount;
import static java.util.Arrays.copyOf;

/** Sudoku board that is able to fill empty cells with matching values. */
public class Board {
    /** Assists in building sudoku board. */
    public static class Builder {
        private final byte[] cells = new byte[CELLS];
        private int count;

        /**
         * Add board cell.
         *
         * @param value Cell value.
         * @return This builder instance.
         */
        public Builder add(int value) {
            if (count == CELLS) {
                throw new IllegalStateException("too much input");
            }
            if (value < 0 || value > 9) {
                throw new IllegalArgumentException("input out of range");
            }
            cells[count++] = (byte) value;
            return this;
        }

        /** @return Sudoku board built from the specified data. */
        public Board build() {
            if (count < CELLS) {
                throw new IllegalStateException("insufficient input");
            }
            return new Board(cells);
        }
    }

    /** Forks new actions when there is a need to split search. */
    public class SolverAction extends RecursiveAction {
        private final Listener listener;
        private final byte[] cells;
        private final int[] candidates;
        private final int index;
        private int forkedActions;

        private SolverAction(Listener listener, byte[] cells, int[] candidates, int index) {
            this.listener = listener;
            this.cells = cells;
            this.candidates = candidates;
            this.index = index;
            forkedActions = 1;
        }

        @Override
        protected void compute() {
            solve(index);
        }

        /** @return Total number of all child actions forked by this action. */
        public int getForkedActions() {
            return forkedActions;
        }

        private void solve(int index) {
            if (index == candidates.length) {
                // All candidates are eliminated, so the solution is found!
                assert validateBoard(cells);
                listener.solution(cells);
            } else {
                int v = candidates[index];
                int vx = (v >> X_OFF) & XY_MASK;
                int vy = (v >> Y_OFF) & XY_MASK;
                int vs = v & SET_MASK;

                // Iterate over all candidates for a cell.
                int branches = bitCount(vs);
                if (branches > 1) {
                    // Search in parallel.
                    ArrayList<SolverAction> tasks = new ArrayList<SolverAction>(branches);

                    int n = 0;
                    while (vs > 0) {
                        if ((vs & 1) == 1) {
                            // Found the next candidate.
                            cells[vy * DIM + vx] = (byte) (n + 1);

                            byte[] nextCells;
                            int[] nextCandidates;
                            if (vs > 1) {
                                // Going to fork search, so make clone of the mutable lists.
                                nextCells = copyOf(cells, cells.length);
                                nextCandidates = copyOf(candidates, candidates.length);
                            } else {
                                // The last fork branch, share mutable lists to preserve memory.
                                nextCells = cells;
                                nextCandidates = candidates;
                            }

                            // Remove this candidate from the corresponding row, cell and block.
                            eliminateCandidate(nextCandidates, peers, index, n);

                            // Solve recursively in parallel thread.
                            tasks.add(new SolverAction(listener, nextCells, nextCandidates, index + 1));
                        }
                        vs >>= 1;
                        n++;
                    }

                    // Fork...
                    invokeAll(tasks);
                    // ... and join.
                    for (SolverAction task : tasks) {
                        forkedActions += task.forkedActions;
                    }
                } else {
                    // Not forking so search recursively.
                    int n = 0;
                    while (vs > 0) {
                        if ((vs & 1) == 1) {
                            // Found the next candidate.
                            cells[vy * DIM + vx] = (byte) (n + 1);

                            // Remove this candidate from the corresponding row, cell and block.
                            eliminateCandidate(candidates, peers, index, n);

                            // Solve recursively.
                            solve(index + 1);

                            // Only one branch here.
                            break;
                        }
                        vs >>= 1;
                        n++;
                    }
                }
            }
        }
    }

    /** Board width and height. */
    public static final int DIM = 9;
    /** Number of cells in the board. */
    public static final int CELLS = DIM * DIM;
    /** Mask of bit set of candidate values. */
    private static final int SET_MASK = 511;
    /** Offset to X coordinate. */
    private static final int X_OFF = 10;
    /** Offset to Y coordinate. */
    private static final int Y_OFF = 14;
    /** Number of bits in X and Y coordinate. */
    private static final int XY_MASK = 15;
    /** Offset to index in the array of peers. */
    private static final int P_OFF = 18;
    /** Board cell values in range 1-9, zero means empty cell. */
    private final byte[] cells;
    /**
     * Array of sets of candidate values for empty cells.
     * <p>Every array element is a record of fields encoded as a bit string:</p>
     * <pre>
     *   P   Y   X   BITSET      record field name
     * +---+---+---+--------+
     *   ~   4   4     10        bit count
     * </pre>
     * <p>Where <code>P</code> is the index in the array of peers,
     * <code>X</code> and <code>Y</code> are coordinates of the cell in
     * range [1-9] minus 1. <code>BITSET</code> is a bit mask where each bit
     * in position <code>N</code> designates candidate decimal number
     * <code>N + 1</code>. If, for example 3th bit is set, it means that 4
     * is a possible candidate for cell X,Y. There may be up to 9 candidates
     * for every empty cell, hence at most 9 bits set.</p>
     */
    private final int[] candidates;
    /**
     * Indexes of peer candidates for every candidate element.
     * Peers of a cells are on the same row, column or in the same block.
     */
    private final byte[][] peers;
    private final BigInteger searchSpace;


    private Board(byte[] c) {
        cells = c;
        candidates = findCandidates(cells);
        //sortCandidates(candidates);
        peers = findPeers(candidates);
        BigInteger n = BigInteger.valueOf(1);
        for (int candidate : candidates) {
            n = n.multiply(BigInteger.valueOf(bitCount(candidate & SET_MASK)));
        }
        searchSpace = n;
    }

    /** @return The number of branches in the search space. */
    public BigInteger getSearchSpace() {
        return searchSpace;
    }

    /** @return Number of empty cells. */
    public int getEmptyCells() {
        return candidates.length;
    }

    /** @return Ratio of filled to all cells. */
    public double getFillFactor() {
        return (double) (CELLS - candidates.length) / (double) CELLS;
    }

    /**
     * Solve sudoku.
     *
     * @param listener Listener to be notified of solutions.
     */
    public void solve(Listener listener) {
        // Solve recursively starting from the first candidate.
        // Clone mutable structures to make method reentrant.
        solveSequentially(listener,
                copyOf(cells, cells.length),
                copyOf(candidates, candidates.length),
                0);
    }

    /**
     * Create recursive action to solve Sudoku board in parallel
     * in a fork join pool.
     *
     * @param listener Listener to be notified of solutions.
     * @return A recursive action instance.
     */
    public SolverAction newSolverAction(Listener listener) {
        // Clone mutable structures to make method reentrant.
        return new SolverAction(listener,
                copyOf(cells, cells.length),
                copyOf(candidates, candidates.length),
                0);
    }


    /**
     * The algorithm!
     *
     * @param listener   A listener instance to be notified of found solutions.
     * @param cells      Cells to be filled in.
     * @param candidates Array of candidate sets.
     * @param index      The index of candidate set to start search from.
     * @return Whether to keep searching or just stop.
     */
    private boolean solveSequentially(Listener listener, byte[] cells, int[] candidates, int index) {
        if (index == candidates.length) {
            // All candidates are eliminated, so the solution is found!
            assert validateBoard(cells);
            return listener.solution(cells);
        } else {
            int v = candidates[index];
            int vx = (v >> X_OFF) & XY_MASK;
            int vy = (v >> Y_OFF) & XY_MASK;
            int vs = v & SET_MASK;

            // Iterate over all candidates for a cell.
            int n = 0;
            while (vs > 0) {
                if ((vs & 1) == 1) {
                    // Found the next candidate.
                    cells[vy * DIM + vx] = (byte) (n + 1);

                    int[] nextCandidates;
                    if (vs > 1) {
                        // Going to fork search, so make clone of the mutable candidates list.
                        nextCandidates = copyOf(candidates, candidates.length);
                    } else {
                        // The last fork branch, share mutable list to preserve memory.
                        nextCandidates = candidates;
                    }

                    // Remove this candidate from the corresponding row, cell and block.
                    eliminateCandidate(nextCandidates, peers, index, n);

                    // Solve recursively.
                    boolean more = solveSequentially(listener, cells, nextCandidates, index + 1);

                    if (!more) {
                        // Listener does not want new results, so stop searching.
                        return false;
                    }
                }
                vs >>= 1;
                n++;
            }

            return true;
        }
    }

    /**
     * Remove candidate value from cells on the same row, column
     * and in the same block.
     *
     * @param candidates Candidates array.
     * @param peers      Candidate peers.
     * @param index      Candidate index.
     * @param n          Candidates to remove.
     */
    private static void eliminateCandidate(int[] candidates, byte[][] peers, int index, int n) {
        byte[] p = peers[candidates[index] >> P_OFF];
        int mask = ~(1 << n);
        for (byte i : p) {
            candidates[i] &= mask;
        }
    }

    /**
     * Make sure the board is fully solved.
     *
     * @param cells Board cells.
     * @return Value indicating validity of the board.
     */
    private static boolean validateBoard(byte[] cells) {
        try {
            for (int y = 0; y < DIM; y++) {
                for (int x = 0; x < DIM; x++) {
                    int s = findCellCandidates(cells, x, y);
                    if (s > 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (IllegalStateException ex) {
            return false;
        }
    }

    /**
     * For every empty board cell fill set of possible candidate values.
     *
     * @param cells Board cells.
     * @return Array of candidate records.
     */
    private static int[] findCandidates(byte[] cells) {
        int[] candidates = new int[CELLS];
        int length = 0;
        for (int y = 0; y < DIM; y++) {
            for (int x = 0; x < DIM; x++) {
                if (cells[y * DIM + x] == 0) {
                    int s = findCellCandidates(cells, x, y);
                    s = s // 10 bits for candidates bit set
                            | (x << X_OFF) // 4 bits for x coordinate
                            | (y << Y_OFF); // 4 bits for y coordinate
                    candidates[length++] = s;
                }
            }
        }
        return copyOf(candidates, length);
    }

    /**
     * Find all possible values to put in the empty cell with
     * the specified coordinates.
     *
     * @param cells Board cells.
     * @param x     Cell X coordinate.
     * @param y     Cell Y coordinate.
     * @return Cell candidate values encoded as a bit set.
     * @throws IllegalStateException If board contains illegal values.
     */
    private static int findCellCandidates(byte[] cells, int x, int y) {
        // scan row x
        int s1 = 0;
        for (int i = 0; i < DIM; i++) {
            int c = cells[y * DIM + i];
            if (c > 0) {
                int bit = 1 << (c - 1);
                if ((s1 & bit) != 0) {
                    throw new IllegalStateException(
                            String.format("illegal value %d at %d:%d", c, i + 1, y + 1));
                }
                s1 |= bit;
            }
        }
        // scan column y
        int s2 = 0;
        for (int i = 0; i < DIM; i++) {
            int c = cells[i * DIM + x];
            if (c > 0) {
                int bit = 1 << (c - 1);
                if ((s2 & bit) != 0) {
                    throw new IllegalStateException(
                            String.format("illegal value %d at %d:%d", c, x + 1, i + 1));
                }
                s2 |= bit;
            }
        }
        // scan block 3x3 containing point x,y
        int s3 = 0;
        for (int j = (x / 3) * 3; j < 3; j++) {
            for (int i = (y / 3) * 3; i < 3; i++) {
                int c = cells[j * DIM + i];
                if (c > 0) {
                    int bit = 1 << (c - 1);
                    if ((s3 & bit) != 0) {
                        throw new IllegalStateException(
                                String.format("illegal value %d at %d:%d", c, i + 1, j + 1));
                    }
                    s3 |= bit;
                }
            }
        }
        return ~(s1 | s2 | s3) & SET_MASK;
    }

    /**
     * For every candidate cell find other candidates on the same
     * row, column and block.
     *
     * @param candidates Candidates array.
     * @return Array of indexes of peers for each candidate.
     */
    private static byte[][] findPeers(int[] candidates) {
        byte[][] peers = new byte[candidates.length][];
        for (int m = 0; m < candidates.length; m++) {
            int v = candidates[m];
            int vx = (v >> X_OFF) & XY_MASK;
            int vy = (v >> Y_OFF) & XY_MASK;
            int vb = block(vx, vy);
            // Count peers.
            int pn = 0;
            for (int n = 0; n < candidates.length; n++) {
                int w = candidates[n];
                int wx = (w >> X_OFF) & XY_MASK;
                int wy = (w >> Y_OFF) & XY_MASK;
                int wb = block(wx, wy);
                // See if other candidate set is on the same row, column or block.
                if (wx == vx || wy == vy || wb == vb) {
                    pn++;
                }
            }
            // Populate peers.
            byte[] p = peers[m] = new byte[pn];
            int pi = 0;
            for (int n = 0; n < candidates.length; n++) {
                int w = candidates[n];
                int wx = (w >> X_OFF) & XY_MASK;
                int wy = (w >> Y_OFF) & XY_MASK;
                int wb = block(wx, wy);
                // See if other candidate set is on the same row, column or block.
                if (wx == vx || wy == vy || wb == vb) {
                    p[pi++] = (byte) n;
                }
            }
            // Make reference to peers in candidate.
            candidates[m] = v | (m << P_OFF);
        }
        return peers;
    }

    private static int block(int x, int y) {
        return y / 3 * 3 + x / 3;
    }

    private static void sortCandidates(int[] candidates) {
        for (int m = 1; m < candidates.length; m++) {
            int v = candidates[m];
            int n = m;
            for (; n > 0 && bitCount(candidates[n - 1] & SET_MASK)
                    > bitCount(v & SET_MASK); n--) {
                candidates[n] = candidates[n - 1];
            }
            candidates[n] = v;
        }
    }
}
