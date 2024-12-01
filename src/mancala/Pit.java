package mancala;
import java.awt.Dimension;

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
}
