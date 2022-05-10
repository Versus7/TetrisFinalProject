import java.util.ArrayList;

public class Board {
    Block[][] grid = new Block[20][10];
    ArrayList<Piece> allPieces = new ArrayList<Piece>();

    public Board() {
        allPieces.add(new Piece(Piece.shapeType.LSHAPE, 15, 2));
        allPieces.add(new Piece(Piece.shapeType.SQUARE, 7, 7));

    }

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public void clearRow(int rowNum) {

    }

    public void addPiece(Piece a) {

    }

    public boolean containsPoint(Coordinate c) {
        for (Piece p: allPieces) {
            if (p.containsPoint(c)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String result = "";

        for (int i = 0; i < 20; i++) {
            for (int j = 0;j < 10; j++) {
                result += grid[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}