package main.model.tetromino;

import main.model.Vector2;
import main.model.Tile;

public class Tetromino {
    private Vector2 position;
    private Tile[][] form;

    public Tetromino(int x, int y, Tile[][] form) {
        this.position = new Vector2(x, y);
        this.form = form;
    }

    public int getWidth() {
        return form[0].length;
    }

    public int getHeight() {
        return form.length;
    }

    public Tile getTile(int x, int y) {
        return form[x][y];
    }

    public void setForm(Tile[][] form) {
        this.form = form;
    }

    public Tile getUnmappedTile(Vector2 coordinates) {
        if (inShape(coordinates)) {
            return form[coordinates.y][coordinates.x];
        } else {
            return Tile.OUT_OF_BOUNDS;
        }
    }

    public Tile getMappedTile(Vector2 coordinates) {
        var unmappedCoordinates = coordinates.minus(position);
        return getUnmappedTile(unmappedCoordinates);
    }

    private boolean inShape(Vector2 coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    public void moveLeft() {
        position.x--;
    }

    public void moveRight() {
        position.x++;
    }

    public void moveDown() {
        position.y++;
    }

    public void moveUp() {
        position.y--;
    }

    public Vector2 getPosition() {
        return position;
    }
}
