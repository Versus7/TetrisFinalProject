package Models;
import java.awt.Color;
import java.awt.Graphics;

import Views.TetrisWindowPanel;

public class Block {
    private Coordinate coordinate;
    private Color c;

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

    public void draw(Graphics g) {
        double squareSize = TetrisWindowPanel.SQUAREWIDTH;
        double xCoord = (double)(coordinate.getX() * squareSize);
        double yCoord = (double)(coordinate.getY() * squareSize);

        g.setColor(Color.black);
        g.fillRect((int)(xCoord), (int)(yCoord), (int)(squareSize), (int)(squareSize));

        g.setColor(c);
        g.fillRect((int)(xCoord), (int)(yCoord), (int)(TetrisWindowPanel.SQUAREWIDTH*0.95), (int)(TetrisWindowPanel.SQUAREWIDTH*0.95));
    }

    public String toString() {
        return getCoords().toString();
    }
}