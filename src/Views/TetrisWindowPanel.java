package Views;
import java.awt.*;
import javax.swing.*;

// file imports
import Models.Board;
import Models.Block;

public class TetrisWindowPanel extends JPanel {
    private Board board = new Board();
    public static Double SQUAREWIDTH = 10.0;

    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.setBackground(Color.black);
        g.setColor(board.getCurrentPiece().getColor());

        SQUAREWIDTH = (double)(getHeight()) / 20.0;

        // drawing out the tetris grid
        // for (int i = 0; i < 10; i++) {
        //     for (int j = 0; j < 20; j++) {
        //         g.drawRect(i*SQUAREWIDTH, j*SQUAREWIDTH, SQUAREWIDTH, SQUAREWIDTH);
        //         // g.drawString(i + ", " + j, i*SQUAREWIDTH, j*SQUAREWIDTH);
        //     }
        // }
        g.drawRect(0, 0, (int)(SQUAREWIDTH*10), (int)(SQUAREWIDTH*20));

        board.getCurrentPiece().draw(g);
        for (Block b: board.getAllBlocks()) {
            b.draw(g);
        }
    }

    public Board getBoard() {
        return board;
    }
}