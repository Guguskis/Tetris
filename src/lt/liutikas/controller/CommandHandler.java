package lt.liutikas.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lt.liutikas.controller.commands.Command;
import lt.liutikas.controller.commands.MoveDownCommand;
import lt.liutikas.controller.commands.MoveLeftCommand;
import lt.liutikas.controller.commands.MoveRightCommand;
import lt.liutikas.controller.commands.RotateTetrominoCommand;
import lt.liutikas.renderer.Renderer;

import java.util.EnumMap;

public class CommandHandler {
    private Renderer renderer;

    private EnumMap<KeyCode, Command> commands = new EnumMap<>(KeyCode.class);

    public CommandHandler(Renderer renderer, GameLogic logic, TetrominoConveyor conveyor) {
        this.renderer = renderer;
        prepareCommands(conveyor, logic);
    }

    public void handle(KeyEvent key) {
        var command = commands.get(key.getCode());

        if (command != null) {
            command.execute();
            renderer.drawFrame();
        }
    }

    private void prepareCommands(TetrominoConveyor conveyor, GameLogic logic) {
        commands.put(KeyCode.A, new MoveLeftCommand(conveyor, logic));
        commands.put(KeyCode.LEFT, new MoveLeftCommand(conveyor, logic));

        commands.put(KeyCode.D, new MoveRightCommand(conveyor, logic));
        commands.put(KeyCode.RIGHT, new MoveRightCommand(conveyor, logic));

        commands.put(KeyCode.S, new MoveDownCommand(conveyor, logic));
        commands.put(KeyCode.DOWN, new MoveDownCommand(conveyor, logic));

        commands.put(KeyCode.W, new RotateTetrominoCommand(conveyor, logic));
        commands.put(KeyCode.UP, new RotateTetrominoCommand(conveyor, logic));
    }
}
