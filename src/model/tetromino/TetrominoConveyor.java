package model.tetromino;


import model.Grid;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetrominoConveyor {
    private final Grid grid;
    private Tetromino current;
    private Tetromino next;
    private Random random;

    public TetrominoConveyor(Grid grid, Random random) {
        this.grid = grid;
        injectRandomAndGenerateValues(random);
    }

    private void injectRandomAndGenerateValues(Random random) {
        this.random = random;
        this.current = getRandom();
        this.next = getRandom();
    }

    public Tetromino getNext() {
        return next;
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void move() {
        current = next;
        next = getRandom();
    }

    private Tetromino getRandom() {
        int centerX = grid.getWidth() / 2;
        String randomType = getRandomType();
        return TetrominoFactory.getInstance(randomType, centerX);
    }

    private String getRandomType() {
        List<String> availableTypes = getTetrominoTypes();
        int randomSelection = random.nextInt(availableTypes.size());
        return availableTypes.get(randomSelection);
    }

    private List<String> getTetrominoTypes() {
        return Arrays.asList("I", "J", "L", "O", "S", "T", "Z");
    }

}
