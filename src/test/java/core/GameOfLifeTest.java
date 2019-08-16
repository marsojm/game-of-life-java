package core;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeTest {

    @Test
    public void testShouldBeAbleToCreateNewGame() {
        GameOfLife game = new GameOfLife(new Grid(new int[][] {{1}}));
    }

    @Test
    public void testShouldGetEmptyGridFromEmptyGrid() {
        Grid input = new Grid(new int[][] {
            {0,0,0},
            {0,0,0},
            {0,0,0}
        });

        GameOfLife game = new GameOfLife(input);
        Grid nextState = game.nextState();

        assertEquals(input, nextState);
    }

    @Test
    public void testShouldGetEmptyGridFromOneAliveCell() {
        Grid input = new Grid(new int[][] {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        });

        Grid expected = new Grid(new int[][] {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        });

        GameOfLife game = new GameOfLife(input);
        Grid nextState = game.nextState();

        assertEquals(expected, nextState);
    }

    @Test
    public void testShouldGetTubFromTub() {
        Grid input = new Grid(new int[][] {
                {0,1,0},
                {1,0,1},
                {0,1,0}
        });

        GameOfLife game = new GameOfLife(input);
        Grid nextState = game.nextState();

        assertEquals(input, nextState);
    }

    @Test
    public void testGetBlinker() {
        Grid input = new Grid(new int[][] {
                {0,0,0},
                {1,1,1},
                {0,0,0}
        });

        Grid expectedState1 = new Grid(new int[][] {
                {0,1,0},
                {0,1,0},
                {0,1,0}
        });

        Grid expectedState2 = new Grid(new int[][] {
                {0,0,0},
                {1,1,1},
                {0,0,0}
        });

        GameOfLife game = new GameOfLife(input);
        for (int i = 0; i < 10; i++) {
            Grid nextState = game.nextState();
            Grid expected = i % 2 == 0 ? expectedState1 : expectedState2;

            assertEquals(expected, nextState);
        }
    }
}