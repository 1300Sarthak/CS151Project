package mancala;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MancalaView extends JFrame implements MancalaListener{
    private final JPanel viewPanel; 
    private final MancalaBoard board;
    private final ArrayList<Pit> pits;
    private JPanel panel;
    private JButton undoButton;
    private JButton nextTurnButton;

    public MancalaView(MancalaBoard board, String format) {
        this.board = board;
        pits = new ArrayList<Pit>(14); 
        viewPanel = new JPanel(new BorderLayout());
        if ("Format1".equalsIgnoreCase(format)) {
            viewPanel.add(new Format1(board).getPanel(), BorderLayout.CENTER);
        } else if ("Format2".equalsIgnoreCase(format)) {
            viewPanel.add(new Format2(board).getPanel(), BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Invalid format");
        }

        //initialize undo and next turn button
        undoButton = new JButton("Undo");
        nextTurnButton = new JButton("Next Turn");
    }
    
    public void changed()
    {
    	board.currentBoardState();
    	revalidate();
    	repaint();
    }
    
    public JButton getUndoButton()
    {
    	return undoButton;
    }
    
    public JButton getNextTurnButton()
    {
    	return nextTurnButton;
    }
    public ArrayList<Pit> getPitList()
    {
    	return pits;
    }
    
    private void initializeBoard(MancalaBoard board) {
    
    	//Format Strategy might need to go here.
        JPanel pitsPanel = new JPanel(new GridLayout(2, 6));
        pitsPanel.setOpaque(false);

        Pit mancalaA = new Pit("Mancala A", board.currentBoardState().get(6), true, 6);
        Pit mancalaB = new Pit("Mancala B", board.currentBoardState().get(13), true, 13);

        panel.add(mancalaB, BorderLayout.WEST);
        panel.add(mancalaA, BorderLayout.EAST);

        String[] pitLabels = {"A1", "A2", "A3", "A4", "A5", "A6", "B6", "B5", "B4", "B3", "B2", "B1"};

        for (int i = 0; i < 14; i++) {
            if (i == 6 || 1 == 13)
            {
            	pits.add(null);
            }
            else
            {
            	Pit pit = new Pit(pitLabels[i], board.currentBoardState().get(i), false, i);
                pits.add(pit);
                pitsPanel.add(pit);
            }
            pits.set(6, mancalaA);
            pits.set(13, mancalaB);
        }

        panel.add(pitsPanel, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return viewPanel;
    }
    
    public void switchFormat(String format) {
        viewPanel.removeAll();
        if ("Format1".equalsIgnoreCase(format)) {
            viewPanel.add(new Format1(board).getPanel(), BorderLayout.CENTER);
        } else if ("Format2".equalsIgnoreCase(format)) {
            viewPanel.add(new Format2(board).getPanel(), BorderLayout.CENTER);
        } else {
            throw new IllegalArgumentException("Invalid format");
        }
        viewPanel.revalidate();
        viewPanel.repaint();
    }
}
