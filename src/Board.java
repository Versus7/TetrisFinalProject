import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private Piece currentPiece = new Piece(Piece.shapeType.LSHAPE, 5, 0);

    public Board() {
        allPieces.add(new Piece(Piece.shapeType.LSHAPE, 15, 2));
        allPieces.add(new Piece(Piece.shapeType.SQUARE, 7, 7));

    }

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void generateNewPiece() {
        allPieces.add(currentPiece);
        currentPiece = new Piece(Piece.shapeType.LSHAPE, 7, 0);
    }

    public void clearRow(int rowNum) {

    }

    public void addPiece(Piece a) {
        allPieces.add(a);
    }

    public boolean containsPoint(Coordinate c) {
        for (Piece p: allPieces) {
            if (p.containsPoint(c)) {
                return true;
            }
        }
        return false;
    }

    public void movePieceDown() {
        // TODO: Add condition checking if piece is at the bottom of the game thing
        if (!containsPoint(new Coordinate(currentPiece.getLowestPoint().getX(), currentPiece.getLowestPoint().getY()+1))) {
            System.out.println("No block detected!");
            currentPiece.decrementY();
        } else {
            System.out.println("Block detected below! No movement");
            generateNewPiece();
        }
    }

    public void movePieceRight() {

    }
}