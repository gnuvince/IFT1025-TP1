package jeudelavie;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        board.setCell(3, 1, true);
        board.setCell(3, 2, true);
        board.setCell(3, 3, true);
        board.setCell(2, 3, true);
        board.setCell(1, 2, true);

        for (int i = 0; i < 15; i++) {
            System.out.println(board);
            board.nextGeneration();
        }

    }
}
