package ds;

import java.util.ArrayList;

public class Shape {
    public Position position;
    public int[][] form;

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

    public int getUnmappedTile(Position coordinates) {
        if (inShape(coordinates)) {
            return form[coordinates.y][coordinates.x];
        } else {
            return -1;
        }
    }

    public int getMappedTile(Position coordinates) {
        var unmappedCoordinates = coordinates.minus(position);
        return getUnmappedTile(unmappedCoordinates);
    }

    public boolean inShape(Position coordinates) {
        var inXAxis = coordinates.x >= 0 && coordinates.x < getWidth();
        var inYAxis = coordinates.y >= 0 && coordinates.y < getHeight();
        return inXAxis && inYAxis;
    }

    private int[][] getRandomForm() {
        var shapes = new ArrayList<int[][]>();

        shapes.add(new int[][]{
                {1, 1, 1, 1},
        });
        shapes.add(new int[][]{
                {1, 0, 0},
                {1, 1, 1},
        });
        shapes.add(new int[][]{
                {0, 0, 1},
                {1, 1, 1},
        });
        shapes.add(new int[][]{
                {1, 1},
                {1, 1},
        });
        shapes.add(new int[][]{
                {0, 1, 1},
                {1, 1, 0},
        });
        shapes.add(new int[][]{
                {0, 1, 0},
                {1, 1, 1},
        });
        shapes.add(new int[][]{
                {1, 1, 0},
                {0, 1, 1},
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
        var newForm = new int[getWidth()][getHeight()];

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
