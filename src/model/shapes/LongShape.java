package model.shapes;

import model.Tile;

public class LongShape extends Shape {
    public LongShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
