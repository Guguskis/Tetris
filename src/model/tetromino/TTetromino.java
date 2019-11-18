package model.tetromino;

import model.Tile;

public class TTetromino extends Tetromino {

    public TTetromino(int x) {
        super(x-1, 0, new Tile[][]{
                {Tile.EMPTY, Tile.OCCUPIED, Tile.EMPTY},
                {Tile.OCCUPIED, Tile.OCCUPIED, Tile.OCCUPIED},
        });
    }
}
