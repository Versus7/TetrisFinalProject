package Models;
import java.util.ArrayList;

public class Board {
    private ArrayList<Block> allBlocks = new ArrayList<Block>();
    private Piece currentPiece = ShapeInitializer.getRandomPiece();
    
    private Piece heldPiece;
    private Piece[] upcomingPieces = new Piece[3];
    private Piece ghostPiece = ShapeInitializer.parsePiece(currentPiece.getClass().getSimpleName());
    private boolean gameOver = false;
    private boolean didHold = false;

    private Stats stats = new Stats();

    public Board() {
        upcomingPieces[0] = ShapeInitializer.getRandomPiece();
        upcomingPieces[1] = ShapeInitializer.getRandomPiece();
        upcomingPieces[2] = ShapeInitializer.getRandomPiece();

        while (upcomingPieces[2].getClass().getSimpleName().equals(upcomingPieces[1].getClass().getSimpleName()) && upcomingPieces[1].getClass().getSimpleName().equals(upcomingPieces[0].getClass().getSimpleName())) {
            upcomingPieces[2] = ShapeInitializer.getRandomPiece();
        }

        ghostPiece.changeY(10);
        ghostPiece.setCustomOpacity(0.5f);
    }

    // Getters
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

    public boolean gameStatus() {
        return gameOver;
    }

    // Setters, Other Methods

    public void generateNewPiece() {
        for (Block b: currentPiece.getShape()) {
            allBlocks.add(b);

            if (b.getCoords().getY() <= 0) {
                gameOver = true;
            }
        }

        shiftPieces();
        clearRow(checkRowFull());
        updateGhost();
        
        didHold = false;
    }

    /*
     * Method is called when a piece is dropped.
     * It sets the next piece to the first piece in the "upcoming pieces" list, and shifts the list up.
     */
    private void shiftPieces() {
        int idealYValue = currentPiece.getClass().getSimpleName().equals("IBlock") ? -3 : -2;
        currentPiece = ShapeInitializer.parsePiece(upcomingPieces[0].getClass().getSimpleName());
        currentPiece.changeY(-1*currentPiece.getCenterPoint().getY()+idealYValue+1);

        upcomingPieces[0] = ShapeInitializer.parsePiece(upcomingPieces[1].getClass().getSimpleName());
        upcomingPieces[1] = ShapeInitializer.parsePiece(upcomingPieces[2].getClass().getSimpleName());
        upcomingPieces[2] = ShapeInitializer.getRandomPiece();

        // prevents the upcoming from having 3 of the same piece type
        while (upcomingPieces[2].getClass().getSimpleName().equals(upcomingPieces[1].getClass().getSimpleName()) && upcomingPieces[1].getClass().getSimpleName().equals(upcomingPieces[0].getClass().getSimpleName())) {
            upcomingPieces[2] = ShapeInitializer.getRandomPiece();
        }
    }

    // Method swaps the current piece and held piece, if the player has not already held in the same turn
    public void holdPiece() {
        if (didHold) {
            return;
        }

        if (heldPiece == null) { // holding pieces for the first time
            heldPiece = currentPiece;
            didHold = true;
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

        didHold = true;
    }

    /**
     * Clears rows that are full, and moves blocks above those rows downwards
     * @param rowsToClear the indices of the rows that are full
     */
    private void clearRow(ArrayList<Integer> rowsToClear) {
       if (rowsToClear.size() <= 0) {
           return;
       }

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
     * Method checks every row to see if the row is full
     * If a row is full, the row is added to an ArrayList
     * @return ArrayList with indices of rows to remove
     */
    private ArrayList<Integer> checkRowFull() {
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
    private boolean containsPoint(Coordinate c) {
        for (Block b: allBlocks) {
            if (b.getCoords().getX() == c.getX() && b.getCoords().getY() == c.getY()) {
                return true;
            }
        }
        return false;
    }
    
    // Methods to move pieces

    /**
     * Method checks if the piece can move down
     * @param a piece to move down
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

    // Repeats the movePieceDown method until the piece cannot move down any further
    public void dropPieceCompletely(Piece a, boolean forCurrentPiece) {
        int distance = 0;
        while (true) {
            for (Block b: a.getShape()) {
                Coordinate c = b.getCoords();
                if (containsPoint(new Coordinate(c.getX(), c.getY() + 1)) || c.getY() == 19) {
                    if (forCurrentPiece) {
                        stats.hardDrop(distance);
                    }
                    return;
                }
            }

            a.changeY(1);
            distance++;
        }
     }

     // Updates ghost piece with all properties of current piece
    public void updateGhost() {
        if (!ghostPiece.getClass().getSimpleName().equals(currentPiece.getClass().getSimpleName())) {
            ghostPiece = ShapeInitializer.parsePiece(currentPiece.getClass().getSimpleName());
            ghostPiece.setCustomOpacity(0.6f);
        } else {
            ghostPiece.changeY(-20);
        }

        while (ghostPiece.getRotatedAngle() < currentPiece.getRotatedAngle()) {
            ghostPiece.rotateRight();
        }
        while (ghostPiece.getRotatedAngle() > currentPiece.getRotatedAngle()) {
            ghostPiece.rotateLeft();
        }
        
        ghostPiece.changeX(currentPiece.getLeftmostCoordinate() - ghostPiece.getLeftmostCoordinate());
        ghostPiece.changeY(currentPiece.getCenterPoint().getY()-ghostPiece.getCenterPoint().getY());
        dropPieceCompletely(ghostPiece, false);
    }

    // Moving pieces to the side
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

    // Helper method for rotation methods, determines if a rotation is legal or not
    private boolean isValidPiece(Piece p) {
        for (Block b: p.getShape()) {
            if (containsPoint(b.getCoords())) {
                return false;
            }
        }
        return true;
    }

    // Rotation methods
    public void rotatePieceRight() {
        Piece temp = ShapeInitializer.parsePiece(currentPiece.getClass().getSimpleName());
        temp.setShape(currentPiece.getShape());

        temp.rotateRight();
        if (isValidPiece(temp)) {
            currentPiece.rotateRight();
        }
    }

    public void rotatePieceLeft() {
        Piece temp = ShapeInitializer.parsePiece(currentPiece.getClass().getSimpleName());
        temp.setShape(currentPiece.getShape());
        temp.rotateLeft();
        if (isValidPiece(temp)) {
            currentPiece.rotateLeft();
        }   
    }
}