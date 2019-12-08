package main.model;

import main.model.tetromino.Tetromino;

public class Grid {
    private Tile[][] map;

    public Grid(int width, int height) {
        this.map = new Tile[height][width];
    }

    public Grid(Grid grid) {
        this.map = grid.map;
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    private boolean inBounds(Position coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    public Tile getTile(Position coordinates) {
        if (inBounds(coordinates)) {
            return map[coordinates.y][coordinates.x];
        } else if (coordinates.y < 0) {
            return Tile.EMPTY;
        }
        return Tile.OUT_OF_BOUNDS;
    }

    private void setTile(Position coordinates, Tile tile) {
        if (inBounds(coordinates)) {
            map[coordinates.y][coordinates.x] = tile;
        }
    }

    private boolean lineIsFull(int y) {
        for (int x = 0; x < getWidth(); x++) {
            if (getTile(new Position(x, y)) != Tile.OCCUPIED) {
                return false;
            }
        }
        return true;
    }

    private void removeLine(int y) {
        for (int x = 0; x < getWidth(); x++) {
            setTile(new Position(x, y), Tile.EMPTY);
        }
    }

    private void pushDownLinesAbove(int y) {
        for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
            pushLineDown(lineAbove);
        }
    }

    private void pushLineDown(int y) {
        for (int x = 0; x < getWidth(); x++) {
            var tile = getTile(new Position(x, y));
            setTile(new Position(x, y + 1), tile);
        }
        removeLine(y);
    }

    public Tile getTile(int x, int y) {
        return getTile(new Position(x, y));
    }

    private void imprint(Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var coordinates = new Position(j, i);
                var tile = tetromino.getUnmappedTile(coordinates);

                if (tile == Tile.OCCUPIED) {
                    var mappedCoordinates = coordinates.plus(tetromino.getPosition());
                    setTile(mappedCoordinates, tile);
                }
            }
        }
    }

    public int getFullLineCount(Tetromino tetromino) {
        int count = 0;
        Grid gridCopy = new Grid(this);
        gridCopy.imprint(tetromino);

        for (int y = 0; y < gridCopy.getHeight(); y++) {
            if (gridCopy.lineIsFull(y)) {
                count++;
            }
        }

        return count;
    }

    public void removeFullLines(Tetromino tetromino) {
        imprint(tetromino);

        var top = getHeight() - 1;
        for (int y = top; y >= 0; ) {
            if (lineIsFull(y)) {
                removeLine(y);
                pushDownLinesAbove(y);
            } else {
                y--;
            }
        }
    }
}
