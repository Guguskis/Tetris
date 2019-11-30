package model.tetromino;


import model.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetrominoManager {
    private final Grid grid;
    private Tetromino current;
    private Tetromino next;

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
        // Todo use dependency injection
        Random r = new Random();

        List<String> availableTypes = getTetrominoTypes();
        int centerX = grid.getWidth() / 2;

        int randomSelection = r.nextInt(availableTypes.size());

        String randomType = availableTypes.get(randomSelection);
        return TetrominoFactory.getInstance(randomType, centerX);
    }

    private List<String> getTetrominoTypes() {
        return Arrays.asList("I", "J", "L", "O", "S", "T", "Z");
    }

}
