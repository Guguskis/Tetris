package model.tetromino;

import model.Tile;

public class RightZTetromino extends Tetromino {
    public RightZTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
        });
    }
}
