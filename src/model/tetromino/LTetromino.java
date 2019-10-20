package model.tetromino;

import model.Tile;

public class LTetromino extends Tetromino {
    public LTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Empty, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
