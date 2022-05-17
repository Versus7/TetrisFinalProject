package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class SquareBlock extends Piece {
    public SquareBlock(int x) {
        super(shapeType.SQUARE, x, -2);
    }

    // Squares don't rotate!
    @Override
    public void rotateRight() {}

    @Override
    public void rotateLeft() {}
}