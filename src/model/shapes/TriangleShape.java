package model.shapes;

import model.Tile;

public class TriangleShape extends Shape {

    public TriangleShape(int x, int y) {
        super(x, y, new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
    }
}
