package main.service;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.controller.commands.Command;
import main.controller.renderer.Renderer;
import main.model.GameState;

import java.util.EnumMap;

public class KeyboardHandler {
    private EnumMap<KeyCode, Command> commands;
    private GameState gameState;
    private Renderer renderer;

    public KeyboardHandler(EnumMap<KeyCode, Command> commands, GameState gameState, Renderer renderer) {
        this.commands = commands;
        this.gameState = gameState;
        this.renderer = renderer;
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
}
