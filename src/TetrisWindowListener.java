import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisWindowListener implements KeyListener {
    private Piece pieceInFocus;
    TetrisWindowPanel panel;

    public TetrisWindowListener(TetrisWindowPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        // System.out.println(e.getKeyText(e.getKeyCode()));
        // System.out.println(e.getKeyChar());
        System.out.println("Key pressed!");
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key released!");
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Key typed!");
        
    }

}