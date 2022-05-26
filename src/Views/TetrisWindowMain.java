package Views;

import javax.swing.*;
import java.awt.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");

        JPanel everything = new JPanel();
        everything.setLayout(new BorderLayout());

        TetrisWindowPanel game = new TetrisWindowPanel();
        ScorePanel score = new ScorePanel();
        new TetrisWindowListener(game, score);
        
        everything.add(game, BorderLayout.CENTER);
        everything.add(score, BorderLayout.SOUTH);

        // JFrame configuration
        window.setContentPane(everything);
        window.setSize(200, 400);
        window.setLocation(500, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // make sure key events are registered
        game.requestFocusInWindow();
    }
}