package main.model;

import main.model.tetromino.Tetromino;

public class Playfield {
    private Tile[][] tiles;

    public Playfield(int width, int height) {
        this.tiles = new Tile[height][width];
    }

    public Playfield(Playfield playfield) {
        this.tiles = playfield.tiles;
    }

    public Playfield(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }

    private boolean inBounds(Vector2 coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    public Tile getTile(Vector2 coordinates) {
        if (inBounds(coordinates)) {
            return tiles[coordinates.y][coordinates.x];
        } else if (coordinates.y < 0) {
            return Tile.EMPTY;
        }
        return Tile.OUT_OF_BOUNDS;
    }

    public void setTile(Vector2 coordinates, Tile tile) {
        if (inBounds(coordinates)) {
            tiles[coordinates.y][coordinates.x] = tile;
        }
    }

    private boolean lineIsFull(int y) {
        for (int x = 0; x < getWidth(); x++) {
            if (getTile(new Vector2(x, y)) != Tile.OCCUPIED) {
                return false;
            }
        }
        return true;
    }

    private void removeLine(int y) {
        for (int x = 0; x < getWidth(); x++) {
            setTile(new Vector2(x, y), Tile.EMPTY);
        }
    }

    private void pushDownLinesAbove(int y) {
        for (int lineAbove = y - 1; lineAbove >= 0; lineAbove--) {
            pushLineDown(lineAbove);
        }
    }

    private void pushLineDown(int y) {
        for (int x = 0; x < getWidth(); x++) {
            var tile = getTile(new Vector2(x, y));
            setTile(new Vector2(x, y + 1), tile);
        }
        removeLine(y);
    }

    public Tile getTile(int x, int y) {
        return getTile(new Vector2(x, y));
    }

    public void imprint(Tetromino tetromino) {
        for (int i = 0; i < tetromino.getHeight(); i++) {
            for (int j = 0; j < tetromino.getWidth(); j++) {
                var coordinates = new Vector2(j, i);
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
        Playfield playfieldCopy = new Playfield(this);
        playfieldCopy.imprint(tetromino);

        for (int y = 0; y < playfieldCopy.getHeight(); y++) {
            if (playfieldCopy.lineIsFull(y)) {
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
