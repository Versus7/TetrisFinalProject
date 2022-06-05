package Models;

// Rank is a helper class for Leaderboard, to keep track of the name and score of a player

public class Rank {
   private String name;
   private int score;

   public Rank(String name, int score) {
      this.name = name;
      this.score = score;
   }
   
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
