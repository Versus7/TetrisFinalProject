import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Piece {
    private ArrayList<Block> shape = new ArrayList<Block>();
    private Color color;

    public Piece(shapeType t, int x, int y) {
        ShapeInitializer.makeShape(t, x, y, shape);
        color = ShapeInitializer.switchColor(t);
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

    public boolean removeInRow(int row) {
        for (int i = 0; i < getShape().size(); i++) {
            if (getShape().get(i).getCoords().getY() == row) {
                getShape().remove(i);
            }
        }
        return getShape().size() == 0;
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
        System.out.println(getShape().toString());
    }

    // TODO: Return an arrayList because there can be multiple lowest points in say like a square
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

    public Coordinate getCenterPoint() {
        int avgX = 0;
        int avgY = 0;

        for (Block b: getShape()) {
            avgX += b.getCoords().getX();
            avgY += b.getCoords().getY();
        }
        return new Coordinate(avgX / 4, avgY / 4);
    }
    
    private void rotate(double d) {
        Coordinate originalLowest = getCenterPoint();
        System.out.println("Original center: " + originalLowest);
        double degrees = Math.toRadians(d);
        for (int i = 0; i < 4; i++) {
            int originalX = getShape().get(i).getCoords().getX();
            int originalY = getShape().get(i).getCoords().getY();

            getShape().get(i).getCoords().setX(Math.abs((int)(originalX*Math.cos(degrees)-originalY*Math.sin(degrees))));
            getShape().get(i).getCoords().setY(Math.abs((int)(originalX*Math.sin(degrees)+originalY*Math.cos(degrees))));

        }

        // fix values
        System.out.println("Rotated center: " + getCenterPoint());
        int offsetX = originalLowest.getX() - getCenterPoint().getX();
        int offsetY = originalLowest.getY() - getCenterPoint().getY();

        for (int i = 0; i < 4; i++) {
            getShape().get(i).changeX(offsetX);
            getShape().get(i).changeY(offsetY);
        }
        System.out.println("Rotated: " + getShape().toString());
    }

    public void rotateRight() {
        rotate(-90);
    }

    public void rotateLeft() {
        rotate(90);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Block b: shape) {
            b.draw(g);
        }
    }
}