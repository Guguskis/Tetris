package test.service;

import main.model.Grid;
import main.model.Vector2;
import main.model.Tile;
import main.model.tetromino.Tetromino;
import main.model.tetromino.TetrominoFactory;
import main.model.tetromino.TetrominoType;
import main.service.CollisionDetector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionDetectorTest {
    private CollisionDetector detector = new CollisionDetector();

    @Test
    public void hasNotCollided_ReturnsFalse() {
        Grid grid = new Grid(10, 20);
        Tetromino tetromino = TetrominoFactory.getInstance(TetrominoType.I, grid.getWidth() / 2);

        boolean actual = detector.hasCollided(grid, tetromino);

        assertFalse(actual);
    }

    @Test
    public void hasCollided_ReturnsTrue() {
        Grid grid = new Grid(10, 20);
        for (int i = 0; i < grid.getWidth(); i++) {
            grid.setTile(new Vector2(i, 0), Tile.OCCUPIED);
        }
        Tetromino tetromino = TetrominoFactory.getInstance(TetrominoType.L, grid.getWidth() / 2);

        boolean actual = detector.hasCollided(grid, tetromino);

        assertTrue(actual);
    }

}