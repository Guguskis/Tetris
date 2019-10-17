package view.renderer;

import controller.commands.Score;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.tetromino.Tetromino;

public interface Renderer {
    void outline(Position start, int width, int height, Color color);

    void mainView(Position start, Tetromino tetromino, Grid grid);

    void score(Position start, Score score);

    void fillBackground(int width, int height, Color color);

    void nextTetromino(Position start, Tetromino tetromino);

}
