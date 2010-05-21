package jeudelavie;


public class Board {
    private boolean board[][];
    private int rows;
    private int cols;

    /**
     * Create a new board
     * @param rows number of rows
     * @param cols number of columns
     */
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new boolean[rows][cols];
    }


    /**
     *
     * @param x the row
     * @param y the column
     * @return the value at x,y
     */
    public boolean getCell(int x, int y) {
        return board[x][y];
    }

    /**
     * Set the value of a cell
     * @param x the row
     * @param y the column
     * @param b the boolean value
     */
    public void setCell(int x, int y, boolean b) {
        if (x < rows && y < cols)
            board[x][y] = b;
    }



    public void nextGeneration() {
        Board newBoard = new Board(rows, cols);

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < rows; ++j) {
                newBoard.setCell(i, j, this.nextGeneration(i, j));
            }
        }

        board = newBoard.board;
    }

    public boolean nextGeneration(int x, int y) {
        int adjacent = occupiedAdjacentCells(x, y);

        if (adjacent == 3)
            return true;
        else if (adjacent >= 4)
            return false;
        else if (adjacent <= 1)
            return false;

        return getCell(x, y);
    }

    public int occupiedAdjacentCells(int x, int y) {
        int occupied = 0;

        for (int i = x-1; i <= x+1; ++i) {
            for (int j = y-1; j <= y+1; ++j) {
                if (i != x || j != y) {
                    try {
                        occupied += getCell(i, j) ? 1 : 0;
                    }
                    catch (IndexOutOfBoundsException e) {}
                }
            }
        }
        return occupied;
    }


    public String toString() {
        StringBuilder out = new StringBuilder();
        for (boolean[] row: board) {
            for (boolean cell: row) {
                out.append(cell ? "*" : ".");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
