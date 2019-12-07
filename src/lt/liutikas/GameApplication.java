package lt.liutikas;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lt.liutikas.controller.CollisionDetector;
import lt.liutikas.controller.GameLogic;
import lt.liutikas.controller.KeyboardInput;
import lt.liutikas.controller.Level;
import lt.liutikas.controller.RandomTetrominoGenerator;
import lt.liutikas.controller.Score;
import lt.liutikas.controller.TetrominoConveyor;
import lt.liutikas.controller.commands.Command;
import lt.liutikas.controller.commands.MoveDownCommand;
import lt.liutikas.controller.rotator.DefaultRotator;
import lt.liutikas.controller.rotator.Rotator;
import lt.liutikas.model.Grid;
import lt.liutikas.renderer.DefaultRenderer;
import lt.liutikas.renderer.Renderer;

import java.util.Random;

public class GameApplication extends Application {

    private GameLogic logic;
    private Renderer renderer;
    private Scene scene;
    private TetrominoConveyor conveyor;
    private KeyboardInput keyboardInput;
    private Grid grid;
    private CollisionDetector detector;
    private Rotator rotator;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        configure();
        setAutomaticMoveDown();
        mapCommandsToKeyboardInput();
    }

    private void configure() {
        grid = new Grid(10, 20);
        detector = new CollisionDetector();
        rotator = new DefaultRotator();

        logic = new GameLogic(grid, new Score(), new Level());
        conveyor = new TetrominoConveyor(grid, new RandomTetrominoGenerator(new Random()));

        Group root = new Group();
        scene = new Scene(root);
        renderer = new DefaultRenderer(root, grid, logic, conveyor);

        keyboardInput = new KeyboardInput(renderer, grid, logic, conveyor, detector, rotator);
    }

    private void mapCommandsToKeyboardInput() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyboardInput::handle);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setAutomaticMoveDown() {
        new AnimationTimer() {
            long lastTick = 0;
            Command moveDown = new MoveDownCommand(grid, logic, conveyor, detector);

            @Override
            public void handle(long now) {
                if (logic.isGameOver()) {
                    stop();
                }

                if (lastTick == 0) {
                    lastTick = now;
                    renderer.drawFrame();
                }

                var gameSpeed = logic.getTickIntervalInMilliseconds() * 1e9;
                if (now - lastTick > gameSpeed) {
                    lastTick = now;
                    moveDown.execute();
                    renderer.drawFrame();
                }
            }
        }.start();
    }

}