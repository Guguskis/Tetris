package view.renderer;

import controller.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;

public class SimpleRenderer implements Renderer {

    private GraphicsContext context;
    private int scale;

    public SimpleRenderer(GraphicsContext context, int scale) {
        this.context = context;
        this.scale = scale;
    }

    private void drawTetromino(Position start, Tetromino tetromino, Color color) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var tile = tetromino.getUnmappedTile(new Position(j, i));
                if (tile == Tile.Occupied) {
                    var offset = new Position(j, i).plus(tetromino.getPosition());
                    var drawPosition = start.plus(offset);
                    drawScaledRect(drawPosition, Color.KHAKI);
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
    public void mainView(Position start, Tetromino tetromino, Grid grid) {
        drawTetromino(start, tetromino, Color.GREEN);
        drawGrid(start, grid, Color.GREY);
    }

    private Position getScaled(Position topLeftCorner) {
        return topLeftCorner.multiply(scale);
    }

    @Override
    public void gameInformation(Position start, GameLogic gameLogic) {
        var scoreCoordinates = start;
        var scoreText = "Score: " + gameLogic.getScore();
        fillScaledText(scoreCoordinates, scoreText, Color.YELLOW);

        var levelCoordinates = start.plus(new Position(0, 2));
        var levelText = "Level: " + gameLogic.getLevel();
        fillScaledText(levelCoordinates, levelText, Color.YELLOWGREEN);

    }

    @Override
    public void fillBackground(int width, int height, Color color) {
        drawRect(new Position(0, 0), width, height, color);
    }

    @Override
    public void nextTetromino(Position start, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                if (tetromino.getUnmappedTile(new Position(j, i)) == Tile.Occupied) {
                    var drawPosition = start.plus(new Position(j, i));
                    drawScaledRect(drawPosition, Color.PURPLE);
                }
            }
        }
    }

    private void drawRect(Position where, int width, int height, Color color) {
        context.setFill(color);
        context.fillRect(where.x, where.y, width, height);
    }

    private void drawScaledRect(Position where, Color color) {
        var scaledWhere = getScaled(where);
        drawRect(scaledWhere, scale, scale, color);
    }

    private void fillScaledText(Position where, String text, Color color) {
        context.setFill(color);
        context.fillText(text, where.x * scale, where.y * scale);
    }
}
