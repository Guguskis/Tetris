package model.tetromino;

import model.Tile;

public class JTetromino extends Tetromino {
    public JTetromino(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Empty, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
