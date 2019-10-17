package model.shapes;

import model.Tile;

public class LeftLShape extends Shape {
    public LeftLShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Empty, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
