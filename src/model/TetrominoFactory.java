package model;


import model.tetromino.*;

import java.util.Arrays;
import java.util.List;

public class TetrominoFactory {
    private final Grid grid;
    private Tetromino current;
    private Tetromino next;


    public TetrominoFactory(Grid grid) {
        this.grid = grid;
        current = getRandom(grid.getWidth()/2);
        next = getRandom(grid.getWidth()/2);
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void moveConveyor() {
        current = next;
        next = getRandom(grid.getWidth()/2);
    }

    public Tetromino preview() {
        return next;
    }

    private Tetromino getRandom(int x) {
        var options = getAvailableTetrominoes(x);
        int selection = (int) (Math.random() * (options.size() - 1));
        return options.get(selection);
    }

    private List<Tetromino> getAvailableTetrominoes(int x) {
        return Arrays.asList(
                new LTetromino(x),
                new JTetromino(x),
                new ZTetromino(x),
                new STetromino(x),
                new ITetromino(x),
                new OTetromino(x),
                new TTetromino(x)
        );
    }
}
