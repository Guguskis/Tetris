package view.renderer;

import controller.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;
import model.tetromino.TetrominoManager;

public class SimpleRenderer implements Renderer {

    private GraphicsContext context;
    private int width;
    private int height;
    private int scale;

    private Color backgroundColor = Color.rgb(30, 0, 40);
    private Color outlineColor = Color.rgb(125, 190, 80);
    private Color currentTetrominoColor = Color.GREEN;
    private Color gridColor = Color.GREY;
    private Color nextTetrominoColor = Color.PURPLE;


    public SimpleRenderer(GraphicsContext context, int width, int height, int scale) {
        this.context = context;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    private void drawTetromino(Position start, Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var tile = tetromino.getUnmappedTile(new Position(j, i));
                if (tile == Tile.OCCUPIED) {
                    var offset = new Position(j, i).plus(tetromino.getPosition());
                    var drawPosition = start.plus(offset);
                    drawScaledRect(drawPosition, currentTetrominoColor);
                }
            }
        }
    }

    private void drawGrid(Position start, Grid grid) {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                var tile = grid.getTile(new Position(j, i));
                if (tile == Tile.OCCUPIED) {
                    var drawPosition = start.plus(new Position(j, i));
                    drawScaledRect(drawPosition, gridColor);
                }
            }
        }
    }

    private void outline(Grid grid) {
        int outlineWidth = grid.getWidth() + 1;
        int outlineHeight = grid.getHeight() + 1;

        Position start = new Position(0, 0);
        var end = start.plus(new Position(outlineWidth, outlineHeight));


        for (int y = start.y; y <= end.y; y++) {
            for (int x = start.x; x <= end.x; x++) {
                if (y == start.y || y == end.y || x == start.x || x == end.x) {
                    drawScaledRect(new Position(x, y), outlineColor);
                }
            }
        }
    }

    private void mainView(Grid grid, TetrominoManager manager) {
        Tetromino tetromino = manager.getCurrent();
        Position start = new Position(1, 1);

        drawTetromino(start, tetromino);
        drawGrid(start, grid);
    }

    private Position getScaled(Position topLeftCorner) {
        return topLeftCorner.multiply(scale);
    }

    private void gameInformation(Grid grid, GameLogic gameLogic) {
        Position start = new Position(grid.getWidth() + 4, 7);

        var scoreCoordinates = start;
        var scoreText = "Score: " + gameLogic.getScore();
        fillScaledText(scoreCoordinates, scoreText, Color.YELLOW);

        var levelCoordinates = start.plus(new Position(0, 1));
        var levelText = "Level: " + gameLogic.getLevel();
        fillScaledText(levelCoordinates, levelText, Color.YELLOWGREEN);

    }

    private void fillBackground() {
        drawRect(new Position(0, 0), width, height, backgroundColor);
    }

    private void nextTetromino(Grid grid, TetrominoManager manager) {
        Tetromino tetromino = manager.getNext();
        var start = new Position(grid.getWidth() + 4, 1);

        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                if (tetromino.getUnmappedTile(new Position(j, i)) == Tile.OCCUPIED) {
                    var drawPosition = start.plus(new Position(j, i));
                    drawScaledRect(drawPosition, nextTetrominoColor);
                }
            }
        }
    }

    @Override
    public void drawFrame(Grid grid, GameLogic gameLogic, TetrominoManager tetrominoManager) {
        fillBackground();
        outline(grid);
        mainView(grid, tetrominoManager);
        nextTetromino(grid, tetrominoManager);
        gameInformation(grid, gameLogic);
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
