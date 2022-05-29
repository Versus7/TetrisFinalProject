package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class IBlock extends Piece {
    public IBlock(int x) {
        super(shapeType.LINE, x, -3);
    }
}