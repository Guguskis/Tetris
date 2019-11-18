package view.renderer;

import controller.GameLogic;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.tetromino.Tetromino;

public interface Renderer {
    void outline(Position start, int width, int height, Color color);

    void mainView(Position start, Tetromino tetromino, Grid grid);

    void gameInformation(Position start, GameLogic gameLogic);

    void fillBackground(int width, int height, Color color);

    void nextTetromino(Position start, Tetromino tetromino);

}
