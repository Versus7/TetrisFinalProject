import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> allPieces = new ArrayList<Piece>();
    private Piece.shapeType[] types = Piece.shapeType.values();
    private Piece currentPiece = new Piece(types[(int)(Math.random()*types.length)], 5, 0);

    public Board() {}

    public ArrayList<Piece> getAllPieces() {
        return allPieces;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void generateNewPiece() {
        allPieces.add(currentPiece);
        currentPiece = new Piece(types[(int)(Math.random()*types.length)], 5, -3);
        clearRow(checkRowFull());
    }

    public void clearRow(ArrayList<Integer> rowsToClear) {
        ArrayList<Piece> piecesWithClearedBlocks = new ArrayList<Piece>();
       if (rowsToClear.size() <= 0) {
           return;
       } 

       for (int row: rowsToClear) {
           // remove rows

           for (Piece p: getAllPieces()) {
               for (Coordinate c: p.getLowestPoints()) {
                   if (c.getY() < row) {
                       piecesWithClearedBlocks.add(p);
                       break;
                   }
               }
               if (p.removeInRow(row)) {
                   getAllPieces().remove(p);
               }
           }

           // move above rows, down
       }

       for (int i = 0; i < piecesWithClearedBlocks.size(); i++) {
           movePieceDown(piecesWithClearedBlocks.get(i));
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

    public void movePieceDown(Piece a) {
        for (Coordinate c: a.getLowestPoints()) {
            if (containsPoint(new Coordinate(c.getX(), c.getY() + 1)) || c.getY() == 19) {
                System.out.println("block below, creating new block");
                generateNewPiece();
                return;
            }
        }

        if (a.getLowestPoints().get(0).getY() < 19) {
            System.out.println("moving block down");
            a.decrementY();
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
    }
}