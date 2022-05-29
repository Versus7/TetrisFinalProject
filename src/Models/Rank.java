package Models;

public class Rank {
   String name;
   int score;

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
