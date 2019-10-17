package model.shapes;


import model.Position;

import java.util.Arrays;
import java.util.List;

public class ShapeFactory {


    public static Shape makeRandom(int x, int y){
        var shapes = getAvailableShapes(x, y);
        int randomOption = (int) (Math.random() * (shapes.size() - 1));
        return shapes.get(randomOption);
    }

    private static List<Shape> getAvailableShapes(int x, int y) {
        return Arrays.asList(
                new LeftLShape(x, y),
                new RightLShape(x, y),
                new LeftZShape(x, y),
                new RightZShape(x, y),
                new LongShape(x, y),
                new SquareShape(x, y),
                new TriangleShape(x, y)
        );
    }
}
