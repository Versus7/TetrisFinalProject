package Views;
import java.awt.*;
import javax.swing.*;

import Models.Piece;

public class InfoPanel extends JPanel {
    private TetrisWindowPanel game;
    private Piece heldPiece;
    
    public InfoPanel(TetrisWindowPanel game) {
        this.game = game;
        // System.out.println("Window width: " + windowWidth);

        setPreferredSize(new Dimension((int)(150), getHeight()));

        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        heldPiece = game.getBoard().getHeldPiece();

        Double x = (getHeight()) / 23.0;
        // System.out.println("X: " + x);
        int startX = (int)(getWidth()*0.1);
        // System.out.println("Start x: " + startX);
        int squareSideLength = (int)(4*x);
        
        setPreferredSize(new Dimension(squareSideLength+50, getHeight()));

        // Currently Held Piece
        g2.setColor(heldPiece == null ? game.getBoard().getCurrentPiece().getColor() : heldPiece.getColor());
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(startX, x.intValue(), squareSideLength, squareSideLength, 10, 10);

        if (heldPiece != null) {
            while (heldPiece.getCenterPoint().getX() > 1) {
                heldPiece.changeX(-1);
            }

            while (heldPiece.getCenterPoint().getY() > 2) {
                heldPiece.changeY(-1);
            }
            heldPiece.draw(g2);
        }

        // The following code deals with the "Upcoming" blocks.
        // Colors the boxes as the same color as the current piece
        Piece second = game.getBoard().getUpcomingPieces()[0];

        g2.setColor(second.getColor());
        g2.drawRoundRect(startX, (int)(8*x), squareSideLength, squareSideLength, 10, 10);

        while (second.getCenterPoint().getX() > 1) {
            second.changeX(-1);
        }
        while (second.getCenterPoint().getY() < 7) {
            second.changeY(1);
        }
        second.draw(g2);


        // Third piece in the row
        Piece third = game.getBoard().getUpcomingPieces()[1];

        g2.setColor(third.getColor());
        g2.drawRoundRect(startX, (int)(13*x), squareSideLength, squareSideLength, 10, 10);

        while (third.getCenterPoint().getX() > 1) {
            third.changeX(-1);
        }
        while (third.getCenterPoint().getY() < 12) {
            third.changeY(1);
        }
        third.draw(g2);

        // Fourth piece in the row
        Piece fourth = game.getBoard().getUpcomingPieces()[2];

        g2.setColor(fourth.getColor());
        g2.drawRoundRect(startX, (int)(18*x), squareSideLength, squareSideLength, 10, 10);

        while (fourth.getCenterPoint().getX() > 1) {
            fourth.changeX(-1);
        }
        while (fourth.getCenterPoint().getY() < 17) {
            fourth.changeY(1);
        }
        fourth.draw(g2);

        // System.out.println();
        // System.out.println();
    }
}