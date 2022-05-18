package Models;
import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private Piece currentPiece = ShapeInitializer.getRandomPiece(5);

    public Board() {}

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void generateNewPiece() {
        allPieces.add(currentPiece);
        currentPiece = ShapeInitializer.getRandomPiece(5);
        // currentPiece = new IBlock(5);
        clearRow(checkRowFull());
    }

    public void clearRow(ArrayList<Integer> rowsToClear) {
       if (rowsToClear.size() <= 0) {
           return;
       }
       System.out.println(rowsToClear.toString());

        for (int row: rowsToClear) {
            // remove rows
            for (int i = 0; i < getAllPieces().size(); i++) {
                if (getAllPieces().get(i).removeInRow(row)) {
                    getAllPieces().remove(i);
                    i--;
                }
            }
            
            for (int j = 0; j < getAllPieces().size(); j++) {
                if (getAllPieces().get(j).getLowestPoints().get(0).getY() < row) {
                    movePieceDown(getAllPieces().get(j));
                }
            }
        }
    }

    /**
     * Method checks every row to see if rows are full
     * If a row is full, the row is added to an ArrayList
     * @return ArrayList with indices of rows to remove
     */
    public ArrayList<Integer> checkRowFull() {
        ArrayList<Integer> rowToClear = new ArrayList<Integer>();
        for (int r = 0; r < 20; r++) {
            int numInRow = 0;
            for (int i = 0; i < 10; i++) {
                if (containsPoint(new Coordinate(i, r))) {
                    numInRow++;
                }
            }

            if (numInRow == 10) {
                rowToClear.add(r);
            }
        }
        return rowToClear;
    }

    public boolean containsPoint(Coordinate c) {
        for (Piece p: allPieces) {
            if (p.containsPoint(c)) {
                return true;
            }
        }
        return false;
    }
    
    // this overridden method of containsPoint checks the points of everything except the piece passed in
    public boolean containsPoint(Coordinate c, Piece p) {
        for (Piece piece: allPieces) {
            if (piece == p) {
                continue;
            }

            if (piece.containsPoint(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean movePieceDown(Piece a) {
        for (Block b: a.getShape()) {
            Coordinate c = b.getCoords();
            if (containsPoint(new Coordinate(c.getX(), c.getY() + 1), a) || c.getY() == 19) {
                generateNewPiece();
                return true;
            }
        }

        a.decrementY();

        return false;
    }

    public void movePieceRight() {
        for (Block b: currentPiece.getShape()) {
            if (containsPoint(new Coordinate(b.getCoords().getX() + 1, b.getCoords().getY()))
            || b.getCoords().getX() > 8) {
                return;
            }
        }

        currentPiece.changeX(1);
    }

    public void movePieceLeft() {
        for (Block b: currentPiece.getShape()) {
            if (containsPoint(new Coordinate(b.getCoords().getX() - 1, b.getCoords().getY()))
            || b.getCoords().getX() < 1) {
                return;
            }
        }

        currentPiece.changeX(-1);
    }
}