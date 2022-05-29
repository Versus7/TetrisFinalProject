package Models;
import java.util.ArrayList;

public class Board {
    /**
     * consider switching to a 2D array
     */
    private ArrayList<Block> allBlocks = new ArrayList<Block>();
    private Piece currentPiece = ShapeInitializer.getRandomPiece(5);
    
    private Piece heldPiece;
    private Piece[] upcomingPieces = new Piece[3];
    private Piece ghostPiece = ShapeInitializer.parsePiece(currentPiece.getClass().getSimpleName());
    private boolean gameOver = false;

    private Stats stats = new Stats();

    public Board() {
        upcomingPieces[0] = ShapeInitializer.getRandomPiece(5);
        upcomingPieces[1] = ShapeInitializer.getRandomPiece(5);
        upcomingPieces[2] = ShapeInitializer.getRandomPiece(5);

        ghostPiece.changeY(10);
    }

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Stats getStats() {
        return stats;
    }

    public Piece getHeldPiece() {
        return heldPiece;
    }

    public Piece[] getUpcomingPieces() {
        return upcomingPieces;
    }

    public Piece getGhostPiece() {
        return ghostPiece;
    }

    public void generateNewPiece() {
        for (Block b: currentPiece.getShape()) {
            allBlocks.add(b);

            if (b.getCoords().getY() <= 0) {
                gameOver = true;
            }
        }

        shiftPieces();
        clearRow(checkRowFull());
    }

    public void shiftPieces() {
        int idealYValue = currentPiece.getClass().getSimpleName().equals("IBlock") ? -3 : -2;
        currentPiece = ShapeInitializer.parsePiece(upcomingPieces[0].getClass().getSimpleName());
        currentPiece.changeY(-1*currentPiece.getCenterPoint().getY()+idealYValue+1);

        upcomingPieces[0] = ShapeInitializer.parsePiece(upcomingPieces[1].getClass().getSimpleName());
        upcomingPieces[1] = ShapeInitializer.parsePiece(upcomingPieces[2].getClass().getSimpleName());
        upcomingPieces[2] = ShapeInitializer.getRandomPiece(5);

    }

    public boolean gameStatus() {
        return gameOver;
    }

    public void holdPiece() {
        if (heldPiece == null) { // holding pieces for the first time
            heldPiece = currentPiece;
            shiftPieces();
            return;
        }
            
        // Doesn't swap if the pieces are the same
        if (heldPiece.getClass().getSimpleName().equals(currentPiece.getClass().getSimpleName())) {
            return;
        }

        // Swaps the pieces
        String currentName = currentPiece.getClass().getSimpleName();
        String heldName = heldPiece.getClass().getSimpleName();
        currentPiece = ShapeInitializer.parsePiece(heldName);
        heldPiece = ShapeInitializer.parsePiece(currentName);
    }

    /**
     * Clears rows that are full, and moves blocks above downwards
     * @param rowsToClear the indices of the rows that are full
     */
    public void clearRow(ArrayList<Integer> rowsToClear) {
       if (rowsToClear.size() <= 0) {
           return;
       }

    //    System.out.println("Rows to clear: " + rowsToClear.toString());
    //    System.out.println("Size of rows to clear: " + (rowsToClear.size()-1));
       stats.incrementScore(rowsToClear.size());

        for (int row: rowsToClear) {
            for (int i = 0; i < allBlocks.size(); i++) {
                if (allBlocks.get(i).getCoords().getY() == row) {
                    allBlocks.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < allBlocks.size(); i++) {
                if (allBlocks.get(i).getCoords().getY() < row) {
                    allBlocks.get(i).changeY(1);
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

    /**
     * Method checks if a point is occupied or not by a block
     * @param c the coordinate to check
     * @return true if the point is occupied, false otherwise
     */
    public boolean containsPoint(Coordinate c) {
        for (Block b: allBlocks) {
            if (b.getCoords().getX() == c.getX() && b.getCoords().getY() == c.getY()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method checks if the piece can move down
     * @param a piece to move down, if possible
     * @return true if the piece can move down, false otherwise
     */
    public boolean movePieceDown(Piece a) {
        for (Block b: a.getShape()) {
            Coordinate c = b.getCoords();
            if (containsPoint(new Coordinate(c.getX(), c.getY() + 1)) || c.getY() == 19) {
                generateNewPiece();
                return true;
            }
        }

        a.changeY(1);

        return false;
    }

    public void dropPieceCompletely(Piece a) {
        while (true) {
            for (Block b: a.getShape()) {
                Coordinate c = b.getCoords();
                if (containsPoint(new Coordinate(c.getX(), c.getY() + 1)) || c.getY() == 19) {
                    return;
                }
            }

            a.changeY(1);
        }
    }

    public void redrawGhost() {
        if (!getGhostPiece().getClass().getSimpleName().equals(getCurrentPiece().getClass().getSimpleName())) {
            ghostPiece = ShapeInitializer.parsePiece(getCurrentPiece().getClass().getSimpleName());
        } else {
            ghostPiece.changeY(-20);
        }
        
        if (getCurrentPiece().getRotatedAngle() > ghostPiece.getRotatedAngle()) {
            ghostPiece.rotateRight();
        } else if (getCurrentPiece().getRotatedAngle() < ghostPiece.getRotatedAngle()) {
            ghostPiece.rotateLeft();
        }

        ghostPiece.changeX(getCurrentPiece().getLeftmostCoordinate() - ghostPiece.getLeftmostCoordinate());
        ghostPiece.changeY(getCurrentPiece().getCenterPoint().getY()-ghostPiece.getCenterPoint().getY());
        dropPieceCompletely(ghostPiece);
    }

    /**
     * Method moves blocks down if possible
     * Method is called when a row is to be cleared
     * It moves all blocks above the row downwards if possible
     * @param b block to move down
     */
    public void moveBlockDown(Block b) {
        if (containsPoint(new Coordinate(b.getCoords().getX(), b.getCoords().getY() + 1)) || b.getCoords().getY() == 19) {
            return;
        }
        b.changeY(1);
    }

    /**
     * The following two methods attempt to move a piece right or left
     * If there is a piece in the way, or it is reaching the borders of the grid, the piece will not move
     */
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

    public void rotatePieceRight() {
        currentPiece.rotateRight();
    }

    // define the isValidPiece method
    public boolean isValidPiece(Piece p) {
        for (Block b: p.getShape()) {
            if (containsPoint(b.getCoords())) {
                return false;
            }
        }
        return true;
    }

    public void rotatePieceLeft() {
        currentPiece.rotateLeft();
    }
}