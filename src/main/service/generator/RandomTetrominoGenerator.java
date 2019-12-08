package main.service.generator;

import main.model.tetromino.Tetromino;
import main.model.tetromino.TetrominoFactory;
import main.model.tetromino.TetrominoType;

import java.util.Random;

public class RandomTetrominoGenerator implements TetrominoGenerator {
    private Random random;

    public RandomTetrominoGenerator(Random random) {
        this.random = random;
    }

    private TetrominoType getRandomType() {
        TetrominoType[] values = TetrominoType.values();
        int randomSelection = random.nextInt(values.length);
        return values[randomSelection];
    }

    @Override
    public Tetromino getNext(int x) {
        return TetrominoFactory.getInstance(getRandomType(), x);
    }
}
