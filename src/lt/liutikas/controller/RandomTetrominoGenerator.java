package lt.liutikas.controller;

import lt.liutikas.model.tetromino.Tetromino;
import lt.liutikas.model.tetromino.TetrominoFactory;
import lt.liutikas.model.tetromino.Type;

import java.util.Random;

public class RandomTetrominoGenerator implements TetrominoGenerator {
    private Random random;

    public RandomTetrominoGenerator(Random random) {
        this.random = random;
    }

    private Type getRandomType() {
        Type[] values = Type.values();
        int randomSelection = random.nextInt(values.length);
        return values[randomSelection];
    }

    @Override
    public Tetromino getNext(int x) {
        return TetrominoFactory.getInstance(getRandomType(), x);
    }
}
