package Views;
import java.awt.event.*;
import javax.swing.Timer;

// file imports
import Models.Piece;

public class TetrisWindowListener implements KeyListener, ActionListener, FocusListener {
    TetrisWindowPanel panel;
    ScorePanel score;
    InfoPanel info;
    Timer timer;
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
            case 65:
                panel.getBoard().movePieceLeft();
                break;
            case 68:
                panel.getBoard().movePieceRight();
                break;
            case 83:
                panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
                break;
            case 32:
                panel.getBoard().dropPieceCompletely(panel.getBoard().getCurrentPiece());
                panel.getBoard().generateNewPiece();
                break;
            case 72: // hold pieces
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
            case 70:
                panel.getBoard().getCurrentPiece().rotateRight();
                break;
            case 81:
                panel.getBoard().getCurrentPiece().rotateLeft();
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

            System.out.println(panel.getBoard().getCurrentPiece().getShape().toString());
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        inFocus = true;
    }

    @Override
    public void focusLost(FocusEvent e) {
        inFocus = false;
    }

    public void updateEverything() {
        if (panel.getBoard().gameStatus()) {
            timer.stop();
            panel.removeKeyListener(this);
            panel.removeFocusListener(this);
            System.out.println("Game over!");

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