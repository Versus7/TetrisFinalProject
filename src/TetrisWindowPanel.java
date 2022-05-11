import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class TetrisWindowPanel extends JPanel {
    private Board board = new Board();
    public static int SQUAREWIDTH = 40;

    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        SQUAREWIDTH = getHeight() / 20;

        // drawing out the tetris grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.drawRect(i*SQUAREWIDTH, j*SQUAREWIDTH, SQUAREWIDTH, SQUAREWIDTH);
                // g.drawString(i + ", " + j, i*SQUAREWIDTH, j*SQUAREWIDTH);
            }
        }

        board.getCurrentPiece().draw(g);
        // repaint();
        for (Piece p: board.getAllPieces()) {
            p.draw(g);
        }


    }

    public Board getBoard() {
        return board;
    }
}