package model.shapes;

import model.Tile;

public class RightLShape extends Shape {
    public RightLShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Occupied, Tile.Empty, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
