package core;

/*
Any live cell with fewer than two live neighbours dies, as if by underpopulation.
Any live cell with two or three live neighbours lives on to the next generation.
Any live cell with more than three live neighbours dies, as if by overpopulation.
Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

 */
public class CellChecker {

    private static final int DEAD = 0;
    private static final int ALIVE = 1;

    public static int nextState(Grid grid, int row, int col) {

        boolean cellIsAlive = grid.getCellValue(row, col) == ALIVE;
        long aliveNeighbours = grid.getNeighbours(row,col).stream().filter(n -> n == ALIVE).count();

        if (cellIsAlive) {
            if (aliveNeighbours < 2) {
                return DEAD;
            }

            if (aliveNeighbours < 4) {
                return ALIVE;
            }

            return DEAD;
        }

        if (aliveNeighbours == 3) {
            return ALIVE;
        }

        return DEAD;
    }
}
