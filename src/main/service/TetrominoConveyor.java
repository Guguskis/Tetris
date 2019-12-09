package main.service;


import main.model.Playfield;
import main.service.generator.TetrominoGenerator;
import main.model.tetromino.Tetromino;

public class TetrominoConveyor {
    private final Playfield playfield;
    private Tetromino current;
    private Tetromino next;
    private TetrominoGenerator generator;

    public TetrominoConveyor(Playfield playfield, TetrominoGenerator generator) {
        this.playfield = playfield;

        this.generator = generator;
        this.current = generator.getNext(getCenterX());
        this.next = generator.getNext(getCenterX());
    }

    private int getCenterX() {
        return playfield.getWidth() / 2;
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
