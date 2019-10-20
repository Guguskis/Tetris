package model;


import model.tetromino.*;

import java.util.Arrays;
import java.util.List;

public class TetrominoFactory {
    private Tetromino current;
    private Tetromino next;

    public TetrominoFactory(int x, int y) {
        current = getRandom(x, y);
        next = getRandom(x, y);
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void moveConveyor(int x, int y) {
        current = next;
        next = getRandom(x, y);
    }

    public Tetromino preview() {
        return next;
    }

    private Tetromino getRandom(int x, int y) {

        var options = getAvailableTetrominoes(x, y);
        int selection = (int) (Math.random() * (options.size() - 1));
        return options.get(selection);
    }

    private List<Tetromino> getAvailableTetrominoes(int x, int y) {
        return Arrays.asList(
                new LTetromino(x, y),
                new JTetromino(x, y),
                new ZTetromino(x, y),
                new STetromino(x, y),
                new ITetromino(x, y),
                new OTetromino(x, y),
                new TTetromino(x, y)
        );
    }
}
