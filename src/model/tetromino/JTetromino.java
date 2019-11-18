package model.tetromino;

import model.Tile;

public class JTetromino extends Tetromino {
    public JTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.EMPTY, Tile.EMPTY},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }
}
