package main.controller.renderer;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.model.GameState;
import main.service.TetrominoConveyor;
import main.model.Grid;
import main.model.Vector2;
import main.model.Tile;
import main.model.tetromino.Tetromino;

public class DefaultRenderer implements Renderer {

    private int width = 800;
    private int height = 800;
    private int scale = 20;

    private GraphicsContext context;
    private Grid grid;
    private GameState gameState;
    private TetrominoConveyor conveyor;

    private Color backgroundColor = Color.rgb(30, 0, 40);
    private Color outlineColor = Color.rgb(125, 190, 80);
    private Color currentTetrominoColor = Color.GREEN;
    private Color gridColor = Color.GREY;
    private Color nextTetrominoColor = Color.PURPLE;
    private Vector2 absoluteOffset = new Vector2(0, 0);


    public DefaultRenderer(Group root, Grid grid, GameState gameState, TetrominoConveyor conveyor) {
        this.context = getGraphicsContext(root);
        this.grid = grid;
        this.gameState = gameState;
        this.conveyor = conveyor;
    }

    private GraphicsContext getGraphicsContext(Group root) {
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        return canvas.getGraphicsContext2D();
    }

    @Override
    public void drawFrame() {
        fillBackground();
        drawOutlinedGridAndCurrentTetromino(grid, conveyor.getCurrent());
        drawNextTetromino(grid, conveyor.getNext());
        drawGameInformation(grid, gameState);
    }

    private void drawGameInformation(Grid grid, GameState gameState) {
        Vector2 gridOffset = getGridOffset(grid);
        Vector2 outlineOffset = getOutlineOffset();

        Vector2 topLeftCorner = new Vector2(2, 6).plus(gridOffset).plus(outlineOffset);

        String scoreText = "Score: " + gameState.getScore();
        fillScaledText(topLeftCorner, scoreText, Color.YELLOW);

        int linesToSkip = 1;
        Vector2 levelCoordinates = topLeftCorner.plus(0, linesToSkip);
        String levelText = "Level: " + gameState.getLevel();
        fillScaledText(levelCoordinates, levelText, Color.YELLOWGREEN);
    }

    private Vector2 getOutlineOffset() {
        return new Vector2(2, 0);
    }

    private Vector2 getGridOffset(Grid grid) {
        return new Vector2(grid.getWidth(), 0);
    }

    private void drawNextTetromino(Grid grid, Tetromino tetromino) {
        Vector2 offset = getOutlineOffset().plus(getGridOffset(grid));
        Vector2 topLeftCorner = new Vector2(2, 1).plus(offset);
        drawTetromino(topLeftCorner, tetromino, nextTetrominoColor);
    }

    private void drawOutlinedGridAndCurrentTetromino(Grid grid, Tetromino tetromino) {
        Vector2 topLeftCorner = new Vector2(0, 0);
        drawOutline(grid, topLeftCorner);

        Vector2 gridPositionOffset = new Vector2(1, 1);
        Vector2 gridPosition = topLeftCorner.plus(gridPositionOffset);
        drawGrid(grid, gridPosition);

        Vector2 tetrominoTopLeftCorner = gridPosition.plus(tetromino.getPosition());
        drawTetromino(tetrominoTopLeftCorner, tetromino, currentTetrominoColor);
    }

    private void drawOutline(Grid grid, Vector2 topLeftCorner) {
        Vector2 bottomRightCorner = topLeftCorner.plus(grid.getWidth() + 1, grid.getHeight() + 1);

        for (int y = topLeftCorner.y; y <= bottomRightCorner.y; y++) {
            for (int x = topLeftCorner.x; x <= bottomRightCorner.x; x++) {
                if (onOutline(topLeftCorner, bottomRightCorner, y, x)) {
                    drawScaledRect(new Vector2(x, y), outlineColor);
                }
            }
        }
    }

    private boolean onOutline(Vector2 topLeftCorner, Vector2 bottomRightCorner, int y, int x) {
        return y == topLeftCorner.y || y == bottomRightCorner.y || x == topLeftCorner.x || x == bottomRightCorner.x;
    }

    private void fillBackground() {
        drawRect(new Vector2(0, 0), width, height, backgroundColor);
    }

    private void drawTetromino(Vector2 startPosition, Tetromino tetromino, Color color) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                Vector2 currentPosition = new Vector2(j, i);
                if (tetromino.getUnmappedTile(currentPosition) == Tile.OCCUPIED) {
                    Vector2 drawPosition = startPosition.plus(currentPosition);
                    drawScaledRect(drawPosition, color);
                }
            }
        }
    }

    private void drawGrid(Grid grid, Vector2 topLeftCorner) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.getTile(x, y) == Tile.OCCUPIED) {
                    Vector2 drawPosition = topLeftCorner.plus(x, y);
                    drawScaledRect(drawPosition, gridColor);
                }
            }
        }
    }

    private void fillScaledText(Vector2 position, String text, Color color) {
        position = position.plus(absoluteOffset);
        Vector2 scaledPosition = position.multiply(scale);
        context.setFill(color);
        context.fillText(text, scaledPosition.x, scaledPosition.y);
    }

    private void drawScaledRect(Vector2 position, Color color) {
        Vector2 scaledWhere = getScaled(position.plus(absoluteOffset));
        drawRect(scaledWhere, scale, scale, color);
    }

    private Vector2 getScaled(Vector2 position) {
        return position.multiply(scale);
    }

    private void drawRect(Vector2 where, int width, int height, Color color) {
        context.setFill(color);
        context.fillRect(where.x, where.y, width, height);
    }
}
