package lt.liutikas.controller.commands;

import lt.liutikas.service.CollisionDetector;
import lt.liutikas.service.TetrominoConveyor;
import lt.liutikas.model.Grid;

public class MoveRightCommand implements Command {
    private final Grid grid;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveRightCommand(Grid grid, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.grid = grid;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveRight();
        if (detector.hasCollided(grid, tetromino)) {
            tetromino.moveLeft();
        }
    }
}
