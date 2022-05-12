import javax.swing.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        TetrisWindowPanel panel = new TetrisWindowPanel();

        new TetrisWindowListener(panel);

        window.setContentPane(panel);
        
        window.setSize(400, 800);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        panel.requestFocusInWindow();
    }
}