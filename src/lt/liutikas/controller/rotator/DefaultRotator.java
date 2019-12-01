package lt.liutikas.controller.rotator;

import lt.liutikas.model.Tile;
import lt.liutikas.model.tetromino.Tetromino;

public class DefaultRotator implements Rotator {
    @Override
    public void clockwise(Tetromino tetromino) {
        var width = tetromino.getWidth();
        var height = tetromino.getHeight();
        var rotatedForm = new Tile[width][height];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rotatedForm[j][i] = tetromino.getTile(height - i - 1, j);
            }
        }

        tetromino.setForm(rotatedForm);
    }


    @Override
    public void counterClockwise(Tetromino tetromino) {
        for (int i = 0; i < 3; i++) {
            clockwise(tetromino);
        }
    }
}
