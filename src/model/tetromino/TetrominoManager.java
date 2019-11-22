package model.tetromino;


import model.Grid;

import java.util.Arrays;
import java.util.List;

public class TetrominoManager {
    private final Grid grid;
    private Tetromino current;
    private Tetromino next;

    // Pasiziureti state pattern
    public TetrominoManager(Grid grid) {
        this.grid = grid;
        this.current = getRandom();
        this.next = getRandom();
    }

    public Tetromino getNext() {
        return next;
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void parseNext() {
        current = next;
        next = getRandom();
    }

    private Tetromino getRandom() {
        var options = getAvailableTetrominoes();
        int selection = (int) (Math.random() * (options.size()));
        return options.get(selection);
    }

    private List<Tetromino> getAvailableTetrominoes() {
        int centerX = grid.getWidth() / 2;
        return Arrays.asList(
                new LTetromino(centerX),
                new JTetromino(centerX),
                new ZTetromino(centerX),
                new STetromino(centerX),
                new ITetromino(centerX),
                new OTetromino(centerX),
                new TTetromino(centerX)
        );
    }
}
