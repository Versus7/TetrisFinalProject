package Views;

import javax.swing.*;
import java.awt.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");

        JPanel everything = new JPanel();
        everything.setLayout(new BorderLayout());

        TetrisWindowPanel game = new TetrisWindowPanel();
        InfoPanel infoPanel = new InfoPanel(game);
        new TetrisWindowListener(game, infoPanel);
        
        everything.add(game, BorderLayout.CENTER);
        everything.add(infoPanel, BorderLayout.EAST);

        // JFrame configuration
        window.setContentPane(everything);
        window.setSize(700, 1000);
        window.setLocation(500, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // make sure key events are registered
        game.requestFocusInWindow();
    }
}