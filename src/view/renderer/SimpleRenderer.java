package view.renderer;

import controller.commands.Score;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.shapes.Shape;
import model.Tile;

public class SimpleRenderer implements Renderer {

    private GraphicsContext context;
    private int scale;

    public SimpleRenderer(GraphicsContext context, int scale) {
        this.context = context;
        this.scale = scale;
    }

    private void drawShape(Position start, Shape shape, Color color) {
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                var tile = shape.getUnmappedTile(new Position(j, i));
                if (tile == Tile.Occupied) {
                    var offset = new Position(j, i).plus(shape.position);
                    var drawPosition = start.plus(offset);
                    drawScaledRect(drawPosition, color);
                }
            }
        }
    }


    private void drawGrid(Position start, Grid grid, Color color) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                var tile = grid.getTile(new Position(j, i));
                if (tile == Tile.Occupied) {
                    var drawPosition = start.plus(new Position(j, i));
                    drawScaledRect(drawPosition, color);
                }
            }
        }
    }

    @Override
    public void outline(Position start, int width, int height, Color color) {
        var end = new Position(start.plus(new Position(width - 1, height - 1)));

        for (int y = start.y; y <= end.y; y++) {
            for (int x = start.x; x <= end.x; x++) {
                if (y == start.y || y == end.y || x == start.x || x == end.x) {
                    drawScaledRect(new Position(x, y), color);
                }
            }
        }
    }

    @Override
    public void mainView(Position start, Shape shape, Grid grid) {
        drawShape(start, shape, Color.GREEN);
        drawGrid(start, grid, Color.GREY);
    }

    private Position getScaled(Position topLeftCorner) {
        return topLeftCorner.multiply(scale);
    }

    @Override
    public void score(Position start, Score score) {
        //Todo implement
    }

    @Override
    public void fillBackground(int width, int height, Color color) {
        drawRect(new Position(0, 0), width, height, color);
    }

    @Override
    public void nextShape(Position start, Shape shape) {
        //Todo implement
    }

    private void drawRect(Position where, int width, int height, Color color) {
        context.setFill(color);
        context.fillRect(where.x, where.y, width, height);
    }

    private void drawScaledRect(Position where, Color color) {
        var scaledWhere = getScaled(where);
        drawRect(scaledWhere, scale, scale, color);
    }
}
