package view.Renderer;

import controller.commands.Score;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.Shape;

public interface Renderer {
    void outline(Position start, int width, int height, Color color);

    void mainView(Position start, Shape shape, Grid grid);

    void score(Position start, Score score);

    void fillBackground(int width, int height, Color color);

    void nextShape(Position start, Shape shape);

}
