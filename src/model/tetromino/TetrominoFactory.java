package model.tetromino;


import java.util.Arrays;
import java.util.List;

public class TetrominoFactory {
    public TetrominoFactory() {
    }

    public Tetromino makeRandom(int x, int y) {
        var shapes = getAvailableShapes(x, y);
        int randomOption = (int) (Math.random() * (shapes.size() - 1));
        return shapes.get(randomOption);
    }

    private List<Tetromino> getAvailableShapes(int x, int y) {
        return Arrays.asList(
                new LeftLTetromino(x, y),
                new RightLTetromino(x, y),
                new LeftZTetromino(x, y),
                new RightZTetromino(x, y),
                new LongTetromino(x, y),
                new SquareTetromino(x, y),
                new TriangleTetromino(x, y)
        );
    }
}
