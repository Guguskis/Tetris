package lt.liutikas.model;

public class Level {
    private int goal = 0;

    public void increase(int linesRemoved) {
        switch (linesRemoved) {
            case 1:
                goal += 1;
                break;
            case 2:
                goal += 3;
                break;
            case 3:
                goal += 5;
                break;
            case 4:
                goal += 8;
                break;
        }
    }

    public int get() {
        return goal / 10 + 1;
    }
}
