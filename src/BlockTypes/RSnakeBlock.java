package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class RSnakeBlock extends Piece {
    public RSnakeBlock(int x) {
        super(shapeType.RSNAKE, x, -2);
    }

    @Override
    public void rotateRight() {
        // TODO: Adjust rotation so that it cannot rotate if it is on the bottom
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 0) {
            super.rotateRight();
        }
    }

    @Override
    public void rotateLeft() {
        int centerY = getCenterPoint().getX();
        if (centerY < 8 && centerY > 1) {
            super.rotateLeft();
            changeY(1);
        }
    }
}