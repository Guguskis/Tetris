package model;

import java.util.ArrayList;

public class Shape {
    public Position position;
    public Tile[][] form;

    public Shape(int x, int y) {
        this.position = new Position(x, y);
        this.form = getRandomForm();
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

    private Tile[][] getRandomForm() {
        var shapes = new ArrayList<Tile[][]>();

        shapes.add(new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
        shapes.add(new Tile[][]{
                {Tile.Occupied, Tile.Empty, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
        shapes.add(new Tile[][]{
                {Tile.Empty, Tile.Empty, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
        shapes.add(new Tile[][]{
                {Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied},
        });
        shapes.add(new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
        });
        shapes.add(new Tile[][]{
                {Tile.Empty, Tile.Occupied, Tile.Empty},
                {Tile.Occupied, Tile.Occupied, Tile.Occupied},
        });
        shapes.add(new Tile[][]{
                {Tile.Occupied, Tile.Occupied, Tile.Empty},
                {Tile.Empty, Tile.Occupied, Tile.Occupied},
        });

        int randomOption = (int) (Math.random() * (shapes.size() - 1));
        var randomShape = shapes.get(randomOption);

        return randomShape;
    }

    public void reset(int x, int y) {
        position.setPosition(x, y);
        form = getRandomForm();
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
}
