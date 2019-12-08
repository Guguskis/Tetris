package lt.liutikas.controller.commands;

import lt.liutikas.service.CollisionDetector;
import lt.liutikas.service.TetrominoConveyor;
import lt.liutikas.model.Grid;

public class MoveLeftCommand implements Command {
    private final Grid grid;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveLeftCommand(Grid grid, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.conveyor = conveyor;
        this.detector = detector;
        this.grid = grid;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveLeft();
        if (detector.hasCollided(grid, tetromino)) {
            tetromino.moveRight();
        }
    }
}
