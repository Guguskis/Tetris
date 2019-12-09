package main.controller.commands;

import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.model.Playfield;

public class MoveRightCommand implements Command {
    private final Playfield playfield;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveRightCommand(Playfield playfield, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.playfield = playfield;
        this.conveyor = conveyor;
        this.detector = detector;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveRight();
        if (detector.hasCollided(playfield, tetromino)) {
            tetromino.moveLeft();
        }
    }
}
