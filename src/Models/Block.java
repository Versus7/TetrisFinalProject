package Models;
import java.awt.Color;
import java.awt.Graphics;

import Views.TetrisWindowPanel;

public class Block {
    Coordinate coordinate;

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

    public void draw(Graphics g) {
        Color normal = g.getColor();
        double squareSize = TetrisWindowPanel.SQUAREWIDTH;
        double xCoord = (double)(coordinate.getX() * squareSize);
        double yCoord = (double)(coordinate.getY() * squareSize);

        g.setColor(Color.black);
        g.fillRect((int)(xCoord), (int)(yCoord), (int)(squareSize), (int)(squareSize));

        g.setColor(normal);
        g.fillRect((int)(xCoord), (int)(yCoord), (int)(TetrisWindowPanel.SQUAREWIDTH*0.95), (int)(TetrisWindowPanel.SQUAREWIDTH*0.95));
    }

    public String toString() {
        return getCoords().toString();
    }
}