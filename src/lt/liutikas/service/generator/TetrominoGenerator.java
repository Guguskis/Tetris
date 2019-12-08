package lt.liutikas.service.generator;

import lt.liutikas.model.tetromino.Tetromino;

public interface TetrominoGenerator {
    Tetromino getNext(int x);
}
