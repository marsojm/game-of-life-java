package core;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GridTest {
    private final int DEAD = 0;
    private final int ALIVE = 1;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitializingWithNull() {
        Grid g = new Grid(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitializingWith1DArray() {
        int[][] inputGrid = {};
        Grid g = new Grid(inputGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitializingWithDifferentLengthRows() {
        int[][] inputGrid = { { 0, 0, 0 }, {0, 0}, {0, 0, 0} };
        Grid g = new Grid(inputGrid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenInitializingWithZeroLengthRows() {
        int[][] inputGrid = { {}, {}, {} };
        Grid g = new Grid(inputGrid);
    }

    @Test
    public void shouldInitializeRowAndColumnCount() {
        int[][] inputGrid = { {1,1}, {0,0}, {1,0} };
        Grid g = new Grid(inputGrid);
        
        assertEquals(3, g.getRowCount());
        assertEquals(2, g.getColumnCount());
    }

    @Test
    public void shouldIGetCellValue() {
        int[][] inputGrid = { {0 ,1, 2}, {3, 4, 5}, {6,7,8} };
        Grid g = new Grid(inputGrid);

        assertEquals(Integer.valueOf(5), g.getCellValue(1, 2));
    }

    @Test
    public void shouldIGetNullIfCoordinateIsNotInTheGrid() {
        int[][] inputGrid = { {0 ,1, 2}, {3, 4, 5}, {6,7,8} };
        Grid g = new Grid(inputGrid);

        assertEquals(null, g.getCellValue(-1, 2));
    }

    @Test
    public void testShouldFindEightAliveNeighbours() {
        Grid grid = new Grid(new int[][]{ {1,1,1}, {1,1,1}, {1,1,1} });

        List<Integer> neighbours = grid.getNeighbours(1,1);
        long alive = count(neighbours, ALIVE);
        long dead = count(neighbours, DEAD);

        assertEquals(8, alive);
        assertEquals(0, dead);
    }

    @Test
    public void testShouldFindFourAliveNeighboursAtTheCorners() {
        Grid grid = new Grid(new int[][]{ {1,0,1},
                                          {0,0,0},
                                          {1,0,1} });

        List<Integer> neighbours = grid.getNeighbours(1,1);
        long alive = count(neighbours, ALIVE);
        long dead = count(neighbours, DEAD);

        assertEquals(4, alive);
        assertEquals(4, dead);
    }

    @Test
    public void testShouldFindFourAliveNeighbours() {
        Grid grid = new Grid(new int[][]{ {0,1,0},
                                          {1,0,1},
                                          {0,1,0} });



        List<Integer> neighbours = grid.getNeighbours(1,1);
        long alive = count(neighbours, ALIVE);
        long dead = count(neighbours, DEAD);

        assertEquals(4, alive);
        assertEquals(4, dead);
    }

    @Test
    public void testShouldFindOnlyThreeNeighboursForEachCorner() {
        Grid grid = new Grid(new int[][] { {1,1,1}, {1,1,1}, {1,1,1} } );

        int[][] coordinates = {coord(0,0), coord(0,2), coord(2,0), coord(2,2)};

        for (int[] c: coordinates) {
            int actual = grid.getNeighbours(c[0], c[1]).size();

            assertEquals(3, actual);
        }
    }

    @Test
    public void testShouldFindOnlyFiveNeighboursForEachEdge() {
        Grid grid = new Grid(new int[][] { {1,1,1}, {1,1,1}, {1,1,1} });

        int[][] coordinates = {coord(0,1), coord(1,0), coord(1,2), coord(2,1)};

        for (int[] c: coordinates) {
            int actual = grid.getNeighbours(c[0], c[1]).size();

            assertEquals(5, actual);
        }
    }

    private int[] coord(int x, int y) {
        return new int[] {x ,y };
    }

    private long count(List<Integer> neighbours, int status) {
        return neighbours.stream()
                .filter(n -> n == status)
                .count();
    }
}