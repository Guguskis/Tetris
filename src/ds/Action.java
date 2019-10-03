package ds;

public class Action {
    public static void moveDown(Grid grid, Shape shape) {
        shape.position.moveDown();
        if (Rules.hasCollided(grid, shape)) {
            shape.position.moveUp();
            Rules.imprintShape(grid, shape);
            shape.position.setPosition(0, 0);
            shape.reset(0, 0);
        }
    }

    public static void rotateShape(Grid grid, Shape shape) {
        shape.rotateClockwise();
        if (Rules.hasCollided(grid, shape)) {
            shape.rotateCounterClockwise();
        }
    }

    public static void moveRight(Grid grid, Shape shape) {
        shape.position.moveRight();
        if (Rules.hasCollided(grid, shape)) {
            shape.position.moveLeft();
        }
    }

    public static void moveLeft(Grid grid, Shape shape) {
        shape.position.moveLeft();
        if (Rules.hasCollided(grid, shape)) {
            shape.position.moveRight();
        }
    }
}
