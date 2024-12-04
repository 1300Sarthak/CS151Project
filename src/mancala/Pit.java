package mancala;

import java.awt.*;
import javax.swing.*;

public class Pit extends JPanel {
    private final String pitName;
    private int stoneCount;
    private final boolean isMancala;
    private final int index;

    public Pit(String pitName, int stoneCount, boolean isMancala, int index) 
    {
        this.pitName = pitName;
        this.stoneCount = stoneCount;
        this.isMancala = isMancala;
        this.index = index;
        
        setOpaque(false);
       
        if (isMancala) {
            setPreferredSize(new Dimension(100, 300));
        } else {
            setPreferredSize(new Dimension(80, 80));
        }
    }
    
/**
 * Sarthak Sethi
 * Returns the index of this pit
 *
 * @return the index of the pit
 */
    public int getIndex()
    {
        return index;
    }
    
/**
 * Sarthak Sethi
 * Returns the current number of stones in this pit
 *
 * @return the stone count of the pit
 */
    public int getStoneCount()
    {
        return stoneCount;
    }

/**
 * Sarthak Sethi
 * Sets the number of stones in this pit and then calls repaint to update the display
 *
 * @param stoneCount the new stone count of the pit
 */
    public void setStoneCount(int stoneCount)
    {
        this.stoneCount = stoneCount;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (isMancala) {
            g2d.setColor(new Color(126, 100, 70));
            g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 50, 50);
        } else {
            g2d.setColor(new Color(255, 215, 0));
            g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20);
        }

        // draw stones in the pits
        g2d.setColor(Color.BLACK);  // stone color
        int x = 20, y = 20;  // stone position
        int stoneDiameter = 20;  // size of  stone

        for (int i = 0; i < stoneCount; i++) {
            g2d.fillOval(x, y, stoneDiameter, stoneDiameter);
            x += stoneDiameter + 5;
            if (x + stoneDiameter > getWidth() - 20) {
                x = 20;
                y += stoneDiameter + 5;
            }
        }

        g2d.setColor(Color.WHITE);
        FontMetrics metrics = g2d.getFontMetrics();
        int labelWidth = metrics.stringWidth(pitName);
        int xLabel = (getWidth() - labelWidth) / 2;
        int yLabel = getHeight() - 10;
        g2d.drawString(pitName, xLabel, yLabel);
    }
}