package mancala;

import java.awt.*;
import javax.swing.*;

public class Pit extends JPanel {
	private final String pitName;
	private int stoneCount;
    private final boolean isMancala;
    private int index;

    public Pit(String pitName, int stoneCount, boolean isMancala, int index) 
    {
        this.index = index;
    	this.pitName = pitName;
        this.stoneCount = stoneCount;
        this.isMancala = isMancala;
        setOpaque(false);
        if (isMancala) {
            setPreferredSize(new Dimension(100, 300));
        } else {
            setPreferredSize(new Dimension(80, 80));
        }
    }
    
    public int getIndex()
    {
    	return index;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // color of pit background
        if (isMancala) {
            g2d.setColor(new Color(126, 100, 70));
        } else {
            g2d.setColor(new Color(255, 215, 0));  
        }
        g2d.fillRoundRect(10, 10, getWidth() - 20, getHeight() - 20, 20, 20); 

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
        g2d.drawString(pitName, xLabel, yLabel);  // draw  label
    }

    // Update stone count (e.g., for when stones are moved or changed)
    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
        repaint();  // repaint to get new stone count
    }

    public int getStoneCount() {
        return stoneCount;
    }


}
