package view.renderer;

import controller.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Grid;
import model.Position;
import model.Tile;
import model.tetromino.Tetromino;
import model.tetromino.TetrominoConveyor;

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
    private Position absoluteOffset = new Position(0, 0);


    public SimpleRenderer(GraphicsContext context, int width, int height, int scale) {
        this.context = context;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    @Override
    public void drawFrame(Grid grid, GameLogic gameLogic, TetrominoConveyor tetrominoManager) {
        Tetromino currentTetromino = tetrominoManager.getCurrent();
        Tetromino nextTetromino = tetrominoManager.getNext();

        fillBackground();
        drawOutlinedGridAndCurrentTetromino(grid, currentTetromino);
        drawNextTetromino(grid, nextTetromino);
        drawGameInformation(grid, gameLogic);
    }

    private void drawGameInformation(Grid grid, GameLogic gameLogic) {
        Position gridOffset = getGridOffset(grid);
        Position outlineOffset = getOutlineOffset();

        Position topLeftCorner = new Position(2, 6).plus(gridOffset).plus(outlineOffset);

        String scoreText = "Score: " + gameLogic.getScore();
        fillScaledText(topLeftCorner, scoreText, Color.YELLOW);

        int linesToSkip = 1;
        Position levelCoordinates = topLeftCorner.plus(0, linesToSkip);
        String levelText = "Level: " + gameLogic.getLevel();
        fillScaledText(levelCoordinates, levelText, Color.YELLOWGREEN);
    }

    private Position getOutlineOffset() {
        return new Position(2, 0);
    }

    private Position getGridOffset(Grid grid) {
        return new Position(grid.getWidth(), 0);
    }

    private void drawNextTetromino(Grid grid, Tetromino tetromino) {
        Position offset = getOutlineOffset().plus(getGridOffset(grid));
        Position topLeftCorner = new Position(2, 1).plus(offset);
        drawTetromino(topLeftCorner, tetromino, nextTetrominoColor);
    }

    private void drawOutlinedGridAndCurrentTetromino(Grid grid, Tetromino tetromino) {
        Position topLeftCorner = new Position(0, 0);
        drawOutline(grid, topLeftCorner);

        Position gridPositionOffset = new Position(1, 1);
        Position gridPosition = topLeftCorner.plus(gridPositionOffset);
        drawGrid(grid, gridPosition);

        Position tetrominoTopLeftCorner = gridPosition.plus(tetromino.getPosition());
        drawTetromino(tetrominoTopLeftCorner, tetromino, currentTetrominoColor);
    }

    private void drawOutline(Grid grid, Position topLeftCorner) {
        Position bottomRightCorner = topLeftCorner.plus(grid.getWidth() + 1, grid.getHeight() + 1);

        for (int y = topLeftCorner.y; y <= bottomRightCorner.y; y++) {
            for (int x = topLeftCorner.x; x <= bottomRightCorner.x; x++) {
                if (onOutline(topLeftCorner, bottomRightCorner, y, x)) {
                    drawScaledRect(new Position(x, y), outlineColor);
                }
            }
        }
    }

    private boolean onOutline(Position topLeftCorner, Position bottomRightCorner, int y, int x) {
        return y == topLeftCorner.y || y == bottomRightCorner.y || x == topLeftCorner.x || x == bottomRightCorner.x;
    }

    private void fillBackground() {
        drawRect(new Position(0, 0), width, height, backgroundColor);
    }

    private void drawTetromino(Position startPosition, Tetromino tetromino, Color color) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                Position currentPosition = new Position(j, i);
                if (tetromino.getUnmappedTile(currentPosition) == Tile.OCCUPIED) {
                    Position drawPosition = startPosition.plus(currentPosition);
                    drawScaledRect(drawPosition, color);
                }
            }
        }
    }

    private void drawGrid(Grid grid, Position topLeftCorner) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                if (grid.getTile(x, y) == Tile.OCCUPIED) {
                    Position drawPosition = topLeftCorner.plus(x, y);
                    drawScaledRect(drawPosition, gridColor);
                }
            }
        }
    }

    private void fillScaledText(Position position, String text, Color color) {
        position = position.plus(absoluteOffset);
        Position scaledPosition = position.multiply(scale);
        context.setFill(color);
        context.fillText(text, scaledPosition.x, scaledPosition.y);
    }

    private void drawScaledRect(Position position, Color color) {
        Position scaledWhere = getScaled(position.plus(absoluteOffset));
        drawRect(scaledWhere, scale, scale, color);
    }

    private Position getScaled(Position position) {
        return position.multiply(scale);
    }

    private void drawRect(Position where, int width, int height, Color color) {
        context.setFill(color);
        context.fillRect(where.x, where.y, width, height);
    }
}
