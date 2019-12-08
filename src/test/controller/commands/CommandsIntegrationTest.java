package test.controller.commands;

import main.controller.GameState;
import main.controller.commands.Command;
import main.controller.commands.MoveDownCommand;
import main.controller.commands.MoveLeftCommand;
import main.controller.commands.MoveRightCommand;
import main.controller.commands.RotateTetrominoCommand;
import main.model.Grid;
import main.model.Level;
import main.model.Score;
import main.model.tetromino.Tetromino;
import main.model.tetromino.TetrominoFactory;
import main.model.tetromino.TetrominoType;
import main.service.CollisionDetector;
import main.service.TetrominoConveyor;
import main.service.generator.TetrominoGenerator;
import main.service.rotator.DefaultRotator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

class CommandsIntegrationTest {
    private Command rotate;
    private Command left;
    private Command down;
    private Command right;


    @BeforeAll
    public void setup() {
        var grid = new Grid(10, 20);
        var gameState = new GameState(new Score(), new Level());
        var conveyor = new TetrominoConveyor(grid, new GeneratorMock());
        var detector = new CollisionDetector();
        var rotator = new DefaultRotator();

        rotate = new RotateTetrominoCommand(grid, rotator, conveyor, detector);
        left = new MoveLeftCommand(grid, conveyor, detector);
        down = new MoveDownCommand(grid, gameState, conveyor, detector);
        right = new MoveRightCommand(grid, conveyor, detector);
    }

    @Test
    public void left_canMove_TetrominoMovedLeft() {

    }

    private class GeneratorMock implements TetrominoGenerator {
        @Override
        public Tetromino getNext(int x) {
            return TetrominoFactory.getInstance(TetrominoType.I, x);
        }
    }

}