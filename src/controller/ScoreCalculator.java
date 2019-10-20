package controller;

import controller.Rules;

public class ScoreCalculator {
    private int score = 0;

    public void calculate(int linesRemoved, int level) {
        try {
            int scoreToAdd = (level + 1) * getLineCoefficient(linesRemoved);
            score += scoreToAdd;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        return score;
    }

    private int getLineCoefficient(int linesRemoved) throws Exception {
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
                throw new Exception("Coefficient is not specified for " + linesRemoved + " lines.");
        }
    }
}
