package Views;
import java.awt.event.*;
import javax.swing.Timer;

// file imports
import Models.Piece;

public class TetrisWindowListener implements KeyListener, ActionListener, FocusListener {
    TetrisWindowPanel panel;
    Timer timer;
    boolean inFocus = true;

    public TetrisWindowListener(TetrisWindowPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
        panel.addFocusListener(this);
        timer = new Timer(1000, this);
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
                Piece temp = panel.getBoard().getCurrentPiece();
                while (temp == panel.getBoard().getCurrentPiece()) {
                    panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
                }
                break;
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
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("Key released!");
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println("Key typed!");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (inFocus) {
            panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
            panel.repaint();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        inFocus = true;
        // TODO Auto-generated method stub
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        inFocus = false;
        // TODO Auto-generated method stub
        
    }

}