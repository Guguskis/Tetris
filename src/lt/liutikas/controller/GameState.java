package lt.liutikas.controller;

public class GameState {
    private boolean gameOver = false;
    private Score score;
    private Level level;

    public GameState(Score score, Level level) {
        this.score = score;
        this.level = level;
    }

    public int getLevel() {
        return level.get();
    }

    public int getScore() {
        return score.get();
    }

    public double getTickIntervalInMilliseconds() {
        return Math.pow((0.8 - ((getLevel() - 1) * 0.007)), getLevel() - 1);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void update(int removedLines) {
        score.increase(removedLines, getLevel());
        level.increase(removedLines);
    }
}
