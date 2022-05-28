package Models;
import java.util.ArrayList;
import BlockTypes.*;
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
                float[] colorValues = Color.RGBtoHSB(146, 5, 240, null);
                return Color.getHSBColor(colorValues[0], colorValues[1], colorValues[2]);
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

    public static Piece getRandomPiece(int x) {
        int index = (int)(Math.random()*7)+1;

        switch(index) {
            case 1:
                return new IBlock(x);
            case 2:
                return new SquareBlock(x);
            case 3:
                return new TBlock(x);
            case 4:
                return new RSnakeBlock(x);
            case 5:
                return new LSnakeBlock(x);
            case 6:
                return new LeftL(x);
            case 7:
                return new RightL(x);
            default:
                return null;
        }
    }

    public static Piece parsePiece(String s) {
        switch (s) {
            case "IBlock":
                return new IBlock(5);
            case "LeftL":
                return new LeftL(5);
            case "LSnakeBlock":
                return new LSnakeBlock(5);
            case "RightL":
                return new RightL(5);
            case "RSnakeBlock":
                return new RSnakeBlock(5);
            case "SquareBlock":
                return new SquareBlock(5);
            case "TBlock":
                return new TBlock(5);
            default:
                return null;
        }
    }
}
