package mancala;
import javax.swing.*;
import java.awt.*;

public class MancalaView extends JPanel implements MancalaListener{
    private MancalaBoard board;
    private JButton undoButton;
    private JButton formatOne;
    private JButton formatTwo;

    public MancalaView(MancalaBoard board) {
        this.board = board;
        //setup the GUI stuff here such as the pits and the Mancalas
    }
    
    public JButton getUndoButton()
    {
    	return undoButton;
    }
    
    public JButton getFormatOneButton()
    {
    	return formatOne;
    }
    
    public JButton getFormatTwoButton()
    {
    	return formatTwo;
    }

    public void refresh() {
        //this will update the view based on what is currently going on the board
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //I will draw the board, pits, and stones here
    }
}
