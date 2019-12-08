package main.controller.commands;

import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.service.rotator.Rotator;
import main.model.Grid;

public class RotateTetrominoCommand implements Command {
    private final Grid grid;
    private final Rotator rotator;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public RotateTetrominoCommand(Grid grid, Rotator rotator, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.grid = grid;
        this.rotator = rotator;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        rotator.clockwise(tetromino);
        if (detector.hasCollided(grid, tetromino)) {
            rotator.counterClockwise(tetromino);
        }
    }
}
