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
        int centerY = getCenterPoint().getY();
        if (centerX < 8 && centerX >= 0 && centerY < 18) {
            super.rotateRight();
            changeX(1);
        }
    }

    @Override
    public void rotateLeft() {
        int centerX = getCenterPoint().getX();
        int centerY = getCenterPoint().getY();
        if (centerX < 8 && centerX > 1 && centerY < 19) {
            super.rotateLeft();
            changeY(1);
        }
    }
}