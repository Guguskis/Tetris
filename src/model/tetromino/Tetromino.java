package model.tetromino;

import model.Position;
import model.Tile;

public class Tetromino {
    public Position position;
    public Tile[][] form;

    protected Tetromino(int x, int y, Tile[][] form) {
        this.position = new Position(x, y);
        this.form = form;
    }

    public int getWidth() {
        return form[0].length;
    }

    public int getHeight() {
        return form.length;
    }

    public Tile getUnmappedTile(Position coordinates) {
        if (inShape(coordinates)) {
            return form[coordinates.y][coordinates.x];
        } else {
            return Tile.OutOfBounds;
        }
    }

    public Tile getMappedTile(Position coordinates) {
        var unmappedCoordinates = coordinates.minus(position);
        return getUnmappedTile(unmappedCoordinates);
    }

    public boolean inShape(Position coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    public void rotateClockwise() {
        var newForm = new Tile[getWidth()][getHeight()];

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                newForm[j][i] = form[getHeight() - i - 1][j];
            }
        }

        form = newForm;
    }

    public void rotateCounterClockwise() {
        for (int i = 0; i < 3; i++) {
            rotateClockwise();
        }
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
}
