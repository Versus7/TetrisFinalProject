package Models;
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
        // System.out.println("Block shape: " + getShape().toString());
        for (int i = 0; i < getShape().size(); i++) {
            if (getShape().get(i).getCoords().getY() == row) {
                getShape().remove(i);
                i--;
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
        // System.out.println(getShape().toString());
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
        Coordinate center = getCenterPoint();
        double centerX = center.getX();
        double centerY = center.getY();

        // System.out.println("Center: " + center);

        double degrees = Math.toRadians(d);

        for (int i = 0; i < 4; i++) {
            double originalX = getShape().get(i).getCoords().getX();
            double originalY = getShape().get(i).getCoords().getY();

            double calculatedX = originalX*Math.cos(degrees)-originalY*Math.sin(degrees) + 
            centerX - (centerX*Math.cos(degrees)-centerY*Math.sin(degrees));
            double calculatedY = originalX*Math.sin(degrees)+originalY*Math.cos(degrees) +
            centerY - (centerX*Math.sin(degrees)+centerY*Math.cos(degrees));

            // System.out.println("New x (double): " + calculatedX);
            // System.out.println("New y (double): " + calculatedY);

            // System.out.print(getShape().get(i) + " --> ");

            getShape().get(i).getCoords().setX(
                (int)(Math.round(calculatedX))
            );

            getShape().get(i).getCoords().setY(
                (int)(Math.round(calculatedY))
            );

            // System.out.print(getShape().get(i));
            // System.out.println();

        }
    }

    public void rotateRight() {
        // System.out.println("Rotating right!");
        rotate(90);
        decrementY();
    }

    public void rotateLeft() {
        // System.out.println("Rotating left!");
        rotate(-90);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for (Block b: shape) {
            b.draw(g);
        }
    }
}