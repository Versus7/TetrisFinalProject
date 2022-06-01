package Models;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Leaderboard {
    private Rank[] ranks = new Rank[5];

    public Leaderboard() {
        try {
            String path = System.getProperty("user.dir");
            File file = new File(path + "/src/Models/leaderboard.txt");
            if (!file.exists()) {
                System.out.println("Creating new file!");
                file.createNewFile();
                return;
            }

            // copy the contents of the file to the ranks array
            try {
                Scanner scanner = new Scanner(file);
                for (int i = 0; i < 5; i++) {
                    String line = scanner.nextLine();
                    String[] lineSplit = line.split(",");
                    ranks[i] = new Rank(lineSplit[0], Integer.parseInt(lineSplit[1]));
                }
                scanner.close();
            } catch (NoSuchElementException e) {
                System.out.println("File is empty!");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkScore(int score) {
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == null || score > ranks[i].getScore()) {
                return true;
            }
        }
        return false;
    }

    public void addScore(String name, int score) {
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == null) {
                ranks[i] = new Rank(name, score);
                break;
            } else if (ranks[i].getScore() < score) {
                Rank temp = ranks[i];
                ranks[i] = new Rank(name, score);
                for (int j = i + 1; j < ranks.length; j++) {
                    if (ranks[j] == null) {
                        break;
                    }
                    Rank temp2 = ranks[j];
                    ranks[j] = temp;
                    temp = temp2;
                }
                break;
            }
        }

        // copy the ranks array back to the file
        try {
            String path = System.getProperty("user.dir");
            File file = new File(path + "/src/Models/leaderboard.txt");
            if (!file.exists()) {
                System.out.println("Creating new file!");
                file.createNewFile();
                return;
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i] == null) {
                    break;
                }
                bw.write(ranks[i].getName() + "," + ranks[i].getScore());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Used to print out the leaderboard at the end of the game
    public String getRankings() {
        String rankings = "";
        for (int i = 0; i < 5; i++) {
            if (ranks[i] == null) {
                break;
            }
            rankings += (i+1) + "). " + ranks[i].getName() + " " + String.format("%,d", ranks[i].getScore()) + "\n";


        }
        return rankings;
    }
}