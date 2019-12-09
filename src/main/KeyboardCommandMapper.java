package main;

import javafx.scene.input.KeyCode;
import main.controller.commands.Command;
import main.controller.commands.MoveDownCommand;
import main.controller.commands.MoveLeftCommand;
import main.controller.commands.MoveRightCommand;
import main.controller.commands.RotateTetrominoCommand;
import main.model.GameState;
import main.model.Playfield;
import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.service.rotator.Rotator;

import java.util.EnumMap;

public class KeyboardCommandMapper {
    private Playfield playfield;
    private TetrominoConveyor conveyor;
    private CollisionDetector detector;
    private Rotator rotator;
    private GameState gameState;

    public KeyboardCommandMapper(Playfield playfield, TetrominoConveyor conveyor, CollisionDetector detector, Rotator rotator, GameState gameState) {
        this.playfield = playfield;
        this.conveyor = conveyor;
        this.detector = detector;
        this.rotator = rotator;
        this.gameState = gameState;
    }

    public EnumMap<KeyCode, Command> getCommands() {
        EnumMap<KeyCode, Command> commands = new EnumMap<KeyCode, Command>(KeyCode.class);

        var moveLeft = new MoveLeftCommand(playfield, conveyor, detector);
        commands.put(KeyCode.A, moveLeft);
        commands.put(KeyCode.LEFT, moveLeft);

        var moveRight = new MoveRightCommand(playfield, conveyor, detector);
        commands.put(KeyCode.D, moveRight);
        commands.put(KeyCode.RIGHT, moveRight);

        var moveDown = new MoveDownCommand(playfield, gameState, conveyor, detector);
        commands.put(KeyCode.S, moveDown);
        commands.put(KeyCode.DOWN, moveDown);

        var rotateTetromino = new RotateTetrominoCommand(playfield, rotator, conveyor, detector);
        commands.put(KeyCode.W, rotateTetromino);
        commands.put(KeyCode.UP, rotateTetromino);
        return commands;
    }

}
