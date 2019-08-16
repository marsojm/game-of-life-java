package core;

public class GameOfLife {

    private Grid grid;

    public GameOfLife(Grid grid) {
        this.grid = grid;
    }

    public Grid nextState() {
        int[][] newState = new int[this.grid.getRowCount()][];

        for(int row = 0; row < this.grid.getRowCount(); row++) {
            int[] newRow = new int[this.grid.getRowCount()];

            for (int col = 0; col < this.grid.getColumnCount(); col++) {
                newRow[col] = CellChecker.nextState(this.grid, row, col);
            }

            newState[row] = newRow;
        }

        this.grid = new Grid(newState);

        return new Grid(newState);
    }
}
