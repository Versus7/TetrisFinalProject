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
        setBackground(Color.black);

        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(4));
        g2.setColor(board.getCurrentPiece().getColor());

        SQUAREWIDTH = (double)(getHeight()) / 20.0;
        g2.drawRect(0, 0, (int)(SQUAREWIDTH*10.0), (int)(SQUAREWIDTH*20.0));


        // g2.setStroke(new BasicStroke((float)0.01));
        // g2.setColor(Color.white);
        // // drawing out the tetris grid
        // for (int i = 0; i < 10; i++) {
        //     for (int j = 0; j < 20; j++) {
        //         g.drawRect((int)(i*SQUAREWIDTH), (int)(j*SQUAREWIDTH), SQUAREWIDTH.intValue(), SQUAREWIDTH.intValue());
        //         // g.drawString(i + ", " + j, i*SQUAREWIDTH, j*SQUAREWIDTH);
        //     }
        // }

        // g2.setColor(board.getCurrentPiece().getColor());
        g2.setStroke(new BasicStroke(2));

        g2.drawLine((int)(board.getCurrentPiece().getLeftmostCoordinate()*SQUAREWIDTH), 0, (int)(board.getCurrentPiece().getLeftmostCoordinate()*SQUAREWIDTH), (int)(SQUAREWIDTH*20.0));
        g2.drawLine((int)((board.getCurrentPiece().getRightmostCoordinate()+1)*SQUAREWIDTH), 0, (int)((board.getCurrentPiece().getRightmostCoordinate()+1)*SQUAREWIDTH), (int)(SQUAREWIDTH*20.0));

        board.getCurrentPiece().draw(g2);
        for (Block b: board.getAllBlocks()) {
            b.draw(g2);
        }
    }

    public Board getBoard() {
        return board;
    }
}