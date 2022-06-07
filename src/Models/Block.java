package Models;
import java.awt.*;

import Views.TetrisWindowPanel;

// This class is the unit by which pieces are made of.

public class Block {
    private Coordinate coordinate;
    private Color c;
    private double customSize = 0;
    private float customOpacity = 1;

    public Block(int x, int y) {
        coordinate = new Coordinate(x, y);
    }

    public Coordinate getCoords() {
        return coordinate;
    }

    public void changeX(int amount) {
        coordinate.setX(coordinate.getX() + amount);
    }

    public void changeY(int amount) {
        coordinate.setY(coordinate.getY() + amount);
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public void setCustomSize(double d) {
        customSize = d;
    }

    public void setCustomOpacity(float d) {
        customOpacity = d;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        double squareSize;
        if (customSize != 0) {
            squareSize = customSize;
        } else {
            squareSize = TetrisWindowPanel.SQUAREWIDTH;
        }
        double xCoord = (double)(coordinate.getX() * squareSize);
        double yCoord = (double)(coordinate.getY() * squareSize);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f*customOpacity));
        g2.setColor(c);
        g2.fillRect((int)(xCoord), (int)(yCoord), (int)squareSize, (int)squareSize);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f*customOpacity));
        g.fillRect((int)(xCoord), (int)(yCoord), (int)(squareSize*0.85), (int)(squareSize*0.85));

    }

    public String toString() {
        return coordinate.toString();
    }
}