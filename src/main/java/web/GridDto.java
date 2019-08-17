package web;

public class GridDto {

    private int[][] grid;

    public GridDto() {

    }

    public GridDto(int[][] grid) {
        this.grid = grid;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
