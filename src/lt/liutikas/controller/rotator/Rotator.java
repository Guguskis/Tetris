package lt.liutikas.controller.rotator;

import lt.liutikas.model.tetromino.Tetromino;

public interface Rotator {
    void clockwise(Tetromino tetromino);

    void counterClockwise(Tetromino tetromino);
}
