package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class IBlock extends Piece {
    public IBlock(int x) {
        super(shapeType.LINE, x, -3);
    }

    @Override
    public void rotateRight() {
        // TODO: Adjust rotation so that it cannot rotate if it is on the bottom
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 1) {
            super.rotateRight();
            decrementY();
        }
    }

    @Override
    public void rotateLeft() {
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 1) {
            super.rotateLeft();
        }
    }
}