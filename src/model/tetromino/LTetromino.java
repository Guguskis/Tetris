package model.tetromino;

import model.Tile;

public class LTetromino extends Tetromino {
    public LTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.EMPTY, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }
}
