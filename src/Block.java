import java.awt.Graphics;
import java.awt.Color;

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
        g.setColor(Color.blue);
        g.fillRect(coordinate.getX()*50, coordinate.getY()*50, 50, 50);
    }
}