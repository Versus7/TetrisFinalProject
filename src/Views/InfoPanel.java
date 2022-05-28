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

        heldPiece = game.getBoard().getHeldPiece();

        Double x = (getHeight()) / 23.0;
        // System.out.println("InfoPanel height: " + getHeight());
        int startX = (int)(getWidth()*0.1);
        int squareSideLength = (int)(4*x);
        
        setPreferredSize(new Dimension(squareSideLength+50, getHeight()));

        // Color the first box the same as the currently held piece, if that piece is not null
        g.setColor(heldPiece == null ? game.getBoard().getCurrentPiece().getColor() : heldPiece.getColor());
        g.drawRoundRect(startX, x.intValue(), squareSideLength, squareSideLength, 10, 10);

        if (heldPiece != null) {
            System.out.println("Drawing held piece!");
            // System.out.println(heldPiece.getCenterPoint());
            // heldPiece.changeX(-1);
            while (heldPiece.getCenterPoint().getX() > 1) {
                heldPiece.changeX(-1);
            }
            heldPiece.draw(g);
        }
        // Colors the boxes as the same color as the current piece
        g.setColor(game.getBoard().getCurrentPiece().getColor());
        g.drawRoundRect(startX, (int)(8*x), squareSideLength, squareSideLength, 10, 10);
        g.drawRoundRect(startX, (int)(13*x), squareSideLength, squareSideLength, 10, 10);
        g.drawRoundRect(startX, (int)(18*x), squareSideLength, squareSideLength, 10, 10);
    }
}
