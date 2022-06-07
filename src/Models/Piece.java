package Models;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Piece {
    private ArrayList<Block> shape = new ArrayList<Block>();
    private Color color;
    private int rotatedAngle = 0;
    private double customSize;
    private float customOpacity;

    public Piece(shapeType t, int y) {
        ShapeInitializer.makeShape(t, y, shape);
        color = ShapeInitializer.switchColor(t);

        for (Block b: shape) {
            b.setColor(color);
        }
    }

    // Getters
    public ArrayList<Block> getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getRotatedAngle() {
        return rotatedAngle;
    }

    public int getLowestPoint() {
        int lowest = Integer.MIN_VALUE;

        // find the lowest value
        for (Block c: shape) {
            if (c.getCoords().getY() > lowest) {
                lowest = c.getCoords().getY();
            }
        }
        return lowest;
    }

    // Used by Board to align ghost piece and current piece
    public int getLeftmostCoordinate() {
        int left = Integer.MAX_VALUE;

        for (Block b: shape) {
            if (b.getCoords().getX() < left) {
                left = b.getCoords().getX();
            }
        }

        return left;
    }

    public int getRightmostCoordinate() {
        int right = Integer.MIN_VALUE;

        for (Block b: shape) {
            if (b.getCoords().getX() > right) {
                right = b.getCoords().getX();
            }
        }

        return right;
    }

    public Coordinate getCenterPoint() {
        double avgX = 0;
        double avgY = 0;

        for (Block b: shape) {
            avgX += b.getCoords().getX();
            avgY += b.getCoords().getY();
        }
        return new Coordinate((int)Math.round(avgX / 4.0), (int)Math.round(avgY / 4.0));
    }

    // Setters, Other Methods
    public void setShape(ArrayList<Block> s) {
        shape.clear();
        for (Block b: s) {
            shape.add(new Block(b.getCoords().getX(), b.getCoords().getY()));
        }
    }

    public void setCustomSize(double s) {
        customSize = s;

        for (int i = 0; i < shape.size(); i++) {
            shape.get(i).setCustomSize(customSize);
        }
    }

    public void setCustomOpacity(float f) {
        customOpacity = f;

        for (int i = 0; i < shape.size(); i++) {
            shape.get(i).setCustomOpacity(customOpacity);
        }
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

    public void changeY(int amount) {
        for (int i = 0; i < shape.size(); i++) {
            shape.get(i).changeY(amount);
        }
    }

    // Methods for Rotation
    private void rotate(double d) {
        Coordinate center = getCenterPoint();
        double centerX = center.getX();
        double centerY = center.getY();

        double degrees = Math.toRadians(d);

        for (int i = 0; i < 4; i++) {
            double originalX = shape.get(i).getCoords().getX();
            double originalY = shape.get(i).getCoords().getY();

            double calculatedX = originalX * Math.cos(degrees) - originalY * Math.sin(degrees);
            double calculatedY = originalX * Math.sin(degrees) + originalY * Math.cos(degrees);

            shape.get(i).getCoords().setX(
                (int)(Math.round(calculatedX))
            );

            shape.get(i).getCoords().setY(
                (int)(Math.round(calculatedY))
            );
        }

        changeX((int)Math.floor(centerX - getCenterPoint().getX()));
        changeY((int)Math.ceil(centerY - getCenterPoint().getY()));

        rotatedAngle += d;
    }

    public void rotateRight() {
        rotate(90);
        checkBounds();
    }

    public void rotateLeft() {
        rotate(-90);
        checkBounds();
    }

    // This method checks if the piece extends beyond the bounds of the grid. If it does, it will adjust the coordinates accordingly.
    public void checkBounds() {
        // check right side of board
        if (getRightmostCoordinate() > 8) {
            changeX(-1*(getRightmostCoordinate() - 9));
        }

        if (getRightmostCoordinate() == 8) {
            changeX(1);
        }

        // checking left side of the board
        if (getLeftmostCoordinate() < 0) {
            changeX(1-getLeftmostCoordinate());
        }

        if (getLeftmostCoordinate() == 1) {
            changeX(-1);
        }

        // checking bottom of the board
        if (getLowestPoint() > 19) {
            changeY(-1*(getLowestPoint() - 19));
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Block b: shape) {
            b.draw(g);
        }
    }
}