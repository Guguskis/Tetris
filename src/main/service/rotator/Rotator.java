package main.service.rotator;

import main.model.tetromino.Tetromino;

public interface Rotator {
    void clockwise(Tetromino tetromino);

    void counterClockwise(Tetromino tetromino);
}
