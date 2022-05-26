package Views;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ScorePanel extends JPanel {
    private int score;
    private int level;
    private JTextPane scoreLabel;
    private JTextPane levelLabel;


    public ScorePanel() {
        score = 0;
        level = 1;

        setLayout(new GridLayout(2, 1));
        setBackground(Color.black);
        setForeground(Color.white);

        scoreLabel = new JTextPane();
        scoreLabel.setText(score + "");
        scoreLabel.setEditable(false);
        scoreLabel.setBackground(Color.blue);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setFont(new Font("System", Font.PLAIN, 20));
        
        // center the text in scoreLabel
        StyledDocument doc = scoreLabel.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
        add(scoreLabel);

        // Level Label

        levelLabel = new JTextPane();
        levelLabel.setText("Level: " + level);
        levelLabel.setEditable(false);
        levelLabel.setBackground(Color.black);
        levelLabel.setForeground(Color.white);
        levelLabel.setFont(new Font("System", Font.PLAIN, 10));
        add(levelLabel);

        // center text in levelLabel
        StyledDocument doc2 = levelLabel.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);
    }
    
    public void incrementScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }

    public void incrementLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);
        g.setFont(new Font("System", Font.BOLD, 20));

        scoreLabel.setText(score + "");
        levelLabel.setText("Level " + level);
    }
    
}
