// source : https://github.com/aradzie/sudoku
package sudoku;

import java.io.*;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final ForkJoinPool pool = new ForkJoinPool();

    public static void main(String[] args)
            throws IOException {
        Board.Builder builder = new Board.Builder();

        readBoard(args, builder);

        Board board = builder.build();

        benchmark(board, System.out);
    }

    private static void benchmark(Board board, PrintStream out) {
        out.println(String.format("empty cells: %d", board.getEmptyCells()));
        out.println(String.format("fill factor: %d%%", (int) (100 * board.getFillFactor())));
        out.println(String.format("search space before elimination: %s branches", board.getSearchSpace()));

        out.println();

        out.println("solving sequentially...");
        Listener.Counter c1 = new Listener.Counter();
        long t1 = solveSequentially(board, c1);
        out.println(String.format("done in: %,dms", t1));
        out.println(String.format("solutions: %,d", c1.count()));

        out.println();

        out.println("solving parallelly...");
        Listener.Counter c2 = new Listener.Counter();
        Board.SolverAction action = board.newSolverAction(c2);
        long t2 = solveParallelly(pool, action);
        out.println(String.format("done in: %,dms", t2));
        out.println(String.format("solutions: %,d", c2.count()));
        out.println(String.format("forked solvers: %,d", action.getForkedActions()));

        out.println();

        System.out.println(String.format("speedup: %f", (float) t1 / (float) t2));

        out.println();

        System.out.println(pool);
    }

    private static long solveSequentially(Board board, Listener.Counter listener) {
        long start = System.currentTimeMillis();
        board.solve(listener);
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long solveParallelly(ForkJoinPool pool, Board.SolverAction action) {
        long start = System.currentTimeMillis();
        pool.execute(action);
        action.join();
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static void readBoard(String[] args, Board.Builder builder)
            throws IOException {
        InputStream in;
        if (args.length == 1) {
            in = new FileInputStream(args[0]);
        } else {
            in = System.in;
        }
        Reader reader = new InputStreamReader(in);
        try {
            readBoard(reader, builder);
        } finally {
            reader.close();
        }
    }

    /**
     * Read board cells from the specified reader.
     * <p>New line character is ignored. Digit represents board cell value. Space
     * represents empty cell. There must be exactly 81 (9x9) digits or spaces.</p>
     */
    private static void readBoard(Reader reader, Board.Builder builder)
            throws IOException {
        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == '\r' || ch == '\n') {
                continue;
            }
            if (ch >= '0' && ch <= '9') {
                builder.add(ch - '0');
                continue;
            }
            if (ch == ' ' || ch == '_' || ch == '.') {
                builder.add(0);
                continue;
            }
            throw new IllegalStateException("illegal input");
        }
    }
}
