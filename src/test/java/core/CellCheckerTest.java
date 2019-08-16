package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellCheckerTest {

    private final int DEAD = 0;
    private final int ALIVE = 1;

    @Test
    public void testDeadCellWithNoNeighboursShouldStayDead() {
        final Grid grid = new Grid(new int[][]{ {0,0,0}, {0,0,0}, {0,0,0} });

        for (int row = 0; row < grid.getRowCount(); row++) {
            for (int col = 0; col < grid.getColumnCount(); col++) {
                int actual = CellChecker.nextState(grid, row, col);
                assertEquals(DEAD, actual);
            }
        }
    }

    @Test
    public void testAliveCellWithNoNeighboursShouldDie() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(coord(row, col));
        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(DEAD, actual);
    }

    @Test
    public void testAliveCellWithOneNeighbourShouldDie() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(coord(row, col), coord(row, col+1));
        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(DEAD, actual);
    }

    @Test
    public void testAliveCellWithTwoNeighbourShouldLive() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(coord(row, col), coord(row, col+1), coord(row, col-1));
        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(ALIVE, actual);
    }

    @Test
    public void testAliveCellWithThreeNeighbourShouldLive() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(
                coord(row, col),
                coord(row, col+1),
                coord(row, col-1),
                coord(row-1, col));

        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(ALIVE, actual);
    }

    @Test
    public void testAliveCellWithFourNeighbourShouldDie() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(
                coord(row, col),
                coord(row, col+1),
                coord(row, col-1),
                coord(row-1, col),
                coord(row+1, col));

        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(DEAD, actual);
    }

    @Test
    public void testDeadCellWithThreeNeighbourShouldLive() {
        int row = 1;
        int col = 1;

        final Grid grid = generateGridWithAliveCells(
                coord(row, col+1),
                coord(row, col-1),
                coord(row-1, col));

        int actual = CellChecker.nextState(grid, row, col);
        assertEquals(ALIVE, actual);
    }

    private Grid generateGridWithAliveCells(int[] ...coordinates) {
        int[][] grid = { {0,0,0}, {0,0,0}, {0,0,0} };

        for (int[] coordinate: coordinates) {
            grid[coordinate[0]][coordinate[1]] = 1;
        }

        return new Grid(grid);
    }

    private int[] coord(int x, int y) {
        return new int[] {x ,y };
    }
}