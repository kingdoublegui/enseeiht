// source : https://github.com/aradzie/sudoku
package sudoku;

public class SolveEmptyBoard {
    public static void main(String[] args) {
        Board.Builder builder = new Board.Builder();
        for (int n = 0; n < 81; n++) {
            builder.add(0);
        }
        Board board = builder.build();

        board.solve(new Listener() {
            final Listener.Printer printer = new Listener.Printer(System.out);
            int count;

            @Override
            public boolean solution(byte[] cells) {
                printer.solution(cells);

                return ++count < 10;
            }
        });
    }
}
