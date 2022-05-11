import java.awt.event.*;
import javax.swing.Timer;

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
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 65) {
            // move left
            panel.getBoard().getCurrentPiece().changeX(-1);
       } else if (e.getKeyCode() == 68) {
           // move right
           panel.getBoard().getCurrentPiece().changeX(1);
       } else if (e.getKeyCode() == 83) {
           // move downwards, faster
            if (inFocus) {
                panel.getBoard().movePieceDown();
            }
       } else if (e.getKeyCode() == 70) {
           // "f" key to test new piece generation
           panel.getBoard().generateNewPiece();
       }
    //    System.out.println(e.getKeyCode());
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
            panel.getBoard().movePieceDown();;
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