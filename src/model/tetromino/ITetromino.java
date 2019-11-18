package model.tetromino;

import model.Tile;

public class ITetromino extends Tetromino {
    public ITetromino(int x) {
        super(x-2, 0, new Tile[][]{
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED}
        });
    }
}
