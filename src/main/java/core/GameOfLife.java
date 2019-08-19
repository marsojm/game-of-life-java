package core;

public class GameOfLife {

    public static Grid nextState(Grid currentState) {
        int[][] newState = new int[currentState.getRowCount()][];

        for(int row = 0; row < currentState.getRowCount(); row++) {
            int[] newRow = new int[currentState.getColumnCount()];

            for (int col = 0; col < currentState.getColumnCount(); col++) {
                newRow[col] = CellChecker.nextState(currentState, row, col);
            }

            newState[row] = newRow;
        }

        return new Grid(newState);
    }
}
