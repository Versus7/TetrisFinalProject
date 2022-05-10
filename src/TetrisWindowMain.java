import javax.swing.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        TetrisWindowPanel panel = new TetrisWindowPanel();
        TetrisWindowListener listener = new TetrisWindowListener(panel);

        // new TetrisWindowListener(panel);
        panel.addKeyListener(listener);
        window.setContentPane(panel);
        
        window.setSize(500, 1000);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        window.requestFocusInWindow();
    }
}