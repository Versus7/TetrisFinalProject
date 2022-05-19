package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class IBlock extends Piece {
    public IBlock(int x) {
        super(shapeType.LINE, x, -3);
    }

    @Override
    public void rotateRight() {
        int centerX = getCenterPoint().getX();
        int centerY = getCenterPoint().getY();
        if (centerX < 9 && centerX > 0 && centerY < 18) {
            super.rotateRight();
            changeY(-1);
        }
    }

    @Override
    public void rotateLeft() {
        int centerX = getCenterPoint().getX();
        int centerY = getCenterPoint().getY();
        if (centerX < 8 && centerX > 0 && centerY < 18) {
            super.rotateLeft();
        }
    }
}