import java.awt.Graphics;
import java.awt.Color;

public class Piece {
    public static enum shapeType {
        LSHAPE,
        SQUARE
    }

    private enum Direction {
        LEFT,
        RIGHT
    }

    private Block[] shape;
    private Color color = Color.blue;


    // center point to base the other points off of
    private Coordinate c;
    public Piece(shapeType t, int x, int y) {
        c = new Coordinate(x, y);
        switch (t) {
            case LSHAPE:
                // System.out.println("An l shaped block has been created!");
                shape = new Block[4];
                shape[0] = new Block(c.getX(), c.getY());
                shape[1] = new Block(c.getX(), c.getY()+1);
                shape[2] = new Block(c.getX(), c.getY()+2);
                shape[3] = new Block(c.getX(), c.getY()+3);
                break;
            case SQUARE:
                // System.out.println("A square shaped block has been created!");
                shape = new Block[4];

                shape[0] = new Block(c.getX(), c.getY());
                shape[1] = new Block(c.getX() + 1, c.getY());
                shape[2] = new Block(c.getX(), c.getY()+1);
                shape[3] = new Block(c.getX()+1, c.getY()+1);
                break;
            default:
                System.out.println("Some other type has been created!");
        }
    }

    public void changeX(int amount) {
        // System.out.println("Evaluating options!");
        Direction toBeMoved = amount > 0 ? Direction.RIGHT : Direction.LEFT;
        if (!withinBounds(toBeMoved)) {
            return;
        }

        // System.out.println("Not near a wall!");
        // if it does satisfy the conditions
        for (int i = 0; i < shape.length; i++) {
            shape[i].changeX(amount);
        }
    }

    public void draw(Graphics g) {
        for (Block b: shape) {
            b.draw(g);
        }
    }

    public boolean withinBounds(Direction d) {
        if (d == Direction.RIGHT) {
            for (Block b: shape) {
                if (b.coordinate.getX() > 8) {
                    return false;
                }
            }
            return true;
        } else {
            for (Block b: shape) {
                if (b.coordinate.getX() < 1) {
                    return false;
                }
            }

            return true;
        }
    }

}