
public class IBlock extends Piece {
    public IBlock(int x, int y) {
        super(shapeType.LINE, x, y);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void rotateRight() {
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 1) {
            super.rotateRight();
        }
    }

    @Override
    public void rotateLeft() {
        int centerY = getCenterPoint().getY();
        if (centerY < 8 && centerY > 1) {
            super.rotateLeft();
        }
    }
}