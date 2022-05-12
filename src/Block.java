import java.awt.Graphics;

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
        g.fillRect(coordinate.getX()*TetrisWindowPanel.SQUAREWIDTH, coordinate.getY()*TetrisWindowPanel.SQUAREWIDTH, TetrisWindowPanel.SQUAREWIDTH, TetrisWindowPanel.SQUAREWIDTH);
    }
}