// source : https://github.com/aradzie/sudoku
package eightqueens;

import java.io.PrintStream;

/**
 * Solve <a href="http://en.wikipedia.org/wiki/Eight_queens_puzzle">Eight
 * Queens</a> puzzle.
 */
public class EightQueens {
    interface Listener {
        boolean report(Position[] p);

        class Count implements Listener {
            int count;

            @Override
            public boolean report(Position[] p) {
                count++;
                return true;
            }
        }

        class Print implements Listener {
            final PrintStream out;

            Print() {
                out = System.out;
            }

            @Override
            public boolean report(Position[] p) {
                out.println("FOUND SOLUTION:");
                for (Position position : p) {
                    out.println(position);
                }
                out.println();
                out.flush();
                return false;
            }
        }
    }

    public static void main(String[] args) {
        new EightQueens().run();
    }

    private void run() {
        Listener.Count count = new Listener.Count();
        Listener.Print print = new Listener.Print();
        new Solver(count, 8, 8).solve();
        System.out.println("Solutions: " + count.count);
    }

    static class Position {
        final int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x=" + x + ", y=" + y;
        }
    }

    static class Solver {
        /** The listener to report solutions to. */
        final Listener listener;
        /** Board size. */
        final int size;
        /** Number of queens to place on the board. */
        final int queens;
        /** Circular list of nodes for rows. */
        final Node V;
        /** Circular list of nodes for columns. */
        final Node H;
        /** First array of occupied diagonals. */
        final boolean[] d1;
        /** Second array of occupied diagonals. */
        final boolean[] d2;
        /** Array of positions of queens in a solution. */
        final Position[] p;

        Solver(Listener listener, int size, int queens) {
            this.listener = listener;
            this.size = size;
            this.queens = queens;
            V = Node.list(this.size);
            H = Node.list(this.size);
            d1 = new boolean[this.size * 2];
            d2 = new boolean[this.size * 2];
            p = new Position[this.queens];
        }

        void solve() {
            solveImpl(V.r, 0);
        }

        /**
         * Recursive solver function.
         *
         * @param v The row to start from.
         * @param q The next queen to place on the board.
         * @return Whether to keep searching.
         */
        boolean solveImpl(Node v, int q) {
            if (q == queens) {
                // no more queens so a solution is found!
                return listener.report(p);
            } else {
                boolean more = true;

                // outer cycle
                //Node v = V.r;
                while (more && v != V) {
                    //v.take();

                    // inner cycle
                    Node h = H.r;
                    while (more && h != H) {
                        h.take();

                        // are diagonals free?
                        int d1i = size + v.index - h.index;
                        int d2i = size + h.index - size + 1 + v.index;
                        if (!d1[d1i] && !d2[d2i]) {
                            d1[d1i] = true;
                            d2[d2i] = true;

                            p[q] = new Position(h.index, v.index);

                            // recursive call
                            more = solveImpl(v.r, q + 1);

                            p[q] = null;

                            d1[d1i] = false;
                            d2[d2i] = false;
                        }

                        h.yield();
                        h = h.r;
                    }

                    //v.yield();
                    v = v.r;
                }

                return more;
            }
        }
    }


    /**
     * Node represents horizontal of vertical position on a board.
     * Nodes are organized in a double-linked list with one extra head node.
     */
    static class Node {
        final int index;
        Node l, r;

        Node(int index) {
            this.index = index;
        }

        /**
         * Make circular list with the specified number of nodes
         * and one extra head node with <code>{@link #index} = -1</code>.
         *
         * @param max List size.
         * @return Head of circular list.
         */
        static Node list(int max) {
            Node head = new Node(-1), last = head;
            for (int n = 0; n < max; n++) {
                Node node = new Node(n);
                last.r = node;
                node.l = last;
                last = node;
            }
            last.r = head;
            head.l = last;
            return head;
        }

        /** Remove this node from the owning list. */
        void take() {
            l.r = r;
            r.l = l;
        }

        /** Return this node to the owning list. */
        void yield() {
            l.r = this;
            r.l = this;
        }
    }
}
