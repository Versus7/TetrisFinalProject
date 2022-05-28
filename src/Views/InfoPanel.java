package Views;
import java.awt.*;
import javax.swing.*;

public class InfoPanel extends JPanel {
    private TetrisWindowPanel game;
    
    public InfoPanel(TetrisWindowPanel game) {
        this.game = game;
        // System.out.println("Window width: " + windowWidth);

        setPreferredSize(new Dimension((int)(150), getHeight()));

        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(game.getBoard().getCurrentPiece().getColor());
        Double x = (getHeight()) / 23.0;
        // System.out.println("InfoPanel height: " + getHeight());
        int startX = (int)(getWidth()*0.1);
        int squareSideLength = (int)(4*x);
        
        setPreferredSize(new Dimension(squareSideLength+50, getHeight()));
        g.drawRoundRect(startX, x.intValue(), squareSideLength, squareSideLength, 10, 10);

        g.drawRoundRect(startX, (int)(8*x), squareSideLength, squareSideLength, 10, 10);
        g.drawRoundRect(startX, (int)(13*x), squareSideLength, squareSideLength, 10, 10);
        g.drawRoundRect(startX, (int)(18*x), squareSideLength, squareSideLength, 10, 10);
    }
}
