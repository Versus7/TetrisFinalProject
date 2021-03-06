package Views;
import java.awt.*;
import javax.swing.*;

// file imports
import Models.Board;
import Models.Leaderboard;
import Models.Block;

/**
 * Credit for custom opacity: https://stackoverflow.com/questions/11552092/changing-image-opacity
 */

public class TetrisWindowPanel extends JPanel {
    private Board board = new Board();
    public static Double SQUAREWIDTH = 10.0;
    private Leaderboard leaderboard = new Leaderboard();
    private boolean gamePaused = false;

    public TetrisWindowPanel() {
        JPanel everything = new JPanel();
        everything.setLayout(new GridLayout(20, 10));

        add(everything);
    }

    public void setPause(boolean pause) {
        gamePaused = pause;
        repaint();
    }

    public boolean getPausedStatus() {
        return gamePaused;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.black);

        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(4));
        g2.setColor(board.getCurrentPiece().getColor());

        SQUAREWIDTH = (double)(getHeight()) / 20.0;

        // border
        g2.drawRect(0, 0, (int)(SQUAREWIDTH*10.0), (int)(SQUAREWIDTH*20.0));

        // grid
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(0.5f));
        for (int i = 0; i < 10; i++) {		        
            for (int j = 0; j < 20; j++) {		       
                g.drawRect((int)(i*SQUAREWIDTH), (int)(j*SQUAREWIDTH), SQUAREWIDTH.intValue(), SQUAREWIDTH.intValue()); 
            }
        }	

        g2.setStroke(new BasicStroke(4));
        g2.setColor(board.getCurrentPiece().getColor());
        
        // Current Piece
        board.getCurrentPiece().draw(g2);

        // Drawing the whole board
        for (Block b: board.getAllBlocks()) {
            b.draw(g2);
        }

        // Ghost Piece
        board.updateGhost();
        board.getGhostPiece().draw(g2);

        // Game pause screen
        if (gamePaused) {
            g2.setColor(Color.black);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.white);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setFont(new Font("Arial", Font.BOLD, (int)(SQUAREWIDTH*2.0)));
            g2.drawString("PAUSED", SQUAREWIDTH.intValue(), getHeight()/2);

            // Draw the score and the level count below
            g2.setFont(new Font("Arial", Font.BOLD, (int)(SQUAREWIDTH*1.0)));
            g2.drawString("Score: " + board.getStats().getScore(), SQUAREWIDTH.intValue(), getHeight() - (int)(SQUAREWIDTH*1.0));
            g2.drawString("Level: " + board.getStats().getLevel(), SQUAREWIDTH.intValue(), getHeight() - (int)(SQUAREWIDTH*2.0));
        }

    }

    public Board getBoard() {
        return board;
    }

    // returns true if the user wants to play again
    public boolean endGame() {
        if(leaderboard.checkScore(board.getStats().getScore())) {
            String s = JOptionPane.showInputDialog(this, "New high score! Enter your initials: ", "New High Score", JOptionPane.INFORMATION_MESSAGE);

            if (s.length() > 3) {
                s = s.substring(0, 3);
            }
            leaderboard.addScore(s.toUpperCase(), board.getStats().getScore());

        }

        JOptionPane.showMessageDialog(this, leaderboard.getRankings(), "Leaderboard", JOptionPane.PLAIN_MESSAGE);

        // The following 2 lines of code were modified from the Oracle documentation for the JOptionPane class
        // https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#create
        Object[] options = {"Play Again", "Quit Game"};
        int n = JOptionPane.showOptionDialog(this,"Play Again?","Another Round", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (n == 0) {
            board = new Board();
            return true;
        } else {
            System.exit(0);
            return false;
        }
    }
}