import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class TetrisWindowPanel extends JPanel {

    Piece test = new Piece(Piece.shapeType.LSHAPE, 10, 0);
    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.drawRect(i*50, j*50, 50, 50);
            }
        }

        // Block test = new Block(5, 5);
        // test.draw(g);

        test.changeX(-1);
        test.draw(g);
        // repaint();


    }
}