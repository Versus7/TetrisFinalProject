import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Piece {
    public static enum shapeType {
        LSHAPE,
        SQUARE
    }

    private enum Direction {
        LEFT,
        RIGHT,
        DOWN
    }

    private ArrayList<Block> shape = new ArrayList<Block>();
    private static Color[] possibleColors = new Color[] {Color.blue, Color.orange, Color.yellow, Color.green, Color.red, Color.CYAN};
    private Color color = possibleColors[(int)(Math.random()*possibleColors.length)];

    // center point to base the other points off of
    private Coordinate c;
    public Piece(shapeType t, int x, int y) {
        c = new Coordinate(x, y);
        
        switch (t) {
            case LSHAPE:
                // System.out.println("An l shaped block has been created!");
                shape.add(new Block(c.getX(), c.getY()));
                shape.add(new Block(c.getX(), c.getY() + 1));
                shape.add(new Block(c.getX(), c.getY() + 2));
                shape.add(new Block(c.getX(), c.getY() + 3));
                break;
            case SQUARE:
                // System.out.println("A square shaped block has been created!");
                shape.add(new Block(c.getX(), c.getY()));
                shape.add(new Block(c.getX() + 1, c.getY()));
                shape.add(new Block(c.getX(), c.getY()+1));
                shape.add(new Block(c.getX()+1, c.getY()+1));
                break;
            default:
                System.out.println("Some other type has been created!");
        }
    }

    public ArrayList<Block> getShape() {
        return shape;
    }

    public boolean containsPoint(Coordinate c) {
        for (Block b: shape) {
            if (b.getCoords().getX() == c.getX() && b.getCoords().getY() == c.getY()) {
                return true;
            }
        }
        return false;
    }

    public void changeX(int amount) {
        for (int i = 0; i < shape.size(); i++) {
            shape.get(i).changeX(amount);
        }
    }

    public void decrementY() {
        for (int i = 0; i < shape.size(); i++) {
            shape.get(i).changeY(1);
        }
    }

    public Coordinate getLowestPoint() {
        int lowest = shape.get(0).getCoords().getY();
        Coordinate lowestCoords = shape.get(0).getCoords();

        for (Block c: shape) {
            if (c.getCoords().getY() > lowest) {
                lowest = c.getCoords().getY();
                lowestCoords = c.getCoords();
            }
        }

        return lowestCoords;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Block b: shape) {
            b.draw(g);
        }
    }

    // public boolean withinBounds(Direction d) {
    //     if (d == Direction.RIGHT) {
    //         for (Block b: shape) {
    //             if (b.coordinate.getX() > 8) {
    //                 return false;
    //             }
    //         }
    //         return true;
    //     } else if (d == Direction.LEFT) {
    //         for (Block b: shape) {
    //             if (b.coordinate.getX() < 1) {
    //                 return false;
    //             }
    //         }

    //         return true;
    //     } else {
    //         for (Block b: shape) {
    //             if (b.coordinate.getY() > 18) {
    //                 return false;
    //             }
    //         }

    //         return true;
    //     }
    // }

}