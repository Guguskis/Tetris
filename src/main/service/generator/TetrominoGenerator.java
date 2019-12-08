package main.service.generator;

import main.model.tetromino.Tetromino;

public interface TetrominoGenerator {
    Tetromino getNext(int x);
}
