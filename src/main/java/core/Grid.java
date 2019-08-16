package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {

    private int[][] grid;
    private int rowCount;
    private int columnCount;

    private static final Coord[] potentialNeighbours = {
            // top row
            new Coord(-1,-1),
            new Coord(-1,0),
            new Coord(-1,1),
            // left and right
            new Coord(0,-1),
            new Coord(0,1),
            // bottom row
            new Coord(1,-1),
            new Coord(1,0),
            new Coord(1,1)
    };

    private static class Coord {
        private final int row;
        private final int col;

        public Coord(int row, int col) {

            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }

    public Grid(int[][] grid) {
        this.grid = Grid.validateGrid(grid);
        this.rowCount = grid.length;
        this.columnCount = grid[0].length;
    }

    private static int[][] validateGrid(int[][] grid) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid can't be null.");
        }

        if (grid.length < 1) {
            throw new IllegalArgumentException("Grid must have at least one row and one column.");
        }

        int expectedLength = grid[0].length;

        if (expectedLength == 0) {
            throw new IllegalArgumentException("Row must have at least item.");
        }

        boolean allRowsHaveSameLength = Arrays.stream(grid)
                                                .map(row -> row.length)
                                                .allMatch(len -> len == expectedLength);

        if (!allRowsHaveSameLength) {
            throw new IllegalArgumentException("All rows must have same length.");
        }

        return grid;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Integer getCellValue(int row, int col) {
        return getIfIsWithinBoundaries(row, col);
    }

    public List<Integer> getNeighbours(int row, int col) {
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < potentialNeighbours.length; i++) {
            int x = row + potentialNeighbours[i].getRow();
            int y = col + potentialNeighbours[i].getCol();
            Integer value = getIfIsWithinBoundaries(x, y);

            if (value != null) {
                result.add(value);
            }
        }

        return result;
    }

    public Integer getIfIsWithinBoundaries(int row, int col) {

        if (row >= 0 && row < this.columnCount && col >= 0 && col < this.rowCount) {
            return grid[row][col];
        }

        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        if (this == other) {
            return true;
        }

        Grid g = (Grid)other;

        if (this.rowCount != g.getRowCount() || this.columnCount != g.getColumnCount()) {
            return false;
        }

        for (int row = 0; row < this.rowCount; row++ ) {
            for(int col = 0; col < this.columnCount; col++) {
                if (this.getIfIsWithinBoundaries(row, col) != g.getCellValue(row, col)) {
                    return false;
                }
            }
        }


        return true;
    }
}
