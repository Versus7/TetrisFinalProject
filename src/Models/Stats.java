package Models;

// This class keeps track of all game stats, and handles increasing the score and level.
public class Stats {
   private int score;
   private int level;
   private int linesCleared;
   
    public Stats() {
        score = 0;
        level = 1;
        linesCleared = 0;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void incrementScore(int lines) {
        if (lines == 0) {
            return;
        }

        linesCleared += lines;
        if (linesCleared >= level * 5) {
            incrementLevel(1);
            linesCleared = 0;
        }

        int constant;
        switch (lines) {
            case 1:
                constant = 100;
                break;
            case 2:
                constant = 300;
                break;
            case 3:
                constant = 500;
                break;
            case 4:
                constant = 800;
                break;
            default:
                constant = 0;
                break;
        }

        score += (constant * lines);
    }

    public void hardDrop(int distance) {
        score += (distance * 2 * level);
    }

    public void softDrop(int distance) {
        score += distance * level;
    }

    private void incrementLevel(int amount) {
        level += amount;
    }
}
