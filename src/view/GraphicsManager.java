package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.Shape;

public class GraphicsManager {

    private GraphicsContext context;
    private int scale;

    public GraphicsManager(GraphicsContext context, int scale) {
        this.context = context;
        this.scale = scale;
    }

    public void drawScene(Shape shape, Grid grid) {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, 2000, 2000);

        drawShape(shape, Color.GREEN);
        drawGrid(grid, Color.AZURE);
    }

    private void drawShape(Shape shape, Color color) {
        context.setFill(color);
        for (int i = 0; i < shape.getHeight(); i++) {
            for (int j = 0; j < shape.getWidth(); j++) {
                if (shape.getUnmappedTile(new Position(j, i)) == 1) {
                    var x = (j + 1 + shape.position.x) * scale;
                    var y = (i + 1 + shape.position.y) * scale;
                    context.fillRect(x, y, scale, scale);
                }
            }
        }
    }

    private void drawGrid(Grid grid, Color color) {
        for (int i = -1; i < grid.getHeight() + 1; i++) {
            for (int j = -1; j < grid.getWidth() + 1; j++) {
                var tile = grid.getTile(new Position(j, i));
                if (tile == 1 || tile == -1) {
                    if (tile == 1) {
                        context.setFill(color);

                    } else if (tile == -1) {
                        context.setFill(Color.RED);
                    }
                    var x = (j + 1) * scale;
                    var y = (i + 1) * scale;
                    context.fillRect(x, y, scale, scale);
                }
            }
        }
    }
}
