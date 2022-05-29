package Views;
import java.awt.*;
import javax.swing.*;

// file imports
import Models.Board;
import Models.Leaderboard;
import Models.Block;

/**
 * Credit for Ghost Piece's opacity: https://stackoverflow.com/questions/11552092/changing-image-opacity
 */

public class TetrisWindowPanel extends JPanel {
    private Board board = new Board();
    public static Double SQUAREWIDTH = 10.0;
    private Leaderboard leaderboard = new Leaderboard();

    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);

        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(4));
        g2.setColor(board.getCurrentPiece().getColor());

        SQUAREWIDTH = (double)(getHeight()) / 20.0;
        g2.drawRect(0, 0, (int)(SQUAREWIDTH*10.0), (int)(SQUAREWIDTH*20.0));

        board.getCurrentPiece().draw(g2);

        // Drawing the whole board
        for (Block b: board.getAllBlocks()) {
            b.draw(g2);
        }


        // Dealing with the ghost piece at the bottom
        // See credit given above for the following 2 lines of code
        float opacity = 0.75f;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        board.redrawGhost();
        board.getGhostPiece().draw(g2);

    }

    public Board getBoard() {
        return board;
    }

    public void endGame() {
        System.out.println("End game fucntion from TetrisWindowPanel");
        
        if(leaderboard.checkScore(board.getStats().getScore())) {
            String s = JOptionPane.showInputDialog(this, "New high score! Enter your initials: ", "New High Score", JOptionPane.INFORMATION_MESSAGE);

            if (s.length() > 3) {
                s = s.substring(0, 3);
            }
            leaderboard.addScore(s.toUpperCase(), board.getStats().getScore());

        }

        JOptionPane.showMessageDialog(this, leaderboard.getRankings(), "Leaderboard", JOptionPane.PLAIN_MESSAGE);
    }
}