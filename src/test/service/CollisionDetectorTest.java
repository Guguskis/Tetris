package test.service;

import main.model.Playfield;
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
        Playfield playfield = new Playfield(10, 20);
        Tetromino tetromino = TetrominoFactory.getInstance(TetrominoType.I, playfield.getWidth() / 2);

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertFalse(actual);
    }

    @Test
    public void hasCollided_ReturnsTrue() {
        Playfield playfield = new Playfield(10, 20);
        for (int i = 0; i < playfield.getWidth(); i++) {
            playfield.setTile(new Vector2(i, 0), Tile.OCCUPIED);
        }
        Tetromino tetromino = TetrominoFactory.getInstance(TetrominoType.L, playfield.getWidth() / 2);

        boolean actual = detector.hasCollided(playfield, tetromino);

        assertTrue(actual);
    }

}