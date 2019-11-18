package view.renderer;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoManager;

public interface Renderer {
    void drawFrame(Grid grid, GameLogic gameLogic, TetrominoManager tetrominoManager);
}
