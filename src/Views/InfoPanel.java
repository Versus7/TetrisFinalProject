package Views;
import java.awt.*;
import javax.swing.*;

import Models.Piece;

public class InfoPanel extends JPanel {
    private TetrisWindowPanel game;
    private Piece heldPiece;
    
    public InfoPanel(TetrisWindowPanel game) {
        this.game = game;

        setPreferredSize(new Dimension((int)(150), getHeight()));
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        heldPiece = game.getBoard().getHeldPiece();

        Double x = (getHeight()) / 23.0;
        int startX = (int)(getWidth()*0.1);
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

            while (heldPiece.getCenterPoint().getY() < 2) {
                heldPiece.changeY(1);
            }
            heldPiece.draw(g);
        }
        // Show the score
        double fontSize = x*0.75;
        if ((game.getBoard().getStats().getScore() + "").length() > 2) {
            fontSize *= ((game.getBoard().getStats().getScore()  + "").length() * -0.1 + 1.2);
        }
        Font font = new Font(Font.MONOSPACED, Font.BOLD, (int)(fontSize));

        g2.setColor(Color.white);
        // g2.setFont(new Font("Arial", Font.PLAIN, (int)(x*0.75)));
        g2.setFont(font);
        g2.drawString("Score: " + game.getBoard().getStats().getScore(), startX, (int)(x*6.0));
        g2.drawString("Level: " + game.getBoard().getStats().getLevel(), startX, (int)(x*7.0));
        // g2.drawString("Lines: " + game.getBoard().getStats().getLines(), startX, (int)(x*4.5));


        // The following code deals with the "Upcoming" blocks.
        Piece second = game.getBoard().getUpcomingPieces()[0];

        g.setColor(second.getColor());
        g.drawRoundRect(startX, (int)(8*x), squareSideLength, squareSideLength, 10, 10);

        while (second.getCenterPoint().getX() > 1) {
            second.changeX(-1);
        }
        while (second.getCenterPoint().getY() < 8) {
            second.changeY(1);
        }
        second.draw(g);


        // Third piece in the row
        Piece third = game.getBoard().getUpcomingPieces()[1];

        g.setColor(third.getColor());
        g.drawRoundRect(startX, (int)(13*x), squareSideLength, squareSideLength, 10, 10);

        while (third.getCenterPoint().getX() > 1) {
            third.changeX(-1);
        }
        while (third.getCenterPoint().getY() < 12) {
            third.changeY(1);
        }
        third.draw(g);

        // Fourth piece in the row
        Piece fourth = game.getBoard().getUpcomingPieces()[2];

        g.setColor(fourth.getColor());
        g.drawRoundRect(startX, (int)(18*x), squareSideLength, squareSideLength, 10, 10);

        while (fourth.getCenterPoint().getX() > 1) {
            fourth.changeX(-1);
        }
        while (fourth.getCenterPoint().getY() < 17) {
            fourth.changeY(1);
        }
        fourth.draw(g);
    }
}
