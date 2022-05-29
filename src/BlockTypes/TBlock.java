package BlockTypes;
import Models.Piece;
import Models.shapeType;

public class TBlock extends Piece {
    public TBlock(int x) {
        super(shapeType.PYRAMID, x, -2);
    }
}