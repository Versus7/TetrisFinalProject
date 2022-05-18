package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class TBlock extends Piece {
    public TBlock(int x) {
        super(shapeType.PYRAMID, x, -2);
    }

    @Override
    public void rotateRight() {
        // TODO: Adjust rotation so that it cannot rotate if it is on the bottom
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 1) {
            super.rotateRight();
            // decrementY();
        }
    }

    @Override
    public void rotateLeft() {
        int centerY = getCenterPoint().getX();
        if (centerY < 8 && centerY > 1) {
            super.rotateLeft();
        }
    }
}