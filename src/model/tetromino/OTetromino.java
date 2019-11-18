package model.tetromino;

import model.Tile;

public class OTetromino extends Tetromino {
    public OTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED},
                {Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }
}
