package model.tetromino;

import model.Position;
import model.Tile;

public class Tetromino {
    private Position position;
    private Tile[][] form;

    public Tetromino(int x, int y, Tile[][] form) {
        this.position = new Position(x, y);
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

    public Tile getUnmappedTile(Position coordinates) {
        if (inShape(coordinates)) {
            return form[coordinates.y][coordinates.x];
        } else {
            return Tile.OUT_OF_BOUNDS;
        }
    }

    public Tile getMappedTile(Position coordinates) {
        var unmappedCoordinates = coordinates.minus(position);
        return getUnmappedTile(unmappedCoordinates);
    }

    private boolean inShape(Position coordinates) {
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

    public Position getPosition() {
        return position;
    }
}
