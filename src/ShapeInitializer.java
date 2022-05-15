import java.util.ArrayList;
import java.awt.Color;

public class ShapeInitializer {
    public static void makeShape(shapeType t, int x, int y, ArrayList<Block> shape) {
        Coordinate c = new Coordinate(x, y);
        shape.add(new Block(c.getX(), c.getY()));
        switch (t) {
            case LINE:
                shape.add(new Block(c.getX(), c.getY() + 1));
                shape.add(new Block(c.getX(), c.getY() + 2));
                shape.add(new Block(c.getX(), c.getY() + 3));
                break;
            case SQUARE:
                shape.add(new Block(c.getX() + 1, c.getY()));
                shape.add(new Block(c.getX(), c.getY()+1));
                shape.add(new Block(c.getX()+1, c.getY()+1));
                break;
            case PYRAMID:
                shape.add(new Block(c.getX() - 1, c.getY() + 1));
                shape.add(new Block(c.getX() + 1, c.getY() + 1));
                shape.add(new Block(c.getX(), c.getY() + 1));
                break;
            case RSNAKE:
                shape.add(new Block(c.getX() + 1, c.getY()));
                shape.add(new Block(c.getX(), c.getY() + 1));
                shape.add(new Block(c.getX() - 1, c.getY()+1));
                break;
            case LSNAKE:
                shape.add(new Block(c.getX() - 1, c.getY()));
                shape.add(new Block(c.getX(), c.getY() + 1));
                shape.add(new Block(c.getX() + 1, c.getY()+1));
                break;
            case LEFTL:
                shape.add(new Block(c.getX(), c.getY()+1));
                shape.add(new Block(c.getX() + 1, c.getY() + 1));
                shape.add(new Block(c.getX() + 2, c.getY()+1));
                break;
            case RIGHTL:
                shape.add(new Block(c.getX(), c.getY()+1));
                shape.add(new Block(c.getX() - 1, c.getY() + 1));
                shape.add(new Block(c.getX() - 2, c.getY()+1));
                break;
            default:
                System.out.println("A block without one of the defined types has been created");
        }
    }   

    public static Color switchColor(shapeType t) {
        switch (t) {
            case LINE:
                return Color.cyan;
            case SQUARE:
                return Color.yellow;
            case PYRAMID:
                return Color.getHSBColor(300, 100, 50);
            case RSNAKE:
                return Color.green;
            case LSNAKE:
                return Color.red;
            case LEFTL:
                return Color.blue;
            case RIGHTL:
                return Color.orange;
            default:
                return Color.black;
        }
    }
}