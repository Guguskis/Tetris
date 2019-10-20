package model.tetromino;

import model.Tile;

public class ITetromino extends Tetromino {
    public ITetromino(int x) {
        super(x-2, 0, new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Occupied, Tile.Occupied}
        });
    }
}
