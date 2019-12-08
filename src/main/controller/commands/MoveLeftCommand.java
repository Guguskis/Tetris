package main.controller.commands;

import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.model.Grid;

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
