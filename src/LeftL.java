public class LeftL extends Piece {
    public LeftL(int x) {
        super(shapeType.LEFTL, x, -2);
    }

    @Override
    public void rotateRight() {
        // TODO: Adjust rotation so that it cannot rotate if it is on the bottom
        int centerX = getCenterPoint().getX();
        if (centerX < 8 && centerX > 0) {
            super.rotateLeft();
            // decrementY();
        }
    }

    @Override
    public void rotateLeft() {
        int centerY = getCenterPoint().getX();
        if (centerY < 8 && centerY > 1) {
            super.rotateRight();
            // decrementY();
        }
    }
}