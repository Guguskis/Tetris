package view.renderer;

import controller.GameLogic;
import controller.TetrominoConveyor;
import model.Grid;

public interface Renderer {
    void drawFrame(Grid grid, GameLogic gameLogic, TetrominoConveyor conveyor);
}
