package model.tetromino;

import model.Tile;

public class JTetromino extends Tetromino {
    public JTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.Occupied, Tile.Empty, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
