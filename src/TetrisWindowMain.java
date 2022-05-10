import javax.swing.*;

public class TetrisWindowMain {
    public static void main(String[] args) {
        JFrame window = new JFrame("Tetris");
        TetrisWindowPanel panel = new TetrisWindowPanel();

        new TetrisWindowListener(panel);

        window.setContentPane(panel);
        
        window.setSize(500, 1002);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        // unclear if this next line is needed
        // window.requestFocusInWindow();
        panel.requestFocusInWindow();
    }
}