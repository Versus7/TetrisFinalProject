import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private Piece currentPiece = new Piece(Piece.shapeType.LSHAPE, 5, 0);

    public Board() {}

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void generateNewPiece() {
        allPieces.add(currentPiece);
        currentPiece = new Piece(Piece.shapeType.LSHAPE, (int)(Math.random()*10), -3);
        clearRow(checkRowFull());
    }

    public void clearRow(ArrayList<Integer> rowsToClear) {
       if (rowsToClear.size() <= 0) {
           return;
       } 

       for (int row: rowsToClear) {
           // remove rows

           for (Piece p: getAllPieces()) {
               p.removeInRow(row);
           }

           // move above rows, down
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

    public Piece containsPoint(Coordinate c, boolean a) {
        Piece found = null;
        for (Piece p: allPieces) {
            if (p.containsPoint(c)) {
              found = p;  
            }
        }
        return found;
    }

    public void movePieceDown(Piece a) {
        // TODO: Fix condition checking if piece is at the bottom of the game thing
        // System.out.println("(" + currentPiece.getLowestPoint().getX() + ", " + currentPiece.getLowestPoint().getY() + ")");
        if (!containsPoint(new Coordinate(a.getLowestPoint().getX(), a.getLowestPoint().getY()+1))
         && a.getLowestPoint().getY() < 19) {
            // System.out.println("No block detected!");
            a.decrementY();
        } else {
            // System.out.println("Block detected below! No movement");
            generateNewPiece();
        }
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
    }}