package Views;

import javax.swing.*;
import java.awt.*;

// imports to handle the music
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class TetrisWindowMain {
    public static void main(String[] args) {
        // music
        File musicFile = new File(System.getProperty("user.dir") + "/src/TetrisTheme.wav");
        // play the music
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(musicFile));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }


        // views
        JFrame window = new JFrame("Tetris");

        JPanel everything = new JPanel();
        everything.setLayout(new BorderLayout());

        TetrisWindowPanel game = new TetrisWindowPanel();
        ScorePanel score = new ScorePanel(game);
        InfoPanel infoPanel = new InfoPanel(game);
        new TetrisWindowListener(game, score, infoPanel);
        
        everything.add(game, BorderLayout.CENTER);
        everything.add(score, BorderLayout.SOUTH);
        everything.add(infoPanel, BorderLayout.EAST);

        // JFrame configuration
        window.setContentPane(everything);
        window.setSize(1000, 1000);
        window.setLocation(500, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // make sure key events are registered
        game.requestFocusInWindow();
    }
}