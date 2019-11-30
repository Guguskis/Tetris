package controller;

import model.tetromino.Tetromino;
import model.tetromino.TetrominoFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TetrominoGenerator {
    private Random random;

    public TetrominoGenerator(Random random) {
        this.random = random;
    }

    public Tetromino getRandom(int centerX) {
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
