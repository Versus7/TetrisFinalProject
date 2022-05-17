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
        if (e.getKeyCode() == 65) { // move left
            panel.getBoard().movePieceLeft();
       } else if (e.getKeyCode() == 68) { // move right
            panel.getBoard().movePieceRight();
       } else if (e.getKeyCode() == 83) { // move downwards, faster
            if (inFocus) {
                panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
            }
       } else if (e.getKeyCode() == 87) { // drop down as much as possible
            // TODO: Remove hardcoding
            Piece temp = panel.getBoard().getCurrentPiece();
            while (temp == panel.getBoard().getCurrentPiece()) {
                panel.getBoard().movePieceDown(panel.getBoard().getCurrentPiece());
            }
       } else if (e.getKeyCode() == 70) {
           // rotate right
           panel.getBoard().getCurrentPiece().rotateRight();
       } else if (e.getKeyCode() == 81) {
           panel.getBoard().getCurrentPiece().rotateLeft();
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