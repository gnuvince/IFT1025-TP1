package jeudelavie;

public class Main {
    public static void main(String[] args) {
        Grille board = new Grille(10);
        board.setState(3, 1, true);
        board.setState(3, 2, true);
        board.setState(3, 3, true);
        board.setState(2, 3, true);
        board.setState(1, 2, true);

        for (int i = 0; i < 15; i++) {
            System.out.println(board);
            board.nextGeneration();
        }

    }
}
