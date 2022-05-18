package Views;
import java.awt.*;
import javax.swing.*;

// file imports
import Models.Board;
import Models.Piece;

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
        super.setBackground(Color.black);
        g.setColor(Color.white);

        SQUAREWIDTH = getHeight() / 20;

        // drawing out the tetris grid
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.drawRect(i*SQUAREWIDTH, j*SQUAREWIDTH, SQUAREWIDTH, SQUAREWIDTH);
                // g.drawString(i + ", " + j, i*SQUAREWIDTH, j*SQUAREWIDTH);
            }
        }

        board.getCurrentPiece().draw(g);
        for (Piece p: board.getAllPieces()) {
            p.draw(g);
        }


    }

    public Board getBoard() {
        return board;
    }
}