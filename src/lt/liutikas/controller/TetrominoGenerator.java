package lt.liutikas.controller;

import lt.liutikas.model.tetromino.Tetromino;

public interface TetrominoGenerator {
    Tetromino getNext(int x);
}
