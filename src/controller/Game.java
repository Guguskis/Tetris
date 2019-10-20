package controller;

import controller.commands.*;
import javafx.scene.paint.Color;
import model.Position;
import model.TetrominoFactory;
import view.renderer.SimpleRenderer;
import model.Grid;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.renderer.Renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Game extends Application {
    private int width;
    private int height;

    private Scene gameplayScene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        width = 800;
        height = 800;
        this.gameplayScene = setGameplayScene();

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(gameplayScene);
        primaryStage.show();
        // when should a variable be field
        // Design pattern examples
        // cohesion
        // I was about to commit, but though I better do it after implementing next shape for nice commit message
        // Customized factory, now I cannot initialise properly to always have tetromino aligned vertically
        // Solution - maybe customize factory contructor to take in Grid? Probably not, need to keep it x and y
    }

    private Scene setGameplayScene() {
        var root = new Group();
        var scene = new Scene(root);
        var canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        var rules = Rules.getInstance();
        var grid = new Grid(10, 20);
        var tetrominoFactory = new TetrominoFactory(grid.getWidth() / 2, 0);

        var graphicsContext = canvas.getGraphicsContext2D();
        var renderer = new SimpleRenderer(graphicsContext, 12);

        var commands = getPreparedCommands(tetrominoFactory, grid, rules);
        setMovementLogic(renderer, scene, tetrominoFactory, grid, commands, rules);

        return scene;
    }

    private void setMovementLogic(Renderer renderer, Scene gameplayScene, TetrominoFactory factory, Grid grid, HashMap<KeyCode, CommandInterface> commands, Rules rules) {
        var moveDownCommand = (MoveDownCommand)getCommand(commands, MoveDownCommand.class);

        if (moveDownCommand != null) {
            automaticallyMoveTetrominoDown(renderer, moveDownCommand, factory, grid, rules);
        }

        gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            handleCommand(renderer, factory, grid, commands, key, rules);
        });
    }

    private CommandInterface getCommand(HashMap<KeyCode, CommandInterface> commands, Class<? extends CommandInterface> type) {
        var unfilteredCommands = commands.entrySet().stream()
                .map(command -> command.getValue())
                .collect(Collectors.toList());
        var filteredCommands = new ArrayList<CommandInterface>();


        for (var command : unfilteredCommands) {
            if (command.getClass() == type) {
                filteredCommands.add(command);
            }
        }

        return filteredCommands.size() == 1 ? filteredCommands.get(0) : null;
    }

    private void handleCommand(Renderer renderer, TetrominoFactory factory, Grid grid, HashMap<KeyCode, CommandInterface> commands, KeyEvent key, Rules rules) {
        var command = getCommand(commands, key);

        if (command != null) {
            command.execute();
            drawScene(renderer, factory, grid, rules);
        }
    }

    private CommandInterface getCommand(HashMap<KeyCode, CommandInterface> commands, KeyEvent key) {
        return commands.get(key.getCode());
    }

    private void drawScene(Renderer renderer, TetrominoFactory factory, Grid grid, Rules rules) {
        var currentTetromino = factory.getCurrent();
        var nextTetromino = factory.preview();

        renderer.fillBackground(width, height, Color.rgb(30, 0, 40));
        renderer.outline(new Position(0, 0), grid.getWidth() + 2, grid.getHeight() + 2, Color.rgb(125, 190, 80));
        renderer.mainView(new Position(1, 1), currentTetromino, grid);
        renderer.nextTetromino(new Position(grid.getWidth() + 4, 1), nextTetromino);
        renderer.gameInformation(new Position(grid.getWidth() + 4, 7), rules);
    }

    private void automaticallyMoveTetrominoDown(Renderer renderer, MoveDownCommand command, TetrominoFactory factory, Grid grid, Rules rules) {
        new AnimationTimer() {
            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    drawScene(renderer, factory, grid, rules);
                }
                if (now - lastTick > 250 * 1e6) {
                    lastTick = now;
                    command.execute();
                    drawScene(renderer, factory, grid, rules);
                }
            }
        }.start();
    }

    private HashMap<KeyCode, CommandInterface> getPreparedCommands(TetrominoFactory factory, Grid grid, Rules rules) {
        var newCommands = new HashMap<KeyCode, CommandInterface>();
        newCommands.put(KeyCode.A, new MoveLeftCommand(factory, grid, rules));
        newCommands.put(KeyCode.D, new MoveRightCommand(factory, grid, rules));
        newCommands.put(KeyCode.S, new MoveDownCommand(factory, grid, rules));
        newCommands.put(KeyCode.S, new MoveDownCommand(factory, grid, rules));
        newCommands.put(KeyCode.W, new RotateTetrominoCommand(factory, grid, rules));

        return newCommands;
    }

}