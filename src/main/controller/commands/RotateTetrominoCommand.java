package main.controller.commands;

import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.service.rotator.Rotator;
import main.model.Playfield;

public class RotateTetrominoCommand implements Command {
    private final Playfield playfield;
    private final Rotator rotator;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public RotateTetrominoCommand(Playfield playfield, Rotator rotator, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.playfield = playfield;
        this.rotator = rotator;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        rotator.clockwise(tetromino);
        if (detector.hasCollided(playfield, tetromino)) {
            rotator.counterClockwise(tetromino);
        }
    }
}
