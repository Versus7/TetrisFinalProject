package Models;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Piece {
    private ArrayList<Block> shape = new ArrayList<Block>();
    private Color color;
    private int rotatedAngle = 0;

    public Piece(shapeType t, int x, int y) {
        ShapeInitializer.makeShape(t, x, y, shape);
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

    public ArrayList<Coordinate> getLowestPoints() {
        ArrayList<Coordinate> lowestPieces = new ArrayList<Coordinate>();
        int lowest = Integer.MIN_VALUE;

        // find the lowest value
        for (Block c: shape) {
            if (c.getCoords().getY() > lowest) {
                lowest = c.getCoords().getY();
            }
        }

        // add blocks with the lowest value to an arraylist
        for (Block c: shape) {
            if (c.getCoords().getY() == lowest) {
                lowestPieces.add(c.getCoords());
            }
        }

        // return the array list
        return lowestPieces;
    }

    public int getLeftmostCoordinate() {
        int left = Integer.MAX_VALUE;

        for (Block b: getShape()) {
            if (b.getCoords().getX() < left) {
                left = b.getCoords().getX();
            }
        }

        return left;
    }

    public int getRightmostCoordinate() {
        int right = Integer.MIN_VALUE;

        for (Block b: getShape()) {
            if (b.getCoords().getX() > right) {
                right = b.getCoords().getX();
            }
        }

        return right;
    }

    public Coordinate getCenterPoint() {
        int avgX = 0;
        int avgY = 0;

        for (Block b: getShape()) {
            avgX += b.getCoords().getX();
            avgY += b.getCoords().getY();
        }
        return new Coordinate(avgX / 4, avgY / 4);
    }

    // Setters, Other Methods
    public void setShape(ArrayList<Block> s) {
        shape.clear();
        for (Block b: s) {
            shape.add(new Block(b.getCoords().getX(), b.getCoords().getY()));
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
    public void rotate(double d) {
        Coordinate center = getCenterPoint();
        double centerX = center.getX();
        double centerY = center.getY();

        double degrees = Math.toRadians(d);

        for (int i = 0; i < 4; i++) {
            double originalX = getShape().get(i).getCoords().getX();
            double originalY = getShape().get(i).getCoords().getY();

            double calculatedX = originalX * Math.cos(degrees) - originalY * Math.sin(degrees);
            double calculatedY = originalX * Math.sin(degrees) + originalY * Math.cos(degrees);

            getShape().get(i).getCoords().setX(
                (int)(Math.round(calculatedX))
            );

            getShape().get(i).getCoords().setY(
                (int)(Math.round(calculatedY))
            );
        }

        changeX((int)Math.floor(centerX - getCenterPoint().getX()));
        changeY((int)Math.ceil(centerY - getCenterPoint().getY()));
    }

    public void rotateRight() {
        rotate(90);
        rotatedAngle += 90;
        changeY(1);
        changeX(1);

        checkBounds();
    }

    public void rotateLeft() {
        rotate(-90);
        rotatedAngle -= 90;

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
        if (getLowestPoints().get(0).getY() > 19) {
            changeY(-1*(getLowestPoints().get(0).getY() - 19));
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Block b: shape) {
            b.draw(g);
        }
    }
}