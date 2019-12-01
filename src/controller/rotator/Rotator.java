package controller.rotator;

import model.tetromino.Tetromino;

public interface Rotator {
    void clockwise(Tetromino tetromino);

    void counterClockwise(Tetromino tetromino);
}
