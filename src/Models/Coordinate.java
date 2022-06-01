package Models;

public class Coordinate {
    private int x; 
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters
    public void setX(int newX) {
        x = newX;
    }

    public void setY(int newY) {
        y = newY;
    }

    // Debug to-string method
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}