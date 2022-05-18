package Views;
import javax.swing.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        TetrisWindowPanel panel = new TetrisWindowPanel();

        new TetrisWindowListener(panel);

        window.setContentPane(panel);
        
        window.setSize(200, 400);

        window.setLocation(500, 200);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        panel.requestFocusInWindow();
    }
}