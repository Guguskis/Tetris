package model.tetromino;

import model.Tile;

public class OTetromino extends Tetromino {
    public OTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied},
        });
    }
}
