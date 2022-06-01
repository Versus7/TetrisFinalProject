package Views;
import java.awt.event.*;
import javax.swing.Timer;

// imports to handle the music
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

public class TetrisWindowListener implements KeyListener, ActionListener, FocusListener {
    TetrisWindowPanel panel;
    ScorePanel score;
    InfoPanel info;
    Timer timer;
    Clip clip;
    boolean inFocus = true;
    
    double speed = 1000;

    public TetrisWindowListener(TetrisWindowPanel panel, ScorePanel score, InfoPanel info) {
        this.panel = panel;
        this.score = score;
        this.info = info;

        panel.addKeyListener(this);
        panel.addFocusListener(this);
        timer = new Timer((int)(speed), this);
        timer.start();

        File musicFile = new File(System.getProperty("user.dir") + "/src/TetrisTheme.wav");
        // // play the music
        try {
            clip = AudioSystem.getClip();
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        /**
         * control - rotate piece left
         * up arrow - rotate right
         * c is hold
         * spacebar insta drop
         * eventually switch system to use arrow keys instead of WASD
         */
        switch (e.getKeyCode()) {
            case 37:
                panel.getBoard().movePieceLeft();
                break;
            case 39:
                panel.getBoard().movePieceRight();
                break;
            case 40:
                panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
                break;
            case 32:
                panel.getBoard().dropPieceCompletely(panel.getBoard().getCurrentPiece());
                panel.getBoard().generateNewPiece();
                break;
            case 67: // hold pieces
                panel.getBoard().holdPiece();
            default:
                break;
        }
        speed = Math.pow((0.8-((double)panel.getBoard().getStats().getLevel()-1.0)*0.007), (double)(panel.getBoard().getStats().getLevel())-1.0) * 1000;
        timer.setDelay((int)speed);

        updateEverything();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                panel.getBoard().rotatePieceRight();
                break;
            case 81:
                panel.getBoard().rotatePieceLeft();
                break;
            default:
                break;
        }
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inFocus) {
            panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
            updateEverything();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        inFocus = true;

        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        inFocus = false;

        if (clip != null) {
            clip.stop();
        }
    }

    public void updateEverything() {
        if (panel.getBoard().gameStatus()) {
            timer.stop();
            panel.removeKeyListener(this);
            panel.removeFocusListener(this);

            if (panel.endGame()) {
                timer.restart();
                panel.addKeyListener(this);
                panel.addFocusListener(this);
            }
        }

        panel.repaint();
        score.repaint();
        info.repaint();
    }

}