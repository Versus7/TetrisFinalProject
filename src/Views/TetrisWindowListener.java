package Views;
import java.awt.event.*;
import javax.swing.Timer;

// imports to handle the music
import java.io.File;
import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Credit for playing the music: https://www.delftstack.com/howto/java/play-sound-in-java/
 */

public class TetrisWindowListener implements KeyListener, ActionListener, FocusListener {
    private TetrisWindowPanel panel;
    private ScorePanel score;
    private InfoPanel info;
    private Timer timer;
    private Clip clip;
    private boolean paused = false;
    
    private double speed = 1000;

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
        if (e.getKeyCode() == 27) {
            paused = !paused;
            panel.setPause(paused);
            if (!paused) {
                if (clip != null) {
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            } else {
                if (clip != null) {
                    clip.stop();
                }
            }
        }

        if (paused) {
            return;
        }

        switch (e.getKeyCode()) {
            case 37:
                panel.getBoard().movePieceLeft();
                break;
            case 39:
                panel.getBoard().movePieceRight();
                break;
            case 40:
                panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
                panel.getBoard().getStats().softDrop(1);
                break;
            case 32:
                panel.getBoard().dropPieceCompletely(panel.getBoard().getCurrentPiece(), true);
                panel.getBoard().generateNewPiece();
                break;
            case 67: // hold pieces
                panel.getBoard().holdPiece();
                break;
            default:
                break;
        }
        speed = Math.pow((0.8-((double)panel.getBoard().getStats().getLevel()-1.0)*0.007), (double)(panel.getBoard().getStats().getLevel())-1.0) * 1000;
        timer.setDelay((int)speed);

        updateEverything();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!paused) {
            switch (e.getKeyCode()) {
                case 38:
                    panel.getBoard().rotatePieceRight();
                    break;
                case 90:
                    panel.getBoard().rotatePieceLeft();
                    break;
                default:
                    break;
            }
            panel.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!paused) {
            panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
            updateEverything();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (!paused) {
            return;
        }
        paused = false;
        panel.setPause(paused);

        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        paused = true;
        panel.setPause(paused);

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