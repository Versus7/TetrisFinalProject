
public class IBlock extends Piece {
    public IBlock(int x, int y) {
        super(shapeType.LINE, x, y);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void rotateRight() {
        Coordinate originalLowest = getCenterPoint();
        System.out.println("Original center: " + originalLowest);
        double degrees = Math.toRadians(-90);
        for (int i = 0; i < 4; i++) {
            int originalX = getShape().get(i).getCoords().getX();
            int originalY = getShape().get(i).getCoords().getY();

            getShape().get(i).getCoords().setX(Math.abs((int)(originalX*Math.cos(degrees)-originalY*Math.sin(degrees))));
            getShape().get(i).getCoords().setY(Math.abs((int)(originalX*Math.sin(degrees)+originalY*Math.cos(degrees))));

        }

        // fix values
        System.out.println("Rotated center: " + getCenterPoint());
        int offsetX = originalLowest.getX() - getCenterPoint().getX();
        int offsetY = originalLowest.getY() - getCenterPoint().getY();

        for (int i = 0; i < 4; i++) {
            getShape().get(i).changeX(offsetX);
            getShape().get(i).changeY(offsetY);
        }
        System.out.println("Rotated: " + getShape().toString());
    }

}