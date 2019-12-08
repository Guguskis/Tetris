package main.service;


import main.model.Grid;
import main.service.generator.TetrominoGenerator;
import main.model.tetromino.Tetromino;

public class TetrominoConveyor {
    private final Grid grid;
    private Tetromino current;
    private Tetromino next;
    private TetrominoGenerator generator;

    public TetrominoConveyor(Grid grid, TetrominoGenerator generator) {
        this.grid = grid;

        this.generator = generator;
        this.current = generator.getNext(getCenterX());
        this.next = generator.getNext(getCenterX());
    }

    private int getCenterX() {
        return grid.getWidth() / 2;
    }

    public Tetromino getNext() {
        return next;
    }

    public Tetromino getCurrent() {
        return current;
    }

    public void move() {
        current = next;
        next = generator.getNext(getCenterX());
    }
}
