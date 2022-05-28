package Models;
import java.util.ArrayList;

public class Board {
    /**
     * consider switching to a 2D array
     */
    private ArrayList<Block> allBlocks = new ArrayList<Block>();
    private Piece currentPiece = ShapeInitializer.getRandomPiece(5);
    private Stats stats = new Stats();

    public Board() {}

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Stats getStats() {
        return stats;
    }

    public void generateNewPiece() {
        for (Block b: currentPiece.getShape()) {
            allBlocks.add(b);
        }

        currentPiece = ShapeInitializer.getRandomPiece(5);
        clearRow(checkRowFull());
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
}