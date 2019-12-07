package lt.liutikas.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lt.liutikas.controller.commands.Command;
import lt.liutikas.controller.commands.MoveDownCommand;
import lt.liutikas.controller.commands.MoveLeftCommand;
import lt.liutikas.controller.commands.MoveRightCommand;
import lt.liutikas.controller.commands.RotateTetrominoCommand;
import lt.liutikas.controller.rotator.Rotator;
import lt.liutikas.model.Grid;
import lt.liutikas.renderer.Renderer;

import java.util.EnumMap;

public class KeyboardInput {
    private Renderer renderer;
    private Grid grid;
    private GameState gameState;
    private TetrominoConveyor conveyor;
    private CollisionDetector detector;
    private Rotator rotator;

    private EnumMap<KeyCode, Command> commands = new EnumMap<>(KeyCode.class);

    public KeyboardInput(Renderer renderer, Grid grid, GameState gameState, TetrominoConveyor conveyor, CollisionDetector detector, Rotator rotator) {
        this.renderer = renderer;
        this.grid = grid;
        this.gameState = gameState;
        this.conveyor = conveyor;
        this.detector = detector;
        this.rotator = rotator;
        prepareCommands();
    }

    public void handle(KeyEvent key) {
        if (gameState.isGameOver()) {
            return;
        }

        var command = commands.get(key.getCode());
        if (command != null) {
            command.execute();
            renderer.drawFrame();
        }
    }

    private void prepareCommands() {
        var moveLeft = new MoveLeftCommand(grid, conveyor, detector);
        add(moveLeft, KeyCode.A);
        add(moveLeft, KeyCode.LEFT);

        var moveRight = new MoveRightCommand(grid, conveyor, detector);
        add(moveRight, KeyCode.D);
        add(moveRight, KeyCode.RIGHT);

        var moveDown = new MoveDownCommand(grid, gameState, conveyor, detector);
        add(moveDown, KeyCode.S);
        add(moveDown, KeyCode.DOWN);

        var rotateTetromino = new RotateTetrominoCommand(grid, rotator, conveyor, detector);
        add(rotateTetromino, KeyCode.W);
        add(rotateTetromino, KeyCode.UP);
    }

    private void add(Command command, KeyCode a) {
        commands.put(a, command);
    }
}
