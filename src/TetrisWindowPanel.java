import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class TetrisWindowPanel extends JPanel {
    private Board board = new Board();

    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // drawing out the tetris grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.drawRect(i*Block.SQUAREWIDTH, j*Block.SQUAREWIDTH, Block.SQUAREWIDTH, Block.SQUAREWIDTH);
                g.drawString(i + ", " + j, i*Block.SQUAREWIDTH, j*Block.SQUAREWIDTH);
            }
        }

        // Block test = new Block(5, 5);
        // test.draw(g);

        // test.changeX(-1);
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