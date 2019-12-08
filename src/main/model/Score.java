package main.model;

public class Score {
    private int score = 0;

    public int get() {
        return score;
    }

    public void increase(int linesRemoved, int level) {
        int scoreToAdd = (level + 1) * getLineCoefficient(linesRemoved);
        score += scoreToAdd;
    }

    private int getLineCoefficient(int linesRemoved) {
        switch (linesRemoved) {
            case 0:
                return 0;
            case 1:
                return 40;
            case 2:
                return 100;
            case 3:
                return 300;
            case 4:
                return 1200;
            default:
                throw new RuntimeException("Coefficient is not specified for " + linesRemoved + " removed lines.");
        }
    }
}
