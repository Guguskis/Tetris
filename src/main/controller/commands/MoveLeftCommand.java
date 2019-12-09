package main.controller.commands;

import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.model.Playfield;

public class MoveLeftCommand implements Command {
    private final Playfield playfield;
    private final TetrominoConveyor conveyor;
    private final CollisionDetector detector;

    public MoveLeftCommand(Playfield playfield, TetrominoConveyor conveyor, CollisionDetector detector) {
        this.conveyor = conveyor;
        this.detector = detector;
        this.playfield = playfield;
    }

    @Override
    public void execute() {
        var tetromino = conveyor.getCurrent();

        tetromino.moveLeft();
        if (detector.hasCollided(playfield, tetromino)) {
            tetromino.moveRight();
        }
    }
}
