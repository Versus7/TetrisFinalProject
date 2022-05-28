package Models;
import java.util.ArrayList;

public class Board {
    /**
     * consider switching to a 2D array
     */
    private ArrayList<Block> allBlocks = new ArrayList<Block>();
    private Piece currentPiece = ShapeInitializer.getRandomPiece(5);
    private int linesCleared = 0;

    public Board() {}

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public int getLinesCleared() {
        return linesCleared;
    }

    public void generateNewPiece() {
        for (Block b: currentPiece.getShape()) {
            allBlocks.add(b);
        }

        currentPiece = ShapeInitializer.getRandomPiece(5);
        clearRow(checkRowFull());
    }

    public void clearRow(ArrayList<Integer> rowsToClear) {
       if (rowsToClear.size() <= 0) {
           return;
       }

       linesCleared += rowsToClear.size();
       System.out.println(rowsToClear.toString());

        for (int row: rowsToClear) {
            // remove rows
            int blocksRemoved = 0;
            for (int i = 0; i < allBlocks.size(); i++) {
                if (allBlocks.get(i).getCoords().getY() == row) {
                    // System.out.println("removing block number: " + i);
                    allBlocks.remove(i);
                    i--;
                    blocksRemoved++;
                }
            }
            System.out.println("blocks removed: " + blocksRemoved);

            // shift all blocks down using moveBLock method
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

    public boolean containsPoint(Coordinate c) {
        for (Block b: allBlocks) {
            if (b.getCoords().getX() == c.getX() && b.getCoords().getY() == c.getY()) {
                return true;
            }
        }
        return false;
    }
    
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

    public void moveBlockDown(Block b) {
        if (containsPoint(new Coordinate(b.getCoords().getX(), b.getCoords().getY() + 1)) || b.getCoords().getY() == 19) {
            return;
        }
        b.changeY(1);
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