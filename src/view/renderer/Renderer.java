package view.renderer;

import controller.GameLogic;
import model.Grid;
import model.tetromino.TetrominoConveyor;

public interface Renderer {
    void drawFrame(Grid grid, GameLogic gameLogic, TetrominoConveyor conveyor);
}
