package Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Credit for centering text in a JTextPane: https://stackoverflow.com/questions/3213045/centering-text-in-a-jtextarea-or-jtextpane-horizontal-text-alignment
 */

public class ScorePanel extends JPanel {
    private JTextPane scoreLabel;
    private JTextPane levelLabel;
    
    private TetrisWindowPanel game;


    public ScorePanel(TetrisWindowPanel game) {
        this.game = game;

        setLayout(new GridLayout(2, 1));
        setBackground(Color.black);
        setForeground(Color.white);

        // Score Label
        
        scoreLabel = new JTextPane();
        scoreLabel.setText(game.getBoard().getStats().getScore() + "");
        scoreLabel.setEditable(false);
        scoreLabel.setBackground(Color.black);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("System", Font.PLAIN, 30));
        
        // center the text in scoreLabel
        StyledDocument doc = scoreLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        add(scoreLabel);

        // Level Label

        levelLabel = new JTextPane();
        levelLabel.setText("Level: " + game.getBoard().getStats().getLevel());
        levelLabel.setEditable(false);
        levelLabel.setBackground(Color.black);
        levelLabel.setForeground(Color.white);
        levelLabel.setFont(new Font("System", Font.PLAIN, 20));
        add(levelLabel);

        // center text in levelLabel
        StyledDocument doc2 = levelLabel.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.setFont(new Font("System", Font.BOLD, 20));

        scoreLabel.setText(game.getBoard().getStats().getScore() + "");
        levelLabel.setText("Level " + game.getBoard().getStats().getLevel());
    }
    
}
